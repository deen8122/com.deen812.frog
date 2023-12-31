package com.deen812.zombie.scene;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.deen812.hroommy.Constants;
import com.deen812.hroommy.Resource;
import com.deen812.hroommy.main;
import com.deen812.hroommy.SceneManager.AllScenes;
import com.deen812.hroommy.R;


import android.content.Intent;
import android.text.Spanned;

public class HelpScene {

	/*
	 * 	 */
	private Scene mScene;
	private Constants mConst;
	private Resource mRes;
	private main game;

	public HelpScene(main game) {
		mRes = game.mRes;
		this.game = game;
		this.mScene = new Scene(0);
        this.mScene.attachChild(new Entity());
        this.mScene.setBackgroundEnabled(true);
        mConst = Constants.getInstance();
        
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
        onLoadComplete();
        
        
      
	}
	
	public void onLoadComplete() {
		
		// TextView tvBottom = (TextView) findViewById(R.id.tvBottom);
		 //String dtr = findViewById(R.id.hello);
		//R.string.hello;
	//	this.getText(R.string.hello);
	//	this.getResources().getString(R.string.app_name)
		
		
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
               	 game.sceneManager.setCurrentScene(AllScenes.MENU);
               //	game.finish();
           //    	android.os.Process.killProcess(android.os.Process.myPid());

                }
                
                return true;
            }; 
     };
     mScene.attachChild(btn_exit2);
     this.mScene.registerTouchArea(btn_exit2);
     
	
   //  Sprite btn_exit21  = new Sprite(100  , 80, mRes.how_play);
   //  btn_exit21.setScale(1.4f);
   //  mScene.attachChild(btn_exit21);
     
     String str=(String)game.getResources().getString(R.string.how_play_title);            
     Text t = new Text(230, 50, mRes.mFont_Plok, str);
     t.setColor(0.1f, 0.3f, 0.12f);
 	 this.mScene.attachChild(t);
 	 
 	 str=(String)game.getResources().getString(R.string.how_play_text);            
     t = new Text(-50, 95, mRes.mFont, str);
     t.setColor(0.1f, 0.3f, 0.12f);
     t.setScale(0.7f);
	 this.mScene.attachChild(t);
	
	 

     
			
		}

	private String findViewById(int text1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.mScene;
	}
	

}
