package com.mps.springreactiveexample.service.impl;

import com.mps.springreactiveexample.MockBackendApi;
import com.mps.springreactiveexample.model.*;
import com.mps.springreactiveexample.service.ItemSearchService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


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
        System.out.println("\t\tInside "+supportedItem()+" searchItem with thread " + Thread.currentThread().toString());

        //TODO: how to make these XXX YYY calls conditionals In clear way?
        return Mono.zip(mockBackendApi.getItemANameApi(sisr.getItemIdentifiers().getItemId()),
                        sisr.isAddXXXDetails()
                        ?mockBackendApi.getItemAXXXApi(sisr.getItemIdentifiers().getItemId())
                        :Mono.empty(),
                        sisr.isAddYYYDetails()
                        ?mockBackendApi.getItemAYYYApi(sisr.getItemIdentifiers().getItemId())
                        :Mono.empty())
                .map(tuple3 -> Item.builder()
                        .name(tuple3.getT1())
                        .xxxDetails(tuple3.getT2())
                        .yyyDetails(tuple3.getT3())
                        .build()
                );
    }


}
