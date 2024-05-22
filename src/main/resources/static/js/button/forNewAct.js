let arr = [];
let dataEngineers;
let machines;
let checkedMachine;
let checkedCompany;
let checkedEngineersAdd = [];
let timeOfWork = 0;
let listWorks = [];
let regTime = /^\d{2}:\d{2}\b/;
const regNumber = /\d/;
const regText = /[а-яА-ЯA-Za-z]/;
let editWorkIndex;
let descriptionOfAct = $('#descriptionOfAct')
let authUserId;
let listPartForAct = new Map;
let filesForAct = new FormData;

const exceptionValidateActModalBody = $('#exceptionValidate-NewAct div[name = "exceptionValidate-bodyModal-NewAct"]')
const exceptionValidateActModal = $('#exceptionValidate-NewAct')

const dateAct = $('#dateAct-newAct input[name="date"]')
const dateWork = $('#addWorksModal input[name="date"]')
const timeFrom = $('#addWorksModal input[name="timeWorkFrom"]')
const timeTo = $('#addWorksModal input[name="timeWorkTo"]')
const timeLunchFrom = $('#addWorksModal input[name="timeLunchFrom"]')
const timeLunchTo = $('#addWorksModal input[name="timeLunchTo"]')
const descriptionOfWorks = $('#addWorksModal textarea[id="descriptionOfWorks"]')
const placeOfDeparture = $('#addWorksModal input[name="placeOfDeparture"]')
const placeOfArrival = $('#addWorksModal input[name="placeOfArrival"]')
const mileage = $('#addWorksModal input[name="mileage"]')
const numberAct = $('#input-numberAct-newAct')
const numberAndDateApplication = $('#numberAndDAteApplication')
const exceptionAddWorkModal = $('#addWorksModal div[name = "exception-addWorkModal"]')

