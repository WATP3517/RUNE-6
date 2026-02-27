package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

public class RectObj extends GameObjAdv
{
    int cache = 0;
    String type = "R";
    public RectObj()
    {
        super();
    }
    public RectObj(Image img, int x, int y, int width, int height, double speed, GameWin frame)
    {
        super(img, x, y, width, height, speed, frame);
    }

    public void setType(String t)
    {
        this.type = t;
    }

    public void SetCache(int c)
    {
        this.cache = c;
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
    public void paintSelf(Graphics gVfx) {
        
        if(cache > 0 && GameWin.count % 1 == 0)
            cache -= 1;
        if (type.equals("W"))
            img = GameUtils.VFX_W[10-cache];
        else if (type.equals("R"))
            img = GameUtils.VFX_R[10-cache];
        else if (type.equals("G"))
            img = GameUtils.VFX_G[10-cache];
        else if (type.equals("RM"))
            img = GameUtils.VFX_RM[10-cache];
        else if (type.equals("Hit"))
            img = GameUtils.VFX_Hit[cache];
        super.paintSelf(gVfx);
    }
    
}
