package templates

layout 'layouts/main.groovy',
	pageTitle: 'Login',
	pageStylesheet: 'login',
	info: info,
	warning: warning,
	pageBody: contents {
		p {
			yield 'Logged out successfully.'
		}
		p {
			a(href: '/', 'Continue')
		}
	}