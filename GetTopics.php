<?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");

    $cat = isset($_POST['cat']) ? $_POST['cat'] : null;
    
    $statement = mysqli_prepare($con, "SELECT topic_id, topic_subject, topic_date, topic_cat, topic_name FROM topics WHERE topic_cat = ?");
    mysqli_stmt_bind_param($statement, "s", $cat);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $subject, $date, $cat, $name);
    
    $temp = array();
    $i = 0;
    
    while(mysqli_stmt_fetch($statement)){
        $temp["id"."$i"] = $id;
        $temp["cat"."$i"] = $cat;
        $temp["date"."$i"] = $date;
        $temp["subject"."$i"] = $subject;
        $temp["name"."$i"] = $name;
        $i++;
    }
    $temp["count"] = $i;
    
    echo json_encode($temp);
   
?>