$(document).ready(async function () {
    $('#buttonSaveCompany').click(async function (event) {
        event.preventDefault()
        var dataUrl = 'http://localhost:8080/api/v3/companies';
        var data = JSON.stringify({
            nameCompany: $('#nameCompany').val()
            , inn: $('#inn').val()
            , kpp: $('#kpp').val()
            , locationCompany: {
                city: $('#city').val()
                , street: $('#street').val()
                , house: $('#house').val()
                , zipCode: $('#zipCode').val()
            }
        });
        var tet = await fetch(dataUrl, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=utf-8'
            },
            body: data
        })
        console.log(tet)
    })
});