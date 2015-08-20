function initUserAdmin() {
	TableManaged.init();

	$('#add_users').hide();
	
	$('button.btn.green').click(function() {
		$('#add_users').show();
		$('#list_users').hide();
	});
	$('a.btn.default.button-previous').click(function(){
		$('#add_users').hide();
		$('#list_users').show();
	});
	$('button.btn.default').click(function() {
		sendRequestJQ('','','');
	});

    var form2 = $('#submit_form');
    var error2 = $('.alert-danger', form2);
    var success2 = $('.alert-success', form2);
    form2.validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-block help-block-error', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",  // validate all fields including form hidden input
        rules: {
        	username: {
                minlength: 6,
                required: true
            },
            email: {
                required: true,
                email: true
            },
            email: {
                required: true,
                email: true
            },
            url: {
                required: true,
                url: true
            },
            number: {
                required: true,
                number: true
            },
            digits: {
                required: true,
                digits: true
            },
            creditcard: {
                required: true,
                creditcard: true
            },
        },

        invalidHandler: function (event, validator) { //display error alert on form submit              
            success2.hide();
            error2.show();
            Metronic.scrollTo(error2, -200);
        },

        errorPlacement: function (error, element) { // render error placement for each input type
            var icon = $(element).parent('.input-icon').children('i');
            icon.removeClass('fa-check').addClass("fa-warning");  
            icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
        },

        highlight: function (element) { // hightlight error inputs
            $(element)
                .closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group   
        },

        unhighlight: function (element) { // revert the change done by hightlight
            
        },

        success: function (label, element) {
            var icon = $(element).parent('.input-icon').children('i');
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
            icon.removeClass("fa-warning").addClass("fa-check");
        },

        submitHandler: function (form) {
            success2.show();
            error2.hide();
            form[0].submit(); // submit the form
        }
    });
    
}