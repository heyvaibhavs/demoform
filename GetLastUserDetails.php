<?php
include('connectionDB.php');


if ($con->connect_error) {
  die("Connection failed: " . $con->connect_error);
}
    $result = array();
    $result['LastUserDetails'] = array();
    $select= "SELECT * FROM `UserDetails` ORDER BY user_id DESC LIMIT 1 ";
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
    
        array_push($result['LastUserDetails'], $index);
    }
  $result["Status"]="Success";
 echo json_encode($result);

$con->close();
?>