const exceptionEditWorkModal = $('#editWorksModal div[name = "exception-editWorkModal"]')
const editWorkDate = $('#editWorksModal input[name="date-editWork"]')
const editWorkTimeFrom = $('#editWorksModal input[name="timeWorkFrom-editWork"]')
const editWorkTimeTo = $('#editWorksModal input[name="timeWorkTo-editWork"]')
const editWorkTimeLunchFrom = $('#editWorksModal input[name="timeLunchFrom-editWork"]')
const editWorkTimeLunchTo = $('#editWorksModal input[name="timeLunchTo-editWork"]')
const editWorksDescription = $('#editWorksModal textarea[name="descriptionOfWorks-editWork"]')
const editPlaceOfDeparture = $('#editWorksModal input[name="placeOfDeparture-editWork"]')
const editPlaceOfArrival = $('#editWorksModal input[name="placeOfArrival-editWork"]')
const editMileage = $('#editWorksModal input[name="mileage-editWork"]')
$(document).ready(async function () {
    $('li[name = "companyData"]').css({"font-weight": "700"})
    // const authUser = (await getListEntities('/api/v1/authUser/'))
    const authUser = await getAuthUser()
    authUserId = authUser.id
    const username = authUser.userName
    const counterAct = authUser.counterActs

    $('#input-numberAct-newAct').val(username + '-' + counterAct)

    $('#search-button-newAct-AddEngineer').click(async function () {
        let input = $('#search-button-val-engineers-newAct').val()
        dataEngineers = (await getListEntities(getUriForListEntities('/api/v1/usersByFilter?', 'lastname', input))).content
        $('#engineers-newAct-List').empty()
        await createEngineersTable(dataEngineers, authUser,'#engineers-newAct-List', arr)

        // $.each(dataEngineers, function (i, v) {
        //     if (JSON.stringify(authUser) === JSON.stringify(v)) {
        //         dataEngineers.splice(i, 1)
        //
        //     }
        // })
        // $.each(dataEngineers, function (index, value) {
        //     if (JSON.stringify(arr.at(value.id)) === JSON.stringify(value)) {
        //         $('#engineers-newAct-List').append(
        //             "<div class='row'> " +
        //             "<div class='form-check'>" +
        //             "  <input class='form-check-input' type='checkbox' name='checkBox-engineer-newAct' value=" + value.id + " checked>" +
        //             "  <label class='form-check-label' for='checkBox-engineer-newAct'>" +
        //             value.lastname + " " + value.firstname + " " + value.surname +
        //             "  </label>" +
        //             "</div>" +
        //             "</div>"
        //         )
        //     } else {
        //         $('#engineers-newAct-List').append(
        //             "<div class='row'> " +
        //             "<div class='form-check'>" +
        //             "  <input class='form-check-input' type='checkbox' name='checkBox-engineer-newAct' value=" + value.id + " >" +
        //             "  <label class='form-check-label' for='checkBox-engineer-newAct'>" +
        //             value.lastname + " " + value.firstname + " " + value.surname +
        //             "  </label>" +
        //             "</div>" +
        //             "</div>"
        //         )
        //     }
        // })
        //
        $('#engineers-newAct-List input[name ="checkBox-engineer-newAct"]').click(function () {
            let val = $(this).val()
            if ($(this).is(':checked')) {
                $.each(dataEngineers, function (i, v) {
                    if (v.id === +val) {
                        arr[val] = v
                    }
                })
            } else {
                arr[val] = undefined
            }
        })
    })

    // $('#close-newAct-addEngineer').click(function () {
    //     clearArr()
    //
    // })

    $('#button-addEngineer-newAct').click(function () {
        $('#engineers-newAct-List').empty()
    })


    // $('#reset-button-addEngineer-newAct').click(function () {
    //     clearArr()
    // })

    $('#button-addEngineer-newAct-modal').click(function () {
        $('#addServiceEngineer-newAct').empty()
        $.each(arr, function (i, v) {
            if (v !== undefined) {
                $('#addServiceEngineer-newAct').append(
                    "<li>" + v.lastname + " " + v.firstname + " " + v.surname + "</li>"
                )
            }
        })
    })

    $('#search-button-newAct-AddMachine').click(async function () {
        let filter = $('#search-button-val-machines-newAct').val()
        machines = await (await getListEntities(getUriForListEntities("http://localhost:8080/api/v2/machines?"
            , "serialNumber"
            , filter
            , 0
            , 10))).content
        console.log(machines)
        await createMachineTableForUser('body-machineTable-newAct', machines)

        $('#body-machineTable-newAct input[name = "machineRadioNewAct"]').click(function () {
            let id = $(this).val()
            $.each(machines, function (ind, val) {
                if (val.id === +id) {
                    checkedMachine = val
                }
            })
        })
    })


    $('#button-addMachine-newAct-modal').click(async function () {
        if (checkedMachine !== undefined) {
            checkedCompany = await (getListEntities("http://localhost:8080/api/v3/companies/" + checkedMachine.companyId))
            $('#modelMachine-newAct').text(checkedMachine.type)
            $('#serialNumber-newAct').text(checkedMachine.serialNumber)
            $('#type-newAct').text(checkedMachine.subtype)
            $('#yearOfRelease-newAct').text(checkedMachine.yearOfRelease)
            $('#operatingTime-newAct').attr('placeholder', checkedMachine.operatingTime)
            $('#nameCompany-newAct').text(checkedCompany.nameCompany)
            $('#inn-newAct').text(checkedCompany.inn)
            $('#kpp-newAct').text(checkedCompany.kpp)
            $('#streetAndHouse-newAct').text(checkedCompany.locationCompany.street + ' ' + checkedCompany.locationCompany.house)
            $('#city-newAct').text(checkedCompany.locationCompany.city)
            $('#zipCode-newAct').text(checkedCompany.locationCompany.zipCode)
            $('#contact-newAct').text()
        }
    })

    $('#operatingTime-newAct').blur(function () {

        const operatingTime = $('#operatingTime-newAct')
        if (regNumber.test(operatingTime.val())) {
            checkedMachine.operatingTime = operatingTime.val()
        }
    })

    $('#deleteMachine-newAct').click(function () {
        checkedCompany = undefined
        checkedMachine = undefined
        $('#modelMachine-newAct').text('')
        $('#serialNumber-newAct').text('')
        $('#type-newAct').text('')
        $('#yearOfRelease-newAct').text('')
        $('#operatingTime-newAct').attr('placeholder', '')
        $('#nameCompany-newAct').text('')
        $('#inn-newAct').text('')
        $('#kpp-newAct').text('')
        $('#streetAndHouse-newAct').text('')
        $('#city-newAct').text('')
        $('#zipCode-newAct').text('')
        $('#contact-newAct').text('')
    })

// Окно добавления работы
    dateWork.change(function () {
        let checkDate = new Date(dateWork.val()) - new Date(dateAct.val())
        if (checkDate < 0 || isNaN(checkDate)) {
            exceptionAddWorkModal.empty()
            exceptionAddWorkModal.append(
                "<p class='text-danger'>Дата проведения работ не соответствует дате акта!</p>"
            )
            dateWork.val('')
        } else {
            exceptionAddWorkModal.empty()
            validateAddWorkModal("date", "date", "addWorks")
            timeFrom.prop('disabled', false)
        }

    })

    $('#addWorksModal textarea[name="descriptionOfWorks"]').change(function () {
        validateAddWorkModal("descriptionOfWorks"
            , !regText.test($('#addWorksModal textarea[name="descriptionOfWorks"]').val()) ? undefined : "descriptionOfWorks"
            , "addWorks")
    })

    timeFrom.blur(function () {
        checkTimeFrom(timeFrom, timeTo, timeLunchFrom, timeLunchTo, exceptionAddWorkModal, dateWork, "addWorks")
        if (regTime.test(timeLunchFrom.val())) {
            checkTimeLunchFrom(dateWork, timeFrom, timeTo, timeLunchFrom, timeLunchTo, exceptionAddWorkModal)
        }
    })

    timeTo.blur(function () {
        checkTimeTo(timeFrom, timeTo, timeLunchFrom, timeLunchTo, exceptionAddWorkModal, dateWork, "addWorks")
        if (regTime.test(timeLunchFrom.val())) {
            checkTimeLunchFrom(dateWork, timeFrom, timeTo, timeLunchFrom, timeLunchTo, exceptionAddWorkModal)
        }
    })

    timeLunchFrom.blur(function () {
        if (regTime.test(timeLunchFrom.val())) {
            checkTimeLunchFrom(dateWork, timeFrom, timeTo, timeLunchFrom, timeLunchTo, exceptionAddWorkModal)
        } else {
            timeLunchFrom.val(undefined)
            timeLunchTo.val(undefined)
        }
    })

    let listParts;
    $('#search-button-partList-newAct').click(async function () {
        let bodyTable = "partsTableForUsers"
        let filter = $('#search-button-val-partList-newAct').val()
        let filterBy = $('input[name = "flexRadioSearchBy"]:checked').val()
        let pageSize = 10
        listParts = await getListEntities(getUriForListEntities('/api/v4/parts/allPartsByFilter?'
            , filterBy
            , filter
            , undefined
            , pageSize))
        clearPartsTable(bodyTable)
        createTableParts(listParts.content, ['#', "Номер детали", "Название"], bodyTable)
        createCountPage(listParts.totalPages, 'count-pages-for-partList-table-newAct')

        $('#count-pages-for-partList-table-newAct a').click(async function () {
            listParts = await getListEntities(getUriForListEntities('/api/v4/parts/allPartsByFilter?'
                , filterBy
                , filter
                , this.id - 1
                , pageSize))
            clearPartsTable("partsTableForUsers")
            createTableParts(listParts.content, ['#', "Номер детали", "Название"], "partsTableForUsers")
        })

        $('#save-addPartsModal-newAct').click(function () {
            let t = $('#partsTableForUsers-body input[name = "partRadio"]:checked').val()
            if (t !== undefined) {
                let part = listParts.content.at(t)
                listPartForAct.set(part.id, part)
                $('#partsList-newAct').append(
                    "<tr id=" + part.id + ">" +
                    // "<th scope='row'>" +
                    // "<input class='form-check-input' type='radio' name='partRadio' id='partRadio' value=" + index + ">" +
                    // "</th>" +
                    "<td>" +
                    "<button onclick='deletePart(" + part.id + ")' type='button' class= 'btn btn-primary btn-sm'>" +
                    "<i class='bi bi-x-circle-fill'></i>" +
                    "</button>" +
                    "</td>" +
                    "<td>" + part.identNumber + "</td>" +
                    "<td>" + part.name + "</td>" +
                    "<td><input type='number' class='form-control form-control-sm'></td>" +
                    "<td>" +
                    "<select class='form-select form-select-sm'>" +
                    "  <option selected  value='L'>Либхерр</option>" +
                    "  <option value='K'>Клиент</option>" +
                    "  <option value='T'>TnT</option>" +
                    "</select>" +
                    "</td>" +
                    "</tr>"
                )
                clearPartsTable("partsTableForUsers")
            }
        })
    })

    $('#addParts-newAct').click(function () {
        clearPartsTable("partsTableForUsers")
        $('#search-button-val-partList-newAct').val(undefined)
        $('#count-pages-for-partList-table-newAct').empty()
    })


    $('#save-addWorksModal-newAct').click(async function () {
        exceptionAddWorkModal.empty()
        let workJSON = {
            workDate: dateWork.val()
            , startWork: timeFrom.val()
            , endWork: timeTo.val()
            , startLunch: timeLunchFrom.val()
            , endLunch: timeLunchTo.val()
            , placeOfDeparture: placeOfDeparture.val()
            , placeOfArrival: placeOfArrival.val()
            , mileage: mileage.val()
            , workDescription: descriptionOfWorks.val()
        }

        if (regTime.test(timeLunchFrom.val())) {
            if (await addWork(
                workJSON, exceptionAddWorkModal
            )) {
                createWorkRow(listWorks)
                clearAddWorkModal()
                resetValidateModals(exceptionAddWorkModal, "addWorks")
                $('#close-addWorkModal-top-newAct').click()
            }

        } else {
            if (await addWork(
                // dateWork.val()
                // , timeFrom.val()
                // , timeTo.val()
                // , undefined
                // , undefined
                // , placeOfDeparture.val()
                // , placeOfArrival.val()
                // , mileage.val()
                // , descriptionOfWorks.val()
                workJSON, exceptionAddWorkModal
            )) {
                createWorkRow(listWorks)
                clearAddWorkModal()
                resetValidateModals(exceptionAddWorkModal, "addWorks")
                $('#close-addWorkModal-top-newAct').click()
            }
        }
    })

    $('#close-addWorkModal-top-newAct').click(function () {
        clearAddWorkModal()
        resetValidateModals(exceptionAddWorkModal, "addWorks")
    })

    $('#close-addWorkModal-newAct').click(function () {
        clearAddWorkModal()
        resetValidateModals(exceptionAddWorkModal, "addWorks")
    })

    $('#button-addMachine-newAct').click(function () {
        $('#body-machineTable-newAct').empty()
    })

    $('#reset-button-addMachine-newAct').click(function () {
        $('#body-machineTable-newAct').empty()
    })

    $('#close-newAct-addMachine').click(function () {
        $('#body-machineTable-newAct').empty()
    })

// Окно редактирования работ
    editWorkDate.focus(function () {
        let oldDateWork = editWorkDate.val()
        editWorkDate.blur(function () {
            let checkDate = new Date(editWorkDate.val()) - new Date(dateAct.val())
            if (checkDate < 0 || isNaN(checkDate)) {
                exceptionEditWorkModal.empty()
                exceptionEditWorkModal.append(
                    "<p class='text-danger'>Дата проведения работ не соответствует дате акта!</p>"
                )
                editWorkDate.val(oldDateWork)
            } else {
                exceptionEditWorkModal.empty()
                validateAddWorkModal("date", "date", "editWorks")
            }
        })
    })

    editWorkTimeFrom.blur(function () {
        checkTimeFrom(editWorkTimeFrom, editWorkTimeTo, editWorkTimeLunchFrom, editWorkTimeLunchTo, exceptionEditWorkModal, editWorkDate, "editWorks")
        if (regTime.test(editWorkTimeLunchFrom.val())) {
            checkTimeLunchFrom(editWorkDate, editWorkTimeFrom, editWorkTimeTo, editWorkTimeLunchFrom, editWorkTimeLunchTo, exceptionEditWorkModal)
        }

    })

    editWorkTimeTo.blur(function () {
        checkTimeTo(editWorkTimeFrom, editWorkTimeTo, editWorkTimeLunchFrom, editWorkTimeLunchTo, exceptionEditWorkModal, editWorkDate, "editWorks")
        if (regTime.test(editWorkTimeLunchFrom.val())) {
            checkTimeLunchFrom(editWorkDate, editWorkTimeFrom, editWorkTimeTo, editWorkTimeLunchFrom, editWorkTimeLunchTo, exceptionEditWorkModal)
        }
    })

    editWorksDescription.blur(function () {
        if (regText.test(editWorksDescription.val())) {
            validateAddWorkModal("descriptionOfWorks", "descriptionOfWorks", "editWorks")
        } else {
            validateAddWorkModal("descriptionOfWorks", undefined, "editWorks")
            editWorksDescription.prop('required', true)
        }
    })

    editWorkTimeLunchFrom.blur(function () {
        if (regTime.test(editWorkTimeLunchFrom.val())) {
            checkTimeLunchFrom(editWorkDate, editWorkTimeFrom, editWorkTimeTo, editWorkTimeLunchFrom, editWorkTimeLunchTo, exceptionEditWorkModal)
        } else {
            editWorkTimeLunchFrom.val(undefined)
            editWorkTimeLunchTo.val(undefined)
        }
    })

    $('#save-editWorksModal-newAct').click(async function () {
        exceptionEditWorkModal.empty()

        let workOld = listWorks[editWorkIndex]
        let work = {
            workDate: editWorkDate.val()
            , startWork: editWorkTimeFrom.val()
            , endWork: editWorkTimeTo.val()
            , startLunch: editWorkTimeLunchFrom.val()
            , endLunch: editWorkTimeLunchTo.val()
            , placeOfDeparture: editPlaceOfDeparture.val()
            , placeOfArrival: editPlaceOfArrival.val()
            , mileage: editMileage.val()
            , workDescription: editWorksDescription.val()
        }

        listWorks.splice(editWorkIndex, 1)
        console.log(listWorks)
        // work.workDate = editWorkDate.val()
        // work.startWork = editWorkTimeFrom.val()
        // work.endWork = editWorkTimeTo.val()
        // work.startLunch = editWorkTimeLunchFrom.val()
        // work.endLunch = editWorkTimeLunchTo.val()
        // work.placeOfDeparture = editPlaceOfDeparture.val()
        // work.placeOfArrival = editPlaceOfArrival.val()
        // work.mileage = editMileage.val()
        // work.workDescription = editWorksDescription.val()

        if (await addWork(work, exceptionEditWorkModal)) {
            console.log("checked addWork")
            createWorkRow(listWorks)
            resetValidateModals(exceptionEditWorkModal, "editWorks")
            $('#close-editWorkModal-top-newAct').click()
        } else {
            listWorks.push(workOld)
        }

        // if (regTime.test(editWorkTimeLunchFrom.val())) {
        //
        //     work.workDate = editWorkDate.val()
        //     work.startWork = editWorkTimeFrom.val()
        //     work.endWork = editWorkTimeTo.val()
        //     work.startLunch = editWorkTimeLunchFrom.val()
        //     work.endLunch = editWorkTimeLunchTo.val()
        //     work.placeOfDeparture = editPlaceOfDeparture.val()
        //     work.placeOfArrival = editPlaceOfArrival.val()
        //     work.mileage = editMileage.val()
        //     work.workDescription = editWorksDescription.val()
        //
        //
        // } else {
        //
        //     work.workDate = editWorkDate.val()
        //     work.startWork = editWorkTimeFrom.val()
        //     work.endWork = editWorkTimeTo.val()
        //     work.startLunch = undefined
        //     work.endLunch = undefined
        //     work.placeOfDeparture = editPlaceOfDeparture.val()
        //     work.placeOfArrival = editPlaceOfArrival.val()
        //     work.mileage = editMileage.val()
        //     work.workDescription = editWorksDescription.val()
        //
        // }
        // createWorkRow(listWorks)
        // resetValidateModals(exceptionAddWorkModal, "editWorks")
    })


    $('#save-newAct').click(async function () {
        $.each(arr, function (i, v) {
            if (v !== undefined) {
                checkedEngineersAdd.push(v)
                arr = [];
            }
        })
        let parts = await getPartsList(listPartForAct)
        let data = {
            date: $('#date').val()
            , number: numberAct.val()
            , numberApplication: numberAndDateApplication.val()
            , works: listWorks
            , machine: checkedMachine
            , users: checkedEngineersAdd
            , car: $('#inputCarModel').val()
            , licensePlate: $('#inputCarNumber').val()
            , placeOfWork: $('#placeOfWork').val()
            , actPay: getPayAct()
            , actDescription: descriptionOfAct.val()
            , parts: parts
        };

        if (await validateAct(data)) {
            let form = getArrFileForUpload()
            form.append("act", new Blob([JSON.stringify(data)], {
                type: "application/json"
            }));
            let response = await fetch('http://localhost:8080/api/v5/acts/create', {
                method: 'POST'
                , body: form
            })

            if (response.json().then(res => {
                return res
            })) {
                location.reload()
            } else {
                exceptionValidateActModal.modal('show')
                exceptionValidateActModalBody.append(
                    "<p class='text-danger' style='font-size: 20px'>Произошла ошибка при добавлении файлов обратитесь к администратору!</p>"
                )
            }
        }
        checkedEngineersAdd = [];
    })

    $('#exceptionValidate-NewAct button[name = "exceptionValidate-close-NewAct"]').click(
        function () {
            exceptionValidateActModalBody.empty()
        }
    )

    $('#inputGroupFileAddNewAct').click(async function () {

        let input = document.getElementById('inputGroupFileNewAct')
        $.each(input.files, function (k, v) {
            filesForAct.append(v.name, v)
            // filesForAct.append('file', v)
        })

        $('#fileList-newAct').empty()
        createTableFilesForAct()
        $('#inputGroupFileNewAct').val('')
    })

    $('#close-newAct-addFile').click(function () {
        // filesForAct = new FormData()
        // $('#fileList-newAct').empty()
        $('#inputGroupFileNewAct').val('')
    })
    $('#reset-button-addFile-newAct').click(function () {
        // filesForAct = new FormData()
        // $('#fileList-newAct').empty()
        $('#inputGroupFileNewAct').val('')
    })
})
;

