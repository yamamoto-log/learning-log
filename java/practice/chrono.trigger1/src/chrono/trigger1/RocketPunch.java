package chrono.trigger1;

// ロボ用の攻撃部品
public class RocketPunch implements AttackBehavior {
	@Override
	public void execute(String attackerName, int level) {
		System.out.println(attackerName + "のロケットパンチ！");
	}
}