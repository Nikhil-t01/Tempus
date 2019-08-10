<?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");

    $name = isset($_POST['name']) ? $_POST['name'] : null;
    $ldap = isset($_POST['ldap']) ? $_POST['ldap'] : null;
    $courses = isset($_POST['courses']) ? $_POST['courses'] : null;
    $password = isset($_POST['password']) ? $_POST['password'] : null;
    $passwordC = isset($_POST['passwordC']) ? $_POST['passwordC'] : null;
    $dept = isset($_POST['dept']) ? $_POST['dept'] : null;
    $pending_events = isset($_POST['pending_events']) ? $_POST['pending_events'] : null;
    $response = array();
    $response["success"] = false;
    if(empty($name)){$response["success"] = false;}
    else {
    if($password == $passwordC){
    $statement = mysqli_prepare($con, "INSERT INTO info_user (name, ldap, password, courses, Department, pending_events) VALUES (?, ?, ?, ?, ?, ?)");
    
    mysqli_stmt_bind_param($statement, "ssssss", $name, $ldap, $password, $courses, $dept, $pending_events);
    mysqli_stmt_execute($statement);
   
    $response["success"] = true;  
    }}
    echo json_encode($response);
?>