function getUriForListEntities(uri, filterBy, filter, pageNumber, pageSize) {
    return uri
        + filterBy
        + '='
        + filter
        + '&page='
        + pageNumber
        + '&size='
        + pageSize
}


async function getListEntities(uri) {
    let t = await (await fetch(uri)).json()
    return t
}

function createMachineTableForUser(bodyTable, machineList) {
    $('#' + bodyTable).empty()
    $.each(machineList, function (index, value) {
        $('#' + bodyTable).append(
            "<tr>" +
            "<th scope='row'>"+
            "<input class='form-check-input' type='radio' name='machineRadioNewAct' id='machineRadioNewAct' value='" + value.id + "'>"+
            " </th>"+
            "<td>" + value.type + "</td>" +
            "<td>" + value.serialNumber + "</td>" +
            "<td>" + value.operatingTime + "</td>" +
            "</tr>"
        )
    })
}

function getWeekDay(day){
    const dayOfWeek = ["Пн","Вт","Ср","Чт","Пт","Сб","Вс",]
    return dayOfWeek[day - 1];
}