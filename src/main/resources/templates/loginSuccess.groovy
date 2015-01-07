layout 'layouts/main.tpl',
	pageTitle: 'Login',
	pageStylesheet: 'login',
	pageBody: contents {
		p {
			yield 'Login successful.'
		}
		p {
			a(href: '/', 'Continue')
		}
	}