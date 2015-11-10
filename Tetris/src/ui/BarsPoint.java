package ui;

import java.awt.Graphics;

import config.GameConfig;

public class BarsPoint extends Bars {
	
	private static final int LEVEL_UP = GameConfig.getSYSTEM_CONFIG().getLevelUp();
	
	
	private static final int POINT_BIT = GameConfig.getSYSTEM_CONFIG().getPointBit();
	private final int expW;
	private final int pointX;
	private final int pointY;
	private final int rmLineY;
	private final int expY;
	public BarsPoint(int x, int y, int w, int h){
		super(x, y, w, h);
		//初始化分数显示X坐标
		this.pointX = this.w - IMG_NUM * POINT_BIT - PADDING;
		//初始化分数显示Y坐标
		this.pointY = PADDING;
		//初始化消行显示Y坐标
		this.rmLineY = this.pointY + Img.IMG_POINT.getHeight(null) + PADDING;
		//初始化经验值槽宽度
		this.expW = this.w - (PADDING<<1);
		//初始化经验值Y坐标
		this.expY = this.rmLineY + Img.IMG_RMLINE.getHeight(null) + PADDING;
	}
	
	public void paint(Graphics g){
		this.createBars(g);
		//窗口标题 分数
		g.drawImage(Img.IMG_POINT, this.x + PADDING, this.y + PADDING, null);
		this.drawNumber(pointX, pointY, POINT_BIT, g, this.dto.getNowPoint());
		//窗口标题 消行
		g.drawImage(Img.IMG_RMLINE, this.x + PADDING, this.y + rmLineY, null);
		this.drawNumber(pointX, rmLineY, POINT_BIT, g, this.dto.getNowMoveLine());
		//绘制经验值槽
		int rmLine = this.dto.getNowMoveLine();
		int h = 32;
		this.drawBlood(this.x + PADDING, this.y + expY, expW, h, "下一级", Integer.toString(((rmLine/20)+1) * 20), (double)(rmLine % LEVEL_UP), (double)LEVEL_UP, g);
	}
	
}
