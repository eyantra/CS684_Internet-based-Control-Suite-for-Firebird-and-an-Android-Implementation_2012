
<?php

$bot_id = $_POST["bot_id"];
$task_id = $_POST["task_id"];
$param[0] = $_POST["t1"];
$param[1] = $_POST["t2"];
$param[2] = $_POST["t3"];
$param[3] = $_POST["t4"];
$param[4] = $_POST["t5"];

$message = $bot_id."\\$".$task_id;
for($i=0; $param[$i] != "";$i++)
	$message = $message."\\$".$param[$i];
		
$message = $message."#";


$fp = fopen('/home/vibhor/Desktop/test', 'a');
fwrite($fp, $message);
fclose($fp);

$last_line = system("python newface.py $message", $retval);

?>
