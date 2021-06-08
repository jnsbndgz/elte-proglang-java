package calc;

import calc.*;
import calc.util.CellName;
import calc.util.SheetException;

public class Sheet
{
    private int numOfRows, numOfCols;
    private Evaluable[][] sheet;

    public Sheet(int numOfRows, int numOfCols)
    {
        if(numOfCols > CellName.getMaxCol() || numOfCols < 0) throw new IllegalArgumentException();
        sheet = new Evaluable[numOfRows][numOfCols];
        this.numOfRows = numOfRows;
        this.numOfCols = numOfCols;
    }

    public void insertToSheet(String cellName, Evaluable ev)
    {
        try
        {
            sheet[CellName.getRowIndexFromCellName(cellName)][CellName.getColIndexFromCellName(cellName)] = ev;
        }
        catch(SheetException e)
        {
            System.out.println(e);
        }
        
    }

    public Evaluable getFromSheet(String cellName)
    {
        try
        {
            return sheet[CellName.getRowIndexFromCellName(cellName)][CellName.getColIndexFromCellName(cellName)];
        }
        catch(SheetException e)
        {
            System.out.println(e);
            return null;
        }
    }

    public static int constructIntFromOperandStr(String operandStr, Sheet sh)
    {
        if(Character.isUpperCase(operandStr.charAt(0)))
        {
            try
            {
                if(!CellName.isCellNameValid(operandStr)) throw new IllegalArgumentException();
                return sh.getFromSheet(operandStr).eval(sh);
            }
            catch(SheetException e)
            {
                System.out.println(e);
            }
            
        } 

        return Integer.parseInt(operandStr);
    }

    public String toString()
    {
        StringBuffer res = new StringBuffer();
        for(int i = 0; i < numOfRows; i++)
        {
            for(int j = 0; j < numOfCols; j++)
            {
                try
                {
                    if(sheet[i][j] == null)
                    {
                        res.append("null");
                    }
                    else
                    {
                        res.append(sheet[i][j].eval(this));
                    }
                    
                    if(j < numOfCols - 1) res.append(" ");
                }
                catch(Exception e) { }                
            }
            if(i < numOfRows - 1) res.append(System.lineSeparator());
        }

        return res.toString();
    }
} 