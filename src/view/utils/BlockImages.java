package view.utils;

import java.awt.*;
import java.io.*;
import java.util.*;
import model.tetris.common.BlockCell;

public enum BlockImages
{
	/*
	 * [ブロック画像の追加方法]
	 *  1.等幅な4個のブロックが横1列に並んだ画像をresources/img/下に設置.
	 *    - 左から1番目:無ブロック用
	 *    - 左から2番目:ゴーストブロック表示用
	 *    - 左から3番目:移動ブロック表示用
	 *    - 左から4番目:固定ブロック表示用
	 *  2.Enum定数を定義し、引数に/img/以下の画像パスを指定.
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
	 * ブロックの描画用画像を生成します.
	 * @param number 生成するブロックセル
	 * @return 生成されたブロック画像
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

		// 色の変換処理が重いため、ブロック画像をキャッシュする
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
	 * 各Enumインスタンスのpathからブロック画像を読み込みます.
	 * @return 読み込みに成功した場合または既に読み込んでいる場合はtrueを返し、読み込みに失敗した場合はfalseを返します.
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
