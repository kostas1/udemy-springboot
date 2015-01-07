layout 'layouts/main.tpl',
	pageTitle: 'Login',
	pageStylesheet: 'login',
	pageBody: contents {
		p {
			yield 'Login failed. Try again'
		}
		p {
			a(href: '/login', 'Login')
		}
	}