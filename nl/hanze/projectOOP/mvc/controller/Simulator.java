package nl.hanze.projectOOP.mvc.controller;

import nl.hanze.projectOOP.mvc.view.SimulatorView;
import nl.hanze.projectOOP.mvc.model.Car;
import nl.hanze.projectOOP.mvc.view.Location;
import nl.hanze.projectOOP.mvc.model.AdHocCar;
import nl.hanze.projectOOP.mvc.model.ParkingPassCar;
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

    private double moneyEarned = 0.00;
    private double moneyEarnedDay = 0.00;
    private double moneyEarnedWeek = 0.00;

    private int carsPassed = 0;
    int carCounter = 0;


    private int tickPause = 100;

    int weekDayArrivals = 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals = 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    int totalAdHocCar = 0;
    int totalParkingPassCar = 0;
    int totalReservationCar = 0;

    int carCounter = 0;

    public Simulator() {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30);
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    private void tick() {
        advanceTime();
        handleExit();
        updateViews();
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
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
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
        simulatorView.updateView();
        simulatorView.carCounter(carCounter);
        int missedCars = carsPassed;
        simulatorView.missedCars(missedCars);
    }

    private void carsArriving() {
        int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
    }

    private void carsEntering(CarQueue queue) {
        int i = 0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue() > 0 &&
                simulatorView.getNumberOfOpenSpots() > 0 &&
                i < enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = simulatorView.getFirstFreeLocation();
            simulatorView.setCarAt(freeLocation, car);
            i++;
        }
        carCounter +=i;
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
            case "nl.hanze.projectOOP.mvc.AdHocCar":
                moneyEarned += (10);
                moneyEarnedDay += (10);
                moneyEarnedWeek += (10);
                break;
            case "nl.hanze.projectOOP.mvc.ReservationCar":
                moneyEarned += (5);
                moneyEarnedDay += (5);
                moneyEarnedWeek += (5);
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

    private void addArrivingCars(int numberOfCars, String type) {
        // Add the cars to the back of the queue.
        switch (type) {
            case AD_HOC:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar());
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                }
                break;
        }
    }

    private void carLeavesSpot(Car car) {
        simulatorView.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
/**
    private void addArrivingCars(int numberOfCars, Car car) {
        // Add the cars to the back of the queue.
        switch (car.getClass().getName()) {
            case "nl.hanze.projectOOP.mvc.AdHocCar":
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar(stayLeaveModifier(true)));
                    totalAdHocCar++;
                }
                break;
            case "nl.hanze.projectOOP.mvc.ParkingPassCar":
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new ParkingPassCar(stayLeaveModifier(true)));
                    totalParkingPassCar++;
                }
                break;
            case "nl.hanze.projectOOP.mvc.ReservationCar":
                for (int i = 0; i < numberOfCars; i++) {
                    ReservationCar reservationCar = new ReservationCar(stayLeaveModifier(true));
                    moneyEarned += (((reservationCar.getMinutesLeft() / 60) * 2.50) + 5);
                    entranceCarQueue.addCar(reservationCar);
                    totalReservationCar++;
                }
                break;
        }
    }

 */
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
}
