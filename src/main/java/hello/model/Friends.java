package hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friends {

    //The id is mostly for internal use by MongoDB
    @Id
    private String id;

    //left unannotated.
    //It is assumed that they’ll be mapped to fields that share the same name as the properties themselves.
    private String firstName;
    private String lastName;
    private String email;

    private int graduationYear;
    private String university;
    private String major;

    private List<String> acceptedFriendList;
    private List<String> pendingFriendList;

    public Friends(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

