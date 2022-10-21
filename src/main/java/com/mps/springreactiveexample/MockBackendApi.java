package com.mps.springreactiveexample;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Random;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
@Component
public class MockBackendApi {

    public Mono<String> getItemANameApi(long id) {
        return Mono.just("A_" + generateRandomString());
    }

    public Mono<String> getItemBNameApi(long id) {
        return Mono.just("B_" + generateRandomString());
    }

    public Mono<String> getItemCNameApi(long id) {
        return Mono.just("C_" + generateRandomString());
    }

    public Mono<String> getItemAXXXApi(long id) {
        return Mono.just("A_XXX_" + generateRandomString());
    }

    public Mono<String> getItemBXXXApi(long id) {
        return Mono.just("B_XXX_" + generateRandomString());
    }

    public Mono<String> getItemCXXXApi(long id) {
        return Mono.just("C_XXX_" + generateRandomString());
    }

    public Mono<String> getItemAYYYApi(long id) {
        return Mono.just("A_YYY_" + generateRandomString());
    }

    public Mono<String> getItemBYYYApi(long id) {
        return Mono.just("B_YYY_" + generateRandomString());
    }

    public Mono<String> getItemCYYYApi(long id) {
        return Mono.just("C_YYY_" + generateRandomString());
    }


    private String generateRandomString() {
        return new Random()
                .ints(48, 123)
                .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
                .limit(15)
                .mapToObj(c -> (char) c)
                .collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();

    }
}
