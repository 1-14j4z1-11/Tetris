package model.score;

/**
 * �X�R�A���Ǘ����邽�߂̃N���X
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
	 * �C���X�^���X�����������܂�.
	 * @param maxScore �X�R�A�ő�l
	 * @param calculator �X�R�A�v�Z���@
	 */
	public Score(int maxScore, IScoreCalculator calculator)
	{
		if(calculator == null)
			throw new NullPointerException();
		
		this.maxScore = maxScore;
		this.calculator = calculator;
	}
	
	/**
	 * �L�^�����������܂�.
	 * @param level �������x��
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
	 * �X�R�A���X�V���܂�.
	 * @param count ����̍폜�������C��/�v���[����
	 * @param isTSpin T-Spin�������������ǂ���
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
		
		// ���x���㏸
		if(this.quota <= 0)
		{
			this.level++;
			this.quota = this.calculator.calcQuota(this.level) + this.quota;
		}
		
		this.gravity = this.calculator.calcGravity(this.level);
	}
	
	/**
	 * �X�R�A�����Z���܂�.</br>
	 * ���Z��ɍő�X�R�A�𒴂���ꍇ�͍ő�X�R�A�ɂȂ�܂�.
	 * @param value ���Z����l
	 * @return ���Z��̃X�R�A�l��Ԃ��܂�.
	 */
	public final int addScore(int value)
	{
		this.score = Math.min(this.score + value, this.maxScore);
		return this.score;
	}
	
	/**
	 * �X�R�A�̌v�Z���@��ݒ肵�܂�.
	 * @param calculator �ݒ肷��IScoreCalculator
	 * @exception NullPointerException ������null�̏ꍇ
	 */
	public void setCalculator(IScoreCalculator calculator)
	{
		if(calculator == null)
			throw new NullPointerException();
		
		this.calculator = calculator;
	}
	
	/** ���݂̃X�R�A�ŌŒ肳�ꂽIScore�C���X�^���X�𐶐����܂�. */
	public IScore createSnapShot()
	{
		// ���g�̃R�s�[�𐶐�����(�n���h���̋��L������邽�߃n���h����Null�ɂ���)
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
	
	// �A�N�Z�T
	/** ���x�����擾���܂� */
	public final int getLevel()	{ return this.level; }
	
	/** �m���}���擾���܂� */
	public final int getQuota()	{ return this.quota; }

	/** �������x���擾���܂�. */
	public final double getGravity() { return this.gravity; }
	
	/** �X�R�A���擾���܂� */
	public final int getScore()	{ return this.score; }
	
	/** �폜���C�������擾���܂� */
	public final int getLine()	{ return this.line; }
	
	/** �e�g���X�񐔂��擾���܂� */
	public final int getTetris()	{ return this.tetris; }
	
	/** �A�������擾���܂� */
	public final int getRen()		{ return this.ren; }
	
	/** ����܂ł̍ō��A�������擾���܂� */
	public final int getMaxRen()	{ return this.maxRen; }
	
	/** T-Spin�񐔂��擾���܂� */
	public final int getTSpin()		{ return this.tSpin; }
	
	/** ���O��Tetris���ǂ������擾���܂� */
	public final boolean isTetris() { return this.teteisFlag; }
	
	/** ����BackToBack��Ԃ��ǂ������擾���܂� */
	public final boolean isBackToBack()	{ return this.btbFlag; }
	
	/** ���O��T-Spin���ǂ������擾���܂� */
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
