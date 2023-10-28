function class_animal_enemy(x,y,animal,enemy){
      this.iSprPos=2;
      this.iSprDir = 1;
      this.w = 32;
      this.h = 32;
      this.enemy = enemy;
      this.life = 500; 
       var path;
      this.id = '';
      this.x=0;
      this.y=0;
      this.is = '';
      this.type = 'animal';
      this.animal = animal;
      var pl,upr,tr,w,h;
      pl= 80; upr=0.5; tr = 0.3;w=28;h=28;
    switch(animal){
       case '1':path='cat.png'; break;
       case '2':path='cat2.png';  break;
       case 'cat':path='cat2.png';  break;

    }//--------------------

          var i2 = new Image(); 
          i2.src = 'image/'+path;
           this.image = i2;
           this.body = createDinamicBox(x,y, w, h ,new Array(null, pl,  tr,  upr,  this.type), null);
          
}


class_animal.prototype.getBody = function(){
    return this.body;
}
class_animal.prototype.collision = function(contact,impulse){
  
                var a = contact.GetFixtureA().GetUserData();
		var b = contact.GetFixtureB().GetUserData();
		var c = null;
		if(a==='ground' || b==='ground') return;
                if(a===SIMPLE_BOX || b===SIMPLE_BOX) return;
                if(a === this.type){ 
		       c = b; 
		       contact.GetFixtureB().GetBody().GetFixtureList().SetUserData('z_bomb');
		   }
		else {
		    c = a;
		    contact.GetFixtureA().GetBody().GetFixtureList().SetUserData('z_bomb'); 
		}

                if(this.enemy=='player'){
		     global_bomb_detect=true;
		}
                       

                     

                          this.iSprDir=0;
                          var magnitude = Math.sqrt(
                              impulse.normalImpulses[0] * impulse.normalImpulses[0] + 
                              impulse.normalImpulses[1] * impulse.normalImpulses[1]);
                        
                             this.life-=magnitude/5;
           
            if(this.life < 0){
                  this.iSprDir=5;
                  this.iSprPos=0;
                  global_end = true;
                  global_loose =this.id; 
             }
              
             
              
}


class_animal.prototype.update = function(context){
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
}//-----------------------------------------------------------------------------
