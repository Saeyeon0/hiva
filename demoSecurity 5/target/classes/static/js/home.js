var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];

btn.onclick = function () {
    modal.style.display = "block";
}

span.onclick = function () {
    modal.style.display = "none";
}

window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

var modals = document.getElementById("Modal");
var btn = document.getElementById("myButton");

var span = document.getElementsByClassName("closed")[0];

btn.onclick = function () {
    modals.style.display = "block";d89;m;dr4ee0eri
}

span.onclick = function () {
    modals.style.display = "none";
}

window.onclick = function (event) {
    if (event.target == modal) {
        modals.style.display = "none";
    }
}

