<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="ghostNetOverView.css">
</head>
<body onload="getLocation()">
<div style="background-color: beige; width: 100%; height: 965px; float: right; position: relative;" >
    <div class="container">
        <div>
            <form action="main" method="get">
                Koordinaten : (<input name="lat" class="text">, <input name="lon" class="text"> )
                Name: <input name="name" type="text" class="text">
                Telefonnummer: <input name="nummer" type="number" class="text">
                <button type="button" value="submit" class="submitBtn" onclick="console.log('submit wurde gedrueckt')"> Submit </button>
            </form>
        </div>
        <div class="ghostNetCartsContainer">
            GhostNetCarts
        </div>
    </div>
</div>

<script>
    const lat = document.getElementById("lat");
    const lon = document.getElementById("lon");
    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            x.innerHTML = "Geolocation is not supported by this browser.";
        }
    }

    function showPosition(position) {
        lat.value = position.coords.latitude;
          lon.value =  position.coords.longitude;
    }
</script>
</body>
</html>
