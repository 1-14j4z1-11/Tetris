package model.menu;

import java.util.*;

public class MenuItem 
{
	private final Config config;
	private final IMenuItemHandler handler;
	private List<MenuItem> nextItems = new ArrayList<>();
	
	/**
	 * �C���X�^���X�����������܂�
	 * @param config �ݒ�C���X�^���X
	 * @param handler ���j���[�Ɋւ���̏����̃n���h��(���������Ȃ��ꍇ��null��ݒ�)
	 */
	public MenuItem(Config config, IMenuItemHandler handler)
	{
		this.config = config;
		this.handler = handler;
	}
	
	/** ���j���[�e�L�X�g���擾���܂�. */
	public String getText()
	{
		return this.text;
	}
	
	/**
	 * ���̃��j���[�̉��K�w�̃A�C�e����ǉ����܂�.
	 * @param item �ǉ����郁�j���[�A�C�e��
	 * @return ���̃C���X�^���X���g
	 * @exception NullPointerException ������null�̏ꍇ
	 */
	public MenuItem addNextItem(MenuItem item)
	{
		if(item == null)
			throw new NullPointerException();
		
		this.nextItems.add(item);
		return this;
	}
	
	/**
	 * ���j���[�I�����̏��������s���܂�.
	 * @return ���̃A�C�e���̉��K�w�̃��j���[�A�C�e�����X�g
	 */
	public MenuList menuSelected()
	{
		if(this.handler != null)
			this.handler.execute();
			
		return new MenuList(this.nextItems);
	}
}
