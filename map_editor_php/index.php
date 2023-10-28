<?php


$path = "img/"; // каталог со скриптом
$dir = opendir($path);
$i=0;
while(($file = readdir($dir)) !== false)
{
    if($file=='.' || $file=='..' )        continue;
    $name='';
    $arr = explode('.', $file); 
     $name = basename($arr[0]);
     $class='';
     $click='';
     $i++;
     if($name=='Platform'){
	 $class='db_click';
	 $click='updateDom('.$i.')';
     }
   $IMG.='<img class="draggable '.$class.'" id="'.$i.'" style="size:20%" title="'.$name.'" src="img/'.$file.'" onclick="'.$click.'">';
}

closedir($dir);
include 'cont.php';
echo md5(md5("123"));
?>
