package nl.hanze.projectOOP.mvc;
public class Main
{
    public static void main(String[] args)
    {
        Simulator test1 = new Simulator();
        test1.run();
        BarGraph graph = new BarGraph();
        graph.run();
        Graph graph1 = new Graph();
        graph1.run();
    }
}