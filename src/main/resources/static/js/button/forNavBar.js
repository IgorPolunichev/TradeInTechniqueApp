
const uriListMachines = '/users/listMachines';
const uriListUsers = '/users';
const uriListCompanies = '/users/listCompanies';
$(document).ready(function (){
    $('#getListMachines').attr('href', uriListMachines)
    $('#getListUsers').attr('href', uriListUsers)
    $('#getListCompanies').attr('href', uriListCompanies)
})