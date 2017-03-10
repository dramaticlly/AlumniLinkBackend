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
    //TODO, optimize them into query, hard to parse these
    List<String> findByAcceptedFriendList(@Param("id") String id);
    List<String> findByPendingFriendList(@Param("id") String id);


}
