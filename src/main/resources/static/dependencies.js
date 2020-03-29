var valueStream = $("#valueStream").val();
var productionLine = $("#productionLine").val();

function sendAjaxRequestValueStream() {
    valueStream = $("#valueStream").val();
    $.get("/BoschToolMonitor/productionLines?valueStream=" + valueStream, function (data) {
        $("#productionLine").empty();
        $("#productType").empty();
        data.forEach(function (item, i) {
            var option = "<option>" + item + "</option>";
            $("#productionLine").append(option);
            if (i == 0){
                productionLine = $("#productionLine").val();
            }
        });
        $.get("/BoschToolMonitor/productTypes?valueStream=" + valueStream + '&productionLine=' + productionLine, function (data) {
            $("#productType").empty();
            data.forEach(function (item, i) {
                var option = "<option>" + item + "</option>";
                $("#productType").append(option);
            });
        });
    });
};

function sendAjaxRequestProductionLine() {
    productionLine = $("#productionLine").val();
    valueStream = $("#valueStream").val();
    $.get("/BoschToolMonitor/productTypes?valueStream=" + valueStream + '&productionLine=' + productionLine, function (data) {
        $("#productType").empty();
        data.forEach(function (item, i) {
            var option = "<option>" + item + "</option>";
            $("#productType").append(option);
        });
    });
};

$(document).ready(function () {
    $("#valueStream").change(function () {
        sendAjaxRequestValueStream();
    });
    $("#productionLine").change(function () {
        sendAjaxRequestProductionLine();
    });
    $("#nav-placeholder").load("/BoschToolMonitor/NavigationBar");
});