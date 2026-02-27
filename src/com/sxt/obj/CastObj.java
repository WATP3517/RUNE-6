package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;

import com.sxt.GameWin;

public class CastObj extends GameObjAdv
{

    public CastObj()
    {
        super();
    }

    public CastObj(Image img, int x, int y, int width, int height, double speed, GameWin frame)
    {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y -= speed;
    }

}
