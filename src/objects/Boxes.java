package objects;

import objects.BoxObject.ObjectType;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.util.Log;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.deen812.hroommy.Constants;
import com.deen812.hroommy.Resource;

public class Boxes extends BoxObject {

	public enum BoxType {
		  Box1,
		  Box2,
		  Box3,
		  Box4,
		  Palka, Rope, Palka2, Kaktus1, Star, Finish, Fly1, Platform, Osa, Rope_Bad, Palka2Rot
		  
	}


	private Sprite BoxSprite;
	private Sprite BoxSprite2;
	private Body mBoxBody;
	private float xCenter;
	private float yCenter;
	final FixtureDef objectFixtureDef_HEAD;
	
	
	public Boxes(float x , float y ,BoxType typeBox) {
		
		type = BodyType.StaticBody;
		objectFixtureDef_HEAD = PhysicsFactory.createFixtureDef(50f, 0.1f, 1f);
		
		switch(typeBox){
		//
		case Rope:new Rope(x, y);   break;
		case Rope_Bad:new Rope_Bad(x, y);   break;
		case Osa:new Osa(x, y);   break;
		
		case Fly1:new Fly(x, y, null);   break;
		// звезды
		case Star:new Star(x, y);   break;
		
		//
		case Finish:new Finish(x, y);   break;
		
		// кактус
		case Kaktus1:  createKaktus(x,y);break;
		// ящик
		case Palka:createPalka(x,y, Resource.getInstance().h_box);break;
		// палка	
		case Palka2:createPalka(x,y, Resource.getInstance().h_palka);break;
		
		case Palka2Rot:createPalka(x,y, Resource.getInstance().h_palka2);break;
			
			
		default:
			
			 BoxSprite  = new Sprite(x , y , mRes.h_cloud) {
					@Override
		            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		                if (pSceneTouchEvent.isActionDown()     ) {
		               	 this.setScale((float) 1.2);                               
		              	Log.v("Boxes","onAreaTouched"); 
		             // 	/*
		               	 Constants.getInstance().gameScene.BoxTouched(
		               			 0,
		               			 0 ,
		               			 mBoxBody,
		               			ObjectType.Box);
		             	Log.v("Box","xCenter="+xCenter+" yCenter="+yCenter);
		               			//*/
		               	 /*
		           	 Constants.getInstance().gameScene.BoxTouched(
		           			mBoxBody.getPosition().x*30,
		           			mBoxBody.getPosition().y*30,
		           			 mBoxBody,
		           			ObjectType.Box);
		           			 */
		                }
		               
		                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		               	 this.setScale((float) 1.0);
		               	Constants.getInstance().gameScene.BoxTouchedEnd();
		                }
		                
		                return true;
		            }; 
				};
				
				this.xCenter = (x + BoxSprite.getWidth()/2 )/30;
				this.yCenter = (y + BoxSprite.getHeight()/2)/30;
			
				
				this.mScene.attachChild(BoxSprite);
				this.mScene.registerTouchArea(BoxSprite);
				BoxSprite.setCullingEnabled(true);
				
				objectFixtureDef_HEAD.filter.categoryBits = 0x04;
				objectFixtureDef_HEAD.filter.maskBits = 0x0A;
			       		        
				mBoxBody = this.CreateBody(BoxSprite, objectFixtureDef_HEAD);				 
				mBoxBody.getFixtureList().get(0).setUserData("box");
				mBoxBody.getFixtureList().get(0).setSensor(true);
				
				    //Rectangle   rect2 = new Rectangle(0,0,2,2);
				    //rect2.setColor(0, 0, 0);
				   // this.mScene.attachChild(rect2);
				 //   rect2.setPosition(mBoxBody.getPosition().x*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 
				    //		mBoxBody.getPosition().y*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
			
			break;
		}
		

		
		    
		
	}


	private void createPalka2(float x, float y, TextureRegion h_palka) {
		
		createPalka( x, y,  h_palka);
		//mBoxBody.setTransform(mBoxBody.getWorldCenter(), (float) (3.14/2));
	}


	private void createKaktus(float x, float y) {
		BoxSprite2  = new Sprite(x , y , Resource.getInstance().h_kaktus1) {
			@Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                if (pSceneTouchEvent.isActionDown()     ) {
              	                  Constants.getInstance().gameScene.BoxTouched(0,0, mBoxBody,ObjectType.Box);
                          }
               
                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
               	             Constants.getInstance().gameScene.BoxTouchedEnd();
                }
                
                return true;
            }; 
		};
		this.mScene.attachChild(BoxSprite2);
	//	this.mScene.registerTouchArea(BoxSprite2);
		BoxSprite2.setCullingEnabled(true);
		objectFixtureDef_HEAD.density =250f;
	
		objectFixtureDef_HEAD.filter.groupIndex=2;
		objectFixtureDef_HEAD.filter.categoryBits = 0x08;
		objectFixtureDef_HEAD.filter.maskBits=0x07;
		
		mBoxBody = this.CreateDinamicBody(BoxSprite2, objectFixtureDef_HEAD);
		mBoxBody.setFixedRotation(true);
		mBoxBody.getFixtureList().get(0).setUserData("kaktus");

		
	}


	private void createPalka(final float x , final float y, TextureRegion h_box) {
		BoxSprite2  = new Sprite(x , y , h_box) {
			@Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                if (pSceneTouchEvent.isActionDown()     ) {
              	                  Constants.getInstance().gameScene.BoxTouched(0,0, mBoxBody,ObjectType.Box);
                          }
               
                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
               	             Constants.getInstance().gameScene.BoxTouchedEnd();
                }
                
                return true;
            }; 
		};
		this.mScene.attachChild(BoxSprite2);
		this.mScene.registerTouchArea(BoxSprite2);
		BoxSprite2.setCullingEnabled(true);
		objectFixtureDef_HEAD.density =200f;
		objectFixtureDef_HEAD.filter.groupIndex=2;
		objectFixtureDef_HEAD.filter.categoryBits = 0x08;
		objectFixtureDef_HEAD.filter.maskBits=0x07;
		
		mBoxBody = this.CreateDinamicBody(BoxSprite2, objectFixtureDef_HEAD);				 
		mBoxBody.getFixtureList().get(0).setUserData("box");

		
	}


	@Override
	public void startTouch(float x, float y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beginContact() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void Die() {
		// TODO Auto-generated method stub
		
	}
}
