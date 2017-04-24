package com.lais.streams;

import com.lais.server.Request;

import java.util.Random;

/**
 * Created by DocDVZ on 23.04.2017.
 */
public class PuassonStream {

    protected double lambda;
    protected StreamType streamType;
    protected final Random random = new Random();


    public PuassonStream(double lambda){
        this.lambda = lambda;
        this.streamType = StreamType.PUASSON;
    }

    public Request generateRequest(){
        if (random.nextInt(100)<lambda*100){
            Request rq = new Request();
            rq.setStreamType(streamType);
            return rq;
        } else {
            return null;
        }
    }
}
