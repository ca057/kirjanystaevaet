console.log('¯\\_(ツ)_/¯');

var KY = {
	request: function(url) {
		const ajax = function (type, data) {
			return $.ajax({
				url: url,
				type: type,
				data: JSON.stringify(data),
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
			});
		};
		
		return {
			POST: (data) => ajax('POST', data),
			GET: () => ajax('GET'),
			DELETE: (data) => ajax('DELETE', data),
		};
	},
	inputsAreValid: function (inputs) {
		// TODO refactor, so it works with or without leading '#'
		return inputs.filter(e => $('#' + e).val().trim() === "").length === 0;
	}
};

const MAIL = /[A-Za-z0-9\.\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}\~]+\@[A-Za-z0-9\_\-]+\.[A-Za-z]{2,3}/g;
const handle = function() {
	const userManagement = function () {
		console.info("USERS ARE MANAGED");
		const inputs = ["name", "surname", "street", "streetnumber", "plz", "email", "role"];
		$("#add-user-submit").on('click', (e) => {
			if (!KY.inputsAreValid(inputs) && !MAIL.test($("#email").val().trim())) {
				console.err('Something with the inputs is wrong...');
				return;
			}
			e.preventDefault();
			console.log('I WILL REGISTER THAT LOVELY HUMAN FOR YA.')
			const data = {};

			inputs.forEach((e) => {
				const id = '#' + e;
				$(id).prop('disabled', true);
				data[e] = $(id).val();
			});
			$("#add-user-submit").prop('disabled', true);
			KY.request('/kirjanystaevaet/backend/nutzerinnen/add')
				.POST(data).done(() => {
					console.info('USER IS REGISTERED');
					inputs.forEach(e => {
						const id = '#' + e;
						$(id).prop('disabled', false).val('');
					});
					$("#add-user-submit").prop('disabled', false);
				})
				.fail((jqXHR, status, err) => {
					// TODO show error message
					alert('Nutzer:in konnte nicht angelegt werden.');
					inputs.forEach(e => {
						const id = '#' + e;
						$(id).prop('disabled', false);
						$("#add-user-submit").prop('disabled', false);
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
