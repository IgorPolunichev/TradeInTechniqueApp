$(document).ready(async function () {
    createActsTable(await getActList())
});

async function deleteAct(actId) {
    await fetch("/api/v5/acts/delete/" + actId, {
            method: 'DELETE'
            , headers: {
                'Content-type': 'application/json; charset=utf-8'
            }
        }
    )
    $('#body-allActs').empty()
    createActsTable(await getActList())
}

function createActsTable(listAct) {
    $.each(listAct, function (i, v) {
        console.log(v)
        $('#body-allActs').append(
            "<tr>" +
            "<td>" + v.date + "</td>" +
            "<td>" + v.number + "</td>" +
            "<td>" + v.machineSerialNumber + "</td>" +
            "<td>" + v.machineType + "</td>" +
            "<td>" + v.actDescription + "</td>" +
            "<td>" +
            "<button onclick=deleteAct(" + v.id + ") type='button' class='btn btn-danger btn-sm' style='margin-right:3px'>Удалить</button>" +
            "<button type='button' class='btn btn-success btn-sm' style='margin-right:3px'>Редактировать</button>" +
            "<button type='button' class='btn btn-secondary btn-sm'>Закрыть</button>" +
            "</td>" +
            "</tr>"
        )
    })
}

async function getActList(){
    let listAct = (await getListEntities('/api/v5/acts'))
    return listAct;
}