package myImpl;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public class ErlangStream extends PuassonStream {

    private int erlangOrder;
    private int count;

    public ErlangStream(double lambda, int erlangOrder){
        super(lambda);
        this.erlangOrder = erlangOrder;
    }

    @Override
    public Request generateRequest() {
        Request request = super.generateRequest();
        if (request!=null){
            request.setStreamType(StreamType.ERLANG);
            if (count%erlangOrder!=0){
                request = null;
            }
            count++;
        }
        return request;
    }
}
