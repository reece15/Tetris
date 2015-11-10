package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import config.GameConfig;
import dto.Player;

public abstract class BarsData extends Bars{
	
	private static final int MAX_ROW = GameConfig.getDATA_CONFIG().getMaxRow();
	private static int START_Y = 0;
	private static int SPA = 0;
	private static int IMG_BLOOD_H = Img.WORLD_TITLE.getHeight(null);
	
	public BarsData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA = (this.h - IMG_NUM * MAX_ROW - (PADDING << 1) - Img.WORLD_TITLE.getHeight(null)) / MAX_ROW;
		START_Y = PADDING + IMG_BLOOD_H + SPA;
	}

	@Override
	abstract public void paint(Graphics g);
	
	public void showData(Image imgTitle, List<Player> players, Graphics g){
		g.drawImage(imgTitle, this.x + PADDING, this.y + PADDING, null);
		int nowPoint = this.dto.getNowPoint();
		int drawPoint = 0;
		for(int i = 0; i < MAX_ROW; i++){
			Player p = players.get(i);
			if(nowPoint >= p.getPoint()){
				drawPoint = p.getPoint();
			}
			else{
				drawPoint = nowPoint;
			}
			String strPoint;
			if(p.getPoint() == 0){
				strPoint = null;
			}
			else{
				strPoint = Integer.toString(p.getPoint());
			}
			this.drawBlood(
					this.x + PADDING, 
					this.y + START_Y + (SPA + IMG_BLOOD_H) * i, 
					this.w - ((PADDING) <<1), 
					IMG_BLOOD_H, 
					p.getName(), 
					strPoint, 
					drawPoint, 
					p.getPoint(), 
					g
				);
		}
	}
	
}
