
function createBox(t){
    var obj = new class_object(0,0,t);
    var body = createDinamicBox2(400,300,obj);
    obj.body = body;
    ARR_PLAYER.push(obj);
}
function addBox(type){
   //  var n = $('#n'+type).html();
   //  if(Number(n)===0) return;
   //  n--;
  //   $('#n'+type).html(n);
    switch(type){
case '9': createBox('9');     break;
case '10': createBox('10');   break;
case '11': createBox('11');   break;
case '12': createBox('12');   break;
case '15': createBox('15');   break;
case '17': createBox('17');   break;
  //ARR_ENEMY.push(new class_gun_enemy(600,300,'gun1'));
case 'gun1': 
            ARR_PLAYER.push(new class_gun_enemy(100,300,'gun1'));
            break;
	    
	    
 case 'gun2': 
            ARR_PLAYER.push(new class_gun_enemy(100,300,'gun2'));
            break;
	    
case 'cat1': 
  //   var o = new class_animal(x, y,'1','enemy')
            ARR_PLAYER.push(new class_animal(200,260,'cat1','player'));  
            break;
	      
case 'enemy': 
  //   var o = new class_animal(x, y,'1','enemy')
            ARR_PLAYER.push(new class_animal(200,260,'cat1','enemy'));  
            break;
    }
    
    
 
   
      //     var body = createDinamicBox(100,200, 20, 20 ,new Array(null, 100,  0.6,  0.2, BONUS_TYPE[0]), document.getElementById("bonus_1"));
	// body.ApplyImpulse(new b2Vec2(0, 1), body.GetPosition());
}


