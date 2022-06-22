public class LinkedList
{
	Node head; // head of list
    /* Linked list Node. This inner class is made static so that
    main() can access it */
    static class Node
    {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }
    public void printList()
	{
	Node n = head;
	while (n != null) {
	System.out.print(n.data + " ");
	n = n.next;
	}
	}
	public static void main(String[] args)
	{
	/* Start with the empty list. */
	LinkedList llist = new LinkedList();
	llist.head = new Node(1);
	Node second = new Node(2);
	Node third = new Node(3);
	llist.head.next = second;
	second.next = third;


}
}