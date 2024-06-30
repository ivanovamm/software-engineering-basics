package example.jmx;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean {
    private int totalPoints = 0;
    private int pointsInArea = 0;
    private long sequenceNumber = 1;


    @Override
    public void addPoint(double x, double y, double r) {
        totalPoints++;
        if (isInside(x, y, r)) {
            pointsInArea++;
        } else {
            Notification notification = new Notification(
                    "PointOutOfBounds",
                    this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Точка (" + x + ", " + y + ") с радиусом " + r + " находится за пределами области."
            );
            sendNotification(notification);
        }
    }

    private boolean isInside(double x, double y, double r) {
        if (x >= 0 && y >= 0) {
            return (x <= r) && (y <= r / 2);
        }
        if (x < 0 && y >= 0) {
            return (x >= -r / 2) && (y <= r);
        }
        if (x <= 0 && y <= 0) {
            return (x * x + y * y) <= (r * r);
        }
        return false;
    }
}
