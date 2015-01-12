package templates

layout 'layouts/main.groovy',
	pageTitle: 'Login',
	pageStylesheet: 'login',
	extra: model,
	pageBody: contents {
		p {
			yield 'Login successful.'
		}
		p {
			a(href: '/', 'Continue')
		}
	}