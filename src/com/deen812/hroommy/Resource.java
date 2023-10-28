package com.deen812.hroommy;


import java.io.IOException;
import java.util.ArrayList;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
//import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
//import org.anddev.andengine.opengl.texture.atlas.buildable.BuildableTextureAtlas;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class Resource {


public Engine mEngine;
public TextureRegion mBackgroundTextureRegion;
public TextureRegion board;


//TEXT
public Texture mFontTexture;
public Font mFont;



// BUttons
public TextureRegion btn_pause;
public TextureRegion paused;

public TextureRegion bg1;
public TextureRegion wall1;
public TextureRegion wall2;

public TiledTextureRegion text;
//public TextureRegion paused_content;
public TextureRegion btn_reload;
public TextureRegion btn_menu;
public TextureRegion bg2;
public TextureRegion btn_exit;
public TextureRegion bg4;
public TextureRegion txt_start;
public TextureRegion btn_sound;

public TextureRegion btn_back;
public TextureRegion  btn_next;
public TiledTextureRegion menu_level;
public TextureRegion game_name;
public TextureRegion btn_prev;
public TextureRegion head_select;
public TextureRegion btn_bye;
public TextureRegion btn_info;
public TextureRegion btn_no;

public Music mysound;
//public TextureRegion how;
public TextureRegion vibro;
public TextureRegion music;




public TextureRegion btn_plus;
public TextureRegion coin_bg;

public TextureRegion LIFE_PROGR_BG;

public Font mFont_Plok;
public TextureRegion bg_home;
//public TextureRegion btn_c;
public TextureRegion panel_heart;



public Sound m_pak;


public TextureRegion btn_help;
//public TextureRegion how_play;

public TextureRegion btn_music;


public TiledTextureRegion h_hamelion;
public TiledTextureRegion h_fly1;
public TextureRegion h_box;
public TextureRegion h_rope1;
public TextureRegion h_rope2;
public TextureRegion h_rope3;
public TextureRegion h_palka;
public TextureRegion h_kaktus1;
public TiledTextureRegion h_star;
public TiledTextureRegion h_finish;
public TextureRegion h_cloud;
public TextureRegion h_cloud2;
public TextureRegion h_star_simple;
public TextureRegion h_rope_bad1;
public TiledTextureRegion h_effect_star;
public TextureRegion h_effect_finish;
public TiledTextureRegion h_osa;
public TextureRegion h_lang;
public TextureRegion h_btn_bye;
public TextureRegion h_palka2;
//public TextureRegion mParallaxLayerBack;
//public TextureRegion mParallaxLayerMid;
//public TextureRegion mParallaxLayerFront;



public Sound m_star;
public Sound m_lang;
public Sound m_finish;


public TiledTextureRegion level_star;
public TiledTextureRegion star_lc;
public TextureRegion palec;

private static Resource instance;

	  public static Resource getInstance() {
	    if(instance == null) {
	      instance = new Resource();
	    }
	    return instance;
	  }

	  private Resource() {
		    
	  }
