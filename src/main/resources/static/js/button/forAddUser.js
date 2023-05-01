$(document).ready(async function () {
    // $('#buttonRegister').click(function (event) {
    //     event.preventDefault()
    //
    //     $.ajax({
    //         type: 'POST',
    //         uri: '/api/v1',
    //         data:
    //             JSON.stringify(
    //                 {username: $('#username').val()}
    //             ),
    //         traditional: true,
    //         headers: {'Content-type': 'application/json; charset=utf-8'}
    //     })
    // });
    // Получение CSRF токена
    var token = $('input[name="_csrf"]').attr('value');

    // Отправка данных на создание юзера
    $('#buttonRegister').click(async function (event) {
        event.preventDefault()
        var data = JSON.stringify({username:$('#username').val()
            , firstname: $('#firstname').val()
            , lastname: $('#lastname').val()
            , surname: $('#surname').val()
            , birthDate: $('#birthDate').val()
            , rowPassword: $('#rowPassword').val()
            , role: $('#roles').val()
            , position: $('#position').val()});
        console.log(data)
        var dataUrl = 'http://localhost:8080/api/v1';

        // $.post('api/v1',
        //     JSON.stringify(data)).contentType('application/json; charset=utf-8');
        var tet = await fetch(dataUrl, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=utf-8'
            },
            body: data
        })
        console.log(tet)
        console.log(token)

        // const res = await fetch('http://localhost:8080/login', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     body: {username:"test"}
        // })
        // console.log(res)

        // $.ajax({
        //     type: 'POST',
        //     uri: 'http://localhost:8080/api/v1',
        //     data:
        //         JSON.stringify({username: "test"}),
        //     traditional: true,
        //     contentType: 'application/json; charset=utf-8',
        //     dataType: 'json',
        //     async: true
        // headers: {
        //     'Content-type': 'application/json; charset=utf-8',
        //     'username':'polusha',
        //     'X-CSRF-Token': token
        // },
        // success: function (result) {
        //     $('#surname').html(result)
        // }
        // })
    });


    // Добавление выпадающего списка ролей
    const roles = await (await fetch('http://localhost:8080/api/v1/roles')).json();
    $.each(roles, function (index, value) {
        $('#roles').append(
            $(document.createElement('option')).prop({
                    value: value,
                    text: value
                }
            ))
    })

    // Добавление выпадающего списка должностей
    const position = await (await fetch('http://localhost:8080/api/v1/positions')).json();
    $.each(position, function (index, value) {
        $('#position').append(
            $(document.createElement('option')).prop({
                    value: value,
                    text: value
                }
            ))
    })


    console.log(roles[0]);

});