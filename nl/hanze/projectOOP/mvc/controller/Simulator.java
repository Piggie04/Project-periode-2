package nl.hanze.projectOOP.mvc.controller;

import nl.hanze.projectOOP.mvc.view.SimulatorView;
import nl.hanze.projectOOP.mvc.model.Location;
import nl.hanze.projectOOP.mvc.model.ParkingPassCar;
import nl.hanze.projectOOP.mvc.model.Car;
import nl.hanze.projectOOP.mvc.model.AdHocCar;
import nl.hanze.projectOOP.mvc.model.ReservationCar;
import nl.hanze.projectOOP.mvc.model.CarQueue;
import java.util.Random;

public class Simulator {

	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	
	
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private SimulatorView simulatorView;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    double priceCarparking = 1.10;

    int totalParkingPassCar = 0;
    int totalReservationCar = 0;
    int totalAdHocCar = 0;

    int carsPassed = 0;


    double moneyEarned = 0.00;
    double moneyEarnedDay = 0.00;
    double moneyEarnedWeek = 0.00;

    int carCounter = 0;

    private int tickPause = 100;

    String[] daysoftheWeek = {"Maandag","Dinsdag","Woensdag","Donderdag","Vrijdag","Zaterdag","Zondag"};

    private int weekDayArrivals = 100; // average number of arriving cars per hour
    private int weekendArrivals = 150; // average number of arriving cars per hour
    private int weekDayPassArrivals = 30 ; // average number of arriving cars per hour
    private int weekendPassArrivals = 10;// average number of arriving cars per hour
    private int weekDayReservationArrival = 50;
    private int weekendReservationArrival = 150;

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute


