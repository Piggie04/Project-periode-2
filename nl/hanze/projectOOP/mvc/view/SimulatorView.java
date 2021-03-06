package nl.hanze.projectOOP.mvc.view;
import nl.hanze.projectOOP.mvc.model.Location;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import nl.hanze.projectOOP.mvc.model.Car;
import javax.swing.*;
import java.awt.*;
import org.jfree.data.general.DefaultPieDataset;

public class SimulatorView extends JFrame {
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private Car[][][] cars;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel startKnop;
    private javax.swing.JPanel lengteQueue;
    private javax.swing.JPanel opbrengst;
    private javax.swing.JPanel parkeergarage;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JPanel tellers;
    private javax.swing.JPanel verlies;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;

    DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
    DefaultPieDataset dataset2 = new DefaultPieDataset( );
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    private int adHocCar = 0;
    private int parkingCar = 0;
    private int reservationCar = 0;
    public int number;
    private double[] winst = {0.00,0.00,0.00,0.00,0.00,0.00,0.00};
    private String dagen = "";

    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        carParkView = new CarParkView();

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(false);
        initComponents();
        setVisible(true);
        dataset.setValue(0, "Mensen in de rij", "Bezoekers");
        JFreeChart chart = ChartFactory.createBarChart("Lengte Queue", "Soort auto", "Aantal", dataset, PlotOrientation.VERTICAL, true, true, false);
        jPanel1.setLayout(new java.awt.BorderLayout());
        ChartPanel CP = new ChartPanel(chart);
        CP.setPreferredSize(new Dimension(550,350));
        jPanel1.add(CP);

        dataset1.setValue(0, "Dag", "Maandag");
        dataset1.setValue(0, "Dag", "Dinsdag");
        dataset1.setValue(0, "Dag", "Woensdag");
        dataset1.setValue(0, "Dag", "Donderdag");
        dataset1.setValue(0, "Dag", "Vrijdag");
        dataset1.setValue(0, "Dag", "Zaterdag");
        dataset1.setValue(0, "Dag", "Zondag");
        JFreeChart chart1 = ChartFactory.createLineChart("Opbrengst", "Dag", "Aantal", dataset1, PlotOrientation.VERTICAL, true, true, true);
        jPanel2.setLayout(new java.awt.BorderLayout());
        ChartPanel CP1 = new ChartPanel(chart1);
        CP1.setPreferredSize(new Dimension(550,350));
        jPanel2.add(CP1);


        int empty = (540 - adHocCar - parkingCar - reservationCar);

        dataset2.setValue( "Normaal" , 0);
        dataset2.setValue( "Abonnement" , 0);
        dataset2.setValue( "Reservering" , 0);
        dataset2.setValue("Leeg", empty);
        JFreeChart chart2 = ChartFactory.createPieChart("Soort auto's", dataset2, true, true, true);
        jPanel4.setLayout(new java.awt.BorderLayout());
        ChartPanel CP2 = new ChartPanel(chart2);
        CP2.setPreferredSize(new Dimension(550,350));
        jPanel4.add(CP2);

