package ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;


import config.BarsConfig;
import config.FrameConfig;
import config.GameConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

@SuppressWarnings("serial")
public class PanelGame extends JPanel {
	
	private List<Bars> bar= null;
	private JButton btnConfig;
	private Point cfgPoint = GameConfig.getFRAME_CONFIG().getBtnConfig().getCfg();
	private Point startPoint = GameConfig.getFRAME_CONFIG().getBtnConfig().getStart();
	private JButton btnStart;
	private static final int BTN_H = GameConfig.getFRAME_CONFIG().getBtnConfig().getButtonH();
	private static final int BTN_W = GameConfig.getFRAME_CONFIG().getBtnConfig().getButtonW();
	private GameControl gameControl;
	
	
	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}
	public PanelGame(GameDto dto, GameControl gameControl){
		this.gameControl = gameControl;
		//初始化组件
		this.setLayout(null);
		initComponent();
		//初始化边框
		initBars(dto);
		//初始化按钮
		initButtons();
	}
	private void initButtons() {
		//初始化设置按钮
		this.btnConfig = new JButton(Img.IMG_CONFIG);
		this.btnConfig.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gameControl.showConfig();
			}
		});
		this.btnConfig.setBounds(
				this.cfgPoint.x,
				this.cfgPoint.y, 
				BTN_W,
				BTN_H);
		//初始化开始按钮
		this.btnStart = new JButton(Img.IMG_START);
		this.btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gameControl.start();
				requestFocus();
			}
		});
		this.btnStart.setBounds(
				this.startPoint.x, 
				this.startPoint.y, 
				BTN_W, 
				BTN_H);
		this.add(this.btnConfig);
		this.add(this.btnStart);
	}
	/*
	 * 安装玩家控制器
	 * 
	 */
	public void setGameControl(PlayerControl control){
		this.addKeyListener(control);
	}
	/*
	 * 初始化组件
	 * 
	 */
	
	private void initComponent(){
		
	}
	/*
	 * 初始化边框窗体
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void initBars(GameDto dto){
		//获得游戏配置
		FrameConfig cfg = GameConfig.getFRAME_CONFIG();
		//获取边窗配置
		List<BarsConfig> bc = cfg.getBarConfig();
		//创建边窗数组
		bar = new ArrayList<Bars>(bc.size());
		//创建所有边窗对象
		for(BarsConfig b : bc){
			try{
			//获得类对象
			Class<?> cls = Class.forName(b.getClassName());
			//获得构造函数
			Constructor ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
			//调用构造函数创建对象
			Bars c = (Bars)ctr.newInstance(
					b.getX(),
					b.getY(),
					b.getW(),
					b.getH()
			); 
			//设置游戏数据对象
			c.setDto(dto);
			//把创建的对象放入集合
			bar.add(c);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < bar.size(); i++)
			bar.get(i).paint(g);
	}
	public void buttonSwitch(boolean key){
		this.btnConfig.setEnabled(key);
		this.btnStart.setEnabled(key);
	}
}
