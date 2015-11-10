package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import config.GameConfig;
import entity.GameAct;

public class GameDto{
	private List<Player> worldRecode = null;
	
	private List<Player> localRecode = null;
	
	private boolean[][] gameMap;
	
	private GameAct gameAct;
	
	private int next;
	
	private boolean flagStart = false;
	
	private int level;
	
	private int nowPoint;
	
	private boolean isFack;
	
	private int nowMoveLine;
	
	private boolean shadow = true;
	
	private static final int MAX_TYPE = GameConfig.getSYSTEM_CONFIG().getMaxType();
	
	private static final int MAX_ROW = GameConfig.getDATA_CONFIG().getMaxRow();
	
	public final int GAME_H = GameConfig.getSYSTEM_CONFIG().getMaxY() + 1;
	
	public final int GAME_W = GameConfig.getSYSTEM_CONFIG().getMaxX() + 1;
	
	private boolean pause;
	
	public boolean isPause() {
		return pause;
	}

	public void switchPause() {
		this.pause = !pause;
	}

	public GameDto(){
		this.dtoInit();
	}

	public void dtoInit() {
		//初始化地图
		this.gameMap = new boolean[GAME_W][GAME_H];
		this.next = new Random().nextInt(MAX_TYPE);
		this.level = 0;
		this.nowPoint = 0;
		this.nowMoveLine = 0;
		this.pause = false;
		this.isFack = false;
	}

	public List<Player> getWorldRecode() {
		return worldRecode;
	}

	public void setWorldRecode(List<Player> worldRecode) {
		this.worldRecode = setRecode(worldRecode);
	}

	public List<Player> getLocalRecode() {
		return localRecode;
	}

	public void setLocalRecode(List<Player> localRecode) {
		this.localRecode = setRecode(localRecode);
	}
	private List<Player> setRecode(List<Player> Recode){
		if(Recode == null){
			Recode = new ArrayList<Player>();
		}
		while(Recode.size() < MAX_ROW){
			Recode.add(new Player("No Data", 0));
		}
		Collections.sort(Recode);
		return Recode;
	}
	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public GameAct getGameAct() {
		return gameAct;
	}

	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNowPoint() {
		return nowPoint;
	}

	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}

	public int getNowMoveLine() {
		return nowMoveLine;
	}

	public void setNowMoveLine(int nowMoveLine) {
		this.nowMoveLine = nowMoveLine;
	}

	public boolean isFlagStart() {
		return flagStart;
	}

	public void setFlagStart(boolean flagStart) {
		this.flagStart = flagStart;
	}
	
	public void switchShadow(){
		this.shadow = !this.shadow;
	}

	public boolean isShadow() {
		return shadow;
	}

	public boolean isFack() {
		return isFack;
	}

	public void setFack(boolean isFack) {
		this.isFack = isFack;
	}
	
}