function getArrFileForUpload() {
    let newFormData = new FormData;
    filesForAct.forEach(function (k, v) {
        newFormData.append("files", k);
    })
    return newFormData;

}

function createTableFilesForAct() {
    filesForAct.forEach(function (k, v) {
        $('#fileList-newAct').append(
            "<tr >" +
            "<td>" +
            "<button onclick='deleteFile(this)' name='" + v + "'  type='button' class= 'btn btn-primary btn-sm'>" +
            "<i class='bi bi-x-circle-fill'></i>" +
            "</button>" +
            "</td>" +
            "<td>" + v + "</td>" +
            "</tr>"
        )
    })

}

function deleteFile(nameFile) {
    let deleteFile = $(nameFile).attr('name')
    filesForAct.delete(deleteFile)
    $('#fileList-newAct').empty()
    createTableFilesForAct()
}

function deletePart(partId) {
    // let idTr = listPartForAct.at(partIndex - 1).id
    // listPartForAct.splice(partIndex - 1, 1)
    listPartForAct.delete(partId)
    $("#partsList-newAct tr[id =" + partId + "]").remove()
}

async function getPartsList(listPartForAct) {
    let list = [];
    listPartForAct.values()
    $('#partsList-newAct tr').each(function () {
        let t = $(this).attr('id')
        listPartForAct.forEach(function (k, v) {
            console.log(k)
            console.log(v)
            if (v === +t) {

                // let data = {
                //     id: +t
                //     , identNumber: i.identNumber
                //     , name: i.name
                //     , count: +$('#' + t + ' input').val()
                //     , owner: $('#' + t + ' option:selected').val()
                // }
                list.push(k)
            }
        })

    })
    return list;
}

