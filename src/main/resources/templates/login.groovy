layout 'layouts/main.tpl',
pageTitle: 'Login',
pageBody: contents {
	p {
		'Login please'
	}
	form(method: 'post', action: 'login') {
		input(type: 'text', name: 'username', placeholder: 'Username')
		input(type: 'text', name: 'password', placeholder: 'Password')
		input(type: 'submit', value: 'Login')
	}
}