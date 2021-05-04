# ASSIGNMENT #8  

In this assignment, you will be building a video game called Bombs Away. In the process, you will learn how to use the Java ArrayList collection.	 

**Bombs Away**
The object of the game is to avoid any bombs hitting the ground and destroying your civilization. Luckily you have a powerful laser that you can fire at the falling bombs. You aim your laser with the mouse and fire it by pressing the button. Bombs are different sizes and require varying amounts of lasering to destroy. But watch out, some of the bombs are splitters. When a splitter is destroyed, it splits into two bomblets that also need to be destroyed. After destroying 10 bombs, you get a single shot nuke. The nuke destroys all currently falling bombs. The nuke is fired by pressing the spacebar. After using the nuke, you have to destroy another 10 bombs with your laser before you get another one. Good luck!  

**Classes:** You should start by downloading the file [bombs.zip](https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/bombs/bombs.zip). The zip file also contains stub versions for the two classes you will be developing as well as the background image file. You will need to add instances variables, the bodies of instance methods, and the body of the main() method. Here is a description of the two classes:  

* BombsAway.java - Client program containing the main() method that runs the game. The main() method will be creating the new Bomb objects and storing them in a Java ArrayList. By using an ArrayList, you'll be able to create a varying number of falling bombs as well as remove ones as they are destroyed. This would be difficult to do with a normal array of objects.  

* Bomb.java - The class that represents a falling bomb. A bomb knows things like: x-position, y-position, y-velocity, radius, initial strength, current strength, and whether it is a splitter. The class can do things like create a new bomb at the top of the screen, create a new bomb based on a parent splitter bomb, draw itself, see if a point is on the bomb, see if a bomb is a splitter, update its position, get its x-position, get its y-position, get its radius, and reduce its strength.  

**Canvas size and coordinates.** In this assignment, we'll leave the canvas size at the default size of 512 x 512 pixels. We will also use the default coordinate system which has the lower-left corner being (0.0, 0.0) and the upper-right corner being (1.0, 1.0). For a background image, draw mcommand.jpg at the center of the window.  

**Creating new bombs.** At every time step of your animation loop, there is a 1% chance that a new bomb will be created. When a new bomb is constructed, it should be assigned values as follows:  
* A random x-location in [0.0, 1.0).
* A fixed y-location of 1.0.
* A random y-velocity in [-0.003, -0.001).
* A radius in [0.02, 0.05).
* An initial strength which is a random integer value in [10, 50). The strength determines how many times it must be lasered before it is destroyed.
* Whether the bomb is a splitter or not. There is a 10% chance a new bomb is a splitter.  

**Bomb dynamics and appearance.** A Bomb updates its position in the updatePos() method by adding its *y-velocity* to its *y-position*. A bomb starts out as completely blue (RGB 0.0, 0.0, 1.0). As it gets lasered, it starts to turn red (RGB 1.0, 0.0, 0.0). We'll use a linear interpolation between blue and red based on how much of a bomb's initial strength is left. If a bomb started with an initial strength of T and currently has L strength left, the current color RGB is (1.0 - L/T, 0.0, L/T).  

**Firing the laser.** You can detect when the user presses the mouse button using StdDraw.mousePressed(). Your game should draw a red line from the lower-left corner to the current mouse coordinate when the laser is fire. The laser hits a falling bomb if the mouse x- and y-position intersects the circle representing the bomb. The laser can hit multiple bombs simultaneously if they are overlapping. Your intersects() instance method should determine the Euclidean distance between the mouse coordinate and the center of a bomb. If this distance is less than or equal to the radius of the bomb, it counts as a hit. Each hit reduces the remaining strength of a bomb by one.  

**Creating bomblets.** Should a bomb be destroyed that is a splitter, two new bombs should be created. The two new bombs should be assigned values as follows:  
* The first bomblet is created at the x-location of the parent bomb minus the parent's radius. The second bomblet is created at the x-location of the parent bomb plus the parent's radius.
* The same y-location as the parent bomb had before it was destroyed.
* The y-velocity of the parent bomb plus 10%.
* A radius that is half the size of the parent bomb.
* An initial strength which is the same as the parent bomb started with.
* Bomblets cannot be splitters themselves.  

**Game over.** The game is over if the y-position of the center of any bomb reaches the line y = 0.  

**Scoring.** You should display a score in the upper-left corner. You get one point for every bomb you destroy. In the case of a splitter, you get a point for destroying the big bomb and another two if you successfully destroy both bomblets.  

**Nukes.** After you score ten points, you gain the power of activating a nuke that destroys all falling bombs. You should indicate the nuke is available by putting an asterisk after the number in the scoreboard. The nuke is activated by pressing the spacebar. The screen should flash red for 100 milliseconds when the nuke is fired and then all bombs should be destroyed. Be sure to count the destroyed bombs in your score. After using the nuke, the user has to laser 10 more bombs in the normal way before the nuke can be used again. You can only have one nuke in your stockpile.  
Is there an easy way to generate random numbers in a certain integer or double range? You can use the static methods in StdRandom.java to help you do this.

**How long should I pause between steps in my animation loop?** We used StdDraw.show(10).  

**How do I draw a line?** StdDraw has a method for drawing a straight line between two points: StdDraw.line(double x1, double y1, double x2, double y2).  

**How do I detect when the user hits the spacebar?** First check if a key has been pressed by calling StdDraw.hasNextKeyTyped(). Then check which character was typed using StdDraw.nextKeyTyped(). The spacebar character is simply a space between two single apostrophes.  

**How do I create an ArrayList?** An ArrayList is a special type of thing in Java called a generic. It can contain a collection of any reference type. You need to specify the type it is going to hold when you create the ArrayList. For example to create a new collection that can hold Ball objects, you would do: ArrayList<Ball> balls = new ArrayList<Ball>();  

**What methods are available for an ArrayList?** A summary of all methods is available here. For the assignment, you'll probably want to use the add(E e), remove(int index), and clear() methods.  

**How do I draw in a specific RGB color?** You need to create a new Color object, constructing it with the values for the red, green, and blue components. For example, to create a new color halfway be/tween red and blue you would do: Color c = new Color(0.5f, 0.0f, 0.5f); Note that the constructor to Color does not take double parameters, so you may need to cast things to a float. To be able to use the Color class, you'll need to import java.awt.*;  

**What mouse related methods do I need?** You'll need StdDraw.mousePressed() to decide if the user is currently holding down the mouse button. You'll also need StdDraw.mouseX() and StdDraw.mouseY() to determine the mouse pointer's location. Note that the Bomb class is exhibiting good data encapsulation, it doesn't actually know or care how clients are determining when and where user actions occur. This is the job of BombsAway and so all mouse and keyboard input should be handled in this client class and not inside the Bomb class.  

---

