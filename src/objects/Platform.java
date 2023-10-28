package objects;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Platform extends BoxObject {

	private Sprite S1;
	private Body B1;

	public Platform(float x, float y , int width,int height) {
	     
		mRes.wall1.setWidth(width);	
		S1 = new Sprite (x, y , mRes.wall1.clone());
		//S1.getTextureRegion().setWidth(width);
		this.mScene.attachChild(S1);
		//  S1.animate(100);
     	S1.setCullingEnabled(true);
		final FixtureDef objectFixtureDef_HEAD = PhysicsFactory.createFixtureDef(1f, 0.1f, 1f);
		   objectFixtureDef_HEAD.filter.categoryBits = 0x02;
		   objectFixtureDef_HEAD.filter.maskBits = -1;
		this.type = BodyType.StaticBody;
	    B1 = this.CreateBody(S1, objectFixtureDef_HEAD);
		B1.getFixtureList().get(0).setUserData("ground");
		//B1.getFixtureList().get(0).setSensor(true);
		//B1.setUserData(this);
		
	}

	@Override
	public void startTouch(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Die() {
		mPhysicsWorld.destroyBody(B1);
		mScene.detachChild(S1);
		
		
	}

	@Override
	public void beginContact() {
		// TODO Auto-generated method stub
		
	}

}
