package com.lais.server;

import com.lais.streams.StreamType;

import java.text.DecimalFormat;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public class Request {

    private StreamType streamType;
    private long processingTime;
    private int queueTime;
    private int dequeueTime;
    private int queueSize;
    private ServerStatus serverStatus;
    private Integer nextPuasson;
    private Integer nextErlang;
    private int queueVector;


    @Override
    public String toString(){
        return String.format(" %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s", streamType, queueTime, nextErlang, nextPuasson, dequeueTime+processingTime, serverStatus, queueSize, dequeueTime-queueTime, processingTime);
    }

    public StreamType getStreamType() {
        return streamType;
    }

    public void setStreamType(StreamType streamType) {
        this.streamType = streamType;
    }

    public int getDequeueTime() {
        return dequeueTime;
    }

    public void setDequeueTime(int dequeueTime) {
        this.dequeueTime = dequeueTime;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(int queueTime) {
        this.queueTime = queueTime;
    }

    public Integer getNextPuasson() {
        return nextPuasson;
    }

    public void setNextPuasson(Integer nextPuasson) {
        this.nextPuasson = nextPuasson;
    }

    public Integer getNextErlang() {
        return nextErlang;
    }

    public void setNextErlang(Integer nextErlang) {
        this.nextErlang = nextErlang;
    }

    public int getQueueVector() {
        return queueVector;
    }

    public void setQueueVector(int queueVector) {
        this.queueVector = queueVector;
    }
}
