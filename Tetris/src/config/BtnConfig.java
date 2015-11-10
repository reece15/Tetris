package config;

import java.awt.Point;

import org.dom4j.Element;

public class BtnConfig {
	
	private final int buttonW;
	private final int buttonH;
	private final Point start;
	private final Point cfg;
	
	public BtnConfig(Element button){
		this.buttonH = Integer.parseInt(button.attributeValue("h"));
		this.buttonW = Integer.parseInt(button.attributeValue("w"));
		this.cfg = new Point(Integer.parseInt(button.element("config").attributeValue("x")),Integer.parseInt(button.element("config").attributeValue("y")));
		this.start = new Point(Integer.parseInt(button.element("start").attributeValue("x")),Integer.parseInt(button.element("start").attributeValue("y")));

	}

	public int getButtonW() {
		return buttonW;
	}

	public int getButtonH() {
		return buttonH;
	}

	public Point getStart() {
		return start;
	}

	public Point getCfg() {
		return cfg;
	}
}
