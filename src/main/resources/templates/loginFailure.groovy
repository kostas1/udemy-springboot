package templates

layout 'layouts/main.groovy',
	pageTitle: 'Login',
	pageStylesheet: 'login',
	extra: model,
	pageBody: contents {
		p {
			yield 'Login failed. Try again'
		}
		p {
			a(href: '/login', 'Login')
		}
	}