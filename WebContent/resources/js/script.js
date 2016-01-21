/**
 * Script for client-side-logic for handling user input, input validation etc. 
 * No ADMIN logic here to hide the administration logic from public.
 */

// AJAX handler for register page
function handleRegistration () {
	var userData = {};
	
	$("#register-submit").on("click", function (e) {
		e.preventDefault();
		$(this).prop("disabled", true);
		userData = {
			name: $('#name').prop("disabled", true).val(),
			surname: $('#surname').prop("disabled", true).val(),
			email: $('#email').prop("disabled", true).val(),
			password: $('#password').prop("disabled", true).val()
		};

		var CSRF_TOKEN = $('[name=_csrf]').val();
		var CSRF_HEADER = "X-CSRF-TOKEN";
		
		// POST data to server, if response is error, check which data is wrong and display it to the user and give him a second try
		$.ajax({
			url: "/registrierung",
			type: "POST",
			data: userData,
			beforeSend: function (xhr) {
				xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
			}
		})
		.done(function (response) {
			console.log(response)
			// if response is success, redirect the user (should be done by server, if we end up here, something is wrong
		})
		.fail(function (jqXHR, status, err) {
			// an error occurred, hopefully because the user already exists. no one can know...
			console.log(jqXHR, status, err);
		});
	});
	
	
}

$(document).ready(function () {
	// DOM loaded, check what we have to do
	if (window.location.pathname.indexOf("/registrierung") != -1) {
		// we are on the register page, handle the registration
		handleRegistration();
	}
});