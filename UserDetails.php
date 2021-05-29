<?php

include('connectionDB.php');

$user_name=$_REQUEST['user_name'];
$email_id=$_REQUEST['email_id'];
$contact_number=$_REQUEST['contact_number'];
$date_of_birth=$_REQUEST['date_of_birth'];
$address=$_REQUEST['address'];
$city=$_REQUEST['city'];
$state=$_REQUEST['state'];
$country=$_REQUEST['country'];
$Pincode=$_REQUEST['Pincode'];

if($con)
{
        $sql="insert into UserDetails (user_name,email_id,contact_number,date_of_birth,
        address,city,state,country,Pincode) values ('$user_name','$email_id','$contact_number','$date_of_birth','$address','$city','$state','$country','$Pincode')";
        if(mysqli_query($con,$sql)){
            echo json_encode(array('response'=>"User Successfully Inserted"));
        }else{
            echo json_encode(array('response'=>"Failed, try again after sometime"));
        }
}else{
    $status = "Connection Failed";
    echo json_encode(array('response'=>"Connection Failed"));
}
mysqli_close($con);
?>