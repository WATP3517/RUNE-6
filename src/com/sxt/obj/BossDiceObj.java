package com.sxt.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

public class BossDiceObj extends GameObjAdv
{
    int rollCount = 0;
    int r = 0;
    public BossDiceObj()
    {
        super();
    }
    public BossDiceObj(Image img, int x, int y, int width, int height, double speed, GameWin frame)
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
        if (rollCount > 0 && GameWin.count%3 == 0)
        {
            r = (int)(Math.random()*4+1);
            img = GameUtils.BossDice[r];
            GameWin.DiceSFX.change("assets/Sound/DiceRoll"+((int)Math.random()*3+1)+".wav");
            GameWin.DiceSFX.play();
            rollCount--;
        }
    }

    public int getValue(){
        return r;
    }

    public void roll(int count){
        rollCount = count;
    }
    public int getRollCount() {
        return rollCount;
    }
    
}
