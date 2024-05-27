package dev.manish.productservicemanish;

import dev.manish.productservicemanish.inheritanceexamples.joinedtable.JTMentorRepository;
import dev.manish.productservicemanish.inheritanceexamples.joinedtable.JTUserRepository;
import dev.manish.productservicemanish.inheritanceexamples.joinedtable.Mentor;
import dev.manish.productservicemanish.inheritanceexamples.joinedtable.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceManishApplicationTests {

//    @Autowired
//    private JTUserRepository jtuserRepository;
//    @Autowired
//    private JTMentorRepository jtmentorRepository;

    @Test
    void contextLoads() {
    }


//    @Test
//    void testInheritanceExample() {
//        User user = new User();
//
//        user.setEmail("manish@gmail.com");
//        user.setPassword("Manish");
//
//        jtuserRepository.save(user);
//
//
//        Mentor mentor = new Mentor();
//        mentor.setEmail("manish@gmail.com");
//        mentor.setPassword("Manish");
//        mentor.setNumberOfMentees(5);
//
//        jtmentorRepository.save(mentor);
//
//    }


}
