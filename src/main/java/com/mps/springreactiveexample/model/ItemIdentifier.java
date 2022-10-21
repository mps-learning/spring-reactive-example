package com.mps.springreactiveexample.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ItemIdentifier {
    long itemId;
    ItemType itemType;
}
