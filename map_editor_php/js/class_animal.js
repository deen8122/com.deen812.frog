function class_animal(x,y,type,enemy){
    
      this.iSprPos=2;
      this.iSprDir = 1;
      this.w = 32;
      this.h = 32;
      this.enemy = enemy||'enemy';
      this.life = 500; 
       var path;
      this.id = '';
      this.x=0;
      this.y=0;
      this.is = '';
      this.type = type;
      this.type2 = 'animal';
      var pl,upr,tr,w,h;
      this.fillStyle='yellow';
      pl= 200; upr=0.1; tr = 0.9;w=28;h=28;
      
      if( this.enemy=='enemy'){
	  
      }else {
	  this.iSprDir = 0;
      }
    switch(type){
       case 'cat1':path='cat3.png'; break;
       case 'cat2':path='cat2.png';  break;
       default:path='cat3.png';  break;

    }//--------------------
     this.rect = new rect_gun(this.life);
     this.rect.h = 5;
          var i2 = new Image(); 
          i2.src = 'image/'+path;
           this.image = i2;
	   
           this.body = createDinamicPoly(x,y, w, h ,new Array(null, pl,  tr,  upr, 'animal'), null);
          
}

class_animal.prototype.setlife = function(){
    this.iSprDir=3;
    if(this.life < 400){
	 this.iSprPos=0;
	  this.fillStyle='green';
    }
     if(this.life < 300){
	 this.iSprPos=1;
	  this.fillStyle='blue';
    }
     if(this.life < 200){
	 this.iSprPos=2;
	  this.fillStyle='red';
    }
     if(this.life < 100){
	 this.iSprPos=0;
    }
     if(this.life < 50){
	 this.iSprPos=0;
    }
    
       
}
class_animal.prototype.getBody = function(){
    return this.body;
}
class_animal.prototype.collision = function(contact,impulse){
    if( GAME_TYPE === 'END') return;
  if(this.life < 0) {
      
	
	return;
    }
                var a = contact.GetFixtureA().GetUserData();
		var b = contact.GetFixtureB().GetUserData();
		var c = null;
		//if(a!=='animal' || b!=='animal') return;
		if(a==='z_bomb' || b==='z_bomb' ||a==='bomb' || b==='bomb' ||a==='enemy_bomb' || b==='enemy_bomb'){}
		else return;
		//l2('столкновение с '+this.enemy);
		
		
                if(a === 'animal'){ 
		       c = b; 
		       contact.GetFixtureB().GetBody().GetFixtureList().SetUserData('z_bomb');
		   }
		else {
		    c = a;
		    contact.GetFixtureA().GetBody().GetFixtureList().SetUserData('z_bomb'); 
		}


                           if(this.enemy==='player'){
		     global_bomb_detect=true;
		     global_bomb_x = this.body.GetPosition().x
		}

                     

                          
                          var magnitude = Math.sqrt(
                              impulse.normalImpulses[0] * impulse.normalImpulses[0] + 
                              impulse.normalImpulses[1] * impulse.normalImpulses[1]);
                        
                             this.life-=parseInt(magnitude/5);
			     this.setlife();
           
            if(this.life < 0 ||this.body.GetPosition().x   < 0 || this.body.GetPosition().x*worldScale > sceneWidth){
                if(this.enemy==='enemy')  show_end_menu('win');
                else   show_end_menu('loose');
                  GAME_TYPE = 'END';
	          this.iSprDir=5;		  
                  this.iSprPos=0;
                  global_end = true;
                  global_loose =this.id; GLOBAL_ENEMY_N--;
                  return;
		  
             }
	 
	     
	     if(this.enemy=='enemy'){
		 PL_MONEY++;
                // update_money();
		     if(GLOBAL_ENEMY_N <=0){
		   // clearInterval(GLOBAL_UPDATE);
		   // alert('Враг проиграл');
		  //worldScale++;
	     }
	     }else{
		 if(PL_MONEY>0)PL_MONEY--;
		     if(GLOBAL_PLAYER_N <=0){
		   // clearInterval(GLOBAL_UPDATE);
		   // alert('Враг проиграл');
		  //worldScale++;
	     }
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
                   context.fillStyle = this.fillStyle;
                   context.fillText(str, this.x, this.y-10);
           
}//-----------------------------------------------------------------------------
