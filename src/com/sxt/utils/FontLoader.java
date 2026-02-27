package com.sxt.utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FilenameFilter;

public class FontLoader {
    static int loadedFontCount = 0;
    public static int loadFontsFromDirectory(String fontDirPath) {
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        File fontDir = new File(fontDirPath);
        
        if (!fontDir.exists() || !fontDir.isDirectory()) {
            System.err.println("目录不存在或不是一个有效的目录: " + fontDirPath);
            return 0;
        }
        
        File[] fontFiles = fontDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return lowercaseName.endsWith(".ttf") || 
                       lowercaseName.endsWith(".ttc") || 
                       lowercaseName.endsWith(".otf");
            }
        });
        
        if (fontFiles == null || fontFiles.length == 0) {
            System.out.println("指定目录下没有找到字体文件");
            return 0;
        }
        
        int loadedCount = 0;
        
        for (File fontFile : fontFiles) {
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                
                ge.registerFont(font);
                
                System.out.println("成功加载字体: " + fontFile.getName());
                loadedCount++;
            } catch (Exception e) {
                System.err.println("加载字体失败 " + fontFile.getName() + ": " + e.getMessage());
            }
        }
        
        return loadedCount;
    }
    
    public static void loadFont(String fontDirectory) {
        int loaded = loadFontsFromDirectory(fontDirectory);
        loadedFontCount = loaded;
        System.out.println("共成功加载 " + loaded + " 个字体文件");
    }

    public static int getLoadedFontCount() {
        return loadedFontCount;
    }
}
    