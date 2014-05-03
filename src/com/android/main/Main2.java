package com.android.main;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.android.main.locationUtil.LocationUtil;
import com.android.model.Model;
import com.android.plants.PlantModel;
import com.android.produce.ProduceModel;
import com.android.zombs.ZombModel;
/**
 * 
 * 运行该游戏请用480*320分辨率的屏幕
 * @author Think
 * 大量创建对象会导致内存溢出,尽量避免
 * 迭代对象用迭代器不能并发修改,此时最好用循环,或将集合赋值给另一个集合(未尝试)
 */
public class Main2 extends Activity {
	private boolean flag=true;
	private GameView view;
	Random random=new Random();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
//        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        //设置全屏
        
        Window window=this.getWindow();
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        view=(GameView)findViewById(R.id.main_game);
        //生成卡片
        for(int i=0;i<10;i++){
        	Model card=PlantModel.createPlant(this,i);
        	card.setDrawable(view,PlantModel.PLANT_STATE_1);
        	card.setPosXY(63+30*i, 4);
        	view.addCard(card);
        }
    }
	public boolean onTouchEvent(MotionEvent event) {
		pullPlant(event);
		clickSun(event);
		if(flag){
			new InnerThread().start();
			new InnerThread2().start();
			new InnerThread3().start();
			flag=false;
		}
		return super.onTouchEvent(event);
	}
	private class InnerThread extends Thread{
    	public void run(){
    		try{
    			while(view.getModels().size()<=30){
    				Model model=ZombModel.createZomb(Main2.this,random.nextInt(9));
    				model.setPosXY(470,(int)(Math.random()*280));
    				model.setDrawable(view,ZombModel.ZOMB_STATE_1);
    				LocationUtil.location(model);
    				view.addModel(model);
    				view.draw();
    				Thread.sleep((int)(Math.random()*30000));
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    }
	
	//控制僵尸移动
	private class InnerThread2 extends Thread{
		public void run(){
			while(true){
			for(int i=0;i<view.getModels().size();i++){
				Model m=view.getModels().get(i);
				if(m instanceof ZombModel){
					if(m.getPosX()>=0){
						m.setPosX(m.getPosX()-5);
					}
				}
				view.draw();
			}
			//控制豆前进
			for(int i=0;i<view.getPeas().size();i++){
				ProduceModel pea=(ProduceModel)view.getPeas().get(i);
					if(pea.getPosX()<=480){
						pea.setPosX(pea.getPosX()+50);
					}else{
						view.getPeas().remove(pea);
					}
				view.draw();
			}
			try{
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
			}
		}
	}
	//植物生产产物线程
	private class InnerThread3 extends Thread{
//		int num=0;
    	public void run(){
    		
    		try{
    			while(true){
    				for(int i=0;i<view.getPlants().size();i++){
    					PlantModel plant=(PlantModel)view.getPlants().get(i);
    					//是太阳花就生产太阳
//    					如何知道太阳花的数量?
    					if(plant.map.get(plant)==0){
    						if(view.getSuns().size()<=getSunNum()){
    						Model produce=ProduceModel.createProduce(Main2.this,0);
    						produce.setPosXY(plant.getPosX()+12,plant.getPosY()+12);
    						produce.setDrawable(view,0);
    						view.addSun(produce);
    						
    						view.draw();
    						}
    					}
    					//是植物就生产豆
    					else if(plant.map.get(plant)==1||plant.map.get(plant)==2){
    						Model produce=ProduceModel.createProduce(Main2.this,1);
    						produce.setPosXY(plant.getPosX()+12,plant.getPosY()+8);
    						produce.setDrawable(view,0);
    						view.addPea(produce);
    						view.draw();
    					}
    				}
    				//创建太阳
//    				for(int i=0;i<view.getPlants().size();i++){
//    					PlantModel plant=(PlantModel)view.getPlants().get(i);
//    						if(num<=view.getSuns().size()){
//    						Model produce=ProduceModel.createProduce(Main2.this,0);
//    						produce.setPosXY(plant.getPosX()+12,plant.getPosY()+12);
//    						produce.setDrawable(view,0);
//    						view.addSun(produce);
//    						}
//    						view.draw();
//    				}
    			//没有太阳花也要出太阳  未实现	 
//    					Model produce=ProduceModel.createProduce(Main2.this,0);
//    					produce.setPosXY((int)Math.random()*280,(random.nextInt(4)+1)*7);
//    					produce.setDrawable(view,0);
//    					view.addProduce(produce);
//    					view.draw();
//    				
    				Thread.sleep(10000);
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    }
	//触控拖出植物
	private void pullPlant(MotionEvent event){
		if(flag)return;
		float x=event.getX();
		float y=event.getY();
		//按下事件
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			for(int i=0;i<view.getCards().size();i++){
				Model card=view.getCards().get(i);
				if(x>card.getPosX()&&x<card.getPosX()+card.getWidth()
						&&y>card.getPosY()&&y<card.getPosY()+card.getHeight()){
					Model plant=PlantModel.createPlant(this,i);
					plant.setDrawable(view, PlantModel.PLANT_STATE_0);
					plant.setPosXY((int)x-card.getWidth()/2,(int)y-card.getHeight()/2);
					view.setPlant(plant);
					break;
				}
			}
		}
		//拖曳事件
		if(view.getPlant()!=null){
			view.getPlant().setPosXY((int)x-view.getPlant().getWidth()/2,(int)y-view.getPlant().getHeight()/2);
		}
		//松开事件
		if(event.getAction()==MotionEvent.ACTION_UP){
			if(view.getPlant()!=null){
				Model plant=view.getPlant();
				plant.setDrawable(view, PlantModel.PLANT_STATE_2);
				plant.setPosXY((int)x-plant.getWidth()/2,(int)y-plant.getHeight()/2);
				LocationUtil.setLocation(plant);
				view.addPlant(plant);
				view.setPlant(null);
			}
		}
	}
	//销毁太阳点击事件
	private void clickSun(MotionEvent event){
		float x=event.getX();
		float y=event.getY();
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			for(int i=0;i<view.getSuns().size();i++){
				ProduceModel produce=(ProduceModel)view.getSuns().get(i);
				if(produce.getMap().get(produce)==0){
				if(x>produce.getPosX()&&x<produce.getPosX()+produce.getWidth()
						&&y>produce.getPosY()&&y<produce.getPosY()+produce.getHeight()){
					view.getSuns().remove(produce);
					break;
				}
				}
			}
		}
	}
	private int getSunNum(){
		int num=0;
		for(int i=0;i<view.getPlants().size();i++){
			PlantModel plant=(PlantModel)view.getPlants().get(i);
			if(plant.map.get(plant)==0){
				num++;
			}
		}
		return num;
	}
	public void finish() {
		
		super.finish();
	}
	protected void onDestroy() {
		
		super.onDestroy();
	}
	
}