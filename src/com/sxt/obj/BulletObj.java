package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

public class BulletObj extends GameObjAdv
{
    public BulletObj() {
        super();
    }

    public BulletObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public Image getImg() {
        // TODO Auto-generated method stub
        return super.getImg();
    }

    @Override
    public Rectangle getRec() {
        // TODO Auto-generated method stub
        return super.getRec();
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        if (y >= 400)
            GameUtils.removeObjListAdv.add(this);
    }
    
}
