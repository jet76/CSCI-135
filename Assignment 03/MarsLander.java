// name         : Joe Turner
// username     : jeturner
// description  : A video game where 1 player must manually pilot a spaceship to a safe landing within the bounds of a landing pad on Mars before its fuel runs out because the autopilot has failed. W key activates bottom thrusters, D key activates right thrusters, A key activates left thrusters, and each keypress uses 1 fuel. 
import java.awt.Font; // Used to set font style for drawing text
public class MarsLander
{
    public static void main(String[] args)
    {
        // Read the text file containing information for the level.
        int width = StdIn.readInt();
        int height = StdIn.readInt();
        String backgroundImage = StdIn.readString();
        String ship = StdIn.readString();
        String shipBottom = StdIn.readString();
        String shipLeft = StdIn.readString();
        String shipRight = StdIn.readString();
        String shipLanded = StdIn.readString();
        String shipCrashed = StdIn.readString();
        String shipDrawn = ship;
        int horizontalDistance = StdIn.readInt();
        int verticalDistance = StdIn.readInt();
        double shipX = StdIn.readDouble();
        double shipY = StdIn.readDouble();
        int fuel = StdIn.readInt();
        String thrusterSound = StdIn.readString();
        String goodLandingSound = StdIn.readString();
        String badLandingSound = StdIn.readString();
        double gravityTerm = StdIn.readDouble();
        double maxSurvivableVelocity = StdIn.readDouble();
        double thrustAmount = StdIn.readDouble();
        double landingPadCenter = StdIn.readDouble();
        double landingPadCenterDistanceToEdge = StdIn.readDouble();
        double velX = 0;
        double velY = 0;
        // Set the canvas and scale
        StdDraw.clear();       
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0.0, width);
        StdDraw.setYscale(0.0, height);
        // Start the game
        while (true)
        {        
            // Calculate gravity's effect on the ship
            velY += gravityTerm;
            // set/reset the standard ship image to be drawn
            shipDrawn = ship;
            // Check for player input via keyboard
            if (StdDraw.hasNextKeyTyped() && fuel > 0)
            {
                // Get the key pressed and store in variable
                char ch = StdDraw.nextKeyTyped();
                // If 'w' key pressed by player
                if (ch == 'w')
                {
                    // Increase the ship's Y velocity
                    velY += thrustAmount;
                    // Calculate fuel usage
                    fuel -= 1;
                    // Set bottom thruster ship image to be drawn
                    shipDrawn = shipBottom;
                    // Play the thruster sound
                    StdAudio.play(thrusterSound);
                }
                // If 'd' key pressed by player
                else if (ch == 'd')
                {
                    // Increase the ship's X velocity
                    velX += thrustAmount;
                    // Calculate fuel usage
                    fuel -= 1;
                    // Set the left thruster ship image to be drawn
                    shipDrawn = shipLeft;
                    // Play the thruster sound
                    StdAudio.play(thrusterSound);
                }
                // If 'a' key pressed by player
                else if (ch == 'a')
                {
                    // Decrease the ship's X velocity
                    velX -= thrustAmount;
                    // Calculate fuel usage
                    fuel -= 1;
                    // Set the right thruster ship image to be drawn
                    shipDrawn = shipRight;
                    // Play the thruster sound
                    StdAudio.play(thrusterSound);
                }              
            }
            // Adjust X coordinate of the ship
            shipX += velX;
            // Adjust Y coordinate of the ship
            shipY += velY;
            // If the ship's Y coordinate is less than or equal to 1 the ship has touched down
            if (shipY - verticalDistance <= 1)
            {
                // Set the ship's Y coordinate to the landed position
                shipY = 0 + verticalDistance;
                // If both velocities are within the limit and the lander has landed within the bounds of the landing pad then the ship has landed 
                if ((Math.abs(velX) <= maxSurvivableVelocity && Math.abs(velY) <= maxSurvivableVelocity) && (shipX - horizontalDistance >= landingPadCenter - landingPadCenterDistanceToEdge && shipX + horizontalDistance <= landingPadCenter + landingPadCenterDistanceToEdge))
                {
                    // Set the landed ship image to be drawn
                    shipDrawn = shipLanded;
                    // Play the good landing sound
                    StdAudio.play(goodLandingSound);
                }
                // Else the ship has crashed!
                else
                {
                    // Set the crashed ship image to be drawn
                    shipDrawn = shipCrashed;
                    // Play the crash sound
                    StdAudio.play(badLandingSound);
                }
            }

            // Set the pen color for text drawn
            StdDraw.setPenColor(StdDraw.RED);
            // Set the font for text drawn
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));
            // Draw the background image
            StdDraw.picture(width / 2, height / 2, backgroundImage);
            // Draw the ship
            StdDraw.picture(shipX, shipY, shipDrawn);
            // Draw the landing pad
            StdDraw.filledRectangle(landingPadCenter, 0, landingPadCenterDistanceToEdge, 3);
            // Draw the fuel reading
            StdDraw.text(0, height, "Fuel: " + fuel);
            // Pause for 100 milliseconds
            StdDraw.show(100);
            // If the ship has touched down 
            if (shipY == 0 + verticalDistance)
            {
                // Stop the game
                break;
            }
        }
    }
}