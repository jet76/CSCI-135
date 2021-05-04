import java.util.*;
public class Timer
{
    public static void main(String[] args) 
    {        
        String title = StdIn.readString();
        String artist = StdIn.readString();
        long bpm = Long.parseLong(StdIn.readString());
        int measure = Integer.parseInt(StdIn.readString());
        int riffs = Integer.parseInt(StdIn.readString());
        int [][] song = new int[riffs][measure];
        for (int i = 0; i < riffs; i++)
        {
            for (int j = 0; j < measure; j++)
            {
                song[i][j] = Integer.parseInt(StdIn.readString());                
            }
        }
        long timeNow = 0;
        long nextTic = 0;
        long ticTime = 15000 / bpm;
        int tics = 0;
        int riff = 0;
        final int numStrings = 55;
        GuitarString guitar[] = new GuitarString[numStrings];
        for (int i = 0; i < guitar.length; i++)
        {
            guitar[i] = new GuitarString(440.0 * Math.pow(2, (double) (i - 24) / 12));
        }
        StdDraw.text(0.5, 0.5, title + " by " + artist);
        while (true)
        {
            Date date = new Date();
            timeNow = date.getTime();
            if (timeNow >= nextTic)                
            {
                nextTic = timeNow + ticTime;
                if ((tics < measure) && (song[riff][tics] > -1)) guitar[song[riff][tics]].pluck();
                //System.out.println(riff + " " + tics + " " + song[riff][tics]);
                tics += 1;
            }
            double sample = 0.0;
            for (int i = 0; i < numStrings; i++)
            {
                sample += guitar[i].sample();
            }
            StdAudio.play(sample);
            for (int i = 0; i < numStrings; i++) // advance the simulation of each guitar string by one step
            {
                guitar[i].tic();
            }
            if (tics == measure)
            {
                tics = 0;
                riff += 1;
                if (riff == riffs) break;
            }
        }
    }
}
