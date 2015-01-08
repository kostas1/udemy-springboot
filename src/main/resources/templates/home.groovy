package templates

layout 'layouts/main.groovy',
		pageTitle: 'Home',
		authentication: authentication,
		info: info,
		warning: warning,
		pageBody: contents {
			a(href: '/posts/view', 'View posts')
		}