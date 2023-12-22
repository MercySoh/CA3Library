var x = 100;

var interval = setInterval(
    function() {
        var div = document.getElementById("moving-div");

        div.style.left = x + "vw";
        x -= 0.125;

        if(x < -100){
            x = 100;
        }
        },
    1
);