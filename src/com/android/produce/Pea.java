package com.android.produce;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.main.R;

public class Pea extends ProduceModel{

	public Pea(int width, int height, Context context) {
		super(width, height, context);
		// TODO Auto-generated constructor stub
	}
	public void initDrawable(){
		Drawable d0=createDrawable(R.drawable.bullet_0,28,28);
		addDrawable(STATE_0,d0);
	}
}
