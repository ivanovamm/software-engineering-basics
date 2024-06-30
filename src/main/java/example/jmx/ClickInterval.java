package example.jmx;

import java.util.LinkedList;
import java.util.Queue;

public class ClickInterval implements ClickIntervalMBean {
    private long lastTimestamp = 0;
    private long totalInterval = 0;
    private int clickCount = 0;
    private final int maxClicksToStore = 100;
    private Queue<Long> clickIntervals = new LinkedList<>();


    @Override
    public void addClick(long timestamp) {
        if (lastTimestamp != 0) {
            long interval = timestamp - lastTimestamp;
            clickIntervals.add(interval);
            totalInterval += interval;

            if (clickIntervals.size() > maxClicksToStore) {
                totalInterval -= clickIntervals.poll();
            }
        }
        lastTimestamp = timestamp;
        clickCount++;
    }
}
