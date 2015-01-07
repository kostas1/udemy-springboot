package springboot.services;

import springboot.data.entities.User;

public interface MailService {
	
    void sendRegistrationConfirmation(User user);

    void sendPasswordResetUrl(User user);
}
