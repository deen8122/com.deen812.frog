package objects;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.modifier.IEntityModifier;
import org.anddev.andengine.entity.modifier.LoopEntityModifier;
import org.anddev.andengine.entity.modifier.RotationModifier;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Finish extends BoxObject {

	private AnimatedSprite S1;
	private Body B1;
	private Sprite effect;

	public Finish(float x, float y) {
	     
		S1 = new AnimatedSprite (x, y-50 , mRes.h_finish);
		this.mScene.attachChild(S1);
		//  S1.animate(100);
     	S1.setCullingEnabled(true);
     	//S1.setColor(0, 0, 0);
     
     	S1.setAlpha(0.4f);
		final FixtureDef objectFixtureDef_HEAD = PhysicsFactory.createFixtureDef(1f, 0.1f, 1f);
		objectFixtureDef_HEAD.filter.groupIndex=2;
		objectFixtureDef_HEAD.filter.categoryBits = 0x08;
		objectFixtureDef_HEAD.filter.maskBits=0x07;
		this.type = BodyType.StaticBody;
	    B1 = this.CreateBody(S1, objectFixtureDef_HEAD);
		B1.getFixtureList().get(0).setUserData("finish");
		B1.getFixtureList().get(0).setSensor(true);
		B1.setUserData(this);
		mConst.mFinish = this;
	 effect = new Sprite(S1.getX()+ S1.getWidth()/2 - mRes.h_effect_finish.getWidth()/2
				,S1.getY()+ S1.getHeight()/2 - mRes.h_effect_finish.getHeight()/2,
				mRes.h_effect_finish);
	 mScene.attachChild(effect);
	 effect.setVisible(false);
		
	}

	@Override
	public void startTouch(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Die() {
		//mPhysicsWorld.destroyBody(B1);
		//mScene.detachChild(S1);
		 effect.setVisible(true);
		
		//mScene.detachChild(S1);
		
		effect.setZIndex(S1.getZIndex()-1);
		effect.registerEntityModifier(
				(IEntityModifier) new LoopEntityModifier(new RotationModifier(6f,0f,360f)));
		
		
	}

	@Override
	public void beginContact() {
		// TODO Auto-generated method stub
		
	}

	public void setEnable() {
		S1.setAlpha(1f);
		
	}

}
