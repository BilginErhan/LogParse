<?php

if (empty($_GET["files"])) {
    $returnArray["status"] = "400";
    $returnArray["Error"] = "File Not Found";
    echo json_encode($returnArray);
}else{
    
$files = htmlentities($_GET["files"]);

$file = parse_ini_file("log.ini");
    
    $host = trim($file["dbhost"]);
    $user = trim($file["dbuser"]);
    $pass = trim($file["dbpass"]);
    $name = trim($file["dbname"]);

    
    require("access_log_database.php");
    $access = new access($host , $user , $pass , $name);
    $access-> connect();

    $file_names = $access -> getFileNames($files);
    if (!empty($file_names)){
    echo json_encode($file_names);
                 }
    $access-> disconnect();
}
?>