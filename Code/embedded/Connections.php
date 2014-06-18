<?php $hostname_localhost ="localhost";
$database_localhost ="embedded";
$username_localhost ="root";
$password_localhost ="root";
 
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);
?>
