layout 'layouts/main.tpl',
	pageTitle: 'title',
	pageStylesheet: 'posts/main',
	authentication: authentication,
	pageBody: contents {
		p {
			'List of posts'
		}
		a(href: 'create', 'Create new')
		ul {
			posts.each { post ->
				div {
					p {
						yield "Id: $post.id, Title: $post.title, Content: $post.content"
					}
					a(href: "/posts/edit?id=$post.id", 'Edit')
					newLine()
					a(href: "/posts/delete?id=$post.id", 'Delete')
				}
			}
		}
	}