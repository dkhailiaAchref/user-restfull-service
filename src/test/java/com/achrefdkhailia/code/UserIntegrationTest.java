package com.achrefdkhailia.code;

import com.achrefdkhailia.code.application.Application;
import com.achrefdkhailia.code.domain.User;
import com.achrefdkhailia.code.repository.UserRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.achrefdkhailia.code.ParamTestFixtures.assertionResponseFailure;
import static com.achrefdkhailia.code.ParamTestFixtures.assertionResponseUser;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * cette classe de test , simule les appels controller
 * par accés direct http sur le navigateur avec url de base ( http://localhost:8080/users  )
 */
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = Application.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    UserRepository userRepository;
    private static final String ENDPOINT = "/users";
    private static final User newUser = new User();
    private static Long id;

    @Before
    public void setup() {
        id = 1L;
        newUser.setId(id);
        newUser.setFirstName("John"); //John
        newUser.setLastName("sina"); //sina
        userRepository.save(newUser);
    }

    /**************************** success use cases **************************************/

    @Test
    public void test0_add_user_success() {
        ResponseEntity<User> user = restTemplate.postForEntity(ENDPOINT,
                new HttpEntity<User>(newUser),
                User.class);

        assertionResponseUser(user, newUser, "test0_add_user_success", HttpStatus.CREATED);
    }


    @Test
    public void test2_find_user_success() {
        ResponseEntity<User> user = restTemplate.getForEntity(ENDPOINT + "/" + id,
                User.class);
        assertionResponseUser(user, newUser, "test2_find_user_success", HttpStatus.OK);
    }

    @Test
    public void test3_findall_users_success() {
        ResponseEntity<User[]> users = restTemplate.getForEntity(ENDPOINT,
                User[].class);

        assertThat(users.getStatusCode(),
                is(equalTo(HttpStatus.OK)));
        assertThat(users.getBody().length,
                is(equalTo((int) userRepository.count())));
    }

    @Test
    public void test4_update_user_success() {
        newUser.setFirstName("New name");
        ResponseEntity<User> user = restTemplate.exchange(ENDPOINT + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<User>(newUser),
                User.class);
        assertionResponseUser(user, newUser, "test4_update_user_success", HttpStatus.OK);
    }


    @Test
    public void test5_delete_user_success() {
        ResponseEntity<String> response = restTemplate.exchange(ENDPOINT + "/" + id,
                HttpMethod.DELETE,
                null,
                String.class);
        System.out.println("test5_delete_user_success=> status response " + response.getStatusCode()+ " , expectedStatus :"+HttpStatus.OK);

        assertThat(response.getStatusCode(),
                is(equalTo(HttpStatus.OK)));
    }

    /**************************** failure use cases **************************************/
    @Test
    public void test1_add_user_failure() {
        ResponseEntity<User> response = restTemplate.exchange(ENDPOINT,
                HttpMethod.POST,
                null,
                User.class);

        assertionResponseFailure(response, "test1_add_user_failure", HttpStatus.CREATED);

    }

    @Test
    public void test6_find_user_failure() {
        ResponseEntity<User> response = restTemplate.getForEntity(ENDPOINT + "/" + id,
                User.class);

        assertionResponseFailure(response, "test6_find_user_failure", HttpStatus.OK);
    }


        @Test
    public void test8_update_user_failure() {
        newUser.setFirstName("new Name");
        ResponseEntity<User> response = restTemplate.exchange(ENDPOINT + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<User>(newUser),
                User.class);

        assertionResponseFailure(response,"test8_update_user_failure",HttpStatus.OK);

    }

    @Test
    public void test9_delete_user_failure() {
        ResponseEntity<String> response = restTemplate.exchange(ENDPOINT + "/" + id,
                HttpMethod.DELETE,
                null,
                String.class);

       assertionResponseFailure(response,"test9_delete_user_failure",HttpStatus.OK);
    }
}
