package templates

layout 'layouts/main.groovy',
	pageTitle: 'Login',
	pageStylesheet: 'login',
	info: info,
	warning: warning,
	pageBody: contents {
		p {
			yield 'Login failed. Try again'
		}
		p {
			a(href: '/login', 'Login')
		}
	}