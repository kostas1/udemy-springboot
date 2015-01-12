package templates

layout 'layouts/main.groovy',
	pageTitle: 'Login',
	extra: model,
	pageBody: contents {
		div(class: 'col-md-4') {
			form(action: '/login', method: 'post') {
				input(type: 'hidden', name: _csrf.parameterName, value: _csrf.token)

				div(class: 'form-group') {
					input(class: 'form-control', type: 'text', name: 'username', placeholder: 'Email', value: username)
				}
				div(class: 'form-group') {
					input(class: 'form-control', type: 'password', name: 'password', placeholder: 'Password', value: password)
				}
				input(class: 'btn btn-primary', type: 'submit', value: 'Login')
			}
		}
	}