package hello.repository;

import hello.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// Spring Data REST will create an implementation of this interface automatically.
// Then it will use the @RepositoryRestResource annotation to direct Spring MVC to create RESTful endpoints at /people.
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);
    // help generate query like this:       "href" : "http://localhost:8080/people/search/findByLastName{?name}",
    List<Customer> findByLastName(@Param("name") String name);

}
