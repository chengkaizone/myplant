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
 * ���и���Ϸ����480*320�ֱ��ʵ���Ļ
 * @author Think
 * ������������ᵼ���ڴ����,��������
 * ���������õ��������ܲ����޸�,��ʱ�����ѭ��,�򽫼��ϸ�ֵ����һ������(δ����)
 */
public class Main2 extends Activity {
	private boolean flag=true;
	private GameView view;
	Random random=new Random();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //�����ޱ���
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
//        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        //����ȫ��
        
        Window window=this.getWindow();
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        view=(GameView)findViewById(R.id.main_game);
        //���ɿ�Ƭ
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
	
	//���ƽ�ʬ�ƶ�
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
			//���ƶ�ǰ��
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
	//ֲ�����������߳�
	private class InnerThread3 extends Thread{
//		int num=0;
    	public void run(){
    		
    		try{
    			while(true){
    				for(int i=0;i<view.getPlants().size();i++){
    					PlantModel plant=(PlantModel)view.getPlants().get(i);
    					//��̫����������̫��
//    					���֪��̫����������?
    					if(plant.map.get(plant)==0){
    						if(view.getSuns().size()<=getSunNum()){
    						Model produce=ProduceModel.createProduce(Main2.this,0);
    						produce.setPosXY(plant.getPosX()+12,plant.getPosY()+12);
    						produce.setDrawable(view,0);
    						view.addSun(produce);
    						
    						view.draw();
    						}
    					}
    					//��ֲ���������
    					else if(plant.map.get(plant)==1||plant.map.get(plant)==2){
    						Model produce=ProduceModel.createProduce(Main2.this,1);
    						produce.setPosXY(plant.getPosX()+12,plant.getPosY()+8);
    						produce.setDrawable(view,0);
    						view.addPea(produce);
    						view.draw();
    					}
    				}
    				//����̫��
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
    			//û��̫����ҲҪ��̫��  δʵ��	 
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
	//�����ϳ�ֲ��
	private void pullPlant(MotionEvent event){
		if(flag)return;
		float x=event.getX();
		float y=event.getY();
		//�����¼�
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
		//��ҷ�¼�
		if(view.getPlant()!=null){
			view.getPlant().setPosXY((int)x-view.getPlant().getWidth()/2,(int)y-view.getPlant().getHeight()/2);
		}
		//�ɿ��¼�
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
	//����̫������¼�
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