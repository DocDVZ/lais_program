package com.lais.app;

import com.lais.server.Server;
import com.lais.server.TimeQuant;
import com.lais.streams.ErlangStream;
import com.lais.streams.PuassonStream;
import com.lais.valuegenerators.ExpanentialGenerator;
import com.lais.valuegenerators.GaussGenerator;

/**
 * Created by DocDVZ on 23.04.2017.
 */
public class Main {

    // Total modelling time
    private static int modellingTime = 500;

    // Erlang stream parameters
    private static int erlangOrder = 3;
    private static double erlangLambda = 0.5d;

    // Puasson stream parameters
    private static double puassonLambda = 0.2d;

    // Gauss distribution parameters
    private static double gaussBase = 15d;
    private static double gaussSigma = 2d;

    // Expanential distribution parameters
    private static double expanentLambda = 0.33d;



    public static void main(String[] args) {
        // Turn based time flow engine
        TimeQuant tq = new TimeQuant();

        // Initializing data streams
        ErlangStream erlangStream = new ErlangStream(erlangLambda, erlangOrder);
        PuassonStream puassonStream = new PuassonStream(puassonLambda);

        // Initializing random value generators
        GaussGenerator gaussGenerator = new GaussGenerator(gaussBase, gaussSigma);
        ExpanentialGenerator expanentialGenerator = new ExpanentialGenerator(expanentLambda);

        // Initializing server
        Server server = new Server(tq);
        server.setExpanential(expanentialGenerator);
        server.setGaussian(gaussGenerator);

        // Processing requests during modelling time
        for (int i = 0; i< modellingTime; i++){
            // adding requests to server queue
            server.processRequest(erlangStream.generateRequest());
            server.processRequest(puassonStream.generateRequest());

            // do server processing and perform next turn
            server.doProcessTurn();
            tq.next();
        }



    }


}
