// name			: Joe Turner
// username		: jeturner
// description	: Guess a number between 1 and 100. Number can be passed via args or generated randomly. Program gives hints as to how close each guess is to the actual number. Operates under the assumption that the user will enter an integer.
public class NumberHunt {
	public static void main(String[] args)
	{
		// Declare some integer variables to keep track of the number, guess, and number of guess (count).
		int number = 0;
		int guess = 0;
		int count = 0;

		// If a number between 1 and 100 has been passed via args then use it as the number to be guessed,
		if ((args.length != 0) && (Integer.parseInt(args[0]) >= 1) && (Integer.parseInt(args[0]) <= 100))
		{
			number = Integer.parseInt(args[0]);
		}
		// otherwise generate a random number.
		else
		{
			number = (int) (Math.random() * 100) + 1;
		}

		// Repeat until the number is guessed.
		while (guess != number)
		{
			// Prompt user to enter a guess.
			System.out.print("Guess a number between 1-100: ");
			// Store the guess.
			guess = StdIn.readInt();
			// Keep count for the number of guess.
			count += 1;
			
			// If the guess is between 1 and 100 then
			if (guess >= 1 && guess <= 100)
			{
				// determine the results of the guess.
				if (guess == number)
				{
					System.out.println("You nailed it!");
					System.out.print("It took you " + count + " guess");
					if (count == 1)
					{
						System.out.println("!");
					}
					else
					{
						System.out.println("es.");
					}
				}
				else if (guess == number + 1 || guess == number - 1)
				{
					System.out.println("On fire.");
				}
				else if (guess >= number - 5 && guess <= number + 5)
				{
					System.out.println("Hot.");
				}
				else if (guess >= number - 10 && guess <= number + 10)
				{
					System.out.println("Getting warmer.");
				}
				else if (guess >= number - 19 && guess <= number + 19)
				{
					System.out.println("Cold.");
				}
				else
				{
					System.out.println("Ice cold.");
				}
			}
			// Otherwise let the user know the guess was invalid.
			else
			{
				System.out.println("Invalid Input!");
			}
		}
	}
}
