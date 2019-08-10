<?php
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");
    
    $statement = mysqli_prepare($con, "SELECT event_id, event_user, event_content, event_cat, event_stime, event_etime FROM events");
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $user, $desc, $cat, $start, $end);
    
    $response = array();
    $i = 0;

    while(mysqli_stmt_fetch($statement)){
        $response["id"."$i"] = $id;
        $response["user"."$i"] = $user;   
        $response["desc"."$i"] = $desc;
        $response["cat"."$i"] = $cat;
        $response["start"."$i"] = $start;
        $response["end"."$i"] = $end;
        $i++;
    }
    $response["total"] = $i;
    
    echo json_encode($response);
?>