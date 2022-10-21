package com.mps.springreactiveexample.service.impl;

import com.mps.springreactiveexample.MockBackendApi;
import com.mps.springreactiveexample.model.*;
import com.mps.springreactiveexample.service.ItemSearchService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


import java.util.List;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
@Service
public class AItemSearchService implements ItemSearchService {


    final MockBackendApi mockBackendApi;

    public AItemSearchService(MockBackendApi mockBackendApi) {
        this.mockBackendApi = mockBackendApi;
    }

    @Override
    public ItemType supportedItem() {
        return ItemType.A;
    }

    //TODO: all these calls need to be asynchronous.
    @Override
    public Mono<Item> searchItem(SingleItemSearchRequest sisr) {
        System.out.println("\t\tInside " + supportedItem() + " searchItem with thread " + Thread.currentThread().toString());

        //TODO: how to make these XXX YYY calls conditionals In clear way?
        return Mono.zip(getNameDetails(sisr).defaultIfEmpty("Default Name"),
                        getXXXDetails(sisr).defaultIfEmpty("Default XXX Details"),
                        getYYYDetails(sisr).defaultIfEmpty("Default YYY Details"))
                .map(tuple3 -> Item.builder()
                        .name(tuple3.getT1())
                        .xxxDetails(tuple3.getT2())
                        .yyyDetails(tuple3.getT3())
                        .build()
                );
    }
    private Mono<String> getNameDetails(SingleItemSearchRequest sisr) {
        return mockBackendApi.getItemANameApi(sisr.getItemIdentifiers().getItemId());
    }
    private Mono<String> getYYYDetails(SingleItemSearchRequest sisr) {
        return sisr.isAddYYYDetails()
               ? mockBackendApi.getItemAYYYApi(sisr.getItemIdentifiers().getItemId())
               : Mono.empty();
    }
    private Mono<String> getXXXDetails(SingleItemSearchRequest sisr) {
        return sisr.isAddXXXDetails()
               ? mockBackendApi.getItemAXXXApi(sisr.getItemIdentifiers().getItemId())
               : Mono.empty();
    }


}
