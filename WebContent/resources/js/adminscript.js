console.log('¯\\_(ツ)_/¯');

const manage = function() {
	const userManagement = function () {
		console.info("USERS ARE MANAGED");
		const addInputs = ["name", "surname", "street", "streetnumber", "plz", "email", "role"];
		const editInputs = ["edit-id", "edit-name", "edit-surname", "edit-street", "edit-streetnumber",
			"edit-plz", "edit-email", "edit-role", "edit-password"];
		// two input handlers for verifying a correct URL. Both handlers do more or less the same, but minor changes exist. Refactoring should be done
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
		
		$('#edit-plz').on('input', function(e) {
			if ($(this).val().length < 5) {
				$('#edit-plz-info-wrapper').slideUp();
				$('#edit-plz-selection').empty();
				return;
			}
			console.log("Request all PLZs from the server.");
			KY.request('/kirjanystaevaet/registrierung/plz')
				.GET_PARAM("code=" + $(this).val()).done(data => {
					console.log("Server send data: " + data.toString());
					$('#edit-plz-info').text("Wählen Sie den korrekten Ort.").css("color", "#727272").show();
					data.forEach(e => {
						$('#edit-plz-selection').append("<span><input type='radio' name='plz' value='" + e.plzId + "'>" + e.postcode + ": " + e.place + "</span>");
					});					
					$('#edit-plz-info-wrapper').slideDown();
				}).fail((jqXHR, status, err) => {
					console.log("An error occured with status [" + status + "] and error [" + err + "].");
					$('#edit-plz-info').text("Die PLZ scheint nicht korrekt zu sein.").css("color", "red").show();
					$('#edit-plz-info-wrapper').slideDown();
				});
		});
		
		// handles adding a user		
		$("#add-user-submit").on('click', (e) => {
			if (!KY.inputsAreNotEmpty(addInputs) && !KY.MAIL.test($("#email").val().trim())) {
				console.error('Something with the inputs of adding a user is wrong...');
				return;
			}
			e.preventDefault();
			console.log('I WILL REGISTER THAT LOVELY HUMAN FOR YA.')
			const data = {};

			addInputs.forEach((e) => {
				$('#' + e).prop('disabled', true);
				data[e] = $( "[name="+ e +"]").val();
			});
			$("#add-user-submit").prop('disabled', true);
			KY.request('/kirjanystaevaet/backend/nutzerinnen/add')
				.POST(data).done(() => {
					console.info('USER IS REGISTERED');
					addInputs.forEach(e => {
						$('#' + e).prop('disabled', false).val('');
					});
					$("#add-user-submit").prop('disabled', false);
				})
				.fail((jqXHR, status, err) => {
					// TODO show error message
					alert('Nutzer:in konnte nicht angelegt werden.');
					addInputs.forEach(e => {
						const id = '#' + e;
						$('#' + e).prop('disabled', false);
						$("#add-user-submit").prop('disabled', false);
					});
				});
		});
		// handles editing a user
		$('#edit-user-submit').on('click', (e) => {
			// TODO check password
			e.preventDefault();
			const data = {};

			editInputs.forEach((e) => {
				$('#' + e).prop('disabled', true);
				data[e.substring(5)] = $("[name="+ e +"]").val();
			});
			$("#edit-user-submit").prop('disabled', true);
			KY.request('/kirjanystaevaet/backend/nutzerinnen/edit')
				.POST(data).done(() => {
					console.info('DATA OF USER IS EDITED');
					editInputs.forEach(e => {
						$('#' + e).prop('disabled', false).val('');
					});
					$("#edit-user-submit").prop('disabled', false);
				})
				.fail((jqXHR, status, err) => {
					alert('Daten der:des Nutzers:in konnten nicht geändert werden.');
					editInputs.forEach(e => {
						$('#' + e).prop('disabled', false);
						$("#edit-user-submit").prop('disabled', false);
					});
				});
		});
	};
	
	const stockManagement = function () {
		console.info('STOCK IS MANAGED');
		$('#autorinnen-anlegen-submit').on('click', (e) => {
			e.preventDefault();
			const data = {
				nameF: $('#autorinnen-anlegen-first').val().trim(),
				nameL: $('#autorinnen-anlegen-last').val().trim(),
				newAuthor: false
			};

			$('#autorinnen-anlegen-first').prop('disabled', true);
			$('#autorinnen-anlegen-last').prop('disabled', true);
			$('#autorinnen-anlegen-submit').prop('disabled', true);
			
			KY.request('/kirjanystaevaet/backend/bestand/autorinnen/add').POST(data).done(() => {
					console.log("AUTHOR IS CREATED.");
					$('#autorinnen-anlegen-first').val('').prop('disabled', false);
					$('#autorinnen-anlegen-last').val('').prop('disabled', false);
					$('#autorinnen-anlegen-submit').prop('disabled', false);
				}).fail((jqXHR, status, err) => {
					console.log(jqXHR.status);
					console.log(jqXHR.status === 409);
					if (jqXHR.status === 409) {
						if (confirm("Die Datenbank enthält möglicherweise schon eine:n " +
								"Autor:in mit diesem Namen. Datensatz trotzdem einfügen? \n" +
								"Vorname: " + data.nameF +"\n" +
								"Nachme: " + data.nameL)) {
							data.newAuthor = true;
							KY.request('/kirjanystaevaet/backend/bestand/autorinnen/add').POST(data).done(() => {
								console.log("AUTHOR IS CREATED.")
							}).fail(() => {
								alert("Ein:e Autor:in mit diesen Daten konnte nicht angelegt werden.")
							});
						}
					} else {
						alert("Ein unbekannter Fehler ist aufgetreten. Versuchen Sie das Anlegen des Datensatzes erneut.");
					}
					$('#autorinnen-anlegen-first').val('').prop('disabled', false);
					$('#autorinnen-anlegen-last').val('').prop('disabled', false);
					$('#autorinnen-anlegen-submit').prop('disabled', false);
				});
		});
		$('#buecher-aendern-isbn').on('change', () => {
			const inputs = ['categories', 'title', 'authors', 'description', 'price', 'publisher', 'day', 'month', 'year', 'edition', 'pages'];
			const isbn = $('#buecher-aendern-isbn').val().trim();
			$('#buecher-aendern-data-loading').show();
			inputs.forEach(e => {
				$('#buecher-aendern-' + e).prop('disabled', true);
			});
			
			if (isbn && isbn !== '') {
				KY.request('/kirjanystaevaet/backend/bestand/buecher/data').GET_PARAM('isbn=' + isbn).done(data => {
					inputs.forEach(e => {
						$('#buecher-aendern-' + e).prop('disabled', false).val(data[e]);
					});
					$('#buecher-aendern-data-loading').hide();
				}).fail((j, s, e) => {
					alert('Daten konnten nicht geladen werden:\n' + 'Status: ' + status + '; Fehler: ' + e);
					$('#buecher-aendern-data-loading').hide();
					inputs.forEach(e => {
						$('#buecher-aendern-' + e).prop('disabled', false);
					});
				});
			}
		});
	};
		
	return {
		users: () => userManagement(),
		stock: () => stockManagement()
	};
};

const frontend = function () {
	return {
		bootstrap: () => {
			const showTab = name => $('a[href="#' + name + '"]').tab('show');
			const getName = hash => hash.split('#')[1].split('-')[0];
			
			if (window.location.hash) {
				showTab(getName(window.location.hash));
			}
			window.addEventListener('hashchange', () => {
				if (window.location.hash) {
					showTab(getName(window.location.hash)); 				
				}
		 	});
		}
	};
};

$(document).ready(function () {
	manage().users();
	manage().stock();
	frontend().bootstrap();
});
