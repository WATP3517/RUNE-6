package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.sxt.GameWin;

public class MagObj extends GameObjAdv
{
    int value = 0;
    int maxvalue = 0;

    public MagObj() {
        super();
    }

    public MagObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
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
        super.paintSelf(gImage);
    }
    
}
