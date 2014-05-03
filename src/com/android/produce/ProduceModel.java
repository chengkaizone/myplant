package com.android.produce;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.android.model.Model;

public class ProduceModel extends Model{
	public static final int P_0=0;
	public static final int P_1=1;
	public static final int P_2=2;
	public static final int P_3=3;
	
	public static final int STATE_0=0;
	public static final int STATE_1=1;
	public static final int STATE_2=2;
	public static Map<Object,Integer> map=new HashMap<Object,Integer>();
	public static Map<Object, Integer> getMap() {
		return map;
	}
	public static void setMap(Map<Object, Integer> map) {
		ProduceModel.map = map;
	}
	public ProduceModel(int width, int height, Context context) {
		super(width, height, context);
		// TODO Auto-generated constructor stub
	}
	public static ProduceModel createProduce(Context context,int type){
		ProduceModel pm=null;
		switch(type){
		case P_0:pm=new Sun(20,20,context);map.put(pm, 0);break;
		case P_1:pm=new Pea(10,10,context);map.put(pm, 1);break;
		}
		return pm;
	}
}
