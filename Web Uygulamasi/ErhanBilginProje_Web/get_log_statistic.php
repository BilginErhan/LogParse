<?php

if (empty($_GET["file_name"])) {
    $returnArray["status"] = "400";
    $returnArray["Error"] = "File Not Found";
    echo json_encode($returnArray);
}else{
    
$file_name = htmlentities($_GET["file_name"]);

$file = parse_ini_file("log.ini");
    
    $host = trim($file["dbhost"]);
    $user = trim($file["dbuser"]);
    $pass = trim($file["dbpass"]);
    $name = trim($file["dbname"]);

    
    require("access_log_database.php");
    $access = new access($host , $user , $pass , $name);
    $access-> connect();

    $parse_tbl = $access -> getLogFileWithId($file_name,1);
    $http_status_tbl = $access -> getLogFileWithId($file_name,2);
    $browser_tbl = $access -> getLogFileWithId($file_name,3);
    $bitrate_tbl = $access -> getLogFileWithId($file_name,4);
    if (!empty($parse_tbl)){
    echo '[' . json_encode($parse_tbl) . ' , ' .
        json_encode($http_status_tbl)  . ' , ' .
        json_encode($browser_tbl)      . ' , ' .
        json_encode($bitrate_tbl)      . ' ] ' ;
        }
    $access-> disconnect();
}
?>