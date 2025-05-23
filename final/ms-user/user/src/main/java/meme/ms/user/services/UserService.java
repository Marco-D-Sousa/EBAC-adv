package meme.ms.user.services;


import meme.ms.user.models.UserModel;
import meme.ms.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public UserModel save(UserModel userModel) {

		UserModel newUser = null;
		try {
			newUser = userRepository.save(userModel);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return newUser;
	}
}
