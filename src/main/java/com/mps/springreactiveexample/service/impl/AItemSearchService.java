package com.mps.springreactiveexample.service.impl;

import com.mps.springreactiveexample.MockBackendApi;
import com.mps.springreactiveexample.model.*;
import com.mps.springreactiveexample.service.ItemSearchService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
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

        //TODO: how to make these XXX YYY calls conditionals ?
        return Mono.zip(mockBackendApi.getItemANameApi(sisr.getItemIdentifiers().getItemId()),
                        mockBackendApi.getItemAXXXApi(sisr.getItemIdentifiers().getItemId()),
                        mockBackendApi.getItemAYYYApi(sisr.getItemIdentifiers().getItemId()))
                .map(tuple3 -> Item.builder()
                        .name(tuple3.getT1())
                        .xxxDetails(tuple3.getT2())
                        .yyyDetails(tuple3.getT3())
                        .build()
                );
    }

    @Override
    public Mono<Item> searchItems(List<SingleItemSearchRequest> requests) {
        //TODO:  Hwo to call the search for each item paralally ?

        return null;
    }
}