async function validateAct(act) {
    let res = true;
    if (act.date === '') {
        exceptionValidateActModalBody.append(
            "<p class='text-danger' style='font-size: 20px'>Не указана дата акта!</p>"
        )
        res = false;
    } else if (act.works.length === 0) {
        exceptionValidateActModalBody.append(
            "<p class='text-danger' style='font-size: 20px'>В акте нет работ!</p>"
        )
        res = false;
    } else if (act.machine === undefined) {
        exceptionValidateActModalBody.append(
            "<p class='text-danger' style='font-size: 20px'>Выберете машину!</p>"
        )
        res = false;
    } else if (!regText.test(act.actDescription)) {
        console.log("regText test")
        exceptionValidateActModalBody.append(
            "<p class='text-danger' style='font-size: 20px'>Необходимо описание к акту!</p>"
        )
        res = false;
    }

    if (!res) {
        exceptionValidateActModal.modal('show')
        return false;
    } else {
        return true;
    }

}




function clearAddWorkModal() {
    dateWork.val('')
    timeFrom.val('')
    timeTo.val('')
    timeLunchTo.val('')
    timeLunchFrom.val('')
    descriptionOfWorks.val('')
    placeOfDeparture.val('')
    placeOfArrival.val('')
    mileage.val('')
    timeFrom.prop('disabled', true)
    timeTo.prop('disabled', true)
    timeLunchFrom.prop('disabled', true)
    // exceptionAddWorkModal.empty()
}

