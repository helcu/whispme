package com.whispcorp.whispme.util;

/**
 * Created by NightmareTK on 05/07/2017.
 */

import java.util.UUID;

public class UUIDGenerator {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}
