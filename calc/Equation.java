package calc;

import calc.util.CellName;
import calc.util.SheetException;
import calc.*;

public class Equation implements Evaluable
{
    private String operand1, operand2;
    private Character operator;

    public Equation(String str)
    {   
        for(int i = 0; i < str.length(); i++)
        {
            if(Character.isWhitespace(str.charAt(i))) throw new IllegalArgumentException();
            if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/')
            {
                operator = str.charAt(i);
            }
        }

        if(operator == null) throw new IllegalArgumentException();

        String[] splits = str.split("\\" + operator);

        if(Character.isLowerCase(splits[0].charAt(0))) throw new IllegalArgumentException();
        if(Character.isLowerCase(splits[1].charAt(0))) throw new IllegalArgumentException();

        this.operand1 = splits[0];
        this.operand2 = splits[1];
    }

    @Override
    public int eval(Sheet sh) throws SheetException
    {
        int op1 = Sheet.constructIntFromOperandStr(operand1, sh);
        int op2 = Sheet.constructIntFromOperandStr(operand2, sh);

        if(operator == '+') {
            return op1 + op2;
        }
        else if (operator == '-')
        {
            if(op1 - op2 < 0) throw new ArithmeticException();
        } 
        else if(operator == '*')
        {
            return op1 * op2;
        }
        else
        {
            if(op2 == 0) throw new ArithmeticException();
            return op1 / op2;
        }

        return 0;
    }
} 