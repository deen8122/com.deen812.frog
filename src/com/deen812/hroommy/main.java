package com.deen812.hroommy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.level.LevelLoader;
import org.anddev.andengine.level.LevelLoader.IEntityLoader;
import org.anddev.andengine.level.util.constants.LevelConstants;
import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.SAXUtils;
import org.xml.sax.Attributes;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

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
import com.deen812.game.util.IabHelper;
import com.deen812.game.util.IabResult;
import com.deen812.game.util.Inventory;
import com.deen812.game.util.Purchase;
import com.deen812.hroommy.SceneManager.AllScenes;
import com.deen812.zombie.scene.Dialog;





public class main extends BaseGameActivity implements  IOnSceneTouchListener, IOnAreaTouchListener, IScrollDetectorListener
{

	public SmoothCamera mSmoothCamera;
	private Constants mConst;
	public SceneManager sceneManager;
	public Resource mRes;
	private Scene mScene;
	private SurfaceScrollDetector mScrollDetector;
	private IabHelper mHelper;
	private String TAG="main";


	private boolean mIsPremium = false;
	private static final String TAG_ENTITY = "entity";

	private static final String ITEM_SKU = "fullversion";
    static final String SKU_PREMIUM = "fullversion";
	 static final int RC_REQUEST = 10001;
	protected static final String SCU_LIFE = "life3";
	protected static final int TANK_MAX = 10;
	protected static final String SKU_GAS = null;
	private int mTank;
	public boolean f_man_die = false;
	private UserData userdata;
	private static Activity me = null;
	//-----------------------------------------------------------------------
	@Override
	public Engine onLoadEngine() {
		    //this.getResources().getString(R.string.app_name);
	       
		    mConst  =  Constants.getInstance();
		    mConst.main_activity = this;
		    init_PREFERNCE();
		    DisplayMetrics dm = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
            this.mSmoothCamera = new SmoothCamera(0, 0, mConst.CAMERA_WIDTH, mConst.CAMERA_HEIGHT, mConst.WORLD_WIDTH, mConst.WORLD_HEIGHT, 1.0f);
               // mSmoothCamera.setBounds(0, mConst.WORLD_WIDTH, 0, mConst.WORLD_HEIGHT);
               // mSmoothCamera.setBoundsEnabled(true); 
               EngineOptions engineOptions =   new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(mConst.CAMERA_WIDTH, mConst.CAMERA_HEIGHT), this.mSmoothCamera);
                             engineOptions.setNeedsSound(true);
                             engineOptions.setNeedsMusic(true);
                            // engineOptions.setn.setNeedsMultiTouch(true);
                            //engineOptions.getTouchOptions().m.setNeedsMultiTouch(true);
                            // engineOptions.getTouchOptions().
                             //engineOptions.getTouchOptions().setNeedsMultiTouch(true);
                             //engineOptions.getRenderOptions().getRenderOptions().setDithering(true); 
                             engineOptions.getRenderOptions().disableExtensionVertexBufferObjects();
                             mSmoothCamera.setMaxVelocityX(1);
                           //  mSmoothCamera.setm
        final Engine mEngine = new Engine(engineOptions);
        this.mEngine = mEngine;
        mConst.mEngine = this.mEngine;
        try {
			if(MultiTouch.isSupported(this)) {
				mEngine.setTouchController(new MultiTouchController());
				if(MultiTouch.isSupportedDistinct(this)) {
				//	Toast.makeText(this, "MultiTouch detected --> Drag multiple Sprites with multiple fingers!", Toast.LENGTH_LONG).show();
				} else {
					//Toast.makeText(this, "MultiTouch detected --> Drag multiple Sprites with multiple fingers!\n\n(Your device might have problems to distinguish between separate fingers.)", Toast.LENGTH_LONG).show();
				}
			} else {
				//Toast.makeText(this, "Sorry your device does NOT support MultiTouch!\n\n(Falling back to SingleTouch.)", Toast.LENGTH_LONG).show();
			}
		} catch (final MultiTouchException e) {
		//	Toast.makeText(this, "Sorry your Android Version does NOT support MultiTouch!\n\n(Falling back to SingleTouch.)", Toast.LENGTH_LONG).show();
		}

        
	        return mEngine;
	}

	
	
	 // Вызывается перед выходом из "активного" состояния
    @Override
    public void onPause(){
        // "Замораживает" пользовательский интерфейс, потоки 
        // или трудоемкие процессы, которые могут не обновляться, 
        // пока Активность не находится на переднем плане.
    	//mRes.mysound.stop();
    	if(mRes !=null)mRes.mysound.pause();
        super.onPause();
    }
    @Override
    public void onResume() {
    	 super.onResume();
    	Log.v(TAG,"onResume");
    	if(mRes !=null && Constants.getInstance().SOUND_ON==1)mRes.mysound.play();
    }
    // Вызывается перед тем, как Активность перестает быть "видимой".
    @Override
    public void onStop(){
        // "Замораживает" пользовательский интерфейс, потоки 
        // или операции, которые могут подождать, пока Активность
        // не отображается на экране. Сохраняйте все введенные
        // данные и изменения в UI так, как будто после вызова
        // этого метода процесс должен быть закрыт.
    	//mRes.mysound.stop();
        super.onStop();
    }

	//--------------------------------------------------------------------------
	@Override
	public void onLoadResources() {
		sceneManager = new SceneManager(this, mEngine, mSmoothCamera);
		sceneManager.loadSplashResources();
		 mRes  = Resource.getInstance();
		 mRes.onLoadResources(this ,this.mEngine );
	}
	
	//------------------------------------------------------------------------------

	@Override
	public Scene onLoadScene() {
	    this.mEngine.registerUpdateHandler(new FPSLogger());
        this.mScene =    sceneManager.createSplashScene();
       
		sceneManager.setRes(mRes);
        this.mScrollDetector = new SurfaceScrollDetector(this);
        this.mScrollDetector.setEnabled(true);
        
   
	
        return this.mScene;
	}


	//----------------------------------------------------------------------------------
	@Override
	public void onLoadComplete() {
	/*
	 *  МУЗЫКА	
	 */
		mRes.mysound.setLooping(true);
		mRes.mysound.setVolume(0.6f);

		if(mConst.F_SEND_REV == 0) {
		mConst.COUNT_EXIT+=1;
Log.v("MS","mConst.COUNT_EXIT="+mConst.COUNT_EXIT+ "  mConst.F_SEND_REV="+mConst.F_SEND_REV);
UpdateParam(0,mConst.COUNT_EXIT);
		}	 
   	 /*
   	  * 
   	  */

     mEngine.registerUpdateHandler(new TimerHandler(3f,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
						Log.v("GAME","onTimePassed ");
						mEngine.unregisterUpdateHandler(pTimerHandler);
						sceneManager.createMenuScene();
						sceneManager.setCurrentScene(AllScenes.MENU);
						//sceneManager.setCurrentScene(AllScenes.GAME);
					}
				}));
		
	}

	@Override
	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		return false;
	}


	/*
	 * --------------------------------------------------------------------------
	 * PERFERENCE
	 * 
	 */
	private void init_PREFERNCE() {
		
		//ComplexPreferences complexPrefenreces = 
		//		ComplexPreferences.getComplexPreferences(getBaseContext(), "SETTING", MODE_PRIVATE);
		//complexPrefenreces.putObject("user", user);
		//complexPrefenreces.putObject("list", complexObject);
	//	complexPrefenreces.commit();
		
		ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, "SETTING", MODE_PRIVATE);
		
		 userdata = complexPreferences.getObject("user", UserData.class);
		if(userdata== null )userdata = new UserData();
		mConst.LEVEL_STAR_COL = userdata.LEVEL_STAR_COL;
		
		
	//	userdata.LEVEL_STAR_COL[3]=1;
	//	userdata.LEVEL_STAR_COL[4]=0;
	//	userdata.LEVEL_STAR_COL[5]=2;
	///    complexPreferences.putObject("user", userdata);
	  //  complexPreferences.commit();
	    
		me = this;
		SharedPreferences mSettings = getSharedPreferences("SETTING", Context.MODE_PRIVATE);
		mConst.COIN = mSettings.getInt(mConst.COIN_NAME, 0);
		
		///mConst.COL_HEART = mSettings.getInt(mConst.HEART_NAME, 3);
		//mConst.COL_HUMMER = mSettings.getInt(mConst.HUMMER_NAME, 1);
     	//mConst.COL_SNOW = mSettings.getInt(mConst.SNOW_NAME, 1);
     	mConst.LEVEL =  mSettings.getInt(mConst.LEVEL_NAME, 1);
     	mConst.FULL_VERSION =mSettings.getInt("FULL_VERSION", 0);
     	
     	//mConst.SOUND_ON
     	mConst.SOUND_ON =mSettings.getInt("SOUND_ON", 1);
     	mConst.MUSIC_ON =mSettings.getInt("MUSIC_ON", 1);
     	Log.v("MAIN","mConst.LEVEL ="+mConst.LEVEL);
     	
     	//mConst.COUNT_EXIT
     	mConst.COUNT_EXIT = mSettings.getInt("count_exit", 0);
     	//F_SEND_REV;
     	Log.v("MAIN","mConst.COUNT_EXIT="+mConst.COUNT_EXIT);
     	mConst.F_SEND_REV = mSettings.getInt("F_SEND_REV", 0);
     	init_bye();
	}
	public void updateByeParam() {
		
		    Log.v("MAIN","updateByeParam()");
		    //SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
		    SharedPreferences mSettings = getSharedPreferences("SETTING", Context.MODE_PRIVATE);
		    Editor spe = mSettings.edit();
		    
		    spe.putInt(mConst.COIN_NAME, mConst.COIN);
		    spe.commit();
		    
		  
			
		//	editor.putInt(APP_PREFERENCES_COUNTER, game.map);
		//	editor.commit();
		    spe.putInt(mConst.HEART_NAME, mConst.COL_HEART);spe.commit();
		    
		    spe.putInt(mConst.HUMMER_NAME, mConst.COL_HUMMER);spe.commit();
		
		    spe.putInt(mConst.SNOW_NAME, mConst.COL_SNOW);spe.commit();
	}
	//-------------------------------------------------------------------------------------




