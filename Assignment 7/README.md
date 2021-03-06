# ASSIGNMENT #7 - Guitar Hero  

In this assignment, you will be making a program that simulates the plucking of guitar strings. You will gain experience creating data types including a queue data type.  

Write a program to simulate plucking a guitar string using the Karplus-Strong algorithm. This algorithm played a seminal role in the emergence of physically modeled sound synthesis (where a physical description of a musical instrument is used to synthesize sound electronically).  

**Digital audio.** Before reading this assignment, review the StdAudio portion of section 1.5 of Introduction to Programming in Java or [this page](http://introcs.cs.princeton.edu/java/15inout/).

**Simulate the plucking of a guitar string.** When a guitar string is plucked, the string vibrates and creates sound. The length of the string determines its *fundamental frequency* of vibration. We model a guitar string by sampling its *displacement* (a real number between -1/2 and +1/2) at *N* equally spaced points (in time), where *N* equals the *sampling rate* (44,100) divided by the fundamental frequency (rounding the quotient to the nearest integer).  

![Sampling from Karplus-Strong](guitar-samples.png)  

* *Plucking the string.* The excitation of the string can contain energy at any frequency. We simulate the excitation with *white noise*: set each of the *N* displacements to a random real number between -1/2 and +1/2.  

  ![White noise](white-noise.png)  

* *The resulting vibrations.* After the string is plucked, the string vibrates. The pluck causes a displacement which spreads wave-like over time. The Karplus-Strong algorithm simulates this vibration by maintaining a *ring buffer* of the *N* samples: the algorithm repeatedly deletes the first sample from the buffer and adds to the end of the buffer the average of the first two samples, scaled by an *energy decay factor* of 0.996.  

  ![the Karplus-Strong update](karplus-strong.png)  

*Why it works?* The two primary components that make the Karplus-Strong algorithm work are the ring buffer feedback mechanism and the averaging operation.  

* *The ring buffer feedback mechanism.* The ring buffer models the medium (a string tied down at both ends) in which the energy travels back and forth. The length of the ring buffer determines the fundamental frequency of the resulting sound. Sonically, the feedback mechanism reinforces only the fundamental frequency and its harmonics (frequencies at integer multiples of the fundamental). The energy decay factor (.996 in this case) models the slight dissipation in energy as the wave makes a roundtrip through the string.  
* *The averaging operation.* The averaging operation serves as a gentle low-pass filter (which removes higher frequencies while allowing lower frequencies to pass, hence the name). Because it is in the path of the feedback, this has the effect of gradually attenuating the higher harmonics while keeping the lower ones, which corresponds closely with how a plucked guitar string sounds.  

**Getting started** You will be developing three classes in this assignment, RingBuffer.java, GuitarString.java, and GuitarHero.java. You will need to create these files from scratch implementing the APIs prescribed on this page. You will also need the support classes [StdAudio.java](StdAudio.java) and [StdDraw.java](StdDraw.java).  

**Ring buffer.** Your first task is to create a data type to model the ring buffer. Write a class named RingBuffer that implements the following API:  

<pre>
public class RingBuffer  
-----------------------------------------------------------------------------------------  
        RingBuffer(int capacity)  // create an empty ring buffer, with given max capacity  
    int size()                    // return number of items currently in the buffer  
boolean isEmpty()                 // is the buffer empty (size equals zero)?  
boolean isFull()                  // is the buffer full  (size equals capacity)?  
   void enqueue(double x)         // add item x to the end  
 double dequeue()                 // delete and return item from the front  
 double peek()                    // return (but do not delete) item from the front  
</pre>

Since the ring buffer has a known maximum capacity, implement it using a double array of that length. For efficiency, use *cyclic wrap-around*: Maintain one integer instance variable first that stores the index of the least recently inserted item; maintain a second integer instance variable last that stores the index one beyond the most recently inserted item. To insert an item, put it at index last and increment last. To remove an item, take it from index first and increment first. When either index equals capacity, make it wrap-around by changing the index to 0.  

Implement RingBuffer to throw an exception if the client attempts to dequeue() or peek() from an empty buffer or enqueue() into a full buffer.  

  ![Ring buffer](ring-buffer.png)  

You can test your RingBuffer data type on the following toy client, using the main() provided. It enqueues the numbers 1 through N, and then repeatedly dequeues the first two, and enqueues their sum.  

```
public static void main(String[] args) 
{
      int N = Integer.parseInt(args[0]);
      RingBuffer buffer = new RingBuffer(N);  
      for (int i = 1; i <= N; i++) 
      {
          buffer.enqueue(i);
      }
      double t = buffer.dequeue();
      buffer.enqueue(t);
      System.out.println("Size after wrap-around is " + buffer.size());
      while (buffer.size() >= 2) 
      {
          double x = buffer.dequeue();
          double y = buffer.dequeue();
          buffer.enqueue(x + y);
      }
      System.out.println(buffer.peek());
}
```

```console
% java RingBuffer 10  
Size after wrap-around is 10  
55.0  
```

```console
% java RingBuffer 100  
Size after wrap-around is 100  
5050.0  
```

**Guitar string.** Next, create a data type to model a vibrating guitar string. Write a class named GuitarString that implements the following API:  

<pre>
public class GuitarString
------------------------------------------------------------------------------------------------------------------------  
       GuitarString(double frequency)  // create a guitar string of the given frequency, using a sampling rate of 44,100  
       GuitarString(double[] init)     // create a guitar string whose size and initial values are given by the array  
  void pluck()                         // set the buffer to white noise  
  void tic()                           // advance the simulation one time step  
double sample()                        // return the current sample  
   int time()                          // return number of tics  
</pre>

* *Constructors.* There are two ways to create a GuitarString object.    
  * The first constructor creates a RingBuffer of the desired capacity *N* (*the sampling rate* 44,100 divided by *frequency*, rounded to the nearest integer), and initializes it to represent a guitar string at rest by enqueueing *N* zeros.  
  * The second constructor creates a RingBuffer of capacity equal to the size of the array, and initializes the contents of the buffer to the values in the array. On this assignment, its main purpose is for debugging and grading.
* *Pluck*. Replace the *N* items in the ring buffer with *N* random values between -0.5 and +0.5.  
* *Tic*. Apply the Karplus-Strong update: 1) compute the average of the first two samples, 2) delete the sample at the front of the ring buffer, 3) add to the end of the ring buffer the previously computed average multiplied by the energy decay factor.  
* *Sample*. Return the value of the item at the front of the ring buffer.  
* *Time*. Return the total number of times tic() was called.  

