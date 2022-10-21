package com.mps.springreactiveexample;

import com.mps.springreactiveexample.model.*;
import com.mps.springreactiveexample.service.ItemSearchService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
@RestController
@RequestMapping("/items")
public class ItemController {

    final List<ItemSearchService> itemSearchServices;

    public ItemController(List<ItemSearchService> itemSearchServices) {
        this.itemSearchServices = itemSearchServices;
    }

    @PostMapping
    List<Item>  searchItems(@RequestBody ItemsSearchRequest itemsSearchRequest) {

        Map<ItemType, List<SingleItemSearchRequest>> searchRequestByItemType =
                breakRequestByItemTypes(itemsSearchRequest);

        //TODO:
        // need some magical code here to do all the operations asynchronous
        // I have one service auto wierd for each ItemType (each entry in above hashMap)
        // From controller we will call SearchService:searchItems() passing the list of singleItemSearchReq for that ItemType
        // Goal is to call all services asynchronously

        //Below code doesn't work

        Flux<Item> itemFlux = Flux.fromIterable(searchRequestByItemType.entrySet())
                .flatMap(e ->
                        getService(e.getKey()).searchItems(e.getValue()))
                .subscribeOn(Schedulers.boundedElastic());

        List<Item>  listMono = itemFlux.collectList().block();

        return listMono;
    }

    private Map<ItemType, List<SingleItemSearchRequest>> breakRequestByItemTypes(final ItemsSearchRequest isr) {

        return isr
                .getItemIdentifiers()
                .stream()
                .map(e -> SingleItemSearchRequest
                        .builder()
                        .itemIdentifiers(e)
                        .isAddYYYDetails(isr.isAddYYYDetails())
                        .isAddXXXDetails(isr.isAddXXXDetails())
                        .build())
                .collect(Collectors.groupingBy(e -> e.getItemIdentifiers().getItemType()));


    }

    private ItemSearchService getService(final ItemType itemType) {
        return itemSearchServices
                .stream()
                .filter(e -> e.supportedItem() == itemType)
                .findFirst()
                .orElse(null);
    }

}
