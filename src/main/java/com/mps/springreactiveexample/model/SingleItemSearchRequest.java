package com.mps.springreactiveexample.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class SingleItemSearchRequest {

    ItemIdentifier itemIdentifiers;
    boolean isAddXXXDetails;
    boolean isAddYYYDetails;
}
