
<?php
$bot_id = $_POST["bot_id"];

$hd = mysql_connect("localhost", "root","root") or die ("Unable to connect");
mysql_select_db("embedded", $hd) or die ("Unable to select database");


$query1 = "SELECT * from `embedded`.`messagepass` where `bot_id` = '$bot_id';";
$res1 = mysql_query("$query1") or die ("Unable to run query1");		

while($row = mysql_fetch_assoc($res1) ){
	$message = $result['message'];
	echo $message;
}

$query2 = "DELETE from `embedded`.`messagepass` where `messagepass`.`bot_id` = '$bot_id';";
//echo $query2;
$res2 = mysql_query("$query2") or die ("Unable to run query1");		

?>
