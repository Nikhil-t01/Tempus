<?php
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");
    
    $dept = isset($_POST['dept']) ? $_POST['dept'] : null;
    
    $statement = mysqli_prepare($con, "SELECT dept_id, dept_events FROM info_dept WHERE dept_name = ?");
    mysqli_stmt_bind_param($statement, "s", $dept);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $dept_id, $dept_events);
    $response = array();
    while(mysqli_stmt_fetch($statement)){
        $response['dept_id'] = $dept_id;
        $response['dept_events'] = $dept_events;
    }
    echo json_encode($response);
?>