function create_map_enemy() {
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");
    sceneWidth = context.canvas.width;
    sceneHeight= context.canvas.height;
    canvasPosition = getElementPosition(canvas); 
     world.SetGravity(new b2Vec2(0, 2));
        GAME_TYPE = 'CREATE';
        create_tower();
        debugDraw();   
        anim = window.setInterval(update,1000/120);
        document.onkeydown=function(e){changeKey((e||window.event).keyCode, 1);}
        document.onkeyup=function(e){changeKey((e||window.event).keyCode, 0);}
        document.addEventListener("mousedown", handleMouseDown2, true);
        document.addEventListener("mouseup", handleMouseUp2, true);
        m_game = new class_game();


function mouse_joint_manager(){
    if(GAME_TYPE=='GAME') return;
      if (isMouseDown && (!mouseJoint)) {
			var body = getBodyAtMouse();
			if (body) {
           
                           // console.log(body.m_fixtureList.m_userData);
                           selectedBody = body;
				var md = new b2MouseJointDef();
				md.bodyA = world.GetGroundBody();
				md.bodyB = body;
				md.target.Set(mouseX, mouseY);
				md.collideConnected = true;
				md.maxForce = 500.0 * body.GetMass();
				mouseJoint = world.CreateJoint(md);
				body.SetAwake(true);
			}else{
                           // body.translate(mouseX, mouseY);
                        }
		}
                if(mouseJoint) {
			if(isMouseDown) {
                       //     if(body.m_fixtureList.m_userData=='gun') return;
				mouseJoint.SetTarget(new b2Vec2(mouseX, mouseY));
			} else {
				world.DestroyJoint(mouseJoint);
				mouseJoint = null;
                                selectedBody=null;
			}
		}
}


function handleMouseDown2(e) {//--------------------------------
   isMouseDown = true;
  //  var body = getBodyAtMouse(); 
 //   l(body);
 switch(GAME_TYPE){
        }//-------------

        handleMouseMove2(e);
        document.addEventListener("mousemove", handleMouseMove2, true);	
}//-----------------------------------------------------------------

function handleMouseUp2(e) {
        isMouseDown = false;
        _start_shoot=false;
if(e.clientX  > 400 &&e.clientY < 90){
    for(var i=0; i < ARR_PLAYER.length;i++){
	if(ARR_PLAYER[i].getBody()===selectedBody){
	    var type = ARR_PLAYER[i].type;
	     var n = $('#n'+type).html();
              n++;
	    $('#n'+type).html(n); 
	     ARR_PLAYER.splice(i, 1); 
	}
    }
    ARR_DELETE.push(selectedBody);
}
	if(selectedBody!=null){
               selectedBody.SetFixedRotation(false);
               selectedBody=null;
        }
     
	document.removeEventListener("mousemove", handleMouseMove2, true);
	mouseX = undefined;
	mouseY = undefined;
        
}

function handleMouseMove2(e) {
    
	mouseX = (e.clientX - canvasPosition.x + toX) / worldScale;
	mouseY = (e.clientY - canvasPosition.y) / worldScale;
	if(e.clientX  > 400 &&e.clientY < 90){py=1;}else py=0;
    switch(GAME_TYPE){
                case "CREATE":break;
                case 'GAME': 
                    break;
       
        }//-------------
       
        
}
var index=0;
 function changeKey(which, to){ 
 //  console.log(b);
   if(to==1){
	switch (which){
		case 65:case 37: $.cookie('save_obj',null); break; // left
		case 87: case 38: break; // up
		case 68: case 39: break; // right
		case 83: case 40: break;// down
               
		case 49://1
                     GAME_TYPE = 'GAME';
                      for(var b = world.m_bodyList; b != null; b = b.m_next){
                      b.SetAwake(true);
                     }
                    // save_map();
                     world.SetGravity(new b2Vec2(0, 10));
                     break;
                     
                 case 50://2
                     GAME_TYPE = 'CREATE';
                     
                     world.SetGravity(new b2Vec2(0, 0));
                   
                     break;
                     
		case 51: fire(); break; //3
                case 52:    
			    if( ARR_PL_BOMB.length<=index)index=0;
			    BOMB_TYPE =ARR_PL_BOMB[index] ;
			    index++;
                            break;   //// 

	}
   }
        if(to==0){ x=0;} 
}


   
var b2Listener = Box2D.Dynamics.b2ContactListener;
var listener = new b2Listener;
listener.BeginContact = function(contact) {//----------
    var a =contact.GetFixtureA().GetBody();
    var b = contact.GetFixtureB().GetBody();
    var da = contact.GetFixtureA().GetUserData();
    var db = contact.GetFixtureB().GetUserData();
      if((da==='bomb'|| da==='enemy_bomb') && (db === SIMPLE_BOX))  break_obj(b,a);
      if((db==='bomb'|| db==='enemy_bomb') && (da === SIMPLE_BOX))  break_obj(a,b);
  
  if(da==='bomb'|| da==='enemy_bomb')  set_bonus(da,b,db);
  if(db==='bomb'|| db==='enemy_bomb' ) set_bonus(db,a,da);
	        if(da==='enemy_bomb')  {		    
                    var obj = get_obj2(a , ARR_BOMB);
		    GLOBAL_BOMB_X[obj.id] = a.GetPosition().x * worldScale;
                }
	        if(db==='enemy_bomb')  {		     
		     var obj = get_obj2(b , ARR_BOMB);
		     GLOBAL_BOMB_X[obj.id] = b.GetPosition().x * worldScale;
                }
	 //  }
               if(db==='bomb'||da==='bomb') {
		  
		        var body = get_body_by_fix('bomb',contact);
                        if(body){
                            body.GetFixtureList().SetUserData('z_bomb');
	                       var obj = get_obj2(body , ARR_BOMB);
	                       obj.boom();
			      body.GetFixtureList().SetUserData('z_bomb');
			}
                 }
                 if(db==='enemy_bomb'||da==='enemy_bomb') {
		        var body = get_body_by_fix('enemy_bomb',contact);
                        if(body){
	                       var obj = get_obj2(body , ARR_BOMB);
	                       obj.boom();
			     body.GetFixtureList().SetUserData('z_bomb');
			}
                 }
               
                 
      //---------------
      if(GAME_TYPE === 'CREATE'){ world.ClearForces();}
      
   
            
     }
     //-------------------------------------------
listener.EndContact = function(contact) {
           var a =contact.GetFixtureA().GetBody();
           var b = contact.GetFixtureB().GetBody();
           var da = contact.GetFixtureA().GetUserData();
           var db = contact.GetFixtureB().GetUserData();

     
if(GAME_TYPE === 'CREATE'){  a.SetLinearVelocity(new b2Vec2(0,0));a.SetAngularVelocity(0);b.SetLinearVelocity(new b2Vec2(0,0));b.SetAngularVelocity(0);world.ClearForces();}
}

listener.PostSolve = function(contact, impulse) {
     var a =contact.GetFixtureA().GetBody();
     var b = contact.GetFixtureB().GetBody();
     var da = contact.GetFixtureA().GetUserData();
     var db = contact.GetFixtureB().GetUserData();
  //if((da==='bomb'|| da==='enemy_bomb') && (db === SIMPLE_BOX))  break_obj(b,a, impulse);
//  if((db==='bomb'|| db==='enemy_bomb') && (da === SIMPLE_BOX))  break_obj(a,b,impulse);
    //--------------------------------------------
    var obj=null;
      var body = get_body_by_fix('animal',contact);
          if(body){
	      obj = get_obj(body);
	      obj.collision(contact,impulse);
          }                    
}
listener.PreSolve = function(contact, oldManifold) {
     var a =contact.GetFixtureA().GetBody();
     var b = contact.GetFixtureB().GetBody();
     var da = contact.GetFixtureA().GetUserData();
     var db = contact.GetFixtureB().GetUserData();
  //if((da==='bomb'|| da==='enemy_bomb') && (db === SIMPLE_BOX)) l(oldManifold)// break_obj(b,a, impulse);
 // if((db==='bomb'|| db==='enemy_bomb') && (da === SIMPLE_BOX)) l(oldManifold)// break_obj(a,b,impulse);
}
world.SetContactListener(listener);
   

//==============================================================================
function update() {

       context.clearRect(0, 0, sceneWidth,sceneHeight ); //очистить холст  
       mouse_joint_manager()

       world.Step(1/60,10,10);          
       world.DrawDebugData(); 
            for(var i=0; i < ground_arr.length;i++){  ground_arr[i].draw(context) ;}
            draw_basket(context,py);
               for(var b = world.m_bodyList; b != null; b = b.m_next){
                  if(b.GetUserData()){ 
                     // if(!b.IsAwake())continue;
                     context.save();
                     context.translate(b.GetPosition().x*worldScale,b.GetPosition().y*worldScale);
                     context.rotate(b.GetAngle()); 
                     var data = b.GetUserData();
                     if(data.image==null){
                                   context.drawImage(data,- b.GetUserData().width/2, - b.GetUserData().height/2 ); 
                                   context.restore(); 
                                   continue;
                     }
                     
                     if(data.polygon){
                         var   vec_points_obj2 = [];// data.points;
                         var s = b.GetFixtureList().GetShape();
                         var vertexCount = s.GetVertexCount();
                         var localVertices = s.GetVertices();
                         var xf = b.GetTransform();         
                         var vertices = new b2Vec2(vertexCount);
                  
                         for (i = 0; i < vertexCount; ++i)
                          {
                          vertices[i] = b2Math.MulX(xf, localVertices[i]);
                        // D.push(new _draw(vertices[i].x*worldScale , vertices[i].y*worldScale, 'green'));
                          vec_points_obj2.push(localVertices[i]);
                          }
                          
                          context.beginPath();
                          context.moveTo(vec_points_obj2[0].x*worldScale,vec_points_obj2[0].y*worldScale);
                          for(var i = 1; i < vec_points_obj2.length; i++ ){
                         context.lineTo(vec_points_obj2[i].x*worldScale,vec_points_obj2[i].y*worldScale);
                        }
                        context.closePath();
                        context.clip();
  var pat=context.createPattern(data.image,"repeat");
        context.fillStyle=pat;
context.fill();

       context.restore(); 
                     }else{ context.drawImage(data.image,-data.w/2, -data.h/2 ); }
               
                               //  context.stroke();
                                   context.restore();       
            }
          }

            for( i=0; i < ARR_PLAYER.length;i++){ ARR_PLAYER[i].update(context) ;}

    // enemy_gun2.update(context);

//--------------------------------------


   for(i=0; i < ARR_DELETE.length; i++){         
         world.DestroyBody(ARR_DELETE[i]);
         ARR_DELETE.splice(i, 1); 
            
   }//-----------------------------


         world.ClearForces();
};  
//==============================================================================    

function debugDraw(){
        var debugDraw = new b2DebugDraw();
        debugDraw.SetSprite(document.getElementById("canvas").getContext("2d"));
        debugDraw.SetDrawScale(30.0);
        debugDraw.SetFillAlpha(0.5);
        debugDraw.SetLineThickness(1.0);
        debugDraw.SetFlags(b2DebugDraw.e_shapeBit | b2DebugDraw.e_jointBit);
        world.SetDebugDraw(debugDraw);
    }
   



};


