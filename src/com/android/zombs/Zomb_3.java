package com.android.zombs;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.main.R;

public class Zomb_3 extends ZombModel{

	public Zomb_3(int width, int height, Context context) {
		super(width, height, context);
		initDrawable();
	}
	public void initDrawable(){
		Drawable d0=createDrawable(R.drawable.zomb_3,112,150);
		addDrawable(ZOMB_STATE_0,d0);
		Drawable d1=createDrawable(R.drawable.zomb_3,112,150,8);
		addDrawable(ZOMB_STATE_1,d1);
	}

}
