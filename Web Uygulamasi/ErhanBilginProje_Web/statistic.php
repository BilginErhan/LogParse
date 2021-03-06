<html>
    <head><title>Charting Log Files</title>
        <meta charset="utf-8">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
        <script src = "MyChart.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body onload="StartChart()">
    <header>
      <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
          <div class="row">
            <div class="col-sm-8 col-md-7 py-4">
              <h4 class="text-white">Hakkında</h4>
              <p class="text-muted">Servera düşen isteklerin parse edilerek analiz edilmesi</p>
            </div>
            <div class="col-sm-4 offset-md-1 py-4">
              <h4 class="text-white">İletişim : 0 538 044 23 72</h4>
              <ul class="list-unstyled">
                <li><a href="https://www.linkedin.com/in/erhan-bilgin-52a86298/" target="_blank" class="text-white">Linkedin</a></li>
                <li><a href="mailto:bilginerhna95@gmail.com" class="text-white">Email</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container d-flex justify-content-between">
          <a href="index.php" class="navbar-brand d-flex align-items-center">
            
            <strong>Parse Chart</strong>
          </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
      </div>
        <style>
            div{
                margin-top: 10px;
            }
        </style>
    </header>
        
        
        
        <div class="album py-10 bg-light">
        <div class="container">
            
            <div class="container">
                <table class = "table table-striped" id="Parse_tbl" data-height="460">
                <thead>
                    <tr>
                        <th>Dosya İsmi</th>
                        <th>En Çok İzleme yapan Kullanıcı</th>
                        <th>Toplam Yollanan Veri(GB)</th>
                        <th>Toplam Unique İçerik </th>
                        <th>Toplam Unique Ip</th>
                    </tr>
                </thead>
                <tbody id="tablebody" style="text-align: center;"></tbody>
            </table>
            </div>

          <div class="row top5" >
                <div class="col-md-10">
                  <div class="card mb-10 shadow-sm">
                      <canvas id = "bitrate_video"></canvas>
                    <div class="card-body">
                        <p class="card-text"><center><strong>Bitrate Analiz Grafiği</strong></center></p>
                      <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
            </div>

            <div class = "row">
                <div class="col-md-10">
                  <div class="card mb-10 shadow-sm">
                      <canvas id = "browser"></canvas>
                    <div class="card-body">
                        <p class="card-text"><center><strong>Browser Analiz Grafiği</strong></center></p>
                    </div>
                  </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10">
                  <div class="card mb-10 shadow-sm">
                      <canvas id = "status"></canvas>
                    <div class="card-body">
                        <p class="card-text"><center><strong>Http Status Grafiği</strong></center></p>
                    </div>
                  </div>
                </div>
            </div>
        </div>
        </div>
        
        <p id= "file_name" style="visibility: hidden;"><?php
                if (empty($_GET["file_name"])) {
                $returnArray["status"] = "400";
                $returnArray["Error"] = "File Not Found";
                echo json_encode($returnArray);
            }else{

            echo htmlentities($_GET["file_name"]);
            }
    ?></p>
    </body>
</html>