function clearArr() {
    $.each(arr, function (index) {
        arr[index] = 0;
    })
    $('#engineers-newAct-List').empty()
}

function getTime(timeMs) {
    let hours = Math.floor(timeMs / 3600000)
    let minutes = (timeMs / 60000) % 60
    let h = hours < 9 ? '0' + hours : hours
    let m = minutes < 9 ? '0' + minutes : minutes
    return h + ':' + m;
}

// async function addWork(date, timeFrom, timeTo, timeLunchFrom, timeLunchTo, routeFrom, routeTo, mileage, descriptionOfWorks) {
async function addWork(work, exceptionModal) {
    if (await checkWork(work, exceptionModal)) {
        listWorks.push(work)
        return true;
    } else {
        return false;
    }


}

async function checkWork(work, exceptionModal) {
    let result;
    console.log(work)
    if (listWorks.length > 0) {
        listWorks.forEach(function (v) {
            console.log(v)
            let timeStartInListWorks = new Date(v.workDate + "T" + v.startWork).getTime()
            let timeEndInListWorks = new Date(v.workDate + "T" + v.endWork).getTime()
            let timeStartCurrent = new Date(work.workDate + "T" + work.startWork).getTime()
            let timeEndCurrent = new Date(work.workDate + "T" + work.endWork).getTime()
            if ((timeStartCurrent >= timeStartInListWorks && timeEndCurrent <= timeEndInListWorks)
                || (timeStartCurrent < timeStartInListWorks && timeEndCurrent > timeStartInListWorks)
                || (timeStartCurrent < timeEndInListWorks && timeEndCurrent > timeEndInListWorks)) {
                exceptionModal.empty()
                exceptionModal.append(
                    "<p class='text-danger'>" +
                    "В акте уже есть такой промежуток времени" +
                    "</p>"
                )
                result = false;
                return false;
            } else {
                result = true;
            }
        })
        result ? await checkWorkInDb(work, exceptionModal) : result;
    } else {
        result = await checkWorkInDb(work, exceptionModal);
    }
    return result;
}

