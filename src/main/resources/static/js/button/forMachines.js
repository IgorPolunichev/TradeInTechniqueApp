const formTable = $('#form-tables');
let machinesList = 0;
let defaultSizePageMachineList = 5;
let defaultPageNumberMachineList = 0;
let machineFilter = 0;
let pageNumberNow = 0;
let machineIdForUpdate = 0;
let company = 0;
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
            console.log('tet')
            await getAllMachines(getURLForMachinesList(machineFilter, this.id - 1, defaultSizePageMachineList))
            createMachineTable(machinesList)
        })

    })

    $('#count-pages-forMachinesList a').click(async function () {
        await getAllMachines(getURLForMachinesList(machineFilter, this.id - 1, defaultSizePage))

        createMachineTable(machinesList)
    })

    $('#search-button-companiesEditMachine').click(async function () {
        const nameCompany = $('#search-button-val-companiesEditMachine').val()
        const nameBodyTable = 'body-companiesForEditMachine-table'
        await setCompanies(getURLForAddMachine(nameCompany, defaultPageNumber, defaultSizePage))
        createCompaniesTable(nameBodyTable, companies)

        let countPagesForCompaniesTable = $('#count-pages-for-' + nameBodyTable)
        let i = 1
        countPagesForCompaniesTable.empty()
        while (i <= totalPages) {
            countPagesForCompaniesTable.append("<li class='page-item'><a id='" + i + "'class='page-link' href='#'>" +
                i +
                "</a></li>")
            i++;
        }

        $('#count-pages-for-body-companiesForEditMachine-table a').click(async function () {
            console.log('tet')
            await setCompanies(getURLForAddMachine(nameCompany, this.id - 1, defaultSizePage))
            const nameBodyTable = 'body-companiesForEditMachine-table'
            createCompaniesTable(nameBodyTable, companies)
        })
    })

    $('#editMachine-update').click(async function () {
        let id = machineIdForUpdate
        let type = $('#editMachine-type').val()
        let serialNumber = $('#editMachine-serialNumber').val()
        let subtype = $('#editMachine-subtype').val()
        let operatingTime = $('#editMachine-operatingTime').val()
        let idCompany;
        if ($('input[name="companyRadio"]').is(':checked')) {
            idCompany = $('#companyRadio:checked').val()
            console.log(idCompany)
        } else {
            idCompany = company
        }
        let yearOfRelease = $('#editMachine-yearOfRelease').val()
        const updateMachineURL = 'http://localhost:8080/api/v2/machines'

        const data = JSON.stringify({
            id: id
            , type: type
            , serialNumber: serialNumber
            , subtype: subtype
            , operatingTime: operatingTime
            , yearOfRelease: yearOfRelease
            , companyId: idCompany
        });
        await fetch(updateMachineURL, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json; charset=utf-8'
            },
            body: data
        });

        await getAllMachines(getURLForMachinesList(machineFilter, defaultPageNumberMachineList, defaultSizePageMachineList))
        formTable.empty()
        createMachineTable(machinesList)


    })

});

async function getAllMachines(url) {
    const allMachines = await (await fetch(url)).json();
    machinesList = allMachines.content
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
        "<th scope='col'>Subtype</th>" +
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
            "<td>" + value.subtype + "</td>" +
            "<td>" + value.operatingTime + "</td>" +
            "<td>" + value.yearOfRelease + "</td>" +
            "<td>" + value.companyName + "</td>" +
            "<td>" +
            "<button onclick='editMachine(" + value.id + ")' type='button' class='btn btn-success' data-bs-target='#editMachine'>Edit</button>" +
            "</td>" +
            "<td><button onclick='deleteMachine(" + value.id + ")' type='button' class='btn btn-danger'>Delete</button></td>" +
            "</tr>"
        )
    })
};

function editMachine(id) {
    $('#editMachine').modal('show')
    $('#body-companiesForEditMachine-table').empty()
    $.each(machinesList, function (index, value) {
        if (value.id === id) {
            machineIdForUpdate = id
            company = value.companyId
            console.log(company)

            $('#editMachine-operatingTime').val(value.operatingTime)
            $('#editMachine-serialNumber').val(value.serialNumber)
            $('#editMachine-subtype').val(value.subtype)
            $('#editMachine-type').val(value.type)
            $('#editMachine-yearOfRelease').val(value.yearOfRelease)
            $('#editMachine-machineOwner').val(value.companyName)
        }
    })

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





