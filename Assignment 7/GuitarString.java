// name        : Joe Turner
// username    : jeturner
// description : Implements a GuitarString for GuitarHero
public class GuitarString
{
    private RingBuffer rb;
    private int size; 
    private double decayFactor = 0.996;
    private int tics;
    public GuitarString(double frequency)  // create a guitar string of the given frequency, using a sampling rate of 44,100
    {
        size = (int) Math.round(44100 / frequency);
        tics = 0;
        rb = new RingBuffer(size);
        for (int i = 0; i < size; i++) rb.enqueue(0); 
    }
    public GuitarString(double[] init)     // create a guitar string whose size and initial values are given by the array
    {
        size = init.length;
        rb = new RingBuffer(size);
        for (int i = 0; i < size; i++)
        {            
            rb.enqueue(init[i]);
        }
    }
    public void pluck()                         // set the buffer to white noise
    {
        for (int i = 0; i < rb.size(); i++)
            {
            rb.dequeue();
            rb.enqueue(Math.random() - 0.5); 
            }        
    }
    public void tic()                           // advance the simulation one time step
    {
        tics ++;
        double x = rb.dequeue(); 
        double y = rb.peek();
        rb.enqueue(((x + y) / 2) * decayFactor);
    }
    public double sample()                        // return the current sample
    {
        return rb.peek();
    }
    public int time()                          // return number of tics
    {
        return tics;
    }
    public static void main(String[] args) 
    {
        int N = Integer.parseInt(args[0]);
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
