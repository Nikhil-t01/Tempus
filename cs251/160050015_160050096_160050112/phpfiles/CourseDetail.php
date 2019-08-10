<?php
    // error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");

    $course_id = isset($_POST['course_id']) ? $_POST['course_id'] : null;

    $statement = mysqli_prepare($con, "SELECT * FROM info_courses WHERE course_id = ?");
    mysqli_stmt_bind_param($statement, "i", $course_id);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $a, $b, $c, $d, $e, $f, $g, $h, $i, $j, $k);

    $response = array();
    while(mysqli_stmt_fetch($statement)) {
        $response["mon"] = $b;
        $response["tue"] = $c;
        $response["wed"] = $d;
        $response["thu"] = $e;
        $response["fri"] = $f;
        $response["sat"] = $g;
        $response["sun"] = $h;
        $response["name"] = $i;
        $response["venue"] = $j;
       
    }
   
    echo json_encode($response);
?>