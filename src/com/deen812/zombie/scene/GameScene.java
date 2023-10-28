package com.deen812.zombie.scene;

import java.io.IOException;
import java.util.Iterator;

import objects.BoxObject;
import objects.BoxObject.ObjectType;
import objects.Boxes;
import objects.Boxes.BoxType;
import objects.Fly;
import objects.Hrummy;
import objects.Platform;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.AutoParallaxBackground;
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.sprite.Sprite;

import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.level.LevelLoader;
import org.anddev.andengine.level.LevelLoader.IEntityLoader;
import org.anddev.andengine.level.util.constants.LevelConstants;


import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.SAXUtils;
import org.xml.sax.Attributes;




import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import com.deen812.hroommy.Constants;
import com.deen812.hroommy.MapManager;
import com.deen812.hroommy.Resource;
import com.deen812.hroommy.main;
import com.deen812.hroommy.MapManager.LevelDef;

import com.deen812.hroommy.SceneManager.AllScenes;
import com.deen812.hroommy.R;



import com.deen812.zombie.object.LifeCtrl;


import android.hardware.SensorManager;
import android.util.Log;



public class GameScene implements IOnSceneTouchListener {

	/*
	 * 	 */
	public Scene mScene;
	private Constants mConst;
	private Resource mRes;
	private main game;
	private PhysicsWorld mPhysicsWorld;

	private String TAG="GameScene";

	public LifeCtrl life_ctrl;

	//private LevelDef ldef;
	//private MapManager mapmanager;
	private Text t2;
	private int CMD_CLOSE_SCENE;
	private boolean f_hook_play = true;
	private Hrummy mHrummy;
	private HUD hud;
	private boolean f_player_win;
    int STAR_COL = 0;
	private int STAR_COL_CURENT = 0;
	private boolean F_FINISH_ENABLE;
	private int  GAME_TYPE  = 0 ;
	private int gameTime;
	private ChangeableText timerText;
	public GameScene(main game) {
		Log.v(TAG,"GameScene()");
		mRes = game.mRes;
		this.game = game;
		this.mScene = new Scene(0);
        this.mScene.attachChild(new Entity());
        this.mScene.setBackgroundEnabled(true);
        mConst = Constants.getInstance();       
       
        this.mScene.setTouchAreaBindingEnabled(true);
 
        this.mPhysicsWorld = new PhysicsWorld(new Vector2(0, 10), false);
        mPhysicsWorld.setContactListener(createContactListener());
        mConst.mPhysicsWorld  = mPhysicsWorld;
        Dialog.getInstance();

	}

	
	
	/*
	 * START GAME
	 */
public void start_game() {
	     Log.v(TAG,"start_game()");
	     game.f_man_die = false;
	     f_player_win = false; 

	         if(mConst.SELECTED_MAP > mConst.MAX_COL_MAP) {
	    	/*
	    	 * Эсли это последний уровень
	    	 */
	    	   mConst.SELECTED_MAP = 1;
	    	
	         }
	         //----

	    Sprite  backgrund = new Sprite(0, 0, mRes.mBackgroundTextureRegion);
	    this.mScene.setOnSceneTouchListener(this);
	    this.mScene.setTouchAreaBindingEnabled(true);
	    backgrund.setZIndex(0);
	    backgrund.setWidth(mConst.CAMERA_WIDTH);
	    backgrund.setHeight(mConst.CAMERA_HEIGHT);
        this.mScene.registerUpdateHandler(this.mPhysicsWorld);
          mConst.gameScene = this;
          mConst.CURRENT_SCENE = mScene;
          mScene.setIgnoreUpdate(false);
         hud = new HUD();
         mConst.hud =hud;
        
         STAR_COL = 0;
         STAR_COL_CURENT = 0;
         F_FINISH_ENABLE = false;
         game.mSmoothCamera.setHUD(hud);
       //BTN_PAUSE
        
        
         Sprite	 btn_pause = new Sprite(mConst.CAMERA_WIDTH -game.mRes.btn_menu.getWidth()-10  ,10, game.mRes.btn_pause){
		

			@Override
           public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {
				 
               if (pSceneTouchEvent.isActionDown()  && Dialog.getInstance().btn_paused_on==false ) {
               	 this.setScale(1.1f);
               	 if(Constants.getInstance().F_PLAYER_WIN == false){
               	 Dialog.getInstance().ShowPaused();
               
               	 }
               	
      
               }
               if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel() || pSceneTouchEvent.isActionOutside()) {
              	 this.setScale(1.0f);
              	// man.key_up();
               }
               return true;
           };
		};
	
		
		hud.attachChild(btn_pause);
		hud.registerTouchArea(btn_pause);
		
		
		
       
        init_level(); 
        mHrummy =  new Hrummy(10,getHeight(70) );
        this.game.mSmoothCamera.setCenterDirect(mConst.CAMERA_WIDTH/2,getHeight(mConst.CAMERA_HEIGHT/2));
    
   

