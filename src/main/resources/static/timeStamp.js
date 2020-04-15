function getTimeStamp() {
    var today = new Date();

    var year = today.getFullYear();

    var month = ((today.getMonth() + 1) < 10 ? '0' : '') + (today.getMonth() + 1);

    var date = (today.getDate() < 10 ? '0' : '') + today.getDate();

    var hours = ((today.getHours()) < 10 ? '0' : '') + (today.getHours());

    var minutes = ((today.getMinutes()) < 10 ? '0' : '') + (today.getMinutes());

    var amorpm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours == 0 ? 12 : hours;

    var dateTime = year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ' ' + amorpm;

    document.getElementById("timeStamp").value = dateTime;
}