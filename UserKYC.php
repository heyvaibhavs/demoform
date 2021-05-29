<?php

include('connectionDB.php');

$user_id=$_REQUEST['user_id'];
$gstin_no=$_REQUEST['gstin_no'];
$pan_no=$_REQUEST['pan_no'];
$aadhaar_no=$_REQUEST['aadhaar_no'];
$driving_licence=$_REQUEST['driving_licence'];
$voter_id=$_REQUEST['voter_id'];
$upi_id=$_REQUEST['upi_id'];
$key = SHA1(MD5('Foodiezzkey'),256);

if($con)
{
        $sql="insert into UserKYC (user_id,gstin_no,pan_no,aadhaar_no,
        driving_licence,voter_id,upi_id) values ('$user_id',
        AES_ENCRYPT('$gstin_no','$key'),
        AES_ENCRYPT('$pan_no','$key'),
        AES_ENCRYPT('$aadhaar_no','$key'),
        AES_ENCRYPT('$driving_licence','$key'),
        AES_ENCRYPT('$voter_id','$key'),
        AES_ENCRYPT('$upi_id','$key'))";
        if(mysqli_query($con,$sql)){
            echo json_encode(array('response'=>"KYC Successfull"));
        }else{
            echo json_encode(array('response'=>"Failed, try again after sometime"));
        }
}else{
    $status = "Connection Failed";
    echo json_encode(array('response'=>"Connection Failed"));
}
mysqli_close($con);
?>