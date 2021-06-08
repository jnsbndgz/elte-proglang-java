package calc;

import calc.util.SheetException;

interface Evaluable
{
    int eval(Sheet sh) throws SheetException;
}