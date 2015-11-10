package config;

import org.dom4j.Element;

public class DataConfig {
	private final DataInterfaceConfig dataA;
	private final DataInterfaceConfig dataB;
	private final int maxRow;
	
	public DataConfig(Element data) {
		this.dataA = new DataInterfaceConfig(data.element("dataA"));
		this.dataB = new DataInterfaceConfig(data.element("dataB"));
		this.maxRow = Integer.parseInt(data.attributeValue("maxRow"));
	}

	public DataInterfaceConfig getDataA() {
		return dataA;
	}

	public DataInterfaceConfig getDataB() {
		return dataB;
	}

	public int getMaxRow() {
		return maxRow;
	}
}
