package com.sun.honor.util;

import java.util.UUID;

/**
 * @author sunjian.
 */
public class NumberUtils {
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
