package config;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
	
	private static FrameConfig FRAME_CONFIG = null;
	private static SystemConfig SYSTEM_CONFIG = null;
	private static DataConfig DATA_CONFIG = null;
	
	static{
		try{
		//xml读取器
		SAXReader reader = new SAXReader();
		//读取XML文件
		Document doc = reader.read("data/cfg.xml");
		//XML根节点
		Element game = doc.getRootElement();
		//配置窗口
		FRAME_CONFIG = new FrameConfig(game.element("frame"));
		//配置系统参数
		SYSTEM_CONFIG = new SystemConfig(game.element("system"));
		//配置数据访问参数
		DATA_CONFIG = new DataConfig(game.element("data"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public static FrameConfig getFRAME_CONFIG() {
		return FRAME_CONFIG;
	}

	public static SystemConfig getSYSTEM_CONFIG() {
		return SYSTEM_CONFIG;
	}

	public static DataConfig getDATA_CONFIG() {
		return DATA_CONFIG;
	}

	private GameConfig(){}
		
}
