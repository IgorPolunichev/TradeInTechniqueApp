const dataUrl = 'http://localhost:8080/api/v3/companies';
$(document).ready(async function () {
    $('#buttonSaveCompany').click(async function (event) {
        event.preventDefault()
        let nameCompany = $('#nameCompany')
        let inn = $('#inn')
        let kpp = $('#kpp')
        let city = $('#city')
        let street = $('#street')
        let house = $('#house')
        let zipCode = $('#zipCode')

        let data = JSON.stringify({
            nameCompany: nameCompany.val()
            , inn: inn.val()
            , kpp: kpp.val()
            , locationCompany: {
                city: city.val()
                , street: street.val()
                , house: house.val()
                , zipCode: zipCode.val()
            }
        });
        await fetch(dataUrl, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=utf-8'
            },
            body: data
        });
        nameCompany.val('')
        inn.val('')
        kpp.val('')
        city.val('')
        street.val('')
        house.val('')
        zipCode.val('')
    })

});