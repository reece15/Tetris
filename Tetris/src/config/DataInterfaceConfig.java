package config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class DataInterfaceConfig {
	private final String className;
	
	private final Map<String, String> param;
	
	@SuppressWarnings("unchecked")
	public DataInterfaceConfig(Element e){
		this.className = e.attributeValue("class");
		
		this.param = new HashMap<String, String>();
		List<Element> params = e.elements("param");
		for(Element each : params){
			this.param.put(each.attributeValue("key"), each.attributeValue("value"));
		}
	}

	public String getClassName() {
		return className;
	}

	public Map<String, String> getParam() {
		return param;
	}
}
