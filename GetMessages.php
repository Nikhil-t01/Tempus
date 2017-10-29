<?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");

    
    $topic_id = isset($_POST['topic_id']) ? $_POST['topic_id'] : null;
    
    $statement = mysqli_prepare($con, "SELECT post_content, post_user, post_date FROM posts WHERE post_topic = ?");
    mysqli_stmt_bind_param($statement, "s", $topic_id);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $message, $ldap, $date);
    
    $temp = array();
    $temp["topic_id"] = $topic_id;
    $i = 0;
    
    while(mysqli_stmt_fetch($statement)){
        $temp["ldap"."$i"] = $ldap;
        $temp["message"."$i"] = $message;   
        $temp["date"."$i"] = $date;
        $i++;
    }
    $temp["count"] = $i;
    
    echo json_encode($temp);
   
?>