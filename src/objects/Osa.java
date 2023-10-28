package objects;

import objects.BoxObject.ObjectType;
import objects.Fly.FlyType;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.input.touch.TouchEvent;

import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.deen812.hroommy.Constants;

public class Osa extends BoxObject{


	private AnimatedSprite mASprite;
	private Body mBody;
	private IUpdateHandler updateHandler;
	private int Xfly;
	private int Yfly;
	private boolean f_block =false;
	public Osa(final float x,final float y ) {
		
		final FixtureDef objectFixtureDef_HEAD = PhysicsFactory.createFixtureDef(150f, 0.9f, 0.1f);
		//objectFixtureDef_HEAD.filter.categoryBits = 0x04;
	//	objectFixtureDef_HEAD.filter.maskBits = 0x0A;
		objectFixtureDef_HEAD.filter.groupIndex=2;
		objectFixtureDef_HEAD.filter.categoryBits = 0x08;
		objectFixtureDef_HEAD.filter.maskBits=0x07;
		
		mASprite = new AnimatedSprite( x , y ,this.mRes.h_osa.clone()){
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
	//	this.mScene.registerTouchArea(mASprite);
		
		mASprite.animate(new long[] { 50,50,50,50}, new int[]{0,1,2,1}, -1);
		mASprite.setCullingEnabled(true);
		//mASprite.animate(100);
		//mBody = this.CreateDinamicBody(mASprite, objectFixtureDef_HEAD);
		mBody = this.CreateCircleBody(mASprite.getX(), mASprite.getY()+40, objectFixtureDef_HEAD, 12);
		mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mASprite, mBody, true, true));
		//mBody = this.CreateKinematicBody(mASprite, objectFixtureDef_HEAD);
		mBody.setFixedRotation(true);
		mBody.getFixtureList().get(0).setUserData("kaktus");
		mBody.setUserData(this);
		mBody.resetMassData();//.setMassData(0);
		//body.applyForce(new Vector2(0,-SensorManager.GRAVITY_EARTH), new Vector2(body.getWorldCenter()));
		mBody.applyLinearImpulse(new Vector2(-3,-10), new Vector2(mBody.getWorldCenter()));	
		  
		Xfly = 1;
		Yfly = 1;
		/*
		mScene.registerUpdateHandler(new TimerHandler(1f,
					new ITimerCallback() {
						@Override
						public void onTimePassed(TimerHandler pTimerHandler) {
								if(mConst.h_HrummySprite.getX() <  mASprite.getX()){
					mASprite.getTextureRegion().setFlippedHorizontal(false);//.setFlippedVertical(false);
					Xfly = -1;
				}else {Xfly = 1;
				mASprite.getTextureRegion().setFlippedHorizontal(true);}

				if(mConst.h_HrummySprite.getY() <  mASprite.getY()){
					Yfly = -1;
				}else Yfly = 1;
							//mScene.unregisterUpdateHandler(pTimerHandler);

						}
					}));
				*/
		
		updateHandler = new IUpdateHandler() {  
			@Override  
     public void onUpdate(float arg0) { 
				
				if(mConst.h_HrummySprite.getX() <  mASprite.getX()){
					mASprite.getTextureRegion().setFlippedHorizontal(false);//.setFlippedVertical(false);
					Xfly = -1;
				}else {Xfly = 1;
				mASprite.getTextureRegion().setFlippedHorizontal(true);}

				if(mConst.h_HrummySprite.getY() <  mASprite.getY()){
					Yfly = -1;
				}else Yfly = 1;
				
				mBody.applyLinearImpulse(new Vector2(Xfly*5,-SensorManager.GRAVITY_EARTH + 5*Yfly), new Vector2(mBody.getWorldCenter()));	
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
	
		if( f_block == true) return;
		f_block =true;
		mASprite.animate(new long[] { 50,50,50,50}, new int[]{0,3,4,3}, 0, new IAnimationListener() {

			@Override
			public void onAnimationEnd(final AnimatedSprite pAnimatedSprite) {
				f_block = false;
				mASprite.animate(new long[] { 50,50,50,50}, new int[]{0,1,2,1}, -1);
				
			}
			
		});
}
	
	
}//----
