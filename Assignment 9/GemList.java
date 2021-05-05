/*************************************************************************
 * Name        : Joe Turner
 * Username    : jeturner
 * Description : Creates linked lists full of shiny, happy gems
 *************************************************************************/
public class GemList 
{
    int index; // used to keep track of position in the linked list
    private class Node // node stuff that could have been included to begin with
    {
        private Gem gem; // gem in the current node
        private Node next; // next node in the list
    }
    private Node first = null; // first node
    private Node last = null; // last node
    int size() // return the number of gems in the list   
    {
        int size = 0; // counter for list size
        Node current = first; // start at the first node
        while (current != null) // loop through all nodes
        {
            size += 1; // count the node
            current = current.next; // select the next node
        }
        return size; // return the size of the list
    }
    void draw(double y) // draw all the gems in the list at the given y-coordinate
    {
        index = 0; // counter for the list size which ise used to determine X coordinate of a gem being drawn
        Node current = first; // start at the first node
        while (current != null) // loop through all nodes
        {
            current.gem.draw(GemGame.indexToX(index), y); // draw the current gem
            index ++; // increment the count so we know where to draw the next gem
            current = current.next; // select the next node
        }
    }
    public String toString() // return a string representation of the list
    {
        String result = ""; // empty string that will eventually hold the results we want to return
        Node current = first; // select the first node
        while (current != null) // loop through all nodes
        {
            result += current.gem + " "; // add the current gem to the string
            current = current.next; // select the next node
        }
        return result; // return the string containing results
    }
    void insertBefore(Gem gem, int index) // insert the given gem before the 0-based index in the existing list
    // if index is >= size of the list, the new gem is inserted at the end
    {
        if (first == null) // if there are no gems in the list
        {
            Node node = new Node(); // new node
            node.gem = gem; // assign a gem to the node
            node.next = first; // set the next node in the list to newly created node
            first = node; // set the first node to new node
            last = node; // set the last node to new node
        }
        else if (index <= 0) // if the gem is going in at the start of the list
        {
            Node node = new Node(); // new node
            node.gem = gem; // assign a gem to the node
            node.next = first; // set the next node in the list to the current first node
            first = node; // set the first node in list to the newly created node
        }
        else if (index >= this.size()) //if the gem is going at the end of the list
        {
            Node oldlast = last; // get the old last node
            last = new Node(); // create a new nod to be the last node
            last.gem = gem; // assign the gem to the newly created last node
            last.next = null; // set the next node
            oldlast.next = last; // link the old last node to the new last node
        }
        else // add a new gem between other gems in the list
        {
            Node current = first; // select the first node
            this.index = 0; // counter for keeping track of position in the list
            while (current != null) // loop through all nodes
            {
                if (this.index == index - 1) // when we reach the spot before the insert position
                {
                    Node newnext = current.next; // get the next node in the list for later linking to new node
                    Node node = new Node(); // new node
                    node.gem = gem; // assign gem
                    node.next = newnext; // link new node to the next node in list
                    current.next = node; // link the current node to the newly created node
                }
                else current = current.next; //  get the next node
                this.index ++; // increment the count
            }
        }
    }
    int score() // calculate the total score of the list 
    {
        Node current = first; // select first node
        int blockscore = 0; // used for calculating block scores
        int score = 0; // used for total score
        int points = 0; // used for gem point value
        int multiplier = 1; // stores the multiplier
        GemType type; // used for current gem type
        GemType lasttype = null; // used to determine if multiplier needs incrementing
        while (current != null) // loop through all nodes
        {
            type = current.gem.getType(); // get the current gem type
            points = current.gem.getPoints(); // get the current gem points
            if (type == lasttype) // if current gem is same type as previous gem
            {
                multiplier ++; // increment the multiplier
                blockscore += points; // add gem's point value to the block score
            }
            else // otherwise
            {
                score += multiplier * blockscore; // calculate the last block score and add it to the total score
                multiplier = 1; // reset the multiplier
                blockscore = 0 + points; // set new block score to current gem points 
            }
            lasttype = type; // set the last gem type looked at
            current = current.next; // select the next node
        }
        score += blockscore * multiplier; // add the final block score to the total score (last gem of block of gems)
        return score; // return the total score
    }
    public static void main(String [] args)
    {
        GemList list = new GemList();
        System.out.println(list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.9);		

        list.insertBefore(new Gem(GemType.BLUE, 10), 0);
        System.out.println(list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.8);

        list.insertBefore(new Gem(GemType.BLUE, 20), 0);
        System.out.println(list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.7);

        list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
        System.out.println(list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.6);

        list.insertBefore(new Gem(GemType.ORANGE, 0), 2);
        list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
        System.out.println(list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.5);

        list.insertBefore(new Gem(GemType.BLUE, 50), 99);
        System.out.println(list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.4);

        list.insertBefore(new Gem(GemType.GREEN, 50), 2);
        System.out.println(list);
        System.out.println("size = " + list.size() + ", score = " + list.score());
        list.draw(0.3);		
    }	
}
