package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.sxt.GameWin;

public class HudObj extends GameObjAdv
{
    public HudObj()
    {
        super();
    }
    public HudObj(Image img, int x, int y, int width, int height, double speed, GameWin frame)
    {
        super(img, x, y, width, height, speed, frame);
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
