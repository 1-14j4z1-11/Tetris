package controller;

import java.awt.event.KeyEvent;
import view.draw.IDrawer;

/**
 * �Q�[���̊e��Ԃł̏�ԕω��Ɋւ��鏈����񋟂���C���^�[�t�F�[�X
 */
public interface IController
{
	/** ���C����Drawer���擾���܂�. */
	IDrawer getMainDrawer();
	
	/** NextBlock��Drawer���擾���܂�. */
	IDrawer getNextDrawer();
	
	/** HoldBlock��Drawer���擾���܂�. */
	IDrawer getHoldDrawer();
	
	/** Score��Drawer���擾���܂�. */
	IDrawer getScoreDrawer();
	
	/** Manual��Drawer���擾���܂�. */
	IDrawer getManualDrawer();
	
	/** �L�[�C�x���g���󂯎��AModel�̏��������s���܂�. */
	void keyPressed(KeyEvent e);
	
	/** �t���[����i�߂܂�. */
	void timeTicked();
	
	/** �t���[�����x�̍������������邩�ǂ����𔻒肵�܂� */
	boolean allowFastFrame();
}
