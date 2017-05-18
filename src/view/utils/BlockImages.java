package view.utils;

import java.awt.*;
import java.io.*;
import java.util.*;
import model.tetris.common.BlockCell;

public enum BlockImages
{
	/*
	 * [�u���b�N�摜�̒ǉ����@]
	 *  1.������4�̃u���b�N����1��ɕ��񂾉摜��resources/img/���ɐݒu.
	 *    - ������1�Ԗ�:���u���b�N�p
	 *    - ������2�Ԗ�:�S�[�X�g�u���b�N�\���p
	 *    - ������3�Ԗ�:�ړ��u���b�N�\���p
	 *    - ������4�Ԗ�:�Œ�u���b�N�\���p
	 *  2.Enum�萔���`���A������/img/�ȉ��̉摜�p�X���w��.
	 */
	
	PLAIN("/img/Block_Plain.png");
	
	private static final int BLOCK_COUNT = 4;
	private final String path;
	private Image[] rawImages;
	private final HashMap<BlockCell<Color>, Image[]> coloredImages = new HashMap<>(8);
	
	private BlockImages(String path)
	{
		this.path = path;
	}
	
	/**
	 * �u���b�N�̕`��p�摜�𐶐����܂�.
	 * @param number ��������u���b�N�Z��
	 * @return �������ꂽ�u���b�N�摜
	 */
	public Image createBlockImage(BlockCell<Color> cell)
	{
		if(this.rawImages == null)
		{
			if(!this.readNumberImage())
			{
				System.err.printf("Image file could not be opened : %s%n", this.path);
				return null;
			}
		}
		
		int index;
		
		switch(cell.getState())
		{
			case NONE:
				index = 0;
				break;
			case GHOST:
				index = 1;
				break;
			case MOVE:
				index = 2;
				break;
			case FIXED:
				index = 3;
				break;
			default:
				throw new AssertionError();
		}

		// �F�̕ϊ��������d�����߁A�u���b�N�摜���L���b�V������
		if(coloredImages.containsKey(cell))
		{
			return coloredImages.get(cell)[index];
		}
		else
		{
			Image[] images = new Image[BLOCK_COUNT];
			
			for(int i = 0; i < BLOCK_COUNT; i++)
			{
				images[i] = ImageUtils.changeColor(this.rawImages[i], cell.getTag());
			}
			
			this.coloredImages.put(cell, images);
			
			return images[index];
		}
	}

	/**
	 * �eEnum�C���X�^���X��path����u���b�N�摜��ǂݍ��݂܂�.
	 * @return �ǂݍ��݂ɐ��������ꍇ�܂��͊��ɓǂݍ���ł���ꍇ��true��Ԃ��A�ǂݍ��݂Ɏ��s�����ꍇ��false��Ԃ��܂�.
	 */
	private boolean readNumberImage()
	{
		if(this.rawImages != null)
			return true;
		
		try
		{
			this.rawImages = ImageUtils.readIntegratedImage(this.path, BLOCK_COUNT, 1);
		}
		catch(IOException e)
		{
			return false;
		}

		return true;
	}
}
