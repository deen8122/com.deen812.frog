function world(){

  
    var b2Vec2 = Box2D.Common.Math.b2Vec2;
    var b2AABB = Box2D.Collision.b2AABB;
    var b2BodyDef = Box2D.Dynamics.b2BodyDef;
    var b2Body = Box2D.Dynamics.b2Body;
    var b2FixtureDef = Box2D.Dynamics.b2FixtureDef;
    var b2Fixture = Box2D.Dynamics.b2Fixture;
    var b2World = Box2D.Dynamics.b2World;
    var b2PolygonShape = Box2D.Collision.Shapes.b2PolygonShape;
    var b2DebugDraw = Box2D.Dynamics.b2DebugDraw;
    
      
      
    var canvas = document.getElementById("canvas");
    var context = canvas.getContext("2d");
    
    var ropeJointDef;
    var worldScale =30;
    
    var world = new b2World(new b2Vec2(0, 10),true);
    
    var canvasPosition = getElementPosition(canvas);
    
     var distanceJoint;
     var isHooked=false;
       var e; 
       var hero;
    debugDraw();             
    window.setInterval(update,1000/60);
    
    createBox(900,30,320,480,b2Body.b2_staticBody,null);
    createBox(640,30,320,0,b2Body.b2_staticBody,null);
    createBox(30,480,0,240,b2Body.b2_staticBody,null);
    createBox(30,480,640,240,b2Body.b2_staticBody,null);
    // hero=addBox(320,460,20,20,b2Body.b2_dynamicBody);
     //hero.SetUserData('p');
                        var bodyDef=new b2BodyDef();
			bodyDef.position.Set(320/worldScale,460/worldScale);
			bodyDef.type=b2Body.b2_dynamicBody;
			var polygonShape=new b2PolygonShape();
			polygonShape.SetAsBox(20/2/worldScale,20/2/worldScale);
			var fixtureDef=new b2FixtureDef();
			fixtureDef.shape=polygonShape;
			fixtureDef.density=1;//плотность
			fixtureDef.restitution=0.2;//упругость
			fixtureDef.friction=0.5;//трение
                        //fixtureDef.SetUserData ("player");
                //        bodyDef.SetUserData("player");
			var body=world.CreateBody(bodyDef);
                        body.SetUserData("player");
			body.CreateFixture(fixtureDef);
                       //body.SetUserData (); 
                       hero = body;
                      body.SetUserData(); 
                       
 

    	function manageHook(){
			// as long as the hook is active, I shorten a bit joint distance
			if (isHooked) {
				// BODY MUST BE AWAKE!!!!!!
			hero.SetAwake(true);
                            if(distanceJoint.GetLength()<1){
                              distanceJoint.SetLength(1);  
                            }else 
				distanceJoint.SetLength(distanceJoint.GetLength()*0.99);
			}
		}  
      function queryCallback(fixture) {
			var touchedBody=fixture.GetBody();
                      // alert(touchedBody.GetPosition().x);
			if (touchedBody.GetType()==b2Body.b2_staticBody) {
				// if I have a body under the mouse,
                                // I create a distance joint between the hero and mouse position
				var distanceJointDef=new Box2D.Dynamics.Joints.b2DistanceJointDef();

				distanceJointDef.Initialize(hero,touchedBody,hero.GetWorldCenter(),
                                new b2Vec2(e.clientX/worldScale,e.clientY/worldScale));
				distanceJointDef.collideConnected=true;
				distanceJoint=world.CreateJoint(distanceJointDef);// as b2DistanceJoint;
				isHooked=true;
                               //  ropeJointDef = new b2RopeJointDef();
			}
			return false;
		}
	

        
        function releaseHook(e) {
			if (distanceJoint) {
				world.DestroyJoint(distanceJoint);
			}
		}
             
	
                
                
                
                
                
        
       
          function addBox(pX,pY,w,h,bodyType) {
			var bodyDef=new b2BodyDef();
			bodyDef.position.Set(pX/worldScale,pY/worldScale);
			bodyDef.type=bodyType;
			var polygonShape=new b2PolygonShape();
			polygonShape.SetAsBox(w/2/worldScale,h/2/worldScale);
			var fixtureDef=new b2FixtureDef();
			fixtureDef.shape=polygonShape;
			fixtureDef.density=1;//плотность
			fixtureDef.restitution=0.2;//упругость
			fixtureDef.friction=0.5;//трение
                        //fixtureDef.SetUserData ("player");
			var body=world.CreateBody(bodyDef);
                    //   body.SetUserData(pX);
			body.CreateFixture(fixtureDef);
                        
			return body;
		}  
    function createBox(width,height,pX,pY,type,data){
        var bodyDef = new b2BodyDef;
        bodyDef.type = type;
        bodyDef.position.Set(pX/worldScale,pY/worldScale);
        bodyDef.userData=data;
        var polygonShape = new b2PolygonShape;
        polygonShape.SetAsBox(width/2/worldScale,height/2/worldScale);
        var fixtureDef = new b2FixtureDef;
        fixtureDef.density = 1.0;
        fixtureDef.friction = 0.5;
        fixtureDef.restitution = 0.5;
        fixtureDef.shape = polygonShape;
        var body=world.CreateBody(bodyDef);
        body.CreateFixture(fixtureDef);
       // body.SetUserData ("ground");
    }
    
    
    
    
    function debugDraw(){
        var debugDraw = new b2DebugDraw();
        debugDraw.SetSprite(document.getElementById("canvas").getContext("2d"));
        debugDraw.SetDrawScale(30.0);
        debugDraw.SetFillAlpha(0.5);
        debugDraw.SetLineThickness(1.0);
        debugDraw.SetFlags(b2DebugDraw.e_shapeBit | b2DebugDraw.e_jointBit);
        world.SetDebugDraw(debugDraw);
    }
   
   
   
   

    
    
    
   document.addEventListener("mousedown",function(e2){       
       e = e2;fireHook(e);
});

    //http://js-tut.aardon.de/js-tut/tutorial/position.html
    function getElementPosition(element) {
        var elem=element, tagname="", x=0, y=0;
        while((typeof(elem) == "object") && (typeof(elem.tagName) != "undefined")) {
            y += elem.offsetTop;
            x += elem.offsetLeft;
            tagname = elem.tagName.toUpperCase();
            if(tagname == "BODY"){
                elem=0;
            }
            if(typeof(elem) == "object"){
                if(typeof(elem.offsetParent) == "object"){
                    elem = elem.offsetParent;
                }
            }
        }
        return {x: x, y: y};
    }
 
};