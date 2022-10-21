package com.mps.springreactiveexample;

import com.mps.springreactiveexample.model.*;
import com.mps.springreactiveexample.service.ItemSearchService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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
    List<Item> searchItems(@RequestBody ItemsSearchRequest itemsSearchRequest) {

        Map<ItemType, List<SingleItemSearchRequest>> searchRequestByItemType=
                breakRequestByItemTypes(itemsSearchRequest);

        //TODO:
        // need some magical code here to do all the operations asynchronous
        // we have one service auto wierd for each ItemType (each entry of above hashMap)
        // we can call
        // either service method searchItem with one singleItemSearch object or
        // or service method searchItems with the list  singleItemSearch
        // whatever is the best suitable to make all calls asynchronous

//        Flux.fromIterable(searchRequestByItemType.entrySet())
//                .

        return null; // just for compilation
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
                .collect(Collectors.groupingBy(e->e.getItemIdentifiers().getItemType()));


    }


}
