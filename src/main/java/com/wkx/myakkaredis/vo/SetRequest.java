package com.wkx.myakkaredis.vo;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SetRequest implements Serializable{

    private final String key;

    private final Object value;

    public SetRequest(String key,Object value){
        this.key=key;
        this.value=value;
    }
}
