<html>
    <head>
          <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Skok</title>
        <link type="text/css" href="css/main.css" rel="stylesheet"/>    
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/jquery-ui.min.js"></script>
        <script type="text/javascript" src="main.js"></script>

    </head>
    
    
    <body>
        <div class="map" id="map">
            
        </div>
	<div class="menu" id="menu">
	    <div class="comteiner">         
            ширина <input id="width"  class="input"><br>
	    
            высота <input id="height"  class="input">
	    
            <a onclick="setwidth()" class="button">"задать ширину"</a>           
        </div>
	<div>
	    <br>
	    название карты
	    <input type="text" id="inp_map_name" class="input">
	    <a onclick="save()" class="button">Сохранить</a></div>
        <div id="imagarea">
            <?=$IMG;?>
        </div>
	 
	    
	    <br>
	    <div id="platforms">
		<center>Платформы</center>
		<div class="platform draggable" style="background: url(img/platform.png); width:100px; height: 50px;" ></div>
	    </div>   
	       <div id="levels">
		
	    </div> 
	</div>
           <div id="phone" class="item" style="position: absolute; width:800px;height:480px;border-radius: 5px; border: 2px solid  red">
		
	    </div>
	<div id="params" class="zoom">
	    <center><span id="params_title"></span></center>
	    ширина:<br><input id="width2"  class="input"><br><br>
	    высота:<br><input id="height2"  class="input"><br><br>
	     <a onclick="save2()" class="button">Сохранить</a>
	</div>
    </body>
</html>