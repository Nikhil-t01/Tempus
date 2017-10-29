<?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");

    $dept = isset($_POST['dept']) ? $_POST['dept'] : null;
    $id = isset($_POST['event_id']) ? $_POST['event_id'] : null;
    
    $cid = "";
    
    $st = mysqli_prepare($con, "SELECT dept_id FROM info_dept WHERE dept_name = ?");
    mysqli_stmt_bind_param($st, "s", $dept);
    mysqli_stmt_execute($st);
    mysqli_stmt_store_result($st);
    mysqli_stmt_bind_result($st, $cids);
    
    while(mysqli_stmt_fetch($st)){
        $cid = $cids;
    }
    
    if($cid<10)
        $cid = "0".$cid;
    
    $cid = "%".$cid."%";
    
    $statement = mysqli_prepare($con, "UPDATE info_dept SET dept_events = CONCAT(dept_events, '$id')  WHERE dept_name = '$dept' ");
    mysqli_stmt_bind_param($statement);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement);
    
    $statement2 = mysqli_prepare($con, "UPDATE info_user SET pending_events = CONCAT(pending_events, '$id') WHERE dept_name = '$dept' ");
    mysqli_stmt_bind_param($statement2);
    mysqli_stmt_execute($statement2);
    mysqli_stmt_store_result($statement2);
    mysqli_stmt_bind_result($statement2);
    
    $response = array();
    $response['cid'] = $cid;
    echo json_encode($response);
?>