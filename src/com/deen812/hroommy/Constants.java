package com.deen812.hroommy;

import objects.Finish;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import com.deen812.zombie.object.LifeCtrl;

import com.deen812.zombie.scene.ByeScene;
import com.deen812.zombie.scene.GameScene;



public class Constants {
	private static final String COL_COIN = "COL_COIN";

	public final String LEVEL_NAME = "LEVEL";

	public String base64EncodedPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv8be5UR5rZYNWsdEZD+3gRb/EUdVX43lvR3/ONIaOmLUcstaVY72WoXqXJ3kB/LmKnoN7FktTAjUc5og9nU/BHlvQneMCUTcqBUS/fqPVHBQYJGqg6BovP071ACeQ4ado8ofq6gkTK3yN5RRO6cPXPq2W3b/wmfIdTsjukFRvpxEfiE2FJVd8s6vc/WdA18tlZTfmByVjJoZj6EHjfT+WPYRXb11Zn4i0hGGihr9gjmc9azuOZE3Nk9L6xtSF92I+7GPHeRQu6eQgnyHNWEWGzC58k4gRWQIYOGEUMUdMcNISwdXLFFVXsXvKVVIzBGSzDhT0xOnYfRV6Vs2QEccSwIDAQAB";
	//chunk_split($publicKey, 64, "\n") 
	//AIzaSyBfNkrtXBZu9EyLB61v33FEM8FZysw0z-I
	public String base64EncodedPublicKey2 ="31129deen";
	int CAMERA_Y_CENTER = 240;
	public float CAMERA_WIDTH = 800;
	public float CAMERA_HEIGHT = 480;  
	public float TABLE_WIDTH = 750;
	public float TABLE_HEIGHT = 420;  
    float GAME_TABLE_X = 210;
    float GAME_TABLE_Y = 210;
	public float WORLD_WIDTH =  2000 ;
	public float WORLD_HEIGHT = 2000;
    float ZOOM_MAX =1.5f;
    float ZOOM_MIN = 0.5f;
    float GROUND_HEIGHT = 150;

    

	public float LEN_FIRST=0;
	protected Vector2 point3;
	public float point3_x;
	public float point3_y;
	protected float BALL_X = 0;
	public float BALL_Y = 0;
	public int PLAYER_SCORE = 0;
	public int LEVEL= 1;
	public int TEXT_U_LOST = 1;
	public int MAX_LEVEL = 45;
	public int SELECTED_MAP = 0;

	public int COIN =101;

	public int COL_HEART = 8;

	public int COL_HUMMER = 3;

	public int COL_SNOW = 3;

	public String COIN_NAME ="COIN";

	public String HEART_NAME="HEART";

	public String HUMMER_NAME ="HUMMER";

	public String SNOW_NAME = "SNOW";



	public int ZOMBIE_COL;

	public int CURRENT_ZOMBIE_I = 0;

	public Scene CURRENT_SCENE;

	public HUD hud;



	//public int LIFE_COL;

	public int USER_KILL_Z =0;

	public LifeCtrl life_ctrl;

	public boolean F_ENEMY_END = false;

	public main main_activity;

	public GameScene gameScene;

	public int KILLED_ZOMBIE;

	public int LIFE_COL_INIT;

	public Engine mEngine;

	public int LIFE_CURRENT;

	public int LIFE_CURRENT2;

	public int COL_HEART2;

	public int SOUND_ON = 1;

	public int MUSIC_ON = 0;

	public PhysicsWorld mPhysicsWorld;

	public AnimatedSprite MAN;

	public ByeScene BYE_SCENE;

	public int H_STAR_COL   = 0;

	public int H_LIFE_COL = 0;

	public int MAX_COL_MAP = 46;

	public AnimatedSprite h_HrummySprite =null;

	public boolean F_PLAYER_WIN = false;

	public Finish mFinish;

	public int FULL_VERSION = 0;

	public int F_SEND_REV;

	public int COUNT_EXIT = 0;
	
	public int LEVEL_STAR_COL[];

	public int STAR_COL_MAX;

	public int STAR_COL_CURENT;

	public int COL_LEVEL_STAR;

	public int FREE_LEVELS = 16;
	
	
	
	
    private static Constants instance;

	  public static Constants getInstance() {
	    if(instance == null) {
	      instance = new Constants();
	    }
	    return instance;
	  }

	  private Constants() {

		  
	  }

	  /*
	   * Обновляем параметры которые можно купить за деньги
	   * 
	   */
	public void updateByeParam(main game) {

		
		

	}
	  
	  
}