You can test your GuitarString data type with the following toy client, using the main() provided.  

```
public static void main(String[] args) 
{
    int N = Integer.parseInt(args[0]);
    double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
    GuitarString testString = new GuitarString(samples);
    for (int i = 0; i < N; i++) 
    {
        int t = testString.time();
        double sample = testString.sample();
        System.out.printf("%6d %8.4f\n", t, sample);
        testString.tic();
    }
}
```

```console
% java GuitarString 25  
     0   0.2000  
     1   0.4000  
     2   0.5000  
     3   0.3000  
     4  -0.2000  
     5   0.4000  
     6   0.3000  
     7   0.0000  
     8  -0.1000  
     9  -0.3000  
    10   0.2988  
    11   0.4482  
    12   0.3984  
    13   0.0498  
    14   0.0996  
    15   0.3486  
    16   0.1494  
    17  -0.0498  
    18  -0.1992  
    19  -0.0006  
    20   0.3720  
    21   0.4216  
    22   0.2232  
    23   0.0744  
    24   0.2232  
```

**Interactive guitar player.** [GuitarHeroLite.java](GuitarHeroLite.java) is a sample GuitarString client that plays the guitar in real-time, using the keyboard to input notes. When the user types the lowercase letter 'a' or 'c', the program plucks the corresponding string. Since the combined result of several sound waves is the superposition of the individual sound waves, we play the sum of all string samples.  

Write a program GuitarHero that is similar to GuitarHeroLite, but supports a total of 37 notes on the chromatic scale from 110Hz to 880Hz. In general, make the ith character of the string  

   String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  

This keyboard arrangement imitates a piano keyboard: The "white keys" are on the qwerty and zxcv rows and the "black keys" on the 12345 and asdf rows of the keyboard.  

![Piano keyboard](keyboard.png)  

The *i*th character of the string corresponds to a frequency of 440 ?? 2<sup>(i - 24) / 12</sup>, so that the character 'q' is 110Hz, 'i' is 220Hz, 'v' is 440Hz, and ' ' is 880Hz. Don't even think of including 37 individual GuitarString variables or a 37-way if statement! Instead, create an array of 37 GuitarString objects and use keyboard.indexOf(key) to figure out which key was typed. Make sure your program does not crash if a key is played that is not one of your 37 notes. You can test your GuitarHero program by typing the following into your guitar to get the beginning of Led Zeppelin's *Stairway to Heaven*. Multiple notes in a column are dyads and chords.  

