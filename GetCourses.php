<?php
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");
    
    $ldap = isset($_POST['ldap']) ? $_POST['ldap'] : null;
    
    $statement = mysqli_prepare($con, "SELECT courses FROM info_user WHERE ldap = ?");
    mysqli_stmt_bind_param($statement, "ss", $ldap, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $courses);
    
    $response = array();
    
    while(mysqli_stmt_fetch($statement)){
        $response["courses"] = $courses;
    }
    
    echo json_encode($response);
?>