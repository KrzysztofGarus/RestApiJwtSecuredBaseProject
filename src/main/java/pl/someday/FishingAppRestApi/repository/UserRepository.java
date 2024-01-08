package pl.someday.FishingAppRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.someday.FishingAppRestApi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
