package com.android.plants;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.android.model.Model;
/**
 * 创建植物的工厂
 * @author Think
 *
 */
public class PlantModel extends Model{
	public static Map<Object,Integer> map=new HashMap<Object,Integer>();
	public static final int PLANT_0=0;//太阳花
	public static final int PLANT_1=1;
	public static final int PLANT_2=2;
	public static final int PLANT_3=3;
	public static final int PLANT_4=4;
	public static final int PLANT_5=5;
	public static final int PLANT_6=6;
	public static final int PLANT_7=7;
	public static final int PLANT_8=8;
	public static final int PLANT_9=9;
	
	public static final int PLANT_STATE_0=0;
	public static final int PLANT_STATE_1=1;
	public static final int PLANT_STATE_2=2;
	public PlantModel(int width, int height, Context context) {
		super(width, height, context);
	}
	//根据type创建具体植物,设置植物的宽高；
	public static PlantModel createPlant(Context context,int type){
		PlantModel pm=null;
		switch(type){
		case PLANT_0:pm=new Plant_0(30,40,context);map.put(pm,0);break;
		case PLANT_1:pm=new Plant_1(30,40,context);map.put(pm,1);break;
		case PLANT_2:pm=new Plant_2(30,40,context);map.put(pm,2);break;
		case PLANT_3:pm=new Plant_3(30,40,context);map.put(pm,3);break;
		case PLANT_4:pm=new Plant_4(30,40,context);map.put(pm,4);break;
		case PLANT_5:pm=new Plant_5(30,40,context);map.put(pm,5);break;
		case PLANT_6:pm=new Plant_6(30,40,context);map.put(pm,6);break;
		case PLANT_7:pm=new Plant_7(30,40,context);map.put(pm,7);break;
		case PLANT_8:pm=new Plant_8(30,40,context);map.put(pm,8);break;
		case PLANT_9:pm=new Plant_9(30,40,context);map.put(pm,9);break;
		}
		return pm;
	}

}
