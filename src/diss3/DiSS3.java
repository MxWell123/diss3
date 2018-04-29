/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diss3;

import java.util.Random;
import simulation.Mc;
import simulation.MySimulation;

/**
 *
 * @author davidecek
 */
public class DiSS3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MySimulation sim = new MySimulation();
        sim.simulate(10, 90000000d);
    }

}
