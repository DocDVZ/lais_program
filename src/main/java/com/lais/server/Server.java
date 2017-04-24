package com.lais.server;

import com.lais.streams.StreamType;
import com.lais.valuegenerators.TimeValueGenerator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public class Server {

    private Queue<Request> requestQueue = new LinkedList<>();
    private TimeQuant tq;
    private Request currentRQ;
    private ServerStatus status = ServerStatus.AVAILIBLE;
    private TimeValueGenerator gaussian;
    private TimeValueGenerator expanential;


    public Server(TimeQuant tq){
        this.tq = tq;
    }

    public void processRequest(Request rq){
        rq.setQueueTime(tq.getCurrentTime());
        long processingTime = generateProcessingTime(rq.getStreamType());
        // TODO serverStatus
        requestQueue.add(rq);
    }

    public void doProcessTurn() {

    }

    private long generateProcessingTime(StreamType st){
        if (st.equals(StreamType.PUASSON)){
            return gaussian.generateValue();
        } else if(st.equals(StreamType.ERLANG)){
            return expanential.generateValue();
        } else {
            throw new IllegalArgumentException("(╯°□°）╯︵ ┻━┻ —");
        }
    }

    public void setGaussian(TimeValueGenerator gaussian) {
        this.gaussian = gaussian;
    }

    public void setExpanential(TimeValueGenerator expanential) {
        this.expanential = expanential;
    }
}
