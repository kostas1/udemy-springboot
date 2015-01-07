layout 'layouts/main.tpl',
	pageTitle: 'Home page',
	pageStylesheet: 'home',
	authentication: authentication,
	pageBody: contents {
		a(href: '/posts/view', 'View posts')
}