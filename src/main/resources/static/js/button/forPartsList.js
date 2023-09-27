let pageNumber;
let listEntities;
let pageSize = 15;
let totalPages;
let pageNumberNow;
let filterBy;
let filter;
const idBodyTable = "body-partList-table";
const nameColumns = ["#", "Ident number", "Part name"];



$(document).ready(function () {
    $('#uploadPartsList').click(async function () {
        let input = document.getElementById('inputGroupFile04')
        let tet = new FormData
        tet.append('file', input.files[0])
        await fetch('http://localhost:8080/api/v4/parts/uploadPartsList', {
            method: 'POST'
            , body: tet
        })
    })

    $('#search-button-partList').click(async function () {
        clearTable(idBodyTable)
        filter = $('#search-button-val-partList').val();
        filterBy = $('input[name="flexRadioSearchBy"]:checked').val()
        await getListEntities(getUriForListEntities('/api/v4/parts/allPartsByFilter?'
            ,filterBy
            , filter
            , pageNumber
            , pageSize))
        createAllTable(idBodyTable, nameColumns, listEntities)
        let i = 1
        const countPagesForPartList = $('#count-pages-for-partList-table')
        countPagesForPartList.empty()
        while (i <= totalPages) {
            countPagesForPartList.append("<li class='page-item'><a id='" + i + "'class='page-link' href='#'>" +
                i +
                "</a></li>")
            pageNumberNow = i
            i++;
        }

        $('#count-pages-for-partList-table a').click(async function () {
            console.log('test')
            await getListEntities(getUriForListEntities('/api/v4/parts/allPartsByFilter?'
                ,filterBy
                , filter
                , this.id - 1
                , pageSize))
            clearTable(idBodyTable)
            createAllTable(idBodyTable, nameColumns, listEntities)
        })

        $('#addPart').click(function (){
            let name = $('#partName')
            let identNumber =$('#identNumber')
            const data = JSON.stringify({
                name: name.val()
                ,identNumber: identNumber.val()
            })
            savePart(data)
            name.val('')
            identNumber.val('')
        })
    })

    $('#deletePart').click(function (){
        if($('input[name="partRadio"]').is(':checked')){
            let idForPartDelete = $('#partRadio:checked').val()
            deletePart(idForPartDelete)
        }

    })



})

function createAllTable(nameBodyTable, nameColumns, listEntities) {
    $.each(nameColumns, function (i, v) {
        $('#' + nameBodyTable + '-thad').append(
            "<th scope='col'>" + v + "</th>"
        )
    })
    console.log(listEntities)

    $.each(listEntities, function (index, value) {
        $('#' + nameBodyTable + '-body').append(
            "<tr>" +
            "<th scope='row'>" +
            "<input class='form-check-input' type='radio' name='partRadio' id='partRadio' value=" + value.id + ">" +
            "</th>" +
            "<td>" + value.identNumber + "</td>" +
            "<td>" + value.name + "</td>" +
            "</tr>"
        )
    })

}

async function getListEntities(uri) {
    let entities = await (await fetch(uri)).json()
    listEntities = entities.content
    totalPages = entities.totalPages;
}

function getUriForListEntities(uri, filterBy, filter, pageNumber, pageSize) {
    return uri
        + filterBy
        +'='
        +filter
        + '&page='
        + pageNumber
        + '&size='
        + pageSize
}

function clearTable(nameBodyTable) {
    $('#' + nameBodyTable + '-thad').empty()
    $('#' + nameBodyTable + '-body').empty()
}

async function deletePart(id) {
    await fetch('/api/v4/parts/' + id, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json; charset=utf-8'
        }
    })
    await getListEntities(getUriForListEntities('/api/v4/parts/allPartsByFilter?'
        ,filterBy
        , filter
        , pageNumber
        , pageSize))
    clearTable(idBodyTable)
    createAllTable(idBodyTable,nameColumns,listEntities)
}
async function savePart(data){
    await fetch('/api/v4/parts', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json; charset=utf-8'
        },
        body: data
    });
};