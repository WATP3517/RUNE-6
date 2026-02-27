package com.sxt.obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.sxt.GameWin;

public class TextBarObj extends GameObjAdv
{
    String text = "";
    Color color = Color.BLACK;
    int Tsize = 20;
    
    public TextBarObj() {
        super();
    }

    public TextBarObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    public void setText(String t, Color c, int s) {
        this.text = t;
        this.color = c;
        this.Tsize = s;
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintText(gImage, text, color, Tsize);
    }

    @Override
    public void paintText(Graphics gImage, String text, Color color, int Tsize) {
        super.paintText(gImage, text, color, Tsize);
    }
    
}
