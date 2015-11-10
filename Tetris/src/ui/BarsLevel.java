package ui;

import java.awt.Graphics;

public class BarsLevel extends Bars {
	
	
	public BarsLevel(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.createBars(g);
		g.drawImage(Img.LEVEL_TITLE, this.x + PADDING, this.y + PADDING, null);
		this.drawNumber(10, 64, 4, g, this.getDto().getLevel());   
	}
	
}
