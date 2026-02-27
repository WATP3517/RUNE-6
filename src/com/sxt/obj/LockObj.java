package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

public class LockObj extends GameObjAdv
{
    boolean isLock = false;
    public LockObj()
    {
        super();
    }

    public LockObj(Image img, int x, int y, int width, int height, int state, GameWin frame)
    {
        super(img, x, y, width, height, state, frame);
    }

    public void setLock(boolean isl)
    {
        if (isl == true)
        {
            this.img = GameUtils.Lock;
            GameWin.SFXadd("assets/Sound/Lockon.wav");
        }
        else
        {
            this.img = null;
            GameWin.SFXadd("assets/Sound/Lockoff.wav");
        }
        this.isLock = isl;
    }

    public boolean getLock()
    {
        return this.isLock;
    }
    @Override
    public Image getImg() {
        return super.getImg();
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
    }
    
}
