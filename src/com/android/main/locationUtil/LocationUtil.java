package com.android.main.locationUtil;

import com.android.model.Model;

public class LocationUtil {
	//x�����ʼ����
	public static int cellposX=80;
	//y�����ʼ����
	public static int cellposY=70;
	//ÿһ�����ӵĿ�
	public static int cellWidth=30;
	//ÿһ�����ӵĸ�
	public static int cellHeight=60;
	//x���ϸ��ӵ�������
	public static int maxColumnX=9;
	//y���ϸ��ӵ�������
	public static int maxColumnY=5;
	
	
	public static void setLocation(Model model){
		int x=model.getPosX()+model.getWidth()-cellposX;
		int y=model.getPosY()+model.getHeight()-cellposY;
		x=(x/cellWidth)*cellWidth+cellposX;
		y=(y/cellHeight)*cellHeight+cellposY;
		
		if(x>=320){
			x=320;
		}
		if(x<=80){
			x=80;
		}
		if(y<=70){
			y=70;
		}
		if(y>=290){
			y=290;
		}
		model.setPosXY(x-model.getWidth(),y-model.getHeight());
	}
	public static void location(Model model){
		int y=model.getPosY()+model.getHeight()-cellposY;
		y=(y/cellHeight)*cellHeight+cellposY;
		
		if(y<=70){
			y=70;
		}
		if(y>=290){
			y=290;
		}
		model.setPosY(y-model.getHeight());
	}
	
}
