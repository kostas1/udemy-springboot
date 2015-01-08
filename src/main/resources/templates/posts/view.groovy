package templates.posts

layout 'layouts/main.groovy',
	pageTitle: 'title',
	pageStylesheet: 'posts/main',
	authentication: authentication,
	info: info,
	warning: warning,
	pageBody: contents {
		p {
			'List of posts'
		}
		a(class: 'btn btn-primary', href: 'create', 'Create new')

		table(class: 'table table-striped') {
			tr {
				td {
					yield 'Id'
				}
				td {
					yield 'Title'
				}
				td {
					yield 'Content'
				}
				td {
					yield 'Actions'
				}
			}
			posts.each { post ->
				tr {
					td {
						yield post.id
					}
					td {
						yield post.title
					}
					td {
						yield post.content
					}
					td {
						a(class: 'btn btn-primary', href: "/posts/edit?id=$post.id", 'Edit')
						a(class: 'btn btn-primary', href: "/posts/delete?id=$post.id", 'Delete')
					}
				}
			}
		}
	}