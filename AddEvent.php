 <?php
    error_reporting(E_ALL ^ E_WARNING);
    $con = mysqli_connect("localhost", "id3099371_tempus", "machautempus", "id3099371_user");
    
    $category = isset($_POST['category']) ? $_POST['category'] : null;
    $description = isset($_POST['description']) ? $_POST['description'] : null;
    $name = isset($_POST['name']) ? $_POST['name'] : null;
    $stime = isset($_POST['stime']) ? $_POST['stime'] : null;
    $etime = isset($_POST['etime']) ? $_POST['etime'] : null;
    
    $response = array();
    $response["success"] = false;

    $statement = mysqli_prepare($con, "INSERT INTO events (event_cat,  event_content, event_user, event_stime, event_etime) VALUES (?, ?, ?, ?, ?)");
    
    mysqli_stmt_bind_param($statement, "sssss", $category, $description, $name, $stime, $etime);
    mysqli_stmt_execute($statement);
    
    $n = $con->insert_id;
      
    $response["id"] = $n;
    echo json_encode($response);
?>