package com.rallibau.apps.products.controller.tweet;


import com.rallibau.apps.tweets.TweetsApplication;
import com.rallibau.shared.infrastructure.InfrastructureTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TweetsApplication.class)
@SpringBootTest
public abstract class TweetsContextInfrastructureTestCase extends InfrastructureTestCase {
}
