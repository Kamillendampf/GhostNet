<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="o" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:c="http://java.sun.com/jsf/core">
<head>
    <title></title>
    <link rel="stylesheet" href="ghostNetOverView.css">
</head>
<body  ${Fail}>
<div style="background-color: beige; width: 100%; height: inherit; float: right; position: relative;">
    <div class="container">
        <div style="margin-top: 20px; justify-content: center;">
            <form action="lgen" method="get">
                <button>License Kaufen</button>
            </form>
            <form action="main" method="post">
                Koordinaten : (<label for="lat"></label><input id="lat" name="lat" type="text" class="text">,
                <input id="lon" name="lon" type="text" class="text"> )<br>
                Netzgroesse (in cm) : <label>
                <input name="size" type="number" class="text">
            </label> <br>
                Reporter Name: <label>
                <input name="name" type="text" class="text">
            </label><br>
                Telefonnummer (z.B. +49 123 1234567): <label>
                <input name="nummer" type="number" class="text">
            </label><br>
                <button name="submit"
                        style="background-color: beige; border-radius: 15px; border: 2px solid rgb(245,222,179); cursor: pointer;">
                    Submit
                </button>
            </form>
        </div>

        <div class="ghostNetCartsContainer">
            GhostNetCarts
            <div style=" height: inherit; -ms-overflow-y: hidden !important;">
                <o:forEach var="gnb" items="${gnbs}" varStatus="counter">
                <div style="margin-left: 20px; background-color: beige; border-radius: 15px; border: 2px solid rgb(245,222,179); margin-top: 10px; margin-right: 30px; text-align: left; padding: 10px">
                    Netz-ID:${gnb.id}
                    <div class="koords" title="${gnb.koordinaten}">Koordinaten : (<o:out
                            value="${gnb.koordinaten}"></o:out>)
                    </div>
                    <div>Size (in cm):<o:out value="${gnb.size}"></o:out></div>


                    <o:if test="${gnb.editor.equals('') && hasLicense}">
                        <form action="update" method="post">
                            <div>Editor:
                                <button name="id" value="${gnb.id}" type="submit">Eintragen</button>
                            </div>
                        </form>
                    </o:if>
                    <o:if test="${hasLicense}">
                        <div>Editor: <o:out value="${gnb.editor}"></o:out></div>
                    </o:if>
                    <o:if test="${!hasLicense}">
                        <div>Editor: <o:out value="${gnb.editor}"></o:out></div>
                    </o:if>


                    <div>Reporter <o:out value="${gnb.reporter}"></o:out></div>


                    <div class="status" title="${gnb.status}">
                        <o:if test="${gnb.status == 0}">Status: Gemeldet
                            <o:if test="${!hasLicense}">
                                <button name="id" value="${gnb.id}" onclick="drop(${gnb.id});">Verschollen melden</button>
                                <div id="${gnb.id}" style="display: none">
                                    <form action="update" method="get">Name:<input type="text" name="name"
                                                                               value="${gnb.id}"> <br>
                                        Telefonnummer: <input type="text" name="nummer">
                                        <button name="id" value="${gnb.id}">Submit</button>
                                    </form>
                                </div>
                            </o:if>
                            <o:if test="${hasLicense}">
                                <form action="update" method="get">
                                    <select name="status">
                                        <option value="0"> Gemeldet </option>
                                        <option value="2"> Geborgen</option>
                                        <option value="3"> Verschollen</option>
                                    </select>
                                    <button name="lid" value="${gnb.id}">Submit</button>
                                </form>
                            </o:if>
                        </o:if>
                    </div>
                    <o:if test="${gnb.status == 1}">Status: Bergung
                        <o:if test="${!hasLicense}">
                            <button name="id" value="${gnb.id}" onclick="drop(${gnb.id});">Verschollen melden</button>
                            <div id="${gnb.id}" style="display: none">
                                <form action="update" method="get">
                                    Name:<input type="text" name="name" value="${gnb.id}"> <br>
                                    Telefonnummer: <input type="text" name="nummer">
                                    <button name="id" value="${gnb.id}">Submit</button>
                                </form>
                            </div>
                        </o:if>
                        <o:if test="${hasLicense}">
                            <form action="update" method="get">
                                <select name="status">
                                    <option value="0"> Gemeldet</option>
                                    <option value="2"> Geborgen</option>
                                    <option value="3"> Verschollen</option>
                                </select>
                                <button name="lid" value="${gnb.id}">Submit</button>
                            </form>
                        </o:if>
                    </o:if>
                    <o:if test="${gnb.status == 2}"> Status: Geborgen <o:if test="${hasLicense}">
                        <form action="update" method="get">
                            <select name="status">
                                <option value="0"> Gemeldet</option>
                                <option value="2"> Geborgen</option>
                                <option value="3"> Verschollen</option>
                            </select>
                            <button name="lid" value="${gnb.id}">Submit</button>
                        </form>
                    </o:if></o:if>
                    <o:if test="${gnb.status == 3}">Status: Verschollen <o:if test="${hasLicense}">
                        <form action="update" method="get">
                            <select name="status">
                                <option value="0"> Gemeldet</option>
                                <option value="2"> Geborgen</option>
                                <option value="3"> Verschollen</option>
                            </select>
                            <button name="lid" value="${gnb.id}">Submit</button>
                        </form>
                    </o:if>
                    </o:if>
                </div>





            </div>
            </o:forEach>
        </div>
    </div>
</div>
</div>

<!------SCRIPTS----------->


<script>
    function getLocation() {
        const lat = document.getElementById("lat");
        const lon = document.getElementById("lon");
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("Etwas ist schief gelaufen!")
        }
    }

    function showPosition(position) {
        document.getElementById("lat").value = position.coords.latitude;
        document.getElementById("lon").value = position.coords.longitude;
    }
</script>
<script>
    function drop(id) {
        console.log(id)
        var element = document.getElementById(id);

        element.style.display = 'block';
    }
</script>


</body>
</html>