        mRes.wall1.setWidth((int) mConst.WORLD_WIDTH);
        Sprite spr_ground = new Sprite(0,mConst.WORLD_HEIGHT-32, mRes.wall1.clone());
    // final Shape ground = new Rectangle(0, mConst.WORLD_HEIGHT-32, mConst.WORLD_WIDTH,  250);
     final Shape roof = new Rectangle(0, 0, mConst.WORLD_WIDTH, 10);
     final Shape left = new Rectangle(0, 0, 0, mConst.WORLD_HEIGHT);
     final Shape right = new Rectangle(mConst.WORLD_WIDTH, 0, 0, mConst.WORLD_HEIGHT);
     
     final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(1, 0.1f,0.1f);
     wallFixtureDef.filter.categoryBits = 0x02;
     wallFixtureDef.filter.maskBits = -1;
     PhysicsFactory.createBoxBody(this.mPhysicsWorld, spr_ground, BodyType.StaticBody, wallFixtureDef).setUserData("ground");
     PhysicsFactory.createBoxBody(this.mPhysicsWorld, roof, BodyType.StaticBody, wallFixtureDef).setUserData("");
     PhysicsFactory.createBoxBody(this.mPhysicsWorld, left, BodyType.StaticBody, wallFixtureDef).setUserData("");
     PhysicsFactory.createBoxBody(this.mPhysicsWorld, right, BodyType.StaticBody, wallFixtureDef).setUserData("");
    
     
     mScene.attachChild(spr_ground);
     
    
     
     SpriteBackground bg = new SpriteBackground(backgrund);
     mScene.setBackground(bg);
     mConst.hud.setIgnoreUpdate(false);
    CreateLevel();    
	life_ctrl = new LifeCtrl(this.mScene,this.game,hud, GAME_TYPE);
    mConst.life_ctrl = life_ctrl;
    life_ctrl.UpdateDataStar(STAR_COL);
    
    if(mConst.SELECTED_MAP == 1) {
    	final Sprite palec = new Sprite(0,1700, mRes.palec);
    	palec.setScale(1.5f);
    	
    	 mScene.registerUpdateHandler(new TimerHandler(1f,
					new ITimerCallback() {

						@Override
						public void onTimePassed(TimerHandler pTimerHandler) {
							mScene.attachChild(palec);
							
							mScene.unregisterUpdateHandler(pTimerHandler);
							mScene.registerUpdateHandler(new IUpdateHandler() {

								@Override
								public void onUpdate(float pSecondsElapsed) {
									palec.setPosition(palec.getX()+5, palec.getY());
									if(palec.getX() > 350) {
										mScene.unregisterUpdateHandler(this);
										
										
									}
									
								}

								@Override
								public void reset() {
									// TODO Auto-generated method stub
									
								}  });

						}
					}));
    	 
    	
    }
  //  new Platform(100, this.getHeight(200),100,67);
}


