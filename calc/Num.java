package calc;

import calc.Evaluable;
import calc.util.SheetException;

public class Num implements Evaluable
{
    private int number;

    public Num(int number)
    {
        if(number < 0) throw new IllegalArgumentException();
        this.number = number;
    }

    @Override
    public int eval(Sheet sh) throws SheetException
    {
        return number;
    }
}