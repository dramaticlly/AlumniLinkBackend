package hello.controller;

import hello.model.Friends;
import hello.repository.FriendsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@RepositoryRestController
public class CustomizeController {

    private final FriendsRepository repository;

    @Autowired
    public CustomizeController(FriendsRepository repo){
        repository = repo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friends/search/findByEmail")
    public @ResponseBody ResponseEntity<?> getEmais(@Param("email") String email){
        Friends friend = repository.findByEmail(email);
        log.info("findByEmail: args("+email + ") :" + friend.toString());
        //Resource<Friends> resource = new Resource<>(emails);
        return ResponseEntity.ok(friend);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friends/search/findByUniversity")
    public @ResponseBody ResponseEntity<?> getUniversities(@Param("university") String university){
        List<Friends> friends = repository.findByUniversity(university);
        friends.forEach( friend ->
            log.info("findByUniversity: args(" + university + ") :" + friend.toString()));
        return ResponseEntity.ok(friends);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friends/search/findByMajor")
    public @ResponseBody ResponseEntity<?> getMajor(@Param("major") String major){
        List<Friends> friends = repository.findByMajor(major);
        friends.forEach( friend ->
                log.info("findByMajor: args(" + major + ") :" + friend.toString()));
        return ResponseEntity.ok(friends);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friends/search/findByGraduationYear")
    public @ResponseBody ResponseEntity<?> getUniversities(@Param("year") int year){
        List<Friends> friends = repository.findByGraduationYear(year);
        friends.forEach( friend ->
                log.info("findByGraduationYear: args(" + year + ") :" + friend.toString()));
        return ResponseEntity.ok(friends);
    }

}
