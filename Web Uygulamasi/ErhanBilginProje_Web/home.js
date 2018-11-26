function Start(){
    var response = GetParsingData();
    var jsonData = JSON.parse(response);  
    
    
    var trHTML = '';
    $.each(jsonData, function (i, item) {
    trHTML += '<tr>' +
        '<td><a href = "statistic.php?file_name=' + item.file_name + '">'+item.file_name+'</a></td>' +
        '</tr>';
});

$('#Parse_tbl > tbody').append(trHTML);
    
    

    function GetParsingData(){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", "http://localhost/ErhanBilginProje_Web/get_file_names.php?files=1", false ); // false for synchronous request
        xmlHttp.send( null );
        return xmlHttp.responseText;
    }
    
}