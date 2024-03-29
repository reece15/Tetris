﻿package config;

public class BarsConfig {
	
	private String className;
	private int x;
	private int y;
	private int w;
	private int h;
	
	public BarsConfig(String className, int x, int y, int w, int h){
		this.className = className;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public BarsConfig(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public String getClassName(){
		return className;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
}
