package objects;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.deen812.hroommy.Resource;
import com.deen812.hroommy.SceneManager.AllScenes;
import com.deen812.zombie.scene.Dialog;






public class Hrummy extends BoxObject{

	private static final String TAG = "Hrummy";
	private Body mBody;
	private AnimatedSprite mASprite;
	protected DistanceJoint mDistnaceJoint;
	private Body JoinedBody;
	protected Body bBodyBox2;
	private Sprite rect;
	protected float fff = 0.4f;
	private IUpdateHandler updateHandler;
//	private Rectangle rect2;

	/*
	 * construnctor
	 */
	public Hrummy( float x , float y) {
		final FixtureDef objectFixtureDef_HEAD = PhysicsFactory.createFixtureDef(30f, 0.6f, 0.1f);
		objectFixtureDef_HEAD.filter.categoryBits = 0x04;
		objectFixtureDef_HEAD.filter.maskBits = 0x0A;
		
		
		
		
		mASprite = new AnimatedSprite( x , y ,this.mRes.h_hamelion);
		mConst.h_HrummySprite  = mASprite;
		this.mScene.attachChild(mASprite);
		mBody = this.CreateDinamicBody(mASprite, objectFixtureDef_HEAD);
		mBody.setFixedRotation(false);
		mBody.getFixtureList().get(0).setUserData("hrummy");
		
		 rect = new Sprite(0,0,mRes.h_lang);
	   // rect.setColor(1, 0, 0);
	    this.mScene.attachChild(rect);
	    rect.setRotationCenter(0,5);
	    mASprite.animate(new long[] {2000,100,2500,100}, new int[]{0,3,0,3}, -1);
	    
		   // rect2 = new Rectangle(0,0,6,6);
		  //  rect2.setColor(0, 0, 0);
		   //// this.mScene.attachChild(rect2);
		
		final float cm2 = mConst.CAMERA_WIDTH/2;
		final float cm22 =mConst.WORLD_WIDTH - mConst.CAMERA_WIDTH/2;
		final float ch2 = mConst.CAMERA_HEIGHT/2;
		final float ch22 =mConst.WORLD_HEIGHT -mConst.CAMERA_HEIGHT/2;
	       mScene.registerUpdateHandler(new IUpdateHandler() {  
	   		@Override  
	             public void onUpdate(float arg0) { 
	   		
	   			if(mASprite.getX() > cm2 && mASprite.getX() < cm22 ){
	   				mConst.main_activity.mSmoothCamera.setCenterDirect(mASprite.getX(),mConst.main_activity.mSmoothCamera.getCenterY() );
	   			}
	   			if( mASprite.getY() >ch2 && mASprite.getY() < ch22){
	   				mConst.main_activity.mSmoothCamera.setCenterDirect(
	   						mConst.main_activity.mSmoothCamera.getCenterX(),
	   						mASprite.getY() );
	            }
 	        
	   		}  

	           @Override  
	            public void reset() {   }  
	         });
	       
	}

