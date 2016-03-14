/*
 * Provides function which supports (in its current version) a function to deal with the registration.
 */
const handle = function () {
 	const registration = function () {
 		console.log("Registration is active.")
		const inputs = ["name", "surname", "street", "streetnumber", "plz", "email", "password"];
		$('#plz').on('input', function(e) {
			if ($(this).val().length < 5) {
				$('#plz-info-wrapper').slideUp();
				$('#plz-selection').empty();
				return;
			}
			console.log("Request all PLZs from the server.");
			KY.request('/kirjanystaevaet/registrierung/plz')
				.GET_PARAM("code=" + $(this).val()).done(data => {
					console.log("Server send data: " + data.toString());
					$('#plz-info').text("Wählen Sie den korrekten Ort.").css("color", "#727272").show();
					data.forEach(e => {
						$('#plz-selection').append("<span><input type='radio' name='plz' value='" + e.plzId + "'>" + e.postcode + ": " + e.place + "</span>");
					});					
					$('#plz-info-wrapper').slideDown();
				}).fail((jqXHR, status, err) => {
					console.log("An error occured with status [" + status + "] and error [" + err + "].");
					$('#plz-info').text("Die PLZ scheint nicht korrekt zu sein.").css("color", "red").show();
					$('#plz-info-wrapper').slideDown();
				});
		});

		$("#register-submit").on("click", function (e) {
			if (!KY.inputsAreNotEmpty(inputs) && $("#password").val().trim().length < 6 && !KY.MAIL.test($("#email").val().trim())) {
				console.error('Something with the inputs of adding a user is wrong...');
				return;
			}
			e.preventDefault();
			const data = {};
			inputs.forEach(e => {
				$('#' + e).prop('disabled', true);
				data[e] = $( "[name="+ e +"]").val();
			});
			showMessage("Ihre Eingabe wird verarbeitet.", false);
			$("#register-submit").prop('disabled', true);
			KY.request('/kirjanystaevaet/registrierung')
				.POST(data).done(function (data, status, jqXHR) {
					console.log("Registration of user was successful.");
					inputs.forEach(e => {
						$('#' + e).prop('disabled', false).val('');
					});
					$('#plz-info-wrapper').hide();
					window.location.replace("/kirjanystaevaet/meinkonto");
				}).fail(function (jqXHR, status, err) {
					console.error("The registration was not successful. Try again with a different email.")
					$("#password").val("");
					showMessage("Der Account konnte nicht angelegt werden, versuchen Sie es mit einer anderen Email-Adresse.", true)
					inputs.forEach(e => {
						$('#' + e).prop('disabled', false);
					});
					$("#register-submit").prop('disabled', false);
				});
		});
 	};

 	var showMessage = function (text, error) {
		$("#info-message").show().text(text);
		if (error) {
			$("#info-message").css("color", "red");
		} else {
			$("#info-message").css("color", "#727272");
		}
	}

 	return {
 		registration: () => registration(),
 	};
 };

$(document).ready(function () {
	if (window.location.pathname.indexOf("/registrierung") != -1) {
		handle().registration();
	}
	
	window.onload = function() {
		var elevator = new Elevator({
			element: document.querySelector('.to-top'),
			mainAudio: 'http://tholman.com/elevator.js/music/elevator.mp3',
			endAudio: 'http://tholman.com/elevator.js/music/ding.mp3'
		});
	}
	
});
