
<?php
$hd = mysql_connect("localhost", "root","root") or die ("Unable to connect");

mysql_select_db("embedded", $hd) or die ("Unable to select database");


$query1 = "SELECT distinct(`bot_id`) from `embedded`.`detail_bot`;";

//echo $query1;
$string_to_be_passed;

$res1 = mysql_query("$query1") or die ("Unable to run query1");

  while ($row= mysql_fetch_array($res1)) {
  $title = $row["bot_id"];
  //$title2 = $row["location"] . " - " . $row["style_name"] . "<br />";
//  echo "$title,";
  $string_to_be_passed = $string_to_be_passed.$title.",";
  }
//  echo $string_to_be_passed;

  $string_to_be_passed2 = substr($string_to_be_passed, 0, -1);
  echo $string_to_be_passed2;

?>
