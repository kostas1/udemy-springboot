package templates.layouts

import springboot.helpers.TemplateHelper

yieldUnescaped '<!DOCTYPE html>'
html(lang: 'en') {
	head {
		title(pageTitle)

		meta(charset: 'utf-8')
		meta('http-equiv': 'X-UA-Compatible', content: 'IE=edge')
		meta(name: 'viewport', content: 'width=device-width, initial-scale=1')
		meta(name: 'description', content: '')
		meta(name: 'author', content: '')


		link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
		link(rel: 'stylesheet', href: '/css/simple-sidebar.css')

		comment 'HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries'
		comment 'WARNING: Respond.js doesnt work if you view the page via file:'
		yieldUnescaped '<!--[if lt IE 9]>'
		script(src: 'https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js')
		script(src: 'https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js')
		yieldUnescaped '<![endif]-->'

		script(src: '/js/jquery.js')
		script(src: '/js/bootstrap.min.js')

		script {
			yieldUnescaped """
				\$("#menu-toggle").click(function(e) {
				e.preventDefault();
				\$("#wrapper").toggleClass("toggled");
				});
			"""
		}

		if (pageStylesheet)
			link(rel: 'stylesheet', href: "/css/${pageStylesheet}.css")
	}
	body {
		div(id: 'wrapper') {

			div(id: 'sidebar-wrapper') {
				ul(class: 'sidebar-nav') {
					li(class: 'sidebar-brand') {
						a(href: '/', 'Udemy Spring')
					}
					if (extra.authentication != null && extra.authentication.getName() != 'anonymousUser') {
						li {
							form(id: 'logoutForm', action: '/logout', method: 'post') {
								input(type: 'hidden', name: extra._csrf.parameterName, value: extra._csrf.token)
								a(href: '#', onclick: 'document.getElementById("logoutForm").submit();', "Logout, ${extra.authentication.getName()}")
							}
						}
					} else {
						li(class: 'active') {
							a(href: '/login', 'Login')
						}
						li {
							a(href: '/users/register', 'Register')
						}
						li {
							form(id: 'authFacebook', action: '/auth/facebook', method: 'post') {

								input(type: 'hidden', name: extra._csrf.parameterName, value: extra._csrf.token)
								input(type: 'hidden', name: 'scope', value: 'email')
								a(href: '#', onclick: 'document.getElementById("authFacebook").submit();', 'Connect with Facebook')
							}
						}
					}
					li {
						if (TemplateHelper.hasAuthority("VIEW_POSTS")) {
							a(href: '/posts/view', 'View posts')
						}
					}
				}
			}
			div(id: 'page-content-wrapper') {
				div(class: 'container-fluid') {
					div(class: 'row') {
						div(class: 'col-lg-12') {
							if (extra.info != null) {
								div(class: 'alert alert-info', role: 'alert') {
									yield extra.info
								}
							} else if (extra.warning != null) {
								div(class: 'alert alert-warning', role: 'alert') {
									yield extra.warning
								}
							}
						}
					}
					div(class: 'row') {
						div(class: 'col-lg-12') {
							pageBody()
						}
					}
				}
			}
		}
	}
}