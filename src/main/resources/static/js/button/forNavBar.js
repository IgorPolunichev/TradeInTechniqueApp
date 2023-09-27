
const uriListMachines = '/users/listMachines';
const uriListUsers = '/users';
const uriListCompanies = '/users/listCompanies';
const uriListParts = '/users/listParts' ;
$(document).ready(function (){
    $('#getListMachines').attr('href', uriListMachines)
    $('#getListUsers').attr('href', uriListUsers)
    $('#getListCompanies').attr('href', uriListCompanies)
    $('#getListParts').attr('href',uriListParts)
})