// Name			: Joe Turner
// Username		: jeturner
// Description	: 
public class BoyAndGirl {
	public static void main(String [] args)
	{
		// Integer variable starting out with zero boys
		int b = 0;
		// Integer variable starting out with zero girls
		int g = 0;
		// Integer variable starting out with zero kids.
		int k = 0;
		// Boolean to determine when we have at least 1 boy and 1 girl.
		boolean haveBoth = false;		
		// Reproduce until you have at least one boy and one girl
		while (haveBoth != true)
		{
			// Total the number of kids produced.
			k += 1;
			// Randomly determine gender and total the number of boys/girls
			if (Math.random() < 0.5) b += 1;
			else g += 1;
			// Determine if we have at least 1 of each
			if ((b > 0) && (g> 0)) haveBoth = true;
		}		
		// Display the number of children that resulted of reproduction
		System.out.println("Congratulations! You have " + k + " children. " + b + " boy(s) and " + g + " girl(s).");
	}
}
