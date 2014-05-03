package com.android.plants;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.main.R;

public class Plant_4 extends PlantModel{

	public Plant_4(int width, int height, Context context) {
		super(width, height, context);
		initDrawable();
	}
	public void initDrawable(){
		Drawable d0=createDrawable(R.drawable.plant_4,90,90);
		addDrawable(PLANT_STATE_0,d0);
		Drawable d1=createDrawable1(R.drawable.seedpackets,57,80,5);
		addDrawable(PLANT_STATE_1,d1);
		Drawable d2=createDrawable(R.drawable.plant_4,90,90,8);
		addDrawable(PLANT_STATE_2,d2);
		
	}
}
