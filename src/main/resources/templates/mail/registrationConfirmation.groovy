package templates.mail

yield """
hey, $username

Please confirm your identity by clicking the following url:

http://localhost:8080/users/confirmEmail/${username}/${token}

Thanks

"""