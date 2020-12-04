package com.github.tangmonkmeat.common.enums;

/**
 * @author zwl
 * @date 2020/12/4 11:31
 */
public enum  Lock {

    LOCK("lock"),
    UNLOCK("unlock");

    String key;

    Lock(String key){
        this.key = key;
    }


    public String getKey() {
        return key;
    }
}
