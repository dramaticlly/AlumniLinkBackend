package hello.controller;

import java.util.concurrent.atomic.AtomicLong;

import hello.model.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public String index(){
        return "Greeting from AlumniLink";
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.info(name+" [Slf4j info]has visited, count = "+ counter.toString());
        log.debug(name+" [Slf4j debug]has visited, count = "+ counter.toString());
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
