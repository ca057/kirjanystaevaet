/*
 * This basic script is a wrapper for some often used functions throughout the 
 * client-side application. It helps to simplify the main scripts which handles
 * e.g. the registration of new users.
 */

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
		
		const sendParam = function (type, param) {
			return $.ajax({
				url: (url.charAt(url.length -1) !== "?" ? url + "?" : url) + param,
				type: type,
				beforeSend: function (xhr) {
					xhr.setRequestHeader("X-CSRF-TOKEN", $('[name=_csrf]').val());
				}
			});
		};
		
		return {
			POST: (data) => ajax('POST', data),
			GET: () => ajax('GET'),
			DELETE: (data) => ajax('DELETE', data),
			GET_PARAM: (param) => sendParam('GET', param),
			POST_PARAM: (param) => sendParam('POST', param)
		};
	},
	// checks if the given array of html-ids of inputs without leading '#' are not empty
	inputsAreNotEmpty: function (inputs) {
		return inputs.filter(e => $('#' + e).val().trim() === "").length === 0;
	},
	// simple regular expression used for validating emails
	MAIL: /[A-Za-z0-9\.\!\#\$\%\&\'\*\+\-\/\=\?\^\_\`\{\|\}\~]+\@[A-Za-z0-9\_\-]+\.[A-Za-z]{2,3}/g
};