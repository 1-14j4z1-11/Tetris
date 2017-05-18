package model.score;

/**
 * 単純計算で各パラメータを算出するIScoreCalculatorを実現するクラス
 */
public class SimpleScoreCalculator implements IScoreCalculator
{
	private final int baseScore;
	private final int baseQuota;
	private final double baseGravity;
	
	public SimpleScoreCalculator(int baseScore, int baseQuota, double baseGravity)
	{
		this.baseScore = baseScore;
		this.baseQuota = baseQuota;
		this.baseGravity = baseGravity;
	}
	
	@Override
	public int calcQuota(int level)
	{
		return level * this.baseQuota;
	}
	
	@Override
	public int calcScore(int level, int line, boolean btb, boolean tSpin)
	{
		int coef = (btb ? 2 : 1) * (tSpin ? 3 : 1) * level;
		
		switch(line)
		{
			case 0:
				return 0;
			case 1:
				return coef * this.baseScore;
			case 2:
				return coef * this.baseScore * 2;
			case 3:
				return coef * this.baseScore * 5;
			default:
				return coef * this.baseScore * 8;
		}
	}

	@Override
	public double calcGravity(int level)
	{
		double gravity = 0;
		
		for(; level > 0; level -= 5)
		{
			gravity += this.baseGravity * level;
		}
		
		return gravity;
	}
}
