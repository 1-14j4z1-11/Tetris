package model.score;

/**
 * �X�R�A�v�Z���@���������邽�߂̃C���^�[�t�F�[�X
 */
public interface IScoreCalculator
{
	/**
	 * �m���}���v�Z���܂�
	 * @param level ���݂̃��x��
	 * @return �m���}
	 */
	public int calcQuota(int level);
	
	/**
	 * �X�R�A�������v�Z���܂�
	 * @param level ���x��
	 * @param line ���������C����
	 * @param btb BackToBack�������������ǂ���
	 * @param tSpin T-Spin�������������ǂ���
	 * @return �X�R�A�̑���
	 */
	public int calcScore(int level, int line, boolean btb, boolean tSpin);
	
	/**
	 * �������x���v�Z���܂�
	 * @return �������x
	 */
	public double calcGravity(int level);
}
