package model;

/**
 * Created by DocDVZ on 23.04.2017.
 */
public class ModelOutput {

    String taskName;
    int rownum;
    double modelTime;
    double l1;
    double l2;
    double h;
    double tend;
    int serverStatus;
    int lifoStatus;

    public ModelOutput(String taskName, double modelTime, double l1, double
            l2, double h, double tend, int serverStatus, int lifoStatus) {
        setTaskName(taskName);
        setModelTime(modelTime);
        setL1(l1);
        setL2(l2);
        setH(h);
        setTend(tend);
        setServerStatus(serverStatus);
        setFifoStatus(lifoStatus);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public double getModelTime() {
        return modelTime;
    }

    public void setModelTime(double modelTime) {
        this.modelTime = modelTime;
    }

    public double getL1() {
        return l1;
    }

    public void setL1(double l1) {
        this.l1 = l1;
    }

    public double getL2() {
        return l2;
    }

    public void setL2(double l2) {
        this.l2 = l2;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getTend() {
        return tend;
    }

    public void setTend(double tend) {
        this.tend = tend;
    }

    public int getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getFifoStatus() {
        return lifoStatus;
    }

    public void setFifoStatus(int fifoStatus) {
        this.lifoStatus = fifoStatus;
    }
}