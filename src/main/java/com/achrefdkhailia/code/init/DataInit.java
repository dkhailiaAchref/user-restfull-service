package com.achrefdkhailia.code.init;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.achrefdkhailia.code.domain.User;
import com.achrefdkhailia.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * La classe  DataInit implémente l'interface ApplicationRunner,
 * elle exécutera automatiquement en même temps du démarrage de l'application. Dans cette classe, nous allons intérer quelques enregistrements (record) a la  table  USER.
 */
@Component
public class DataInit implements ApplicationRunner {

    private UserRepository userRepository;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = userRepository.count();

        if (count == 0) {
            User p1 = new User();

            p1.setFirstName("John");
            p1.setLastName("sina");

            Date d1 = df.parse("1980-12-20");
            p1.setDateOfBirth(d1);
            //
            User p2 = new User();

            p2.setFirstName("Smith");
            p1.setLastName("will");
            Date d2 = df.parse("1985-11-11");
            p2.setDateOfBirth(d2);

            userRepository.save(p1);
            userRepository.save(p2);
        }

    }

}