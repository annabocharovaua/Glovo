//package delivery.glovo.repository;
//
//import delivery.glovo.model.Role;
//import delivery.glovo.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testFindByEmail() {
//        // Given
//        String email = "anna@gmail.com";
//        User user = User.builder().name("ann bocharova").email(email).
//                password("sfznjn").roles(List.of(Role.builder().name("admin").build())).build();
//        userRepository.save(user);
//               // When
//        User foundUser = userRepository.findByEmail(email);
//
//        Optional<User> savedUser = userRepository.findById(user.getId());
//        assertTrue(savedUser.isPresent());
//
//        // Then
//        assertNotNull(foundUser);
//        assertEquals(email, foundUser.getEmail());
//        // Add more assertions as needed based on your data model and expectations
//    }
////}