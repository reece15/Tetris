package ui;

import java.awt.Graphics;

public class BarsButton extends Bars{
	
	public BarsButton(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.createBars(g);
	}
}