/*
 * если игрок  прошел уровень!
 */
public void PlayerWin(){
	
	/*
	 * если пользователь выбрал 
	 * последний уровень то  открываем
	 */
	mConst.F_PLAYER_WIN = true;
	if(mConst.SELECTED_MAP ==  mConst.LEVEL) {
    	mConst.LEVEL+=1;
    	mConst.SELECTED_MAP = mConst.LEVEL;
    }else {
    	mConst.SELECTED_MAP+=1;
    }
	
	SharedPreferences mSettings = getSharedPreferences("SETTING", Context.MODE_PRIVATE);
	Editor editor = mSettings.edit();
	// УРОВЕНЬ
	editor.putInt(mConst.LEVEL_NAME, mConst.LEVEL);
	
	// ЖИЗНЬ
	editor.putInt(mConst.HEART_NAME, Constants.getInstance().COL_HEART2);
	//editor.putInt("LIFE_CURRENT", Constants.getInstance().LIFE_CURRENT2);
	//Constants.getInstance().LIFE_CURRENT =Constants.getInstance().LIFE_CURRENT2;
	Constants.getInstance().COL_HEART =Constants.getInstance().COL_HEART2;
	// УБИТЫХ ЗОМБИ
	
	// МОНЕТЫ
	//mConst.COIN += mConst.KILLED_ZOMBIE;
	mConst.KILLED_ZOMBIE = 0;
	editor.putInt(mConst.COIN_NAME, mConst.COIN);
	 editor.commit();

		
		/*
		 * сохраняем    полученные звезды
		 * 
		 */
	//КОЛИЧЕСТВО ВЗЯТЫХ ЗВЕЗД
	 int curent = 2;

	  if(mConst.STAR_COL_CURENT >= (mConst.STAR_COL_MAX-1)) {
	    	 curent = 1;
		 }
		 if(mConst.STAR_COL_CURENT == 0) {
			 curent = 2;
		 }
     if(mConst.STAR_COL_CURENT == mConst.STAR_COL_MAX) {
    	 curent = 0;
	 }
     
     mConst.COL_LEVEL_STAR = curent;
	ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, "SETTING", MODE_PRIVATE);
    userdata.LEVEL_STAR_COL[ mConst.SELECTED_MAP -2 ]=curent;
    mConst.LEVEL_STAR_COL = userdata.LEVEL_STAR_COL;
    complexPreferences.putObject("user", userdata);
    complexPreferences.commit();
		
		
		
		
		
	 mEngine.registerUpdateHandler(new TimerHandler(2f,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {
					
						mEngine.unregisterUpdateHandler(pTimerHandler);
						 Dialog.getInstance().GamerWin();
					}
				}));
	 
	 this.sceneManager.ms3.updateLevels();
	
	this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
        mConst.F_ENEMY_END = false;
	//	Toast.makeText(main.this, "Игра закончилась!", Toast.LENGTH_SHORT).show();
		
		
        }
    });
	
}



	public void KILL_ZOMBIE() {
		if(f_man_die == true) return;

		mConst.KILLED_ZOMBIE++;
		Log.v("MAIN","KILL_ZOMBIE()");
		
		
		if( (mConst.ZOMBIE_COL ==mConst.KILLED_ZOMBIE)) {
			   Log.v("MAIN","KILL_ZOMBIE()   PLAYER WIN! mConst.SELECTED_MAP="+mConst.SELECTED_MAP +" mConst.LEVEL="+mConst.LEVEL );
			// ЕСЛИ ИГРОК ВЫИГРАЛ
			/*
			 * НЕ ПРАВИЛЬНО! ЭТА ФУНКЦИ НЕ ДОЛЖНА БЫТЬ ЗДЕСЬ!
			 */
			
			
		    if(mConst.SELECTED_MAP ==  mConst.LEVEL) {
		    	mConst.LEVEL+=1;
		    	mConst.SELECTED_MAP = mConst.LEVEL;
		    }else {
		    	mConst.SELECTED_MAP+=1;
		    }
			SharedPreferences mSettings = getSharedPreferences("SETTING", Context.MODE_PRIVATE);
			Editor editor = mSettings.edit();
			// УРОВЕНЬ
			editor.putInt(mConst.LEVEL_NAME, mConst.LEVEL);
			
			// ЖИЗНЬ
			editor.putInt(mConst.HEART_NAME, Constants.getInstance().COL_HEART2);
			editor.putInt("LIFE_CURRENT", Constants.getInstance().LIFE_CURRENT2);
			Constants.getInstance().LIFE_CURRENT =Constants.getInstance().LIFE_CURRENT2;
			Constants.getInstance().COL_HEART =Constants.getInstance().COL_HEART2;
			// УБИТЫХ ЗОМБИ
			
			// МОНЕТЫ
			//mConst.COIN += mConst.KILLED_ZOMBIE;
			mConst.KILLED_ZOMBIE = 0;
			editor.putInt(mConst.COIN_NAME, mConst.COIN);
			
			
			 editor.commit();
		
			 mEngine.registerUpdateHandler(new TimerHandler(1f,
						new ITimerCallback() {

							@Override
							public void onTimePassed(TimerHandler pTimerHandler) {
							
								mEngine.unregisterUpdateHandler(pTimerHandler);
								 Dialog.getInstance().GamerWin();
							}
						}));
			 this.sceneManager.ms3.updateLevels();
			
			this.runOnUiThread(new Runnable() {
		        @Override
		        public void run() {
		        mConst.F_ENEMY_END = false;
			//	Toast.makeText(main.this, "Игра закончилась!", Toast.LENGTH_SHORT).show();
				
				
		        }
		    });
		}
		
	}





