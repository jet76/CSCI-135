// name        : Joe Turner
// username    : jeturner
// description : Implements the RingBuffer for a GuitarString
public class RingBuffer
{
    private int Capacity;
    private int size;
    private int first;
    private int last;
    private double[] rbArray;
    public RingBuffer(int capacity)  // create an empty ring buffer, with given max capacity
    {
        Capacity = capacity;
        rbArray = new double[capacity];
        size = 0;
        first = 0;
        last = 0;
    }
    public int size()                    // return number of items currently in the buffer
    {
        return size;
    }
    public boolean isEmpty()                 // is the buffer empty (size equals zero)?
    {
        if (size == 0) return true;
        else return false;
    }
    public boolean isFull()                  // is the buffer full  (size equals capacity)?
    {
        if (size == Capacity) return true;
        else return false;
    }
    public void enqueue(double x)         // add item x to the end
    {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        size ++;
        rbArray[last] = x;
        last ++;
        if (last == Capacity) last = 0; 
    }
    public double dequeue()                 // delete and return item from the front
    {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        size --;
        double val = rbArray[first];
        first ++;        
        if (first == Capacity) first = 0;
        return val;
    }
    public double peek()                    // return (but do not delete) item from the front
    {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        return rbArray[first];
    }

    public static void main(String[] args) 
    {
        int N = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) 
        {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.dequeue();            
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        System.out.println(buffer.peek());
    }
}
