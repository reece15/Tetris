package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;
public class Img {
	
	private Img(){}
	
	public static final String IMG_PATH = "images";
	 public static Image numImage = null;
	 public static Image IMG_BLOOD = null;
	 public static Image BARS = null;
	 public static Image IMG_POINT = null;
	 public static Image IMG_RMLINE = null;
	 public static Image nameImage = null;
	 public static Image pauseImage = null;
	 public static Image WORLD_TITLE = null;
	 public static Image LOCAL_TITLE = null;
	 public static Image PAUSE = null;
	/*
	 * 小方块 图片
	 */
	 public static Image ACT = null;
	/*
	 * 等级 标题图片
	 */
	 public static Image LEVEL_TITLE = null;
	 public static Image IMG_SHADOW = null;
	 public static ImageIcon IMG_START = null;
	 public static ImageIcon IMG_CONFIG = null;
	 public static Image[] NEXT_ACT = null;
	 public static List<Image> BG_LIST = null;
	 public static void setSkin(String path){
		 String skinPath = IMG_PATH + "/" + path;
		 numImage = new ImageIcon(skinPath + "/title/num.png").getImage();
		 IMG_BLOOD = new ImageIcon(skinPath + "/game/blood.png").getImage();
		 BARS = new ImageIcon(skinPath + "/game/bars.png").getImage();
		 IMG_POINT = new ImageIcon(skinPath + "/title/point.png").getImage();
		 IMG_RMLINE = new ImageIcon(skinPath + "/title/line.png").getImage();
		 nameImage = new ImageIcon(skinPath + "/title/name.png").getImage();
		 pauseImage = new ImageIcon(skinPath + "/title/pause.png").getImage();
		 WORLD_TITLE = new ImageIcon(skinPath + "/title/world.png").getImage();
		 LOCAL_TITLE = new ImageIcon(skinPath + "/title/local.png").getImage();
		 PAUSE = new ImageIcon(skinPath + "/title/pause.png").getImage();
		/*
		 * 小方块 图片
		 */
		 ACT = new ImageIcon(skinPath + "/game/block.png").getImage();
		/*
		 * 等级 标题图片
		 */
		 LEVEL_TITLE = new ImageIcon(skinPath + "/title/level.png").getImage();
		 IMG_SHADOW = new ImageIcon(skinPath + "/game/shadow.png").getImage();
		 IMG_START = new ImageIcon(skinPath + "/title/start.png");
		 IMG_CONFIG = new ImageIcon(skinPath + "/title/config.png");
		 NEXT_ACT = new Image[GameConfig.getSYSTEM_CONFIG().getTypeConfig().size()];
		for(int i = 0; i < NEXT_ACT.length; i++){
			NEXT_ACT[i] = new ImageIcon(skinPath + "/game/" + i + ".png").getImage();
		}
		//背景图片数组
		File dir = new File(skinPath + "/background");
		File[] files = dir.listFiles();
		BG_LIST = new ArrayList<Image>();
		for(File file : files){
			if(!file.isDirectory()){
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
			}
		}
		
	}
	 
		 ;
	 /*
		 * 初始化图片
		 */
		static{
			setSkin("简单卡通风格");
		}	
}
