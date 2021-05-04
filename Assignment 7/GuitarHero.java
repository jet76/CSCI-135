// name        : Joe Turner
// username    : jeturner
// description : An unholy abomination of GuitarStrings and RingBuffers that will turn your keyboard into a piano with monochrome keys that sounds like a synthesized guitar. Pretty fun stuff.
import java.awt.Font;
public class GuitarHero
{
	public static void main(String[] args) {
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		int size = 37;
		double sample = 0.0;
		GuitarString gsArray[] = new GuitarString[size];
		boolean start = false;
		for (int i = 0; i < gsArray.length; i++)
		{
			gsArray[i] = new GuitarString(440.0 * Math.pow(2, (double) (i - 24) / 12));
		}
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set font properties
		StdDraw.text(0.5, 0.5, "Click here to start");
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 72)); // Set font properties
		do // Loop until user presses a mouse button to start the game
        {
            start = StdDraw.mousePressed(); // Check to see if the user has pressed a mouse button
        } while (!start);
		StdDraw.clear();
		while (true) // the main input loop 
		{            
			if (StdDraw.hasNextKeyTyped()) // check if the user has typed a key, and, if so, process it
			{
				char key = StdDraw.nextKeyTyped();
				String keyStr = Character.toString(key);
				int index = keyboard.indexOf(key);
				StdDraw.clear();
				if (index >= 0 && index < size)
				{
					StdDraw.setPenColor(StdDraw.BLACK);
					if (index == size - 1) StdDraw.text(0.5, 0.5, "Space");
					else StdDraw.text(0.5, 0.5, keyStr);
					gsArray[index].pluck(); // pluck the corresponding string
				}
				else
				{
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.text(0.5, 0.5, keyStr);
				}
			}            
			for (int i = 0; i < size; i++) // compute the superposition of the samples
			{
				sample += gsArray[i].sample();
			}            
			StdAudio.play(sample); // send the result to standard audio
			sample = 0.0; // Reset the sample            
			for (int i = 0; i < size; i++) // advance the simulation of each guitar string by one step
			{
				gsArray[i].tic();
			}
			//StdDraw.show(10);
		}
	}
}
