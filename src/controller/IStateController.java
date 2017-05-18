package controller;

// package
interface IStateController extends IController
{
	/**
	 * ViewModel�̏������J�n���܂�
	 * @param data ����ViewModel����n���ꂽ�f�[�^
	 */
	// ���̃��\�b�h���Ă΂�Ȃ��ꍇ�ł�ViewModel���@�\����悤�Ɏ�������
	void start(TransitionData data);
	
	/**
	 * ViewModel�̏������I�����A����ViewModel�֓n���f�[�^���擾���܂�
	 * @return ����ViewModel�ɓn���f�[�^
	 */
	TransitionData finish();
}
