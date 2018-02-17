/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangleprogram;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import java.lang.NumberFormatException;
import static org.junit.Assert.*;

/**
 *
 * @author micha
 */
public class TriangleProgramTest {

    private static TriangleProgram tp;

    public TriangleProgramTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        tp = new TriangleProgram();

    }

    @Test
    public void testPositiveNumber() {
        System.out.println("Testing for positive numbers");
        String str = "scalene triangle";
        double[] arr = new double[3];
        arr[0] = 5;
        arr[1] = 4;
        arr[2] = 3;
        tp.setArr(arr);
        tp.calcTriangle();
        assertEquals(tp.getTriangle(), str);
    }

    @Test
    public void testNegativeNumber() {
        System.out.println("Testing for negative number");
        String str = "!Invalid value please type a positive number";
        double[] arr = new double[3];
        arr[0] = 5;
        arr[1] = -4;
        arr[2] = 3;
        tp.setArr(arr);
        tp.calcTriangle();
        assertNotEquals(tp.getTriangle(), str);
    }

    @Test(expected = NumberFormatException.class)
    public void testTextInput() {
        System.out.println("Testing for text input");
        String str = "!Invalid error detected. You got a ";
        String textString = "text";
        double text = Double.parseDouble(textString);
        double[] arr = new double[3];
        arr[0] = text;
        arr[1] = 3;
        arr[2] = 5;
        tp.inputHandler();
        assertEquals(tp.getErrorMessage(), arr);
    }

    @Test
    public void getScaleneTriangle() {
        System.out.println("Testing for Scalene triangle output");
        String str = "scalene triangle";
        double[] arr = new double[3];
        arr[0] = 6.3;
        arr[1] = 5.5;
        arr[2] = 3;
        tp.setArr(arr);
        tp.calcTriangle();
        assertEquals(tp.getTriangle(), str);
    }

    @Test
    public void getEquilateralTriangle() {
        System.out.println("Testing for equilateral triangle output");
        String str = "equilateral triangle";
        double[] arr = new double[3];
        arr[0] = 3;
        arr[1] = 3;
        arr[2] = 3;
        tp.setArr(arr);
        tp.calcTriangle();
        assertEquals(tp.getTriangle(), str);
    }

    @Test
    public void getIsoscelesTriangle() {
        System.out.println("Testing for isosceles triangle output");
        String str = "isosceles triangle";
        double[] arr = new double[3];
        arr[0] = 5;
        arr[1] = 5;
        arr[2] = 3;
        tp.setArr(arr);
        tp.calcTriangle();
        assertEquals(tp.getTriangle(), str);
    }
}
