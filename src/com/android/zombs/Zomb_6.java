package com.android.zombs;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.main.R;

public class Zomb_6 extends ZombModel{
	public Zomb_6(int width, int height, Context context) {
		super(width, height, context);
		initDrawable();
	}
	public void initDrawable(){
		Drawable d0=createDrawable(R.drawable.zomb_6,100,130);
		addDrawable(ZOMB_STATE_0,d0);
		Drawable d1=createDrawable(R.drawable.zomb_6,100,130,5);
		addDrawable(ZOMB_STATE_1,d1);
	}

}
