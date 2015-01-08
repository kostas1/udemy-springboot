package templates.posts

layout 'layouts/main.groovy',
pageTitle: 'Create post',
pageStylesheet: 'posts/edit',
info: info,
warning: warning,
pageBody: contents {
	div(class: 'col-md-4') {
		form(action: '/posts/edit', method: 'post') {
			input(type: 'hidden', name: 'id', value: post.id)

			div(class: 'form-group') {
				input(class: 'form-control', type: 'text', name: 'title', placeholder: 'Title', value: post.title)
			}
			div(class: 'form-group') {
				input(class: 'form-control', type: 'content', name: 'content', placeholder: 'Content', value: post.content)
			}
			input(class: 'btn btn-primary', type: 'submit', value: 'Create')
		}
	}
}