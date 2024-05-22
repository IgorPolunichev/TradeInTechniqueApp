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

function createCountPage(totalPages, bodyCountPages){
    let i = 1
    const countPagesForPartList = $('#'+bodyCountPages)
    countPagesForPartList.empty()
    while (i <= totalPages) {
        countPagesForPartList.append("<li class='page-item'><a id='" + i + "'class='page-link' href='#'>" +
            i +
            "</a></li>")
        pageNumberNow = i
        i++;
    }
}

function createTableParts(listParts, nameColumns , bodyTable){
    $.each(nameColumns, function (i, v) {
        $('#' + bodyTable + '-thad').append(
            "<th scope='col'>" + v + "</th>"
        )
    })
    $.each(listParts, function (index, value) {
        $('#' + bodyTable + '-body').append(
            "<tr>" +
            "<th scope='row'>" +
            "<input class='form-check-input' type='radio' name='partRadio' id='partRadio' value=" + index + ">" +
            "</th>" +
            "<td>" + value.identNumber + "</td>" +
            "<td>" + value.name + "</td>" +
            "</tr>"
        )
    })

}

function clearPartsTable(bodyTable){
    $('#' + bodyTable + '-thad').empty()
    $('#' + bodyTable + '-body').empty()
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