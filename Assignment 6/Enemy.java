// Name        : Joe Turner
// Username    : jeturner
// Description : This class represents the various things that will be flying around trying to kill the player. It knows things like its x-location, y-location, x-velocity, y-velocity, radius, and image. The Enemy objects spin as then bounce around, so they also know their current rotation angle. An Enemy object can do things such as draw itself, update its position, get its x-position, get its y-position, get its radius, and print itself out to the console.

public class Enemy
{
    String eImage; // Declare a variable to hold the enemy image
    Double eX, eY, eRadius, eVelX, eVelY; // Declare variables to hold enemy coordinates, radius, and velocity
    int eAngle; // Declare a variable to hold enemy radius

    public Enemy(String image, double x, double y, double radius, double velX, double velY, int angle) // Accepts x and y coordinates, radius, x and y velocity, and angle the creates an instance of the enemy
    {
        eImage = image; // Set the image for the enemy
        eX = x; // Set the x coordinate for the enemy
        eY = y; // Set the y coordinate for the enemy
        eRadius = radius; // Set the radius for the enemy
        eVelX = velX; // Set the X velocity for the enemy
        eVelY = velY; // Set the Y velocity for the enemy
        eAngle = angle; // Set the angle for the enemy image
    }

    public String toString() // Returns info about the enemy
    {
        String result = "(" + eX + ", " + eY + ") vel (" + String.format("%.4f", eVelX) + ", " + String.format("%.4f", eVelY) + ") r=" + eRadius + " angle=" + eAngle + " " + eImage; // Concatenate enemy info into a string
        return result; // return the enemy info string   
    }

    public void draw() // Draws the enemy
    {
        StdDraw.picture(eX, eY, eImage, eAngle); // draw the enemy
    }

    public double getX() // Returns the enemy's X coordinate
    {
        return eX; // Return the enemy's X coordinate
    }

    public double getY() // Returns the enemy's Y coordinate
    {
        return eY; // Return the enemy's Y coordinate
    }

    public double getRadius() // Returns the enemy's radius
    {
        return eRadius; // Return the enemy's radius
    }

    public void updatePos() // Updates the enemy's position
    {
        eX += eVelX; // Calculate new enemy X velocity
        if (eX > 1.0) // Check to see if the enemy has hit the right edge
        {
            eX = 1.0; // Set the enemy's X coordinate to 1.0
            eVelX *= -1; // Invert the enemy's X velocity
        }
        if (eX< 0.0) // Check to see if the enemy has hit the left edge
        {
            eX = 0.0; // Set the enemy's X coordinate to 0.0
            eVelX *= -1; // Invert the enemy's X velocity
        }
        eY += eVelY; // Calculate new enemy Y velocity
        if (eY > 1.0) // Check to see if the enemy has hit the top edge
        {
            eY = 1.0; // Set the enemy's X coordinate to 1.0
            eVelY *= -1; // Invert the enemy's Y velocity
        }
        if (eY < 0.0) // Check to see if the enemy has hit the bottom edge
        {
            eY = 0.0; // Set the enemy's X coordinate to 0.0
            eVelY *= -1; // Invert the enemy's Y velocity
        }
        eAngle -= 1; // Rotate the enemy image one degree
        if (eAngle < 0) // Check to see if the enemy has completed a full rotation
            eAngle = 359; // Start a new rotation if a full rotation has completed
    }   
}
