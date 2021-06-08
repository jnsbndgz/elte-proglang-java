package calc.util;

import calc.util.SheetException;

public class CellName
{
    private static final String colIndexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean isCellNameValid(String cellName)
    {
        try 
        {
            int n = Integer.parseInt(cellName.substring(1, cellName.length()));
            for(int i = 0; i < colIndexes.length(); i++)
            {
                if(cellName.charAt(0) == colIndexes.charAt(i) && n >= 0)
                {
                    return true;
                }
            }
        } 
        catch (NumberFormatException e)
        {
            return false;
        }

        return false;
    }

    public static int getRowIndexFromCellName(String cellName) throws SheetException
    {
        if(!isCellNameValid(cellName)) throw new SheetException();

        return Integer.parseInt(cellName.substring(1, cellName.length()));
    }

    public static int getColIndexFromCellName(String cellName) throws SheetException
    {
        if(!isCellNameValid(cellName)) throw new SheetException();

        int i = 0;

        while(cellName.charAt(0) != colIndexes.charAt(i))
        {
            i++;
        }

        return i;
    }

    public static int getMaxCol()
    {
        return colIndexes.length();
    }
}