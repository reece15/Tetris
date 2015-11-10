package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class LocalData implements Data {
	
	private  final String path;
	public LocalData(HashMap<String, String> param){
		this.path = param.get("path");
	}
	@SuppressWarnings("unchecked")
	public List<Player> loadData() {
		ObjectInputStream ois = null;
		List<Player> players = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(path));
			try {
				players = (List<Player>)ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return players;
	}

	public void saveData(Player pla){
		ObjectOutputStream oos = null;
		List<Player> temp = null;
		
		List<Player> players = this.loadData();
		if(players == null){
			players = new ArrayList<Player>();
		}
		players.add(pla);
		if(players.size() > 5){
			Collections.sort(players);
			temp = new ArrayList<Player>();
			for(int i = 0; i < 5; i++){
				temp.add(players.get(i));
			}
		}
		else{
			temp = players;
		}
		
		try {
			oos = new ObjectOutputStream(
					new FileOutputStream(path));
			oos.writeObject(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}



}
