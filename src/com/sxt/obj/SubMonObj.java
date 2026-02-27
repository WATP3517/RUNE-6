package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.sxt.GameWin;

public class SubMonObj extends GameObjAdv
{
    public SubMonObj()
    {
        super();
    }

    public SubMonObj(Image img, int x, int y, int width, int height, double speed, GameWin frame)
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
        if (x<447)
        {
            if (y>130)
                y-=speed;
            if (x>62)
                x-=speed*2;
        }
        else
        {
            if (y>130)
                y-=speed;
            if (x<834)
                x+=speed*2;
        }
    }
    
}
