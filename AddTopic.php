 <?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");
    
    $subject = isset($_POST['subject']) ? $_POST['subject'] : null;
    $date = isset($_POST['date']) ? $_POST['date'] : null;
    $cat = isset($_POST['cat']) ? $_POST['cat'] : null;
    $name = isset($_POST['name']) ? $_POST['name'] : null;
    

    $response = array();
    $response["success"] = false;

    $statement = mysqli_prepare($con, "INSERT INTO topics (topic_subject, topic_cat, topic_name) VALUES (?, ?, ?)");
    
    mysqli_stmt_bind_param($statement, "sss", $subject, $cat, $name);
    mysqli_stmt_execute($statement);
    
    $n = $con->insert_id;
    
    $statement = mysqli_prepare($con, "SELECT topic_date FROM topics WHERE topic_id = ?");
    
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