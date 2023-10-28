
function class_bomb(x,y,l,angle,type,power,type2,who){
     this.x = x;
     this.y = y;
     this.id = who|| 'player';
     this.type2  = type2;
     this.angle=angle;
     this.type = type;
     this.power = power;
     this.R = 10;
     this.die=false;
     this.time=80;
     this.cmd='';
     var pl = 100;
     var upr = 0.2;
     var tr = 0.5;
     this.img  = document.getElementById(this.type);
     switch(this.type){
	 
        case 'bomb_1':  this.R=10; pl = 50;   upr=0.2;  break;
	case 'bomb_2':  this.R=10; pl  = 100; upr=0.2; break;
        case 'bomb_3':  this.R=10; pl  = 150; upr=0.2; break;
	/*
	 * 
	 * var BONUS_BOMB_1 = 'bomb_bonus_1';//ПОЯВЛЯЕТСЯ СНАРЯД С ОСКОЛКАМИ СИЛА УДАРА 1
           var BONUS_BOMB_2 = 'bomb_bonus_2';// БЫСТРЫЙ СНАРЯД ВЗРЫВАЮЩИЙ
           var BONUS_BOMB_3 = 'bomb_bonus_3';// С ДЕТОНАТОРОМ

	 */

        case BONUS_BOMB_1:this.time=1;   pl = 50;  this.R=10;    break;// взрывающая
	case BONUS_BOMB_2:this.time=1;   pl = 100; this.R=10;    break;// взрывающая
	case BONUS_BOMB_3:this.time=300; pl = 40; this.R=10;     this.img =  document.getElementById(BONUS_BOMB_3);   break;// взрывающая
	
        case 'OSKOLKI':this.R=4; pl = 50; upr=0.5;this.time=50; this.img= document.getElementById("bomb_osk");break;
	case 'OSKOLKI2':this.R=3; pl = 150; upr=0.1;this.time=50; this.img= document.getElementById("bomb_osk2");break;
      default:
          break;

   }
//this.img  = document.getElementById(this.type);

var bodyDef = new b2BodyDef;
    bodyDef.type = b2Body.b2_dynamicBody;
    bodyDef.position.Set(x+l*Math.cos(angle)/worldScale, y +l*Math.sin(angle)/worldScale);
    bodyDef.userData=this.img;
    bodyDef.isBullet = true;
var fixtureDef=new b2FixtureDef;
if(this.type===BONUS_BOMB_3){
     var polygonShape = new b2PolygonShape;
        polygonShape.SetAsBox(20/worldScale,10/worldScale);
    fixtureDef.shape=polygonShape;
}else   fixtureDef.shape= new b2CircleShape(this.R/worldScale);
    
    
			  fixtureDef.density=pl;//плотность
			  fixtureDef.restitution= upr;//упругость
			  fixtureDef.friction=tr;//трение
                          fixtureDef.userData =  this.type2;
this.body=world.CreateBody(bodyDef);
     this.body.CreateFixture(fixtureDef);
     this.body.SetFixedRotation(false);
     this.body.ApplyImpulse(new b2Vec2(this.power*Math.cos(angle)/worldScale, 
                                  this.power*Math.sin(angle)/worldScale),
                                  this.body.GetWorldCenter());

 
   
}
class_bomb.prototype.update=function(context){
switch(this.cmd){
    case 'dinamit':
	var time=0;
	if(this.time < 300) time=3;
	if(this.time < 200) time=2;
	if(this.time < 100) time=1;
	if(this.time === 0){
	       
	     for(var i=0;i < 30; i++){
                     var bomb = new class_bomb(this.body.GetPosition().x,  this.body.GetPosition().y , 1, i*0.5, "OSKOLKI2" , i*1500, 'bomb');
                     ARR_BOMB.push(bomb);      
                   }
		
		    this.cmd='';
	}
     this.x =  this.body.GetPosition().x*worldScale ;
     this.y =  this.body.GetPosition().y*worldScale ;
               
               context.font = '18px Verdana';
               context.fillStyle = 'white';
               context.fillText(time, this.x-10, this.y);
    break;
    case 'creat_body': 
                   for(var i=0;i < 6; i++){
                     var bomb = new class_bomb(this.body.GetPosition().x,  this.body.GetPosition().y , 1, i*0.5, "OSKOLKI" , i*50, 'bomb');
                     ARR_BOMB.push(bomb);      
                   }
		   this.cmd='';
                     break;
}

    if( GAME_TYPE === 'CREATE')return;

   // this.x =  this.body.GetPosition().x*worldScale ;
   // this.y =  this.body.GetPosition().y*worldScale ;
    //            var str =''+this.time;
     //          context.font = '14px Verdana';
     //          context.fillStyle = 'red';
     //          context.fillText(str, this.x, this.y-50);
}


class_bomb.prototype.getBody=function(){
    return this.body;
}

 class_bomb.prototype.boom=function(){
     
        this.die=true;
	//ДИНАМИТ
	if(this.type===BONUS_BOMB_3 ){
	  this.cmd='dinamit';  
	}
        if(this.type===BONUS_BOMB_2 ||this.type===BONUS_BOMB_1 ){
	    var force = 1;
	    if(this.type===BONUS_BOMB_2){
		force =1.5;
	    }
     var body = this.body;
  l('booooom');
                     for(var pbody = world.m_bodyList; pbody != null; pbody = pbody.m_next){
                           if(pbody.m_fixtureList===null) continue;
                           if(pbody.m_fixtureList.m_userData===SIMPLE_BOX) {
                           var ax=pbody.GetPosition().x, ay=pbody.GetPosition().y;
                              if(ax===null || ay===null)continue;
                           var ang= Math.atan2(ax - body.GetPosition().x, ay - body.GetPosition().y);
                           var F= 2000/((ax - body.GetPosition().x) * (ax - body.GetPosition().x))*force;
			   l('сила взрыва '+this.type+' = '+F);
                           if(F>3000) F=1000;
                         pbody.ApplyImpulse(new b2Vec2(Math.sin(ang) * F, Math.cos(ang) * F), pbody.GetPosition());
                        }
                  }
this.cmd='creat_body';	
        }
}//