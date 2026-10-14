package chrono.trigger1;

// クロノ用の攻撃部品
public class SwordAttack implements AttackBehavior {
	@Override
	public void execute(String attackerName, int level) {
		System.out.println(attackerName + "の刀による斬撃”");
	}
}