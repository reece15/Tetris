package ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextControl extends JTextField {
	
	private int keyCode;
	
	private final String methodName;
	
	public TextControl(int x, int y, int w, int h,  String methodName){
		//初始化keyCode
		this.methodName = methodName;
		//设置位置
		this.setBounds(x, y, w, h);
		//事件监听
		this.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e){
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				setText(KeyEvent.getKeyText((e.getKeyCode())));
				setKeyCode(e.getKeyCode());
			}
		});
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		this.setText(KeyEvent.getKeyText(keyCode));
	}

	public int getKeyCode() {
		return keyCode;
	}

	public String getMethodName() {
		return methodName;
	}
}