async function checkWorkInDb(work, exceptionModal) {
    let bool;
    let t = await fetch('http://localhost:8080/api/v5/acts/checkWorks?id=' + authUserId, {
        method: 'POST'
        , headers: {
            'Content-type': 'application/json; charset=utf-8'
        }
        , body: JSON.stringify(work)
    })
    if (t.status === 302) {
        await t.json().then(works => works).then(t => {
            $.each(t, function (k, v) {
                let numberAct = t[k].actReadDtoForCheckWorks.number;
                let dateAct = t[k].workDate;
                let startWork = t[k].startWork;
                let endWork = t[k].endWork;
                exceptionModal.append(
                    "<p class='text-danger'>" +
                    "В акте " + numberAct + " от " +
                    dateAct + " c " + startWork + " до " + endWork +
                    " проводились работы." +
                    "</p>"
                )
            })
        })
        bool = false;
    } else {
        bool = true;
    }
    return bool;

}


function createWorkRow(listWorks) {
    $('#body-addWorksTable-newAct').empty()
    timeOfWork = 0
    $.each(listWorks, function (i, v) {
        let startLunch = v.startLunch === '' ? "00:00" : v.startLunch
        let endLunch = v.endLunch === '' ? "00:00" : v.endLunch
        let timeOfLunch = new Date(v.workDate + 'T' + endLunch)
            - new Date(v.workDate + 'T' + startLunch)

        let timeOfWorkWithLunch = ((new Date(v.workDate + 'T' + v.endWork)
                - new Date(v.workDate + 'T' + v.startWork))
            - timeOfLunch)

        let timeW = getTime(timeOfWorkWithLunch)

        let timeL = getTime(timeOfLunch)

        timeOfWork = timeOfWork + timeOfWorkWithLunch
        $('#body-addWorksTable-newAct').append(
            "<tr>" +
            "<th scope='row'>" + getWeekDay(new Date(v.workDate).getDay()) + "</th>" +
            "<td>" + v.workDate + "</td>" +
            "<td>" + v.startWork + "</td>" +
            "<td>" + v.endWork + "</td>" +
            "<td>" + timeL + "</td>" +
            "<td>" + timeW + "</td>" +
            "<td> " +
            "<li>" + v.placeOfDeparture + "</li>" +
            "<li>" + v.placeOfArrival + "</li>" +
            "</td>" +
            "<td>" + v.mileage + "</td>" +
            "<td>" +
            "<div class='row p-2'>" +
            "<div class='col'>" +
            "<button onclick='editWork(" + String(i) + ")' type='button' class='btn btn-success'>Изменить</button>" +
            "</div>" +
            "<div class='col'>" +
            "<button onclick='deleteWork(" + String(i) + ")' type='button' class='btn btn-danger'>Удалить</button>" +
            "</div>" +
            "</div>" +
            "</td>" +
            "</tr>" +
            "<tr> <td colSpan='9'> <textarea class='form-control' rows='4' disabled>" + v.workDescription + " </textarea> </td> </tr>" +
            "<tr> <td colSpan='9' class='table-primary'></td> </tr>"
        );
    })
}

