package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;

public class FrameUtil {
	public static void setFrameCenter(JFrame frame){
		//获取游戏配置
		FrameConfig cfg = GameConfig.getFRAME_CONFIG();
		
		//居中
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width - (frame.getWidth()) >> 1);
		int y = (screen.height - (frame.getHeight()) >> 1) - cfg.getUp();
		frame.setLocation(x, y);
	}
}
