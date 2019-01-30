import javax.swing.JOptionPane;

public class LinkedList{
	private Node head;
	
	//when a linked list is created, the head is null because there is no value stored
	public LinkedList(){
		head = null;
	}
	
	
	public void addAtFront(String name, String phone, String email, String reason, int priority){ //method adds a node to the front of the linked list
		Node newNode = new Node(name, phone, email, reason, priority);
		newNode.setNext(head); //sets the next node to the old head
		head = newNode; //sets the head as the new node
	}
	
	/* this method is used to find the desired position that the new node must be put in
	 * the method uses a counter to check for the position
	 */
	public int findPosition(String name, String phone, String email, String reason, int priority){
		
		int position = 1; //position counter is set to 1 all the time
		Node newNode = head; //the new node begins at the head
		
		if(head==null){ //if there is no head, the position must be (1), position is returned as (1)
			return 1;
		}
		
		else{
			if(priority<head.getPriority()){ //if the priority is less than that of the head at the moment, the new node must be at the head
				return 1; //position returned is 1
			}
			else{ //if the priority is not less than the head's priority, following lines are executed
				position=2; //position counter starts at 2 because it cannot be 1
				while(newNode.next!=null){ //runs while loop until the next node is valued at null, then returns
					if(priority==1){ //if priority of new node is 1
						if(newNode.next.getPriority()==1){
							newNode = newNode.next; //if the priority of the next node is equal to this priority, the node becomes the next node to keep going through loop
							position++; //position is incremented by one
						}
						else if(newNode.next.getPriority()==2){ //if the next node has a priority greater than 1, the previous position can be returned
							return position;
						}
						else if(newNode.next.getPriority()==3){ //if the next node has a priority greater than 1, the previous position can be returned
							return position;
						}
					}
					else if(priority==2){ //if the priority of the new node is 2
						if(newNode.next.getPriority()==1){ //if the priority of the next node is less than or equal to that of the new node, the next node must be looked at
							newNode = newNode.next;
							position++; //position must go up
						}
						else if(newNode.next.getPriority()==2){ //if the priority of the next node is less than or equal to that of the new node, the next node must be looked at
							newNode = newNode.next;
							position++; //position must go up
						}
						else if(newNode.next.getPriority()==3){ //if the priority of the next node is greater than the new node, this position can be returned
							return position;
						}
					}
					else if(priority==3){ //for priority 3, position must continue to be incremented until we reach the end of the list
						if(newNode.next.getPriority()==1){
							newNode = newNode.next;
							position++;
						}
						else if(newNode.next.getPriority()==2){           /*for all of these next priorities, they are less than a priority of 3
							newNode = newNode.next;						  * so the position must be incremented until the end is reached */
							position++;
						}
						else if(newNode.next.getPriority()==3){
							newNode = newNode.next;
							position++;
						}
					}
				}
				return position; //once the end of the list is reached, that position is returned
			}
		}
	}
	
	public void insertAtPosition(String name, String phone, String email, String reason, int priority, int position){ //when the insert at position method is called
	
	    Node newNode = new Node(name, phone, email, reason, priority);
	    newNode.next = null;

	    if (head == null) { //if the head is null, return because nothing to be added
	        return;
	    }
	    if (position == 0) { //if the position is 0, which is impossible, set the head to the new node and return
	        newNode.next = head;
	        head = newNode;
	        return;
	    }
	    Node prev = null; //creates a new previous node which is originally set to null
	    Node current = head; //starts from the head, new node object called current
	    int i = 0; //starts a counter at 0
	    while (current !=null && i < position-1) { //while the current is not null, and the value before desired position hasn't been reached
	        prev = current; //previous node becomes the current node
	        current = current.next; //current node becomes the next node
	        i++; //increment
	    }
	    newNode.next = prev.next; 
	    prev.next = newNode; //sets the next node to the previous node
	    return; //exits the method
	}
	
	public void remove(){ //method to remove the front node, always the one that must be removed

		String name = head.getName();            //these lines of code get the values of the head node being removed so they can be printed
		String telephone = head.getPhone();
		String email = head.getEmail();
		String reason = head.getReason();
		int priority = head.getPriority();
		
		Node current = head;  //creates a new node and sets it to the head

		head = current.getNext(); //sets the head to the next node from the new node
	
		JOptionPane.showMessageDialog(null, "               Removed Call" + "\n" + "Name: " +name+ "\n"+"Telephone Number: "+telephone+ "\n"+"Email Address: "+email+ "\n"+"Reason for Call: "+reason+"\n"+"Priority: "+priority);
		//message dialog above shows the user the data of the node they removed
	}
	
	public int size(){ //method to find the size of the list
		Node next;
		int count;
		if (head == null) //if the head is null, the size is 0
			return 0;
		else{ //if size isnt 0...
			count = 1;
			next = head;
			while(next.getNext() != null){ //increments a counter through a while loop everytime the node is poiting to another node
				next = next.getNext();
				count++;
			}
		}
		return count; //returns the integer value of count, the size of the array
	}
		
	
}		