//----------------------------------------------------------------------------------
public void CreateLevel() {

	// mConst.WORLD_HEIGHT = ldef.height;
	// mConst.WORLD_WIDTH = ldef.width;
	// for(int i = 0; i < ldef.Enemies.length;i++){
	//	 new Boxes(ldef.Enemies[i].mX,getHeight(ldef.Enemies[i].mY),ldef.Enemies[i].mEnemyType);
	// }
	 
  //---- ПОКАЗЫВАЕМ НАДПИСЬ КАКОЙ УРОВЕНЬ--------------------
	 String  str=(String)Constants.getInstance().main_activity.getResources().getString(R.string.level);
	 str+= " "+ mConst.SELECTED_MAP;
	  t2 = new Text(0, 0, Resource.getInstance().mFont_Plok, str);
	  t2.setScale(8);
	  t2.setColor(0.2f, 0.6f, 0.2f);
	  t2.setPosition(game.mSmoothCamera.getCenterX()  - t2.getWidth()/2,  game.mSmoothCamera.getCenterY() - t2.getHeight()/2  );
	  mScene.attachChild(t2);
  //  mRes.a_yah.play();
	  mScene.registerUpdateHandler(new IUpdateHandler() {  
			
			private float scale = 8;
			@Override  
   public void onUpdate(float arg0) { 
				
				if( scale <=2) {
				
				     mScene.unregisterUpdateHandler(this);
				     mScene.registerUpdateHandler(new TimerHandler(2f,
								new ITimerCallback() {

									@Override
									public void onTimePassed(TimerHandler pTimerHandler) {
										mScene.detachChild(t2);
										mScene.unregisterUpdateHandler(pTimerHandler);

									}
								}));
				
				}
				else {
					scale  -=0.2f;
					t2.setScale(scale);
				}
		         }  
 @Override  
  public void reset() {   }  
});  
 
	
  
  
}//-----------------------------------------------------------------------------------



//-------------------------ЗАГРУЗКА УРОВНЯ -------------------------------------------------------       
public void init_level(){
	  Log.v("GAME","ЗАПУСТИЛИ MAP()  = "+mConst.SELECTED_MAP);
     final LevelLoader levelLoader = new LevelLoader();
     levelLoader.setAssetBasePath("level/");
     levelLoader.registerEntityLoader(LevelConstants.TAG_LEVEL, new IEntityLoader() {
			@Override
			public void onLoadEntity(final String pEntityName, final Attributes pAttributes) {
				final int width = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH);
				final int height = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT);
				 mConst.WORLD_HEIGHT = height;
				 mConst.WORLD_WIDTH =width;
				try {
				  int type = SAXUtils.getIntAttributeOrThrow(pAttributes, "type");
				 GAME_TYPE = type;
				 Constants.getInstance().COL_HEART = SAXUtils.getIntAttributeOrThrow(pAttributes, "life");
				}catch( java.lang.IllegalArgumentException iae) {
					
				}
				 
				//Toast.makeText(LevelLoaderExample.this, "Loaded level with width=" + width + " and height=" + height + ".", Toast.LENGTH_LONG).show();
			}
		});

     //TIMER
      gameTime=110;

       timerText = new ChangeableText(80, 0, mRes.mFont, gameTime+" sec");

     TimerHandler mTime = new TimerHandler(0.1f,true, new ITimerCallback(){

          @Override

          public void onTimePassed(TimerHandler pTimerHandler) {

               // TODO Auto-generated method stub

        	  gameTime--;

               timerText.setText("s "+(gameTime/10));

               if(gameTime==0){

                  //Do something

               }

         }

      });
     
     //hud.attachChild(timerText);     
     