/*
 * СОХРАНЯЕМ ЗВУК
 */

	public void setSound(int i) {
		SharedPreferences mSettings = getSharedPreferences("SETTING", Context.MODE_PRIVATE);
		Editor editor = mSettings.edit();
		// УРОВЕНЬ
		editor.putInt("SOUND_ON", mConst.SOUND_ON);
		editor.commit();
		
	}
	
	public void setSound2(int i) {
		SharedPreferences mSettings = getSharedPreferences("SETTING", Context.MODE_PRIVATE);
		Editor editor = mSettings.edit();
		// УРОВЕНЬ
		editor.putInt("MUSIC_ON", mConst.MUSIC_ON);
		editor.commit();
		
	}

	
/*	
	  @Override     public void onCreate(Bundle savedInstanceState) {         
		  super.onCreate(savedInstanceState);         
		  setContentView(R.layout.activity_main);         
		  // Читаем сохраненные настройки приложения         loadData();           
		  // В переменной base64EncodedPublicKey должен быть указан открытый ключ RSA приложения вместо          
		  // CONSTRUCT_YOUR_KEY_AND_PLACE_IT_HERE        
		  String base64EncodedPublicKey = "CONSTRUCT_YOUR_KEY_AND_PLACE_IT_HERE";                 
		  // Защита от дурака, проверяет, действительно ли был указан ключ RSA         
		  // и было ли переименовано приложение из стандартного        
		  if (base64EncodedPublicKey.contains("CONSTRUCT_YOUR")) {             
			  throw new RuntimeException("Please put your app's public key in MainActivity.java. See README.");         }         
		  if (getPackageName().startsWith("com.example")) {            
			  throw new RuntimeException("Please change the sample's package name! See README.");         }                 
		  
		  // Создается новая копия объекта хелпера для взаимодействия с биллингом        
Log.d(TAG, "Creating IAB helper.");        
mHelper = new IabHelper(this, base64EncodedPublicKey);                  
// Включается режим логирования в хелпере         
mHelper.enableDebugLogging(true);         
// Начинается настройка хелпера. Это ассинхронная процедура со своими слушателями,         
// которые будут вызванны по завешении получения данных.        
Log.d(TAG, "Starting setup.");        
mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {             
	public void onIabSetupFinished(IabResult result) {                
		Log.d(TAG, "Setup finished.");                  
		if (!result.isSuccess()) {                     
			// Произошла ошибка, сообщаем о ней пользователю и выходим из метода    
			complain("Problem setting up in-app billing: " + result);               
			return;                 
			}                
		// Хелпер полностью инициализирован, получаем                 
		Log.d(TAG, "Setup successful. Querying inventory.");                
		mHelper.queryInventoryAsync(mGotInventoryListener);             }         });     } 
		}
	
	}
}
	  }
	
	*/
