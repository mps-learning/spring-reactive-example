package com.mps.springreactiveexample.service;

import com.mps.springreactiveexample.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
public interface ItemSearchService {

    ItemType supportedItem();


    //Call any one of the following 2 implementations from controller
    // basic idea is to make ALL calls asynchronously and zip/merge responses later
    Mono<Item> searchItem(SingleItemSearchRequest request);


    default Flux<Item> searchItems(List<SingleItemSearchRequest> requests) {

        return Flux.fromIterable(requests)
                .flatMap(this::searchItem)
                .subscribeOn(Schedulers.boundedElastic());
    }
}


