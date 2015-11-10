package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import dto.Player;

public class WorldData implements Data {

	private String url;
	private String token;
	//private 
	
	public WorldData(HashMap<String, String> param){
		this.url = param.get("url");
		this.token = param.get("token");
	}
	
	@SuppressWarnings("unchecked")
	public List<Element> getData(BufferedReader bufReader){
		try{
			//xml读取器
			SAXReader reader = new SAXReader();
			//读取XML文件
			Document doc = reader.read(bufReader);
			//XML根节点
			Element game = doc.getRootElement();
			//玩家
			List<Element> worldPlayer = game.elements("player");
			return worldPlayer;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}
	public BufferedReader open(String target){
		URL getUrl = null;
		
		try {
			getUrl = new URL(target);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
            connection.setConnectTimeout(2000);
            connection.connect(); 
            return new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List<Player> loadData() {
		
		List<Player> players = new ArrayList<Player>();
		String target = this.url + "?token=" + this.token;
		
		List<Element> worldPlayer = this.getData(this.open(target));
		if(worldPlayer == null){
			return null;
		}
		try {
			for(Element player : worldPlayer){
				players.add(new Player(player.attributeValue("name"), Integer.parseInt(player.attributeValue("point"))));
			}
        } catch (Exception e) {
			e.printStackTrace();
		}
		
		return players;
	}

	public void saveData(Player pla) {
		String target = "";
		try {
			target = this.url + "?token=" + this.token + "&name=" + URLEncoder.encode(pla.getName(), "utf-8") + "&point=" + pla.getPoint();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		this.open(target);
	}

}
