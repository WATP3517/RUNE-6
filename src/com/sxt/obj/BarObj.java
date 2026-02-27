package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.sxt.GameWin;

public class BarObj extends GameObjAdv
{
    int value = 0;
    int maxvalue = 0;
    int direction = 0;

    public BarObj() {
        super();
    }

    public BarObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    public void setDirection(int d) {
        direction = d;
    }

    public void setValue(int v) {
        value = v;
    }

    public void setMaxValue(int mv) {
        maxvalue = mv;
    }

    public void addValue(int v) {
        value += v;
    }

    public void minusValue(int mv) {
        value -= mv;
    }
    @Override
    public Image getImg() {
        return super.getImg();
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    @Override
    public void paintSelf(Graphics gImage) {
        if (direction == 0) {
            gImage.drawImage(img, x, y, width, (int)(height*((double)value/(double)maxvalue)), frame);
        } 
        if (direction == 1) {
            gImage.drawImage(img, x, y, (int)(width*((double)value/(double)maxvalue)), height, frame);
        }
    }
    
}
