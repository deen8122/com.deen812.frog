<?php


print_r($_POST);
$xml='<?xml version="1.0" encoding="utf-8"?>
    <level width="'.$_POST[0]['width'].'" height="'.$_POST[0]['height'].'">
	  ';

$i=0;
foreach ($_POST as $arr){
    $i++;
    if($i==1)	continue;
    $width='';
    $height='';
    if($arr['name'] =="Platform"){
	$height='height="'.$arr['h'].'"';
	$width='width="'.$arr['w'].'"';
    }
 $xml.='<entity x="'.$arr['x'].'" y="'.$arr['y'].'" '.$height.'   '.$width.'  type="'.$arr['name'].'" />
     ';   
}


$xml.='</level>';
echo $xml;

if($_POST[0]['map_name']!=''){
 //   $s = fopen('map/'.$_POST[0]['map_name'],"a+");
  //  fwrite($s,$xml."\r\n");
  //  fclose($s);
    file_put_contents('map/'.$_POST[0]['map_name'], $xml);
}
//file_put_contents('map/'.$_POST[0]['map_name'], $xml);

/*
 * <?xml version="1.0" encoding="utf-8"?>
<level width="3000" height="3000">
<entity x="500" y="50"   type="Finish"/>



<entity x="50" y="500"    type="Ropebad" />
<entity x="300" y="600"    type="Rope" />
<entity x="800" y="600"    type="Ropebad" />
<entity x="500" y="800"    type="Ropebad" />
<entity x="300" y="900"    type="Ropebad" />
<entity x="600" y="1100"    type="Ropebad" />
<entity x="400" y="1500"    type="Ropebad" />



<entity x="200" y="130"   type="Palka2Rot"/>
<entity x="380" y="130"   type="Palka2Rot"/>
<entity x="280" y="290"   type="Palka2"/>

<entity x="350" y="30"   type="Osa"/>
<entity x="360" y="30"   type="Osa"/>
<entity x="350" y="30"   type="Osa"/>







</level>



 */
?>
