package com.lais.valuegenerators;

import java.util.Random;

/**
 * Created by dzvyagin on 24.04.2017.
 */
public class ExpanentialGenerator implements TimeValueGenerator{

    private double lambda;
    private final Random random = new Random();

    public ExpanentialGenerator(double lambda){
        this.lambda = lambda;
    }

    @Override
    public long generateValue() {
        return Math.round(Math.log(1-random.nextDouble())/(-lambda));
    }

}