levelLoader.registerEntityLoader("entity", new IEntityLoader() {
	@Override
      public void onLoadEntity(final String pEntityName, final Attributes pAttributes) {
              final float x = SAXUtils.getIntAttributeOrThrow(pAttributes, "x");
              final float y = SAXUtils.getIntAttributeOrThrow(pAttributes, "y");
             // final int w = SAXUtils.getIntAttributeOrThrow(pAttributes, "width");
               int w=0;
               int h=0;
         //     final int h = SAXUtils.getIntAttributeOrThrow(pAttributes, "height");
              final String type = SAXUtils.getAttributeOrThrow(pAttributes, "type");
              if(type.equals("Platform")){ 
            	    w = SAXUtils.getIntAttributeOrThrow(pAttributes, "width");
                    h = SAXUtils.getIntAttributeOrThrow(pAttributes, "height");
              }
            //  final int player = SAXUtils.getIntAttributeOrThrow(pAttributes, "player");
              BoxType t =null;
              
              if(type.equals("Palka")) t = objects.Boxes.BoxType.Palka;
              if(type.equals("Box1")) t = objects.Boxes.BoxType.Box1;
              if(type.equals("Palka2")) t = objects.Boxes.BoxType.Palka2;
              if(type.equals("Palka2Rot")) t = objects.Boxes.BoxType.Palka2Rot;
              if(type.equals("Finish")) t = objects.Boxes.BoxType.Finish;
              if(type.equals("Rope")) t = objects.Boxes.BoxType.Rope;
              if(type.equals("Ropebad")) t = objects.Boxes.BoxType.Rope_Bad;
              
              if(type.equals("Kaktus1")) t = objects.Boxes.BoxType.Kaktus1;
              if(type.equals("Fly1")) t = objects.Boxes.BoxType.Fly1;
              
              /*
               * считаем количестов звезд!!!
               */
              if(type.equals("Star")) {
            	  STAR_COL++;
            	  mConst.STAR_COL_MAX = STAR_COL;
            	  t = objects.Boxes.BoxType.Star;
              }
              if(type.equals("Osa")) t = objects.Boxes.BoxType.Osa;
              
              if(type.equals("Platform")){ 
            	     new Platform(x, getHeight(y),w,h);
              }
            //  if(type.equals("Finish")) t = objects.Boxes.BoxType.Finish;
              
             
              else {
              if(t != null)
              new Boxes(x,getHeight(y)-32 , t);
         
              }


      }


});



try {
      

      levelLoader.loadLevelFromAsset(mConst.main_activity, ""+mConst.SELECTED_MAP+"");
} catch (final IOException e) {
      //Debug.e(e);
  Log.v("levelLoader","exeption =(");
}

	
}




//--------------------------------------------------------------



private ContactListener createContactListener() {
		ContactListener contactListener = new ContactListener()
        {
     
			@Override
            public void beginContact(Contact contact)
            {
				/*
				 * 
				 */
				if( f_player_win == true) return;
                final Fixture x1 = contact.getFixtureA();
                final Fixture x2 = contact.getFixtureB();
         
                Body b1 = x1.getBody();
                Body b2 = x2.getBody();
           //     String str1 = (String) b1.getUserData();
             //   String str2 = (String) b2.getUserData();
               if( (x1.getUserData()  instanceof String) && (x2.getUserData()  instanceof String)  ) {
            	 
                    
            	              // РУКА ПЕРСОНАЖА и ЗОМБИ
                            if(x1.getUserData().equals("hrummy")&&x2.getUserData().equals("kaktus")) {
                            	contact_man_kaktus(b2,b1);
                            }
                            
                            if(x2.getUserData().equals("hrummy")&&x1.getUserData().equals("kaktus")) {
                            	contact_man_kaktus(b1,b2);
                            }
                            
                            if(x1.getUserData().equals("hrummy")&&x2.getUserData().equals("fly")) {
                            	contact_fly(b2,b1);
                            }
                            
                            if(x2.getUserData().equals("hrummy")&&x1.getUserData().equals("fly")) {
                            	contact_fly(b1,b2);
                            }
                            if(x1.getUserData().equals("hrummy")&&x2.getUserData().equals("star")) {
                            	contact_star(b2,b1);
                            }
                            
                            if(x2.getUserData().equals("hrummy")&&x1.getUserData().equals("star")) {
                            	contact_star(b1,b2);
                            }
                            // РУКА ПЕРСОНАЖА и ЗОМБИ
                            if(x1.getUserData().equals("hrummy")&&x2.getUserData().equals("finish")) {
                            	contact_man_finish(b2);
                            }
                            
                            if(x2.getUserData().equals("hrummy")&&x1.getUserData().equals("finish")) {
                            	contact_man_finish(b1);
                            }
                            
                            
                            
                            
                            
                               
            }
            }

			@Override
            public void endContact(Contact contact) { 
		       
	            
			}
            @Override
            public void preSolve(Contact contact, Manifold oldManifold){ }
            @Override
            public void postSolve(Contact contact, final ContactImpulse impulse) { }};
            return contactListener;
	}

protected void contact_man_finish(final Body mBodyFly) {
	
	//if( F_FINISH_ENABLE == false) return;
	
	if( f_player_win == false){
		f_player_win = true;
	    game.PlayerWin();
	    mRes.m_finish.play();
	    mHrummy.animateHappy();
	    this.mConst.main_activity.runOnUpdateThread(new Runnable() {

	        @Override
	        public void run() {
	        	
	   			BoxObject bo = (BoxObject)mBodyFly.getUserData();
	   			bo.Die();
	   			mConst.COIN++;
	   			life_ctrl.UpdateData();
	        }
	  });
	}
	
}



