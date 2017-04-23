package myImpl;

/**
 * Created by DocDVZ on 24.04.2017.
 */
public class TimeQuant {

    private int currentTime = 0;

    public void next(){
        currentTime++;
    }

    public int getCurrentTime() {
        return currentTime;
    }
}