    public Simulator() {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30);
    }
    public void run(){
        for(int i = 0; i<100000; i++){
            tick();
        }
    }
    /*
    public void playPause() {
        isRunning = (!isRunning) ? true : false;

        simulatorView.updatePlayPauseButton(isRunning);

        while(isRunning) {
            tick();
        }
    }
*/
    private void tick() {
        advanceTime();
        handleExit();
        updateViews();
        simulatorView.getCars(totalAdHocCar, totalParkingPassCar, totalReservationCar);
        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handleEntrance();
    }

    private void advanceTime() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
            moneyEarned = 0;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
            moneyEarnedDay = 0;
        }
        while (day > 6) {
            day -= 7;
            CarPayment(0,new ParkingPassCar(stayLeave(true)));
            moneyEarnedWeek = 0;
        }

    }

    private void handleExit() {
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    private void updateViews() {
        simulatorView.tick();
        // Update the car park view.
        simulatorView.updateView();
        //update the graph
        simulatorView.carCounter(carCounter);
        int missedCars = carsPassed;
        simulatorView.missedCars(missedCars);
    }

    private void carsArriving() {
        int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, new AdHocCar(stayLeave(true)));
        numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, new ParkingPassCar(stayLeave(true)));
        numberOfCars=getNumberOfCars(weekDayReservationArrival, weekendReservationArrival);
        addArrivingCars(numberOfCars, new ReservationCar(stayLeave(true)));
    }

    private void carsEntering(CarQueue queue) {
        int i = 0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue() > 0 &&
                simulatorView.getNumberOfOpenSpots() > 0 &&
                i < enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = simulatorView.getSpecifiedCarTypeLocation(car);
            simulatorView.setCarAt(freeLocation, car);
            i++;
        }
    }

    private void carsReadyToLeave() {
        // Add leaving cars to the payment queue.
        Car car = simulatorView.getFirstLeavingCar();
        while (car != null) {
            if (car.getHasToPay()) {
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            } else {
                carLeavesSpot(car);
            }
            car = simulatorView.getFirstLeavingCar();
        }
    }

    private void carsPaying() {
        // Let cars pay.
        int i = 0;
        while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed) {
            Car car = paymentCarQueue.removeCar();
            CarPayment(car.getMinutesStayed(), car);
            carLeavesSpot(car);
            i++;
    	}
    }

    private void CarPayment(int timeStayed, Car car) {
        switch (car.getClass().getName()) {
            case "nl.hanze.projectOOP.mvc.ParkingPassCar":
                moneyEarned += (40.00);
                moneyEarnedDay += (40.00);
                moneyEarnedWeek += (40.00);
                break;
            case "nl.hanze.projectOOP.mvc.ReservationCar":
                moneyEarned += (timeStayed / 60) *priceCarparking + 5;
                moneyEarnedDay += timeStayed / 60 *priceCarparking + 5;
                moneyEarnedWeek += timeStayed / 60 *priceCarparking + 5;
                break;
            case "nl.hanze.projectOOP.mvc.AdHocCar":
                moneyEarned += timeStayed/60 * priceCarparking;
                moneyEarnedDay += timeStayed/60 * priceCarparking;
                moneyEarnedWeek += timeStayed/60 * priceCarparking;
        }
    }

    private void carsLeaving() {
        // Let cars leave.
        int i = 0;
        while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
            exitCarQueue.removeCar();
            i++;
    	}
        carCounter -=i;
    }

    private int getNumberOfCars(int weekDay, int weekend) {
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int) Math.round(numberOfCarsPerHour / 60);
    }


    private void carLeavesSpot(Car car) {
        simulatorView.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    private void addArrivingCars(int numberOfCars, Car car) {
        // Add the cars to the back of the queue.
        switch (car.getClass().getName()) {
            case "nl.hanze.projectOOP.mvc.AdHocCar":
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar(stayLeave(true)));
                    totalAdHocCar++;
                }
                break;
            case "nl.hanze.projectOOP.mvc.ParkingPassCar":
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new ParkingPassCar(stayLeave(true)));
                    totalParkingPassCar++;
                }
                break;
            case "nl.hanze.projectOOP.mvc.ReservationCar":
                for (int i = 0; i < numberOfCars; i++) {
                    ReservationCar reservationCar = new ReservationCar(stayLeave(true));
                    moneyEarned += (((reservationCar.getMinutesLeft() / 60) * 2.50) + 5);
                    entranceCarQueue.addCar(reservationCar);
                    totalReservationCar++;
                }
                break;
        }
    }

    private void handleEntrance() {
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);

        if (entranceCarQueue.carsInQueue() >= 10) {
            double i = Math.random();

            if (i < 0.5) {
                carsPassed++;

                entranceCarQueue.removeCar();
            }
        }

    }

    public double stayLeave(boolean carArriving) {
        double drukte = 0;
        double rand = Math.random();
        if (carArriving) {
            switch (hour) {
                case 0:
                    drukte = Math.random() * 1.0 + 0;
                    break;
                case 1:
                    drukte = Math.random() * 0.5 + 0;
                    break;
                case 2:
                    drukte = Math.random() * 0.5 + 0;
                    break;
                case 3:
                    drukte = Math.random() * 0 + 0;
                    break;
                case 4:
                    drukte = Math.random() * 0 + 0;
                    break;
                case 5:
                    drukte = Math.random() * 1 + 0.5;
                    break;
                case 6:
                    drukte = Math.random() * 2.0 + 0.5;
                    break;
                case 7:
                    drukte = Math.random() * 5.0 + 1.5;
                    break;
                case 8:
                    drukte = Math.random() * 8.0 + 4.0;
                    break;
                case 9:
                    drukte = Math.random() * 9.0 + 3.0;
                    break;
                case 10:
                    drukte = Math.random() * 7.0 + 0.5;
                    break;
                case 11:
                    drukte = Math.random() * 6.0 + 0.5;
                    break;
                case 12:
                    drukte = Math.random() * 8.0 + 0.5;
                    break;
                case 13:
                    drukte = Math.random() * 9.0 + 0.5;
                    break;
                case 14:
                    drukte = Math.random() * 10.0 + 0.5;
                    break;
                case 15:
                    drukte = Math.random() * 8.0 + 0.5;
                    break;
                case 16:
                    drukte = Math.random() * 4.0 + 2.0;
                    break;
                case 17:
                    drukte = Math.random() * 4.0+ 0.5;
                    break;
                case 18:
                    drukte = Math.random() * 5.0 + 0.5;
                    break;
                case 19:
                    drukte = Math.random() * 8.0 + 3.0;
                    break;
                case 20:
                    drukte = Math.random() * 3.0 + 0;
                    break;
                case 21:
                    drukte = Math.random() * 4.0 + 0;
                    break;
                case 22:
                    drukte = Math.random() * 2.0 + 0;
                    break;
                case 23:
                    drukte = Math.random() * 1.0 + 0;
                    break;
            }
        } else {
            switch (hour) {
                case 0:
                    drukte = Math.random() * 0.5 + 0;
                    break;
                case 1:
                    drukte = Math.random() * 0.2 + 0;
                    break;
                case 2:
                    drukte = Math.random() * 0.2 + 0;
                    break;
                case 3:
                    drukte = Math.random() * 0.2 + 0;
                    break;
                case 4:
                    drukte = Math.random() * 0.2 + 0;
                    break;
                case 5:
                    drukte = Math.random() * 0.2 + 0;
                    break;
                case 6:
                    drukte = Math.random() * 0.4 + 0;
                    break;
                case 7:
                    drukte = Math.random() * 0.9 + 0;
                    break;
                case 8:
                        drukte = Math.random() * 1.5 + 0.5;
                    break;
                case 9:
                    drukte = Math.random() * 1.5 + 0.6;
                    break;
                case 10:
                    drukte = Math.random() * 1.7 + 1.1;
                    break;
                case 11:
                    drukte = Math.random() * 1.9 + 0.9;
                    break;
                case 12:
                    drukte = Math.random() * 1.5 + 1.4;
                    break;
                case 13:
                    drukte = Math.random() * 1.9 + 0.5;
                    break;
                case 14:
                    drukte = Math.random() * 1.1 + 0.5;
                    break;
                case 15:
                    drukte = Math.random() * 1.2 + 0.5;
                    break;
                case 16:
                    drukte = Math.random() * 1.2 + 0.8;
                    break;
                case 17:
                    if (daysoftheWeek[day].equals("Thursday")) {
                        drukte = Math.random() * 1.7 + 0.8;
                    } else {
                        drukte = Math.random() * 1.4 + 0.7;
                    }
                    break;
                case 18:
                    if (daysoftheWeek[day].equals("Thursday")) {
                        drukte = Math.random() * 2.0 + 1.5;
                    } else {
                        drukte = Math.random() * 1.5 + 0.9;
                    }
                    break;
                case 19:
                    if (daysoftheWeek[day].equals("Thursday")) {
                        drukte = Math.random() * 1.7 + 1.3;
                    } else {
                        drukte = Math.random() * 1.5 + 0.7;
                    }
                    break;
                case 20:
                    if (daysoftheWeek[day].equals("Thursday")) {
                        drukte = Math.random() * 1.9 + 1.2;
                    } else if (daysoftheWeek[day].equals("Friday")) {
                        drukte = Math.random() * 2.1 + 1.5;
                    } else {
                        drukte = Math.random() * 1.3 + 0.7;
                    }
                    break;
                case 21:
                    if(daysoftheWeek[day].equals("Thursday")){
                        drukte = Math.random() * 1.5 + 1.1;
                    }
                    else if (daysoftheWeek[day].equals("Friday")) {
                        drukte = Math.random() * 1.5 + 1;
                    } else {
                        drukte = Math.random() * 0.9 + 0.6;
                    }
                    break;
                case 22:
                    drukte = Math.random() * 0.6 + 0.3;
                    break;
                case 23:
                    drukte = Math.random() * 0.5 + 0.2;
                    break;

            }
        }
        return drukte;
    }
}
