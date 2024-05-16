package ch.sbsoft.coverletter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.sbsoft.coverletter.roles.Role;
import ch.sbsoft.coverletter.roles.RoleRepository;
import ch.sbsoft.coverletter.roles.RoleService;

@ExtendWith(MockitoExtension.class)
class RolesTests {

	@Mock
	private RoleRepository roleRepositoryMock;

	@InjectMocks
	private RoleService roleService;

	
	@Test
	void testValidObjects() {
		assertNotNull(roleService);
		assertNotNull(roleRepositoryMock);

	}

	@Test
	void testFindRoleByName() {
		when(roleRepositoryMock.findByName("ROLE_USER")).thenReturn(new Role(1l,"ROLE_USER",null));
		Role role = roleService.findRoleByName("ROLE_USER");
		assert(role.getName().equals("ROLE_USER"));

	}

	@Test
	void testFindRoleByNameNotFound() {
		when(roleRepositoryMock.findByName("ROLE_DOES_NOT_EXIST")).thenReturn(null);
		Role role = roleService.findRoleByName("ROLE_DOES_NOT_EXIST");
		assertNull(role);
	}

	@Test
	void testFindAllRoles() {
		when(roleRepositoryMock.findAll()).thenReturn(null);
		List<Role> roleList = roleService.findAll();
		assert(roleList.size() >= 2);
	}

	
}
