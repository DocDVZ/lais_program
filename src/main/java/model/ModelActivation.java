package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelActivation {

    private int normal_n;
    private int normal_a;
    private double normal_b;
    private double eksponent_lambda;
    private double puasson_l;
    private int erlang_l;
    private double erlang_lambda;
    private Random rand;
    public static List<String> queueTaskName;
    public static List<Double> queueTaskRand;

    public static List<ModelOutput> log;

    public static String taskName;
    public static double Tmodel;
    public static double L1;
    public static double L2;
    public static double h;
    public static double TEnd;
    public static int s;
    public static int q;
    public static int i = 0;
    public static int l1counter = 0;
    public static int l2counter = 0;

    public ModelActivation() {
        queueTaskName = new ArrayList<String>();
        queueTaskRand = new ArrayList<Double>();
        normal_n = 1000;
        normal_a = 15;
        normal_b = 1 / 3;
        eksponent_lambda = 1.5;
        puasson_l = 0.2;
        erlang_l = 3;
        erlang_lambda = 0.5;
        rand = new Random();

        taskName = "-";
        Tmodel = 0;
        L1 = 0;
        L2 = 0;
        h = 501;
        TEnd = 500.00;
        s = 0;
        q = 0;

        log = new ArrayList<ModelOutput>();
    }

    public void insertIntoQueue(String name, double rand) {
        queueTaskName.add(name);
        queueTaskRand.add(rand);
    }

    public int queueSize() {
        return queueTaskName.size();
    }

    public void remFirstFromQueue() {
        queueTaskName.remove(0);
        queueTaskRand.remove(0);
    }

    public String getTaskFromQueueName() {
        return queueTaskName.get(0);
    }

    public static double minValue(double a, double b) {
        return Math.min(a, b);
    }

    public void showReport() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        System.out.println(taskName + "\t" + df.format(Tmodel) + "\t\t" + df.format(L1) + "\t\t" + df.format(L2)
                + "\t\t" + df.format(h) + "\t\t" + df.format(TEnd) + "\t" + df.format(s) + "\t" + df.format(q));
    }

    public void insertIntoList(String taskName, double modelTime, double l1, double l2,
                               double h, double tend, int serverStatus, int lifoStatus) {
        log.add(new ModelOutput(taskName, Tmodel, L1, L2, h, TEnd, s, q));
    }

    public double getRand() {
        double generated = rand.nextDouble();
        return generated;
    }

    public double normal() {
        double z = 0, numbers = 0;
        for (int i = 0; i < normal_n; i++) {
            numbers = numbers + getRand();
        }
        z = (numbers - normal_n / 2) / Math.sqrt(normal_n / 15);
        double x = z * normal_b + normal_a;
        if (x < 0) {
            x = normal();
        }
        return round(x);
    }

    public double eksponent() {
        double value = -(1.0 / eksponent_lambda) * Math.log(1 - getRand());
        return round(value);
    }

    public double eksponent(double lambda) {
        double value = -(1.0 / lambda) * Math.log(1 - getRand());
        return value;
    }

    public double puasson() {
        double value = eksponent(puasson_l);
        return round(value);
    }

    public double erlang() {
        double value = 0;
        for (int i = 0; i < erlang_l; i++) {
            value += eksponent(erlang_lambda);
        }
        return round(value);
    }

    public double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) throws InterruptedException {
        ModelActivation model = new ModelActivation();
        queueTaskName = new ArrayList<String>();
        queueTaskRand = new ArrayList<Double>();
        L1 = model.erlang();
        L2 = model.puasson();
        model.showReport();
        model.insertIntoList(taskName, Tmodel, L1, L2, h, TEnd, s, q);
        double averageTimeInqueue = 0;
        System.out.println("Task \t Tm \t L1 \t L2 \t h \t Tend \t S \t Q");
        while (Tmodel <= TEnd) {
            i++;
            Tmodel = minValue(L1, L2);
            if (Tmodel == L1) {
                if (s == 0) {
                    s = 1;
                    h = Tmodel + model.round(model.normal());
                    taskName = "L1";
                    q = model.queueSize();
                }
                if (s == 1) {
                    model.insertIntoQueue("L1", L1);
                    q = model.queueSize();
                }
                L1 = Tmodel + model.round(model.erlang());
            } else if (Tmodel == L2) {
                if (s == 0) {
                    s = 1;
                    h = Tmodel + model.round(model.eksponent());
                    taskName = "L2";
                    q = model.queueSize();
                }
                if (s == 1) {
                    model.insertIntoQueue("L2", L2);
                    q = model.queueSize();
                }
                L2 = Tmodel + model.round(model.puasson());
            }
            if (Tmodel >= h) {
                if (q == 0) {
                    h = 501;
                    s = 0;
                } else {
                    if (q != 0) {
                        if (model.getTaskFromQueueName().equalsIgnoreCase("L1")) {
                            h = Tmodel + model.normal();
                            taskName = "L1";
                            model.remFirstFromQueue();
                            q = model.queueSize();
                        } else if (model.getTaskFromQueueName().equalsIgnoreCase("L2")) {
                            h = Tmodel + model.eksponent();
                            taskName = "L2";
                            model.remFirstFromQueue();
                            q = model.queueSize();
                        }
                    }
                }
            }
            model.showReport();
            model.insertIntoList(taskName, Tmodel, L1, L2, h, TEnd, s, q);
        }
        double time = 500;
        averageTimeInqueue = time / (i - q);
        System.out.println("queue = " + q);
        System.out.println("Number of Tasks = " + i);
        System.out.println("Average time in a queue = " + averageTimeInqueue);
    }
}
