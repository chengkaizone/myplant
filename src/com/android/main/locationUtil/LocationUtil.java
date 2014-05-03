package com.android.main.locationUtil;

import com.android.model.Model;

public class LocationUtil {
	//x轴的起始坐标
	public static int cellposX=80;
	//y轴的起始坐标
	public static int cellposY=70;
	//每一个格子的宽
	public static int cellWidth=30;
	//每一个格子的高
	public static int cellHeight=60;
	//x轴上格子的最大个数
	public static int maxColumnX=9;
	//y轴上格子的最大个数
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
