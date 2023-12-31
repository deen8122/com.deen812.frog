package com.deen812.zombie.scene;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.deen812.hroommy.Constants;
import com.deen812.hroommy.R;
import com.deen812.hroommy.Resource;
import com.deen812.hroommy.main;
import com.deen812.hroommy.SceneManager.AllScenes;
//import com.deen812.zombie.R;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class ReviwesScene {

	/*
	 * 	 */
	private Scene mScene;
	private Constants mConst;
	private Resource mRes;
	private main game;
	private ChangeableText col_coin;
	private ChangeableText col_heart;
	private ChangeableText col_hummer;
	private ChangeableText col_snow;

	public ReviwesScene(final main game) {
		mRes = game.mRes;
		this.game = game;
		this.mScene = new Scene(0);
        this.mScene.attachChild(new Entity());
        this.mScene.setBackgroundEnabled(true);
        mConst = Constants.getInstance();
        
        
     //   mConst.COIN = 100;
        
        
        
        Sprite backgrund = new Sprite(0, 0, mRes.mBackgroundTextureRegion);
        Sprite backgrund2 = new Sprite(30, 20, mRes.board);
     //   Sprite backgrund2 = new Sprite(170, -40, mRes.game_name);
        
        backgrund2.setWidth(mConst.CAMERA_WIDTH-60);
        backgrund2.setHeight(mConst.CAMERA_HEIGHT-40);
        backgrund.setWidth(mConst.CAMERA_WIDTH);
        backgrund.setHeight(mConst.CAMERA_HEIGHT);
        backgrund.attachChild(backgrund2);
        this.mScene.setTouchAreaBindingEnabled(true);
        this.mScene.attachChild(backgrund);

        Sprite btn_exit2  = new Sprite(mConst.CAMERA_WIDTH - 80 , 30, mRes.btn_exit) {
      		 @Override
               public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                   if (pSceneTouchEvent.isActionDown()     ) {
                  	 this.setScale((float) 1.2);
                  	 mRes.m_pak.play();
                   }
                   if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
                  	 this.setScale((float) 1.0);
                  //	Process.killProcess(Process.thisMainScene.myPid());
                  	// game.sceneManager.setCurrentScene(AllScenes.SELECT_LEVEL);
                  //	game.finish();
                  	//mConst.main_activity.setRev();
                 	android.os.Process.killProcess(android.os.Process.myPid());

                   }
                   
                   return true;
               }; 
        };
        mScene.attachChild(btn_exit2);
        this.mScene.registerTouchArea(btn_exit2);
        
        
       /*
        * ������� ������ ������ ����
        * --------------------------------------------------------
        */
        
        String  str=(String)Constants.getInstance().main_activity.getResources().getString(R.string.rev_text);
        Text  t2 = new Text(0, 0, Resource.getInstance().mFont, str);
        t2.setScale(0.6f);
   	         t2.setColor(0.1f, 0.3f, 0.12f);
   	      t2.setPosition(game.mSmoothCamera.getCenterX()  - t2.getWidth()/2, 80 );
   		  mScene.attachChild(t2);
   		  

   		  
   		  // TITLE
   	    str=(String)Constants.getInstance().main_activity.getResources().getString(R.string.rev_title);
        t2 = new Text(0, 0, Resource.getInstance().mFont_Plok, str);
 	         t2.setScale(1.2f);
 	         t2.setColor(0.2f, 0.4f, 0.3f);
 	      t2.setPosition(game.mSmoothCamera.getCenterX()  - t2.getWidth()/2, 50 );
 		  mScene.attachChild(t2);
   		  
     
  		  
  		  
  		  
  		 Sprite button  = new Sprite(game.mSmoothCamera.getCenterX()  - mRes.h_btn_bye.getWidth()/2 , 350, mRes.h_btn_bye) {
      		 @Override
               public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                   if (pSceneTouchEvent.isActionDown()     ) {
                	   this.setScale(1.1f);
                  	 mRes.m_pak.play();
                   }
                   if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
                	   this.setScale(1.0f);
                	 //  mConst.main_activity.byeFullVersion();
                	  
                	   mConst.main_activity.setRev();
                	   mConst.main_activity.rateApp();
                  	// this.setScale((float) 2.0);
                  //	Process.killProcess(Process.thisMainScene.myPid());
                  	 //game.sceneManager.setCurrentScene(AllScenes.MENU);
                  //	game.finish();
                  	//android.os.Process.killProcess(android.os.Process.myPid());

                   }
                   
                   return true;
               }; 
        };
     
       // button.setScale(1.5f);
        mScene.attachChild(button);
        this.mScene.registerTouchArea(button);
        button.setWidth(400);
        
        button.setHeight(90);
        // BUTTOM
 	     str=(String)Constants.getInstance().main_activity.getResources().getString(R.string.rev_btn);
        t2 = new Text(0, 0, Resource.getInstance().mFont_Plok, str);
 	         t2.setScale(1.2f);
 	         t2.setColor(0f, 0.1f, 0.0f);
 	    //  t2.setPosition(game.mSmoothCamera.getCenterX()  - t2.getWidth()/2,  359 );
 	        t2.setPosition(60, 22);
 	     button.attachChild(t2);
 		 button.setPosition(game.mSmoothCamera.getCenterX()  -200, button.getY());
 		  
	}
	
	
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.mScene;
	}
	

}
