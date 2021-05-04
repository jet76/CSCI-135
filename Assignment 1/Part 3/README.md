# Part 3: Baby simulator  

A couple beginning a family decides to keep having children until they have at least one of each sex.

**a. BoyAndGirl.** Write a program BoyAndGirl.java that simulates repeatedly having a child until there is at least one of each sex. Output how many children it took to achieve this goal. Assume there is an equal probability of having either a boy or a girl. Use a while loop to compute the number of children the couple has until getting at least one of each gender.  

```console
% java BoyAndGirl  
Congratulations! You have 3 children.  
% java BoyAndGirl  
Congratulations! You have 6 children.  
% java BoyAndGirl  
Congratulations! You have 2 children.  
```

Possible progress steps:  

* First, think about what variables you need to maintain. You certainly need a counter for the number of children.  
* How will you tell when there is at least one of each gender? That will be the condition of your while loop.  

**b. BoyAndGirls.** Write a program BoysAndGirls.java that takes an integer command-line argument T. In each of T independent experiments, simulate a couple having children until they have at least one of each gender. Use the results of the T experiments to estimate the average number of children the couple will have. Record and output the frequency counts for 2, 3, and 4 children, and also one count for 5 and above. Finally, output the most common number of children in a family (if there is a tie, print only the first most common number of children). As before, assume that the probability of having a boy or a girl is 1/2.

```console
% java BoysAndGirls 2  
Average: 6.0 children to get at least one of each sex.  
Number of families with 2 children: 0  
Number of families with 3 children: 0  
Number of families with 4 children: 0  
Number of families with 5 or more children: 2  
Most common number of children is 5 or more.  
```

```console
% java BoysAndGirls 10  
Average: 3.5 children to get at least one of each sex.  
Number of families with 2 children: 2  
Number of families with 3 children: 3  
Number of families with 4 children: 3  
Number of families with 5 or more children: 2  
Most common number of children is 3.  
```

```console
% java BoysAndGirls 100  
Average: 3.19 children to get at least one of each sex.  
Number of families with 2 children: 44  
Number of families with 3 children: 24  
Number of families with 4 children: 16  
Number of families with 5 or more children: 16  
Most common number of children is 2.  
```

As T increases, we expect the average number of children per family to converge. Use BoysAndGirls to formulate a hypothesis as to what this average is. Run BoysAndGirls with T = 1, 10, 100, 100000 and 1000000 to watch it converge to a sufficiently accurate estimate. Include your experimental results and hypothesis in the header portion of BoysAndGirls.java.  

Possible progress steps:  
* To get started, copy BoyAndGirl.java to a file BoysAndGirls.java.
* Now, think about what additional variables you need to maintain. You certainly need to read in and store the command-line argument T.
* You will also need to count up ALL the children in order to compute the average number of children needed to achieve the goal of both genders.
* You will also need to keep a frequency counter for each of the four possible categories (2, 3, 4, 5 and more).
* Nest the while loop inside an outer loop that repeats T times and add code to update child count and frequency counts after each trial.

---

This README was adapted from an assignment page at Montana Tech: https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/loops/
