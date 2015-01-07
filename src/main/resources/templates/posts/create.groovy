layout 'layouts/main.tpl',
	pageTitle: 'Create post',
	pageStylesheet: 'posts/create',
	pageBody: contents {
		form(action: '/posts/create', method: 'post') {
			label(for: 'title', 'title')
			input(type: 'text', name: 'title')
			label(for: 'content', 'content')
			input(type: 'text', name: 'content')
			input(type: 'submit', value: 'submit')
		}
	}