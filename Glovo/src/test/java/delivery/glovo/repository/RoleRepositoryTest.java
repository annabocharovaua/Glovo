/*package delivery.glovo.repository;

import static org.junit.jupiter.api.Assertions.*;

import delivery.glovo.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindByName() {
        // Given
        String roleName = "ROLE_ADMIN";
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);

        // When
        Role foundRole = roleRepository.findByName(roleName);

        // Then
        assertNotNull(foundRole);
        assertEquals("ROLE_ADMIN", foundRole.getName());
        // Add more assertions as needed based on your data model and expectations
    }
}*/