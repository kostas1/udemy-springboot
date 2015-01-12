package templates

layout 'layouts/main.groovy',
	pageTitle: 'Home',
	extra: model,
	pageBody: contents {
		p {
			yield 'Welcome to Udemy Spring course application!'
		}
	}