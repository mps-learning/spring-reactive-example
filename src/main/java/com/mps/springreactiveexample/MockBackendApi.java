package com.mps.springreactiveexample;

import com.mps.springreactiveexample.model.ItemType;

import java.util.Random;

/**
 * @author manvendrasingh
 * @since 2022-October-21
 * <p>
 * </p>
 **/
public class MockBackendApi {

    public String getItemANameApi(long id) {
        return "A_" + generateRandomString();
    }

    public String getItemBNameApi(long id) {
        return "B_" + generateRandomString();
    }

    public String getItemCNameApi(long id) {
        return "C_" + generateRandomString();
    }

    public String getItemAXXXApi(long id) {
        return "A_XXX_" + generateRandomString();
    }

    public String getItemBXXXApi(long id) {
        return "B_XXX_" + generateRandomString();
    }

    public String getItemCXXXApi(long id) {
        return "C_XXX_" + generateRandomString();
    }

    public String getItemAYYYApi(long id) {
        return "A_YYY_" + generateRandomString();
    }

    public String getItemBYYYApi(long id) {
        return "B_YYY_" + generateRandomString();
    }

    public String getItemCYYYApi(long id) {
        return "C_YYY_" + generateRandomString();
    }


    private String generateRandomString() {
        return new Random()
                .ints(48, 123)
                .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
                .limit(15)
                .mapToObj(c -> (char) c)
                .collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();

    }
}
