// 使用Java 23运行以确保兼容性
符文六号 (RUNE-6) 是一款基于掷骰子玩法的回合制RPG游戏。

- 按【R键】可掷出四枚骰子：
  骰子分为四种颜色，数值范围为1-4，不同颜色对应不同属性：
  - 红色：攻击
  - 绿色：治疗
  - 蓝色：防御（数值不可跨回合累加）
  - 紫色：行动点
  
  核心结算规则：
  1. 攻击与治疗数值累计生效，当行动点总数攒满8点时统一结算——对敌人造成对应攻击伤害，并回复自身等量治疗生命值；
  2. 防御数值每回合立刻结算，可等量抵消该回合来自敌人的伤害。

- 每回合拥有3次掷骰子机会，操作规则：
  1. 按【R键】：重投所有未锁定的骰子；
  2. 按数字键【1/2/3/4】：锁定对应槽位的骰子，该骰子不参与后续重投。

- 按【空格键】：手动结算当前回合。

以上为游戏全部核心操作规则。

// Run with Java 23 to ensure compatibility
RUNE-6 is a turn-based RPG game based on dice-rolling mechanics.

- Press the 'R' key to roll four dice.
  The dice come in four colors with different values (1-4):
  - Red (Attack)
  - Green (Healing)
  - Blue (Defense)
  - Purple (Action Points)
  
  Attack and Healing values are cumulative. They are settled when the total Action Points reach 8, dealing damage to enemies and restoring your own health.
  Defense values are settled immediately each turn, offsetting enemy damage received in the same turn by an equal amount.

- Each turn grants 3 dice-rolling opportunities:
  - Press the 'R' key to re-roll all dice.
  - Press the number keys '1', '2', '3', '4' to lock the corresponding dice slot, preventing it from being re-rolled.

- Press the 'Space' key to settle the current turn.

The above are all the game operation rules.