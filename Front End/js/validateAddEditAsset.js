 $(document).ready(function() {
    $('.addEditAsset').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: 'Your name is not valid',
                validators: {
                    notEmpty: {
                        message: 'The name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'The name must be more than 2 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The name can only consist of alphabetical, number and underscore'
                    }
                }
            },
            category: {
            },
            assetType: {
            },
            status: {
            },
            serialNum: {
                message: 'The serial number is not valid',
                validators: {
                    notEmpty: {
                        message: 'The serial number is required and cannot be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'The serial number must be more than 6 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The serial number can only consist of alphabetical, number and underscore'
                    }
                }
            },
            image: {
            },
            damage: {
            },
            assetNote: {
            }
            
            
            
        }
    });
});