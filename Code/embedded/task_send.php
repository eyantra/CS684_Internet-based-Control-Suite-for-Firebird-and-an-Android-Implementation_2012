
<?php
$bot_id = $_POST["bot_id"];

$hd = mysql_connect("localhost", "root","root") or die ("Unable to connect");

mysql_select_db("embedded", $hd) or die ("Unable to select database");


$query1 = "SELECT `task_id` from `embedded`.`detail_bot` where `bot_id`='$bot_id';";

//echo $query1;
$string_to_be_passed;

$res1 = mysql_query("$query1") or die ("Unable to run query1");

  while ($row= mysql_fetch_array($res1)) {
  $title = $row["task_id"];
  $string_to_be_passed = $string_to_be_passed.$title.",";
  }
//  echo $string_to_be_passed;

  $string_to_be_passed2 = substr($string_to_be_passed, 0, -1);
  echo $string_to_be_passed2;

?>


