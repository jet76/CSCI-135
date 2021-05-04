// Name        : Joe Turner
// Username    : jeturner
// Description : Drops bombs that you can destroy with a laser. The bombs being dropped were not created by terrorists.

// Import so we can use the ArrayList collection
import java.util.ArrayList;
import java.awt.Color;
// Import so you can change the text font if you want
import java.awt.Font;

public class BombsAway
{
    public static void main(String [] args)
    {
        ArrayList<Bomb> bombs = new ArrayList<Bomb>(); // ArrayList for Bombs
        int score = 0; // score
        int destroyed = 0; // use to keep track of how many bombs have been lasered
        boolean nuke = false; // keeps track of whether the player has a nuke or not
        boolean gameover = false; // used to determine when the game has ended
        StdDraw.picture(0.5, 0.5, "mcommand.jpg"); // draw initial background
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18)); // set font properties for drawing the score
        StdDraw.textLeft(0, 1, "Score: " + score); // draw initial score
        while (true) // start the game and play until game over
        {
            if (StdRandom.uniform(0, 100) == 0) // roll 1d100. Generate a new bomb on a roll of 100 (actually 0). 
            {
                Bomb newBomb = new Bomb(); // make a new bomb
                bombs.add(newBomb); // add the new bomb to the ArrayList	            
            }
            StdDraw.picture(0.5, 0.5, "mcommand.jpg"); // draw the background            
            if (StdDraw.mousePressed()) // check for the laser being fired
            {
                StdDraw.setPenColor(Color.red); // set pen color
                StdDraw.line(0.0, 0.0, StdDraw.mouseX(), StdDraw.mouseY()); // draw the laser
                for (int i = bombs.size() -1; i >= 0; i--) // loop through the bombs
                {
                    Bomb bomb = bombs.get(i);   // get the current bomb
                    if (bomb.intersects(StdDraw.mouseX(), StdDraw.mouseY())) // if the laser hit the bomb
                    {
                        int bombStr = bomb.attack(); // reduce bomb strength
                        if (bombStr == 0 ) // if the bomb strength has hit 0
                        {
                            if (bomb.splitter) // if the bomb is a splitter then split it
                            {
                                bombs.add(new Bomb(bomb, bomb.getX() + bomb.getRadius())); // create first splitter
                                bombs.add(new Bomb(bomb, bomb.getX() - bomb.getRadius())); // create second splitter
                            }
                            bombs.remove(i); // remove the destroyed bomb
                            score += 1; // increment score
                            destroyed += 1; // increment current destroyed count
                            if (destroyed == 10) // if the player has destroyed 10 bombs with the laser
                            {
                                destroyed = 0; // reset the count
                                nuke = true; // activate nuke ability
                            }
                        }
                    }
                }
            }
            if (StdDraw.hasNextKeyTyped()) // if the player presses a key
            {
                if (StdDraw.nextKeyTyped() == ' ' && nuke == true) //  if that key is the space bar and nuke ability is activated
                {
                    nuke = false; // deactivate nuke ability
                    for (int i = bombs.size() -1; i >= 0; i--) // loop through the bombs
                    {
                        score += 1; // increment score for each bomb nuked
                    }
                    bombs.clear(); // clear the bombs that were nuked
                    StdDraw.clear(Color.red); // make the canvas red for the nuke effect
                }
            }            
            for (int i = bombs.size() -1; i >= 0; i--) // loop through the bombs
            {
                Bomb bomb = bombs.get(i); // get the current bomb                               
                bomb.draw(); // draw the current bomb;
                if (bomb.getY() <= 0.0) // if a bomb has reached the bottom the player loses
                {
                    StdDraw.setPenColor(Color.red); // change pen color to red
                    StdDraw.setFont(new Font("SansSerif", Font.BOLD, 36)); // change font properties
                    StdDraw.text(0.5, 0.5, "GAME OVER"); // show GAME OVER
                    gameover = true; // change the variable to true
                }
                bomb.updatePos(); // update the bomb's position
            }            
            StdDraw.setPenColor(Color.red); // set pen color
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18)); // set font properties
            if (nuke) StdDraw.textLeft(0, 1, "Score: " + score + " *"); // if the player has nuke ability display an * next to score
            else StdDraw.textLeft(0, 1, "Score: " + score); // otherwise no * with score                
            StdDraw.show(10); // pause briefly before next round
            if (gameover) return; // stop when the game is over                
        }
    }
}
