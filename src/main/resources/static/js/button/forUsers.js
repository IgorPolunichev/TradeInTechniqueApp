let usersList = 0;
const editUserRole = $('#editUser-role');
const formTable = $('#form-tables');

$(document).ready(async function () {

    await getAllUsers()
    createUsersTable(usersList)
});



//Обновление юзера
async function editUser(userId) {
    let userData = 0;
    $.each(usersList, function (index, val) {
        if (val.id === userId) {
            userData = val
            return false;
        }
    })
    $('#editUser-username').val(userData.userName);
    $('#editUser-firstname').val(userData.firstname);
    $('#editUser-lastname').val(userData.lastname);
    $('#editUser-surname').val(userData.surname);
    $('#editUser-birthDate').val(userData.birthDate);

    if (roles === 0) {
        await getRoles()
    }
    editUserRole.empty()

    $.each(roles, function (index, value) {
        editUserRole.append(
            $(document.createElement('option')).prop({
                    value: value,
                    text: value,
                }
            ))
    })

    editUserRole.val(userData.role);

    if (positions === 0) {
        await getPositions()
    }
    $('#editUser-position').empty()
    $.each(positions, function (index, value) {
        $('#editUser-position').append(
            $(document.createElement('option')).prop({
                    value: value,
                    text: value
                }
            ))
    })
    $('#editUser-position').val(userData.position);
    $('#editUser').modal('show')


    $('#editUser-update').click(async function () {
            console.log($('#editUser-username').val())
            userData = JSON.stringify({
                username: $('#editUser-username').val()
                , firstname: $('#editUser-firstname').val()
                , lastname: $('#editUser-lastname').val()
                , surname: $('#editUser-surname').val()
                , birthDate: $('#editUser-birthDate').val()
                , role: $('#editUser-role').val()
                , position: $('#editUser-position').val()
            })
            const dataUrl = "http://localhost:8080/api/v1/" + userId;
            const tet = await fetch(dataUrl, {
                method: 'PUT',
                headers: {
                    'Content-type': 'application/json; charset=utf-8'
                },
                body: userData
            });
            await getAllUsers()
            createUsersTable(usersList)
        }
    )


}

//Удаление юзера
async function deleteUser(userId) {
    await fetch("http://localhost:8080/api/v1/" + userId, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json; charset=utf-8'
        }
    })
    await getAllUsers()
    createUsersTable(usersList)
}

//Получение всех юзеров
async function getAllUsers() {
    const allUsers = await (await fetch('http://localhost:8080/api/v1/users')).json();
    usersList = allUsers.content
}

//Таблица юзеров
function createUsersTable(allUsers) {
    formTable.empty();
    formTable.append(
        "<table class='table table-striped'>" +
        "<thead>" +
        "<tr>" +
        "<th scope='col'>id</th>" +
        "<th scope='col'>Username</th>" +
        "<th scope='col'>Firstname</th>" +
        "<th scope='col'>Lastname</th>" +
        "<th scope='col'>Surname</th>" +
        "<th scope='col'>Birth date</th>" +
        "<th scope='col'>Role</th>" +
        "<th scope='col'>Position</th>" +
        "<th scope='col'>Created at</th>" +
        "<th scope='col'>Modified at</th>" +
        "<th scope='col'>Created by</th>" +
        "<th scope='col'>Last modified by</th>" +
        "</tr>" +
        "</thead>" +
        "<tbody id='users-table'>" +
        "</tbody>" +
        "</table>"
    )
    $.each(allUsers, function (index, value) {
        $('#users-table').append(
            "<tr>" +
            "<th scope='row'>" + value.id + "</th>" +
            "<td>" + value.userName + "</td>" +
            "<td>" + value.firstname + "</td>" +
            "<td>" + value.lastname + "</td>" +
            "<td>" + value.surname + "</td>" +
            "<td>" + value.birthDate + "</td>" +
            "<td>" + value.role + "</td>" +
            "<td>" + value.position + "</td>" +
            "<td>" + value.createdBy + "</td>" +
            "<td>" + value.modifiedAt + "</td>" +
            "<td>" + value.createdBy + "</td>" +
            "<td>" + value.lastModifiedBy + "</td>" +
            "<td>" +
            "<button onclick='editUser(" + value.id + ")' type='button' class='btn btn-success'>Edit</button>" +
            "</td>" +
            "<td><button onclick='deleteUser(" + value.id + ")' type='button' class='btn btn-danger'>Delete</button></td>" +
            "</tr>"
        )
    })
};



