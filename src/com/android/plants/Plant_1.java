package com.android.plants;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.main.R;

public class Plant_1 extends PlantModel{

	public Plant_1(int width, int height, Context context) {
		super(width, height, context);
		
	}
	public void initDrawable(){
		Drawable d0=createDrawable(R.drawable.plant_1,80,80);
		addDrawable(PLANT_STATE_0,d0);
		Drawable d1=createDrawable1(R.drawable.seedpackets,57,80,2);
		addDrawable(PLANT_STATE_1,d1);
		Drawable d2=createDrawable(R.drawable.plant_1,80,80,5);
		addDrawable(PLANT_STATE_2,d2);
		
	}
}
