package com.faceit.assignmentelibrary.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.faceit.assignmentelibrary.domain.data.access.repository")
@EntityScan("com.faceit.assignmentelibrary.domain.data.access.entity")
public class JpaConfig {
}
