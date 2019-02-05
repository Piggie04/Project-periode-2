package nl.hanze.projectOOP.mvc.model;
import nl.hanze.projectOOP.mvc.model.Car;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;
	
    public ParkingPassCar(double hourModifier) {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 + (60 * hourModifier));
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
