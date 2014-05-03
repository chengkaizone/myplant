package com.android.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.android.model.Model;
import com.android.plants.PlantModel;
/**
 * 
 * 角色舞台stage
 * @author Think
 *
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	private boolean flag=false;
	private boolean flag1=true;
	private List<Model> zombs=new ArrayList<Model>();
	private List<Model> plants=new ArrayList<Model>();
	private List<Model> cards=new ArrayList<Model>();
	private List<Model> suns=new ArrayList<Model>();
	private List<Model> peas=new ArrayList<Model>();
	
	public List<Model> getSuns() {
		return suns;
	}
	public void setSuns(List<Model> suns) {
		this.suns = suns;
	}
	public List<Model> getPeas() {
		return peas;
	}
	public void setPeas(List<Model> peas) {
		this.peas = peas;
	}
	private Model plant;
	public Model getPlant() {
		return plant;
	}
	public void setPlant(Model plant) {
		this.plant = plant;
	}
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
	}
	public void addModel(Model zomb){
		zombs.add(zomb);
	}
	public void addSun(Model sun){
		suns.add(sun);
	}
	public void addPea(Model pea){
		peas.add(pea);
	}
	public void addPlant(Model plant){
		plants.add(plant);
	}
	public void addCard(Model card){
		cards.add(card);
	}
	
	public void draw(){
		if(flag){
			SurfaceHolder holder=getHolder();
			Canvas canvas=holder.lockCanvas();
			if(canvas!=null){
			Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.bk1);
			Rect src=new Rect(0,0,677,320);
			Rect des=new Rect(0,0,480,320);
			canvas.drawBitmap(bit,src,des,new Paint());
			drawAll(canvas);
			if(flag1){
			for(int i=0;i<cards.size();i++){
				cards.get(i).draw(canvas);
			}
			for(int i=0;i<plants.size();i++){
				plants.get(i).draw(canvas);
			}
			for(int i=0;i<zombs.size();i++){
				zombs.get(i).draw(canvas);
			}
			for(int i=0;i<suns.size();i++){
				suns.get(i).draw(canvas);
			}
			for(int i=0;i<peas.size();i++){
				peas.get(i).draw(canvas);
			}
			if(plant!=null){
				plant.draw(canvas);
			}
			}
//多线程并发修改时不能用迭代器迭代
//但可以用另一个集合包装
//			for(Model m:models){
//				m.draw(canvas);
//			}
			holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	public List<Model> getModels() {
		return zombs;
	}
	public void setModels(List<Model> zombs) {
		this.zombs = zombs;
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}
	public void surfaceCreated(SurfaceHolder holder) {
		flag=true;
		drawInit();
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	protected boolean verifyDrawable(Drawable who) {
		return who!=null;
	}
	//创建SurfaceView时回调初始画面
	private void drawInit(){
		SurfaceHolder holder=getHolder();
		Canvas canvas=holder.lockCanvas();
		Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.cover);
		Rect src=new Rect(0,0,800,533);
		int screenWidth=this.getWidth();
		int screenHeight=this.getHeight();
		Rect des=new Rect(0,0,screenWidth,screenHeight);
		canvas.drawBitmap(bit,src,des,null);
		holder.unlockCanvasAndPost(canvas);
	}
	public List<Model> getPlants() {
		return plants;
	}
	public void setPlants(List<Model> plants) {
		this.plants = plants;
	}
	public List<Model> getCards() {
		return cards;
	}
	public void setCards(List<Model> cards) {
		this.cards = cards;
	}
	private void drawAll(Canvas canvas){
		drawSun(canvas);
		drawUtil(canvas);
		drawTool(canvas);
		for(int i=0;i<zombs.size();i++){
			if(zombs.get(i).getPosX()<=0){
				drawOver(canvas);
				drawMenu(canvas);
				drawQuit(canvas);
				flag1=false;
			}
		}
	}
	//画工具框
	private void drawUtil(Canvas canvas){
		Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.seedbank);
		Rect src=new Rect(0,0,375,87);
		Rect des=new Rect(60,0,370,50);
		canvas.drawBitmap(bit,src,des,new Paint());
	}
	//画太阳
	private void drawSun(Canvas canvas){
		Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.sunbank);
		Rect src=new Rect(0,0,78,87);
		Rect des=new Rect(10,0,60,50);
		canvas.drawBitmap(bit,src,des,new Paint());
	}
	//
	private void drawMenu(Canvas canvas){
		Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.menu_bn);
		Rect src=new Rect(0,0,111,29);
		Rect des=new Rect(0,295,60,25);
		canvas.drawBitmap(bit,src,des,new Paint());
	}
	//
	private void drawQuit(Canvas canvas){
		Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.menu_bn_quit);
		Rect src=new Rect(0,0,111,29);
		Rect des=new Rect(420,295,60,25);
		canvas.drawBitmap(bit,src,des,new Paint());
	}
	//画锄子
	private void drawTool(Canvas canvas){
		Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.shovel_bank);
		Rect src=new Rect(0,0,70,72);
		Rect des=new Rect(430,0,50,50);
		canvas.drawBitmap(bit,src,des,new Paint());
	}
	//
	private void drawOver(Canvas canvas){
		Bitmap bit=BitmapFactory.decodeResource(getResources(), R.drawable.die);
		Rect src=new Rect(0,0,480,320);
		Rect des=new Rect(60,10,400,300);
		canvas.drawBitmap(bit,src,des,new Paint());
	}
}
