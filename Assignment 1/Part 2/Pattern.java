// Name			: Joe Turner
// Username		: jeturner
// Description	: Accepts two arguments (n and m) then prints an 2n by m "checkerboard" pattern of asterisks and periods.
public class Pattern {
	public static void main(String [] args)
	{
		// Integer variable for storing the number of columns. Columns will be two characters wide (2n).
		int n = Integer.parseInt(args[0]);
		// Integer variable for storing the number of rows.
		int m = Integer.parseInt(args[1]);
		// Boolean variable to store whether or not the the line number is even.
		boolean isEven;
		// Loop to display each row
		for (int i = 1; i <= m; i++)
		{
			// Divide the row number by 2 to determine if the row is even.
			isEven = (i % 2 == 0);
			// Loop to print the columns for each row.
			for (int j = 1; j <= n; j++)
			{
				// If the row number is even print .* for each column.
				if (isEven) 
				{
					System.out.print(".*");
				}
				// Else print *. for each column.
				else
				{
					System.out.print("*.");	
				}
			}
			// If this isn't the last row then start a print a new line for the next row.
			if (i<m)
			{
				System.out.println("");
			}
		}
	}
}
