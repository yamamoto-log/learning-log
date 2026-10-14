package chrono.trigger1;

public class PartyMember {
    private final CharacterCatalog catalog;
    
    private int level = 1;
    private int currentHp;
    private int currentMp;
    private String currentWeapon = "なし";

    public PartyMember(CharacterCatalog catalog) {
        this.catalog = catalog;
        this.currentHp = catalog.baseMaxHp;
        this.currentMp = catalog.baseMaxMp;
    }

    public void attack() {
        catalog.attackBehavior.execute(catalog.name, this.level);
    }

    public void equipWeapon(String weaponName) {
        if (catalog == CharacterCatalog.AYLA) {
            System.out.println("【エラー】" + catalog.name + "は拳で戦うため、武器を装備できません！");
            return;
        }
        this.currentWeapon = weaponName;
        System.out.println(catalog.name + "は " + weaponName + " を装備した！(現在の武器: " + currentWeapon + ")");
    }

    public void useItem(String itemName) {
        System.out.println(catalog.name + "は" + itemName + "を使った！");
    }

    @Override
    public String toString() {
        // catalog.element.getJapaneseName() などのゲッターを使ってデータを成形します
        return String.format("[%s] LV:%d 属性:%s HP:%d/%d 出身:%s(%d年)",
            catalog.name, this.level, catalog.element.getJapaneseName(),
            this.currentHp, catalog.baseMaxHp, 
            catalog.originEra.getJapaneseName(), catalog.originEra.getYear());
    }
}