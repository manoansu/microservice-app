package pt.amane.hruser.reositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.amane.hruser.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
