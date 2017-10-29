<?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");
    
    $ldap = isset($_POST['ldap']) ? $_POST['ldap'] : null;
    $password = isset($_POST['password']) ? $_POST['password'] : null;
    

    $statement1 = mysqli_prepare($con, "SELECT * FROM info_user WHERE ldap = ?");
    mysqli_stmt_bind_param($statement1, "s", $ldap);
    mysqli_stmt_execute($statement1);
    
    mysqli_stmt_store_result($statement1);
    mysqli_stmt_bind_result($statement1, $userID, $name, $ldap, $password, $courses, $dept, $user_events, $pending_events);


    $statement2 = mysqli_prepare($con, "SELECT * FROM info_user WHERE ldap = ? AND password = ?");
    mysqli_stmt_bind_param($statement2, "ss", $ldap, $password);
    mysqli_stmt_execute($statement2);
    
    mysqli_stmt_store_result($statement2);
    mysqli_stmt_bind_result($statement2, $userID, $name, $ldap, $password, $courses, $dept, $user_events, $pending_events);
    
    $response = array();
    $response["success"] = 0;  


    while(mysqli_stmt_fetch($statement1)){
        $response["success"] = 2;  
        $response["name"] = $name;
        $response["password"] = $password;
        $response["ldap"] = $ldap;
        $response["courses"] = $courses;
        $response["dept"] = $dept;
        $response["events"] = $user_events;
        $response["pending_events"] = $pending_events;
    }
    while(mysqli_stmt_fetch($statement2)){
        $response["success"] = 1;  
        $response["name"] = $name;
        $response["password"] = $password;
        $response["ldap"] = $ldap;
        $response["courses"] = $courses;
        $response["dept"] = $dept;
        $response["events"] = $user_events;
        $response["pending_events"] = $pending_events;        
    }
    
    echo json_encode($response);
?>