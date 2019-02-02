package nl.hanze.projectOOP.mvc.view;
import java.awt.Dimension;
import nl.hanze.projectOOP.mvc.view.SimulatorView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gebruiker
 */
public class BarGraphView extends javax.swing.JFrame {

    /**
     * Creates new form BarCharte
     */
    public BarGraphView() {
        initComponents();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(80, "B", "Bezoekers");
        dataset.setValue(10, "A", "Abonnementen");
        JFreeChart chart = ChartFactory.createBarChart("Lengte Queue", "Soort auto", "Aantal", dataset, PlotOrientation.VERTICAL, false, true, false);
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.setVisible(true);
        ChartPanel CP = new ChartPanel(chart);
        CP.setPreferredSize(new Dimension(100,200));
        jPanel1.add(CP);
        jPanel1.validate();

        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1.setValue(10, "Dag", "Maandag");
        dataset1.setValue(20, "Dag", "Dinsdag");
        dataset1.setValue(30, "Dag", "Woensdag");
        dataset1.setValue(40, "Dag", "Donderdag");
        dataset1.setValue(50, "Dag", "Vrijdag");
        dataset1.setValue(60, "Dag", "Zaterdag");
        dataset1.setValue(70, "Dag", "Zondag");
        JFreeChart chart1 = ChartFactory.createLineChart("Opbrengst", "Dag", "Aantal", dataset1, PlotOrientation.VERTICAL, true, true, true);
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.setVisible(true);
        ChartPanel CP1 = new ChartPanel(chart1);
        CP1.setPreferredSize(new Dimension(100,200));
        jPanel2.add(CP1);
        jPanel2.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        lengteQueue = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        opbrengst = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        verlies = new javax.swing.JPanel();
        parkeergarage = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tellers = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 275, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 281, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout lengteQueueLayout = new javax.swing.GroupLayout(lengteQueue);
        lengteQueue.setLayout(lengteQueueLayout);
        lengteQueueLayout.setHorizontalGroup(
                lengteQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lengteQueueLayout.setVerticalGroup(
                lengteQueueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPanel.addTab("Lengte Queue", lengteQueue);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 275, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 281, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout opbrengstLayout = new javax.swing.GroupLayout(opbrengst);
        opbrengst.setLayout(opbrengstLayout);
        opbrengstLayout.setHorizontalGroup(
                opbrengstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)        );
        opbrengstLayout.setVerticalGroup(
                opbrengstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabPanel.addTab("Opbrengst", opbrengst);

        javax.swing.GroupLayout verliesLayout = new javax.swing.GroupLayout(verlies);
        verlies.setLayout(verliesLayout);
        verliesLayout.setHorizontalGroup(
                verliesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 275, Short.MAX_VALUE)
        );
        verliesLayout.setVerticalGroup(
                verliesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 281, Short.MAX_VALUE)
        );

        tabPanel.addTab("Verlies", verlies);

        parkeergarage.setBackground(new java.awt.Color(200, 200, 200));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 275, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 281, Short.MAX_VALUE)
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

        javax.swing.GroupLayout tellersLayout = new javax.swing.GroupLayout(tellers);
        tellers.setLayout(tellersLayout);
        tellersLayout.setHorizontalGroup(
                tellersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        tellersLayout.setVerticalGroup(
                tellersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 166, Short.MAX_VALUE)
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new BarGraphView().setVisible(true);
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel lengteQueue;
    private javax.swing.JPanel opbrengst;
    private javax.swing.JPanel parkeergarage;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JPanel tellers;
    private javax.swing.JPanel verlies;
    // End of variables declaration
}