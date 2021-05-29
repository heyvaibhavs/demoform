<?php

header('Access-Control-Allow-Origin: *');
header("Access-Control-Allow-Methods: HEAD, GET, POST, PUT, PATCH, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method,Access-Control-Request-Headers, Authorization");
header('Content-Type: application/json');

$host = "localhost";
$user = "u713325598_demoDb";
$password =  "demoDb@123";
$db = "u713325598_Demo";

$con = mysqli_connect($host,$user,$password,$db);

if(!$con)
{
    die("Error in connection " .mysqli_connect_error());
}
else
{
echo" " ;

}

?>