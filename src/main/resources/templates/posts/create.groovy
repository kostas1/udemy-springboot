package templates.posts

layout 'layouts/main.groovy',
	pageTitle: 'Create post',
	pageStylesheet: 'posts/create',
	extra: model,
	pageBody: contents {
		div(class: 'col-md-4') {
			form(action: '/posts/create', method: 'post') {
				div(class: 'form-group') {
					input(class: 'form-control', type: 'text', name: 'title', placeholder: 'Title')
				}
				div(class: 'form-group') {
					input(class: 'form-control', type: 'content', name: 'content', placeholder: 'Content')
				}
				input(class: 'btn btn-primary', type: 'submit', value: 'Create')
			}
		}
	}