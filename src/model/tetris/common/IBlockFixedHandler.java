package model.tetris.common;

import model.score.IScore;

/**
 * �u���b�N���Œ肳�ꂽ�Ƃ��ɌĂяo�����n���h��
 */
public interface IBlockFixedHandler
{
	/**
	 * �u���b�N���Œ肳�ꂽ�Ƃ��ɌĂяo����܂�.
	 * @param score �Œ莞�̃X�R�A���
	 */
	void execute(IScore score);
}
