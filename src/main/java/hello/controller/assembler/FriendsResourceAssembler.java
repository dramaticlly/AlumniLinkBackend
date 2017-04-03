package hello.controller.assembler;

import hello.controller.CustomizeController;
import hello.controller.resource.FriendsResource;
import hello.model.Friends;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class FriendsResourceAssembler extends ResourceAssemblerSupport<Friends,FriendsResource>{

    public FriendsResourceAssembler() {
        super(CustomizeController.class, FriendsResource.class);
    }

    @Override
    public FriendsResource toResource(Friends friends) {

        FriendsResource resource = createResourceWithId(friends.getId(),friends);

        resource.setFirstName(friends.getFirstName());
        resource.setLastName(friends.getLastName());
        resource.setEmail(friends.getEmail());
        resource.setGraduationYear(friends.getGraduationYear());
        resource.setUniversity(friends.getUniversity());
        resource.setMajor(friends.getMajor());
        resource.setAcceptedFriendList(friends.getAcceptedFriendList());
        resource.setPendingFriendList(friends.getPendingFriendList());

        return resource;
    }
}
