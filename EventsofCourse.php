<?php
    // error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");
    
    $name = isset($_POST['name']) ? $_POST['name'] : null;
    
    $statement = mysqli_prepare($con, "SELECT course_events FROM info_courses WHERE name = ?");
    mysqli_stmt_bind_param($statement, "s", $name);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $events);
    
    $response = array();
    while(mysqli_stmt_fetch($statement)) {
        $response["courseevents"] = $events;
    }
   
    echo json_encode($response); 
?>