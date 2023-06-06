let roles=0;
let positions = 0;
$(document).ready(async function () {

    // Получение CSRF токена
    const token = $('input[name="_csrf"]').attr('value');

    // Отправка данных на создание юзера
    $('#buttonRegister').click(async function (event) {
        event.preventDefault()
        let data = JSON.stringify({
            username: $('#username').val()
            , firstname: $('#firstname').val()
            , lastname: $('#lastname').val()
            , surname: $('#surname').val()
            , birthDate: $('#birthDate').val()
            , rowPassword: $('#rowPassword').val()
            , role: $('#roles').val()
            , position: $('#position').val()
        });
        const dataUrl = 'http://localhost:8080/api/v1';

        // $.post('api/v1',
        //     JSON.stringify(data)).contentType('application/json; charset=utf-8');
        const tet = await fetch(dataUrl, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=utf-8'
            },
            body: data
        });
        console.log(tet)
        console.log(token)
        await getAllUsers()
        createUsersTable(usersList)

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
    if (roles===0){
        await getRoles()
    }
    // const roles = await (await fetch('http://localhost:8080/api/v1/roles')).json();
    $.each(roles, function (index, value) {
        $('#roles').append(
            $(document.createElement('option')).prop({
                    value: value,
                    text: value
                }
            ))
    })

    // Добавление выпадающего списка должностей
    if(positions===0){
        await getPositions()
    }
    // const position = await (await fetch('http://localhost:8080/api/v1/positions')).json();
    $.each(positions, function (index, value) {
        $('#position').append(
            $(document.createElement('option')).prop({
                    value: value,
                    text: value
                }
            ))
    })


    console.log(roles[0]);

});

async function getRoles(){
    roles = await (await fetch('http://localhost:8080/api/v1/roles')).json();
}

async function getPositions(){
    positions = await (await fetch('http://localhost:8080/api/v1/positions')).json();
}