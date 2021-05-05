/*************************************************************************
 * Name        : Joe Turner
 * Username    : jeturner
 * Description : Makes gems having 1 of 3 possible colors and 1 of 11 possible point values.
 *************************************************************************/

import java.awt.Color;
import java.awt.Font;

enum GemType {GREEN, BLUE, ORANGE};

public class Gem 
{	
    int points; // holds point value of a gem
    GemType type; // holds the type (color) of a gem
    String image; // holds the image file name for a gem.
    final int[] pointVals = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50}; // array of possible point values for gems

    public Gem() // create a gem with random color and point value
    {
        this.type = GemType.values()[(int) (Math.random() * GemType.values().length)]; // assign a random type to the gem
        this.points = pointVals[(int) (Math.random() * pointVals.length)]; // assign a random point value from possible point values to the gem
    }
    Gem(GemType type, int points) // create a gem with the specified color and point value
    {        
        this.points = points; // assign the specified point value to the gem
        this.type = type; // assign the specified type to the gem
    }
    public String toString() // return a string representation of the gem
    {
        return this.getType() + ", " + this.getPoints(); // create a string describing a gem
    }
    GemType getType() // get the type of the gem
    {
        return type; // return the type of gem
    }
    int getPoints() // get point value of the gem
    {
        return points; // return the point value for the gem
    }
    void draw(double x, double y) // draw the gem
    {
        switch(this.type) // get the gem type
        {
            case GREEN: // selected gem is green
                image = "gem_green.png"; // set image to the green gem
                break;
            case BLUE: // selected gem is blue
                image = "gem_blue.png"; // set image to the blue gem
                break;
            case ORANGE: // selected gem is orange
                image = "gem_orange.png"; // set image to the orange gem
                break;
        }
        StdDraw.picture(x, y, image); // draw the image for a gem at x,y
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14)); // set the font properties
        StdDraw.setPenColor(Color.white); // set the pen color
        StdDraw.text(x, y, "" + points); // draw the point value of the gem
    }
    // Test main method
    public static void main(String [] args)
    {
        final int maxGems = 16;

        // Create a gem of each type
        Gem green  = new Gem(GemType.GREEN, 10);
        Gem blue   = new Gem(GemType.BLUE, 20);
        Gem orange = new Gem(GemType.ORANGE, 30);
        System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
        System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
        System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
        green.draw(0.3, 0.7);
        blue.draw(0.5, 0.7);
        orange.draw(0.7, 0.7);

        // A row of random gems
        for (int i = 0; i < maxGems; i++)
        {
            Gem g = new Gem();
            g.draw(1.0 / maxGems * (i + 0.5), 0.5);
        }
    }
}
