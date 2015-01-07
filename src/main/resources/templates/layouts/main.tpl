yieldUnescaped '<!DOCTYPE html>'
html {
	head {
		title(pageTitle)
		if (pageStylesheet)
			link(rel: 'stylesheet', href: "/css/${pageStylesheet}.css")
	}
	body {
		div(class: 'menu') {
			if (authentication != null && authentication.getName() != 'anonymousUser') {
				a(href: '/logout', "Logout, ${authentication.getName()}")
			}
		}
		div(class: 'container') {
			div {
				p {
					'Sample page for Udemy Spring Boot course'
				}
			}
			div(class: 'content') {
				pageBody()
			}
		}
	}
}