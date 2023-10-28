
function class_bonus(time_wait,to){
    if(to===1){
	this.x =0;
	  this.to = to;
	    this.iSprDirP = 2;
	     this.iSprDir=0;
    }else{
	  this.to = -1;
	this.x =sceneWidth;
	  this.iSprDirP = 5;
	   this.iSprDir=2;
    }
       this.iSprPosMax=4;
      this.y =  getRandomInt(10 , 60);
      this.type = 'bonus';
      this.iSprPos=0;
   
      this.w = 54;
      this.h = 50;
      this.time = time_wait; // время 
      //this.to = to;
      this.takt=5;
   this.image = document.getElementById("bird");
   
}
class_bonus.prototype.update=function(context){
    if(this.time < 0){
	
    }else {
	this.time --;
	return;
    }
    this.x+= this.to;
    if(this.x==sceneWidth/2) this.create_bonus();
    if(this.to==1){
	if(this.x > sceneWidth){
	    ARR_BONUS=[];
	      this.create();
	}
    }else{
	if(this.x < 0){
	   ARR_BONUS=[]; 
	   this.create();
	}
	
    }
    
    this.draw(context);
}
class_bonus.prototype.create_bonus=function(){
    
    var n = BONUS_TYPE.length; 
   var r =  getRandomInt(0 , n-1);
   /*
    * var BONUS_LIFE_1 = 'LIFE_1';// ЖОБАВЛЯЕТ +100 К ЖИЗНИ
var BONUS_LIFE_2 = 'LIFE_2';//+200

var BONUS_BOMB_1 = 'bomb_bonus_1';//ПОЯВЛЯЕТСЯ СНАРЯД С ОСКОЛКАМИ СИЛА УДАРА 1
var BONUS_BOMB_2 = 'bomb_bonus_2';// БЫСТРЫЙ СНАРЯД ВЗРЫВАЮЩИЙ
var BONUS_BOMB_3 = 'bomb_bonus_3';// С ДЕТОНАТОРОМ

var BONUS_GUN_1 = 'GUN_1';// УВЕЛИЧИВАЕТ СКОРОСТЬ ПЕРЕЗАРЯДКИ
var BONUS_GUN_2 = 'GUN_2';//+100 к убойности
var BONUS_GUN_3 = 'GUN_3';//ДВОЙНОЙ ВЫСТРЕЛ
    */
 var  img = document.getElementById(BONUS_TYPE[r]);
//l2(BONUS_TYPE[r]);
//l(img);
   
        var body = createDinamicBox(this.x,this.y+this.h, 20, 20 ,new Array(null, 100,  0.6,  0.2,BONUS_TYPE[r]),img );
	 body.ApplyImpulse(new b2Vec2(2*this.to, 1), body.GetPosition());
}

class_bonus.prototype.create=function(){
    if( GAME_TYPE === 'END')return;
	var to = getRandomInt(0,2)-1;
	//l('to='+to);
    ARR_BONUS.push(new class_bonus(getRandomInt(10 , 200),to));
    
}

class_bonus.prototype.draw=function(context){
    if(this.takt ==0){
	this.takt=5;
	 this.iSprPos++;
    if(this.iSprPos > this.iSprPosMax) {this.iSprPos=0; 
        this.iSprDir++;
          if(this.iSprDir > this.iSprDirP) this.iSprDir=this.iSprDirP-2; 
    }
    }else{
	this.takt--;
	
    }
   
    
                     context.drawImage(this.image,
                                       this.iSprPos*this.w,
                                       this.iSprDir*this.h, 
                                       this.w, 
                                       this.h,  
                                       this.x, 
                                       this.y, 
                                       this.w, 
                                       this.h
              );
		  
                var str =''+this.time;
                context.font = '14px Verdana';
                context.fillStyle = 'red';
                context.fillText(str, this.x, this.y-50);
}