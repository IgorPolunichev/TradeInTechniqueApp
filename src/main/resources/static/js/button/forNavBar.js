
const uriListMachines = '/users/listMachines';
const uriListUsers = '/users';
const uriListCompanies = '/users/companies';
$(document).ready(function (){
    $('#getListMachines').attr('href', uriListMachines)
    $('#getListUsers').attr('href', uriListUsers)
    $('#getListCompanies').attr('href', uriListCompanies)
})