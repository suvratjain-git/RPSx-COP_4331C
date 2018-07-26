$(document).ready(function(){
    $("button.submit").click(function(){
        $("#logBox").toggle("fast");
    });
});

$(document).ready(function(){
    $("button.qrButton").click(function(){
        $(".qrCode").toggle("fast");
    });
});
