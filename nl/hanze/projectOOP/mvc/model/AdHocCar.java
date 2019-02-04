package nl.hanze.projectOOP.mvc;
import java.util.Random;
import java.awt.*;

public class AdHocCar extends nl.hanze.projectOOP.mvc.model.Car{
    private static final Color COLOR=Color.red;

    public AdHocCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setBill((int) ((stayMinutes / 60) * 2.50));
    }

    public Color getColor(){
        return COLOR;
    }
}
