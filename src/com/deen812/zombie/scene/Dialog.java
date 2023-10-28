package com.deen812.zombie.scene;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.deen812.hroommy.Constants;
import com.deen812.hroommy.R;
import com.deen812.hroommy.Resource;
import com.deen812.hroommy.SceneManager.AllScenes;




public class Dialog {
  private static final int BTN_EXIT_GAMELOSE = 1;
private static Dialog instance;

  
  
//---------------------------------------------------------------------
  public static Dialog getInstance() {if(instance == null) {instance = new Dialog();}return instance;}






private Sprite paused_content;
private Sprite btn_replay;
private Sprite btn_exit;
private Rectangle bgRect;
private Sprite btn_menu;
private int BTN_EXIT_CMD = 0;
private Text t;
private Text t2;
public boolean btn_paused_on;
private Text textPause;
private AnimatedSprite star1;
private AnimatedSprite star2;
private AnimatedSprite star3;
private Sprite btn_next2;


  /*
   * CONSTRUCTOR
   */
private Dialog() {
	    bgRect = new Rectangle(0,0, Constants.getInstance().CAMERA_WIDTH,Constants.getInstance().CAMERA_HEIGHT);
		bgRect.setColor(0, 0, 0);
		bgRect.setAlpha(0.8f);
		Vector2 c = this.GetCenter(400, 
				                   220);
		  
		paused_content = new Sprite(c.x, c.y ,Resource.getInstance().board );
		paused_content.setWidth(400);
		paused_content.setHeight(220);
		btn_replay = new Sprite(50 ,80, Resource.getInstance().btn_reload){
				 @Override
		         public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		             if (pSceneTouchEvent.isActionDown()     ) {
		            	 this.setScale(2.0f);
		            	 
		             }
		             if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		            	 this.setScale(1.5f);
		            	 Constants.getInstance().gameScene.GameRestart();
		             }
		             
		             return true;
		         };
			};
			btn_replay.setScale(1.5f);
			//������ ����
			 btn_menu = new Sprite(300 ,80,Resource.getInstance().btn_menu){
				 @Override
		         public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {
					
		             if (pSceneTouchEvent.isActionDown()     ) {
		             	 this.setScale(2.0f);
		             	 
		             
		             }
		             if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		            	 this.setScale(1.5f);
		            	 Constants.getInstance().gameScene.CloseScene();
		            //	 hide_paused_content();
		            //	game.closeScene();
		            	 
		             }
		             
		             return true;
		         };
			};
			btn_menu.setScale(1.5f);	
			
			 //---------------------------------------------
			 btn_exit = new Sprite(350 ,-20, Resource.getInstance().btn_exit){
				 @Override
		        public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {
					
		            if (pSceneTouchEvent.isActionDown()     ) {clickedBtnExit(); }
		            if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		            	
		            	}        
		            return true;
		        };
			};
			//btn_menu.setScale(1.5f);
			  String str=(String)Constants.getInstance().main_activity.getResources().getString(R.string.youwin);	    
			  t = new Text(0, 0, Resource.getInstance().mFont_Plok, str);
			
			   str=(String)Constants.getInstance().main_activity.getResources().getString(R.string.loose);
			    
			    t2 = new Text(0, 0, Resource.getInstance().mFont_Plok, str);
				t2.setColor(0, (float)128/225, 0);
			
				
				  str=(String)Constants.getInstance().main_activity.getResources().getString(R.string.pause);				    
				  textPause = new Text(0, 0, Resource.getInstance().mFont_Plok, str);
				  textPause.setColor(0, (float)128/225, 0);
				  if( (textPause.getWidth()+60) > paused_content.getWidth()) {paused_content.setWidth(textPause.getWidth()+60);}
				  textPause.setPosition(paused_content.getWidth()/2 -textPause.getWidth()/2, 20);
				  
				  
				  star1 = new AnimatedSprite(60,80, Resource.getInstance().star_lc.clone());
				  star2 = new AnimatedSprite(170,70, Resource.getInstance().star_lc.clone());
				  star2.setRotation(10);
				  star3 = new AnimatedSprite(280,80, Resource.getInstance().star_lc.clone());
				  star3.setRotation(30);
				  
				//BTN_NEXT
					    btn_next2  = new Sprite(32,180, Resource.getInstance().btn_next) {
					  		 @Override
					           public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
					               if (pSceneTouchEvent.isActionDown()     ) {
					              	 this.setScale((float) 1.6);
					               }
					               if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
					              	 this.setScale((float) 1.5);
					              	Constants.getInstance().gameScene.NextLevel();

					               }
					               
					               return true;
					           }; 
					    };
					    btn_next2.setScale(1.5f);
					    
	//-------------------------------------------------------------------------			
				
}