<pre>
                                              w q q  
        8       u       7       y             o p p  
i p z v b z p b n z p n d [ i d z p i p z p i u i i  
</pre>

What is this familiar melody?  
```
  nn//SS/ ..,,mmn //..,,m //..,,m nn//SS/ ..,,mmn   (S = space)  
```

---

**Do I need to follow the prescribed API?** Yes, we will be testing the methods in the API directly. If your method has a different signature or does not behave as specified, you will lose a substantial number of points. You may not add public methods to the API; however, you may add private methods (which are only accessible in the class in which they are declared).  

**Where do I enter keystrokes in GuitarHeroLite and GuitarHero?** Be sure that the standard draw window has focus by clicking in it. Then, type the keystrokes.  

**Is the size of a RingBuffer equal to the number of nonzeros?** No. Some of the elements in the buffer can be zero. To get an accurate count, increment the instance variable size each time you call enqueue() and decrement it each time you call dequeue().  

**What should RingBuffer do if the client attempts to dequeue() from an empty buffer or enqueue() into a full buffer?** You should *throw an exception* to indicate the error. This is easy to do, here is an example: throw new RuntimeException("Ring buffer overflow");  

**What happens if I call StdAudio.play(x) where x is greater than 1 or less than -1?** The value is *clipped*???it is replaced by the value 1.0 or -1.0, respectively.  

**I get an ArrayOutOfBounds or NullPointerException error in RingBuffer. What could cause this? Does your constructor correctly initialize all of the instance variables?** Did you allocate memory for your array? Did you inadvertently redeclare an instance variable in a method or constructor, thereby shadowing the instance variable with the same name?  
**I get a Ring buffer underflow error in GuitarHeroLite before I type any keystrokes. Why?** Did you forget to initialize the ring buffer to contain N zeros in your GuitarString constructor?  

**When I run GuitarHeroLite for the first time, I hear no sound. What am I doing wrong?** Make sure you have tested with the main() provided for GuitarString. If that works, it is likely something wrong with pluck() since the main() provided for GuitarString does not test that method. To diagnose the problem, print out the values of sample() and check that they become nonzero after you type *lower case* characters 'a' and 'c'.  

**When I run GuitarHeroLite, I hear static (either just one click, and then silence or continual static). What am I doing wrong?** It's likely that pluck() is working, but tic() is not. The best test is to run the main() provided for GuitarString.  

**How do I use keyboard.indexOf(key)?** If keyboard is a String and key is a character, then keyboard.indexOf(key) return the integer index of the first occurrence of the character key in the string keyboard (or -1 if it does not occur).  

**Should I hardwire the constants 44,100, 110.0, 440.0, 880.0, and 37 in my program?** No, in general, we will deduct if you use an unnamed constant (such as 37) in your program more than once. We recommend using the name SAMPLING_RATE for 44,100 and CONCERT_A for 440. But you need not name all of the constants in the formula 2<sup>(i - 24) / 12</sup>.

---

**Extra credit performance** At the beginning of the next lab, perform a piece for your classmates (on my laptop, your laptop, or a lab computer). Partners may perform a duet and both will receive extra credit, or a solo for individual extra credit.  

**Extra credit idea 1.** Write a program GuitarHeroVisualizer.java (by modifying GuitarHero.java) that plots the sound wave in real-time, as the user is playing the keyboard guitar. The output should look something like this, but change over time.  

  ![Sampling from Karplus-Strong](guitar-samples.png)  
  
**Extra credit idea 2.** Modify the Karplus-Strong algorithm to synthesize a different instrument. Consider changing the excitation of the string (from white-noise to something more structured) or changing the averaging formula (from the average of the first two samples to a more complicated rule) or anything else you might imagine.  

**Extra credit idea 3.** Make an actual game similar to the real Guitar Hero game. The game should indicate what keys to play and the timing of the notes. The video to the right shows the game one previous student came up with.  

---

This assignment was originally developed by these folks at Princeton: Andrew Appel, Jeff Bernstein, Maia Ginsburg, Ken Steiglitz, Ge Wang, and Kevin Wayne.
http://introcs.cs.princeton.edu/java/assignments/guitar.html

---

This README was adapted from an assignment page at Montana Tech: https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/guitar/