	@Override
	public void startTouch(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beginContact() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * создаем соединение типа DistanseJoint
	 */
	public void createJoint(final float x, final float y, final Body bBodyBox, final ObjectType fly) {
		
		if( this.JoinedBody == bBodyBox) {
			//deleteJoint();
			//this.JoinedBody = null;
			return;
			
		}
	
//	    Log.v(TAG ,"createJoint() x="+x);
	//    mScene.registerUpdateHandler();
//	    this.mConst.main_activity.
	    mScene.registerUpdateHandler(new IUpdateHandler() {

			@Override
			public void onUpdate(float pSecondsElapsed) {
				mScene.unregisterUpdateHandler(this);
				
	        	 if(mDistnaceJoint!=null) {
		        		// deleteJoint();
		        		    mScene.unregisterUpdateHandler(updateHandler);
		        		    mPhysicsWorld.destroyJoint(mDistnaceJoint);
		        		    mASprite.setCurrentTileIndex(0);
				        	mDistnaceJoint = null;
		        	 }
	        	 
		        Log.v(TAG,"createJoint run");
		        if(mDistnaceJoint==null) {
		        	mASprite.animate(new long[] {100,50,100}, new int[]{0,1,2}, 0);
		        	JoinedBody = bBodyBox;
		        	Log.v(TAG,"createJoint JoinedBody="+JoinedBody.toString());
		      	DistanceJointDef djf = new DistanceJointDef();
		  	    djf.bodyA = mBody; //первое тело соединения
		  	    djf.bodyB =  bBodyBox; //второе тело соединения
		  	    djf.collideConnected = true; //тела не сталкиваются
		  	  
		  	    if(fly == ObjectType.Fly) {
		  	    	 djf.dampingRatio  = 0.5f;
		  	  	     djf.frequencyHz =0.3f;
		  	    }else {
		  	    	 djf.dampingRatio  = 0.5f;
		  	  	     djf.frequencyHz =0.5f;
		  	    }
		  //	  Log.v(TAG,"createJoint run2");
		  	//  djf.frequencyHz =fff ;
		  	    djf.localAnchorA.add(  new Vector2(0f,-0.5f)) ;		  	
		  	    djf.length = 0.0f; //длина соединения
		  	    mDistnaceJoint = (DistanceJoint)mPhysicsWorld.createJoint(djf);
		  	 // mDistnaceJoint.
		  		StartDrawLang(bBodyBox,x,y);
		  	    //Log.v(TAG,"createJoint run3");
		        }
		        
		        
				
			}

			@Override
			public void reset() {}  });
	    
	  
		
	}
	
	
	private void StartDrawLang(final Body bBodyBox, final float x, final float y) {
		//  this.x1 = x;
		 Log.v(TAG,"StartDrawLang");
		Log.v("Hrummy","xCenter="+bBodyBox.getPosition().x+" yCenter="+bBodyBox.getPosition().y);
		 Resource.getInstance().m_lang.play();
	//	final float r =  (float) ( Math.sqrt(x*x + y*y));;
		
		updateHandler = new IUpdateHandler() {  
			@Override  
                public void onUpdate(float arg0) {
				  
			//	float dx = (float) (x -Math.acos( JoinedBody.getAngle()  ));
			//	float dy = (float) (y -  Math.acos( JoinedBody.getAngle() ));
				//rect2.setPosition(dx,dy);
				
				//float dx = JoinedBody.getPosition().x*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT+(float) (x*( JoinedBody.getAngle()  ));
				//float dy =JoinedBody.getPosition().y*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT+ (float) (x*( JoinedBody.getAngle() ));
				//rect2.setPosition(dx,dy);
				  float  distanceX = JoinedBody.getPosition().x*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT-  mASprite.getX() -mASprite.getWidth()/2;
		          float  distanceY = JoinedBody.getPosition().y*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT - mASprite.getY() - mASprite.getHeight()/2;
		          
		        //  rect2.setPosition(JoinedBody.getPosition().x*30, 
		        //		  JoinedBody.getPosition().y*30);
		          rect.setPosition(mASprite.getX() + mASprite.getWidth()/2, 
		        		           mASprite.getY()+mASprite.getHeight()/2 );
				  float shootAngle = (float)Math.atan2(distanceY,distanceX);
				  float  shootPower = (float) ( Math.sqrt(distanceX*distanceX + distanceY*distanceY));
			       
			       float ang = (float) (180*shootAngle/Math.PI);
			      //    circle_text.setText(""+shootAngle);
			         // rect.setPosition(pSceneTouchEvent.getX()+20, pSceneTouchEvent.getY() +20);
			         // rect.setRotationCenter(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
			        rect.setRotation(ang);
			        rect.setWidth(shootPower);
		         }  
              @Override  
              public void reset() {   }  
              };
		
		  this.mScene.registerUpdateHandler(updateHandler);  
		 
		  mScene.registerUpdateHandler( new IUpdateHandler() {

			@Override
			public void onUpdate(float pSecondsElapsed) {
				 rect.setVisible(true);
				
				 mScene.unregisterUpdateHandler(this);
				
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}});
		  Log.v(TAG,"StartDrawLang end");
      
	}
	

	public void animateEat() {
		// if(mDistnaceJoint==null) return;
		 mASprite.animate(new long[] {80,50,50,50,70}, new int[]{2,5,6,5,6}, 0,
				 new IAnimationListener() {
				@Override
				public void onAnimationEnd(final AnimatedSprite pAnimatedSprite) {
	
					animateEYE();
					
				}
				
			});
	}
    public void animateOI() {
    	 if(mDistnaceJoint!=null) return;
    	 mASprite.animate(new long[] {60}, new int[]{5}, 0,
				 new IAnimationListener() {
				@Override
				public void onAnimationEnd(final AnimatedSprite pAnimatedSprite) {
					animateEYE();
					
				}
				
			});
	}

    public void animateHappy() {
    	 if(mDistnaceJoint!=null) return;
    	 mASprite.animate(new long[] {2000,100,2500,100}, new int[]{4,7,4,7}, -1);
 	}
    public void animateEYE() {
    	// if(mDistnaceJoint==null) return;
    	 mASprite.animate(new long[] {2000,100,2500,100}, new int[]{0,3,0,3}, -1);
   	}
    
	public void deleteJoint() {
	
		Log.v(TAG,"deleteJoint()");
		 mASprite.animate(new long[] {2000,100,2500,100}, new int[]{0,3,0,3}, -1);
		  mScene.registerUpdateHandler(new IUpdateHandler() {  
				@Override  
	     public void onUpdate(float arg0) { 
					mScene.unregisterUpdateHandler(this);
					Log.v(TAG,"deleteJoint()  run");
					if(mDistnaceJoint!=null) {
						Log.v(TAG,"deleteJoint()  run2  mDistnaceJoint!=null");
				        	mPhysicsWorld.destroyJoint(mDistnaceJoint);
				        	Log.v(TAG,"deleteJoint()  run3  mDistnaceJoint!=null");
				        	mDistnaceJoint = null;
				        	JoinedBody = null;
				  	   // mDistnaceJoint = (DistanceJoint)mPhysicsWorld.createJoint(djf);      
				        }
			         }  
	   @Override  
	    public void reset() {   }  
	 });
		  
			mASprite.setCurrentTileIndex(0);
			mScene.unregisterUpdateHandler(updateHandler);
			rect.setVisible(false);
			
			
	   
		 /*
		 this.mConst.main_activity.runOnUpdateThread(new Runnable() {

		        @Override
		        public void run() {
		        Log.v(TAG,"deleteJoint run");
		        if(mDistnaceJoint!=null) {
		        	mPhysicsWorld.destroyJoint(mDistnaceJoint);
		        	mDistnaceJoint = null;
		  	   // mDistnaceJoint = (DistanceJoint)mPhysicsWorld.createJoint(djf);      
		        }
		        }
		    });
		    */
		    
		
	}

	@Override
	public void Die() {
		Log.v("Hrummy","Die()");
		
	}

	public void deleteJoint2() {
		Log.v(TAG,"deleteJoint()2");
		mASprite.setCurrentTileIndex(0);
		mScene.unregisterUpdateHandler(updateHandler);
		rect.setVisible(false);
		
		if(mDistnaceJoint!=null) {
	        	mPhysicsWorld.destroyJoint(mDistnaceJoint);
	        	mDistnaceJoint = null;  
	        	JoinedBody = null;
	        }
		
	}
	
	

	

}
