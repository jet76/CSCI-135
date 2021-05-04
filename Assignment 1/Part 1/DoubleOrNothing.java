// Name			: Joe Turner
// Username		: jeturner
// Description	: Accepts 2 arguments: money owed (debt) and cash on hand then calculates the minimum number of times you would have to go double-or-nothing at the roulette table to pay your debt. Assumes a win for every bet.
public class DoubleOrNothing {
	public static void main(String [] args)
	{
		// Variable to hold parsed first argument (money owed)
		double x = Double.parseDouble(args[0]);
		// Variable to hold parsed second argument (cash on hand)
		double y = Double.parseDouble(args[1]);
		// Variable to hold the number of bets/wins required to come out even or ahead.
		int n = 0;
		// If money owed is less than cash on hand we gamble
		if (y < x)
		{
			// while cash on hand is less than money owed assume doubling of cash on hand for each bet
			do
			{
				// Count the bet
				n += 1;
				// Double the cash on hand
				y = 2 * y;
				// Output the result of the bet
				System.out.println("After " + n + " bet(s), total: " + y);
			}
			while (y < x);
		}
		// Output the total number of bets placed
		System.out.println("Number of bets: " + n);
		// Output final cash on hand
		System.out.println("Final amount: " + y);
	}
}
