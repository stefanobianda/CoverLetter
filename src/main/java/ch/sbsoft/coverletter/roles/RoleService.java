package ch.sbsoft.coverletter.roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findRoleByName(String roleName) {
		Role role = roleRepository.findByName(roleName);
		return role;
	}

	public List<Role> findAll() {
		List<Role> roleList = roleRepository.findAll();
		return roleList;
	}
	
	public Role getRoleCreateIfNotExist(String roleName) {
		Role role = roleRepository.findByName(roleName);
		if (role == null) {
			Role newRole = new Role();
			newRole.setName(roleName);
			role = roleRepository.save(newRole);
		}
		return role;
	}

}
