package myImpl;

import java.util.Random;

/**
 * Created by DocDVZ on 23.04.2017.
 */
public class PuassonStream {

    private double lambda;
    private final Random random = new Random();


    public PuassonStream(double lambda){
        this.lambda = lambda;
    }

    public Request generateRequest(){
        if (random.nextInt(100)<lambda*100){
            Request rq = new Request();
            rq.setStreamType(StreamType.PUASSON);
            return rq;
        } else {
            return null;
        }
    }
}
