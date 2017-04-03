package hello.repository;

import hello.model.Friends;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "friends", path = "friends")
public interface FriendsRepository extends MongoRepository<Friends, String>{

    Friends findByFirstName(@Param("name") String firstName);
    Friends findByEmail(@Param("email") String email);

    List<Friends> findByUniversity(@Param("university") String university);
    List<Friends> findByUniversityIgnoreCase(@Param("university") String university);
    List<Friends> findByMajor(@Param("major") String major);
    List<Friends> findByMajorIgnoreCase(@Param("major") String major);
    List<Friends> findByGraduationYear(@Param("year") int year);
}
