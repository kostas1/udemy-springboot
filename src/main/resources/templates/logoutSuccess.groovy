package templates

layout 'layouts/main.groovy',
	pageTitle: 'Login',
	pageStylesheet: 'login',
	extra: model,
	pageBody: contents {
		p {
			yield 'Logged out successfully.'
		}
		p {
			a(href: '/', 'Continue')
		}
	}