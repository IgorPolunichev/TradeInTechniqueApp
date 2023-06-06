let defaultSizePage = 5;
let defaultPageNumber = 0;
let totalPages = 0;
let companies = 0
const countPages = $('#count-pages-forAddMachineForm')
$(document).ready(async function () {

// Получение компаний с фильтром
    $('#search-button-companiesAddMachine').click(async function () {

        const nameCompany = $('#search-button-val-companiesAddMachine').val()
        const url = getURLForAddMachine(nameCompany, defaultPageNumber, defaultSizePage)
        await setCompanies(url)
        createCompaniesTable(companies);

        let i = 1
        countPages.empty()
        while (i <= totalPages) {
            countPages.append("<li class='page-item'><a id='" + i + "'class='page-link' href='#'>" +
                i +
                "</a></li>")
            i++;
        }

        $('#count-pages-forAddMachineForm a').click(async function () {
            await setCompanies(getURLForAddMachine(nameCompany, this.id - 1, defaultSizePage))
            createCompaniesTable(companies)
        })


    });


// Кнопка сохранения машины
    $('#buttonSaveMachine').click(async function () {
        const idCompany = $('#companyRadio:checked').val()
        const type = $('#type').val()
        const serialNumber = $('#serialNumber').val()
        const operatingTime = $('#operatingTime').val()
        const yearOfRelease = $('#yearOfRelease').val()
        const createMachineURL = 'http://localhost:8080/api/v2/machines'

        const data = JSON.stringify({
            type: type
            , serialNumber: serialNumber
            , operatingTime: operatingTime
            , yearOfRelease: yearOfRelease
            , companyId: idCompany
        });
        const createMachine = await fetch(createMachineURL, {
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
        countPages.empty()
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
    totalPages = listCompany.totalPages
    console.log(listCompany)
}

function createCompaniesTable(companies) {
    console.log(companies)
    $('#body-companies-table').empty();
    $.each(companies, function (index, value) {
        $('#body-companies-table').append(
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

