package chrono.trigger1;

// 攻撃という「行動の枠組み」を定義するインターフェース
public interface AttackBehavior {
	void execute(String attackerName, int level);
}