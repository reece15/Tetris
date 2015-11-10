package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import ui.Img;
import util.FrameUtil;
import control.GameControl;

@SuppressWarnings("serial")
public class JFrameConfig extends JFrame {
	
	private JButton btnOk = new JButton("确定");
	
	private JButton btnCancel = new JButton("取消");
	
	private JButton btnUse = new JButton("应用");
	
	private JList skinList = null;
	
	private DefaultListModel skinData = new DefaultListModel();
	
	private TextControl[] keyTexts = new TextControl[8];
	
	private final static String PATH = "data/control.dat";
	
	private JLabel errMsg = new JLabel();
	
	private JPanel skinView = null;
	
	private GameControl gameContol;
	
	private Image[] skinViewList = null;
	
	private final static Image GAME_FACE = new ImageIcon("data/psp.png").getImage();
	
	private final static String[] METHOD_NAMES = {
		"KeyRight",
		"KeyUp",
		"KeyLeft",
		"KeyDown",
		"KeyFunLeft",
		"KeyFunUp",
		"KeyFunRight",
		"KeyFunDown",
		};
	public JFrameConfig(GameControl gameControl){
		
		this.gameContol = gameControl;
		//设置布局管理器  边界布局
		this.setLayout(new BorderLayout());
		//初始化按钮输入框
		this.initKeyText();
		//添加主面板
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		//TODO 测试用
		//设置窗口大小
		this.setSize(653, 390);
		this.setResizable(false);
		//设置居中
		FrameUtil.setFrameCenter(this);
	}
	/* 确定
	 * 写入文件并关闭窗口
	 */
	private void okBtnEvent(){
		if(writeConfig()){
			this.setVisible(false);
			this.gameContol.setOver();
			this.errMsg.setText(null);
		}
	}
	/*
	 * 
	 * 
	 */
	private void cancelBtnEvent(){
		this.setVisible(false);
		this.errMsg.setText(null);
	}
	/*
	 * 应用
	 * 只写入文件
	 */
	private void useBtnEvent(){
		writeConfig();
	}
	/*
	 * 文件操作
	 */
	private boolean writeConfig(){
		HashMap<Integer, String> keySet = new HashMap<Integer, String>();
		for(int i = 0; i < this.keyTexts.length; i++){
			int keyCode = this.keyTexts[i].getKeyCode();
			if(keyCode == 0){
				this.errMsg.setText("错误的按键！");
				return false;
			}
			keySet.put(keyCode, this.keyTexts[i].getMethodName());
		}
		if(keySet.size() != 8){
			this.errMsg.setText("有多个按键的功能相同，按键冲突！");
			return false;
		}
		try {
			//切换皮肤
			Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()).toString() + "/");
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
			this.errMsg.setText("操作成功！");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			this.errMsg.setText("保存失败！");
			return false;
		}
	}
	/*
	 * 初始化按钮输入框
	 */
	@SuppressWarnings("unchecked")
	private void initKeyText() {
		int x = 0;
		int y = 60;
		int w = 64;
		int h = 20;
		for(int i = 0; i < 4; i++){
			keyTexts[i] = new TextControl(x, y, w, h, METHOD_NAMES[i]);
			y += 32;
		}
		x = 580;
		y = 64;
		for(int i = 4; i < 8; i++){
			keyTexts[i] = new TextControl(x, y, w, h, METHOD_NAMES[i]);
			y += 32;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
			HashMap<Integer, String> cfgSet = (HashMap)ois.readObject();
			ois.close();
			Set<Entry<Integer, String>> entrySet = cfgSet.entrySet();
			for(Entry<Integer, String> e : entrySet){
				for(TextControl tc : keyTexts){
					if(tc.getMethodName().equals(e.getValue())){
						tc.setKeyCode(e.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//创建按钮面板
	private JPanel createButtonPanel() {
		//创建按钮面板
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.errMsg.setForeground(Color.RED);
		jp.add(this.errMsg);
		//添加事件监听
		this.btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				okBtnEvent();
				gameContol.repaint();
				requestFocus();
			}
		});
		jp.add(this.btnOk);
		//添加事件监听
		this.btnUse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				useBtnEvent();
				gameContol.repaint();
			}
		});
		jp.add(this.btnUse);
		this.btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cancelBtnEvent();
			}
		});
		jp.add(this.btnCancel);

		return jp;
	}
	//创建主面板(选项卡面板)
	private JTabbedPane createMainPanel(){
		
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("控制设置", this.createControlPanel());
		jtp.addTab("皮肤设置", this.createSkinPanel());
		
		return jtp;
	}
	private JPanel createControlPanel() {
		JPanel jp = new JPanel(){
			
			{
				//设置布局管理器
				this.setLayout(null);
				//添加按钮键值 输入框
				for(int i = 0; i < keyTexts.length; i++){
					this.add(keyTexts[i]);
				}
				
			}
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(GAME_FACE,0, 0, null);
			}
		};
		
		return jp;
	}
	/*
	 * 皮肤面板
	 */
	private JPanel createSkinPanel() {
		
		JPanel jp = new JPanel(new BorderLayout());
		
		File dir = new File(Img.IMG_PATH);
		File[] files = dir.listFiles();
		this.skinViewList = new Image[files.length];
		for(int i = 0; i < files.length; i++){
			this.skinData.addElement(files[i].getName());
			this.skinViewList[i] = new ImageIcon(files[i].getPath() + "/view.jpg").getImage();
		}
		this.skinList = new JList(this.skinData);
		this.skinList.setSelectedIndex(3);
		this.skinList.addMouseListener(new MouseAdapter(){

			public void mouseReleased(MouseEvent e) {
				repaint();
			}
			
		});
		jp.add(new JScrollPane(this.skinList),BorderLayout.WEST);
		this.skinView = new JPanel(){
			
			public void paintComponent(Graphics g){
				Image showImg = skinViewList[skinList.getSelectedIndex()];
				int x = (this.getWidth() - showImg.getWidth(null))>>1;
				int y = (this.getHeight() - showImg.getHeight(null))>>1;
				g.drawImage(showImg , x, y, null);
			}
		};
		jp.add(this.skinView, BorderLayout.CENTER);
		
		return jp;
	}
}
