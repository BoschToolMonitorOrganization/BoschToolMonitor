var valueStream = $("#valueStream").val();
var productionLine = $("#productionLine").val();
var repairCategory = $("#repairCategory").val();

$.ajaxSetup({
    async: false
});

function setValueStream() {
    valueStream = $("#valueStream").val();
}

function setProductionLine() {
    productionLine = $("#productionLine").val();
}

function setRepairCategory() {
    repairCategory = $("#repairCategory").val();
}

function updateProductionLines() {
    $.get("/BoschToolMonitor/productionLines?valueStream=" + valueStream, function (data) {
        $("#productionLine").empty();
        productionLine = data[0];
        data.forEach(function (item, i) {
            var option = "<option>" + item + "</option>";
            $("#productionLine").append(option);
        });
    });
};

function updateProductTypes() {
    $.get("/BoschToolMonitor/productTypes?valueStream=" + valueStream + '&productionLine=' + productionLine, function (data) {
        $("#productType").empty();
        data.forEach(function (item, i) {
            var option = "<option>" + item + "</option>";
            $("#productType").append(option);
        });
    });
};

function updateRepairCategories() {
      $.get("/BoschToolMonitor/repairCategories?valueStream=" + valueStream + '&productionLine=' + productionLine, function (data) {
          $("#repairCategory").empty();
          repairCategory = data[0];
          data.forEach(function (item, i) {
              var option = "<option>" + item + "</option>";
              $("#repairCategory").append(option);
          });
      });
};

function updateRepairDetails() {
    $.get("/BoschToolMonitor/repairDetails?valueStream=" + valueStream + '&productionLine=' + productionLine + '&repairCategory=' + repairCategory, function (data) {
        $("#repairDetail").empty();
        data.forEach(function (item, i) {
            var option = "<option>" + item + "</option>";
            $("#repairDetail").append(option);
        });
    });
};