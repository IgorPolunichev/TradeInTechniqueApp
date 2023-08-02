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
})