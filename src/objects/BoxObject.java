package objects;


import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.ITouchArea;
import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.MathUtils;



import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.LineJoint;
import com.badlogic.gdx.physics.box2d.joints.LineJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.deen812.hroommy.Constants;
import com.deen812.hroommy.Resource;
import com.deen812.hroommy.main;

public abstract class BoxObject {
	public enum ObjectType {
		  Fly, Box,
		  
	}
	
	protected Constants mConst;
	protected Scene mScene;
	private main game;
	PhysicsWorld mPhysicsWorld;
	protected BodyType type = BodyType.DynamicBody;
	protected Resource mRes;
	public BoxObject() {

		this.mConst = Constants.getInstance();
		this.mScene = Constants.getInstance().CURRENT_SCENE;
		this.game = this.mConst.main_activity;
		this.mPhysicsWorld = this.mConst.mPhysicsWorld;
		this.mRes = Resource.getInstance();
		
	   
 


	}
	protected Body CreateBody(IShape head_aspr, FixtureDef objectFixtureDef_HEAD) {
		
		
			
	
		Body body_head = PhysicsFactory.createBoxBody(mPhysicsWorld, 
				 head_aspr, 
				 this.type, 
				 objectFixtureDef_HEAD);
		 mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(head_aspr, body_head, true, false));
		 //body_head.setUserData(this);
		// body_head.getFixtureList().get(0).setUserData("box");
		 return body_head;
	}
	
	protected Body CreateDinamicBody(IShape head_aspr, FixtureDef objectFixtureDef_HEAD) {
		
		
		
		
		Body body_head = PhysicsFactory.createBoxBody(mPhysicsWorld, 
				 head_aspr, 
				 BodyType.DynamicBody, 
				 objectFixtureDef_HEAD);
		 mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(head_aspr, body_head, true, true));
		 body_head.setUserData(this);
		// body_head.getFixtureList().get(0).setUserData("box");
		 return body_head;
	}
	
	
	
	
	
	
	
	
	
	protected Body CreateKinematicBody(AnimatedSprite head_aspr,
			FixtureDef objectFixtureDef_HEAD) {
		
		Body body_head = PhysicsFactory.createBoxBody(mPhysicsWorld, 
				 head_aspr, 
				 BodyType.KinematicBody, 
				 objectFixtureDef_HEAD);
		 mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(head_aspr, body_head, true, true));
		 body_head.setUserData(this);
		// body_head.getFixtureList().get(0).setUserData("box");
		 return body_head;
	}

	
	
	
	protected void CreateTouch(Entity head_aspr) {
		head_aspr  = new Entity() 
			 {
		   	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X, float Y)  {	 
		     if (pSceneTouchEvent.isActionDown()     ) {

		     }
		     if (pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel()) {
		
		    }
		                
		     return true;
		    } 
		     };

		     this.mScene.registerTouchArea((ITouchArea) head_aspr);
		 };
	
	
	protected Body CreateCircleBody(IShape spr, FixtureDef objectFixtureDef_HEAD , float R){
		 final BodyDef circleBodyDef = new BodyDef(); 
 			circleBodyDef.type =  BodyType.DynamicBody;              
 			circleBodyDef.position.x = spr.getX() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
 			circleBodyDef.position.y = spr.getY() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
 			//circleBodyDef.linearDamping = 0.7f;
 			//circleBodyDef.angularDamping = 0.8f;
 			circleBodyDef.angle = MathUtils.degToRad(0);
 			  final Body circleBody = mPhysicsWorld.createBody(circleBodyDef);
 			  final CircleShape circlePoly2 = new CircleShape();  			
 			  final float radius2 = R /PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
 			  circlePoly2.setRadius(radius2);
 			  objectFixtureDef_HEAD.shape = circlePoly2;
 			  circleBody.createFixture(objectFixtureDef_HEAD);

 			  circlePoly2.dispose();
 			 mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(spr, circleBody, true, true));
		return circleBody;
		
	}
	
	protected Body CreateCircleBody(float x , float y, FixtureDef objectFixtureDef_HEAD , float R){
		 final BodyDef circleBodyDef = new BodyDef(); 
			circleBodyDef.type =  BodyType.DynamicBody;              
			circleBodyDef.position.x = x / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
			circleBodyDef.position.y = y / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
			//circleBodyDef.linearDamping = 0.7f;
			//circleBodyDef.angularDamping = 0.8f;
			circleBodyDef.angle = MathUtils.degToRad(0);
			  final Body circleBody = mPhysicsWorld.createBody(circleBodyDef);
			  final CircleShape circlePoly2 = new CircleShape();  			
			  final float radius2 = R /PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
			  circlePoly2.setRadius(radius2);
			  objectFixtureDef_HEAD.shape = circlePoly2;
			  circleBody.createFixture(objectFixtureDef_HEAD);

			  circlePoly2.dispose();
			// mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(spr, circleBody, true, true));
		return circleBody;
		
	}
	
	public abstract void startTouch(float x, float y);
	public abstract void Die();
	public void Contact(int power){}
	public abstract void beginContact();











	protected LineJoint createLineJoint(Body bdLeg12, Body bdLeg22,
			Vector2 vector2, Vector2 vector22 ) {
		//	 bdLeg.setFixedRotation(true);
		 //КРЕПИМ НОГИ К ТЕЛУ
		LineJointDef revoluteJointDef = new LineJointDef();
	       revoluteJointDef.bodyA =bdLeg12; //первое тело соединения
	       revoluteJointDef.bodyB = bdLeg22; //второе тело соединения
	       revoluteJointDef.collideConnected = false; //тела не сталкиваются
	      // revoluteJointDef.motorSpeed = -5f;
	      /// revoluteJointDef.maxMotorTorque=10f;
	      // revoluteJointDef.enableLimit = true;
	      // revoluteJointDef.upperAngle = 0.2f;
	       //revoluteJointDef.lowerAngle = -0.2f;
	       revoluteJointDef.localAnchorA.add( vector2 );//.obtain(0,0);
	       revoluteJointDef.localAnchorB.add( vector22);

	       revoluteJointDef.enableMotor = false;
	       return (LineJoint)  mPhysicsWorld.createJoint(revoluteJointDef);

	}

protected RevoluteJoint createRevoluteJoint(Body bdLeg12, Body bdLeg22,
		Vector2 vector2, Vector2 vector22 ) {
	//	 bdLeg.setFixedRotation(true);
	 //КРЕПИМ НОГИ К ТЕЛУ
	RevoluteJointDef revoluteJointDef = new RevoluteJointDef();
       revoluteJointDef.bodyA =bdLeg12; //первое тело соединения
       revoluteJointDef.bodyB = bdLeg22; //второе тело соединения
       revoluteJointDef.collideConnected = false; //тела не сталкиваются
      // revoluteJointDef.motorSpeed = -5f;
      /// revoluteJointDef.maxMotorTorque=10f;
      // revoluteJointDef.enableLimit = true;
      // revoluteJointDef.upperAngle = 0.2f;
       //revoluteJointDef.lowerAngle = -0.2f;
       revoluteJointDef.localAnchorA.add( vector2 );//.obtain(0,0);
       revoluteJointDef.localAnchorB.add( vector22);

       revoluteJointDef.enableMotor = false;
       return (RevoluteJoint)  mPhysicsWorld.createJoint(revoluteJointDef);

}






}









