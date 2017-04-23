package myImpl;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public class Request {

    private StreamType streamType;
    private int queueTime;
    private int dequeueTime;
    private int queueSize;

    public StreamType getStreamType() {
        return streamType;
    }

    public void setStreamType(StreamType streamType) {
        this.streamType = streamType;
    }

    public int getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(int queueTime) {
        this.queueTime = queueTime;
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
}
