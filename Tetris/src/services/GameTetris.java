package services;

import java.awt.Point;
import java.util.Random;

import config.GameConfig;
import dto.GameDto;
import entity.GameAct;

public class GameTetris implements GameService{
	private GameDto gameDto;
	private static final int LEVEL_UP = GameConfig.getSYSTEM_CONFIG().getLevelUp();
	private Random random = new Random();
	private static final int MAX_TYPE = GameConfig.getSYSTEM_CONFIG().getMaxType();
	public GameTetris(GameDto dto){
		this.gameDto = dto;
		
	}
	public void KeyLeft() {
		if(this.gameDto.isPause()){
			return;
		}
		this.gameDto.getGameAct().move(-1, 0, this.gameDto.getGameMap());
	}
	public void KeyRight() {
		if(this.gameDto.isPause()){
			return;
		}
		this.gameDto.getGameAct().move(1, 0, this.gameDto.getGameMap());
	}

	public boolean KeyDown() {
		if(this.gameDto.isPause()){
			return false;
		}
		boolean moveResult = false;
		synchronized(this.gameDto){
			moveResult = this.gameDto.getGameAct().move(0, 1, this.gameDto.getGameMap());
		}
		if(moveResult){
			return false;
		}
		//获得一个地图对象
		boolean[][] map = this.gameDto.getGameMap();
		Point[] act = this.gameDto.getGameAct().getActPoint();
		//方块堆积
		for(int i = 0; i < act.length; i++){
			map[act[i].x][act[i].y] = true;
		}
		//消行
		int lineNum = this.removeCheck();
		//算等级
		this.levelUp(lineNum);
		//算分
		this.AddPoint(lineNum);
		//得到下一个方块
		this.gameDto.getGameAct().init(this.gameDto.getNext());
		//随机生成下一个方块
		this.gameDto.setNext(new Random().nextInt(MAX_TYPE));
		//检查游戏是否失败
		if(this.isLose()){
			//切换游戏状态
			this.gameDto.setFlagStart(false);
		}
		return true;
	}
	/*
	 * 旋转
	 */
	public void KeyUp() {
		if(this.gameDto.isPause()){
			return;
		}
		synchronized(this.gameDto){
			this.gameDto.getGameAct().round(this.gameDto.getGameMap());
		}
	}
	/*
	 * 直接落下键
	 */
	public void KeyFunDown() {
		if(this.gameDto.isPause()){
			return;
		}
		while(!this.KeyDown());
		
	}
	/*
	 * 阴影开关键
	 */
	public void KeyFunLeft() {
		this.gameDto.switchShadow();
	}
	/*
	 * 暂停键
	 */
	public void KeyFunRight() {
		if(this.gameDto.isFlagStart()){
			this.gameDto.switchPause();
		}
	}
	/*
	 * 作弊键
	 */
	public void KeyFunUp() {
		this.gameDto.setFack(true);
		this.levelUp(4);
		this.AddPoint(4);
	}
	
	private boolean isLose() {
		//获得现在的活动方块
		Point[] actPoints = this.gameDto.getGameAct().getActPoint();
		//获得现在的地图
		boolean[][] map = this.gameDto.getGameMap();
		for(int i = 0; i < actPoints.length; i++){
			if(map[actPoints[i].x][actPoints[i].y]){
				return true;
			}
		}
		return false;
	}
	private void AddPoint(int lineNum){
		
		this.gameDto.setNowPoint(lineNum * 10 + lineNum * lineNum * 5 + this.gameDto.getNowPoint());
	}
	private void levelUp(int lineNum) {
		int rmLine = this.gameDto.getNowMoveLine();
		this.gameDto.setNowMoveLine(rmLine + lineNum);
		this.gameDto.setLevel(this.gameDto.getNowMoveLine() / LEVEL_UP);
	}
	/*
	 * 消行
	 * 
	 */
	private int removeCheck() {
		boolean[][] map = this.gameDto.getGameMap();
		int lineNum = 0;
		
		for(int y = 0; y < this.gameDto.GAME_H; y++){
			if(isFullLine(y, map)){
				this.removeLine(y, map);
				lineNum++;
			}
		}
		return lineNum;
	}
	private void removeLine(int y, boolean[][] map) {
		for(int i = 0; i < this.gameDto.GAME_W; i++){
			for(int j = y; j > 0; j--){
				map[i][j] = map[i][j-1];
			}
			map[i][0] = false;
		}
	}
	private boolean isFullLine(int y, boolean[][] map){
		
		for(int x = 0; x < this.gameDto.GAME_W; x++){
			if(!map[x][y]){
				return false;
			}
		}
		return true;
	}
	
	
	public void start() {
		this.gameDto.setNext(random.nextInt(MAX_TYPE));
		this.gameDto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		this.gameDto.setFlagStart(true);
		//dto初始化
		this.gameDto.dtoInit();
	}
	public void MainAction() {
		this.KeyDown();
	}
}
