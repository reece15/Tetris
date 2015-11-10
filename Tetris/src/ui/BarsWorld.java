package ui;

import java.awt.Graphics;
import java.util.List;

import dto.Player;

public class BarsWorld extends BarsData{
	
	
	
	public BarsWorld(int x, int y, int w, int h){
		super(x, y, w, h);
		
	}
	
	public void paint(Graphics g){
		this.createBars(g);
		List<Player> players = this.dto.getWorldRecode();
		this.showData(Img.WORLD_TITLE, players, g);
	}
}
