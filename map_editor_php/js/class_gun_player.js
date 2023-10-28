var count = 0;
function class_gun_player(x,y,type){
     this.x = x;
     this.y = y;
      this.takt=0;
     this._joint=null;
    
     
     this.type2 = 'gun';
     this.power=5;
     this.type = type;
     this.density     = 50;//плотность
     this.restitution = 0.2;//упругость
     this.friction    = 0.1;//трение
     this.time = 0; // время 
     this.MaxTime=200;//выремя перезарядки
   
   switch(type){
       case 'gun1':
           this.power=14000; // сила выстрела
           this.MaxTime=200;//выремя перезарядки
	   this.img = document.getElementById("gun1");
          
           break;
      case 'gun2':
           this.power=20000; // сила выстрела
           this.MaxTime=400;//выремя перезарядки
	    this.img = document.getElementById("gun2");
           break;
      default:
           this.power=8000; // сила выстрела
	   this.MaxTime=30;//выремя перезарядки
	    this.img = document.getElementById("gun1");
           break;
   }
  
   
   this.rect = new rect_gun(this.MaxTime);
  this.rect.h = 10;
   this.enemy=true; //враг

    var al = -90; //нижний предел
    var au = 45; //верхний предел
    var as =0; //начальный угол соединения

  au = au* Math.PI / 180;
  al =  al* Math.PI / 180; 
    this.body2 = createDinamicBox(this.x,this.y, 40, 30 ,new Array(null, 500,  1.0,  0.1, 'gun_body'), img_res('g1.png'));
    this.body1 = createDinamicBox(this.x,this.y, 60, 20 ,new Array(null, 50,  0.9,  0.1, 'gun'),  this.img);
    var vec1 =new b2Vec2(0, 0);
    var vec2 =new b2Vec2(0, -0.5);
    var   _jointDef = new b2RevoluteJointDef(); //создаем определение соединения -89272394662
			_jointDef.bodyA = this.body1; //первое тело соединения
			_jointDef.bodyB = this.body2; //второе тело соединения
			_jointDef.collideConnected = false; //тела не сталкиваются
			_jointDef.localAnchorA = vec1;//new b2Vec2(0, 0); //якорная точка первого тела
			_jointDef.localAnchorB = vec2;//new b2Vec2(0, 1); //якорная точка второго тела
			//_jointDef.enableLimit = true; //включаем пределы
                        _jointDef.referenceAngle = as//начальный угол соединения
			_jointDef.lowerAngle =al //нижний предел
			_jointDef.upperAngle =au;//верхний предел
			
                        this._joint = _jointDef;
                        l(_jointDef);
			//_jointDef.motorSpeed = 10.0; //желаемая скорость вращения в радианах в секунду
			//_jointDef.maxMotorTorque = 350; //максимальный крутящий момент мотора
			//_jointDef.enableMotor = true; //включаем мотор
			 world.CreateJoint(_jointDef);// as b2RevoluteJoint; //добавляем соединение в мир
                      //   _jointDef.referenceAngle = as* Math.PI / 180; //начальный угол соединения
                        this.body1.SetAngle( as* Math.PI / 180);
//return this;
   
}
class_gun_player.prototype.update=function(context){
    if( GAME_TYPE === 'END')return;
    if( GAME_TYPE === 'CREATE')return;
   if(this.canFire()){

   }

    this.x =  this.body1.GetPosition().x*worldScale ;
    this.y =  this.body1.GetPosition().y*worldScale ;
              //  var str =''+this.time;
             //  context.font = '14px Verdana';
              // context.fillStyle = 'red';
               //context.fillText(str, this.x, this.y-80);
	       this.rect.x = this.x-15;
	       this.rect.y = this.y+65;
	       
	       this.rect.draw(context);
}


class_gun_player.prototype.getBody=function(){
    return this.body2;
}
class_gun_player.prototype.canFire=function(){
    if(this.time >= this.MaxTime) {
       
        return true;
        
    }
    else {
         this.time++;
       
        return false;
    }
}
class_gun_player.prototype.test=function(){
     this.time = this.MaxTime;
}
class_gun_player.prototype.Fire=function(){
    this.time =0;
      this.rect.c =0;
}

var option = function(o){
     this.width = o.width;
     this.height = o.height;
     this.img = o.img;
     //return this;
}
 
 