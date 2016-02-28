// TODO add documentation

KY = {
	// wrapper for performing AJAX-requests
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
	// checks if the given array of html-ids of inputs without leading '#' are not empty
	inputsAreNotEmpty: function (inputs) {
		// TODO refactor, so it works with or without leading '#'
		return inputs.filter(e => $('#' + e).val().trim() === "").length === 0;
	},
	MAIL: /[A-Za-z0-9\.\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}\~]+\@[A-Za-z0-9\_\-]+\.[A-Za-z]{2,3}/g
};