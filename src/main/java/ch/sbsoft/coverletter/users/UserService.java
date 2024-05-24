package ch.sbsoft.coverletter.users;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.sbsoft.coverletter.roles.Role;
import ch.sbsoft.coverletter.roles.RoleService;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleService roleService;

	public User findUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return user.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findUserByUsername(username);
	}

	public User addUser(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword())); 
		Role role = roleService.getRoleCreateIfNotExist(Role.ROLE_USER);
		user.setRoles(Set.of(role));
		return userRepository.save(user);
	}


	public User update(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword())); 
		return userRepository.save(user);
	}

}
