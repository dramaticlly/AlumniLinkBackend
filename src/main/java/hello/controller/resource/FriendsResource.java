package hello.controller.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class FriendsResource extends ResourceSupport {

    private String firstName;
    private String lastName;
    private String email;

    private int graduationYear;
    private String university;
    private String major;

    private List<String> acceptedFriendList;
    private List<String> pendingFriendList;
}
