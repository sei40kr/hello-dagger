package me.yong_ju.hello_dagger;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
@interface MaximumWithdrawal {}
