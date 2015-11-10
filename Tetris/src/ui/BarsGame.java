package ui;

import java.awt.Graphics;
import java.awt.Point;

import config.GameConfig;
import entity.GameAct;

public class BarsGame extends Bars{
	
	
	
	/*
	 * 小方块大小
	 */
	private static int ACT_SIZE = Img.ACT.getHeight(null);
	private static int SIZE_ROL = GameConfig.getFRAME_CONFIG().getSizeRol();
	private static final int LOSE_IDX = GameConfig.getFRAME_CONFIG().getLoseIdx();
	private static final int MAXLEFT = 9999;
	private static final int MAXRIGHT = 0;
	public BarsGame(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.createBars(g);
		//绘制活动方块
		this.drawMainAct(g);
		//绘制地图
		this.drawMap(g);
		
		if(this.dto.isPause()){
			this.drawImageAtCenter(Img.PAUSE, g);
		}
	}
	private void drawMap(Graphics g) {
		boolean[][] map = this.dto.getGameMap();
		for(int x = 0; x < map.length; x++){
			for(int y = 0; y < map[x].length; y++){
				if(map[x][y]){
					int lv = this.dto.getLevel();
					int imgIdx = (lv == 0 ? 0 : (lv - 1) % 7 + 1);
					drawAct(x, y, this.dto.isFlagStart() ? imgIdx : LOSE_IDX, g);
				}
			}
		}
		
	}

	private void drawMainAct(Graphics g) {
		GameAct act = this.dto.getGameAct();
		if(act != null){
			Point[] points = act.getActPoint();
			
			this.drawShadow(points, true, g);
			//获得方块编号
			int actCode = this.dto.getGameAct().getActCode();
			//打印方块
			for(int i = 0; i < points.length; i++){
				drawAct(points[i].x, points[i].y, actCode + 1, g);
			}
		}
	}

	private void drawShadow(Point[] points, boolean b, Graphics g) {
		
		if(!this.dto.isShadow()){
			return;
		}
		int MaxLeft = MAXLEFT;
		int MaxRight = MAXRIGHT;
		for (Point p : points){
			
			MaxLeft = (p.x < MaxLeft ? p.x : MaxLeft);
			MaxRight = (p.x > MaxRight ? p.x : MaxRight);
		}
		g.drawImage(Img.IMG_SHADOW, 
				this.x + SIZE + (MaxLeft << SIZE_ROL), 
				this.y + SIZE, 
				((MaxRight - MaxLeft + 1) << SIZE_ROL ), 
				this.h - (SIZE << 1), 
				null);
	}

	private void drawAct(int x, int y, int Code, Graphics g){
		Code = this.dto.isFlagStart() ? Code : LOSE_IDX;
		g.drawImage(Img.ACT,
				this.x + x * ACT_SIZE + SIZE,
				this.y + y * ACT_SIZE + SIZE,
				this.x + x * ACT_SIZE + ACT_SIZE + SIZE,
				this.y + y * ACT_SIZE + ACT_SIZE + SIZE,
				Code << SIZE_ROL, 0, (Code + 1) << SIZE_ROL, ACT_SIZE, null
				);	
	}
}
