package controller;

import java.util.*;

/**
 * ViewModel�̑J�ڎ��Ɏ󂯓n������f�[�^�̃R���e�i
 */
// package
final class TransitionData
{
	private final HashMap<Class<?>, Object> map = new HashMap<>();
	
	public TransitionData()
	{ }

	public <T> TransitionData(Class<T> type, T value)
	{
		this.addData(type, value);
	}
	
	/**
	 * �f�[�^��ǉ����܂�
	 * @param type �ǉ�����f�[�^�̌^
	 * @param value �ǉ�����f�[�^
	 * @return ���g�̃C���X�^���X
	 */
	public final <T> TransitionData addData(Class<T> type, T value)
	{
		this.map.put(type, value);
		return this;
	}
	
	/**
	 * �f�[�^���擾���܂�
	 * @param type �擾����f�[�^�̌^
	 * @return �w�肳�ꂽ�^�̃f�[�^�����݂���ꍇ�̓f�[�^��Ԃ��A���݂��Ȃ��ꍇ��null��Ԃ��܂�
	 */
	public final <T> T getData(Class<T> type)
	{
		Object value = this.map.get(type);
		return (value != null) ? type.cast(value) : null;
	}
	
	/**
	 * �w�肳�ꂽ�^�̃f�[�^���܂܂�Ă��邩�ǂ������肵�܂�
	 * @param type ���肷��^
	 * @return �f�[�^���܂܂�Ă���ꍇ��true��Ԃ��A�܂܂�Ă��Ȃ��ꍇ��false��Ԃ��܂�
	 */
	public final <T> boolean contains(Class<T> type)
	{
		return this.map.containsKey(type);
	}
}
