
function class_game(){
   this.x = 20;
   this.y = 20;
   
    
}
class_game.prototype.draw=function(context){
    // ARR_PL_BOMB.push(BONUS_BOMB_2,'bomb_0');  

      context.font = '14px Verdana';
      context.fillStyle = 'red';
      context.fillText('::::', this.x, this.y); 
}



class_game.prototype.text=function(ctx,text , x , y){
     ctx.font = '14px Verdana';
     ctx.fillStyle = 'red';
      ctx.fillText(text, x, y); 
}




