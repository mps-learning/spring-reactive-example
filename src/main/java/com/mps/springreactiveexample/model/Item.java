package com.mps.springreactiveexample.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@Builder
public class Item {
    String id;
    String name;
    String xxxDetails;
    String yyyDetails;

}
