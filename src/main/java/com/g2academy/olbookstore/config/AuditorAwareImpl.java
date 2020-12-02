package com.g2academy.olbookstore.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Arrays.asList("Admin", "angga", "rifki", "asep", "malik", "yoseph")
                .get(new Random().nextInt(6)));
    }
}