function draw_basket(context,py){
    context.drawImage(document.getElementById("del"),0,92*py,98,92, 400,0 , 98,92);
}
function create_tower(){
  create_ground(0, scene_height,scene_width,50 ,GROUND_2);

    
}//func-------------------------------------------------------------------------
function save_map2(){
    
    var name_map  = document.getElementById("name_map").value;
    alert(name_map);
    var str='';
     for(var i=0; i < ARR_PLAYER.length; i++){
         var x = ARR_PLAYER[i].getBody().GetPosition().x*worldScale;
         var y =  ARR_PLAYER[i].getBody().GetPosition().y*worldScale; 
         var a = ARR_PLAYER[i].getBody().GetAngle()*180/Math.PI;
         var type = ARR_PLAYER[i].type;
         var type2 =  ARR_PLAYER[i].type2;
 if(type2==='animal')type='animal';
             str+= ''+x+'|'+y+'|'+a+'|'+type+'|'+type2+'::';
	 
     }
    $.ajax({
    url: 'ajax/save_map.php?name_map='+name_map,
    type: 'POST',
    data: ({c:str}),
    success: function(data) {}    
});
    
}

function del_cookie(){
     var cookies = $.cookie();
for(var cookie in cookies) {
 $.removeCookie(cookie);
}
}