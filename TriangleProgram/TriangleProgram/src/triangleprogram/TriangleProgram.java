/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangleprogram;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author micha
 */
public class TriangleProgram {

    /**
     * 
     * @return 
     */
    public double[] getArr() {
        return arr;
    }
    
    /**
     * 
     * @param arr 
     */
    public void setArr(double[] arr) {
        this.arr = arr;
    }
    
    /**
     * 
     * @return 
     */
    public String getTriangle() {
        return triangle;
    }
    
    /**
     * 
     * @param triangle 
     */
    public void setTriangle(String triangle) {
        this.triangle = triangle;
    }

    /**
     * 
     * @return 
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @param errorMessage 
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private double[] arr;
    private String triangle;
    private boolean isRunning;
    private static final Logger LOGGER = Logger.getLogger(TriangleProgram.class.getName());
    private final FileHandler fileHandle;
    private final SimpleFormatter simpleFormat;
    private String errorMessage;

    /**
     * 
     * @throws IOException 
     */
    public TriangleProgram() throws IOException {
        fileHandle = new FileHandler();
        simpleFormat = new SimpleFormatter();
    }

    /**
     * @param simpleFor
     * @param fileHandler
     * @param logger
     */
    public TriangleProgram(SimpleFormatter simpleFor, FileHandler fileHandler, Logger logger) {
        isRunning = true;
        this.fileHandle = fileHandler;
        this.simpleFormat = simpleFor;
        logger.addHandler(fileHandle);
        fileHandler.setFormatter(simpleFormat);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Path to the log file
        String path = TriangleProgram.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1);
        path = path.substring(0, path.length() - 14);

        /*Settinng up a logger, formatter and a filehandler with the given path so it can log errors
        to the file called "Log.txt"*/
        FileHandler fileHandler;
        SimpleFormatter simpleFormatter;
        TriangleProgram triangleProgram;
        try {
            fileHandler = new FileHandler(path + "\\Log.txt");
            simpleFormatter = new SimpleFormatter();
            triangleProgram = new TriangleProgram(simpleFormatter, fileHandler, LOGGER);
            triangleProgram.inputHandler();
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(TriangleProgram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     */
    public void inputHandler() {
        Scanner scan = new Scanner(System.in, "UTF-8");
        arr = new double[3];
        while (isRunning) {
            System.out.println("To enter values to you triangle type 1");
            System.out.println("To exit the program type 2");
            try {
                arr[0] = scan.nextInt();
                if (arr[0] == 1) {
                    for (int i = 0; i < arr.length;) {
                        System.out.println("Please enter a value for your triangle");
                        arr[i] = scan.nextDouble();
                        if (arr[i] < 0) {
                            System.out.println("!Invalid value please type a positive number");
                        } else {
                            i++;
                        }
                    }
                    calcTriangle();
                } else if (arr[0] == 2) {
                    System.out.println("Closing the program, goodbye");
                    isRunning = false;
                } else if (arr[0] > 2 || arr[0] < 0) {
                    System.out.println("!Invalid value please try again");
                }
            } catch (InputMismatchException ex) {
                errorMessage = "!Invalid error detected. You got a ";
                System.out.println(errorMessage + ex);
                LOGGER.log(Level.INFO, "{0}{1}", new Object[]{errorMessage, ex});
                System.out.println("Please try again");
                scan = new Scanner(System.in, "UTF-8");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException exc) {
                    LOGGER.log(Level.INFO, "{0}{1}", new Object[]{errorMessage, exc});
                }
            }
        }
    }

    public void calcTriangle() {
        if (arr[0] == arr[1] && arr[0] == arr[2]) {
            triangle = "equilateral triangle";
        } else if (arr[0] == arr[1] || arr[0] == arr[2] || arr[1] == arr[2]) {
            triangle = "isosceles triangle";
        } else {
            triangle = "scalene triangle";
        }
        System.out.println("--> Your triangle is: " + triangle + " with the sides; " + arr[0] + ", " + arr[1]+ ", " + arr[2]);
        arr[0] = 0;
    }
}
