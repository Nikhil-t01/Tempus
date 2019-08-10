<?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");

    $courses = isset($_POST['courses']) ? $_POST['courses'] : null;
    $ldap = isset($_POST['ldap']) ? $_POST['ldap'] : null;
    $events = isset($_POST['events']) ? $_POST['events'] : null;
    $pEvents = isset($_POST['pending_events']) ? $_POST['pending_events'] : null;
    
    $response = array();
    $response["success"] = false;

    $statement = mysqli_prepare($con, "UPDATE info_user SET courses = ?, user_events = ?, pending_events = ? WHERE ldap = ?");
    
    mysqli_stmt_bind_param($statement, "ssss", $courses, $events, $pEvents, $ldap);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement);
   
    $response["success"] = true;  
    echo json_encode($response);
?>