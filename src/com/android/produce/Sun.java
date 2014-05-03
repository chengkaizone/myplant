package com.android.produce;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.android.main.R;

public class Sun extends ProduceModel{

	public Sun(int width, int height, Context context) {
		super(width, height, context);
		initDrawable();
	}
	public void initDrawable() {
		Drawable d0=createDrawable(R.drawable.sun,100,100);
		addDrawable(STATE_0,d0);
	}
}