public void init_bye(){
	
	Log.v(TAG,"init_bye()");
	mHelper = new IabHelper(this, mConst.base64EncodedPublicKey);
	// включаем дебагинг (в релизной версии ОБЯЗАТЕЛЬНО выставьте в false)
	mHelper.enableDebugLogging(true);
	mHelper.startSetup(new  IabHelper.OnIabSetupFinishedListener() {
	   	        public void onIabSetupFinished(IabResult result) 
	             {
	                   if (!result.isSuccess()) {
	                		
	                           Log.v(TAG, " 466 In-app Billing setup failed: " + result);
	           } else {        Log.v(TAG, " 468 In-app Billing is set up OK");
	       	mHelper.queryInventoryAsync(mGotInventoryListener); 
               }
	         }
	});

}


/*
 *                		
 */


public void bye(){
	 Log.v(TAG,"bye()");
	 String payload = "deen812";
	 Log.v(TAG, "НАЧИНАЕМ ПОКУПКУ.");
     /*
      * для безопасности сгенерьте payload для верификации. В данном
      * примере просто пустая строка юзается. Но в реальном приложение
      * подходить к этому шагу с умом.
      */
     mHelper.launchPurchaseFlow(this, SCU_LIFE, RC_REQUEST,mPurchaseFinishedListener, payload);
     
}//----------------------------------------------------------


