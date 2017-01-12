package hello.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
public class Customer {

    //The id is mostly for internal use by MongoDB
    @Id
    public String id;

    //left unannotated.
    //It is assumed that they’ll be mapped to fields that share the same name as the properties themselves.
    private String firstName;
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

