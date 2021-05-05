// Name        : Keith Vertanen
// Username    : kvertanen
// Description : Ask the user to type in the name, price, and quantity of a
//               product and then print out the total.
public class OrderProduct
{
    public static void main(String [] args)
    {
        // Read in the product name as text
        System.out.print("Name of product: ");
        String product = StdIn.readString();
        
        // Read in the price, this may have a decimal points so use a double
        System.out.print("Price: ");
        double price = StdIn.readDouble();
        
        // Read in the quantity, we assume this is an integer
        System.out.print("Quantity: ");
        int qty = StdIn.readInt();
        
        // Calculate the order total
        double total = price * qty;
        
        // Print out a line showing the quantity, product, price each, and total price.
        System.out.println(qty + " " + product + " " + price + " " + total);
    }    
}