protected void contact_man_kaktus(final Body b2, Body b1) {
	mHrummy.animateOI();
	  this.mConst.main_activity.runOnUpdateThread(new Runnable() {

	        @Override
	        public void run() {
	        	mHrummy.deleteJoint2();
	   			BoxObject bo = (BoxObject)b2.getUserData();
	   			bo.Die();
	        }
	  });
	  
	  
	life_ctrl.MinusLife(1);
	/*
	 * блокируем на одну секунду
	 *  чтоб не блыо быстрого уменьшения жизни
	 */
	//life_ctrl.f_blocked = true;
	  mScene.registerUpdateHandler(new TimerHandler(1f,
				new ITimerCallback() {
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						life_ctrl.f_blocked = false;
						mScene.unregisterUpdateHandler(pTimerHandler);

					}
				}));
	  
}



private void contact_star(final Body mBodyFly, Body mBodyHrummy) {
	Log.v(TAG,"contact_star");
	mHrummy.animateHappy();
	this.STAR_COL_CURENT ++;
	 mConst.STAR_COL_CURENT =this.STAR_COL_CURENT;
	 mRes.m_star.play();
	if(STAR_COL_CURENT == STAR_COL ) {
		 mConst.mFinish.setEnable();
		 mRes.m_finish.play();
		 F_FINISH_ENABLE = true;
		
	}
	  this.mConst.main_activity.runOnUpdateThread(new Runnable() {

	        @Override
	        public void run() {
	        	
	   			BoxObject bo = (BoxObject)mBodyFly.getUserData();
	   			bo.Die();
	   			mConst.COIN++;
	   			life_ctrl.UpdateDataStar(STAR_COL -STAR_COL_CURENT );
	        }
	  });
	  


	
	
}



private void contact_fly(final Body mBodyFly, Body mBodyHrummy) {
	Log.v(TAG,"contact_fly");
	life_ctrl.Plus(1);
	  this.mConst.main_activity.runOnUpdateThread(new Runnable() {

	        @Override
	        public void run() {
	        	mHrummy.deleteJoint2();
	   			BoxObject bo = (BoxObject)mBodyFly.getUserData();
	   			bo.Die();
	   			mHrummy.animateEat();
	        }
	  });

	
}



//---------------------------------------------------------------------------------
public void PlayHook() {
	

	
	 
}



public Scene getScene() {
		// TODO Auto-generated method stub
		return this.mScene;
	}



public void StarUpdate(){
	
}



 public void CloseScene() {
	Log.v("GameScene" ,"CloseScene() CloseScene ");
	mScene.setIgnoreUpdate(true);
	 game.runOnUpdateThread(new Runnable() {

	        @Override
	        public void run() {
	        	Dialog.getInstance().HideAll();
	        	 life_ctrl.dispose();
	        	 mHrummy.deleteJoint();
	            mConst.CURRENT_SCENE = null;
	            mConst.hud =null;
	            mConst.life_ctrl = null;
	            mConst.gameScene = null;
	         	game.mSmoothCamera.getHUD().setPosition(-1000, -1000);
	         	game.mSmoothCamera.getHUD().setIgnoreUpdate(true);
	         	game.mSmoothCamera.getHUD().clearTouchAreas();
	         	
	            game.mSmoothCamera.setCenterDirect(mConst.CAMERA_WIDTH/2, mConst.CAMERA_HEIGHT/2);
	            
	       	 Log.v("GameScene" ,"hud.getChildCount()= "+hud.getChildCount());
	            hud.detachChildren();
	            hud.clearEntityModifiers();
	            hud.clearTouchAreas();
	            hud.clearUpdateHandlers();
	            clearPhysicsWorld( mPhysicsWorld);
	       	    mScene.clearTouchAreas();
	       	   // mHrummy = null;
	       	    t2 = null;
	       		Log.v("GameScene" ,"mScene.getChildCount()= "+mScene.getChildCount());
	       	    mScene.clearChildScene();
	       	    mScene.clearEntityModifiers();
	       	    mScene.clearUpdateHandlers();
	       	 mScene.reset();
	       
	       	 Log.v("GameScene" ,"mScene.getChildCount()= "+mScene.getChildCount());
	       	 
	       	 for(int i = 0; i <mScene.getChildCount(); i++ ) {
	       		mScene.detachChild(  mScene.getChild(i));
	       	 }
	       
	     	 for(int i = 0; i <hud.getChildCount(); i++ ) {
	     		hud.detachChild(  hud.getChild(i));
		       	 }
	     	
	    	 Log.v("GameScene" ,"hud.getChildCount()= "+hud.getChildCount());
	    	 hud = null;
	       	Log.v("GameScene" ,"mScene.getChildCount()= "+mScene.getChildCount());
	       	mScene = null;
	            System.gc();
	                if(CMD_CLOSE_SCENE == 1) {CMD_CLOSE_SCENE = 0;game.sceneManager.setCurrentScene(AllScenes.GAME);}
	                 else game.sceneManager.setCurrentScene(AllScenes.SELECT_LEVEL);
	        }
	    });

}
	
