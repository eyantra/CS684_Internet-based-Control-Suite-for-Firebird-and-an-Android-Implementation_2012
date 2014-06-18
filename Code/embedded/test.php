
<?php

$bot_id = $_POST["bot_id"];
$task_name = $_POST["taskname"];
$param[0] = $_POST["t1"];
$param[1] = $_POST["t2"];
$param[2] = $_POST["t3"];
$param[3] = $_POST["t4"];
$param[4] = $_POST["t5"];
$param[5] = $_POST["t6"];
$param[6] = $_POST["t7"];
$param[7] = $_POST["t8"];
$param[8] = $_POST["t9"];
$param[9] = $_POST["t10"];

for($i = 0; $i<10; $i++) {
	if($param[$i] == "")
	{
		$param[$i] = "NULL";
	}
}

$hd = mysql_connect("localhost", "root","root") or die ("Unable to connect");

mysql_select_db("embedded", $hd) or die ("Unable to select database");

$query1 = "INSERT INTO `embedded`.`detail_bot` (`bot_id`, `task_id`, `param1`, `param2`, `param3`, `param4`, `param5`, `param6`, `param7`, `param8`, `param9`, `param10`) VALUES ('$bot_id', '$task_name', '$param[0]', '$param[1]', '$param[2]', '$param[3]', '$param[4]', '$param[5]', '$param[6]', '$param[7]', '$param[8]', '$param[9]' );";

//echo $query1;

$res1 = mysql_query("$query1") or die ("Unable to run query1");

echo "Your Bot ID is $bot_id, Please remember it or write it somewhere as you will need it for communicating bot<br />";
echo "Click <a href=./register_page.html>here</a> to add more functions"

?>
