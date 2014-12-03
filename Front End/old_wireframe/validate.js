function validateForm() {
    var x = document.forms["editAssetForm"]["assetName"].value;
    if (x == null || x == "") {
        alert("asset name must be filled out");
        return false;
    }
}