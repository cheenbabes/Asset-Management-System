/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadAssetTypes();
    loadAssets();

});
function loadAssets() {
    $('#asset-list').empty();
    $.ajax({
        url: "http://localhost:8080/CapstoneProject/rest/assets",
        type: 'GET'
    }).then(function (data, status) {
        $.each(data, function (index, asset) {

            $('#asset-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
                    asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getAssetInfo(this);" href="#" id="' + asset.assetId + '">Info</a><a onclick="loadAssetTypes();" href="#">Return To All AssetTypes</a></p>');
        });
    });
}

//function loadAssets() {
//    //$('#assetType-list').empty();
//    $.ajax({
//        url: "http://localhost:8080/CapstoneProject/rest/assetTypes",
//        type: 'GET'
//    }).then(function (data, status) {
//        $.each(data, function (index, assetType) {
//
//            $('#asset-list').append('<p><a onclick="getAssetType(this);" href="#" id="' +
//                    assetType.assetTypeId + '">' + assetType.assets + '</a></p><a onclick="getAssetInfo(this);" href="#" id="' + assetType.assets.assetId + '">Info</a>');
//        });
//    });
//
//}


function getAssets(element) {
    //$('#assetType-list').empty();
    //$('#asset-list').empty();
    $.ajax({
        url: "test/" + element.id
    }).then(function (data, status) {
        $.each(data, function (index, asset) {
            $('#assetOfType-list').append('<p>' + " " + asset.assetType.name + " " + asset.assetType.category.categoryName + " " +
                    asset.inStock + " " + asset.serialNumber + " " + asset.damageStatus + '<a onclick="getAssetInfo(this);" href="#" id="' + asset.assetId + '">Info</a><a onclick="loadAssetTypes();" href="#">Return To All AssetTypes</a></p>');
        });
    });
}

function getAssetType(element) {
//ajax defaults to get
    $.ajax({
        url: 'test/' + element.id
    }).success(function (data) {
        $('#detail-name').text('Name: ' + data.name);
    })
}

function getAssetInfo(element) {
//ajax defaults to get
    $.ajax({
        url: 'test/' + element.id
    }).success(function (data) {
        $('#detail-inStock').text('In Stock: ' + data.inStock);
        $('#detail-serialNumber').text('Serial Number: ' + data.serialNumber);
        $('#detail-damageStatus').text('Damage Status: ' + data.damageStatus);
    })
}


function loadAssetTypes() {
    $('#assetType-list').empty();
    //$('#assetOfType-list').empty();
    //$('#asset-list').empty();
    $.ajax({
        url: "http://localhost:8080/CapstoneProject/rest/assetTypes",
        type: 'GET'
    }).then(function (data, status) {
        $.each(data, function (index, assetType) {
            $('#assetType-list').append('<p><a onclick="getAssetType(this);" href="#" id="' +
                    assetType.assetTypeId + '">' + assetType.name + " " + assetType.category.categoryName + '</a></p><a onclick="getAssets(this)" href="#" id="' + assetType.assetTypeId + '">View All Assets of This Type</a>');
        });
    });
}