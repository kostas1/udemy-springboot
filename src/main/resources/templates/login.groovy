package templates

layout 'layouts/main.groovy',
	pageTitle: 'Login',
	info: info,
	warning: warning,
	pageBody: contents {
		div(class: 'col-md-4') {
			form(action: '/login', method: 'post') {
				div(class: 'form-group') {
					input(class: 'form-control', type: 'text', name: 'username', placeholder: 'Username', value: username)
				}
				div(class: 'form-group') {
					input(class: 'form-control', type: 'password', name: 'password', placeholder: 'Password', value: password)
				}
				input(class: 'btn btn-primary', type: 'submit', value: 'Login')
			}
		}
	}