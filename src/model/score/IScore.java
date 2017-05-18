package model.score;

public interface IScore
{
	/** レベルを取得します. */
	int getLevel();
	
	/** ノルマを取得します. */
	int getQuota();

	/** 落下速度を取得します. */
	double getGravity();
	
	/** スコアを取得します. */
	int getScore();
	
	/** 削除ライン数を取得します. */
	int getLine();
	
	/** テトリス回数を取得します. */
	int getTetris();
	
	/** 連鎖数を取得します. */
	int getRen();
	
	/** これまでの最高連鎖数を取得します. */
	int getMaxRen();
	
	/** T-Spin回数を取得します. */
	int getTSpin();
	
	/** 直前がTetrisかどうかを取得します. */
	boolean isTetris();
	
	/** 直前がBackToBackかどうかを取得します. */
	boolean isBackToBack();
	
	/** 直前がT-Spinかどうかを取得します. */
	boolean isTSpin();
}