//mGotInventoryListener – слушатель для восстановления покупок. 
//Слушатель для востановителя покупок.
IabHelper.QueryInventoryFinishedListener mGotInventoryListener = 
new IabHelper.QueryInventoryFinishedListener() {

	
    // конец запроса	
	public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
        Log.v(TAG, " 441 Query inventory finished.");
        if (mHelper == null) return;
        if (result.isFailure()) {
            complain("Failed to query inventory: " + result);
            return;
        }
        Log.d(TAG, "Query inventory was successful.");
        // Do we have the premium upgrade?
        /*
         * Проверяются покупки. Обратите внимание, что надо проверить каждую
         * покупку, чтобы убедиться, что всё норм! см.
         * verifyDeveloperPayload().
         */
        Purchase premiumPurchase = inventory.getPurchase(SKU_PREMIUM);
        mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
        Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
        if( mIsPremium ) {       	
        	//saveData(1); 
        	mConst.FULL_VERSION = 1;
            Log.v(TAG,"saveData()");
            SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
            spe.putInt("FULL_VERSION", 1);
            spe.commit();
        }else {
        	mConst.FULL_VERSION = 0;
        	   Log.v(TAG,"saveData()");
               SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
               spe.putInt("FULL_VERSION", 1);
               spe.commit();
        }     
    }

	private boolean verifyDeveloperPayload(Purchase premiumPurchase) {
		// TODO Auto-generated method stub
		return true;
	}
};

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
    if (mHelper == null) return;

    // Pass on the activity result to the helper for handling
    if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
        // not handled, so handle it ourselves (here's where you'd
        // perform any handling of activity results not related to in-app
        // billing...
        super.onActivityResult(requestCode, resultCode, data);
    }
    else {
        Log.d(TAG, "onActivityResult handled by IABUtil.");
    }
}


/*
 * вызывается после успешной покупки
 */
IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = 
new IabHelper.OnIabPurchaseFinishedListener() {

	
    
	
	public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
        Log.v(TAG, " 537 Покупка завершена: Purchase finished: " + result + ", purchase: " + purchase);
        
        // if we were disposed of in the meantime, quit.
        if (mHelper == null) return;

        if (result.isFailure()) {
        	 Log.v(TAG, " 543 Покупка завершена: result.isFailure()");
     //   	 mHelper.consumeAsync(purchase, mConsumeFinishedListener);
            complain("  608 ОШИБКА: " + result);
            setWaitScreen(false);
            return;
        }
        if (!verifyDeveloperPayload(purchase)) {
            complain("Error purchasing. Authenticity verification failed.");
           // setWaitScreen(false);
            return;
        }
        Log.v(TAG, "Успех !        Purchase successful.");
 if (purchase.getSku().equals(SKU_PREMIUM)) {
            Log.d(TAG, "Purchase is premium upgrade. Congratulating user.");
            alert("Thank you for upgrading to premium!");
            mIsPremium = true;
            saveData(1);
            
            /*
             *  
             */
            //sceneManager.setCurrentScene(AllScenes.SELECT_LEVEL);
            
        }

    }
};




IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {


	
    public void onConsumeFinished(Purchase purchase, IabResult result) {
        Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);
        if (mHelper == null) return;

        if (result.isSuccess()) {
        	 // Потребление продукта прошло успешно.               
             // Активируем логику приложения и заправим наш танк.
             saveData();
             
             alert("Вы купили  полную версию игры! спасибо за покупку! а теперь вперед к приключениям, дружище!");
           // alert("You filled 1/4 tank. Your tank is now " + String.valueOf(mTank) + "/4 full!");
        }
        else {
            complain("Error while consuming: " + result);
        }

       // updateUi();
     //   setWaitScreen(false);
      //  Log.d(TAG, "End consumption flow.");
    }
};



/** сравнение платежных данных */
boolean verifyDeveloperPayload(Purchase p) {
    String payload = p.getDeveloperPayload();
    return true;
}



// Enables or disables the "please wait" screen.
void setWaitScreen(boolean set) {
   // findViewById(R.id.screen_main).setVisibility(set ? View.GONE : View.VISIBLE);
   // findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);
}

void complain(String message) {
    Log.e(TAG, "**** TrivialDrive Error: " + message);
    alert("Error: " + message);
}
void alert(String message) {
   // AlertDialog.Builder bld = new AlertDialog.Builder(this);
   // bld.setMessage(message);
  //  bld.setNeutralButton("OK", null);
    Log.d(TAG, "Showing alert dialog: " + message);
  //  bld.create().show();
}




/*
 * 
 * 
 */
void saveData() {
	mConst.FULL_VERSION = 1;
    UpdateParam(2,1);
    sceneManager.setCurrentScene(AllScenes.THANKS);
}
void saveData(int N) {
	mConst.FULL_VERSION = N;
	 UpdateParam(2,N);
    sceneManager.setCurrentScene(AllScenes.THANKS);
}


void loadData() {
  //  SharedPreferences sp = getPreferences(MODE_PRIVATE);
   // mTank = sp.getInt("tank", 2);
   // Log.d(TAG, "Loaded data: tank = " + String.valueOf(mTank));
}



public void byeFullVersion() {
	 Log.v(TAG,"bye()");
	 String payload = "deen812";
	 Log.v(TAG, "НАЧИНАЕМ ПОКУПКУ.");
     /*
      * для безопасности сгенерьте payload для верификации. В данном
      * примере просто пустая строка юзается. Но в реальном приложение
      * подходить к этому шагу с умом.
      */
     mHelper.launchPurchaseFlow(this, 
    		 SKU_PREMIUM, 
    		 RC_REQUEST,
    		 mPurchaseFinishedListener, 
    		 payload);
	
}


public static void rateApp() {
Intent i = new Intent(Intent.ACTION_VIEW);
i.setData(Uri.parse("market://details?id=com.deen812.hroommy"));

me.startActivity(i);
}

public void checkDemoGame() {
	if(mConst.FULL_VERSION == 1) return;
	if(mConst.SELECTED_MAP >= 16) {
		sceneManager.setCurrentScene(AllScenes.BYE_DEMO);
	}
}



public void setRev() {

	mConst.F_SEND_REV =1;
    UpdateParam(1,1);
}



public void UpdateParam(int i,int value) {
	SharedPreferences mSettings = getSharedPreferences("SETTING", Context.MODE_PRIVATE);
	Editor editor = mSettings.edit();
	// УРОВЕНЬ
	if( i == 0) {editor.putInt("count_exit", value);}
	if( i == 1) {editor.putInt("F_SEND_REV", value);}
	//FULL_VERSION
	if( i == 2) {editor.putInt("FULL_VERSION", value);}
	editor.commit();
}



	
}//enf class

