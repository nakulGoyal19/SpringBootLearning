package com.mockTest;

import com.mocking.Calculator;
import com.mocking.CalculatorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class TestCalculator {

    Calculator c = null;
    CalculatorService service = Mockito.mock(CalculatorService.class);

    @Before
    public void setUp(){
        //c=new Calculator();
        c=new Calculator(service);
        System.out.println("Starting test part ..");

    }
    @Test
    public void testPerform(){
        Mockito.when(service.add(4,9)).thenReturn(13);
        Mockito.when(service.square(2)).thenReturn(4);
        Mockito.when(service.square(3)).thenReturn(9);
        assertEquals(13,c.performSquareSum(2,3));
        Mockito.verify(service).add(4,9);
    }

    @After
    public void tearDown(){
        System.out.println("Ending test part ..");
    }

    @Test
    public void testEvaluate(){
        String s = "1+2+3+4+5+6";
        String s1 = null;
        assertEquals(21,c.evaluateAddition(s));
        assertTrue(c.evaluateAddition(s)==21);
        assertFalse(c.evaluateAddition(s)==0);
        assertNotNull(c.evaluateAddition(s));
        assertEquals(-1,c.evaluateAddition(s1));
        assertEquals(30,c.evaluateAddition("10+20"));
        assertEquals(-1,c.evaluateAddition("10+20.30"));

    }


}
