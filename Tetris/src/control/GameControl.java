package control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import services.GameTetris;
import ui.FrameGame;
import ui.PanelGame;
import ui.window.JFrameConfig;
import ui.window.FrameSavePoint;
import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dto.GameDto;
import dto.Player;
/*
 * 接受玩家键盘事件
 * 控制画面
 * 控制游戏逻辑
 * 
 */
public class GameControl {
	/*
	 * 数据访问接口A B
	 */
	private Data dataA;
	private Data dataB;
	/*
	 * 游戏界面
	 */
	private PanelGame panelGame;
	/*
	 * 游戏逻辑
	 */
	private GameTetris gameService;
	private Thread gameThread = null;
	private HashMap<Integer, Method> action;
	private JFrameConfig frameConfig;
	private GameDto dto = null;
	private PlayerControl control;
	private FrameSavePoint frameSave;
	
	public GameControl(){
		//创建数据源
		this.dto = new GameDto();
		//创建游戏面板  安装游戏控制器
		this.panelGame = new PanelGame(dto, this);
		//创建游戏逻辑块（安装游戏数据源）
		this.gameService = new  GameTetris(dto);
		//创建玩家控制器（连接游戏控制器）
		this.control = new PlayerControl(this);
		//安装玩家控制器
		this.panelGame.setGameControl(control);
		//创建游戏窗体（安装游戏面板）
		new FrameGame(this.panelGame);
		DataInterfaceConfig cfgDataA = GameConfig.getDATA_CONFIG().getDataA();
		DataInterfaceConfig cfgDataB = GameConfig.getDATA_CONFIG().getDataB();
		this.dataA = createDataObject(cfgDataA);
		this.dataB = createDataObject(cfgDataB);
		this.dto.setWorldRecode(dataA.loadData());
		this.panelGame.repaint();
		this.dto.setLocalRecode(dataB.loadData());
		this.setControlConfig();
		this.frameConfig = new JFrameConfig(this);
		this.frameSave = new FrameSavePoint(this);
	}
	/*
	 * 创建数据对象
	 * 
	 */
	private Data createDataObject(DataInterfaceConfig cfg){
		try {
			//获得类对象
			Class<?> cls = Class.forName(cfg.getClassName());
			//获得构造器
			Constructor<?> ctr = cls.getConstructor(HashMap.class);
			//创建对象
			return (Data)ctr.newInstance(cfg.getParam());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void KeyLeft() {
		this.gameService.KeyLeft();
		this.panelGame.repaint();
	}
	public void KeyRight() {
		this.gameService.KeyRight();
		this.panelGame.repaint();
	}

	public void KeyDown() {
		this.gameService.KeyDown();
		this.panelGame.repaint();
	}

	public void KeyUp() {
		this.gameService.KeyUp();
		this.panelGame.repaint();
	}


	public void KeyFunUp() {
		this.gameService.KeyFunUp();
		this.panelGame.repaint();
	}
	public void actionByKeyCode(int keyCode) {
		try {
			if(this.action.containsKey(keyCode)){
				this.action.get(keyCode).invoke(this.gameService);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.panelGame.repaint();
	}
	/*
	 * 读取控制配置
	 */
	@SuppressWarnings("unchecked")
	private void setControlConfig(){
		this.action = new HashMap<Integer, Method>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/control.dat"));
			HashMap<Integer, String> cfgSet = (HashMap)ois.readObject();
			ois.close();
			Set<Entry<Integer, String>> entrySet = cfgSet.entrySet();
			for(Entry<Integer, String> e : entrySet){
				action.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 显示设置窗口
	 */
	public void showConfig(){
		this.frameConfig.setVisible(true);
	}
	/*
	 * 刷新窗口
	 */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}
	public void start() {
		//灰色按钮
		this.panelGame.buttonSwitch(false);
		this.frameConfig.setVisible(false);
		this.frameSave.setVisible(false);
		//初始化游戏
		this.gameService.start();
		this.panelGame.repaint();
		//创建线程对象
		this.gameThread = new Thread(){
			@Override
			public void run(){
				while(dto.isFlagStart()){
					try {
						int level = dto.getLevel();
						Thread.sleep(level < 16 ? (level * (-40) + 740) : 100);
						if(dto.isPause()){
							continue;
						}
						gameService.MainAction();
						panelGame.repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				afterLose();
			}
		};
		this.gameThread.start();
		this.panelGame.repaint();
	}
	public void savePoint(String loseName) {
		Player p = new Player(loseName, this.dto.getNowPoint());
		
		this.dataA.saveData(p);
		this.dataB.saveData(p);
		
		this.dto.setLocalRecode(dataB.loadData());
		this.dto.setWorldRecode(dataA.loadData());
		this.panelGame.repaint();
	}
	private void afterLose() {
		//显示保存得分窗口
		if((!this.dto.isFack()) && (this.dto.getNowPoint() > 0)){
			this.frameSave.show(this.dto.getNowPoint());
		}
		//使按钮可以点击
		this.panelGame.buttonSwitch(true);
	}
	public void repaint(){
		this.panelGame.repaint();
	}
}
