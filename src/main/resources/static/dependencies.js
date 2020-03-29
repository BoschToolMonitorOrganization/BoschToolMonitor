var valueStream = $("#valueStream").val();
var productionLine = $("#productionLine").val();
var repairCategory = $("#repairCategory").val();

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
            $("#repairCategory").empty();
            $("#repairDetail").empty();
            data.forEach(function (item, i) {
                var option = "<option>" + item + "</option>";
                $("#productType").append(option);
            });
            data.forEach(function (item, i) {
                var option = "<option>" + item + "</option>";
                $("#repairCategory").append(option);
                if (i == 0){
                    repairCategory = $("#repairCategory").val();
                }
            });
        });
        $.get("/BoschToolMonitor/productTypes?valueStream=" + valueStream + '&productionLine=' + productionLine + '&repairCategory' + repairCategory, function (data) {
            $("#repairDetail").empty();
                data.forEach(function (item, i) {
                    var option = "<option>" + item + "</option>";
                    $("#repairDetail").append(option);
                });
            });
    });
};

function sendAjaxRequestProductionLine() {
    valueStream = $("#valueStream").val();
    productionLine = $("#productionLine").val();
    $.get("/BoschToolMonitor/productTypes?valueStream=" + valueStream + '&productionLine=' + productionLine, function (data) {
        $("#productType").empty();
        $("#repairCategory").empty();
        $("#repairDetail").empty();
        data.forEach(function (item, i) {
            var option = "<option>" + item + "</option>";
            $("#productType").append(option);
        });
        data.forEach(function (item, i) {
            var option = "<option>" + item + "</option>";
            $("#repairCategory").append(option);
            if (i == 0){
                repairCategory = $("#repairCategory").val();
            }
        });
        data.forEach(function (item, i) {
            var option = "<option>" + item + "</option>";
            $("#repairDetail").append(option);
        });
    });
};

function sendAjaxRequestRepairCategory() {
      valueStream = $("#valueStream").val();
      productionLine = $("#productionLine").val();
      repairCategory = $("#repairCategory").val();
      $.get("/BoschToolMonitor/productTypes?valueStream=" + valueStream + '&productionLine=' + productionLine + '&repairCategory' + repairCategory, function (data) {
          $("#repairDetail").empty();
          data.forEach(function (item, i) {
              var option = "<option>" + item + "</option>";
              $("#repairDetail").append(option);
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
    $("#repairCategory").change(function () {
        sendAjaxRequestRepairCategory();
    });
    $("#nav-placeholder").load("/BoschToolMonitor/NavigationBar");
});