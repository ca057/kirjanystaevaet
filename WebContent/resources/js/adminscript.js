console.log('¯\\_(ツ)_/¯');
console.info(2);

const request = function(url) {
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
}


const handle = function() {
	const userManagement = function () {
		// implement the user management here
		console.info("USERS ARE MANAGED");
		const inputs = ["name", "surname", "street", "streetnumber", "plz", "email", "role"];
		
		$("#add-user-submit").on('click', (e) => {
			if (!allInputsAreValid()) {
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
			request('/kirjanystaevaet/backend/nutzerinnen/add')
				.POST(data).done(() => {
					console.info('USER IS REGISTERED');
					inputs.forEach(e => {
						const id = '#' + e;
						$(id).prop('disabled', false).val('');
					});
				})
				.fail((jqXHR, status, err) => {
					// TODO show error message
					alert('Nutzer:in konnte nicht angelegt werden.');
					inputs.forEach(e => {
						const id = '#' + e;
						$(id).prop('disabled', false);
					});
				});
		});
		
		// TODO make this function more abstract
		const allInputsAreValid = function() {
			var regex = /[A-Za-z0-9\.\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}\~]+\@[A-Za-z0-9\_\-]+\.[A-Za-z]{2,3}/g;
			return $("#name").val().trim() !== "" && 
				$("#surname").val().trim() !== "" && 
				$("#street").val().trim() !== "" && 
				$("#streetnumber").val().trim() !== "" && 
				$("#plz").val().trim() !== "" && 
				$("#email").val().trim() !== "" && 
				regex.test($("#email").val().trim());
		}
	};
	
	return {
		userManagement: () => userManagement(),
	};
};

$(document).ready(function () {
	handle().userManagement();
});
