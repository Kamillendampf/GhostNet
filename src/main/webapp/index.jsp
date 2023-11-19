<jsp:useBean id="handel" class="de.ibapp.ghostnet.Main" scope="request"></jsp:useBean>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>GhostNetFishing</title>
    <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" href="ghostNetOverView.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
          integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
          crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
            integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
            crossorigin=""></script>
    <style>

        #map {
            height: 100%;
            width: 100%;
            position: relative;
        }

        body {
            margin: 0 auto;
            background-color: beige;
            width: 100%;
            height: 930px;
            overflow: hidden;
        }


        .overView {
            z-index: auto;
            width: 30%;
            height: 100%;
            float: right;
            overflow: scroll;
            overflow-x: hidden;
            background-color: beige;
            border-radius: 15px;
            border: 2px solid rgb(245, 222, 179);
            padding: 0;
        }

        .container {
            width: 100%;
            position: absolute;
            z-index: 999;
        }
    </style>

</head>


<body style="height: 950px;" onload="loadDoc(); loadMap(); getLocation();">


<div class="container">

    <div class="overView">
        <jsp:include page="ghostNet.jsp"/>
    </div>
</div>
<div id="map"></div>


<script>
    function loadMap() {

        let map = L.map('map').setView([10.0, 100.0], 2);
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);
        for (var i = 0; i < document.getElementsByClassName("koords").length; i++) {

            if (document.getElementsByClassName("status")[i].title == 0) {
                let coord = document.getElementsByClassName("koords")[i].title.split(",")
                let marker = L.marker(coord).addTo(map);
            }
        }
        map.dragging.disable();
        map.doubleClickZoom.disable();
        map.scrollWheelZoom.disable();
    }
</script>

<script>
    function loadDoc() {
        console.log("Anfrage an den Server versendet.")
        const xhttp = new XMLHttpRequest();
        xhttp.open("GET", "main", false);
        xhttp.send();
        document.getElementsByTagName('body')[0].innerHTML = xhttp.responseText;
    }


</script>

</body>
</html>