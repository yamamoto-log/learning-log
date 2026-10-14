package chrono.trigger1;


// エイラ用の攻撃部品
public class SeikenZuki implements AttackBehavior {
	@Override
	public void execute(String attackerName, int level) {
		int damage = level * 10;
		System.out.println(attackerName + "の正拳突き！");
		System.out.println(damage + " のダメージを与えた！");
		if (level >= 96) {
			System.out.println("クリティカル判定：9999ダメージ！");
		}
	}
}