package ui;

import java.awt.Graphics;

public class BarsAbout extends Bars {
	

	public BarsAbout(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.createBars(g);
		this.drawImageAtCenter(Img.nameImage, g);
		//this.drawImageAtCenter(pauseImage, g);
	}
}
