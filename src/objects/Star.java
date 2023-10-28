package objects;

import objects.BoxObject.ObjectType;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.deen812.hroommy.Constants;

public class Star  extends BoxObject {

	private AnimatedSprite S1;
	private Body B1;

	public Star(float x, float y) {
	     
		S1 = new AnimatedSprite (x, y , mRes.h_star);
		this.mScene.attachChild(S1);
		//S1.animate(100);
     	S1.setCullingEnabled(true);
		final FixtureDef objectFixtureDef_HEAD = PhysicsFactory.createFixtureDef(1f, 0.1f, 1f);
		objectFixtureDef_HEAD.filter.groupIndex=2;
		objectFixtureDef_HEAD.filter.categoryBits = 0x08;
		objectFixtureDef_HEAD.filter.maskBits=0x07;
		this.type = BodyType.StaticBody;
	    B1 = this.CreateBody(S1, objectFixtureDef_HEAD);
		B1.getFixtureList().get(0).setUserData("star");
		B1.getFixtureList().get(0).setSensor(true);
		B1.setUserData(this);
		
	}

	@Override
	public void startTouch(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Die() {
		mPhysicsWorld.destroyBody(B1);
		
		final AnimatedSprite effect = new AnimatedSprite(S1.getX()-20,S1.getY()-5,mRes.h_effect_star);
		mScene.detachChild(S1);
		mScene.attachChild(effect);
		effect.animate(80, 0, new IAnimationListener() {

			@Override
			public void onAnimationEnd(final AnimatedSprite pAnimatedSprite) {
				mScene.registerUpdateHandler(new IUpdateHandler() {  
			   		@Override  
			             public void onUpdate(float arg0) { 
			   			mScene.detachChild(pAnimatedSprite);
			   			mScene.unregisterUpdateHandler(this);
			   		}

					@Override
					public void reset() {}  
			   		
			});
				
			}
			
		});
		
	}

	@Override
	public void beginContact() {
		// TODO Auto-generated method stub
		
	}

}
