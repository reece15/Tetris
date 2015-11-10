package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig {
	private final int width;
	private final int height;
	private final int size;
	private final int padding;
	private final int up;
	private final int sizeRol;
	private final String title;
	private final int loseIdx;
	
	
	private List<BarsConfig> barConfig;
	private BtnConfig btnConfig;
	/*
	 * 配置窗口参数
	 * 
	 * @param frame 配置文件的窗口配置元素
	 */
	@SuppressWarnings("unchecked")
	public FrameConfig(Element frame){
		this.width = Integer.parseInt(frame.attributeValue("width"));
		this.height = Integer.parseInt(frame.attributeValue("height"));
		this.padding = Integer.parseInt(frame.attributeValue("padding"));
		this.size = Integer.parseInt(frame.attributeValue("size"));
		this.up = Integer.parseInt(frame.attributeValue("up"));
		this.title = frame.attributeValue("title");
		this.sizeRol = Integer.parseInt(frame.attributeValue("sizeRol"));
		this.loseIdx = Integer.parseInt(frame.attributeValue("loseIdx"));
		List<Element> bars = frame.elements("bars");
		barConfig = new ArrayList<BarsConfig>();
		for(Element bar : bars){
			BarsConfig bc = new BarsConfig(
					bar.attributeValue("class"),
					Integer.parseInt(bar.attributeValue("x")),
					Integer.parseInt(bar.attributeValue("y")),
					Integer.parseInt(bar.attributeValue("w")),
					Integer.parseInt(bar.attributeValue("h"))
					);
			barConfig.add(bc);
		};
		this.btnConfig =  new BtnConfig(frame.element("button"));
		
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getSize() {
		return size;
	}
	public int getPadding() {
		return padding;
	}
	public List<BarsConfig> getBarConfig() {
		return barConfig;
	}
	public int getUp() {
		return up;
	}
	public String getTitle() {
		return title;
	}
	public int getSizeRol() {
		return sizeRol;
	}
	public int getLoseIdx() {
		return loseIdx;
	}
	public BtnConfig getBtnConfig() {
		return btnConfig;
	}
}
