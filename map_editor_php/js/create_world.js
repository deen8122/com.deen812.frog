 function createDinamicBox2(pX,pY,obj){
        var bodyDef = new b2BodyDef;
        bodyDef.type = b2Body.b2_dynamicBody;
        bodyDef.position.Set(pX/worldScale,pY/worldScale);
        bodyDef.userData=obj;
        //l(obj);
        var polygonShape = new b2PolygonShape;
        polygonShape.SetAsBox(obj.w/2/worldScale,obj.h/2/worldScale);
        var fixtureDef = new b2FixtureDef;
        fixtureDef.density =     obj.pl;//плотность
        fixtureDef.friction =    obj.tr;//трение
        fixtureDef.restitution = obj.upr;//упругость      
         fixtureDef.shape = polygonShape;
         fixtureDef.userData =   SIMPLE_BOX;
        var body=world.CreateBody(bodyDef);
        body.CreateFixture(fixtureDef);
        return body;
    }
    
    
 function createDinamicBox(pX,pY,width,height,array,img ){
        var bodyDef = new b2BodyDef;
        bodyDef.type = b2Body.b2_dynamicBody;
        bodyDef.position.Set(pX/worldScale,pY/worldScale);
       // var o = new option({'width':width , 'height': height , 'img': img});
        bodyDef.userData=img;
        //l(o);
        var polygonShape = new b2PolygonShape;
        polygonShape.SetAsBox(width/2/worldScale,height/2/worldScale);
        var fixtureDef = new b2FixtureDef;
        fixtureDef.density =     array[1];//плотность
        fixtureDef.friction =    array[2];//трение
        fixtureDef.restitution = array[3];//упругость
       
         fixtureDef.shape = polygonShape;
         fixtureDef.userData =   array[4];
        var body=world.CreateBody(bodyDef);
        body.CreateFixture(fixtureDef);
       // var mass= new b2MassData();
       //     mass.mass = 10.0;
       // body.SetMassData(mass);
       //l(body);
        return body;
    }
    
function createDinamicPoly(pX,pY,width,height,array,img ){
        var bodyDef = new b2BodyDef;
        bodyDef.type = b2Body.b2_dynamicBody;
        bodyDef.position.Set(pX/worldScale,pY/worldScale);
       // var o = new option({'width':width , 'height': height , 'img': img});
        bodyDef.userData=img;
        //l(o);
        var polygonShape = new b2PolygonShape;
        //polygonShape.SetAsBox(width/2/worldScale,height/2/worldScale);
	var shape1Vertices3 = new Array();
	var n1 = width/2/worldScale;
	var n2 = height/2/worldScale;
	shape1Vertices3.push(new b2Vec2(n1,n2));
	shape1Vertices3.push(new b2Vec2(-n1,n2));
	shape1Vertices3.push(new b2Vec2(-n1/2,-n2));
	shape1Vertices3.push(new b2Vec2(n1/3,-n2));
	
	polygonShape.SetAsVector(shape1Vertices3);
        var fixtureDef = new b2FixtureDef;
        fixtureDef.density =     array[1];//плотность
        fixtureDef.friction =    array[2];//трение
        fixtureDef.restitution = array[3];//упругость
       
         fixtureDef.shape = polygonShape;
         fixtureDef.userData =   array[4];
        var body=world.CreateBody(bodyDef);
        body.CreateFixture(fixtureDef);
       // var mass= new b2MassData();
       //     mass.mass = 10.0;
       // body.SetMassData(mass);
       //l(body);
        return body;
    } 

function class_ground(img , x , y , w , h){
    this.img = img;
    this.x=x;
    this.y=y;
    this.w = w;
    this.h = h;
    
    
}

class_ground.prototype.draw = function(context){
 context.save();
    var pat=context.createPattern(this.img,"repeat");
     //   context.rect(this.x - this.w/2,this.y - this.h/2,this.w,this.h);
        context.fillStyle=pat;
        //context.fill();
        context.fillRect(this.x - this.w/2,this.y - this.h/2,this.w,this.h);
    // context.stroke();
     context.fillStyle=null;
       context.restore();
 //    context.drawImage(this.img , this.x , this.y);
        
}
function create_ground(x,y,w,h, type){
      var path='';
       
     
   switch(type){
       case GROUND_1:path = 'ground_1.png';break;
       case GROUND_2:path = 'ground_2.png'; break;
       case GROUND_3:path = 'ground_3.png';break;
           
           
       default:
           break;
   }//---------------- 
 var i2 = new Image();
   i2.onload = function(){
         ground_arr.push(new class_ground(i2 , x ,y , w ,h));
         
var bodyDef=new b2BodyDef();
bodyDef.position.Set(x/worldScale,y/worldScale);

bodyDef.type = b2Body.b2_staticBody;
bodyDef.userData=null;
  var polygonShape=new b2PolygonShape();
  polygonShape.SetAsBox(w/2/worldScale,h/2/worldScale);

var fixtureDef=new b2FixtureDef();
fixtureDef.shape=polygonShape;
fixtureDef.density=100;//плотность
fixtureDef.restitution=0.2;//упругость
fixtureDef.friction=0.5;//трение
fixtureDef.userData = 'ground';
var body=world.CreateBody(bodyDef);
body.CreateFixture(fixtureDef);
return body;  
         
   }
       i2.src = 'image/'+path;
     
 
   
   
   

    
    
}//---------

   

