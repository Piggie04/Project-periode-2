package nl.hanze.projectOOP.mvc.model;
import java.util.Random;
import java.awt.*;

public class AdHocCar extends nl.hanze.projectOOP.mvc.model.Car {
    private static final Color COLOR=Color.red;

    public AdHocCar(double hourCrowds) {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60 + (hourCrowds * 60) );
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public Color getColor(){
        return COLOR;
    }
}
