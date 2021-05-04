// Name        : Joe Turner
// Username    : jeturner
// Description : This class represents the player. It knows things like the x-location and y-location of the player. It also knows the image filename used to display the player as well as the radius of the player. We assume the player is a circle, using the radius to detect collision with the Enemy objects. The Player object also has a speed update factor. The speed factor is used to determine how fast the player moves to the mouse location (details below). The Player object can do things such as draw itself, update its position, check intersection with an Enemy object, and print itself out to the console.
public class Player
{
    private String pImage; // Variable to hold player image
    private Double pX; // Variable to hold player X coordinate
    private Double pY; // Variable to hold player Y coordinate
    private Double pRadius; // Variable to hold player radius
    private int pSpeed; // Variable to hold player speed
    
    public Player(String imageFilename, double x, double y, double radius, int speed) // Accepts x and y coordinates, radius, and speed then creates a player instance with those values
    {
        pImage = imageFilename; // Set the player's image
        pX = x; // Set the player's X coordinate
        pY = y; // Set the player's Y coordinate
        pRadius = radius; // Set the player's radius
        pSpeed = speed; // Set the player's speed
    }
    
    public void draw() // draws the player instance
    {
        StdDraw.picture(pX, pY, pImage); // Draw the player image
    }

    public void updatePos(double mouseX, double mouseY) // Accepts x an y coordinates of the mouse and updates the player position
    {
        pX += (mouseX - pX) / pSpeed; // Calculate player's new X position
        if (pX < 0.0) // Check to see if the player has hit the left edge
            pX = 0.0; // Set the X coordinate to 0.0 if the player has hit the left edge
        if (pX > 1.0) // Check to see if the player has hit the right edge
            pX = 1.0; // Set the X coordinate to 1.0 if the player has hit the right edge
                
        pY += (mouseY - pY) / pSpeed; // Calculate player's new X position
        if (pY < 0.0) // Check to see if the player has hit the bottom edge
            pY = 0.0; // Set the Y coordinate to 0.0 if the player has hit the right edge
        if (pY > 1.0) // Check to see if the player has hit the top edge
            pY = 1.0; // Set the Y coordinate to 1.0 if the player has hit the top edge
    }

    public boolean intersects(Enemy enemy) // Accepts one enemy at a time and checks for collision between the enemy and player. Returns true for a collison, false if no collision.
    {
        double x = pX - enemy.getX(); // Calculate the difference between player X and enemy X
        double y = pY - enemy.getY(); // Calculate the difference between player Y and enemy Y
        double d = Math.sqrt(x * x + y * y); // Calculate the distance between enemy and player
        if (d < pRadius + enemy.getRadius()) // If distance is less than the sum of player and enemy radius
            return true; // A collision has occurred
        return false; // Else no collision has occurred
    }

    public String toString() // Returns information about the player
    {
        String result = "(" + pX + ", " + pY + ") r=" + pRadius + " speed=" + pSpeed + " " + pImage; // Concatenate player info to a string      
        return result; // Return the player info string
    }
}