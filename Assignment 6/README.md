# ASSIGNMENT #6  

In this assignment, you will be building a video game called Staying Alive. In the process, you will be gain experience using classes and objects.  
 
**Staying Alive**  

The object of the game is to avoid a bunch of objects that are zooming around the screen and bouncing off the walls. The game is over if the player is hit by any of the objects. The player scores a point for every time step of the game in which they stay alive. The game is played with the mouse. The player's character moves in the direction of the mouse. The further the mouse cursor is away from the character's current location, the faster it moves towards the mouse location.  

**Classes:** You should start by downloading the file [alive.zip](https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/alive/alive.zip). This file contains some example control files and the associated images. These files will need to be in the same directory where you run your program. The zip file also contains stub versions for the three classes you will be developing. You will need to add instances variables, the bodies of instance methods, and the body of the main() method. You should also comment the top of every method (every method needs a comment describing what it does, what parameters it takes, and what it returns). Here is a description of the three classes:  

* StayingAlive.java - Client program containing the main() method that runs the game. Handles reading in the game control file using StdIn. It will create the Player object and an array of Enemy objects. When the program is first started, it prints out a text representation of the Player and Enemy objects.  

* Player.java - Player class. This class represents the player. It knows things like the x-location and y-location of the player. It also knows the image filename used to display the player as well as the radius of the player. We assume the player is a circle, using the radius to detect collision with the Enemy objects. The Player object also has a speed update factor. The speed factor is used to determine how fast the player moves to the mouse location (details below). The Player object can do things such as draw itself, update its position, check intersection with an Enemy object, and print itself out to the console.  

* Enemy.java - Enemy class. This class represents the various things that will be flying around trying to kill the player. It knows things like its x-location, y-location, x-velocity, y-velocity, radius, and image. The Enemy objects spin as then bounce around, so they also know their current rotation angle. An Enemy object can do things such as draw itself, update its position, get its x-position, get its y-position, get its radius, and print itself out to the console.  

**Control file.** Your client program StayingAlive.java shoud read in a game control file from standard input using [StdIn.java](StdIn.java). Here is an example game control file [hitchhiker.txt](hitchhiker.txt) with comments at the end describing the order and meaning of the values:  

stars.jpg  
dont_panic_40.png 0.5 0.5 0.035 100  
6  
asteroid_small.png 0.1 0.1 0.018 -0.002 -0.003  
asteroid_medium.png 0.2 0.2 0.030 0.002 -0.003  
asteroid_large.png 0.3 0.3 0.065 -0.002 0.003  
asteroid_small.png 0.4 0.4 0.018 -0.001 -0.004  
asteroid_medium.png 0.6 0.6 0.030 0.002 -0.003  
asteroid_large.png 0.7 0.7 0.065 -0.0035 0.0025  

# Hitchhikers Guide to the Galaxy: Avoid a bunch of asteroids  
\#  \<background image\>  
\#  \<player image\> \<player x-position\> \<player y-position\> \<player radius\> \<player speed factor\>  
\#  \<number enemies\>  
\#  \<enemy0 image\> \<enemy0 x-position\> \<enemy0 y-position\> \<enemy0 radius\> \<enemy0 x-velocity\> \<enemy0 y-velocity\>  
\#  \<enemy1 image\> \<enemy1 x-position\> \<enemy1 y-position\> \<enemy1 radius\> \<enemy1 x-velocity\> \<enemy1 y-velocity\>  
\#  ...  

**Console output.** Your StayingAlive.java program should output the intial game state to the console window:  

% java StayingAlive < hitchhiker.txt  
PLAYER: (0.5, 0.5) r=0.035 speed=100 dont_panic_40.png  
ENEMY 0: (0.1, 0.1) vel (-0.0020, -0.0030) r=0.018 angle=258 asteroid_small.png  
ENEMY 1: (0.2, 0.2) vel (0.0020, -0.0030) r=0.03 angle=10 asteroid_medium.png  
ENEMY 2: (0.3, 0.3) vel (-0.0020, 0.0030) r=0.065 angle=37 asteroid_large.png  
ENEMY 3: (0.4, 0.4) vel (-0.0010, -0.0040) r=0.018 angle=98 asteroid_small.png  
ENEMY 4: (0.6, 0.6) vel (0.0020, -0.0030) r=0.03 angle=301 asteroid_medium.png  
ENEMY 5: (0.7, 0.7) vel (-0.0035, 0.0025) r=0.065 angle=5 asteroid_large.png  

**Canvas size and coordinates.** In this assignment, we'll leave the canvas size at the default size of 512 x 512 pixels. We will also use the default coordinate system which has the lower-left corner being (0.0, 0.0) and the upper-right corner being (1.0, 1.0). Thus in this assignment you do NOT need to call methods such as StdDraw.setCanvasSize(), StdDraw.setXscale(), or StdDraw.setYscale().  

**Player dynamics.** The Player object updates its *x-position* and *y-position* based on the current mouse location. The current mouse location can be obtained by calling StdDraw.mouseX() and StdDraw.mouseY(). These methods return a double representing the mouse location in the current StdDraw coordinate system. The mouse coordinates are sent to the Player object via the updatePos(double mouseX, double mouseY) method. To calculate the player's new *x-position*, you increment the current *x-position* by the differece between the mouse coordinate and the player's current position divided by the player's *speed factor*.  

For example, if the player has a speed of 100 and is currently at an *x-position* of 0.5 and the mouse is at the x-coordinate 0.7: (0.7 - 0.5) / 100.0 = 0.002. After the update, the player's new *x-position* would be 0.5002. Note that a higher speed factor actually causes slower responsiveness to the mouse input.  

**Enemy dynamics.** The Enemy objects update their position in the updatePos() method by adding their *x-velocity* and *y-velocity* to their *x-position* and *y-position* respectively. In updatePos(), you should also check if the position has gone out of the box (0.0, 0.0) - (1.0, 1.0). If so, you should invert the *x-velocity* and/or *y-velocity* as appropriate. The Enemy objects should rotate counterclockwise 1 degree at every time step of the game. When the object is constructed, the angle should start out at a random value between 0-359 (this way different objects using the same image file won't all look the same).  

**Collision detection.** For simplicity, we assume the player and enemies are all circles. The game is over if the circle representing the player intersects with any enemy circle. This is similar to how I checked for the intersection of Ball objects in the lecture on [classes and objects](https://katie.cs.mtech.edu/classes/archive/f13/csci135/slides/135-classes-objects.pdf).  


**How do I make the drawn text bold like yours?** You need to set the font used by StdDraw. A single call at the start of your program such as StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18)) will do the job.  

**How long should I pause between steps in my animation loop?* We used StdDraw.show(10).  

**I can't print out an object's state to the console window because the instance variables are private. How do I do this?** You should implement toString() methods in your classes. These methods return a String containing all the details of an object's state. toString() gets called automatically whenever you try and append an object to a String, for example: System.out.println("OBJ + " + myObj)  

---

This README was adapted from an assignment page at Montana Tech: https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/alive/
