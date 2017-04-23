package myImpl;

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

    public Server(TimeQuant tq){
        this.tq = tq;
    }

    public void processRequest(Request rq){
        rq.setQueueTime(tq.getCurrentTime());
        int processingTime = generateProcessingTime(rq.getStreamType());
        // TODO serverStatus
        requestQueue.add(rq);
    }

    public void doProcessTurn() {

    }

    private int generateProcessingTime(StreamType st){
        if (st.equals(StreamType.PUASSON)){
            return 0;
        } else if(st.equals(StreamType.ERLANG)){
            return 0;
        } else {
            throw new IllegalArgumentException("(╯°□°）╯︵ ┻━┻ —");
        }
    }

}
