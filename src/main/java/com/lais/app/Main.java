package com.lais.app;

import com.lais.server.Request;
import com.lais.server.Server;
import com.lais.server.ServerStatus;
import com.lais.server.TimeQuant;
import com.lais.streams.ErlangStream;
import com.lais.streams.PuassonStream;
import com.lais.valuegenerators.ExpanentialGenerator;
import com.lais.valuegenerators.GaussGenerator;

import java.text.DecimalFormat;
import java.util.List;

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

        // Processing requests left in queue
        while (server.getStatus().equals(ServerStatus.UNAVAILABLE)){
            server.doProcessTurn();
            tq.next();
        }

        // Printing results to standard output
        List<Request> results = server.getResults();
        System.out.println(String.format("%-5s", "№") + String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s",
                "StreamType", "Queue time", "Next ERLANG", "Next PUASSON", "Time finished", "Server status", "Queue size", "Time in queue", "Processing time"));
        for (int i=0; i<results.size(); i++){
            System.out.println(String.format("%-5s", i+1) + results.get(i));
        }
        Request lastRq = results.get(results.size()-1);
        long totalProcessingTime = 0;
        int totalQueueTime = 0;
        for (Request rq : results){
            totalProcessingTime += rq.getProcessingTime();
            totalQueueTime += rq.getQueueTime();
        }
        DecimalFormat df = new DecimalFormat("###.###");
        System.out.println("Коэффициет простоя: " + df.format( 1d - (double) totalProcessingTime/(lastRq.getDequeueTime() + lastRq.getProcessingTime())));
        System.out.println("Среднее время пробывания в очереди: " + df.format((double) totalQueueTime/results.size()));
    }


}
