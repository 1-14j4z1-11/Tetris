package model.score;

public interface IScore
{
	/** ���x�����擾���܂�. */
	int getLevel();
	
	/** �m���}���擾���܂�. */
	int getQuota();

	/** �������x���擾���܂�. */
	double getGravity();
	
	/** �X�R�A���擾���܂�. */
	int getScore();
	
	/** �폜���C�������擾���܂�. */
	int getLine();
	
	/** �e�g���X�񐔂��擾���܂�. */
	int getTetris();
	
	/** �A�������擾���܂�. */
	int getRen();
	
	/** ����܂ł̍ō��A�������擾���܂�. */
	int getMaxRen();
	
	/** T-Spin�񐔂��擾���܂�. */
	int getTSpin();
	
	/** ���O��Tetris���ǂ������擾���܂�. */
	boolean isTetris();
	
	/** ���O��BackToBack���ǂ������擾���܂�. */
	boolean isBackToBack();
	
	/** ���O��T-Spin���ǂ������擾���܂�. */
	boolean isTSpin();
}
