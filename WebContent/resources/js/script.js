/**
 * Script for client-side-logic for handling user input, input validation etc. 
 * No ADMIN logic here to hide the administration logic from public.
 */

function handleRegistration () {
	var userData = {};
	$("#register-submit").on("click", function (e) {
		e.preventDefault();
		if (!allInputsAreValid()) {
			return;
		}
		toggleInputs(true);
		userData = {
			name: $('#name').val(),
			surname: $('#surname').val(),
			email: $('#email').val(),
			password: $('#password').val()
		};

		var CSRF_TOKEN = $('[name=_csrf]').val();
		var CSRF_HEADER = "X-CSRF-TOKEN";
		
		showMessage("Ihre Eingabe wird verarbeitet.", false);
		$.ajax({
			url: "/kirjanystaevaet/registrierung",
			type: "POST",
			data: userData,
			contentType: 'application/json',
		    mimeType: 'application/json',
			beforeSend: function (xhr) {
				xhr.setRequestHeader(CSRF_HEADER, CSRF_TOKEN);
			}
		})
		.done(function (response) {
			console.log(response)
			// if response is success, redirect the user (should be done by server, if we end up here, something is wrong
		})
		.fail(function (jqXHR, status, err) {
			if (jqXHR.status === 404) {
				console.log("Houston, we have a problem.");
			}
			$("#password").val("");
			showMessage("Der Account konnte nicht angelegt werden, versuchen Sie es mit einer anderen Email-Adresse.", true)
			toggleInputs(false);
		});
	});
	
	function toggleInputs (status) {
		$("#register-submit").prop("disabled", status);
		$("#name").prop("disabled", status);
		$("#surname").prop("disabled", status);
		$("#email").prop("disabled", status);
		$("#password").prop("disabled", status);
	}
	
	function showMessage (text, error) {
		$("#info-message").show().text(text);
		if (error) {
			$("#info-message").css("color", "red");
		} else {
			$("#info-message").css("color", "#727272");
		}
	}
	
	function allInputsAreValid () {
		var regex = /[A-Za-z0-9\.\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}\~]+\@[A-Za-z0-9\_\-]+\.[A-Za-z]{2,3}/g;
		return $("#name").val().trim() !== "" && 
			$("#surname").val().trim() !== "" && 
			$("#email").val().trim() !== "" && 
			regex.test($("#email").val().trim()) &&
			$("#password").val().trim() !== "";
	}
}

$(document).ready(function () {
	// DOM loaded, check what we have to do
	if (window.location.pathname.indexOf("/registrierung") != -1) {
		// we are on the register page, handle the registration
		handleRegistration();
	}
});