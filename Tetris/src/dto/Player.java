﻿package dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Comparable<Player> , Serializable{
	
	private String name;
	
	private int point;
	
	public Player(String name, int point){
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}
	
	
	public void setPoint(int point) {
		this.point = point;
	}

	public int compareTo(Player pla) {
		return pla.point - this.point;
	}


	
}
