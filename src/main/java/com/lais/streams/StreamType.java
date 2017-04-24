package com.lais.streams;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public enum StreamType {

    PUASSON("L2 - PUASSON"), ERLANG("L1 - ERLANG");

    private String name;

    StreamType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
