layout 'layouts/main.tpl',
	pageTitle: 'Login',
	pageStylesheet: 'login',
	pageBody: contents {
		p {
			yield 'Logged out successfully.'
		}
		p {
			a(href: '/', 'Continue')
		}
	}