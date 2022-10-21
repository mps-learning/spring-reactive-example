package com.mps.springreactiveexample.service.impl;

import com.mps.springreactiveexample.MockBackendApi;
import com.mps.springreactiveexample.model.*;
import com.mps.springreactiveexample.service.ItemSearchService;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
public class BItemSearchService implements ItemSearchService {

    final MockBackendApi mockBackendApi;

    public BItemSearchService(MockBackendApi mockBackendApi) {this.mockBackendApi = mockBackendApi;}

    @Override
    public ItemType supportedItem() {
        return ItemType.B;
    }
    //TODO: all these calls need to be asynchronous.
    // Idea is to use Mono/Flux
    @Override
    public Mono<Item> searchItem(SingleItemSearchRequest sisr) {
        Item item = Item
                .builder()
                .name(mockBackendApi.getItemBNameApi(sisr.getItemIdentifiers().getItemId()))
                .xxxDetails(sisr.isAddXXXDetails()
                            ? mockBackendApi.getItemBXXXApi(sisr.getItemIdentifiers().getItemId())
                            : null)
                .yyyDetails(sisr.isAddYYYDetails()
                            ? mockBackendApi.getItemBYYYApi(sisr.getItemIdentifiers().getItemId())
                            : null)
                .build();


        return Mono.just(item);
    }

    @Override
    public Mono<Item> searchItems(List<SingleItemSearchRequest> requests) {
        //can use above method to make async calls to 3 backends
        return null;
    }
}
