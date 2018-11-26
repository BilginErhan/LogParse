function StartChart(){
    var x = document.getElementById("file_name").innerHTML;
    var response = GetParsingData(x);
    var jsonArray = ParsingJsonData(response);
    var jsonData = JSON.parse(response);
    var arr = [];
    var status = document.getElementById('status');
    var status_ctx = status.getContext('2d');
    var browser = document.getElementById('browser');
    var browser_ctx = browser.getContext('2d');
    var bitrate_video = document.getElementById('bitrate_video');
    var video_ctx = bitrate_video.getContext('2d');
    
    
var trHTML = '';
$.each(jsonData[0], function (i, item) {
    trHTML += '<tr>' +
        '<td>' + item.file_name + '</td>' +
        '<td>' + item.most_user_ip + '</td>' +
        '<td>' + item.total_data + '</td>' +
        '<td>' + item.unique_content + '</td>' +
        '<td>' + item.unique_ip + '</td>' +
        '</tr>';
});

$('#Parse_tbl > tbody').append(trHTML);
    
    
    
    var video_chart = new Chart(video_ctx, {
        // The type of chart we want to create
        title:{
        text:"Video"
    },
        type: 'bar',
        // The data for our dataset
        data: {
                labels: jsonData[1].map(function(id){
                return id.bitrate }),

            datasets: [
                {   
                  label: "Bitrate Video",
                  backgroundColor : jsonData[1].map(function(id) {
                        return getRandomColor();
                        }),
                  data: jsonData[1].map(function(id) {
                      if(id.bitrate_type == "video")
                            return id.bitrate_rate;
                        }),
                },
                {
                  label: "Bitrate Audio",
                  backgroundColor : jsonData[1].map(function(id) {
                        return getRandomColor();
                        }),
                  data: jsonData[1].map(function(id) {
                      if(id.bitrate_type == "audio")
                            return id.bitrate_rate;
                        }),
                }
                    ]
        },
        options: {}
    });
    
    
    var browser_chart = new Chart(browser_ctx, {
        // The type of chart we want to create
        type: 'bar',
        // The data for our dataset
        data: {
            labels: jsonData[2].map(function(id){
            return id.browser_name }),

            datasets: [
                {
                  label: "Browser Rate",
                  backgroundColor : jsonData[2].map(function(id) {
                        return getRandomColor();
                        }),
                  data: jsonData[2].map(function(id) {
                        return id.browser_rate;
                        }),
                },
                    ]
        },
        options: {}
    });
    
    var status_chart = new Chart(status_ctx, {
        // The type of chart we want to create
        type: 'bar',
        // The data for our dataset
        data: {
            labels: jsonData[3].map(function(id){
            return id.status_type }),

            datasets: [
                {
                  label: "Http Status Rate",
                  backgroundColor : jsonData[3].map(function(id) {
                        return getRandomColor();
                        }),
                  data: jsonData[3].map(function(id) {
                        return id.status_rate;
                        }),
                },
                    ]
        },
        options: {}
    });



function getRandomColor() {
            var letters = '0123456789ABCDEF'.split('');
            var color = '#';
            for (var i = 0; i < 6; i++ ) {
                color += letters[Math.floor(Math.random() * 16)];
            }
            return color;
                }

function ParsingJsonData(response){
    var jsonData = JSON.parse(response);
    for (var i = 0; i < jsonData.length; i++) {
    var counter = jsonData[i];
    for(var j = 0; j<jsonData[i].length;j++){
        var counter1 = jsonData[i][j];
        console.log(counter1.counter_name);
    }
    
}
}
    
    
    function GetParsingData(x){
    var xmlHttp = new XMLHttpRequest();
    var url = "http://localhost/ErhanBilginProje_Web/get_log_statistic.php?file_name=" + x;
        
    xmlHttp.open( "GET", url, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
}