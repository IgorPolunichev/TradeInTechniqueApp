let roles = 0;
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
        const tet = await fetch(dataUrl, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=utf-8'
            },
            body: data
        });

        $('#username').val('')
        $('#firstname').val('')
        $('#lastname').val('')
        $('#surname').val('')
        $('#birthDate').val('')
        $('#rowPassword').val('')
        $('#roles').val('')
        $('#position').val('')

        await getAllUsers()
        createUsersTable(usersList)
    });


    // Добавление выпадающего списка ролей
    if (roles === 0) {
        await getRoles()
    }
    $.each(roles, function (index, value) {
        $('#roles').append(
            $(document.createElement('option')).prop({
                    value: value,
                    text: value
                }
            ))
    })

    // Добавление выпадающего списка должностей
    if (positions === 0) {
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

async function getRoles() {
    roles = await (await fetch('http://localhost:8080/api/v1/roles')).json();
}

async function getPositions() {
    positions = await (await fetch('http://localhost:8080/api/v1/positions')).json();
}