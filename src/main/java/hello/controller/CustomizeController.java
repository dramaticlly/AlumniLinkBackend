package hello.controller;

import hello.controller.assembler.FriendsResourceAssembler;
import hello.controller.resource.FriendsResource;
import hello.model.Friends;
import hello.repository.FriendsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RepositoryRestController
public class CustomizeController {

    private final FriendsRepository repository;
    private final FriendsResourceAssembler assembler = new FriendsResourceAssembler();

    @Autowired
    public CustomizeController(FriendsRepository repo){
        repository = repo;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/friends/befriend")
    public @ResponseBody
    ResponseEntity beFriends(@RequestParam("uid") String id_a, @RequestParam("friend_uid") String id_b){
        Friends one = repository.findOne(id_a),
                other = repository.findOne(id_b);
        if (one == null || other == null){
            log.error("Befriend hasing empty uid, uid_a = "+ id_a + " uid_b: " + id_b);
            return ResponseEntity.badRequest().build();
        }
        List<String> oneAccepted = one.getAcceptedFriendList() == null ? new ArrayList<>() : one.getAcceptedFriendList();
        List<String> onePending = one.getPendingFriendList() == null ? new ArrayList<>() : one.getPendingFriendList();
        List<String> otherAccepted = other.getAcceptedFriendList() == null ? new ArrayList<>() : other.getAcceptedFriendList();
        List<String> otherPending = other.getPendingFriendList() == null ? new ArrayList<>() : other.getPendingFriendList();

        // adding to both acceptedFriendList
        oneAccepted.add(id_b);
        otherAccepted.add(id_a);
        // removing from both pendingFriendList
        onePending.remove(id_b);
        otherPending.remove(id_a);

        one.setAcceptedFriendList(oneAccepted);
        one.setPendingFriendList(onePending);
        other.setAcceptedFriendList(otherAccepted);
        other.setPendingFriendList(otherPending);

        repository.save(one);
        repository.save(other);
        log.info("Befriend uid_a = "+ id_a + " uid_b: " + id_b);
        return ResponseEntity.ok().build();
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

    @RequestMapping(method = RequestMethod.GET, value = "/friends/search/recommend")
    public @ResponseBody ResponseEntity<?> getRecommendation(@Param("uid") String uid){
        Friends reference = repository.findOne(uid);
        List<Friends> friends = new ArrayList<>();
        // need find with ignored case
        friends.addAll(repository.findByUniversityIgnoreCase(reference.getUniversity()));
        friends.addAll(repository.findByMajorIgnoreCase(reference.getMajor()));
        friends.addAll(repository.findByGraduationYear(reference.getGraduationYear()));

        // TODO: remove duplicate from friends
        Set<Friends> friendsSet = new HashSet<>();
        friendsSet.addAll(friends);
        friends.clear();
        friends.addAll(friendsSet);
        friends.remove(reference);

        //adding HATEOAS links for each friends in list
        List<FriendsResource> resources = friends.stream().map(assembler::toResource).collect(Collectors.toList());
        //friend.add(linkTo(methodOn(CustomizeController.class).getRecommendation(uid)).withSelfRel());

        log.info("getRecommendation: reference("+reference.getId()+"); size: "+friends.size() + friends.toString());
        return ResponseEntity.ok(resources);
    }

}
