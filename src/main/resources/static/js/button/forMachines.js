const formTable = $('#form-tables');
let machinesList = 0;
let defaultSizePageMachineList = 5;
let defaultPageNumberMachineList = 0;
let machineFilter = 0;
let pageNumberNow = 0;
$(document).ready(async function () {
    $('#search-button-machinesList').click(async function () {

        machineFilter = $('#search-button-val-machinesList').val()
        await getAllMachines(getURLForMachinesList(machineFilter, defaultPageNumberMachineList, defaultSizePageMachineList))

        createMachineTable(machinesList)
        let i = 1
        const countPagesForMachinesList = $('#count-pages-forMachinesList')
        countPagesForMachinesList.empty()
        while (i <= totalPages) {
            countPagesForMachinesList.append("<li class='page-item'><a id='" + i + "'class='page-link' href='#'>" +
                i +
                "</a></li>")
            pageNumberNow = i
            i++;
        }
        $('#count-pages-forMachinesList a').click(async function () {
            await getAllMachines(getURLForMachinesList(machineFilter, this.id - 1, defaultSizePage))

            createMachineTable(machinesList)
        })
    })

});

async function getAllMachines(url) {
    const allMachines = await (await fetch(url)).json();
    machinesList = allMachines.content
    console.log(allMachines)
    totalPages = allMachines.totalPages
};

function getURLForMachinesList(serialNumber, defaultPageNumber, defaultSizePage) {
    return 'http://localhost:8080/api/v2/machines?serialNumber='
        + serialNumber
        + '&page='
        + defaultPageNumber
        + '&size='
        + defaultSizePage

}

function createMachineTable(allMachines) {
    formTable.empty();
    formTable.append(
        "<table class='table table-striped'>" +
        "<thead>" +
        "<tr>" +
        "<th scope='col'>id</th>" +
        "<th scope='col'>Type</th>" +
        "<th scope='col'>Serial number</th>" +
        "<th scope='col'>Operating time</th>" +
        "<th scope='col'>Year of release</th>" +
        "<th scope='col'>Company</th>" +
        "<th scope='col'>Edit</th>" +
        "<th scope='col'>Delete</th>" +
        "</tr>" +
        "</thead>" +
        "<tbody id='machines-table'>" +
        "</tbody>" +
        "</table>"
    )

    $.each(allMachines, function (index, value) {

        $('#machines-table').append(
            "<tr>" +
            "<th scope='row'>" + value.id + "</th>" +
            "<td>" + value.type + "</td>" +
            "<td>" + value.serialNumber + "</td>" +
            "<td>" + value.operatingTime + "</td>" +
            "<td>" + value.yearOfRelease + "</td>" +
            "<td>" + value.companyName + "</td>" +
            "<td>" +
            "<button onclick='editMachine(" + value.id + ",pageNumberNow)' type='button' class='btn btn-success'>Edit</button>" +
            "</td>" +
            "<td><button onclick='deleteMachine(" + value.id + ")' type='button' class='btn btn-danger' id='delete-user-" + value.id + "'>Delete</button></td>" +
            "</tr>"
        )
    })
};

function editMachine(id) {

}

async function deleteMachine(id, pageNumber) {
    await fetch("http://localhost:8080/api/v2/machines/" + id, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json; charset=utf-8'
        }
    })


    await getAllMachines(getURLForMachinesList(machineFilter, pageNumber, defaultSizePage))
    formTable.empty()
    createMachineTable(machinesList)
}





