package nl.hanze.projectOOP.mvc.model;
import nl.hanze.projectOOP.mvc.view.Location;
import java.awt.*;

public abstract class Car {
    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    private String subscription;
    private int bill;

    public Car() {

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setBill(int b) {
        this.bill = b;
    }

    public int getBill() {
        return bill;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subs) {
        this.subscription = subs;
    }

    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }

    public abstract Color getColor();

}