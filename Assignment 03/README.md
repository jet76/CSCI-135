# ASSIGNMENT #3  

In this assignment, you will be building a Mars Lander video game. You will learn how to draw things and get keyboard input using static methods in [StdDraw.java](StdDraw.java). You will also use [StdAudio.java](StdAudio.java) to play sound effects.

**Mars Lander**  

You are an astronaut on the first ever manned mission to Mars. Your auto-pilot computer (running Windows 2037 Hyper Mega Ultimate) has just given you the blue screen of death. Rebooting is going to take awhile, so it's up to you to pilot your ship to a soft landing in the only safe spot around. You have a limited amount of fuel and once that runs out, you are at the mercy of gravity! Spaceship controls:  

* w - bottom thrusters (slows the ship's descent)
* d - left thrusters (makes the ship go more to the right)
* a - right thrusters (makes the ship go more to the left)  

Your program MarsLander.java shoud read in a game control file from standard input using [StdIn.java](StdIn.java). Here is an example game control file with comments at the end describing the order and meaning of the values:  

1000 500  
mars_sky.jpg  
ship.png ship_bottom.png ship_left.png ship_right.png ship_landed.png ship_crashed.png  
20 50  
500.0 400.0  
100  
thrust.wav yay.wav explosion.wav  
-0.1  
2.0  
0.5  
500 50  
\# An easy game setup, you start directly above a large pad and have plenty of fuel.  
\# Background image is from one of the NASA Mars rovers.  
\#  
\# Description of values:  
\#  \<width in pixels\> \<height in pixels\>  
\#  <background image>  
\#  <normal ship image> <bottom thrusters> <left thrusters> <right thrusters> <ship landed> <ship crashed>  
\#  <horizontal distance, ship center to edge of thrusters> <vertical distance, ship center to bottom edge of thrusters>  
\#  <ship starting x> <ship starting y>  
\#  <starting fuel>  
\#  <thruster sound> <good landing sound> <bad landing sound>  
\#  <gravity term>  
\#  <maximum survivable velocity>  
\#  <thrust amount>  
\#  <landing pad x center> <distance from center of landing pad to edge>  

You should start by downloading the file [lander.zip](https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/lander/lander.zip). This file contains some example control files and the associated images/sounds. These files will need to be in the same directory where you run your program.  

**Gravity and thrusting**. Your program will need to track the current horizontal and vertical position of the ship (let's call the positions posX and posY). You also need to store a velocity composed of an horizontal component velX and a vertical component velY. Both velocities start at 0. At every time step of the game, the gravity term from the control file is added to velY.  

If the user uses activates the bottom thrusters, the thrust amount from the control file is added to velY. Similarly, the left and right thrusters should change velX. Whenever thrust is applied, the thruster sound should be played and the ship picture updated to the appropriate version. Using any thruster causes the amount of fuel to be reduced by one. The current fuel remaining should be display in the upper-left corner. After updating the velocities, the current position of the ship must be changed. This is achieved by simply adding velX to posX and adding velY to posY.  

Note that it is possible for the ship to go off the top or sides of the screen. The ship can still be hopefully piloted back toward the landing pad. But of course an off screen ship can still hit the ground!  

**Landing and crashing**. Your ship has landed or crashed when the bottom landing pads reaches (or goes below) the y-coordinate of 0. If your landing pads have reached the bottom and either velX or velY is greater than the maximum survivable velocity. Your ship has also crashed if its bottom thrusters are not completely inside the x-coordinates of the landing pad. If you land successfully, you should display the ship landed picture and play the good landing sound. If you crash and burn, you should display the ship crashed picture and play the bad landing sound. After landing or crashing, the game is over and no further updates are performed.  

**Window setup**. The width and height of the game window are given as the start of the control file. We will use these values both for the size of the drawing window (in pixels) and for the coordinate system used by the drawing methods. To set the window size, call StdDraw.setCanvasSize(width, height). To set the x-coordinate range, call StdDraw.setXscale(0, width). To set the y-coordinate range, call StdDraw.setXscale(0, height).  

If you want to test your program with another "expansion pack", download [level_cade.zip](https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/lander/level_cade.zip). This pack has the images, sounds, and a level file that renders a different looking ship and world.  

**My program complains it can't find StdDraw or StdAudio. What is wrong?** Make sure you have download them from the links at the top of the page to the source folder of your project (the one with the *.java files). In order for the classes to appear in Eclipse, you'll need to right click on the project name in Eclipse and select *Refresh*.

**How exactly do I draw images or get keyboard input?** Check out [BoxCar.java](https://katie.cs.mtech.edu/classes/archive/f13/csci135/examples/BoxCar.java) (you'll also need [explosion.wav](explosion.wav) to make this program work).  

**My game goes way to fast. How do I slow it down?** Be sure to call StdDraw.show(ms) at the end of your animation loop. We used a delay value of 100 milliseconds.  

**How do I make the drawn text bold like yours?** You need to set the font used by StdDraw. A single call at the start of your program such as StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));. You'll need to put import java.awt.Font; at the top of your program (outside the class declaration) as well. will do the job.  

**After I crash, the final things I drew are not displayed. What is going on?** When StdDraw is in animation mode (as soon as your program calls StdDraw.show()), the graphics window is only updated on the next call to StdDRaw.show(). If you add a final call StdDraw.show(0) to your program, you should see your final drawing.  

**I check for a crash if the bottom of my ship hits y = 0.0, but my ship just cruises on past without crashing. What is wrong?** Be sure you are not testing a double variable against a literal value with ==. It is very unlikely that your position will be exactly equal to the literal value. You probably should use an inequality instead.
