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
			street: $('#street').val(),
			streetnumber: $('#streetnumber').val(),
			plz: $('#plz').val(),
			email: $('#email').val(),
			password: $('#password').val()
		};
		showMessage("Ihre Eingabe wird verarbeitet.", false);
		$.ajax({
			url: "/kirjanystaevaet/registrierung",
			type: "POST",
			data: JSON.stringify(userData),
			dataType: 'json',
			contentType: 'application/json',
		    processData: false,
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			beforeSend: function (xhr) {
				xhr.setRequestHeader("X-CSRF-TOKEN", $('[name=_csrf]').val());
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
			console.log(jqXHR, status, err);
			$("#password").val("");
			showMessage("Der Account konnte nicht angelegt werden, versuchen Sie es mit einer anderen Email-Adresse.", true)
			toggleInputs(false);
		});
	});
	
	function toggleInputs (status) {
		$("#register-submit").prop("disabled", status);
		$("#name").prop("disabled", status);
		$("#surname").prop("disabled", status);
		$('#street').prop("disabled", status);
		$('#streetnumber').prop("disabled", status);
		$('#plz').prop("disabled", status);
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
			$("#street").val().trim() !== "" && 
			$("#streetnumber").val().trim() !== "" && 
			$("#plz").val().trim() !== "" && 
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