        jPanel1.setVisible(true);
        jPanel2.setVisible(true);
        jPanel4.setVisible(true);
    }

    private void updateViewer(){
        dataset.setValue(number, "Mensen in de rij", "Bezoekers");

        dataset1.setValue(winst[0], "Dag", "Maandag");
        dataset1.setValue(winst[1], "Dag", "Dinsdag");
        dataset1.setValue(winst[2], "Dag", "Woensdag");
        dataset1.setValue(winst[3], "Dag", "Donderdag");
        dataset1.setValue(winst[4], "Dag", "Vrijdag");
        dataset1.setValue(winst[5], "Dag", "Zaterdag");
        dataset1.setValue(winst[6], "Dag", "Zondag");


        int empty = (540 - adHocCar - parkingCar - reservationCar);

        dataset2.setValue( "Normaal" , adHocCar);
        dataset2.setValue( "Abonnement" , parkingCar);
        dataset2.setValue( "Reservering" , reservationCar);
        dataset2.setValue("Leeg", empty);

        jPanel1.setVisible(true);
        jPanel2.setVisible(true);
        jPanel4.setVisible(true);
    }

    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        lengteQueue = new javax.swing.JPanel();
        startKnop = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        opbrengst = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        verlies = new javax.swing.JPanel();
        parkeergarage = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        tellers = new javax.swing.JPanel();
        jTable1 = new javax.swing.JTable();
        jSpinner1 = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Start");
        jButton2.setText("Stop");
        jLabel2.setText(dagen);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addComponent(jLabel2))
                                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout startknopLayout = new javax.swing.GroupLayout(startKnop);
        startKnop.setLayout(startknopLayout);
        startknopLayout.setHorizontalGroup(
                startknopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(100,100,100)
                        .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        startknopLayout.setVerticalGroup(
                startknopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );

        tabPanel.addTab("Start Knop", startKnop);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(850, 850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(350, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout lengteQueueLayout = new javax.swing.GroupLayout(lengteQueue);
        lengteQueue.setLayout(lengteQueueLayout);
        lengteQueueLayout.setHorizontalGroup(
                lengteQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        lengteQueueLayout.setVerticalGroup(
                lengteQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );

        tabPanel.addTab("Lengte Queue", lengteQueue);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(850, 850, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(350, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout opbrengstLayout = new javax.swing.GroupLayout(opbrengst);
        opbrengst.setLayout(opbrengstLayout);
        opbrengstLayout.setHorizontalGroup(
                opbrengstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)        );
        opbrengstLayout.setVerticalGroup(
                opbrengstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );

        tabPanel.addTab("Opbrengst", opbrengst);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(850, 850, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(350, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout verliesLayout = new javax.swing.GroupLayout(verlies);
        verlies.setLayout(verliesLayout);
        verliesLayout.setHorizontalGroup(
                verliesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        verliesLayout.setVerticalGroup(
                verliesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );

        tabPanel.addTab("Soort auto's", verlies);

        parkeergarage.setBackground(new java.awt.Color(200, 200, 200));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(850, 850, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(350, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout parkeergarageLayout = new javax.swing.GroupLayout(parkeergarage);
        parkeergarage.setLayout(parkeergarageLayout);
        parkeergarageLayout.setHorizontalGroup(
                parkeergarageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );
        parkeergarageLayout.setVerticalGroup(
                parkeergarageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tellers.setBackground(new java.awt.Color(200, 200, 200));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null}
                },
                new String [] {
                        "Aantal auto's", "Misgelopen auto's", "Misgelopen opbrengst", "Opbrengst"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);


        javax.swing.GroupLayout tellersLayout = new javax.swing.GroupLayout(tellers);
        tellers.setLayout(tellersLayout);
        tellersLayout.setHorizontalGroup(
                tellersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tellersLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        ));
        tellersLayout.setVerticalGroup(
                tellersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tellersLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(parkeergarage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabPanel))
                        .addComponent(tellers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(parkeergarage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tabPanel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tellers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>



    public void carCounter(int counter) {
        jTable1.setValueAt(counter, 0, 0);
    }

    public void missedCars(int counter) {
        jTable1.setValueAt(counter, 0, 1);
    }

    public void opbrengst(double counter) {
        counter = Math.round(counter);
        jTable1.setValueAt(counter,0,3);
    }
    public void missedOpbrengst(int auto) {
        double counter = auto * 3;
        counter = Math.round(counter);
        jTable1.setValueAt(counter, 0,2);
    }

    public void updateView() {
        carParkView.updateView();
        updateViewer();
    }

    public void dagenTeller(int day,int hour,int minute) {
        String dag = "";
        String minuut;
        String uur;
        switch(day){
            case(0):
                dag = "Maandag";
                break;
            case(1):
                dag = "Dinsdag";
                break;
            case(2):
                dag = "Woensdag";
                break;
            case(3):
                dag = "Donderdag";
                break;
            case(4):
                dag = "Vrijdag";
                break;
            case(5):
                dag = "Zaterdag";
                break;
            case(6):
                dag = "Zondag";
                break;
        }
        uur = Integer.toString(hour);
        minuut = Integer.toString(minute);
        dagen = dag + " " + uur + ":" + minuut;
        jLabel2.setText(dagen);
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    public void getCars(int totalAdHocCar, int totalParkingPassCar, int totalReservationCar)
    {
        this.adHocCar = totalAdHocCar;
        this.parkingCar = totalParkingPassCar;
        this.reservationCar  = totalReservationCar;
    }
    public void getWinstPerDag(double[] winst){
        for(int i = 0; i < 7; i++){
            this.winst[i] = winst[i];
        }
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public Location getSpecifiedLocation()
    {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;

    }

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }
    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    private class CarParkView extends JPanel {

        private Dimension size;
        private Image carParkImage;

        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView() {
            size = new Dimension(0, 0);
        }

        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(850, 375);
        }

        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }

            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }

        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = car == null ? Color.white : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();
        }

        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    19,
                    9); // TODO use dynamic size or constants
        }
    }

}
