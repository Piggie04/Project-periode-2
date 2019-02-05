package nl.hanze.projectOOP.mvc.model;

import java.util.Random;
import java.awt.*;

public class ReservationCar extends nl.hanze.projectOOP.mvc.model.Car {
    private static final Color COLOR = Color.green;
    private int stayMinutes;

    public ReservationCar(double hourModifier) {
        Random random = new Random();
        stayMinutes = (int) (15 + random.nextFloat() * 3 + (hourModifier * 60) );
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    public Color getColor() {
        return COLOR;
    }

    @Override
    public void tick() {
        this.setMinutesLeft(this.getMinutesLeft() - 1);
        if ((stayMinutes - this.getMinutesLeft()) > 10) {
        }
    }
}