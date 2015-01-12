package templates.posts

import springboot.helpers.TemplateHelper

layout 'layouts/main.groovy',
	pageTitle: 'title',
	pageStylesheet: 'posts/main',
	extra: model,
	pageBody: contents {
		p {
			'List of posts'
		}
		if (TemplateHelper.hasAuthority("EDIT_POSTS")) {
			a(class: 'btn btn-primary', href: 'create', 'Create new')
		}

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
						if (TemplateHelper.hasAuthority("EDIT_POSTS")) {
							a(class: 'btn btn-primary', href: "/posts/edit?id=$post.id", 'Edit')
							a(class: 'btn btn-primary', href: "/posts/delete?id=$post.id", 'Delete')
						}
					}
				}
			}
		}
	}