<?php
 

class access {
        var $host = null;
        var $user = null;
        var $password = null;
        var $name = null;
        var $conn = null;
        var $result = null;
        
    function __construct($dbhost , $dbuser , $dbpass , $dbname) {
        $this->host = $dbhost;
        $this->user = $dbuser;
        $this->password = $dbpass;
        $this->name = $dbname;
    }

    public function connect() {
        
        $this->conn = new mysqli($this->host , 
                                 $this->user , 
                                 $this -> password , 
                                 $this->name);
        
        if (mysqli_connect_errno()) {
            echo "Could Not Connect to Database";
        }
        
        
        $this->conn->set_charset("utf-8");       
    }
    
      function disconnect() {
        if ($this->conn != null) {
            $this->conn->close();
        }
      }


     function getLogFileWithId($file_name,$sorgu_type) {
        $returnArray = array();
         if($sorgu_type == 1)
            $sql = "SELECT * FROM parse_tbl where file_name = '$file_name'";
         elseif($sorgu_type == 2)
            $sql = "SELECT * FROM bitrate_tbl where file_name ='$file_name'";
         elseif($sorgu_type == 3)
             $sql = "SELECT * FROM browser_tbl where file_name = '$file_name'";
         elseif($sorgu_type = 4)
             $sql = "SELECT * FROM http_status_tbl where file_name = '$file_name'";
        
            $result = $this->conn->query($sql);
         
            $i = 0;
        if   ($result != null && (mysqli_num_rows($result) >= 1)) {
            while( $row = $result->fetch_array(MYSQLI_ASSOC) ){
                if (!empty($row)) {
                    $returnArray[$i] = $row;
                    $i++;
                } else {
                    $returnArray["status"] = "400"; 
                    $returnArray["Error"] = "File Not Found";    
                }
            }         
            
        }      
        return $returnArray;
     }
    
    function getFileNames($files){
        $returnArray = array();
         if($files == 1){
            $sql = "SELECT DISTINCT file_name FROM parse_tbl";
             $result = $this->conn->query($sql);
             
                 $i = 0;
        if   ($result != null && (mysqli_num_rows($result) >= 1)) {
            while( $row = $result->fetch_array(MYSQLI_ASSOC) ){
                if (!empty($row)) {
                    $returnArray[$i] = $row;
                    $i++;
                } else {
                    $returnArray["status"] = "400"; 
                    $returnArray["Error"] = "File Not Found";    
                }
            }         
            
        }      
        return $returnArray;
         }
            
    }
    
}  

?>