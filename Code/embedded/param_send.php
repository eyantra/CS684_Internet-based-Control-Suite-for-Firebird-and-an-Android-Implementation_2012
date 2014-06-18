
<?php
$bot_id = $_POST["bot_id"];
$task_id = $_POST["task_id"];

$hd = mysql_connect("localhost", "root","root") or die ("Unable to connect");

mysql_select_db("embedded", $hd) or die ("Unable to select database");


$query1 = "SELECT `bot_id`,`task_id`,`param1`,`param2`,`param3`,`param4`,`param5`,`param6`,`param7`,`param8`,`param9`,`param10` from `embedded`.`detail_bot` where `bot_id`='$bot_id' AND `task_id`='$task_id';";

//echo $query1;
$string_to_be_passed;

$res1 = mysql_query("$query1") or die ("Unable to run query1");

  while($row= mysql_fetch_array($res1)) {
  $title = $row["bot_id"];
  $string_to_be_passed = $string_to_be_passed.$title.",";

  $title = $row["task_id"];
  $string_to_be_passed = $string_to_be_passed.$title.",";

  for($i=1; $i<11; $i++){
  $title = $row["param$i"];
  if($title != "NULL"){
  $string_to_be_passed = $string_to_be_passed.$title.",";
  }
 }
}
//  echo $string_to_be_passed;

  $string_to_be_passed2 = substr($string_to_be_passed, 0, -1);
  echo $string_to_be_passed2;

?>


