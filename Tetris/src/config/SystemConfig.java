package config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig {
	
	private final int minX;
	private final int maxX;
	private final int minY;
	private final int maxY;
	private final int levelUp;
	private final int pointBit;
	private final int imgNum;
	private final int maxType;
	private final List<Point[]> typeConfig;
	
	@SuppressWarnings("unchecked")
	public SystemConfig(Element system) {
		this.minX = Integer.parseInt(system.attributeValue("minX"));
		this.maxX = Integer.parseInt(system.attributeValue("maxX"));
		this.minY = Integer.parseInt(system.attributeValue("minY"));
		this.maxY = Integer.parseInt(system.attributeValue("maxY"));
		this.levelUp = Integer.parseInt(system.attributeValue("levelup"));
		this.pointBit = Integer.parseInt(system.attributeValue("pointBit"));
		this.imgNum = Integer.parseInt(system.attributeValue("imgNum"));
		this.maxType = Integer.parseInt(system.attributeValue("maxType"));
		List<Element> rects = system.elements("rect");
		typeConfig = new ArrayList<Point[]>(rects.size());
		for(Element rect : rects){
			List<Element> pointConfig = rect.elements("point");
			Point[] points = new Point[pointConfig.size()];
			for(int i = 0; i < points.length; i++){
				int x = Integer.parseInt(pointConfig.get(i).attributeValue("x"));
				int y = Integer.parseInt(pointConfig.get(i).attributeValue("y"));
				points[i] = new Point(x, y);
			}
			typeConfig.add(points);
		}
	}

	public int getMinX() {
		return minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}

	public List<Point[]> getTypeConfig() {
		return typeConfig;
	}

	public int getLevelUp() {
		return levelUp;
	}

	public int getPointBit() {
		return pointBit;
	}

	public int getImgNum() {
		return imgNum;
	}

	public int getMaxType() {
		return maxType;
	}
}