function editWork(workIndex) {
    editWorkIndex = +workIndex
    let work = listWorks[editWorkIndex]
    $('#editWorksModal').modal('show')
    $('#editWorksModal input[name = "date-editWork"]').val(work.workDate)
    $('#editWorksModal input[name = "timeWorkFrom-editWork"]').val(work.startWork)
    $('#editWorksModal input[name = "timeWorkTo-editWork"]').val(work.endWork)
    $('#editWorksModal input[name = "timeLunchFrom-editWork"]').val(work.startLunch)
    $('#editWorksModal input[name = "timeLunchTo-editWork"]').val(work.endLunch)
    $('#editWorksModal input[name = "placeOfDeparture-editWork"]').val(work.placeOfDeparture)
    $('#editWorksModal input[name = "placeOfArrival-editWork"]').val(work.placeOfArrival)
    $('#editWorksModal input[name = "mileage-editWork"]').val(work.mileage)
    $('#editWorksModal textarea[name = "descriptionOfWorks-editWork"]').val(work.workDescription)

    validateAddWorkModal("descriptionOfWorks", "descriptionOfWorks", "editWorks")
    validateAddWorkModal("timeWorkFrom", "timeWorkFrom", "editWorks")
    validateAddWorkModal("timeWorkTo", "timeWorkTo", "editWorks")
    validateAddWorkModal("date", "date", "editWorks")


}

