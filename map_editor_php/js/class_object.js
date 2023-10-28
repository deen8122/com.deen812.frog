
function class_object(x,y,type){
    this.body = null;
    this._self =false;
    var path;
    this.polygon = false;
    this.points = [];
    this.body;
    this.type2 = SIMPLE_BOX;
    this.type = type;
    var pl,upr,tr,w,h;
    this.id = getRandomInt(0,1000);
    switch(type){
       // короткая доска
       case '17':path='17.png';pl= 80; upr=0; tr = 0.3;w=51;h=8; break;
       //длинная доска
       case '15':path='15.png';pl= 80; upr=0; tr = 0.3;w=93;h=6;break;
     
       case '4':break;
       case '5':break; 
       case '9': path='9.png';pl= 80; upr=0.2; tr = 0.3;w=9;h=27;    break;
       case '10':path='10.png';pl= 500; upr=0.2; tr = 0.9; w=20; h=15;break;//кирпич 1
       case '11':path='11.png';pl= 500; upr=0.4; tr = 0.9; w=34; h=5;break;//кирпич 2
       default:  path='12.png';pl= 100; upr=0;   tr = 0.3; w=31; h=18; break;//ЯЩИК 
    }//--------------------
this.w = w;
this.h = h;
this.pl = pl;
this.upr = upr;
this.tr = tr;
          var i2 = new Image(); 
          i2.src = 'image/'+path;
          this.image = i2;
          // this.body = createDinamicBox(x,y, w, h ,new Array(null, pl,  tr,  upr, SIMPLE_BOX), i2);
}
class_object.prototype.getBody = function(){
    return this.body;
};

class_object.prototype.collision = function(contact,impulse){
  var magnitude = Math.sqrt( impulse.normalImpulses[0] * impulse.normalImpulses[0] + impulse.normalImpulses[1] * impulse.normalImpulses[1]);
  l('magnitude:' + magnitude);
		      
};

function get(){}
 //-----------------------------------------------------------------------------
class_object.prototype.update = function(context){
    /*
                      context.save();
                      context.translate(this.body.GetPosition().x*worldScale,
                                       this.body.GetPosition().y*worldScale);
                     context.rotate(this.body.GetAngle()); 
                     context.drawImage(this.image,
                                      -this.body.GetUserData().width/2,
                                      -this.body.GetUserData().height/2
                              );
                    context.restore(); 
		    */
}//-----------------------------------------------------------------------------

  