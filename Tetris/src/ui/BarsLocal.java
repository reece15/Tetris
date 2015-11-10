package ui;

import java.awt.Graphics;
import java.util.List;


import dto.Player;

public class BarsLocal extends BarsData{
	
	public BarsLocal(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void paint(Graphics g){
		this.createBars(g);
		List<Player> players = this.dto.getLocalRecode();
		this.showData(Img.LOCAL_TITLE, players, g);
	}
}