protected void clearPhysicsWorld(PhysicsWorld physicsWorld)
{
	
	Iterator<Joint> allMyJoints = physicsWorld.getJoints();
	while (allMyJoints.hasNext())
	{
		try
		{
			final Joint myCurrentJoint = allMyJoints.next();
			physicsWorld.destroyJoint(myCurrentJoint);
			Debug.d("DEL JOINT " );
		} 
		catch (Exception localException)
		{
			Debug.d("SPK - THE JOINT DOES NOT WANT TO DIE: " + localException);
		}
	}
	
	Iterator<Body> localIterator = physicsWorld.getBodies();
	while (true)
	{
		if (!localIterator.hasNext())
		{
			physicsWorld.clearForces();
			physicsWorld.clearPhysicsConnectors();
			physicsWorld.reset();
			physicsWorld.dispose();
			physicsWorld = null;
			Debug.d("SPK - physicsWorld = NULL ");
			return;
		}
		try
		{
			physicsWorld.destroyBody(localIterator.next());
			Debug.d("SPK - destroyBody ");
		} 
		catch (Exception localException)
		{
			Debug.d("SPK - THE BODY DOES NOT WANT TO DIE: " + localException);
		}
	}
}






public void GameRestart() {
	CMD_CLOSE_SCENE = 1;
	CloseScene();
	
}

/*
 * ВЫЗЫВАЕТСЯ ПРИ ПЕРЕХОДЕ
 * НА СЛЕДУЮЩИЙ УРОВЕНЬ
 */

public void NextLevel() {
	//mConst.SELECTED_MAP++;
	CMD_CLOSE_SCENE = 1;
	CloseScene();
	
	
	
	
}

/*
 * У ИГРОКА ЗАКАНЧИВАЕТСЯ ЖИЗНЬ
 */

public void ManDie() {
	//mScene.setIgnoreUpdate(true);
	//Constants.getInstance().LIFE_CURRENT =Constants.getInstance().LIFE_CURRENT ;
	//Constants.getInstance().COL_HEART = Constants.getInstance().COL_HEART;
	mScene.setIgnoreUpdate(true);
	Dialog.getInstance().GamerLose();
	//man.die();
	game.f_man_die = true;
	
}



public void PlayerWin() {
    mScene.setIgnoreUpdate(true);
	
}



public void HrummyStartJoin() {
	// TODO Auto-generated method stub
	
}



public void BoxTouched(float x , float y, Body mBoxBody, ObjectType fly) {
	/*
	 * сначала вычисляем длину
	 * если подойдет то  создаем соединение
	 * 
	 */
	mHrummy.createJoint(x , y ,mBoxBody ,fly);
	
}



public void BoxTouchedEnd() {
	// TODO Auto-generated method stub
	
}



@Override
public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
	Log.v("GS","pSceneTouchEvent="+pSceneTouchEvent.getAction());
	if(pSceneTouchEvent.isActionUp() ) {

	}
	mHrummy.deleteJoint();
	return false;
}




private float getHeight(float h) {
	
	
	return mConst.WORLD_HEIGHT - h;
}
}
