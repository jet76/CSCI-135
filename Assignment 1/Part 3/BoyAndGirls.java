// Name			: Joe Turner
// Username		: jeturner
// Description	: 
public class BoyAndGirls {
	public static void main(String [] args)
	{
		int t = Integer.parseInt(args[0]);
		// Integer variable to count number of boys.
		int b = 0;
		// Integer variable to count number of girls.
		int g = 0;
		// Integer variable to count kids per family.
		int k = 0;
		// Integer variable to count the total number of kids.
		int kids = 0;
		// Integer variables for counting the number of families and how many kids are in each family 		
		int twoKids = 0;
		int threeKids = 0;
		int fourKids = 0;
		int fiveOrMoreKids = 0;
		// Boolean variable to determine when a family has at least 1 boy and 1 girl
		boolean haveBoth = false;
		// Reproduce until we have t families have at least 1 boy and 1 girl.
		for (int i = 1; i <= t; i++)
		{
			// Reproduce until you have at least one boy and one girl
			while (haveBoth != true)
			{
				// Total the number of kids produced.
				k += 1;
				kids += 1;
				// Randomly determine gender and total the number of boys/girls
				if (Math.random() < 0.5) b += 1;
				else g += 1;
				// Determine if the family has at least 1 boy and 1 girl
				if ((b > 0) && (g> 0)) haveBoth = true;
			}
			// Total the families
			if (k == 2) twoKids += 1;
			if (k == 3) threeKids += 1;
			if (k == 4) fourKids += 1;
			if (k >= 5) fiveOrMoreKids += 1;
			// Reset variables for the next family
			k = 0;
			b = 0;
			g = 0;
			// Reset the boolean variable
			haveBoth = false;
		}
		// Calculate the average number of kids per family
		double avg = (kids / t);
		// Display results
		System.out.println("Average: " + avg + " children to get to at least 1 of each gender.");
		System.out.println("Number of families with 2 children: " + twoKids);
		System.out.println("Number of families with 3 children: " + threeKids);
		System.out.println("Number of families with 4 children: " + fourKids);
		System.out.println("Number of families with 5 or more children: " + fiveOrMoreKids);
		// Determine the most common number of kids per family
		int common = 5;
		if (fourKids >= fiveOrMoreKids) common = 4;
		if (threeKids >= fourKids) common = 3;
		if (twoKids >= threeKids) common = 2;
		// Display the results
		System.out.println("Most common number of children is " + common + " or more.");
	}
}
