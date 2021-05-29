<?php
include('connectionDB.php');


if ($con->connect_error) {
  die("Connection failed: " . $con->connect_error);
}
    $key = SHA1(MD5('Foodiezzkey'),256);
    $result = array();
    $result['LastUserKYC'] = array();
    $select= "SELECT 
    user_id,
    convert(AES_DECRYPT(gstin_no,'$key'),varchar(15)) as gstin_no,
    convert(AES_DECRYPT(pan_no,'$key'),varchar(10)) as pan_no,
    convert(AES_DECRYPT(aadhaar_no,'$key'),varchar(12)) as aadhaar_no,
    convert(AES_DECRYPT(driving_licence,'$key'),varchar(16)) as driving_licence,
    convert(AES_DECRYPT(voter_id,'$key'),varchar(10)) as voter_id,
    convert(AES_DECRYPT(upi_id,'$key'),varchar(30)) as upi_id 
    FROM `UserKYC` ORDER BY user_id DESC LIMIT 1 ";
    $response = mysqli_query($con,$select);

  // output data of each row
  while($row = mysqli_fetch_array($response))
    {
        $index['user_id'] = $row['user_id'];
        $index['gstin_no'] = $row['gstin_no'];
        $index['pan_no'] = $row['pan_no'];
        $index['aadhaar_no'] = $row['aadhaar_no'];
        $index['driving_licence'] = $row['driving_licence'];
        $index['voter_id'] = $row['voter_id'];
        $index['upi_id'] = $row['upi_id'];
    
        array_push($result['LastUserKYC'], $index);
    }
  $result["Status"]="Success";
 echo json_encode($result);

$con->close();
?>