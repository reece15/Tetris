package ui;

import java.awt.Graphics;

public class BarsBg extends Bars {

	
	public BarsBg(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(Img.BG_LIST.get(this.dto.getLevel() % Img.BG_LIST.size()), x, y, w, h, null);

	}

}
