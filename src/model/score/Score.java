package model.score;

/**
 * スコアを管理するためのクラス
 */
public class Score implements IScore
{
	private static final int TH_TETRIS = 4;
	
	private int level;
	private int quota;
	private double gravity;
	
	private int score;
	private int line;
	private int tetris;
	private int ren;
	private int maxRen;
	private int tSpin;
	private final int maxScore;
	
	private boolean teteisFlag;
	private boolean btbFlag;
	private boolean tSpinFlag;
	private IScoreCalculator calculator;
	
	/**
	 * インスタンスを初期化します.
	 * @param maxScore スコア最大値
	 * @param calculator スコア計算方法
	 */
	public Score(int maxScore, IScoreCalculator calculator)
	{
		if(calculator == null)
			throw new NullPointerException();
		
		this.maxScore = maxScore;
		this.calculator = calculator;
	}
	
	/**
	 * 記録を初期化します.
	 * @param level 初期レベル
	 */
	public void reset(int level)
	{
		this.level = level;
		this.score = 0;
		this.line = 0;
		this.tetris = 0;
		this.ren = 0;
		this.maxRen = 0;
		this.tSpin = 0;

		this.teteisFlag = false;
		this.btbFlag = false;
		this.tSpinFlag = false;
		
		this.quota = this.calculator.calcQuota(level);
		this.gravity = this.calculator.calcGravity(level);
	}
	
	/**
	 * スコアを更新します.
	 * @param count 今回の削除したライン/プレーン数
	 * @param isTSpin T-Spinが発生したかどうか
	 */
	public void update(int count, boolean isTSpin)
	{
		if(count <= 0)
		{
			this.ren = 0;
			this.teteisFlag = false;
			this.tSpinFlag = false;
			
			return;
		}
		
		this.tSpinFlag = isTSpin;
		this.quota -= count;
		this.line += count;
		this.ren++;
		
		if(count >= TH_TETRIS)
		{
			this.tetris++;
			this.teteisFlag = true;
			this.addScore(this.calculator.calcScore(this.level, count, this.btbFlag, this.tSpinFlag));
			this.btbFlag = true;
		}
		else
		{
			this.teteisFlag = false;
			this.btbFlag = false;
			this.addScore(this.calculator.calcScore(this.level, count, this.btbFlag, this.tSpinFlag));
		}
		
		this.maxRen = Math.max(this.ren, this.maxRen);
		
		// レベル上昇
		if(this.quota <= 0)
		{
			this.level++;
			this.quota = this.calculator.calcQuota(this.level) + this.quota;
		}
		
		this.gravity = this.calculator.calcGravity(this.level);
	}
	
	/**
	 * スコアを加算します.</br>
	 * 加算後に最大スコアを超える場合は最大スコアになります.
	 * @param value 加算する値
	 * @return 加算後のスコア値を返します.
	 */
	public final int addScore(int value)
	{
		this.score = Math.min(this.score + value, this.maxScore);
		return this.score;
	}
	
	/**
	 * スコアの計算方法を設定します.
	 * @param calculator 設定するIScoreCalculator
	 * @exception NullPointerException 引数がnullの場合
	 */
	public void setCalculator(IScoreCalculator calculator)
	{
		if(calculator == null)
			throw new NullPointerException();
		
		this.calculator = calculator;
	}
	
	/** 現在のスコアで固定されたIScoreインスタンスを生成します. */
	public IScore createSnapShot()
	{
		// 自身のコピーを生成する(ハンドラの共有を避けるためハンドラはNullにする)
		Score score = new Score(this.maxScore, NullCalculator.INSTANCE);
		
		score.level = this.level;
		score.score = this.score;
		score.line = this.line;
		score.tetris = this.tetris;
		score.ren = this.ren;
		score.maxRen = this.maxRen;
		score.tSpin = this.tSpin;
		
		score.teteisFlag = this.teteisFlag;
		score.btbFlag = this.btbFlag;
		score.tSpinFlag = this.tSpinFlag;
		
		score.quota = this.quota;
		score.gravity = this.gravity;
		
		return score;
	}
	
	// アクセサ
	/** レベルを取得します */
	public final int getLevel()	{ return this.level; }
	
	/** ノルマを取得します */
	public final int getQuota()	{ return this.quota; }

	/** 落下速度を取得します. */
	public final double getGravity() { return this.gravity; }
	
	/** スコアを取得します */
	public final int getScore()	{ return this.score; }
	
	/** 削除ライン数を取得します */
	public final int getLine()	{ return this.line; }
	
	/** テトリス回数を取得します */
	public final int getTetris()	{ return this.tetris; }
	
	/** 連鎖数を取得します */
	public final int getRen()		{ return this.ren; }
	
	/** これまでの最高連鎖数を取得します */
	public final int getMaxRen()	{ return this.maxRen; }
	
	/** T-Spin回数を取得します */
	public final int getTSpin()		{ return this.tSpin; }
	
	/** 直前がTetrisかどうかを取得します */
	public final boolean isTetris() { return this.teteisFlag; }
	
	/** 現在BackToBack状態かどうかを取得します */
	public final boolean isBackToBack()	{ return this.btbFlag; }
	
	/** 直前がT-Spinかどうかを取得します */
	public final boolean isTSpin() 	{ return this.tSpinFlag; }
	
	private static class NullCalculator implements IScoreCalculator
	{
		public static final NullCalculator INSTANCE = new NullCalculator();
		
		private NullCalculator()
		{ }
		
		@Override
		public int calcQuota(int level)
		{
			return 0;
		}
		
		@Override
		public int calcScore(int level, int line, boolean btb, boolean tSpin)
		{
			return 0;
		}
		
		@Override
		public double calcGravity(int level)
		{
			return 0.0;
		}
	}
}
