package objects;

import objects.BoxObject.ObjectType;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.input.touch.TouchEvent;

import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.deen812.hroommy.Constants;

public class LadyBag  extends BoxObject{

	public enum FlyType {
		  Fly1,
		  Fly2,
		  
	}

	private AnimatedSprite mASprite;
	private Body mBody;
	private IUpdateHandler updateHandler;
	public LadyBag(final float x,final float y , FlyType mFlyType) {
		
		final FixtureDef objectFixtureDef_HEAD = PhysicsFactory.createFixtureDef(100f, 0.9f, 0.1f);
		//objectFixtureDef_HEAD.filter.categoryBits = 0x04;
	//	objectFixtureDef_HEAD.filter.maskBits = 0x0A;
		objectFixtureDef_HEAD.filter.groupIndex=2;
		objectFixtureDef_HEAD.filter.categoryBits = 0x08;
		objectFixtureDef_HEAD.filter.maskBits=0x07;
		
		mASprite = new AnimatedSprite( x , y ,this.mRes.h_fly1.clone()){
			@Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                if (pSceneTouchEvent.isActionDown()     ) {
               	 this.setScale((float) 1.2);                               
               	       
               	 Constants.getInstance().gameScene.BoxTouched(
               			mBody.getPosition().x,
               			mBody.getPosition().y ,
               			mBody ,
               			ObjectType.Fly
               			 );
               	mScene.unregisterUpdateHandler(updateHandler);
               	Log.v("Fly","onAreaTouched");
                }
                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
               	 this.setScale((float) 1.0);
                    // 	Constants.getInstance().gameScene.BoxTouchedEnd();
                }
                
                return true;
            }; 
		};
		this.mScene.attachChild(mASprite);
		this.mScene.registerTouchArea(mASprite);
		
		
		mASprite.animate(50);
		//mBody = this.CreateDinamicBody(mASprite, objectFixtureDef_HEAD);
		mBody = this.CreateCircleBody(mASprite.getX(), mASprite.getY()+40, objectFixtureDef_HEAD, 12);
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mASprite, mBody, true, true));
		//mBody = this.CreateKinematicBody(mASprite, objectFixtureDef_HEAD);
		mBody.setFixedRotation(true);
		mBody.getFixtureList().get(0).setUserData("fly");
		mBody.setUserData(this);
		mBody.resetMassData();//.setMassData(0);
		//body.applyForce(new Vector2(0,-SensorManager.GRAVITY_EARTH), new Vector2(body.getWorldCenter()));
		mBody.applyLinearImpulse(new Vector2(-3,-10), new Vector2(mBody.getWorldCenter()));	
		updateHandler = new IUpdateHandler() {  
			@Override  
     public void onUpdate(float arg0) { 
				mBody.applyLinearImpulse(new Vector2(0,-SensorManager.GRAVITY_EARTH), new Vector2(mBody.getWorldCenter()));	
			//	mBody.setTransform(x/30, y/30, 0);
		         }  
   @Override  
    public void reset() {   }  
 };
		
		  this.mScene.registerUpdateHandler(updateHandler);  
		
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
		Log.v("Fly","Die()");
		mScene.unregisterUpdateHandler(updateHandler);
		mScene.unregisterTouchArea(mASprite);
   		
			mPhysicsWorld.destroyBody(mBody);
			mScene.detachChild(mASprite);
	//	 this.mConst.mEngine.registerUpdateHandler( new PostRunnable())
		/*
	    this.mConst.main_activity.runOnUpdateThread(new Runnable() {

	        @Override
	        public void run() {
	        	mPhysicsWorld.destroyBody(mBody);
	        	mScene.detachChild(mASprite);
	        	
	        	
	        }
	    });
	   
	       mScene.registerUpdateHandler(new IUpdateHandler() {  
	   		@Override  
	             public void onUpdate(float arg0) { 
	   			Log.v("Fly","Die()   2");
	   			
	   			
	   			mScene.unregisterTouchArea(mASprite);
	   		
	   			mPhysicsWorld.destroyBody(mBody);
	   			mScene.detachChild(mASprite);
	   			
	   			mScene.unregisterUpdateHandler(this);
	   			
	   		}

			@Override
			public void reset() {}  
	   		
	});
 */
}
	
	
}//----
