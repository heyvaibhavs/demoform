<?php
include('connectionDB.php');


if ($con->connect_error) {
  die("Connection failed: " . $con->connect_error);
}
    $key = SHA1(MD5('Foodiezzkey'),256);
    $result = array();
    $result['UserDetails'] = array();
    $select= "select ud.*,
    convert(AES_DECRYPT(uk.gstin_no,'$key'),varchar(15)) as gstin_no,
    convert(AES_DECRYPT(uk.pan_no,'$key'),varchar(10)) as pan_no,
    convert(AES_DECRYPT(uk.aadhaar_no,'$key'),varchar(12)) as aadhaar_no,
    convert(AES_DECRYPT(uk.driving_licence,'$key'),varchar(16)) as driving_licence,
    convert(AES_DECRYPT(uk.voter_id,'$key'),varchar(10)) as voter_id,
    convert(AES_DECRYPT(uk.upi_id,'$key'),varchar(30)) as upi_id 
    from UserDetails ud left outer join UserKYC uk on ud.user_id = uk.user_id ";
    $response = mysqli_query($con,$select);

  // output data of each row
  while($row = mysqli_fetch_array($response))
    {
        $index['user_id'] = $row['user_id'];
        $index['user_name'] = $row['user_name'];
        $index['email_id'] = $row['email_id'];
        $index['contact_number'] = $row['contact_number'];
        $index['date_of_birth'] = $row['date_of_birth'];
        $index['address'] = $row['address'];
        $index['city'] = $row['city'];
        $index['state'] = $row['state'];
        $index['country'] = $row['country'];
        $index['Pincode'] = $row['Pincode'];
        $index['gstin_no'] = $row['gstin_no'];
        $index['pan_no'] = $row['pan_no'];
        $index['aadhaar_no'] = $row['aadhaar_no'];
        $index['driving_licence'] = $row['driving_licence'];
        $index['voter_id'] = $row['voter_id'];
        $index['upi_id'] = $row['upi_id'];
    
        array_push($result['UserDetails'], $index);
    }
  $result["Status"]="Success";
 echo json_encode($result);

$con->close();
?>