
function class_gun_enemy(x,y,type){
     this.x = x;
     this.y = y;
      this.takt=0;
      this.id =  getRandomInt(0 , 99999);
      l(this.id);
     this._joint=null;
     this.conf = {};
     this.conf.no = 0;
     this.bomb_n = 0;
     this.type2 = 'gun';
     this.power=5000;
     this.type = type;
     this.density     = 50;//плотность
     this.restitution = 0.2;//упругость
     this.friction    = 0.1;//трение
     this.time = 100; // время 
     this.MaxTime=200;//выремя перезарядки
     this.count = 0;
    this.R = 10; //радиус ядра
   switch(type){
       case 'gun1':
           this.power=14000; // сила выстрела
           this.MaxTime=400;//выремя перезарядки
           this.img = document.getElementById("gun1");
           break;
      case 'gun2':
           this.power=20000; // сила выстрела
	    this.MaxTime=500;//выремя перезарядки
	     this.img = document.getElementById("gun2");
           break;
      default:
           this.power=5000; // сила выстрела
	    this.img = document.getElementById("gun2");
           break;
   }
  
   
   
     this.enemy=true; //враг
      var    as =3.3;
      var   au =4.8 ; //верхний предел
      var  al =2.9;

  this.as = as;
  this.al = al;
  this.au = au;
  
    this.body2 = createDinamicBox(this.x,this.y, 40, 30 ,new Array(null, 500,  1.0,  0.1, 'gun_body'), img_res('g2.png'));
    l(this.body2);
    this.body1 = createDinamicBox(this.x,this.y, 60, 20 ,new Array(null, 50,  0.9,  0.1, 'gun'), this.img);
    var vec1 =new b2Vec2(0, 0);
    var vec2 =new b2Vec2(0, -0.5);
    var   _jointDef = new b2RevoluteJointDef(); //создаем определение соединения -89272394662
    this._jointDef = _jointDef;
			_jointDef.bodyA = this.body1; //первое тело соединения
			_jointDef.bodyB = this.body2; //второе тело соединения
			_jointDef.collideConnected = false; //тела не сталкиваются
			_jointDef.localAnchorA = vec1;//new b2Vec2(0, 0); //якорная точка первого тела
			_jointDef.localAnchorB = vec2;//new b2Vec2(0, 1); //якорная точка второго тела
			//_jointDef.enableLimit = true; //включаем пределы
                        _jointDef.referenceAngle = 0;//начальный угол соединения
			//_jointDef.lowerAngle =al; //нижний предел
			//_jointDef.upperAngle =au;//верхний предел
			
                        this._joint = _jointDef;
                       l(_jointDef);
			 world.CreateJoint(_jointDef);// as b2RevoluteJoint; //добавляем соединение в мир

                        this.body1.SetAngle( as);
//return this;
   
}

class_gun_enemy.prototype.update=function (context){
    if( GAME_TYPE == 'END')    return;
    if( GAME_TYPE == 'CREATE') return;
    var ang = this.body1.GetAngle();
    
    /*
    this.x =  this.body1.GetPosition().x*worldScale ;
    this.y =  this.body1.GetPosition().y*worldScale ;
               var str =''+this.time;
              context.font = '14px Verdana';
               context.fillStyle = 'red';
               context.fillText(str, this.x-100, this.y-50);
            */   
	   if(ang < this.al){
              // this.body1.ClearForces();
               this.body1.SetLinearVelocity(new b2Vec2(0,0));
               this.body1.SetAngularVelocity(0);
              ang+=0.3;
              this.body1.SetAngle(ang);
          //     context.fillStyle = 'red';
         //     context.fillText('НИЖЕ ПРЕДЕЛА!', this.x-100, this.y-40);
              return;
	   }
            if(ang > this.au){
           //      context.fillStyle = 'red';
           //    context.fillText('ВЫШЕ ПРЕДЕЛА!', this.x-100, this.y-30);
              ang-=0.1;
              this.body1.SetAngle(ang);
             return;
	   }
   if(this.canFire()){
       this.Fire();
       if(global_bomb_detect){
      this.count++;
      if(this.count==5){
	  
        global_bomb_detect=false;
        this.count=0;
      }
       }
   }
   //object_animate - вот тут находится обьект атаки
  if(global_bomb_detect){

  }
  else{
      /*
       *  float GetLowerLimit();
  float GetUpperLimit();
       */
      if(GLOBAL_BOMB_X[this.id] ===null) GLOBAL_BOMB_X[this.id] =5;
       if(GLOBAL_BOMB_X[this.id]  !==0){
	   
	   // упал левее
         if(GLOBAL_BOMB_X[this.id]  < global_hero_x){
	     
                   
                       ang-=0.06;
                      
        }   
       if( GLOBAL_BOMB_X[this.id]  > global_hero_x){
               
		       this.conf.no++;
		       if( this.conf.no > 5){
			   //если больше 4 раз не попал меняем бомбу
			   this.conf.no = 0;
			   var l = ARR_EN_BOMB.length;
			   this.bomb_n++;
			   if(this.bomb_n >= l ) this.bomb_n=0;
			  // this.bomb = ARR_EN_BOMB[this.bomb_n];
		       }
                       ang+=0.06;
             
        }
	//  if(ang > this.al && ang < this.au){
	        this.body1.SetAngle(ang);
	 //  }
	
    //  if(this.type2 === '1')  l('angl='+ang+'global_bomb_x='+GLOBAL_BOMB_X[this.id]);
         GLOBAL_BOMB_X[this.id] =0;
         }
  }
    
}


class_gun_enemy.prototype.getBody=function(){
    return this.body2;
}
class_gun_enemy.prototype.canFire=function(){
    if(this.time <= 0) {
        this.time = this.MaxTime;
        return true;
        
    }
    else {
         this.time--;
       
        return false;
    }
}
class_gun_enemy.prototype.Fire=function(){

//проверяем угол отклонения
var a = this.body2.GetAngle()*180/Math.PI;
if(a>90 || a < -90){
    return;
}

          var bomb = new class_bomb(this.body1.GetPosition().x, this.body1.GetPosition().y , 50 ,this.body1.GetAngle(), ARR_EN_BOMB[this.bomb_n],this.power ,'enemy_bomb',this.id);
	//  l(bomb);
	  ARR_BOMB.push(bomb);
	 // l( "ARR_BOMB:"+ARR_BOMB.length);
				  
    
}

