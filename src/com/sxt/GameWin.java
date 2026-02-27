package com.sxt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import com.sxt.obj.BarObj;
import com.sxt.obj.BossDiceObj;
import com.sxt.obj.BulletObj;
import com.sxt.obj.CastObj;
import com.sxt.obj.DiceObj;
import com.sxt.obj.GameObjAdv;
import com.sxt.obj.HudObj;
import com.sxt.obj.LockObj;
import com.sxt.obj.MagObj;
import com.sxt.obj.RectObj;
import com.sxt.obj.SubMonObj;
import com.sxt.obj.TextBarObj;
import com.sxt.sfx.SfxPlayer;
import com.sxt.utils.EventManager.TimerStep;
import com.sxt.utils.EventManager;
import com.sxt.utils.FontLoader;
import com.sxt.utils.GameUtils;

public class GameWin extends JFrame {
    Image offScreenImage = null;
    Image offScreenVfx = null;
    int state = 0;
    int bossStage = 0;
    int width = 960;
    int height = 572;
    int movecache = 0;
    int rollcache = 0;
    int atk = 0, def = 0, mot = 0, hel = 0;
    public static int hp = 32, hpmax = 32, b_hp = 84, b_maxhp = 84;
    boolean isStage = false;
    public static int count = 0;

    SfxPlayer BgmManager = new SfxPlayer("assets/Sound/Echoes of the Icy Night.wav");
    SfxPlayer BossSpeak = new SfxPlayer("assets/Sound/BossSpeak.wav");
    public static SfxPlayer DiceSFX = new SfxPlayer("assets/Sound/DiceRoll1.wav");
    SfxPlayer BossHit = new SfxPlayer("assets/Sound/MetalHit.wav");
    SfxPlayer LoadSound = new SfxPlayer("assets/Sound/Load.wav");

    HudObj Edge = new HudObj(GameUtils.Edge, 0, 25, 960, 540, 0, this);
    HudObj Monster = new HudObj(GameUtils.MonsterSleep, 220, 114, 576, 324, 0, this);
    HudObj Title = new HudObj(GameUtils.DiceTitle, 0, 25, 960, 540, 0, this);
    HudObj Line = new HudObj(GameUtils.Line, 0, 25, 960, 540, 0, this);
    BarObj BossHpBar = new BarObj(GameUtils.BossHpBar, 175, 97, 608, 12, 0, this);
    BarObj PlayerHpBar = new BarObj(GameUtils.PlayerHpBar, 32, 457, 148, 72, 0, this);
    BarObj PlayerMotionBar = new BarObj(GameUtils.PlayerMotionBar, 40, 406, 197, 10, 0, this);
    MagObj RollMag = new MagObj(GameUtils.Mag[3], 808, 481, 80, 24, 0, this);
    TextBarObj BossDialog = new TextBarObj(null, 650, 260, 0, 0, 0, this);
    TextBarObj BossName = new TextBarObj(null, 360, 60, 0, 0, 0, this);
    TextBarObj ATKhud = new TextBarObj(null, 728, 420, 0, 0, 0, this);
    TextBarObj HELhud = new TextBarObj(null, 846, 420, 0, 0, 0, this);
    TextBarObj HPhud = new TextBarObj(null, 206, 500, 0, 0, 0, this);
    RectObj VFX = new RectObj(null, 0, 0, 960, 572, 0, this);
    RectObj BhitVFX = new RectObj(null, 220, 114, 576, 324, 0, this);
    RectObj HitVFX = new RectObj(null, 340, 104, 300, 300, 0, this);

    DiceObj Dice[] = {
            new DiceObj(null, 324, 488, 32, 32, 0, this),
            new DiceObj(null, 416, 488, 32, 32, 0, this),
            new DiceObj(null, 513, 488, 32, 32, 0, this),
            new DiceObj(null, 605, 488, 32, 32, 0, this)
    };
    LockObj Lock[] = {
            new LockObj(null, 312, 534, 56, 28, 0, this),
            new LockObj(null, 404, 534, 56, 28, 0, this),
            new LockObj(null, 500, 534, 56, 28, 0, this),
            new LockObj(null, 592, 534, 56, 28, 0, this)
    };
    BossDiceObj BDice[] = {
            new BossDiceObj(GameUtils.BossDice[0], 67, 80, 52, 20, 0, this),
            new BossDiceObj(GameUtils.BossDice[0], 841, 80, 52, 20, 0, this),
    };
    SubMonObj SubMon[] = {
            new SubMonObj(GameUtils.SubMon, 448, 300, 64, 64, 5, this),
            new SubMonObj(GameUtils.SubMon, 446, 300, 64, 64, 5, this)
    };