function addCircle(x, y, r) {
    var fixDef = new b2FixtureDef;
    fixDef.density = 500.0;
    fixDef.friction = 0.5;
    fixDef.restitution = 0.0;

    var bodyDef = new b2BodyDef;
    bodyDef.type = b2Body.b2_dynamicBody;
    bodyDef.position.x = x/worldScale;
    bodyDef.position.y = y/worldScale;
     
    fixDef.shape = new b2CircleShape(r/worldScale);
    fixDef.userData =  'circle';
    var body = world.CreateBody(bodyDef);
    body.CreateFixture(fixDef);
    return body;
}





function joint_revolute2(body1 ,vec1, body2,vec2){
  var   _jointDef = new b2RevoluteJointDef(); //создаем определение соединения
			_jointDef.bodyA = body1; //первое тело соединения
			_jointDef.bodyB = body2; //второе тело соединения
			_jointDef.collideConnected = false; //тела не сталкиваются
			_jointDef.localAnchorA = vec1;//new b2Vec2(0, 0); //якорная точка первого тела
			_jointDef.localAnchorB = vec2;//new b2Vec2(0, 1); //якорная точка второго тела
			_jointDef.enableLimit = true; //включаем пределы
			_jointDef.lowerAngle = -45 * Math.PI / 180; //нижний предел
			_jointDef.upperAngle = 45 * Math.PI / 180; //верхний предел
			_jointDef.referenceAngle = -45* Math.PI / 180; //начальный угол соединения
			_jointDef.motorSpeed = 10.0; //желаемая скорость вращения в радианах в секунду
			_jointDef.maxMotorTorque = 350; //максимальный крутящий момент мотора
			//_jointDef.enableMotor = true; //включаем мотор
			var _jointMotor = world.CreateJoint(_jointDef);// as b2RevoluteJoint; //добавляем соединение в мир
 
}

function joint_revolute(body1 ,vec1, body2,vec2){
  var   _jointDef = new b2RevoluteJointDef(); //создаем определение соединения
			_jointDef.bodyA = body1; //первое тело соединения
			_jointDef.bodyB = body2; //второе тело соединения
			_jointDef.collideConnected = false; //тела не сталкиваются
			_jointDef.localAnchorA = vec1;//new b2Vec2(0, 0); //якорная точка первого тела
			_jointDef.localAnchorB = vec2;//new b2Vec2(0, 1); //якорная точка второго тела
			//_jointDef.enableLimit = true; //включаем пределы
			//_jointDef.lowerAngle = -45 * Math.PI / 180; //нижний предел
			//_jointDef.upperAngle = 45 * Math.PI / 180; //верхний предел
			_jointDef.referenceAngle = 0; //начальный угол соединения
			_jointDef.motorSpeed = 10.0; //желаемая скорость вращения в радианах в секунду
			_jointDef.maxMotorTorque = 350; //максимальный крутящий момент мотора
			//_jointDef.enableMotor = true; //включаем мотор
			return world.CreateJoint(_jointDef);// as b2RevoluteJoint; //добавляем соединение в мир
 
}

function joint_distance(body1, body2 , len){
                      var _jointDef = new b2DistanceJointDef(); //создаем определение соединения первым способом
			_jointDef.bodyA = body1; //первое тело соединения
			_jointDef.bodyB = body2; //второе тело соединения
			_jointDef.localAnchorA = new b2Vec2(0, 0); //якорная точка первого тела
			_jointDef.localAnchorB = new b2Vec2(0, 0); //якорная точка второго тела
			_jointDef.length = len; //длина соединения
			_jointDef.collideConnected = true; //тела могут сталкиваться
			world.CreateJoint(_jointDef); //создаем и добавляем соединение в мир
}


    
 // создать окружность
function createCircleAt(x, y, r) {
    var boxSd = new b2BodyDef();
    boxSd.density = 1.0;
    boxSd.friction = 1.0;
    boxSd.restitution = 0.5;
    boxSd.radius = r;

    // добавить в мир как фигуру
    var boxBd = new b2BodyDef();
    boxBd.AddShape(boxSd);
    boxBd.position.Set(x,y);
    return world.CreateBody(boxBd);
}
   
function createCircle(x, y , R, bodyType){

    var bodyDef= new b2BodyDef(); //геометрические параметры тела
        bodyDef.type = b2Body.b2_dynamicBody; //тела у нас динамические
        
    var bodyShapeCircle = new b2CircleShape(R/worldScale ); //создаем шейп в форме круга с радиусом 5 пикселей
       bodyDef.position.Set( x/ worldScale,y / worldScale); //определяем начальную позицию тела
    var body1 = world.CreateBody(bodyDef); //создаем тело
    
    body1.CreateFixture2(bodyShapeCircle, 1.5); //крепим к телу
   // fixtureDef.userData = 'box';
    return body1;
}



function addBox(pX,pY,w,h,bodyType) {
    
			var bodyDef=new b2BodyDef();
			bodyDef.position.Set(pX/worldScale,pY/worldScale);
			bodyDef.type=bodyType;
			var polygonShape=new b2PolygonShape();
			polygonShape.SetAsBox(w/2/worldScale,h/2/worldScale);
			var fixtureDef=new b2FixtureDef();
			fixtureDef.shape=polygonShape;
			fixtureDef.density=100;//плотность
			fixtureDef.restitution=0.2;//упругость
			fixtureDef.friction=0.5;//трение
                        fixtureDef.userData = 'box';
			var body=world.CreateBody(bodyDef);
                    //   body.SetUserData(pX);
			body.CreateFixture(fixtureDef);
                        
			return body;
		}
 
