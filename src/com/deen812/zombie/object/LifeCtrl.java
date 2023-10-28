package com.deen812.zombie.object;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;

import com.deen812.hroommy.Constants;
import com.deen812.hroommy.main;

public class LifeCtrl {
    public Sprite life;
	private ChangeableText scoreText;
	private int lfie_col;
	//private int life_current;
	private ChangeableText scoreTextZ32;
	private main game;
	private Sprite zh32;
	private boolean man_die = false;
	private Rectangle redRectangle;
	public boolean f_blocked  =false;
	private int game_type;
	public LifeCtrl(Scene mScene, main game, HUD hud, int gAME_TYPE) {
		
		
		this.game_type = gAME_TYPE;
		
		
		this.life = new Sprite(3,41,game.mRes.LIFE_PROGR_BG);
		this.game = game;
		life.setScale(0.8f);
		lfie_col = Constants.getInstance().COL_HEART;
	//	lfie_col = Constants.getInstance().LIFE_COL;
		//life_current = Constants.getInstance().LIFE_CURRENT ;
		Constants.getInstance().LIFE_CURRENT2 =Constants.getInstance().LIFE_CURRENT ;
		Constants.getInstance().COL_HEART2 = Constants.getInstance().COL_HEART;
		scoreText = new ChangeableText(50, 33, game.mRes.mFont, lfie_col+"", 10);
		scoreText.setColor(1f, 0, 0.1f);
		scoreText.setScale(0.8f);
		
		if( gAME_TYPE!= 0) {
		hud.attachChild(scoreText);
		 hud.attachChild(life);
		
		}
		
		 zh32  = new Sprite(0,0,game.mRes.h_star_simple);
		hud.attachChild(zh32);
		zh32.setScale(0.5f);
		scoreTextZ32 = new ChangeableText(50,0, game.mRes.mFont,""+ Constants.getInstance().COIN, 10);
		hud.attachChild(scoreTextZ32);
		scoreTextZ32.setColor(0, 0, 0);
		scoreTextZ32.setScale(0.8f);
		redRectangle = new Rectangle(0,0,Constants.getInstance().CAMERA_WIDTH,Constants.getInstance().CAMERA_HEIGHT );
		redRectangle.setColor(1, 0, 0);
		redRectangle.setAlpha(0.5f);
		
		
		f_blocked  =false;
	}

	public void MinusLife(int life) {
		if(f_blocked   == true) return;
		if(life <=0) return;
		//if( man_die) return;
		lfie_col-=life;
		Constants.getInstance().COL_HEART=lfie_col;
		Constants.getInstance().hud.attachChild(redRectangle);
		
		Constants.getInstance().CURRENT_SCENE.registerUpdateHandler(new IUpdateHandler() {  
			
			@Override  
        public void onUpdate(float arg0) { 
			
				Constants.getInstance().CURRENT_SCENE.unregisterUpdateHandler(this);
				Constants.getInstance().hud.detachChild(redRectangle);
				
		         }  

      @Override  
       public void reset() {   }  
    });  

			if(lfie_col < 0) {
				if(man_die  == false) {
				Constants.getInstance().gameScene.ManDie();
				man_die = true;		
				}
				lfie_col = 0;

			}
		scoreText.setText(lfie_col+"");
	}
	
	public void PlusZombieKill() {
	//	Constants.getInstance().COIN++;
	//	scoreTextZ32.setText(""+Constants.getInstance().COIN);
	//	this.game.KILL_ZOMBIE();
		
		
	}

	
	
	public void dispose() {
		Constants.getInstance().hud.detachChild(life);
		Constants.getInstance().hud.detachChild(redRectangle);
		redRectangle = null;
		Constants.getInstance().hud.detachChild(scoreText);
		Constants.getInstance().hud.detachChild(zh32);
		Constants.getInstance().hud.detachChild(scoreTextZ32);
		f_blocked  =false;
	}

	
	public void Plus(int plus) {
		lfie_col+=plus;
		Constants.getInstance().COL_HEART=lfie_col;
		UpdateData();
	}
	public void clearRect() {
		// TODO Auto-generated method stub
		
	}

	public void UpdateData() {
		//scoreTextZ32.setText(""+Constants.getInstance().COIN);
		scoreText.setText(""+Constants.getInstance().COL_HEART);
		
		
	}
	public void UpdateDataStar(int n) {
		scoreTextZ32.setText(""+n);

		
		
	}
}
