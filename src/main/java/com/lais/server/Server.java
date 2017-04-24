package com.lais.server;

import com.lais.streams.StreamType;
import com.lais.valuegenerators.TimeValueGenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public class Server {

    private Queue<Request> requestQueue = new LinkedList<>();
    private TimeQuant tq;
    private Request currentRQ;
    private ServerStatus status = ServerStatus.AVALIBLE;
    private TimeValueGenerator gaussian;
    private TimeValueGenerator expanential;
    private long currentProcessTime = 0;
    private List<Request> results = new ArrayList<>();
    private int queueVector = 0;


    public Server(TimeQuant tq){
        this.tq = tq;
    }

    public void processRequest(Request rq){
        if (rq==null){return;}
        rq.setQueueTime(tq.getCurrentTime());
        long processingTime = generateProcessingTime(rq.getStreamType());
        rq.setProcessingTime(processingTime);
        rq.setQueueSize(requestQueue.size());
        requestQueue.add(rq);
    }

    public void doProcessTurn() {
        if (currentRQ==null || (currentRQ.getProcessingTime()+currentRQ.getDequeueTime())<=tq.getCurrentTime()){
            // no current request processing or server is busy with request
            currentRQ = requestQueue.poll();
            if (currentRQ != null){
                status = ServerStatus.UNAVALIBLE;
                currentRQ.setDequeueTime(tq.getCurrentTime());
                ServerStatus status = currentRQ.getQueueTime() == tq.getCurrentTime() ? ServerStatus.AVALIBLE : ServerStatus.UNAVALIBLE;
                currentRQ.setServerStatus(status);
                results.add(currentRQ);
            } else {
                status = ServerStatus.AVALIBLE;
            }
        }
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

    public ServerStatus getStatus() {
        return status;
    }

    public List<Request> getResults() {
        processResults();
        return results;
    }

    private void processResults() {
        for (int i=0; i<results.size(); i++){
            Request rq = results.get(i);
            for (int k = i+1; k<results.size(); k++){
                Request iter = results.get(k);
                if (iter.getStreamType().equals(StreamType.PUASSON) && iter.getNextPuasson()==null){
                    rq.setNextPuasson(iter.getQueueTime());
                }
                if (iter.getStreamType().equals(StreamType.ERLANG) && iter.getNextErlang()==null){
                    rq.setNextErlang(iter.getQueueTime());
                }
                if (rq.getNextErlang()!=null && rq.getNextPuasson()!=null ){
                    break;
                }
            }
        }
    }
}
