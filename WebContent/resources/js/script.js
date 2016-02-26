/**
 * Script for client-side-logic for handling user input, input validation etc. 
 */

function handleRegistration () {
	var userData = {};
	$("#register-submit").on("click", function (e) {
		if (!allInputsAreValid()) {
			// TODO validate plz
			return;
		}
		e.preventDefault();
		clearOrDisableInputs("disable", true);
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
		.done(function (data, status, jqXHR) {
			clearOrDisableInputs("clear");
			window.location.replace("/kirjanystaevaet/login");
		})
		.fail(function (jqXHR, status, err) {
			$("#password").val("");
			showMessage("Der Account konnte nicht angelegt werden, versuchen Sie es mit einer anderen Email-Adresse.", true)
			clearOrDisableInputs("disable", false);
		});
	});
	
	function clearOrDisableInputs (task, disable) {
		var inputs = ["#name", "#surname", "#street", "#streetnumber", "#plz", "#email", "#password"];
		if (task === "clear") {
			for (var i = 0; i < inputs.length; i++) {
				$(inputs[i]).val("");
			}
		} else if (task === "disable") {
			$("#register-submit").prop("disabled", disable);
			for (var i = 0; i < inputs.length; i++) {
				$(inputs[i]).prop("disabled", disable);
			}
		}
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