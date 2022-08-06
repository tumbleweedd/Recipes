package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import recipes.model.User;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.email = ?1")
    User findUserByEmail(String email);
    Optional<User> findUserEntityByEmailIgnoreCase(String email);


}
