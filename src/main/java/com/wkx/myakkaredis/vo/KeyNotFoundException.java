package com.wkx.myakkaredis.vo;

import java.io.Serializable;

public class KeyNotFoundException extends Exception implements Serializable{

    public final String key;

    public KeyNotFoundException(String key){
        this.key=key;
    }
}
