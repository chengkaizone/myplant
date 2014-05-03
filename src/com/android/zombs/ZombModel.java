package com.android.zombs;

import android.content.Context;

import com.android.model.Model;
/**
 * 
 * 创建僵尸的工厂
 * @author Think
 *
 */
public class ZombModel extends Model{
	public static final int ZOMB_0=0;
	public static final int ZOMB_1=1;
	public static final int ZOMB_2=2;
	public static final int ZOMB_3=3;
	public static final int ZOMB_4=4;
	public static final int ZOMB_5=5;
	public static final int ZOMB_6=6;
	public static final int ZOMB_7=7;
	public static final int ZOMB_8=8;
	
	public static final int ZOMB_STATE_0=0;
	public static final int ZOMB_STATE_1=1;
	
	public ZombModel(int width, int height, Context context) {
		super(width, height, context);
	}
	//根据type创建具体僵尸并设置宽高
	public static ZombModel createZomb(Context context,int type){
		ZombModel zm =null;
		switch(type){
		case ZOMB_0:zm=new Zomb_0(40,60,context);break;
		case ZOMB_1:zm=new Zomb_1(40,80,context);break;
		case ZOMB_2:zm=new Zomb_2(40,80,context);break;
		case ZOMB_3:zm=new Zomb_3(50,90,context);break;
		case ZOMB_4:zm=new Zomb_4(40,80,context);break;
		case ZOMB_5:zm=new Zomb_5(60,90,context);break;
		case ZOMB_6:zm=new Zomb_6(40,80,context);break;
		case ZOMB_7:zm=new Zomb_7(50,80,context);break;
		case ZOMB_8:zm=new Zomb_8(40,80,context);break;
		}
		return zm;
	}
}
