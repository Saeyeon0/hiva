package com.example.demosecurity.bootstrap;

import com.example.demosecurity.service.UserService;
import com.example.demosecurity.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class LoadData implements CommandLineRunner {
    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * this method runs on startup program, and adds to database new simple user with username test and password test
     * using UserService 's createUser method that saves user to DB
     * */
    @Override
    public void run(String... args) {

        LOG.info("Initializing database with simple user 'test'");

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("test");

//        user.setFirstName("testUser");
//        user.setLastname("testUser");

        userService.createUser(user);
    }

}
