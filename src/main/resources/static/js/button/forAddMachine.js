let defaultSizePage = 5;
let defaultPageNumber = 0;
let totalPages = 0;
let companies = 0
$(document).ready(async function () {

// Получение компаний с фильтром
    $('#search-button-companiesAddMachine').click(async function () {

        const nameCompany = $('#search-button-val-companiesAddMachine').val()
        const url = getURLForAddMachine(nameCompany, defaultPageNumber, defaultSizePage)
        await setCompanies(url)
        const nameBodyTable = 'body-companies-table'
        createCompaniesTable(nameBodyTable, companies);
        let countPagesForCompaniesTable = $('#count-pages-for-' + nameBodyTable)
        let i = 1
        countPagesForCompaniesTable.empty()
        while (i <= totalPages) {
            countPagesForCompaniesTable.append("<li class='page-item'><a id='" + i + "'class='page-link' href='#'>" +
                i +
                "</a></li>")
            i++;
        }

        $('#count-pages-for-body-companies-table a').click(async function () {
            console.log('tet')
            await setCompanies(getURLForAddMachine(nameCompany, this.id - 1, defaultSizePage))
            const nameBodyTable = 'body-companies-table'
            createCompaniesTable(nameBodyTable, companies)
        })
    })
// Кнопка сохранения машины
    $('#buttonSaveMachine').click(async function () {
        let idCompany = $('#companyRadio:checked').val()
        let type = $('#type').val()
        let serialNumber = $('#serialNumber').val()
        let operatingTime = $('#operatingTime').val()
        let yearOfRelease = $('#yearOfRelease').val()
        const createMachineURL = 'http://localhost:8080/api/v2/machines'

        const data = JSON.stringify({
            type: type
            , serialNumber: serialNumber
            , operatingTime: operatingTime
            , yearOfRelease: yearOfRelease
            , companyId: idCompany
        });
        await fetch(createMachineURL, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=utf-8'
            },
            body: data
        });
        $('#body-companies-table').empty();
        $('#type').val('')
        $('#serialNumber').val('')
        $('#operatingTime').val('')
        $('#yearOfRelease').val('')
        formTable.empty()
        await getAllMachines(getURLForMachinesList(machineFilter, defaultPageNumberMachineList, defaultSizePageMachineList))
        createMachineTable(machinesList)
    });
});

function getURLForAddMachine(nameCompany, defaultPageNumber, defaultSizePage) {
    return 'http://localhost:8080/api/v3/companies/allCompaniesByFilter?nameCompany='
        + nameCompany
        + '&page='
        + defaultPageNumber
        + '&size='
        + defaultSizePage
};

async function setCompanies(url) {
    const listCompany = await (await fetch(url)).json()
    companies = listCompany.content
    console.log(companies)
    totalPages = listCompany.totalPages
    console.log(listCompany)
}

function createCompaniesTable(nameBodyTable, companies) {
    $('#' + nameBodyTable).empty();
    $.each(companies, function (index, value) {
        $('#' + nameBodyTable).append(
            "<tr>" +
            "<th scope='row'>" +
            "<input class='form-check-input' type='radio' name='companyRadio' id='companyRadio' value='" +
            value.id + "'>" +
            "</th>" +
            "<td>" + value.nameCompany + "</td>" +
            "<td>" + value.inn + "</td>" +
            "<td>" + value.kpp + "</td>" +
            "<td>" + value.locationCompany.city + "</td>" +
            "<td>" + value.locationCompany.street + "</td>" +
            "<td>" + value.locationCompany.house + "</td>" +
            "</tr>"
        );
    });


};

