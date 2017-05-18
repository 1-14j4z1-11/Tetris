package view.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.*;
import view.frame.ImageLabel.ScaleType;
import view.utils.*;

public class MainFrame extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_FRAME_X = 200;		// ウィンドウ初期位置X
	private static final int DEFAULT_FRAME_Y = 200;		// ウィンドウ初期位置Y
	private static final int TIMER_INTERVAL = 33;		// フレーム更新間隔[ms]
	private static final int TIMER_INTERVAL_FAST = 5;	// フレーム更新間隔(高速時)[ms]
	private static final Color BACK_COLOR = new Color(0x00000000, true);
	
	private static final int MARGIN_MAIN = 10;
	private static final int MARGIN_SUB_TOP = 35;
	private static final int MARGIN_SUB_OTHER = 10;
	
	private final DrawablePanel panel_Main = new DrawablePanel();
	private final DrawablePanel panel_Next = new DrawablePanel();
	private final DrawablePanel panel_Hold = new DrawablePanel();
	private final DrawablePanel panel_Score = new DrawablePanel();
	private final DrawablePanel panel_Manual = new DrawablePanel();
	
	private final ImageLabel bgImage_Main = new ImageLabel();
	private final ImageLabel bgImage_Next = new ImageLabel();
	private final ImageLabel bgImage_Hold = new ImageLabel();
	private final ImageLabel bgImage_Score = new ImageLabel();
	
	private final ImageLabel labelImage_Next = new ImageLabel();
	private final ImageLabel labelImage_Hold = new ImageLabel();
	
	private Timer timer;
	private boolean fast = false;
	
	private final MainController controller;
	
	public MainFrame(MainController controller, String title)
	{
		this.setTitle(title);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(DEFAULT_FRAME_X, DEFAULT_FRAME_Y, 720, 640);
		this.addKeyListener(this);
		
		this.initializeComponent();
		
		this.addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				MainFrame.this.locateComponent();
			}
		});

		this.controller = controller;
	}
	
	/**
	 * コンポーネントを初期化します
	 */
	private void initializeComponent()
	{
		// panel_Main
		this.panel_Main.setBackground(BACK_COLOR);
		this.add(this.panel_Main);
		
		// panel_Next
		this.panel_Next.setBackground(BACK_COLOR);
		this.add(this.panel_Next);
		
		// panel_Hold
		this.panel_Hold.setBackground(BACK_COLOR);
		this.add(this.panel_Hold);
		
		// panel_Score
		this.panel_Score.setBackground(BACK_COLOR);
		this.add(this.panel_Score);

		// panel_Manual
		this.panel_Manual.setBackground(BACK_COLOR);
		this.add(this.panel_Manual);

		// labelImage_Next
		this.labelImage_Next.setScaleType(ScaleType.CENTER);
		this.labelImage_Next.setBackground(BACK_COLOR);
		this.labelImage_Next.setImage(BasicImages.LABEL_NEXT.getImage());
		this.panel_Next.add(this.labelImage_Next);

		// labelImage_Hold
		this.labelImage_Hold.setScaleType(ScaleType.CENTER);
		this.labelImage_Hold.setBackground(BACK_COLOR);
		this.labelImage_Hold.setImage(BasicImages.LABEL_HOLD.getImage());
		this.panel_Hold.add(this.labelImage_Hold);
		
		// bgImage_Main
		this.bgImage_Main.setImage(BasicImages.MAIN_BG.getImage());
		this.panel_Main.add(this.bgImage_Main);
		
		// bgImage_Next
		this.bgImage_Next.setImage(BasicImages.SUB_BG.getImage());
		this.panel_Next.add(this.bgImage_Next);
		
		// bgImage_Hold
		this.bgImage_Hold.setImage(BasicImages.SUB_BG.getImage());
		this.panel_Hold.add(this.bgImage_Hold);

		// bgImage_Score
		this.bgImage_Score.setImage(BasicImages.SUB_BG.getImage());
		this.panel_Score.add(this.bgImage_Score);
		
		// timer
		this.timer = new Timer(TIMER_INTERVAL, this);
		this.timer.start();
		
		// 余白の設定
		this.panel_Main.setMargin(MARGIN_MAIN, MARGIN_MAIN, MARGIN_MAIN, MARGIN_MAIN);
		this.panel_Next.setMargin(MARGIN_SUB_TOP, MARGIN_SUB_OTHER, MARGIN_SUB_OTHER, MARGIN_SUB_OTHER);
		this.panel_Hold.setMargin(MARGIN_SUB_TOP, MARGIN_SUB_OTHER, MARGIN_SUB_OTHER, MARGIN_SUB_OTHER);
		this.panel_Score.setMargin(MARGIN_MAIN, MARGIN_MAIN, MARGIN_MAIN, MARGIN_MAIN);
		//this.panel_Manual.setMargin(MARGIN_SUB_OTHER, MARGIN_SUB_OTHER, MARGIN_SUB_OTHER, MARGIN_SUB_OTHER);
	}
	
	/**
	 * コンポーネントの配置を設定します
	 */
	private void locateComponent()
	{
		Insets insets = this.getInsets();
		int width = this.getWidth() - insets.left - insets.right;
		int height = this.getHeight() - insets.top - insets.bottom;
		int subPanelSize = width / 4 - 20;
		int subPanelOffsetV = MARGIN_SUB_TOP - MARGIN_SUB_OTHER;	// Next/Holdの描画領域を正方形にするための差分
		
		this.panel_Main.setBounds(width / 4, 10, width / 2, height - 20);
		this.panel_Next.setBounds(width * 3 / 4 + 10, 10, subPanelSize, subPanelSize + subPanelOffsetV);
		this.panel_Hold.setBounds(10, 10, subPanelSize, subPanelSize + subPanelOffsetV);
		this.panel_Score.setBounds(width * 3 / 4 + 10, panel_Next.getY() + panel_Next.getHeight() + 10, subPanelSize, 200);
		this.panel_Manual.setBounds(10, 20 + panel_Next.getHeight(), subPanelSize, subPanelSize * 2);
		
		this.bgImage_Main.setBounds(0, 0, this.panel_Main.getWidth(), this.panel_Main.getHeight());
		this.bgImage_Next.setBounds(0, 0, this.panel_Next.getWidth(), this.panel_Next.getHeight());
		this.bgImage_Hold.setBounds(0, 0, this.panel_Hold.getWidth(), this.panel_Hold.getHeight());
		this.bgImage_Score.setBounds(0, 0, this.panel_Score.getWidth(), this.panel_Score.getHeight());
		
		this.labelImage_Next.setBounds(0, 0, this.panel_Next.getWidth(), MARGIN_SUB_TOP);
		this.labelImage_Hold.setBounds(0, 0, this.panel_Hold.getWidth(), MARGIN_SUB_TOP);
		
		this.updateViews();
	}
	
	/**
	 * 状態が更新された際に呼び出すことでViewを更新します
	 */
	public void updateViews()
	{
		this.panel_Main.setDrawer(this.controller.getMainDrawer());
		this.panel_Next.setDrawer(this.controller.getNextDrawer());
		this.panel_Hold.setDrawer(this.controller.getHoldDrawer());
		this.panel_Score.setDrawer(this.controller.getScoreDrawer());
		this.panel_Manual.setDrawer(this.controller.getManualDrawer());

		this.changeFrameSpeed(false);
	}
	
	/**
	 * タイマーイベントハンドラ
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// 描画不可を減らすため必要なものだけ再描画する
		this.panel_Main.setDrawer(this.controller.getMainDrawer());
		this.panel_Next.setDrawer(this.controller.getNextDrawer());
		this.panel_Hold.setDrawer(this.controller.getHoldDrawer());
		this.panel_Score.setDrawer(this.controller.getScoreDrawer());
		//this.panel_Manual.setDrawer(this.viewModel.getManualDrawer());
		
		this.controller.timeTicked();
		this.repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_CONTROL:
				this.changeFrameSpeed(true);
				break;
			default:
				break;
		}
		
		this.controller.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{ }

	@Override
	public void keyTyped(KeyEvent e)
	{ }
	
	/**
	 * フレーム速度を変更する
	 * @param change 値の変更をする場合はtrue, 状態の更新のみをする場合はfalse
	 */
	private void changeFrameSpeed(boolean change)
	{
		this.fast = this.controller.allowFastFrame() ? (this.fast ^ change) : false;
		this.timer.setDelay(this.fast ? TIMER_INTERVAL_FAST : TIMER_INTERVAL);
	}
}