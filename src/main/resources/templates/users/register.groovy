package templates.posts

layout 'layouts/main.groovy',
    pageTitle: 'Register',
    pageStylesheet: 'users/register',
    info: info,
    warning: warning,
    pageBody: contents {
        div(class: 'col-md-4') {
            form(action: '/users/register', method: 'post') {
                div(class: 'form-group') {
                    input(class: 'form-control', type: 'text', name: 'username', placeholder: 'Email', value: username)
                }
                div(class: 'form-group') {
                    input(class: 'form-control', type: 'password', name: 'password', placeholder: 'Password', value: password)
                }
                input(class: 'btn btn-primary', type: 'submit', value: 'Register')
            }
        }
    }