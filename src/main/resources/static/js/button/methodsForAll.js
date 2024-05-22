function getUriForListEntities(uri, filterBy, filter, pageNumber, pageSize) {
    return uri
        + filterBy
        + '='
        + filter
        + '&page='
        + pageNumber
        + '&size='
        + pageSize
};


async function getListEntities(uri) {
    let t = await (await fetch(uri)).json()
    return t
}
async function getAuthUser() {
    return (await getListEntities('/api/v1/authUser/'))

}
async function getPayAct() {
    let atr = $('#actPay-newAct input[name = "options-outlined"]');
    let res = undefined;
    $.each(atr, function (k, v) {
        if (v.checked) {
            res = v.value
        }
    })
    return res;
}
async function checkedEngineers(checkedEngineer) {
    $('#addServiceEngineer-newAct').empty()
    $.each(checkedEngineer, function (i, v) {
        if (v !== undefined) {
            $('#addServiceEngineer-newAct').append(
                "<li>" + v.lastname + " " + v.firstname + " " + v.surname + "</li>"
            )
        }
    })
}

async function createEngineersTable(dataEngineers, authUser, spaceTable, checkedEngineers) {
    $.each(dataEngineers, function (i, v) {
        if (JSON.stringify(authUser) === JSON.stringify(v)) {
            dataEngineers.splice(i, 1)

        }
    })
    $.each(dataEngineers, function (index, value) {
        if (JSON.stringify(checkedEngineers.at(value.id)) === JSON.stringify(value)) {
            $(spaceTable).append(
                "<div class='row'> " +
                "<div class='form-check'>" +
                "  <input class='form-check-input' type='checkbox' name='checkBox-engineer-newAct' value=" + value.id + " checked>" +
                "  <label class='form-check-label' for='checkBox-engineer-newAct'>" +
                value.lastname + " " + value.firstname + " " + value.surname +
                "  </label>" +
                "</div>" +
                "</div>"
            )
        } else {
            $(spaceTable).append(
                "<div class='row'> " +
                "<div class='form-check'>" +
                "  <input class='form-check-input' type='checkbox' name='checkBox-engineer-newAct' value=" + value.id + " >" +
                "  <label class='form-check-label' for='checkBox-engineer-newAct'>" +
                value.lastname + " " + value.firstname + " " + value.surname +
                "  </label>" +
                "</div>" +
                "</div>"
            )
        }
    })
}

function createCountPage(totalPages, bodyCountPages) {
    let i = 1
    const countPagesForPartList = $('#' + bodyCountPages)
    countPagesForPartList.empty()
    while (i <= totalPages) {
        countPagesForPartList.append("<li class='page-item'><a id='" + i + "'class='page-link' href='#'>" +
            i +
            "</a></li>")
        pageNumberNow = i
        i++;
    }
}

function createTableParts(listParts, nameColumns, bodyTable) {
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

function clearPartsTable(bodyTable) {
    $('#' + bodyTable + '-thad').empty()
    $('#' + bodyTable + '-body').empty()
}

async function createMachineTableForUser(bodyTable, machineList) {
    $('#' + bodyTable).empty()
    $.each(machineList, function (index, value) {
        console.log(value)
        $('#' + bodyTable).append(
            "<tr>" +
            "<th scope='row'>" +
            "<input class='form-check-input' type='radio' name='machineRadioNewAct' id='machineRadioNewAct' value='" + value.id + "'>" +
            " </th>" +
            "<td>" + value.type + "</td>" +
            "<td>" + value.serialNumber + "</td>" +
            "<td>" + value.operatingTime + "</td>" +
            "</tr>"
        )
    })
}

function getWeekDay(day) {
    const dayOfWeek = ["Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс",]
    return dayOfWeek[day - 1];
}