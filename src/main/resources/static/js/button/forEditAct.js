let actPay;
let checkedMachine;
$(document).ready(async function (){
    const actId = $('#editActId').attr('value')

    let act = (await getListEntities("/api/v5/acts/"+actId))
    console.log(act)
    console.log(act.date)
    $('#date').attr('disabled' , true)
    $('#date').val(act.date)
    $('#input-numberAct-newAct').val(act.number)
    $('#numberAndDAteApplication').val(act.numberApplication)
    $('#inputCarModel').val(act.car)
    $('#inputCarNumber').val(act.licensePlate)
    $('#placeOfWork').val(act.placeOfWork)
    $('#descriptionOfAct').text(act.actDescription)
    $('#nameCompany-newAct').text(act.machine.company.nameCompany)
    $('#inn-newAct').text(act.machine.company.inn)
    $('#kpp-newAct').text(act.machine.company.kpp)
    $('#streetAndHouse-newAct').text(act.machine.company.locationCompany.street +
        "," + act.machine.company.locationCompany.house)
    $('#city-newAct').text(act.machine.company.locationCompany.city)
    $('#zipCode-newAct').text(act.machine.company.locationCompany.zipCode)
    checkedMachine = act.machine.id
    $('#modelMachine-newAct').text(act.machine.type)
    $('#serialNumber-newAct').text(act.machine.serialNumber)
    $('#type-newAct').text(act.machine.subtype)
    $('#yearOfRelease-newAct').text(act.machine.yearOfRelease)
    $('#operatingTime-newAct').val(act.machine.operatingTime)
    let checkedEngineer =  act.users

    let allEngineers;
    const authUser = await getAuthUser()
    await checkedEngineers(checkedEngineer);

    $('#actPay-newAct input[name="options-outlined"]').each(function (k,v){
        if(this.value === act.actPay){
            $('#'+this.id).attr('checked', true)
        }
    })

    // $('#search-button-newAct-AddMachine').click(async function () {
    //     let filter = $('#search-button-val-machines-newAct').val()
    //     let machines = (await getListEntities(getUriForListEntities("http://localhost:8080/api/v2/machines?"
    //         , "serialNumber"
    //         , filter
    //         , 0
    //         , 10))).content
    //     createMachineTableForUser('body-machineTable-newAct', machines)
    //
    //     $('#body-machineTable-newAct input[name = "machineRadioNewAct"]').click(function () {
    //         let id = $(this).val()
    //         $.each(machines, function (ind, val) {
    //             if (val.id === +id) {
    //                 checkedMachine = val
    //             }
    //         })
    //     })
    // })



    $('#button-addEngineer-newAct').click(function () {
        $('#engineers-newAct-List').empty()
    })

    $('#search-button-newAct-AddEngineer').click(async function (){
        let input = $('#search-button-val-engineers-newAct').val()
        allEngineers = (await getListEntities(getUriForListEntities('/api/v1/usersByFilter?', 'lastname', input))).content
        $('#engineers-newAct-List').empty()
        await createEngineersTable(allEngineers, authUser,'#engineers-newAct-List', checkedEngineer)
        $('#engineers-newAct-List input[name ="checkBox-engineer-newAct"]').click(function () {
            let val = $(this).val()
            if ($(this).is(':checked')) {
                $.each(allEngineers, function (i, v) {
                    if (v.id === +val) {
                        checkedEngineer[val] = v
                    }
                })
            } else {
                checkedEngineer[val] = undefined
            }
        })
    })

    $('#button-addEngineer-newAct-modal').click(function (){
        checkedEngineers(checkedEngineer)
    })





})