/*
 * -----------------------------------------------------------------------
 */



public void GamerLose() {
	 ShowBg();
	// Constants.getInstance().CURRENT_SCENE.setIgnoreUpdate(true);
	 Constants.getInstance().hud.clearTouchAreas();
	 btn_paused_on = true;
	Constants.getInstance().hud.attachChild(paused_content);
	//paused_content.setIgnoreUpdate(false);
	//paused_content.attachChild(btn_exit);
	//Constants.getInstance().hud.registerTouchArea(btn_exit);
	
	
	t2.setPosition(paused_content.getWidth()/2 -t2.getWidth()/2, 10);
	this.paused_content.attachChild(t2);
	
	 paused_content.attachChild(t2);
     paused_content.attachChild(btn_replay);
     Constants.getInstance().hud.registerTouchArea(btn_replay);
     btn_menu.setPosition(btn_menu.getX(), 80);
   //  Constants.getInstance().gameScene.life_ctrl.clearRect();
     btn_replay.setPosition(150, btn_replay.getY());
	 paused_content.attachChild(btn_menu);
	 Constants.getInstance().hud.registerTouchArea(btn_menu);
	//BTN_EXIT_CMD = BTN_EXIT_GAMELOSE ;
	
}


public void GamerWin() {
	 ShowBg();
	 Constants.getInstance().F_PLAYER_WIN = false;
	 btn_paused_on = true;
	 Constants.getInstance().hud.clearTouchAreas();
	 Constants.getInstance().hud.attachChild(paused_content);
	 Constants.getInstance().gameScene.PlayerWin();

	 
	  
		if( (t.getWidth()+60) > paused_content.getWidth()) {paused_content.setWidth(t.getWidth()+60);}
		t.setColor(0, (float)128/225, 0);
		t.setPosition(paused_content.getWidth()/2 -t.getWidth()/2, 20);
		this.paused_content.attachChild(t);
		 paused_content.attachChild(btn_menu);
		
		 
		 btn_menu.setPosition(btn_menu.getX(), 180);
		 //Constants.getInstance().hud.clearTouchAreas();
		 Constants.getInstance().hud.registerTouchArea(btn_menu);
		 
		 
		 
		// btn_replay.setPosition(170, 180);
		// paused_content.attachChild(btn_replay);
	  //    Constants.getInstance().hud.registerTouchArea(btn_replay); 
		   
		    paused_content.attachChild(btn_next2);
		    Constants.getInstance().hud.registerTouchArea(btn_next2);
		    
		    
		    
if(Constants.getInstance().COL_LEVEL_STAR == 0) {
	star1.setCurrentTileIndex(0);
	star2.setCurrentTileIndex(0);
	star3.setCurrentTileIndex(0);
}
if(Constants.getInstance().COL_LEVEL_STAR == 1) {
	star1.setCurrentTileIndex(0);
	star2.setCurrentTileIndex(0);
	star3.setCurrentTileIndex(1);
}
if(Constants.getInstance().COL_LEVEL_STAR == 2) {
	star1.setCurrentTileIndex(0);
	star2.setCurrentTileIndex(1);
	star3.setCurrentTileIndex(1);
}
		    star1.setScale(12);
		    star2.setScale(12);
		    star3.setScale(12);
		    paused_content.attachChild(star1); 
		    paused_content.attachChild(star2);  
		    paused_content.attachChild(star3); 
		    
		    Constants.getInstance().mEngine.registerUpdateHandler(new IUpdateHandler() {  
					
					private float scale = 8;
					@Override  
		   public void onUpdate(float arg0) { 
						
						if( scale <=1.5) {
						
							Constants.getInstance().mEngine.unregisterUpdateHandler(this);
						}
						else {
							scale  -=0.6f;
							star1.setScale(scale);
							star2.setScale(scale);
							star3.setScale(scale);
						}
				         }  
		 @Override  
		  public void reset() {   }  
		});  
		  
		
}


public void init() {
	
}
public void ShowPaused() {
	btn_paused_on = true;
	  ShowBg();
	  BTN_EXIT_CMD = 2;
	  Constants.getInstance().CURRENT_SCENE.setIgnoreUpdate(true);
	  
	  Constants.getInstance().hud.attachChild(paused_content);
      paused_content.attachChild(btn_replay);
      Constants.getInstance().hud.registerTouchArea(btn_replay);
      paused_content.attachChild(textPause);
	
      btn_replay.setPosition(50, 80);
	 paused_content.attachChild(btn_menu);
	 btn_menu.setPosition(btn_menu.getX(), 80);
	 Constants.getInstance().hud.registerTouchArea(btn_menu);
	
	
   
	paused_content.attachChild(btn_exit);
	Constants.getInstance().hud.registerTouchArea(btn_exit);
	
}




