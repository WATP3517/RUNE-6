package com.sxt.sfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SfxPlayer {
    private Clip clip;
    public String filePath;
    private boolean isPlaying;
    
    public SfxPlayer(String filePath) {
        this.filePath = filePath;
        this.isPlaying = false;
        initAudio();
    }
    
    private void initAudio() {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.err.println("音频文件不存在: " + filePath);
                return;
            }
            
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            System.err.println("不支持的音频格式: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("音频文件读取错误: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("音频设备不可用: " + e.getMessage());
        }
    }
    
    public void play() {
        if (clip == null) return;
        
        stop(); 
        clip.setFramePosition(0); 
        clip.start();
        isPlaying = true;
    }
    
    public void playLoop() {
        if (clip == null) return;
        
        stop();
        clip.setFramePosition(0); 
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
        isPlaying = true;
    }
    
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            isPlaying = false;
        }
    }
    
    public void close() {
        stop();
        if (clip != null) {
            clip.close();
        }
    }

    public void change(String path) {
        this.filePath = path;
        initAudio();
    }
    
    public boolean isPlaying() {
        return isPlaying;
    }
}
    