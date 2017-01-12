package hello;

import hello.model.Customer;
import hello.repository.CustomerRepository;
import hello.storage.StorageProperties;
import hello.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    //@Autowired
    //private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService){
        return (args) -> {
            storageService.deleteAll();
            storageService.init();

            /** Mongo
            customerRepository.deleteAll();
            // save a couple of customers
            customerRepository.save(new Customer("Alice", "Smith"));
            customerRepository.save(new Customer("Bob", "Smith"));


            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (Customer customer : customerRepository.findAll()) {
                System.out.println(customer);
            }
            System.out.println();

            // fetch an individual customer
            System.out.println("Customer found with findByFirstName('Alice'):");
            System.out.println("--------------------------------");
            System.out.println(customerRepository.findByFirstName("Alice"));

            System.out.println("Customers found with findByLastName('Smith'):");
            System.out.println("--------------------------------");
            for (Customer customer : customerRepository.findByLastName("Smith")) {
                System.out.println(customer);
            }
            */
        };
    }

}
