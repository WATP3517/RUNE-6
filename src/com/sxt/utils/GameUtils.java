package com.sxt.utils;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.sxt.obj.GameObjAdv;

public class GameUtils {

    public static Image DiceTitle;
    public static Image Edge;
    public static Image MonsterSleep;
    public static Image MonsterFight;
    public static Image MonsterBoost;
    public static Image Line;
    public static Image Lock;
    public static Image PlayerHpBar;
    public static Image PlayerMotionBar;
    public static Image BossHpBar;
    public static Image BulletA;
    public static Image BulletB;
    public static Image SubMon;
    public static Image Castsn;

    public static Image[] Mag = new Image[4];
    public static Image[][] Dice = new Image[4][4];
    public static Image[] BossDice = new Image[5];
    public static Image[] VFX_R = new Image[11];
    public static Image[] VFX_W = new Image[11];
    public static Image[] VFX_G = new Image[11];
    public static Image[] VFX_RM = new Image[11];
    public static Image[] VFX_Hit = new Image[9];

    public static List<GameObjAdv> gameObjListAdv = new ArrayList<>();
    public static List<GameObjAdv> HUDObjListAdv = new ArrayList<>();
    public static List<GameObjAdv> removeObjListAdv = new ArrayList<>();

    static {
        initializeImages();
        
        preloadAllImages();
    }

    private static void initializeImages() {
        DiceTitle = Toolkit.getDefaultToolkit().getImage("assets/Title.png");
        Edge = Toolkit.getDefaultToolkit().getImage("assets/Edge.png");
        MonsterSleep = Toolkit.getDefaultToolkit().getImage("assets/MonsterSleep.png");
        MonsterFight = Toolkit.getDefaultToolkit().getImage("assets/MonsterFight.png");
        MonsterBoost = Toolkit.getDefaultToolkit().getImage("assets/MonsterBoost.png");
        Line = Toolkit.getDefaultToolkit().getImage("assets/3DLine.png");
        Lock = Toolkit.getDefaultToolkit().getImage("assets/Lock.png");
        PlayerHpBar = Toolkit.getDefaultToolkit().getImage("assets/PlayerHpBar.png");
        PlayerMotionBar = Toolkit.getDefaultToolkit().getImage("assets/PlayerMotionBar.png");
        BossHpBar = Toolkit.getDefaultToolkit().getImage("assets/BossHpBar.png");
        BulletA = Toolkit.getDefaultToolkit().getImage("assets/Pin.png");
        BulletB = Toolkit.getDefaultToolkit().getImage("assets/BuckShot.png");
        SubMon = Toolkit.getDefaultToolkit().getImage("assets/Sub.png");
        Castsn = Toolkit.getDefaultToolkit().getImage("assets/Cast.png");

        Mag[1] = Toolkit.getDefaultToolkit().getImage("assets/Mag1.png");
        Mag[2] = Toolkit.getDefaultToolkit().getImage("assets/Mag2.png");
        Mag[3] = Toolkit.getDefaultToolkit().getImage("assets/Mag3.png");

        String[] diceColors = {"R", "B", "G", "P"};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Dice[i][j] = Toolkit.getDefaultToolkit().getImage(
                    "assets/DICE/" + diceColors[i] + (j + 1) + ".png");
            }
        }

        for (int i = 1; i < 5; i++) {
            BossDice[i] = Toolkit.getDefaultToolkit().getImage("assets/BDICE/" + i + ".png");
        }

        for (int i = 0; i < 10; i++) {
            VFX_R[i] = Toolkit.getDefaultToolkit().getImage("assets/VFX/R" + (i + 1) + ".png");
            VFX_W[i] = Toolkit.getDefaultToolkit().getImage("assets/VFX/W" + (i + 1) + ".png");
            VFX_G[i] = Toolkit.getDefaultToolkit().getImage("assets/VFX/G" + (i + 1) + ".png");
            VFX_RM[i] = Toolkit.getDefaultToolkit().getImage("assets/VFX/RM" + (i + 1) + ".png");
        }

        for (int i = 0; i < 8; i++) {
            VFX_Hit[i] = Toolkit.getDefaultToolkit().getImage("assets/VFX/Hit" + (i + 1) + ".png");
        }
    }

    private static void preloadAllImages() {
        Component dummyComponent = new Component() {};
        MediaTracker tracker = new MediaTracker(dummyComponent);
        int id = 0;

        addImageToTracker(tracker, DiceTitle, id++);
        addImageToTracker(tracker, Edge, id++);
        addImageToTracker(tracker, MonsterSleep, id++);
        addImageToTracker(tracker, MonsterFight, id++);
        addImageToTracker(tracker, MonsterBoost, id++);
        addImageToTracker(tracker, Line, id++);
        addImageToTracker(tracker, Lock, id++);
        addImageToTracker(tracker, PlayerHpBar, id++);
        addImageToTracker(tracker, PlayerMotionBar, id++);
        addImageToTracker(tracker, BossHpBar, id++);
        addImageToTracker(tracker, BulletA, id++);
        addImageToTracker(tracker, BulletB, id++);
        addImageToTracker(tracker, SubMon, id++);
        addImageToTracker(tracker, Castsn, id++);

        for (Image img : Mag) {
            addImageToTracker(tracker, img, id++);
        }

        for (Image[] row : Dice) {
            for (Image img : row) {
                addImageToTracker(tracker, img, id++);
            }
        }

        for (Image img : BossDice) {
            addImageToTracker(tracker, img, id++);
        }

        for (Image img : VFX_R) {
            addImageToTracker(tracker, img, id++);
        }

        for (Image img : VFX_W) {
            addImageToTracker(tracker, img, id++);
        }

        for (Image img : VFX_G) {
            addImageToTracker(tracker, img, id++);
        }

        for (Image img : VFX_RM) {
            addImageToTracker(tracker, img, id++);
        }

        for (Image img : VFX_Hit) {
            addImageToTracker(tracker, img, id++);
        }
        try {
            if (!tracker.waitForAll(5000)) {
                System.err.println("部分图片资源加载超时！");
            }

            if (tracker.isErrorAny()) {
                System.err.println("图片资源加载错误！");
            } else {
                System.out.println("所有图片资源加载完成！");
            }
        } catch (InterruptedException e) {
            System.err.println("图片加载被中断：" + e.getMessage());
        }
    }

    private static void addImageToTracker(MediaTracker tracker, Image img, int id) {
        if (img != null) {
            tracker.addImage(img, id);
        }
    }
}
