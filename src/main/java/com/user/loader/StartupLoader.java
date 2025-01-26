package com.user.loader;

import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartupLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        log.info("Loading users from external API into H2 database...");
        userService.loadUsersFromExternalDataAPI();
        log.info("Users successfully loaded into H2 database.");
    }
}