    Font DefaultFont = new java.awt.Font("Megamax Jonathan Too", 1, 20);

    public void launch() {
        this.setVisible(true);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("RUNE-6");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FontLoader.loadFont("assets/Fonts/");
        
        GameUtils.HUDObjListAdv.add(Title);
        BgmManager.playLoop();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (isStage == true) {
                    System.out.println("Key Aborted");
                    return;
                }
                if (state == 0) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        Init();
                        VFX.setType("W");
                        VFX.SetCache(10);
                        BhitVFX.setType("RM");
                        Stage1();
                    }
                }
                if (state == 1) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && movecache > 0
                            && Dice[0].getRollCount() + Dice[1].getRollCount()
                                    + Dice[2].getRollCount() + Dice[3].getRollCount() == 0
                            && Dice[0].getImg() != null) {
                        PlayerSum();
                        rollcache = 3;
                        for (int i = 0; i < Dice.length; i++)
                            Dice[i].setImg(null);
                        for (int i = 0; i < Lock.length; i++)
                            Lock[i].setLock(false);
                        RollMag.setImg(GameUtils.Mag[rollcache]);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_R && rollcache > 0
                            && Dice[0].getRollCount() + Dice[1].getRollCount()
                                    + Dice[2].getRollCount() + Dice[3].getRollCount() == 0
                            && BDice[0].getRollCount() == 0) {
                        for (int i = 0; i < Dice.length; i++)
                            if (Dice[i].getRollCount() == 0 && !Lock[i].getLock())
                                Dice[i].roll(12);
                        rollcache--;
                        RollMag.setImg(GameUtils.Mag[rollcache]);
                    }
                    if (Dice[0].getImg() != null && Dice[0].getRollCount() + Dice[1].getRollCount()
                            + Dice[2].getRollCount() + Dice[3].getRollCount() == 0) {
                        if (e.getKeyCode() == KeyEvent.VK_1)
                            Lock[0].setLock(!Lock[0].getLock());
                        if (e.getKeyCode() == KeyEvent.VK_2)
                            Lock[1].setLock(!Lock[1].getLock());
                        if (e.getKeyCode() == KeyEvent.VK_3)
                            Lock[2].setLock(!Lock[2].getLock());
                        if (e.getKeyCode() == KeyEvent.VK_4)
                            Lock[3].setLock(!Lock[3].getLock());
                        if (e.getKeyCode() == KeyEvent.VK_J) {
                            LoadSound.play();
                            atk+= 5;
                            ATKhud.setText(Integer.toString(atk), Color.WHITE, 20);
                        }
                        if (e.getKeyCode() == KeyEvent.VK_K) {
                            LoadSound.play();
                            mot+= 5;
                            if (mot > 8)
                                mot = 8;
                            PlayerMotionBar.setValue(mot);
                        }
                    }

                }
            }
        });
        while (true) {
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(width, height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0, 0, width, height);

        for (int i = 0; i < GameUtils.gameObjListAdv.size(); i++) {
            GameUtils.gameObjListAdv.get(i).paintSelf(gImage);
        }
        for (int i = 0; i < GameUtils.HUDObjListAdv.size(); i++) {
            GameUtils.HUDObjListAdv.get(i).paintSelf(gImage);
        }
        GameUtils.gameObjListAdv.removeAll(GameUtils.removeObjListAdv);
        GameUtils.HUDObjListAdv.removeAll(GameUtils.removeObjListAdv);
        if (state == 0) {
            gImage.setColor(Color.WHITE);
            gImage.setFont(DefaultFont);
            if (FontLoader.getLoadedFontCount() != 0)
                gImage.drawString("PRESS space To START", 328, 400);
        }
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void SFXadd(String path) {
        SfxPlayer sfxPlayer = new SfxPlayer(path);
        sfxPlayer.play();
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }

    void Stage1() {
        isStage = true;
        bossStage = 1;
        Monster.setImg(GameUtils.MonsterSleep);
        BgmManager.stop();
        EventManager.clearKeyEvents();
        TimerStep[] steps = {
                new TimerStep(1500, () -> {
                    BossSpeak.stop();
                    BossSpeak.change("assets/Sound/BossSpeakShorter.wav");
                    BossSpeak.play();
                    BossDialog.setText("...", Color.WHITE, 15);
                }),
                new TimerStep(1500, () -> {
                    BossSpeak.change("assets/Sound/BossSpeakShorter.wav");
                    BossSpeak.play();
                    BossDialog.setText("Traveler ...", Color.WHITE, 15);
                }),
                new TimerStep(1500, () -> {
                    BossSpeak.change("assets/Sound/BossSpeakShort.wav");
                    BossSpeak.play();
                    BossDialog.setText("You shouldn't be here.", Color.WHITE, 15);
                }),
                new TimerStep(1500, () -> {
                    BossSpeak.stop();
                    BossSpeak.change("assets/Sound/BossSpeakShorter.wav");
                    BossSpeak.play();
                    BossDialog.setText("please ...", Color.WHITE, 15);
                }),
                new TimerStep(1200, () -> {
                    BossSpeak.play();
                    BossDialog.setText("Turn back.", Color.RED, 15);
                }),
                new TimerStep(100, () -> {
                    Monster.setImg(GameUtils.MonsterFight);
                    BossName.setText("Lera the Bee", Color.WHITE, 28);
                    BgmManager.change("assets/Sound/Lera's Theme.wav");
                    BgmManager.playLoop();
                }),
                new TimerStep(400, () -> {
                    BossDialog.setText("", Color.WHITE, 15);
                    isStage = false;
                    EventManager.clearKeyEvents();
                })
        };
        EventManager.executeTimerSequence(steps);
    }

    void Stage2() {
        isStage = true;
        bossStage = 2;
        Monster.setImg(GameUtils.MonsterSleep);
        BgmManager.stop();
        EventManager.clearKeyEvents();
        TimerStep[] steps = {
                new TimerStep(1500, () -> {
                    BossSpeak.stop();
                    BossSpeak.change("assets/Sound/BossSpeakShorter.wav");
                    BossSpeak.play();
                    BossDialog.setText("Well done ...", Color.WHITE, 15);
                }),
                new TimerStep(1500, () -> {
                    BossSpeak.change("assets/Sound/BossSpeakShort.wav");
                    BossSpeak.play();
                    BossDialog.setText("But I'm sorry", Color.WHITE, 15);
                }),

                new TimerStep(1500, () -> {
                    BossSpeak.stop();
                    BossSpeak.change("assets/Sound/BossSpeakShorter.wav");
                    BossSpeak.play();
                    BossDialog.setText("Your journey", Color.WHITE, 15);
                }),
                new TimerStep(1200, () -> {
                    BossSpeak.play();
                    BossDialog.setText("Ends here.", Color.RED, 15);
                }),
                new TimerStep(100, () -> {
                    Monster.setImg(GameUtils.MonsterFight);
                    GameUtils.gameObjListAdv.add(SubMon[0]);
                    GameUtils.gameObjListAdv.add(SubMon[1]);
                    BgmManager.change("assets/Sound/Lera's Challenge.wav");
                    BgmManager.playLoop();
                }),
                new TimerStep(400, () -> {
                    BossDialog.setText("", Color.WHITE, 15);
                    EventManager.clearKeyEvents();
                    isStage = false;
                })
        };
        EventManager.executeTimerSequence(steps);
    }

    int stage = 0;

    void PlayerSum() {
        isStage = true;

        EventManager.clearKeyEvents();
        TimerStep[] steps = {
                new TimerStep(0, () -> {
                    for (int i = 0; i < 4; i++) {
                        int type = Dice[i].getType() + 1;
                        int value = Dice[i].getValue() + 1;
                        if (type == 1)
                            atk += value;
                        else if (type == 2)
                            def += value;
                        else if (type == 3)
                            hel += value;
                        else if (type == 4)
                            mot += value;
                    }
                    System.out.println(atk + " " + def + " " + hel + "/ " + mot);
                    if (mot > 8)
                        mot = 8;
                }),
                new TimerStep(0, () -> {
                    PlayerMotionBar.setValue(mot);
                    LoadSound.play();
                }),
                new TimerStep(600, () -> {
                    ATKhud.setText(Integer.toString(atk), Color.WHITE, 20);
                    LoadSound.play();
                }),
                new TimerStep(600, () -> {
                    HELhud.setText(Integer.toString(hel), Color.WHITE, 20);
                    LoadSound.play();
                }),
                new TimerStep(600, () -> {
                    if (mot >= 8) {
                        System.out.println("PlayerMotion: " + "Attack:" + atk + " Defence:" + def + " Heal:" + hel);
                        if (hel > 0) {
                            hp += hel;
                            VFX.setType("G");
                            VFX.SetCache(10);
                            BossHit.change("assets/Sound/Heal.wav");
                            BossHit.play();
                            if (hp > hpmax)
                                hp = hpmax;
                            hel = 0;
                            HELhud.setText(Integer.toString(hel), Color.WHITE, 20);
                            PlayerHpBar.setValue(hp);
                            HPhud.setText(Integer.toString(hp), Color.WHITE, 25);
                        }
                    }
                }),
                new TimerStep(600, () -> {
                    if (mot >= 8) {
                        if (atk > 0) {
                            HitVFX.setType("Hit");
                            HitVFX.SetCache(7);
                        }
                    }
                }),
                new TimerStep(200, () -> {
                    if (mot >= 8) {
                        if (atk > 0) {
                            BossHit.change("assets/Sound/FleshHit.wav");
                            BossHit.play();
                            BhitVFX.setType("RM");
                            BhitVFX.SetCache(10);
                            if (b_hp > 42 && b_hp - atk <= 42)
                                stage = 2;
                            if (b_hp > 0 && b_hp - atk <= 0)
                                stage = 3;
                            b_hp -= atk;
                            if (b_hp < 0)
                                b_hp = 0;
                            BossHpBar.setValue(b_hp);
                            atk = 0;
                            ATKhud.setText(Integer.toString(atk), Color.WHITE, 20);
                        }
                        mot = 0;
                        PlayerMotionBar.setValue(mot);
                    }
                }),
                new TimerStep(0, () -> {
                    if (stage == 0) {
                        BossRoll();
                    } else if (stage == 2) {
                        Stage2();
                        stage = 0;
                    }
                    else if (stage == 3) {
                        Stage3();
                        stage = 0;
                    }
                })
        };
        EventManager.executeTimerSequence(steps);
    }

    void BossRoll() {
        EventManager.clearKeyEvents();
        TimerStep[] steps = {
                new TimerStep(0, () -> {
                    for (int i = 0; i < BDice.length; i++)
                        BDice[i].roll(12);
                }),
                new TimerStep(1200, () -> {
                    if (bossStage == 1)
                    {
                        GameUtils.gameObjListAdv.add(new BulletObj(GameUtils.BulletA, 62, 140, 60, 60, 40, this));
                        GameUtils.gameObjListAdv.add(new BulletObj(GameUtils.BulletA, 838, 140, 60, 60, 40, this));
                    }
                    else if (bossStage == 2)
                    {
                        GameUtils.gameObjListAdv.add(new BulletObj(GameUtils.BulletB, 62, 140, 60, 60, 40, this));
                        GameUtils.gameObjListAdv.add(new BulletObj(GameUtils.BulletB, 838, 140, 60, 60, 40, this));
                    }
                }),
                new TimerStep(250, () -> {
                    System.out.println("BossVal " + (BDice[0].getValue() + BDice[1].getValue()));
                    def -= (BDice[0].getValue() + BDice[1].getValue());
                    BossHit.change("assets/Sound/MetalHit.wav");
                    VFX.setType("W");
                    if (def < 0) {
                        BossHit.change("assets/Sound/FleshHit.wav");
                        VFX.setType("R");
                        hp += def;
                        def = 0;
                    }
                    BossHit.play();
                    VFX.SetCache(10);
                    PlayerHpBar.setValue(hp);
                    HPhud.setText(Integer.toString(hp), Color.WHITE, 25);
                    def = 0;
                }),
                new TimerStep(100, () -> {
                    isStage = false;
                })
        };
        EventManager.executeTimerSequence(steps);
    }

    void Stage3() {
        isStage = true;
        bossStage = 3;
        Monster.setImg(GameUtils.MonsterSleep);
        BgmManager.stop();
        EventManager.clearKeyEvents();
        TimerStep[] steps = {
                new TimerStep(1500, () -> {
                    BossSpeak.stop();
                    BossSpeak.change("assets/Sound/BossSpeakShorter.wav");
                    BossSpeak.play();
                    BossDialog.setText("...", Color.WHITE, 15);
                }),
                new TimerStep(1500, () -> {
                    BossSpeak.change("assets/Sound/BossSpeakShorter.wav");
                    BossSpeak.play();
                    BossDialog.setText("Child's play ends here.", Color.WHITE, 15);
                }),
                new TimerStep(1500, () -> {
                    BossSpeak.change("assets/Sound/BossSpeakShort.wav");
                    BossSpeak.play();
                    BossDialog.setText("And now is time ...", Color.WHITE, 15);
                }),
                new TimerStep(1500, () -> {
                    BossSpeak.stop();
                    BossSpeak.change("assets/Sound/BossSpeakShorter.wav");
                    BossSpeak.play();
                    BossDialog.setText("for the", Color.WHITE, 15);
                }),
                new TimerStep(1000, () -> {
                    BossSpeak.play();
                    BossDialog.setText("True Battle.", Color.RED, 15);
                }),
                new TimerStep(1000, () -> {
                    VFX.setType("W");
                    VFX.SetCache(10);
                    BossDialog.setText("", Color.WHITE, 15);
                    Monster.setImg(GameUtils.MonsterBoost);
                    Monster.setX(200);
                    BossName.setX(180);
                    BossName.setText("Lera, the forth rune apostle", Color.WHITE, 28);
                    BgmManager.change("assets/Sound/Lera's Challenge.wav");
                    BgmManager.playLoop();
                }),
                new TimerStep(4000, () -> {
                    BossSpeak.change("assets/Sound/End.wav");
                    BossSpeak.play();
                    BgmManager.stop();
                    GameUtils.HUDObjListAdv.add(new GameObjAdv(GameUtils.DiceTitle, 0, 0, 10000, 10000, 0,this));
                }),
                new TimerStep(2000, () -> {
                    GameUtils.HUDObjListAdv.add(new CastObj(GameUtils.Castsn,0,0,960,4500,1,this));
                    BgmManager.change("assets/Sound/Echoes of the Icy Night.wav");
                    BgmManager.play();
                }),
                new TimerStep(90000, () -> {
                    BgmManager.stop();
                    this.dispose();
                })
        };
        EventManager.executeTimerSequence(steps);
    }

    void Init() {
        state = 1;
        count = 0;
        movecache = 1;
        rollcache = 3;
        BgmManager.stop();
        GameUtils.gameObjListAdv.add(Line);
        GameUtils.HUDObjListAdv.add(ATKhud);
        GameUtils.HUDObjListAdv.add(HELhud);
        GameUtils.HUDObjListAdv.add(HPhud);
        ATKhud.setText(Integer.toString(atk), Color.WHITE, 20);
        HELhud.setText(Integer.toString(hel), Color.WHITE, 20);
        HPhud.setText(Integer.toString(hp), Color.WHITE, 25);
        GameUtils.HUDObjListAdv.add(Monster);
        GameUtils.HUDObjListAdv.add(BossHpBar);
        GameUtils.HUDObjListAdv.add(PlayerHpBar);
        GameUtils.HUDObjListAdv.add(PlayerMotionBar);
        GameUtils.HUDObjListAdv.add(RollMag);
        GameUtils.HUDObjListAdv.add(BossDialog);
        GameUtils.HUDObjListAdv.add(BossName);
        GameUtils.HUDObjListAdv.add(Edge);
        GameUtils.HUDObjListAdv.add(VFX);
        GameUtils.HUDObjListAdv.add(BhitVFX);
        GameUtils.HUDObjListAdv.add(HitVFX);
        PlayerHpBar.setDirection(1);
        PlayerMotionBar.setDirection(1);
        BossHpBar.setDirection(1);
        PlayerHpBar.setMaxValue(hpmax);
        PlayerHpBar.setValue(hp);
        PlayerMotionBar.setMaxValue(8);
        PlayerMotionBar.setValue(0);
        BossHpBar.setMaxValue(b_maxhp);
        BossHpBar.setValue(b_hp);
        for (int i = 0; i < Dice.length; i++)
            GameUtils.HUDObjListAdv.add(Dice[i]);
        for (int i = 0; i < Lock.length; i++)
            GameUtils.HUDObjListAdv.add(Lock[i]);
        for (int i = 0; i < BDice.length; i++)
            GameUtils.HUDObjListAdv.add(BDice[i]);
        GameUtils.removeObjListAdv.add(Title);
    }
}
