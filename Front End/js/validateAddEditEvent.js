    $(document).ready(function() {
    $('.addEditEvent').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nameUser: {
                message: 'Your name is not valid',
                validators: {
                    notEmpty: {
                        message: 'The name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'Your name must be more than 2 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'Your name can only consist of alphabetical, number and underscore'
                    }
                }
            },
            userName: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: 'The username is required and cannot be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'The username must be more than 6 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The username can only consist of alphabetical, number and underscore'
                    }
                }
            },
            location: {
                message: 'The location is not valid',
                validators: {
                    notEmpty: {
                        message: 'The location is required and cannot be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'The location must be more than 6 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The location can only consist of alphabetical, number and underscore'
                    }
                }
            },
            size: {
                message: 'The size is not valid',
                validators: {
                    notEmpty: {
                        message: 'The size is required and cannot be empty'
                    },
                    stringLength: {
                        min: 1,
                        max: 2,
                        message: 'The size must be more than 1 and less than 100'
                    },
                    regexp: {
                        regexp:  /^[1-9]\d*$/,
                        message: 'The size can only consist of numbers'
                    }
                }
            },
             startDate: {
                validators: {
                    notEmpty: {
                        message: 'The starting date is required'
                    },
                    date: {
                        format: 'YYYY/MM/DD',
                        message: 'The starting date is not valid'
                    }
                }
            },
             endDate: {
                validators: {
                    notEmpty: {
                        message: 'The ending date is required'
                    },
                    date: {
                        format: 'YYYY/MM/DD',
                        message: 'The ending date is not valid'
                    }
                }
            },
            image: {

            },
            note: {

            }
        }
    });
});