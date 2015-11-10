package entity;


import java.awt.Point;
import java.util.List;

import config.GameConfig;

public class GameAct {
	/*
	 * 方块数组
	 * 
	 */
	private Point[] actPoint;
	/*
	 * 方块编号
	 * 
	 */
	private int actCode;
	private static int MIN_X = GameConfig.getSYSTEM_CONFIG().getMinX();
	private static int MAX_X = GameConfig.getSYSTEM_CONFIG().getMaxX();
	private static int MIN_Y = GameConfig.getSYSTEM_CONFIG().getMinY();
	private static int MAX_Y = GameConfig.getSYSTEM_CONFIG().getMaxY();
	
	private static List<Point[]> TYPE_CONFIG = GameConfig.getSYSTEM_CONFIG().getTypeConfig();

	
	public GameAct(int actCode){
		this.actCode = actCode;
		this.init(actCode);
	}
	
	public void init(int actCode){
		this.actCode = actCode;
		Point[] points = TYPE_CONFIG.get(actCode);
		actPoint = new Point[points.length];
		for(int i = 0; i < points.length; i++){
			actPoint[i] = new Point(points[i].x, points[i].y);
		}
	}
	
	public Point[] getActPoint() {
		return actPoint;
	}
	/*
	 * 方块移动
	 * @param moveX x偏移量
	 * @param moveY y偏移量
	 */
	public boolean move(int moveX, int moveY, boolean[][] map){
		for(int i = 0; i < actPoint.length; i++){
			int newX = actPoint[i].x + moveX;
			int newY = actPoint[i].y + moveY;
			if(this.isOverMap(newX, newY, map)){
				return false;
			}
		}
		for(int i = 0; i < actPoint.length; i++){
			actPoint[i].x += moveX;
			actPoint[i].y += moveY;
		}
		return true;
	}
	/*方块旋转
	 * 
	 * 顺时针:
	 * 
	 * A.x = 0.y + 0.x - B.y
	 * A.y = 0.y - 0.x + B.x
	 */
	public void round(boolean[][] map){
		if(this.actCode == 4){
			return;
		}
		for(int i = 1; i < actPoint.length; i++){
			int newX = actPoint[0].y + actPoint[0].x - actPoint[i].y;
			int newY = actPoint[0].y - actPoint[0].x + actPoint[i].x;
			//判断是否可以旋转
			if(this.isOverMap(newX, newY, map)){
				return;
			}
		}
		for(int i = 1; i < actPoint.length; i++){
			int newX = actPoint[0].y + actPoint[0].x - actPoint[i].y;
			int newY = actPoint[0].y - actPoint[0].x + actPoint[i].x;
			actPoint[i].x = newX;
			actPoint[i].y = newY;
		}
	}
	private boolean isOverMap(int x, int y, boolean[][] map){
		return (x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y || map[x][y]);
	}
	/*
	 * 获取方块编号
	 * 
	 */
	public int getActCode() {
		return actCode;
	}
}
