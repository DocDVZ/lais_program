package com.lais.valuegenerators;

import java.util.Random;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public class GaussGenerator implements TimeValueGenerator {

    private double base;
    private double sigma;
    private final Random random = new Random();

    public GaussGenerator(double base, double sigma){
        this.base = base;
        this.sigma = sigma;
    }


    /**
     * nextGaussian() returns the next pseudorandom,
     * Gaussian ("normally") distributed double value with mean 0.0 and standard deviation 1.0
     * from this random number generator's sequence
     * Simply adding base and deviation
     * @return generated value
     */
    @Override
    public long generateValue() {
        return Math.round(base + sigma*random.nextGaussian());
    }
}
