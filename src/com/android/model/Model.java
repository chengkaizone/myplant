package com.android.model;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class Model {
	private int width;
	private int height;
	private int posX;
	private int posY;
	
	private Context context;
	private Map<Integer,Drawable> drawables=new HashMap<Integer,Drawable>();
	private Drawable currentDrawable;
	public Model(int width, int height, Context context) {
		super();
		this.width = width;
		this.height = height;
		this.context = context;
		initDrawable();
	}
	public void draw(Canvas canvas){
		currentDrawable.setBounds(posX,posY,posX+width,posY+height);
		currentDrawable.draw(canvas);
	}
	public void addDrawable(int type,Drawable drawable){
		drawables.put(type,drawable);
	}
	public void setDrawable(View view,int id){
		if(currentDrawable instanceof AnimationDrawable){
			((AnimationDrawable) currentDrawable).stop();
		}
		currentDrawable=drawables.get(id);
		currentDrawable.setCallback(view);
		if(currentDrawable!=null&&currentDrawable instanceof AnimationDrawable){
		AnimationDrawable anim=(AnimationDrawable)currentDrawable;
		anim.start();
		}
	}
	//创建静态角色
	public Drawable createDrawable(int resId,int width,int height){
		Bitmap src=BitmapFactory.decodeResource(context.getResources(),resId);
		Bitmap des=Bitmap.createBitmap(src,0,0,width,height);
		return new BitmapDrawable(des);
	}
	//创建卡片的角色
	public Drawable createDrawable1(int resId,int width,int height,int cardNo){
		Bitmap src=BitmapFactory.decodeResource(context.getResources(), resId);
		Bitmap des=Bitmap.createBitmap(src,57*(cardNo-1),0,width,height);
		return new BitmapDrawable(des);
	}
	//创建动态角色
	public Drawable createDrawable(int resId,int width,int height,int count){
		Bitmap src=BitmapFactory.decodeResource(context.getResources(),resId);
		AnimationDrawable anim=new AnimationDrawable();
		anim.setOneShot(false);
		for(int i=0;i<count;i++){
			Bitmap des=Bitmap.createBitmap(src,width*i,0,width,height);
			anim.addFrame(new BitmapDrawable(des),200);
		}
		return anim;
		
	}
	public Drawable getCurrentDrawable() {
		return currentDrawable;
	}

	public void setCurrentDrawable(Drawable currentDrawable) {
		this.currentDrawable = currentDrawable;
	}

	public void initDrawable(){}
	public void setPosXY(int posX,int posY){
		this.posX=posX;
		this.posY=posY;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
