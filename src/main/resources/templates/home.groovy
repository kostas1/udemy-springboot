package templates

layout 'layouts/main.groovy',
	pageTitle: 'Home',
	authentication: authentication,
	info: info,
	warning: warning,
	pageBody: contents {
		p {
			yield 'Welcome to Udemy Spring course application!'
		}
	}