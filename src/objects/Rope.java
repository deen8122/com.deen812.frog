package objects;

import objects.BoxObject.ObjectType;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.anddev.andengine.input.touch.TouchEvent;

import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.deen812.hroommy.Constants;

public class Rope extends BoxObject  {

	private Sprite S1;
	private Sprite S2;
	private DistanceJoint mDistnaceJoint;
	private Body B2;
	private Sprite S3;
	private Body B1;

	public Rope(final float x,final float y) {
		
		S1 = new Sprite (x, y , mRes.h_rope1);
		S2 = new Sprite (x, y+100 , mRes.h_rope2){
			@Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
                if (pSceneTouchEvent.isActionDown()     ) {
                 	 Constants.getInstance().gameScene.BoxTouched(
                    			0,
                    			0 ,
                    			B2 ,
                    			ObjectType.Box
                    			 );
                          }
               
                 if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
               	             Constants.getInstance().gameScene.BoxTouchedEnd();
                }
                
                return true;
            }; 
		};
		
		this.mScene.registerTouchArea(S2);
		
		S1.setCullingEnabled(true);
		S2.setCullingEnabled(true);
		
		
		final FixtureDef objectFixtureDef_HEAD = PhysicsFactory.createFixtureDef(50f, 0.9f, 1f);
		objectFixtureDef_HEAD.filter.groupIndex=2;
		objectFixtureDef_HEAD.filter.categoryBits = 0x08;
		objectFixtureDef_HEAD.filter.maskBits=0x07;
		this.type = BodyType.StaticBody;
		 B1 = this.CreateBody(S1, objectFixtureDef_HEAD);
		B1.getFixtureList().get(0).setSensor(true);
		 B2 = this.CreateDinamicBody(S2, objectFixtureDef_HEAD);
		
		DistanceJointDef djf = new DistanceJointDef();
  	    djf.bodyA = B1; //первое тело соединения
  	    djf.bodyB =  B2; //второе тело соединения
  	    djf.collideConnected = true; //тела не сталкиваются
  	    djf.dampingRatio  = 0.5f;
  	  	djf.frequencyHz =0.5f;

  	    //djf.localAnchorA.add(  new Vector2(0f,-0.5f)) ;		  	
  	    djf.length = 4f; //длина соединения
  	    mDistnaceJoint = (DistanceJoint)mPhysicsWorld.createJoint(djf);
  	    
  	  S3 = new Sprite (x+20, y+20 , mRes.h_rope3); 
  	  S3.setRotationCenter(0, 0);
  	// S3.setCullingEnabled(true);
  	  this.mScene.registerUpdateHandler(new IUpdateHandler() {  
			@Override  
          public void onUpdate(float arg0) { 
				  float  distanceX = B2.getPosition().x*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT-  B1.getPosition().x*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
		          float  distanceY = B2.getPosition().y*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT - B1.getPosition().y*PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

				  float shootAngle = (float)Math.atan2(distanceY,distanceX);
				  float shootPower = (float) ( Math.sqrt(distanceX*distanceX + distanceY*distanceY));
			       
			       float ang = (float) (180*shootAngle/Math.PI);
			      //    circle_text.setText(""+shootAngle);
			         // rect.setPosition(pSceneTouchEvent.getX()+20, pSceneTouchEvent.getY() +20);
			         // rect.setRotationCenter(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
			   	  S3.setRotation(ang);
			  	  S3.setWidth(shootPower);
		         }  
              @Override  
              public void reset() {   }  });
              
  	this.mScene.attachChild(S3);
  	this.mScene.attachChild(S1);
  	this.mScene.attachChild(S2);
		
		
		
		
		
	}

	@Override
	public void startTouch(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beginContact() {
		// TODO Auto-generated method stub
		
	}

}
