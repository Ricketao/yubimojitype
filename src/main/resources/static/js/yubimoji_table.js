$(function () {
    $("td").click(function (e) {
        console.log(e.target.id, this);
        if (e.target.id != "") {
            // open video window
            $("body").append('<div id="bg">');
            $("body").append('<div id="photo">');
            $("#photo").hide();
            $("#photo").append('<video>');
            $("#photo video").attr("src", "./images/1moji/" + ($(this).attr("id").replace("fs-", "") + ".mp4"));
            $("#photo video").attr("width", "640");
            $("#photo video").attr("muted", true);
            $("#photo video").attr("autoplay", true);
            $("#bg").fadeIn(1000);
            $("#photo").fadeIn(1000);
            // close video window
            $("#bg").click(function () {
                $("#bg").fadeOut(1000, function () {
                    $("#bg, #photo").remove();
                });
                $("#photo").fadeOut(1000);
            });
        }
        return false;
    });
});