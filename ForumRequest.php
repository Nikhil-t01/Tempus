<?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");

    $topic_id = isset($_POST['topic_id']) ? $_POST['topic_id'] : null;
    $ldap = isset($_POST['ldap']) ? $_POST['ldap'] : null;
    $content = isset($_POST['post_content']) ? $_POST['post_content'] : null;
    
    $statement = mysqli_prepare($con, "INSERT INTO posts (post_topic, post_user, post_content) VALUES (?, ?, ?)");
    
    mysqli_stmt_bind_param($statement, "sss", $topic_id, $ldap, $content);
    mysqli_stmt_execute($statement);

    $n = $con->insert_id;
    $response = array();
    
    $statement = mysqli_prepare($con, "SELECT post_date FROM posts WHERE post_id = ?");
    
    mysqli_stmt_bind_param($statement, "i", $n);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $date);

    $response["success"] = true;  
    $response["id"] = $n;
    while(mysqli_stmt_fetch($statement)){
        $response["date"] = $date;
    }
    echo json_encode($response);
?>