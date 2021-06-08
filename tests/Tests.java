package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import calc.util.*;
import calc.*;

public class Tests
{
    /*********************/
    /* isCellNameValid() */
    /*********************/
    @Test
    public void cellNameContainsSpace()
    {
        assertTrue(!CellName.isCellNameValid("E 1"));
    }

    @Test
    public void cellNameContainsSmall()
    {
        assertTrue(!CellName.isCellNameValid("a1"));
    }

    @Test
    public void BigLetterWrongNumber()
    {
        assertTrue(!CellName.isCellNameValid("a1s5"));
    }

    @Test
    public void LargeLetterLessThenTen()
    {
        assertTrue(CellName.isCellNameValid("A5"));
    }

    @Test
    public void LargeLetterLetterMoreTahnNine()
    {
        assertTrue(CellName.isCellNameValid("S5"));
    }

    /*****************************/
    /* getRowIndexFromCellName() */
    /*****************************/

    @Test
    public void LessThanTen()
    {
        try
        {
            assertEquals(5, CellName.getRowIndexFromCellName("S5"));
        }
        catch(SheetException e) { }
    }

    @Test
    public void MoreThanTen()
    {
        try
        {
            assertEquals(50, CellName.getRowIndexFromCellName("S50"));
        }
        catch(SheetException e) { }
    }

    /*****************************/
    /* getColIndexFromCellName() */
    /*****************************/

    @Test
    public void AisZero()
    {
        try
        {
            assertEquals(0, CellName.getColIndexFromCellName("A10"));
        }
        catch(SheetException e) { }
    }

    @Test
    public void BisOne()
    {
        try
        {
            assertEquals(1, CellName.getColIndexFromCellName("B10"));
        }
        catch(SheetException e) { }
    }

    /**************/
    /* 2. feladat */
    /**************/

    @Test
    public void NumX()
    {
        try
        {
            Num n = new Num(5);
            assertEquals(5, n.eval(null));
        }
        catch (SheetException e) {}
    }

    @Test(expected = IllegalArgumentException.class)
    public void EqTest()
    {
        Equation eq = new Equation("A1/ B2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void EqTestSmallLetter()
    {
        Equation eq = new Equation("a1/B2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void EqTestSmallUnknown()
    {
        Equation eq = new Equation("a1~B2");
    }

    /***********/
    /* Kinézet */
    /***********/

    @Test
    public void SheetLooks()
    {
        Sheet sh = new Sheet(3, 2);

        sh.insertToSheet("A0", new Num(6));
        sh.insertToSheet("A1", new Num(2));
        sh.insertToSheet("A2", new Num(2));
        sh.insertToSheet("B0", new Num(5));
        sh.insertToSheet("B1", new Num(6));
        sh.insertToSheet("B2", new Num(9));

        String expected = "6 5" + System.lineSeparator() + "2 6" + System.lineSeparator() + "2 9";

        assertEquals(expected, sh.toString());
    }

    @Test
    public void EquationTestAdd()
    {
        Sheet sh = new Sheet(3, 3);

        sh.insertToSheet("A0", new Num(6));
        sh.insertToSheet("A1", new Num(2));
        sh.insertToSheet("A2", new Num(2));
        sh.insertToSheet("B0", new Num(5));
        sh.insertToSheet("B1", new Num(6));
        sh.insertToSheet("B2", new Num(9));

        sh.insertToSheet("C0", new Equation("A0+B0"));
        sh.insertToSheet("C1", new Equation("A1+B1"));
        sh.insertToSheet("C2", new Equation("A2+B2"));

        String expected = "6 5 11" + System.lineSeparator() + "2 6 8" + System.lineSeparator() + "2 9 11";

        assertEquals(expected, sh.toString());
    }

    @Test
    public void EquationTestDivide()
    {
        Sheet sh = new Sheet(3, 4);

        sh.insertToSheet("A0", new Num(6));
        sh.insertToSheet("A1", new Num(2));
        sh.insertToSheet("A2", new Num(2));
        sh.insertToSheet("B0", new Num(5));
        sh.insertToSheet("B1", new Num(6));
        sh.insertToSheet("B2", new Num(9));

        sh.insertToSheet("C0", new Equation("A0+B0"));
        sh.insertToSheet("C1", new Equation("A1+B1"));
        sh.insertToSheet("C2", new Equation("A2+B2"));

        sh.insertToSheet("D0", new Equation("C0/2"));
        sh.insertToSheet("D1", new Equation("C1/2"));
        sh.insertToSheet("D2", new Equation("C2/2"));

        String expected = "6 5 11 5" + System.lineSeparator() + "2 6 8 4" + System.lineSeparator() + "2 9 11 5";

        assertEquals(expected, sh.toString());
    }

    @Test
    public void EquationTestMultiple()
    {
        Sheet sh = new Sheet(3, 3);

        sh.insertToSheet("A0", new Num(6));
        sh.insertToSheet("A1", new Num(2));
        sh.insertToSheet("A2", new Num(2));
        sh.insertToSheet("B0", new Num(5));
        sh.insertToSheet("B1", new Num(6));
        sh.insertToSheet("B2", new Num(9));

        sh.insertToSheet("C0", new Equation("10*B0"));
        sh.insertToSheet("C1", new Equation("10*B1"));
        sh.insertToSheet("C2", new Equation("10*B2"));

        String expected = "6 5 50" + System.lineSeparator() + "2 6 60" + System.lineSeparator() + "2 9 90";

        assertEquals(expected, sh.toString());
    }
}