public void clickedBtnExit() {
	Constants.getInstance().hud.unregisterTouchArea(btn_exit);
	Constants.getInstance().hud.unregisterTouchArea(btn_replay);
	Constants.getInstance().hud.unregisterTouchArea(btn_menu);
	Log.v("DIALOG","clickedBtnExit");
	//HideBg();
	btn_paused_on = false;
	 Constants.getInstance().CURRENT_SCENE.setIgnoreUpdate(false);
	 
    switch(BTN_EXIT_CMD) {
    case BTN_EXIT_GAMELOSE:
    	
    	break;
    default:
    	Constants.getInstance().CURRENT_SCENE.registerUpdateHandler(new IUpdateHandler() {  
    		@Override  
             public void onUpdate(float arg0) { 
                        	  Constants.getInstance().CURRENT_SCENE.unregisterUpdateHandler(this); 
                        	  Constants.getInstance().CURRENT_SCENE.setIgnoreUpdate(false);
                        	  paused_content.detachChild(textPause);
                        		paused_content.detachChildren();
                        		paused_content.clearEntityModifiers();
                        		paused_content.clearUpdateHandlers();
                        		Constants.getInstance().hud.detachChild(bgRect);
                        		Constants.getInstance().hud.detachChild(paused_content);
    	     }  

      @Override  
       public void reset() {   }  }); 
    	
    	break;
    }
 
	
	
}



public void ShowBg() {
	
	/*{
	
		

		@Override
        public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {
			 
            if (pSceneTouchEvent.isActionDown()  ) {
            	Constants.getInstance().gameGUI.btn_pause_clicked = false;
            	Constants.getInstance().hud.detachChild(this);
            	Constants.getInstance().hud.unregisterTouchArea(this);
           	    Constants.getInstance().CURRENT_SCENE.setIgnoreUpdate(false);
            	
 }
            if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel() || pSceneTouchEvent.isActionOutside()) {

            }
            return true;
        };
	};
	*/
	 Constants.getInstance().hud.attachChild(bgRect);
	 bgRect.setAlpha(0);
	Constants.getInstance().mEngine.registerUpdateHandler(new IUpdateHandler() {  
		@Override  
         public void onUpdate(float arg0) { 
                      
                      if(bgRect.getAlpha() > 0.6) {
                    	  Constants.getInstance().mEngine.unregisterUpdateHandler(this);  
                      }

                      bgRect.setAlpha( bgRect.getAlpha() + 0.05f);
	     }  

  @Override  
   public void reset() {   }  });  
	
	
	 
	 //Constants.getInstance().CURRENT_SCENE.setIgnoreUpdate(true);
	// Constants.getInstance().hud.registerTouchArea(bgRect);
	
}

public void HideBg() {
	 Constants.getInstance().hud.detachChild(bgRect);
	// Constants.getInstance().gameGUI.btn_pause_clicked = false;
}

public void HideBgSlow() {
	//Constants.getInstance().gameGUI.btn_pause_clicked = false;
	
	//Constants.getInstance().hud.unregisterTouchArea(bgRect);
	Constants.getInstance().CURRENT_SCENE.registerUpdateHandler(new IUpdateHandler() {  
		@Override  
         public void onUpdate(float arg0) { 
                      
                      if(bgRect.getAlpha() == 0) {
                    	  Constants.getInstance().hud.detachChild(bgRect);
                    	  Constants.getInstance().CURRENT_SCENE.unregisterUpdateHandler(this);  
                      }
                      else
                      bgRect.setAlpha( bgRect.getAlpha() - 0.1f);
	     }  

  @Override  
   public void reset() {   }  });  
	
}


public void ShowEndWindow() {
	
	
}


private Vector2 GetCenter(float width , float height) {
	
	float x = Constants.getInstance().CAMERA_WIDTH/2 - width/2;
	float y = Constants.getInstance().CAMERA_HEIGHT/2-height/2;
	return new Vector2(x,y);
}






public void HideAll() {
	HideBg();
	paused_content.detachChildren();
	paused_content.clearEntityModifiers();
	paused_content.clearUpdateHandlers();
	paused_content.detachChild(textPause);
	Constants.getInstance().hud.detachChild(paused_content);
	Constants.getInstance().hud.unregisterTouchArea(btn_menu);
	Constants.getInstance().hud.unregisterTouchArea(this.btn_replay);
	Constants.getInstance().hud.unregisterTouchArea(this.btn_exit);
	Constants.getInstance().hud.unregisterTouchArea(this.btn_next2);
	btn_paused_on = false;
	// TODO Auto-generated method stub
	
}






}//========================================================================
