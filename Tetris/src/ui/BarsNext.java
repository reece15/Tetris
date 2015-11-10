package ui;

import java.awt.Graphics;

public class BarsNext extends Bars {
	
	
	
	public BarsNext(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.createBars(g);
		if(this.dto.isFlagStart()){
			this.drawImageAtCenter(Img.NEXT_ACT[this.dto.getNext()],g);
		}
	}
	
}
