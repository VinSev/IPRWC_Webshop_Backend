package nl.hsleiden.IPRWC_Webshop_Backend.dao.repository;

import nl.hsleiden.IPRWC_Webshop_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
