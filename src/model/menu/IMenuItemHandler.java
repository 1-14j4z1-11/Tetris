package model.menu;

public interface IMenuItemHandler
{
	/**
	 * �I������Ă��郁�j���[���ڂ����s���܂�.
	 * @param controller 
	 */
	void execute();
	
	/**
	 *���j���[�� �ݒ�l�����̒l�ɕύX
	 */
	void changeNext();
	
	/**
	 *���j���[�� �ݒ�l��O�̒l�ɕύX
	 */
	void changePrevious();
}
