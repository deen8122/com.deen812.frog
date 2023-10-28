function save_map(){
    var str='';
     for(var i=0; i < ARR_PLAYER.length; i++){
         var x = ARR_PLAYER[i].getBody().GetPosition().x*worldScale;
         var y =  ARR_PLAYER[i].getBody().GetPosition().y*worldScale; 
         var a = ARR_PLAYER[i].getBody().GetAngle()*180/Math.PI;
         var type = ARR_PLAYER[i].type;
         var type2 =  ARR_PLAYER[i].type2;
            $.cookie('obj_x'+i,x);
            $.cookie('obj_y'+i,y);
            $.cookie('obj_angle'+i,a);
            $.cookie('obj_type'+i, type ); 
            $.cookie('obj_type2'+i,type2);
             str+= ':'+x+'|'+y+'|'+a+'|'+type+'|'+type2+':';
	     l('t='+type+type2);
     }
      $.cookie('obj_animate_n',i);
      l('saved='+i);
     $.cookie('saved_map',1);
    $.ajax({
    url: 'ajax/save_map.php',
    type: 'POST',
    data: ({c:str}),
    success: function(data) {}    
});
    /*
    $.cookie('save_obj','1');
 
     for(var i=0; i < object_arr.length; i++){

            $.cookie('obj_x'+i, object_arr[i].getBody().GetPosition().x*worldScale);
            $.cookie('obj_y'+i, object_arr[i].getBody().GetPosition().y*worldScale);
            $.cookie('obj_angle'+i, object_arr[i].getBody().GetAngle()*180/Math.PI);
            $.cookie('obj_type'+i, object_arr[i].type); 
            if(object_arr[i] instanceof class_gun){
                $.cookie('obj_to'+i, object_arr[i].to); 
            }
            //l('angle save='+object_arr[i].getBody().GetAngle()*180/Math.PI);
         }
         
          $.cookie('obj_n', i);
         //  l('сохранено obj_n:'+i);
 for(i=0; i < object_animate.length; i++){
       $.cookie('obj_x_a'+i, object_animate[i].getBody().GetPosition().x*worldScale);
            $.cookie('obj_y_a'+i, object_animate[i].getBody().GetPosition().y*worldScale);
            $.cookie('obj_angle_a'+i, object_animate[i].getBody().GetAngle()*180/Math.PI);
            $.cookie('obj_type_a'+i, object_animate[i].type); 
            $.cookie('obj_animal_a'+i, object_animate[i].animal); 
             $.cookie('obj_id_a'+i, object_animate[i].id); 
            //l('angle save='+object_arr[i].getBody().GetAngle()*180/Math.PI);
         }
          $.cookie('obj_animate_n', i);
          
for(i=0; i < enemy_gun.length; i++){
            $.cookie('enemy_x'+i, enemy_gun[i].getBody().GetPosition().x*worldScale);
            $.cookie('enemy_y'+i, enemy_gun[i].getBody().GetPosition().y*worldScale);
            $.cookie('enemy_angle'+i, enemy_gun[i].getBody().GetAngle()*180/Math.PI);
            
            $.cookie('enemy_type2'+i, enemy_gun[i].type2); 
          //  $.cookie('obj_animal_a'+i, enemy_gun[i].animal); 
           //  $.cookie('obj_id_a'+i, enemy_gun[i].id); 
            //l('angle save='+object_arr[i].getBody().GetAngle()*180/Math.PI);
         }
          $.cookie('enemy_n', i);
 */
}
 //----------------------------

function l2(t){
//var s =$('#deb').html(); 
//$('#deb').html(t+'<br>'+s);
}

function getBodyAtMouse() {
	mousePVec = new b2Vec2(mouseX, mouseY);
	var aabb = new b2AABB();
	aabb.lowerBound.Set(mouseX - 0.001, mouseY - 0.001);
	aabb.upperBound.Set(mouseX + 0.001, mouseY + 0.001);
	function getBodyCB(fixture) {
         //  console.log(fixture);
		if(fixture.GetBody().GetType() != b2Body.b2_staticBody) {
			if(fixture.GetShape().TestPoint(fixture.GetBody().GetTransform(), mousePVec)) {
				 selectedBody = fixture.GetBody();
                                 selectedBody.SetFixedRotation(true);
                               //  selectedBody.SetFixedRotation(false);
				return true;
			}
		}
		return false;
	}
  
	world.QueryAABB(getBodyCB, aabb);
	return selectedBody;
}

    
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
 
function getCursorPosition(evt, canvas){
  var x, y;
  if (evt.pageX != undefined && evt.pageY != undefined) {
    x = evt.pageX; 
    y = evt.pageY;
  } else { 
    x = evt.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
    y = evt.clientY + document.body.scrollTop  + document.documentElement.scrollTop;
  }
  x = x - canvas.offsetLeft; 
  y = y - canvas.offsetTop;
  return {x:x ,y:y};
}


function img_res(path)
{
	var i = new Image();
	i.src = 'image/'+path;
	//console.log(i);
	return i;
}

function l(txt){
    console.log(txt);
}

function get_obj_gun(body){
    for( var i=0; i < ARR_PLAYER.length;i++){
	if(ARR_PLAYER[i]===null) continue;
	if( body === ARR_PLAYER[i].body1 )
	return ARR_PLAYER[i];
	} 
}

function get_obj(body){
    for( var i=0; i < ARR_PLAYER.length;i++){
	if(ARR_PLAYER[i]===null) continue;
	if( body === ARR_PLAYER[i].getBody() )
	return ARR_PLAYER[i];
	} 
}


function get_body_by_fix(fix,contact){
    if(fix === contact.GetFixtureA().GetUserData()) return contact.GetFixtureA().GetBody();
    if(fix === contact.GetFixtureB().GetUserData()) return contact.GetFixtureB().GetBody();
    else return false;
}

function get_obj2(body,ARR_BOMB2){
    for( var i=0; i < ARR_BOMB2.length;i++){ 
	if(ARR_BOMB2[i]===null) continue;
	if( body === ARR_BOMB2[i].getBody() )
	return ARR_BOMB2[i];
	} 
}

// использование Math.round() даст неравномерное распределение!
function getRandomInt(min, max)
{
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function rect_gun(max){
    this.x = 0;
    this.y = 0;
    this.max =max;
    this.tek = 0;
    this.w = 50;
    this.h=10;
    this.c = 2*max/100;
    this.m = this.c;
    this.p = 1/this.c;
    this.c=0;
}
rect_gun.prototype.draw=function(context){
   if( this.c <50) this.c+=this.p;
   else {
       context.beginPath();
       context.fillStyle = 'yellow';
       context.fillRect(this.x - 10,this.y-50,  this.c,  this.h);
        context.stroke();
       return;
   }
 context.beginPath();
 context.lineWidth="2";
 context.strokeStyle="blue";
 context.rect(this.x-10,this.y-50,  this.w, this.h);
 context.stroke();
  context.beginPath();
 context.lineWidth="2";
 context.fillStyle = 'red';
 context.fillRect(this.x - 10,this.y-50,  this.c,  this.h);
 context.stroke();
           //    var str =''+parseInt(this.c*2,10);
           //    context.font = '14px Verdana';
           //    context.fillStyle = 'red';
	   //    context.fillText(str+'%', this.x+50, this.y-40);
}//
