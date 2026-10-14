package chrono.trigger1;

public enum CharacterCatalog {
    // 属性と時代に、新しく作ったEnumの定数を指定します
    CHRONO("クロノ", Element.HEAVEN, Era.PRESENT, 150, 12, 12, new SwordAttack()),
    ROBO("ロボ", Element.SHADOW, Era.FUTURE, 175, 10, 6, new RocketPunch()),
    AYLA("エイラ", Element.NONE, Era.PREHISTORIC, 200, 0, 13, new SeikenZuki());

    final String name;
    final Element element; // ★ String から Element型 に変更
    final Era originEra;   // ★ String から Era型 に変更
    final int baseMaxHp;
    final int baseMaxMp;
    final int baseSpeed;
    final AttackBehavior attackBehavior;

    CharacterCatalog(String name, Element element, Era originEra, int maxHp, int maxMp, int speed, AttackBehavior behavior) {
        this.name = name;
        this.element = element;
        this.originEra = originEra;
        this.baseMaxHp = maxHp;
        this.baseMaxMp = maxMp;
        this.baseSpeed = speed;
        this.attackBehavior = behavior;
    }
}