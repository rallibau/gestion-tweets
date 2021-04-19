package com.rallibau.apps.products.controller.tweet;

import org.springframework.transaction.annotation.Transactional;

@Transactional("products-transaction_manager")
public abstract class TweetsApplicationTestCase extends ApplicationTestCase {


}
