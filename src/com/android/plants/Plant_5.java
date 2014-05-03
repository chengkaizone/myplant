package com.android.plants;

import com.android.main.R;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Plant_5 extends PlantModel{

	public Plant_5(int width, int height, Context context) {
		super(width, height, context);
		initDrawable();
	}
	public void initDrawable(){
		Drawable d0=createDrawable(R.drawable.plant_5,100,120);
		addDrawable(PLANT_STATE_0,d0);
		Drawable d1=createDrawable1(R.drawable.seedpackets,57,80,6);
		addDrawable(PLANT_STATE_1,d1);
		Drawable d2=createDrawable(R.drawable.plant_5,100,120,8);
		addDrawable(PLANT_STATE_2,d2);
		
	}
}