//=============================================================
	public void onLoadResources(Context ctx , Engine mEngine){
		   this.mEngine = mEngine;
		   
		   SoundFactory.setAssetBasePath("mfx/");
		   MusicFactory.setAssetBasePath("mfx/");
		      
		//   BitmapTextureAtlas;
		//   try
		//   {
		  //     music_ok = MusicFactory.createMusicFromAsset(mEngine.getMusicManager(), ctx,"munch.ogg");
		//   }
		//   catch (IOException e)
		//   {
	//	       e.printStackTrace();
		//   }
		   
		 //load
		
		/*
		 * 
		 * 
		 */
		 //  Texture t12 = new Texture(512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	      ///  this.paused_content = TextureRegionFactory.createFromAsset(t12, ctx, "paused.png", 0, 0);
	     //   this.mEngine.getTextureManager().loadTextures(t12);
	        
	        
		
		    Texture h1 = new Texture(512, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		    h_hamelion = TextureRegionFactory.createTiledFromAsset(h1, ctx, "h_hamelion.png", 0, 0, 4, 2); // 64x32
		    this.mEngine.getTextureManager().loadTextures(h1);
		    
		     h1 = new Texture(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		     level_star = TextureRegionFactory.createTiledFromAsset(h1, ctx, "level_star.png", 0, 0, 1, 3); // 64x32
		    this.mEngine.getTextureManager().loadTextures(h1);
		    
		     h1 = new Texture(128, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		     star_lc = TextureRegionFactory.createTiledFromAsset(h1, ctx, "star_lc.png", 0, 0, 2, 1); // 64x32
		    this.mEngine.getTextureManager().loadTextures(h1);
		    //
		    /*
		     * public TextureRegion mParallaxLayerBack;
public TextureRegion mParallaxLayerMid;
public TextureRegion mParallaxLayerFront;
		     */

	        h1 = new Texture(1024, 128,  TextureOptions.BILINEAR);
	        palec = TextureRegionFactory.createFromAsset(h1, ctx, "palec.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        

		    
		    
		    //
		    h1 = new Texture(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		    h_fly1 = TextureRegionFactory.createTiledFromAsset(h1, ctx, "h_fly1.png", 0, 0, 3, 1); // 64x32
		    this.mEngine.getTextureManager().loadTextures(h1);
		    
		    h1 = new Texture(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		    h_osa = TextureRegionFactory.createTiledFromAsset(h1, ctx, "h_osa32.png", 0, 0, 5, 1); // 64x32
		    this.mEngine.getTextureManager().loadTextures(h1);
		    
		    
		    h1 = new Texture(256, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		    h_star = TextureRegionFactory.createTiledFromAsset(h1, ctx, "h_star.png", 0, 0, 6, 1); // 64x32
		    this.mEngine.getTextureManager().loadTextures(h1);
		    
		    
		    h1 = new Texture(128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		    h_finish = TextureRegionFactory.createTiledFromAsset(h1, ctx, "h_finish.png", 0, 0, 1, 1); // 64x32
		    this.mEngine.getTextureManager().loadTextures(h1);
		    
		    h1 = new Texture(512, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		    h_effect_star = TextureRegionFactory.createTiledFromAsset(h1, ctx, "h_effect_star.png", 0, 0, 5, 1); // 64x32
		    this.mEngine.getTextureManager().loadTextures(h1);
		    
		    
		    
		    h1 = new Texture(64, 64,  TextureOptions.BILINEAR);
	        h_box = TextureRegionFactory.createFromAsset(h1, ctx, "h_box.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        
	        h1 = new Texture(256, 64,  TextureOptions.BILINEAR);
	        h_btn_bye = TextureRegionFactory.createFromAsset(h1, ctx, "h_btn_bye.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        //h_lang
	        h1 = new Texture(64, 16,  TextureOptions.BILINEAR);
	        h_lang = TextureRegionFactory.createFromAsset(h1, ctx, "h_lang.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        
	        //h_effect_finish
		    h1 = new Texture(256, 256,  TextureOptions.BILINEAR);
		    h_effect_finish = TextureRegionFactory.createFromAsset(h1, ctx, "h_effect_finish.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        
	        h1 = new Texture(64, 64,  TextureOptions.BILINEAR);
	        h_star_simple = TextureRegionFactory.createFromAsset(h1, ctx, "h_star_simple.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        
	        h1 = new Texture(128, 128,  TextureOptions.BILINEAR);
	        h_cloud = TextureRegionFactory.createFromAsset(h1, ctx, "h_cloud.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        
	      //  h1 = new Texture(128, 128,  TextureOptions.BILINEAR);
	     //   h_cloud2 = TextureRegionFactory.createFromAsset(h1, ctx, "h_cloud2.png", 0, 0);
	    //    this.mEngine.getTextureManager().loadTextures(h1);
	        
	        
	        
		    h1 = new Texture(256, 32,  TextureOptions.BILINEAR);
	        h_palka = TextureRegionFactory.createFromAsset(h1, ctx, "h_palka.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        h1 = new Texture(32, 256,  TextureOptions.BILINEAR);
	        h_palka2 = TextureRegionFactory.createFromAsset(h1, ctx, "h_palka2.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        h1 = new Texture(128, 128,  TextureOptions.BILINEAR);
	        h_kaktus1 = TextureRegionFactory.createFromAsset(h1, ctx, "h_kaktus1.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        
	        h1 = new Texture(64, 64,  TextureOptions.BILINEAR);
	        h_rope1 = TextureRegionFactory.createFromAsset(h1, ctx, "h_rope1.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        h1 = new Texture(128, 128,  TextureOptions.BILINEAR);
	        h_rope2 = TextureRegionFactory.createFromAsset(h1, ctx, "h_rope2.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
	        
	        h1 = new Texture(128, 16,  TextureOptions.BILINEAR);
	        h_rope3 = TextureRegionFactory.createFromAsset(h1, ctx, "h_rope3.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
		    
	        
	      //  h_rope_bad1;
	        h1 = new Texture(64, 64,  TextureOptions.BILINEAR);
	        h_rope_bad1 = TextureRegionFactory.createFromAsset(h1, ctx, "h_rope_bad1.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(h1);
		   /*
		    * 
		    * 
		    * 
		    */
		  // BitmapTextureAtlas a;
		   TextureRegionFactory.setAssetBasePath("gfx/");
		//   TextureAtlasFactory
		   Constants mConst = Constants.getInstance();
	        //this.mCatTexture = new Texture(128, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	      //  mCatTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mCatTexture,  ctx,  "cat3.png", 0, 0, 3, 6); //96-192/ 64x32
	          
		 //Çàãðóæàþ øðèôò BosaNova ðàçìåðîì 22 ïèêñåëÿ
		//   BitmapTextureAtlas font_BosaNova22_Texture = new BitmapTextureAtlas(512, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		 //  Font font_BosaNova22 = FontFactory.createFromAsset(font_BosaNova22_Texture, this, "fonts/bosanova.ttf", 22, true, Color.WHITE);
		    
		   //Çàãðóæàþ øðèôò BosaNova ðàçìåðîì 54 ïèêñåëÿ
		   //FontFactory.createStrokeFromAsset(pTexture, pContext, pAssetPath, pSize, pAntiAlias, pColor, pStrokeWidth, pStrokeColor, pStrokeOnly)
		 
		   FontFactory.setAssetBasePath("font/");

		   Texture font_BosaNova54_Texture = new Texture(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		   mFont_Plok = FontFactory.createFromAsset(font_BosaNova54_Texture, 
				                                    ctx, "d1.ttf", 46, true, android.graphics.Color.WHITE);
		   this.mEngine.getTextureManager().loadTextures(font_BosaNova54_Texture);  
		   
		//   this.mEngine.getTextureManager().loadTextures(font_BosaNova22_Texture, font_BosaNova54_Texture );
		//   this.mEngine.getFontManager().loadFonts(font_BosaNova22, font_BosaNova54);
		    this.mFontTexture = new Texture(512, 256, TextureOptions.BILINEAR);      
	        this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 38, true, Color.WHITE );
	        this.mEngine.getTextureManager().loadTexture(this.mFontTexture); 
	        this.mEngine.getFontManager().loadFonts(this.mFont,mFont_Plok);  

	   

	 	   Texture mBackgroundTexture = new Texture(1024, 1024,  TextureOptions.BILINEAR);
	        this.mBackgroundTextureRegion = TextureRegionFactory.createFromAsset(mBackgroundTexture, ctx, "bg0.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture);
	          
	        /*
	         * PTVKZ
	         */
	        Texture t6 = new Texture(32, 32,  TextureOptions.REPEATING);
	        this.wall1 = TextureRegionFactory.createFromAsset(t6, ctx, "ground_1.png", 0, 0);
	        
	       // this.wall1.setWidth((int) mConst.WORLD_WIDTH);
	        this.wall1.setHeight(32);
	       
	        this.mEngine.getTextureManager().loadTextures(t6);
	        
	        
	        
	     
	        
	        
	    
                
	        Texture  mBackgroundTexture2 = new Texture(512, 128,  TextureOptions.BILINEAR);
	        this.panel_heart = TextureRegionFactory.createFromAsset(mBackgroundTexture2, ctx, "panel_heart.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture2);
	        
	     //   mBackgroundTexture2 = new Texture(256, 256,  TextureOptions.BILINEAR);
	      //  this.how_play = TextureRegionFactory.createFromAsset(mBackgroundTexture2, ctx, "how_play.png", 0, 0);
	     //   this.mEngine.getTextureManager().loadTextures(mBackgroundTexture2);
	        /*
	         * 
	         * 
	         * 
	         */
	        
	        /*
			    * LIFE
			    */
			    Texture life2 = new Texture(64, 64,  TextureOptions.BILINEAR);
		        this.LIFE_PROGR_BG = TextureRegionFactory.createFromAsset(life2, ctx, "heart.png", 0, 0);
		        this.mEngine.getTextureManager().loadTextures(life2);

	        /*
	         * 
	         * BUTTONS
	         */
	        
	        mBackgroundTexture2 = new Texture(64, 64,  TextureOptions.BILINEAR);
	        this.btn_plus = TextureRegionFactory.createFromAsset(mBackgroundTexture2, ctx, "btn_plus.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture2);
	        
	  
	     //   mBackgroundTexture2 = new Texture(64, 64,  TextureOptions.BILINEAR);
	     //   this.btn_c = TextureRegionFactory.createFromAsset(mBackgroundTexture2, ctx, "btn_c.png", 0, 0);
	     //   this.mEngine.getTextureManager().loadTextures(mBackgroundTexture2);
	        
	        //btn_music
	        mBackgroundTexture2 = new Texture(64, 64,  TextureOptions.BILINEAR);
	        this.btn_music = TextureRegionFactory.createFromAsset(mBackgroundTexture2, ctx, "music.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture2);
	   
	        /*
	         * 
	         * 
	         */
	            
	        /*
	         * COIN_BG
	         * coin_bg
	         */
	        mBackgroundTexture2 = new Texture(256, 128,  TextureOptions.BILINEAR);
	        this.coin_bg = TextureRegionFactory.createFromAsset(mBackgroundTexture2, ctx, "coin_bg.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture2);
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        //board
	        Texture mBackgroundTexture12 = new Texture( 1024, 512, TextureOptions.BILINEAR);
	        this.board = TextureRegionFactory.createFromAsset(mBackgroundTexture12, ctx, "dialog_bg.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture12);
	        
	       

	        
	        
	        //TEXTS
	      //  Texture t10= new Texture(512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	      //  text = TextureRegionFactory.createTiledFromAsset(t10, ctx, "text.png", 0, 0, 1, 5); // 64x32
	       // this.mEngine.getTextureManager().loadTextures(t10);
	        
	        
	        
	        
	        
	        //BTN
	        Texture t11 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_pause = TextureRegionFactory.createFromAsset(t11, ctx, "btn_pause.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t11);
	        
	        
	        
	        Texture t14 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_reload = TextureRegionFactory.createFromAsset(t14, ctx, "btn_replay.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t14);
	        
	        
	        Texture t13 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_menu = TextureRegionFactory.createFromAsset(t13, ctx, "btn_menu.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t13);
	        
	        Texture t15 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_exit = TextureRegionFactory.createFromAsset(t15, ctx, "btn_exit.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t15);
	        
	        
	        
	        Texture t16 = new Texture(128, 128,  TextureOptions.REPEATING);
	        this.txt_start = TextureRegionFactory.createFromAsset(t16, ctx, "play_btn.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t16);
	      
	        //btn_help
	        mBackgroundTexture2 = new Texture(64, 64,  TextureOptions.BILINEAR);
	        this.btn_help = TextureRegionFactory.createFromAsset(mBackgroundTexture2, ctx, "btn_who.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(mBackgroundTexture2);
	        
	        
	        
	        
	        Texture t17 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_sound = TextureRegionFactory.createFromAsset(t17, ctx, "sound.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t17);
	        
	        
	        Texture t171 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_info = TextureRegionFactory.createFromAsset(t171, ctx, "btn_info.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t171);
	        
	        
	        Texture t172 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_bye = TextureRegionFactory.createFromAsset(t172, ctx, "btn_bye.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t172);
	        
	        Texture t173 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_no = TextureRegionFactory.createFromAsset(t173, ctx, "btn_no.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t173);
	 //       Texture t18 = new Texture(512, 128,  TextureOptions.REPEATING);
	//        this.txt_exit = TextureRegionFactory.createFromAsset(t18, ctx, "txt_exit.png", 0, 0);
	//        this.mEngine.getTextureManager().loadTextures(t18);
	  //      
	        
	        Texture t19 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_back = TextureRegionFactory.createFromAsset(t19, ctx, "prev2.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t19);
	        
	        Texture t20 = new Texture(64, 64,  TextureOptions.REPEATING);
	        this.btn_next = TextureRegionFactory.createFromAsset(t20, ctx, "next2.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(t20);
	        
	        Texture btntxt2 = new Texture(128, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        this.menu_level = TextureRegionFactory.createTiledFromAsset(btntxt2, ctx, "sel_origin.png", 0, 0,1,2);
	        this.mEngine.getTextureManager().loadTextures(btntxt2);
	        //public TextureRegion btn_back;
	        //public TiledTextureRegion menu_level;
	
	        /*
	         * ÇÀÄÍÈÉ ÔÎÍ ÄËß ÈÃÐÛ
	         */
	        
	        btntxt2 = new Texture(1024, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	        this.bg_home = TextureRegionFactory.createFromAsset(btntxt2, ctx, "b3.png", 0, 0);
	        this.mEngine.getTextureManager().loadTextures(btntxt2);
	
	
	        /*
	         * ÌÓÇÛÊÀ È ÇÂÓÊÈ
	         */

	        
	        
	        try {
	        	m_pak = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(), ctx, "pak.mp3");
		 		   } catch (IOException e) {
		 		   e.printStackTrace();
		 		   } 
	        
	        try {
	        	m_star = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(), ctx, "star.mp3");
		 		   } catch (IOException e) {
		 		   e.printStackTrace();
		 		   }
	        
	        try {
	        	m_lang = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(), ctx, "lang1.mp3");
		 		   } catch (IOException e) {
		 		   e.printStackTrace();
		 		   }
	        try {
	        	m_finish = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(), ctx, "finish.mp3");
		 		   } catch (IOException e) {
		 		   e.printStackTrace();
		 		   }
	        /*
	      
	     
	       
	        try {
	        	a_yah = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(), ctx, "a_yah.mp3");
		 		   } catch (IOException e) {
		 		   e.printStackTrace();
		 		   } 
		 		   */
	      // ÑÀÓÍËÒÐÅÊ
	        try {
	 		   mysound = MusicFactory.createMusicFromAsset(mEngine.getMusicManager(), ctx, "h_fon_music.mp3");
	 		   } catch (IOException e) {
	 		   e.printStackTrace();
	 		   }
	
	}
}

