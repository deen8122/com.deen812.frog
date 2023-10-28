function  object3(x , y , w , h ,img){
    this.w = w;
    this.h = h;
    this.x = x;
    this.y = y;
         var i2 = new Image(); 
          i2.src = 'image/10.png';
          this.image = i2;
    this.body = createDinamicBox(x,y, w, h ,new Array(null, 10,  0.3,  0.3, '0'), null);
    
}

object3.prototype.draw = function(context){
    //context.drawImage(this.image, this.body.GetUserData().width/2,this.body.GetUserData().height/2);
   context.save();
   //l(this.image);
       context.drawImage(this.image,
                                       this.w+5,
                                       this.h+5, 
                                       this.w+5, 
                                       this.h+5,  
                                       5,
                                       5,
                                      // this.body.GetPosition().x*worldScale - this.w/2 , 
                                      // this.body.GetPosition().y*worldScale  - this.h/2, 
                                       this.w+5, 
                                       this.h+5
              );
                   context.restore();     
             
}
object3.prototype.getBody = function(){
    return this.body;
}
object3.prototype.collision = function(contact,impulse){
    
}




function class_object_animate(x,y,animal,id){
      this.iSprPos=2;
      this.iSprDir = 1;
      this.w = 32;
      this.h = 32;
     this.life = 500; 
    //this.body = null;
    var path;
    this.id = id;
    this.x=0;
    this.y=0;
    this.is = 'good';
    this.type = 'animal';
    this.animal = animal;
    var pl,upr,tr,w,h;
    pl= 80; upr=0.5; tr = 0.3;w=28;h=28;
    switch(animal){
       // короткая доска
       case '1':path='cat.png'; break;
       //длинная доска
       case '2':path='cat2.png'; this.is = 'enemy'; break;
       case '3':break;
       case '4':break;
       case '5':break;  
       case '10':path='10.png';pl= 500; upr=0.2; tr = 0.9; w=20; h=15;break;//кирпич 1
       default:  path='12.png';pl= 100; upr=0;   tr = 0.3; w=31; h=18; break;//ЯЩИК 
    }//--------------------

          var i2 = new Image(); 
          i2.src = 'image/'+path;
          this.image = i2;
           this.body = createDinamicBox(x,y, w, h ,new Array(null, pl,  tr,  upr, id), null);
          // l(this.body);
          
}
class_object_animate.prototype.getBody = function(){
    return this.body;
}
class_object_animate.prototype.collision = function(contact,impulse){
                var a = contact.GetFixtureA().GetUserData();
		var b = contact.GetFixtureB().GetUserData();
                if(a != this.id || b != this.id)return;
                    if(a=='bomb_enemy'||b=='bomb_enemy'||a=='bomb_boom'||b=='bomb_boom'){
                       global_bomb_detect=true;
                        if(a=='bomb_enemy'){
                           contact.GetFixtureA().GetBody().GetFixtureList().SetUserData('z_bomb');
                        }else{
                            contact.GetFixtureB().GetBody().GetFixtureList().SetUserData('z_bomb');
                        }
                     
                       // l(tmp);
                          this.iSprDir=0;
                          var magnitude = Math.sqrt(
                              impulse.normalImpulses[0] * impulse.normalImpulses[0] + 
                              impulse.normalImpulses[1] * impulse.normalImpulses[1]);
                        
                             this.life-=magnitude/4;
           
            if(this.life < 0){
                  this.iSprDir=5;
                  this.iSprPos=0;
                  global_end = true;
                  global_loose =this.id; 
             }
              
             
                }
}

function get_bomb(a,b){
    if(a=='bomb_enemy') return a;
    else return b;
}

 //-----------------------------------------------------------------------------
class_object_animate.prototype.draw = function(context){
this.x =  this.body.GetPosition().x*worldScale - this.w/2 ;
this.y =  this.body.GetPosition().y*worldScale  - this.h/2;
                     context.drawImage(this.image,
                                       this.iSprPos*this.w,
                                       this.iSprDir*this.h, 
                                       this.w, 
                                       this.h,  
                                      this.body.GetPosition().x*worldScale - this.w/2 , 
                                      this.body.GetPosition().y*worldScale  - this.h/2, 
                                       this.w, 
                                       this.h
              );
               var str =''+this.life;
               context.font = '14px Verdana';
               context.fillStyle = 'red';
               context.fillText(str, this.x, this.y-5);
                  //  context.restore();     
}//-----------------------------------------------------------------------------

  