
# Part 1: Double or nothing

You are currently X dollars in debt to your bookie but currently have only Y dollars to your name. Create a program DoubleOrNothing.java to calculate the *minimum* number of times you would have to go double-or-nothing at the roulette table to pay your debt. Your program should take two command-line arguments X and Y. X and Y are floating-point numbers representing the amounts in dollars and cents (e.g. 2345.32). Assume you go "all-in" on every bet, you win every bet, and the casino allows bets of any value. Assume X > 0 and Y > 0. Here some example runs:

% java DoubleOrNothing 1000 100  
After 1 bet(s), total: 200.0  
After 2 bet(s), total: 400.0  
After 3 bet(s), total: 800.0  
After 4 bet(s), total: 1600.0  
Number of bets: 4  
Final amount: 1600.0  

% java DoubleOrNothing 12345.67 13.00  
After 1 bet(s), total: 26.0  
After 2 bet(s), total: 52.0  
After 3 bet(s), total: 104.0  
After 4 bet(s), total: 208.0  
After 5 bet(s), total: 416.0  
After 6 bet(s), total: 832.0  
After 7 bet(s), total: 1664.0  
After 8 bet(s), total: 3328.0  
After 9 bet(s), total: 6656.0  
After 10 bet(s), total: 13312.0  
Number of bets: 10  
Final amount: 13312.0  

% java DoubleOrNothing 0.99 0.25  
After 1 bet(s), total: 0.5  
After 2 bet(s), total: 1.0  
Number of bets: 2  
Final amount: 1.0  

% java DoubleOrNothing 100 1000  
Number of bets: 0  
Final amount: 1000.0  

% java DoubleOrNothing 1000.00 1000.00  
Number of bets: 0  
Final amount: 1000.0  

**Do I really need to worry about strange cases like the last three?** Yes. While these cases may seem silly in our imaginary problem, you should always pay attention to such things when developing software. Failure to handle such tricky cases can result in all sorts of bugs such as your program crashing or going into an infinite loop. You might find this entertaining/terrifying: [History's Worst Software Bugs](http://www.wired.com/software/coolapps/news/2005/11/69355).  

**Do I need to use Math.random() or anything?** No. You can assume you win every bet placed so there is no randomness in this program.  

**My program crashes if one or both of the arguments isn't a number (e.g. java DoubleOrNothing blah 3.14). Is that okay?** Yes. We'll eventually learn how to handle such cases, but not for awhile.

---

This README was adapted from an assignment page at Montana Tech: https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/loops/
