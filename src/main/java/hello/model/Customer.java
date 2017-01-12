package hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    //The id is mostly for internal use by MongoDB
    @Id
    private String id;

    //left unannotated.
    //It is assumed that they’ll be mapped to fields that share the same name as the properties themselves.
    private String firstName;
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

