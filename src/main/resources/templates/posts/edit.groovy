layout 'layouts/main.tpl',
pageTitle: 'Create post',
pageStylesheet: 'posts/edit',
pageBody: contents {
	form(action: '/posts/edit', method: 'post') {
		input(type: 'hidden', name: 'id', value: post.id)
		label(for: 'title', 'title')
		input(type: 'text', name: 'title', value: post.title)
		label(for: 'content', 'content')
		input(type: 'text', name: 'content', value: post.content)
		input(type: 'submit', value: 'submit')
	}
}