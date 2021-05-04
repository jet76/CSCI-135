# Part 1: Number Hunt  

Create a game NumberHunt.java in which users try and guess a random number between 1 and 100. After each guess, the computer prints out a hint according to how close the guess was to the target value:  
* correct guess -> "You nailed it!"  
* 1 number off -> "On fire."  
* 2-5 numbers off -> "Hot."  
* 6-10 numbers off -> "Getting warmer."  
* 11-19 numbers off -> "Cold."  
* 20-99 numbers off -> "Ice cold."  

If the user inputs a number that isn't between 1 and 100, you should print "Invalid input!". After correctly guessing the number, the game should print the total number of guesses (including invalid guesses). You can assume users will only enter integers and not floating-point numbers or text.  

Your program should optionally take a single integer command line argument. If a command line argument is given, use this number instead of drawing a random number. This will help you test your program since you can start with a known number. You can assume the command line argument (if given) will always be between 1 and 100. Here are some example runs:  

% java NumberHunt  
Guess a number between 1-100? 50  
Ice cold.  
Guess a number between 1-100? 20  
Getting warmer.  
Guess a number between 1-100? 10  
Hot.  
Guess a number between 1-100? 5  
Getting warmer.  
Guess a number between 1-100? 15  
Hot.  
Guess a number between 1-100? 12  
You nailed it!  
It took you 6 guesses.  

% java NumberHunt 42  
Guess a number between 1-100? 10  
Ice cold.  
Guess a number between 1-100? 101  
Invalid input!  
Guess a number between 1-100? 30  
Cold.  
Guess a number between 1-100? 35  
Getting warmer.  
Guess a number between 1-100? 40  
Hot.  
Guess a number between 1-100? 41  
On fire.  
Guess a number between 1-100? 43  
On fire.  
Guess a number between 1-100? 42  
You nailed it!  
It took you 8 guesses.  

**How do I read in a number from the user?** You need to use download the [StdIn.java](StdIn.java) class and place it in the same directory as your [NumberHunt.java](NumberHunt.java) program. When you want your program to read in an int from the user, call the method StdIn.readInt().

How do I check if a command line argument was given? You can use args.length to find out how many command line arguments were sent in. If args.length is zero, you know you need to draw a random number. If args.length is greater than zero, you can obtain the target value from args[0].  

How do I draw a random number? You want to use the Math.random() method.  
