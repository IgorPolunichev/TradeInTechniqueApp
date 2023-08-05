let nameCompany = $('#nameCompany-editCompanyModal')
let inn = $('#inn-editCompanyModal')
let kpp = $('#kpp-editCompanyModal')
let city = $('#city-editCompanyModal')
let street = $('#street-editCompanyModal')
let house = $('#house-editCompanyModal')
let zipCode = $('#zipCode-editCompanyModal')
let idCompany ;
let idLocation;
$(document).ready(async function(){

    $('#search-button-companiesList').click(async function () {
        console.log('tet')
        const nameCompany = $('#search-button-val-companiesList').val()
        const url = getURLForAddMachine(nameCompany, defaultPageNumber, defaultSizePage)
        await setCompanies(url)
        const nameBodyTable = 'body-companiesList-table'
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

        $('#count-pages-for-body-companiesList-table a').click(async function () {

            await setCompanies(getURLForAddMachine(nameCompany, this.id - 1, defaultSizePage))
            const nameBodyTable = 'body-companiesList-table'
            createCompaniesTable(nameBodyTable, companies)
        })
    })

    $('#delete-company-companiesList').click(async function () {
        let idCompany;
        if ($('input[name="companyRadio"]').is(':checked')) {
            idCompany = $('#companyRadio:checked').val()
            await fetch("http://localhost:8080/api/v3/companies/" + idCompany, {
                method: 'DELETE',
                headers: {
                    'Content-type': 'application/json; charset=utf-8'
                }
            })
           await updateTable()
        }
    })

    $('#edit-company-companiesList').click(async function(){
        if($('input[name="companyRadio"]').is(':checked')){
            $.each(companies, function (index, value){
                idCompany = $('#companyRadio:checked').val()
                if(value.id === +idCompany){
                    nameCompany.val(value.nameCompany)
                    inn.val(value.inn)
                    kpp.val(value.kpp)
                    idLocation = value.locationCompany.id
                    city.val(value.locationCompany.city)
                    street.val(value.locationCompany.street)
                    house.val(value.locationCompany.house)
                    zipCode.val(value.locationCompany.zipCode)
                }
            })
        }
    })

    $('#buttonUpdateCompany').click(async function () {
        if ($('input[name="companyRadio"]').is(':checked')) {

            let data = JSON.stringify({
                id: idCompany
                , nameCompany: nameCompany.val()
                , inn: inn.val()
                , kpp: kpp.val()
                , locationCompany: {
                    id:idLocation
                    ,city: city.val()
                    , street: street.val()
                    , house: house.val()
                    , zipCode: zipCode.val()
                }
            })
            await fetch(dataUrl, {
                method: 'PUT'
                , headers: {
                    'Content-type': 'application/json; charset=utf-8'
                }
                , body: data
            })
            await updateTable()
            idCompany=null;
            nameCompany.val('')
            inn.val('')
            kpp.val('')
            idLocation = null
            city.val('')
            street.val('')
            house.val('')
            zipCode.val('')
        }
    })
})

async function updateTable(){
    const nameBodyTable = 'body-companiesList-table'
    const nameCompany = $('#search-button-val-companiesList').val()
    const url = getURLForAddMachine(nameCompany, defaultPageNumber, defaultSizePage)
    await setCompanies(url)
    createCompaniesTable(nameBodyTable, companies);
}