package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.FrameUtil;
import control.GameControl;

@SuppressWarnings("serial")
public class FrameSavePoint extends JFrame {
	
	private JButton btnOk = null;
	private JLabel pointShow = null;
	private JTextField loseName;
	private JLabel errMsg;
	private GameControl gameControl;
	
	public FrameSavePoint(GameControl gameControl){
		this.pointShow = new JLabel();
		this.gameControl = gameControl;
		this.setTitle("保存记录");
		this.setSize(256, 128);
		this.setLayout(new BorderLayout());
		this.createCom();
		this.createAction();
		FrameUtil.setFrameCenter(this);
		this.setResizable(false);
	}
	
	public void show(int point){
		this.pointShow.setText("您的得分:" + Integer.toString(point));
		this.setVisible(true);
	}
	private void createAction(){
		//添加事件监听
		this.btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String name = loseName.getText();
				if(name.length() > 5 || name.length() == 0){
					errMsg.setText("名字0<len<5！");
				}else{
					gameControl.savePoint(name);
					setVisible(false);
				}
			}
		});
	}
	@SuppressWarnings("unused")
	private void createCom(){
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.errMsg = new JLabel("     ");
		this.errMsg.setForeground(Color.RED);
		north.add(this.pointShow);
		north.add(this.errMsg);
		this.add(north, BorderLayout.NORTH);
		
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		center.add(new JLabel("您的名字:"));
		this.loseName = new JTextField(10);
		center.add(this.loseName);
		this.add(center,BorderLayout.CENTER);
		
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.btnOk = new JButton("确定");
		south.add(this.btnOk);
		this.add(south, BorderLayout.SOUTH);
	}
}
