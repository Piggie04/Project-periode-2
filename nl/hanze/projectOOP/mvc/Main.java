package nl.hanze.projectOOP.mvc;
import nl.hanze.projectOOP.mvc.controller.Simulator;
import nl.hanze.projectOOP.mvc.view.SimulatorView;

public class Main
{
    public static void main(String[] args)
    {
        Simulator main = new Simulator();
        main.run();
    }
}