function resetValidateModals(exceptionRow, worksModal) {
    exceptionRow.empty()
    validateAddWorkModal("descriptionOfWorks", undefined, worksModal)
    validateAddWorkModal("timeWorkFrom", undefined, worksModal)
    validateAddWorkModal("timeWorkTo", undefined, worksModal)
    validateAddWorkModal("date", undefined, worksModal)
}

function deleteWork(workIndex) {
    listWorks.splice(Number(workIndex), 1)
    createWorkRow(listWorks)
}

let map = {date: undefined, timeWorkFrom: undefined, timeWorkTo: undefined, descriptionOfWorks: undefined}

function validateAddWorkModal(validatePosition, val, nameModal) {
    map[validatePosition] = val
    let bool;
    $.each(map, function (k, v) {
        if (map[k] === undefined) {
            bool = false
            return false;
        } else {
            bool = true
        }
    })
    if (bool) {
        $('#' + nameModal + 'Modal button[id = "save-' + nameModal + 'Modal-newAct"]').prop('disabled', false)
    } else {
        $('#' + nameModal + 'Modal button[id = "save-' + nameModal + 'Modal-newAct"]').prop('disabled', true)
    }
}

function checkTimeFrom(timeFrom, timeTo, timeLunchFrom, timeLunchTo, exceptionModal, workDate, worksModal) {
    if (regTime.test(timeFrom.val())) {
        checkTimeTo(timeFrom, timeTo, timeLunchFrom, timeLunchTo, exceptionModal, workDate, worksModal)
        timeTo.prop('disabled', false)
        timeLunchFrom.prop('disabled', false)
        validateAddWorkModal("timeWorkFrom", "timeWorkFrom", "addWorks")
    } else {
        exceptionModal.empty()
        timeTo.val(undefined)
        timeLunchFrom.val(undefined)
        timeLunchTo.val(undefined)
        timeTo.prop('disabled', true)
        timeLunchFrom.prop('disabled', true)
        validateAddWorkModal("timeWorkTo", undefined, "addWorks")
    }

}

function checkTimeTo(timeFrom, timeTo, timeLunchFrom, timeLunchTo, exceptionModal, dateWork, worksModal) {
    if (regTime.test(timeTo.val())) {
        if ((new Date(dateWork.val() + 'T' + timeTo.val()) - new Date(dateWork.val() + 'T' + timeFrom.val())) <= 0) {
            exceptionModal.empty()
            timeTo.val(undefined)
            timeTo.prop('required', true)
            validateAddWorkModal("timeWorkTo", undefined, worksModal)
            exceptionModal.empty()
            exceptionModal.append(
                "<p class='text-danger'>Не верно указано время окончания работ!</p>"
            )
        } else {
            exceptionModal.empty()
            validateAddWorkModal("timeWorkTo", "timeWorkTo", worksModal)
        }
    } else {
        exceptionModal.empty()
        timeLunchFrom.val(undefined)
        timeLunchTo.val(undefined)
        validateAddWorkModal("timeWorkTo", undefined, worksModal)
    }
}

function checkTimeLunchFrom(dateWork, timeWorkFrom, timeWorkTo, timeLunchFrom, timeLunchTo, exceptionModal) {
    if (regTime.test(timeWorkFrom.val()) && regTime.test(timeWorkTo.val())) {
        let timeLunchFomMls = new Date(dateWork.val() + 'T' + timeLunchFrom.val()).getTime()
        let timeLunchToMls = new Date(dateWork.val() + 'T' + timeLunchFrom.val()).getTime() + 3600000
        let timeWorkFromMls = new Date(dateWork.val() + 'T' + timeWorkFrom.val()).getTime();
        let timeWorkToMls = new Date(dateWork.val() + 'T' + timeWorkTo.val()).getTime()
        if (timeLunchFomMls > timeWorkFromMls && timeLunchToMls <= timeWorkToMls) {
            exceptionModal.empty()
            let val = new Date(timeLunchToMls)
            timeLunchTo.val((val.getHours() < 9 ? "0" + val.getHours() : val.getHours())
                + ":"
                + (val.getMinutes() < 9 ? "0" + val.getMinutes() : val.getMinutes()))
        } else {
            exceptionModal.empty()
            exceptionModal.append(
                "<p class='text-danger'>Не верно указан обеденный перерыв</p>"
            )
            timeLunchFrom.val(undefined)
            timeLunchTo.val(undefined)
        }
    } else {
        timeLunchFrom.val(undefined)
        timeLunchTo.val(undefined)
    }


}


