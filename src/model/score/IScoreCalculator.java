package model.score;

/**
 * スコア計算方法を実装するためのインターフェース
 */
public interface IScoreCalculator
{
	/**
	 * ノルマを計算します
	 * @param level 現在のレベル
	 * @return ノルマ
	 */
	public int calcQuota(int level);
	
	/**
	 * スコア増分を計算します
	 * @param level レベル
	 * @param line 消したライン数
	 * @param btb BackToBackが発生したかどうか
	 * @param tSpin T-Spinが発生したかどうか
	 * @return スコアの増分
	 */
	public int calcScore(int level, int line, boolean btb, boolean tSpin);
	
	/**
	 * 落下速度を計算します
	 * @return 落下速度
	 */
	public double calcGravity(int level);
}
