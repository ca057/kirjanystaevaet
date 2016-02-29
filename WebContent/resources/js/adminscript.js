console.log('¯\\_(ツ)_/¯');

const handle = function() {
	const userManagement = function () {
		console.info("USERS ARE MANAGED");
		const addInputs = ["name", "surname", "street", "streetnumber", "plz", "email", "role"];
		const editInputs = ["edit-id", "edit-name", "edit-surname", "edit-street", "edit-streetnumber",
			"edit-plz", "edit-email", "edit-role", "edit-password"];
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
				data[e] = $('#' + e).val();
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
			e.preventDefault();
			const data = {};

			editInputs.forEach((e) => {
				$('#' + e).prop('disabled', true);
				data[e.substring(5)] = $('#' + e).val();
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
		// TODO implement more here
	};
		
	return {
		userManagement: () => userManagement(),
		stockManagement: () => stockManagement()
	};
};

$(document).ready(function () {
	handle().userManagement();
	handle().stockManagement();
});
