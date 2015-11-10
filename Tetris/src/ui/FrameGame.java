package ui;

import javax.swing.JFrame;

import util.FrameUtil;
import config.FrameConfig;
import config.GameConfig;


@SuppressWarnings("serial")
public class FrameGame extends JFrame {
	
	public FrameGame(PanelGame panel){
		//获取游戏配置
		FrameConfig cfg = GameConfig.getFRAME_CONFIG();
		this.setTitle(cfg.getTitle());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(cfg.getWidth(), cfg.getHeight());
		this.setResizable(false);
		FrameUtil.setFrameCenter(this);
		
		this.setContentPane(panel);
		this.setVisible(true);
	}
}
