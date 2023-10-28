function setwidth(){
    $('#map')
    .css('width' ,$('#width').val()).css('height',$('#height').val());
    
}

function save(){
  var sel = $('#map').find('img');  
  console.log(sel);
  // перебор результатов
  var arr=[];
  arr.push( {
      map_name:$('#inp_map_name').val(),
      width:parseInt($('#map').width()),
      height:parseInt($('#map').height())});
	  var h = $('#map').height()*1 ;
for(var i=0; i<sel.length; i++) {
var coorect = 0;
     var name=$(sel[i]).attr('title');
	 
	 var width=0;
	 var height=0;
	 if(name=="Finish")coorect= 46;
	 if(name=="Platform"){
	    width = parseInt($(sel[i]).width());
	    height = parseInt($(sel[i]).height());
	 }
	 
          var coord = $(sel[i]).offset();
          var x = coord.left;
          var y = h - coord.top -coorect;
     arr.push({ 
	 name:name , 
	 x:parseInt( x ), 
	 y: parseInt( y ),
         w:width,
	 h:height
	} );
     
     
}

l(arr);
 $.ajax({type: "POST",data: $.extend({}, arr),url: 'ajax.php',          
    success: function (data, textStatus) {
		l(data);

	     } ,
    error:function(err){l(err);}});//AJAX


}
$(document).ready(function() {
   
});
 var y=0;
 var x =0;
$(document).ready(function(){
   $('.map').mousemove(function(e){
       x = e.pageX;
       y = e.pageY;
	 //  l(x+' : '+y);

   });
});

var _this=null;
function updateDom(id){
   $('#'+id).removeAttr('onCLick');
   $(".db_click").dblclick(function() {
      // show_form(this);
      $('#params').addClass('ready');
      _this = this;
      $('#params_title').html( $(this).attr('title'));
});
}

function save2(){
  // alert(_this);
    $(_this)
    .css('width' ,$('#width2').val()).css('height',$('#height2').val());
}
var ccc=0;
function l(s){  console.log(s);}
$(function() {
$("#phone").draggable();	
$("#menu").draggable();
 
    
 $(".map").droppable({
        accept: '.draggable',
        drop: function(event, ui) {
	    var clon = $(ui.draggable).clone();
            $(this).append(clon);
             //$(clone).live('click', function() {});
	
            $("#map .draggable").addClass("item").css('position','absolute').css('position','absolute').css('left',x).css('top',y);;
	    
            $(".item").removeClass("ui-draggable draggable");
	    
            $(".item").draggable({
                containment: 'parent'
            });
        }
    });
    $(".draggable").draggable({
        helper: 'clone'
    });	
      
	
	$('.draggable2').draggable({
            helper: "ui-resizable-helper",
          stop: function() {
        //   $(this).clone().appendTo("#imagarea");
        }
    });
    
    //приемник 
 
    
    
 
});
