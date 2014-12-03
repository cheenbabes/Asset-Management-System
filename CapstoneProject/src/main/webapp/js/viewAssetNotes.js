/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    //loadAssets();
    //loadAssetNotes();
    //getNotesForAsset();
    //checkedInOutAssets();
    
    $('#addAsset').click(function() {
        $.ajax({
            type: 'POST',
            url: 'assetNote/' + $('#assetId').val(),
            data: JSON.stringify({
                assetId: $('#assetId').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function(data, status) {
            //getAssetNoteList($('#assetId').val());
            getNotes($('#assetId').val());
        });
    });


});
function loadAssetNotes() {
    $('#assetNote-list').empty();
    $.ajax({
        url: "http://localhost:8080/CapstoneProject/rest/assetNotes",
        type: 'GET'
    }).then(function (data, status) {
        $.each(data, function (index, assetNote) {

            $('#assetNote-list').append('<p>' + " " + assetNote.noteDate + " " + assetNote.category + " " +
                    assetNote.note + '</p>');
        });
    });
}

//function getEvent() {
//    $.ajax({
//        url: "assetNote/" + document.getElementById("#eventForAjax").valueOf(),
//        type: 'GET'
//    }).then(function (data, status) {
//        $.each(data, function (index, assetNote) {
//            getNotesForAsset(assetNote.assetId);
//            
//        });
//    });
//}

//function viewAssetNotes(id) {
//    $.ajax({
//        url: "assetNote/" + id,
//        type: 'GET'
//    }).then(function (data, status) {
//        $.each(data, function (index, assetNote) {
//
//            $('#getNotesForAsset-list').append('<p>' + " " + assetNote.noteDate + " " + assetNote.category + " " +
//                    assetNote.note + '</p>');
//        });
//    });
//}

function getNotes(id) {
    //ajax defaults to get
    $.ajax({
        url: 'assetNote/' + id
    }).success(function(data) {
        $('#detail-category').text('Category: ' + data.category);
        $('#detail-noteDate').text('Time: ' + data.noteDate);
        $('#detail-note').text('Note: ' + data.note);
    })
}

//$("#assetId").val()

//function getAssetNoteList(element) {
//    loadAssetNotes();
//    //var event = document.getElementById("#eventForAjax").valueOf();
//    var assetId = document.getElementById("#assetId").valueOf();
//    $.ajax({
//        url: "assetNote/" + element.id,
//        type: 'GET'
//    }).then(function (data, status) {
//        $.each(data, function (index, assetNote) {
//
//            $('#getNotesForAsset-list').append('<p>' + " " + assetNote.noteDate + " " + assetNote.category + " " +
//                    assetNote.note + '</p>');
//        });
//    });
//    
//}

//function loadAssetNotesForEvent(element) {
//    $('#assetNoteForEvent-list').empty();
//    $.ajax({
//        url: "assetNote/" + element.id,
//        type: 'GET'
//    }).then(function (data, status) {
//        $.each(data, function (index, assetNote) {
//            $("#eventForAjax").val();
//        });
//    });
//}


//function checkedInOutAssets() {
////ajax defaults to get
//    $('#checkedOut-list').empty();
//    $('#checkedIn-list').empty();
//    $.ajax({
//        url: "http://localhost:8080/CapstoneProject/rest/assets",
//    }).then(function (data, status) {
//        $.each(data, function (index, asset) {
//            if (asset.inStock === 0 || asset.inStock === false) {
//
//                $('#checkedOut-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
//                        asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getNotesForAsset(${asset.assetId});" href="#" id="' + asset.assetId + '">Info   </a>|<a onclick="checkOutInAsset(' + asset.assetId + ');" href="#" id="' + asset.assetId + '">   Check In</a></p>');
//
//            }
//        });
//    });
//}