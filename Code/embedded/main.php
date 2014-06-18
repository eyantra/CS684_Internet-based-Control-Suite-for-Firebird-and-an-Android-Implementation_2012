<?php require_once('./Connections.php'); mysql_select_db($database_localhost,$localhost);
 
$UserEmail = $_POST['UserEmail'];
$Password = $_POST['Password'];
 
 $query_search = "select * from `embedded`.`users` where `username` = '$UserEmail' AND `password` = '$Password';";
 $query_exec = mysql_query($query_search) or die(mysql_error());
 $rows = mysql_num_rows($query_exec);
 

 if($rows --> 0) { echo "Y";}
else  {echo "N"; } 
?>
