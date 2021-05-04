// Name        : Joe Turner
// Username    : jeturner
// Description : Client program containing the main() method that runs the game. Handles reading in the game control file using StdIn. It will create the Player object and an array of Enemy objects. When the program is first started, it prints out a text representation of the Player and Enemy objects.

// This is needed if you want to change the text font using StdDraw.setFont()
import java.awt.Font;

public class StayingAlive
{
    public static void main(String[] args)
    {  
        int score = 0; // Start the score at 0
        
        // Read from the file containing game info
        String bg = StdIn.readString(); // Get the background image
        String pImage = StdIn.readString(); // Get the player's image
        Double pX = Double.parseDouble(StdIn.readString()); // Get the player's starting X position
        Double pY = Double.parseDouble(StdIn.readString()); // Get the player's starting Y position
        Double pRadius = Double.parseDouble(StdIn.readString()); // Get the player's radius
        int pSpeed = StdIn.readInt(); // Get the player's speed
        Player playerOne = new Player(pImage, pX, pY, pRadius, pSpeed); // Call the player class to generate the player
        System.out.println("PLAYER: " + playerOne.toString()); // Output the starting data for player

        int eNum = StdIn.readInt(); // Get the number of enemies
        Enemy [] enemy = new Enemy[eNum]; // Declare an array to hold the enemies
        
        for (int i = 0; i < eNum; i++) // loop for each enemy
        {
            String eImage = StdIn.readString(); // Get the enemy's image
            Double eX = Double.parseDouble(StdIn.readString()); // Get the enemy's starting X position
            Double eY = Double.parseDouble(StdIn.readString()); // Get the enemy's starting Y position
            Double eRadius = Double.parseDouble(StdIn.readString()); // Get the enemy's radius
            Double eVelX = Double.parseDouble(StdIn.readString()); // Get the enemy's starting X velocity
            Double eVelY = Double.parseDouble(StdIn.readString()); // Get the enemy's starting X velocity
            int eAngle = (int)(359 * Math.random()); // Set a random angle for the enemy's image
            enemy[i] = new Enemy(eImage, eX, eY, eRadius, eVelX, eVelY, eAngle); // Call the enemy class to generate an enemy
            System.out.println("ENEMY " + i + ": " + enemy[i].toString()); // Out put the starting data for the enemy
        }
        
        StdDraw.setPenColor(StdDraw.RED); // Set pen color
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set font properties
        StdDraw.picture(0.5, 0.5, bg); // Draw the background 
        playerOne.draw(); //Draw the player
        for (int i = 0; i < enemy.length; i++) // Loop to draw the enemies
        {
            enemy[i].draw(); // Draw the enemy
        }
        boolean start = false; // Declare a variable to determine whether the user has started the game
        do // Loop until user presses a mouse button to start the game
        {
            start = StdDraw.mousePressed(); // Check to see if the user has pressed a mouse button
        } while (!start);
        while (true) // Play the game
        {
            StdDraw.picture(0.5, 0.5, bg); // Draw the background            
            Double mX = StdDraw.mouseX(); // Get the mouse's X coordinate
            Double mY = StdDraw.mouseY(); // Get the mouse's Y coordinate
            playerOne.updatePos(mX, mY); // Update player position   
            for (int i = 0; i < enemy.length; i++) // Loop for enemies
            {
                enemy[i].updatePos(); // Update enemy position                
                boolean intersect = false; // Variable for determining if player has collided with an enemy
                do // check for collision between player and enemies
                {
                    int j = 0;
                    intersect = false; // Declare a a boolean variable to determine collision
                    while ((j <= i) && (!intersect)) // Loop to check for collision between player and all enemies
                    {
                        intersect = playerOne.intersects(enemy[j]); // Check for collision
                        j++; // increment the count
                    }
                } while (intersect); // Stop if there has been a collision
                enemy[i].draw(); // Draw the enemy
            }     
            playerOne.draw(); //Draw the player
            score += 1; // Increment the score
            StdDraw.text(0.1, 1, "Score: " + score); // Draw the score
            StdDraw.show(10); // Update the graphics
        }
    }
}
