package com.rallibau.apps.tweets.config;

import com.rallibau.shared.domain.bus.command.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsServerConfiguration {
    private final CommandBus bus;

    public ProductsServerConfiguration(CommandBus bus) {
        this.bus = bus;
    }


}
