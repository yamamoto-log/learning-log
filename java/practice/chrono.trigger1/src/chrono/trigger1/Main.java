package chrono.trigger1;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 現場基準のパーティシステム（trigger1） ===");

        // 全員同じ「PartyMemberクラス」のインスタンスとして一括管理
        PartyMember[] party = {
            new PartyMember(CharacterCatalog.CHRONO),
            new PartyMember(CharacterCatalog.ROBO),
            new PartyMember(CharacterCatalog.AYLA)
        };

        // 1. ステータス表示
        for (PartyMember member : party) {
            System.out.println(member.toString());
        }

        System.out.println("\n--- 戦闘開始 ---");
        // 2. 一斉攻撃（ポリモーフィズム）
        for (PartyMember member : party) {
            member.attack();
        }

        System.out.println("\n--- 装備テスト ---");
        // 3. 装備の安全チェックテスト
        party[0].equipWeapon("紅蓮の剣");   // クロノは成功
        party[2].equipWeapon("青龍偃月刀"); // エイラは自動的に弾かれる
    }
}