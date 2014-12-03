/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    //loadAssets();
    checkedInOutAssets();



//    $('#checkOut').click(function () {
//        $.ajax({
//            type: 'PUT',
//            url: 'test/' + $('#edit-assetId').val(),
//            data: JSON.stringify({
//                assetId: $('#edit-assetId').val(),
//                inStock: $('#edit-inStock').val()
//            }),
//            headers: {
//                'Accept': 'application/json',
//                'Content-Type': 'application/json'
//            },
//            'dataType': 'json'
//        }).success(function (data, status) {
//            $('#edit-assetId').val(''),
//            $('#edit-inStock').val('');
//            loadAssets();
//            checkedInOutAssets();
//        });
//    });
});
//function loadAssets() {
//    $('#asset-list').empty();
//    $.ajax({
//        url: "http://localhost:8080/CapstoneProject/rest/assets",
//        type: 'GET'
//    }).then(function (data, status) {
//        $.each(data, function (index, asset) {
//
//            $('#asset-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
//                    asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getAssetInfo(this);" href="#" id="' + asset.assetId + '">Info   </a>|\n\
//                    <a onclick="checkOutAsset(' + asset.assetId + ');" href="#" id="' + asset.assetId + '">Check Out Asset</a></p>');
//        });
//    });
//}


function checkedInOutAssets() {
//ajax defaults to get
    $('#checkedOut-list').empty();
    $('#checkedIn-list').empty();
    $.ajax({
        url: "http://localhost:8080/CapstoneProject/rest/assets",
    }).then(function (data, status) {
        $.each(data, function (index, asset) {
            if (asset.inStock === 1 || asset.inStock === true) {
                $('#checkedIn-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
                        asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getAssetInfo(this);" href="#" id="' + asset.assetId +
                        '">Info   </a>|<a onclick="checkOutInAsset(' + asset + ');" href="#" id="' + asset.assetId + '">   Check Out</a></p>');
            }
            if (asset.inStock === 0 || asset.inStock === false) {

                $('#checkedOut-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
                        asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getAssetInfo(this);" href="#" id="' + asset.assetId + '">Info   </a>|<a onclick="checkOutInAsset(' + asset.assetId + ');" href="#" id="' + asset.assetId + '">   Check In</a></p>');

            }
        });
    });
}

//function checkedInAsset(element) {
////ajax defaults to get
//    $.ajax({
//        url: 'rest/test/' + element.id
//    }).then(function (data, status) {
//        $.each(data, function (index, asset) {
//            if (asset.inStock === true) {
//                $('#checkedIn-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
//                        asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getAssetInfo(this);" href="#" id="' + asset.assetId + '">Info   </a></p>');
//            }
//        });
//    });
//}


//function getAssets(element) {
//    //$('#assetType-list').empty();
//    //$('#asset-list').empty();
//    $.ajax({
//        url: "test/" + element.id
//    }).then(function (data, status) {
//        $.each(data, function (index, asset) {
//            $('#assetOfType-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
//                    asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getAssetInfo(this);" href="#" id="' + asset.assetId + '">Info</a></p>');
//        });
//    });
//}



function getAssetInfo(element) {
//ajax defaults to get
    $.ajax({
        url: 'rest/' + element.id
    }).success(function (data) {
        $('#detail-inStock').text('In Stock: ' + data.inStock);
        $('#detail-serialNumber').text('Serial Number: ' + data.serialNumber);
        $('#detail-damageStatus').text('Damage Status: ' + data.damageStatus);
    })
}
//function checkOut() {
//
//    $('#checkOut').click(function () {
//        $.ajax({
//            type: 'PUT',
//            url: 'test/' + $('#edit-assetId').val(),
//            data: JSON.stringify({
//                assetId: $('#edit-assetId').val(),
//                inStock: $('#edit-inStock').val()
//            }),
//            headers: {
//                'Accept': 'application/json',
//                'Content-Type': 'application/json'
//            },
//            'dataType': 'json'
//        }).success(function (data, status) {
//            $('#edit-assetId').val(''),
//            $('#edit-inStock').val('');
//            loadAssets();
//            checkedInOutAssets();
//        });
//    });
//}

function checkOutInAsset(asset) {

    $.ajax({
        type: 'PUT',
        url: 'test/' + asset.assetId,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data) {
        $('#assetsForEvent-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
                        asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getAssetInfo(this);" href="#" id="' + asset + '">Info   </a>|<a onclick="checkOutInAsset(' + asset.assetId + ');" href="#" id="' + asset.assetId + '">   Check In</a></p>');

    });
    loadAssets();
    checkedInOutAssets();
}




