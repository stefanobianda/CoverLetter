package ch.sbsoft.coverletter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ch.sbsoft.coverletter.roles.Role;
import ch.sbsoft.coverletter.roles.RoleService;

@SpringBootTest
class RolesTests {

	@Autowired
	private RoleService roleService;

	
	@Test
	void testValidObjects() {
		assertNotNull(roleService);

	}

	@Test
	void testFindRoleByName() {
		Role role = roleService.findRoleByName("ROLE_USER");
		assert(role.getName().equals("ROLE_USER"));

	}

	@Test
	void testFindRoleByNameNotFound() {
		Role role = roleService.findRoleByName("ROLE_DOES_NOT_EXIST");
		assertNull(role);
	}

	@Test
	void testFindAllRoles() {
		List<Role> roleList = roleService.findAll();
		assert(roleList.size() >= 2);
		assert(roleList.size() <= 3);
	}

	@Test
	void testRoleCreateIfNotExist() {
		int size = roleService.findAll().size();
		Role role = roleService.getRoleCreateIfNotExist("ROLE_USER");
		assertNotNull(role);
		assert(role.getName().equals("ROLE_USER"));
		assertEquals(size, roleService.findAll().size());
	}
	
	@Test
	void testRoleCreateIfNotExistNew() {
		int size = roleService.findAll().size();
		Role role = roleService.getRoleCreateIfNotExist("ROLE_NEW");
		assertNotNull(role);
		assert(role.getName().equals("ROLE_NEW"));
		assertEquals(size + 1, roleService.findAll().size());
	}
	
}
