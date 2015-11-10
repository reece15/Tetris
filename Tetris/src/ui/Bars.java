package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

abstract public class Bars {
	
	
	protected static final int IMG_NUM = GameConfig.getSYSTEM_CONFIG().getImgNum();
	protected static final Font DEF_FONT = new Font("黑体", Font.BOLD, 20);
	
	protected static final int  BLOOD_H = Img.IMG_BLOOD.getHeight(null);
	protected static final int  BLOOD_W = Img.IMG_BLOOD.getWidth(null);
	protected GameDto dto = null;
	//�߿��С
	protected static final int SIZE;
	protected static final int PADDING;
	
	protected static int BARS_H = Img.BARS.getHeight(null);
	protected static int BARS_W = Img.BARS.getWidth(null);
	//���Ͻ�x y ���
	protected int x;
	protected int y;
	//���ڸߺͿ�
	protected int h;
	protected int w;
	static{
		FrameConfig cfg = GameConfig.getFRAME_CONFIG();
		PADDING = cfg.getPadding();
		SIZE = cfg.getSize();
	}
	
	public Bars(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public GameDto getDto() {
		return dto;
	}

	public void setDto(GameDto dto) {
		this.dto = dto;
	}

	//���Ʊ߿�
	protected void createBars(Graphics g){
		//����
		g.drawImage(Img.BARS, x, y, x + SIZE, y + SIZE, 0, 0, SIZE, SIZE, null);
		//��
		g.drawImage(Img.BARS, x + SIZE, y, x + w - SIZE, y + SIZE, SIZE, 0, BARS_W - SIZE, SIZE, null);
		//����
		g.drawImage(Img.BARS, x + w - SIZE, y, x + w, y + SIZE, BARS_W - SIZE, 0, BARS_W, SIZE, null);
		//��
		g.drawImage(Img.BARS, x, y + SIZE, x + SIZE, y + h - SIZE, 0, SIZE, SIZE, BARS_H - SIZE, null);
		//��
		g.drawImage(Img.BARS, x + SIZE, y + SIZE, x + w - SIZE, y + h - SIZE, SIZE, SIZE, BARS_W - SIZE, BARS_H - SIZE, null);
		//��
		g.drawImage(Img.BARS, x + w - SIZE, y + SIZE, x + w, y + h - SIZE, BARS_W - SIZE, SIZE, BARS_W, BARS_H - SIZE, null);
		//����
		g.drawImage(Img.BARS, x, y + h - SIZE, x + SIZE, y + h, 0, BARS_H - SIZE, SIZE, BARS_H, null);
		//��
		g.drawImage(Img.BARS, x + SIZE, y + h - SIZE, x + w - SIZE, y + h, SIZE, BARS_H - SIZE, BARS_W - SIZE, BARS_H, null);
		//����
		g.drawImage(Img.BARS, x + w - SIZE, y + h - SIZE, x + w, y + h, BARS_W - SIZE, BARS_H - SIZE, BARS_W, BARS_H, null);
		
	}
	/*
	 * ��ʾ����
	 * @param x ���Ͻ�x���
	 * @param y ���Ͻ�y���
	 * @param count ����λ��
	 * @param g ���ʶ���
	 * @param num Ҫ���Ƶ�����
	 */
	protected void drawNumber(int x, int y, int count, Graphics g, int num){
		//ת��Ϊ�ַ�
		String strNum = Integer.toString(num);
		for(int i = 0; i < count; i++){
			if(count - i <= strNum.length()){
				int idx = i - count + strNum.length();
				int bit = strNum.charAt(idx) - '0';
				g.drawImage(
					Img.numImage, 
					this.x + x + IMG_NUM * i, 
					this.y + y, 
					this.x + x + IMG_NUM * (i + 1), 
					this.y + y + IMG_NUM, 
					bit * IMG_NUM, 
					0, 
					(bit + 1) * IMG_NUM, 
					IMG_NUM ,
					null
					);
			}
		}
	
	} 
	
	/*
	 * ����ֵ��
	 */
	protected void drawBlood(int x, int y, int w, int h, String title, String num, double value, double maxValue, Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, w, h);
		g.setColor(Color.WHITE);
		g.fillRect(x + 1, y + 1, w - 2, h - 2);
		g.setColor(Color.BLACK);
		g.fillRect(x + 2, y + 2, w - 4, h - 4);
		g.setColor(Color.RED);
		double p = value / maxValue;
		int bloodW = (int)(p * (w - 4));
		int subIdx = (int)(p * BLOOD_W);
		g.drawImage(
				Img.IMG_BLOOD, 
				x + 2, 
				y + 2, 
				x - 2 + bloodW, 
				y + h - 2, 
				subIdx, 
				0, 
				subIdx - 1, 
				BLOOD_H, 
				null
			);
		g.setColor(Color.WHITE);
		g.setFont(DEF_FONT);
		g.drawString(title, x + 200, y + 24);
		if(num != null){
			g.drawString(num, x + 4, y + 22);
		}
	}
	/*
	 * 
	 * ���м��ͼ
	 * 
	 */
	protected void drawImageAtCenter(Image img, Graphics g){
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		int imgX = this.x + (this.w - imgW >> 1);
		int imgY = this.y + (this.h - imgH >> 1);
		g.drawImage(img, imgX, imgY, null);
	}
	//����
	abstract public void paint(Graphics g);
}
