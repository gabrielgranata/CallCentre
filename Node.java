public class Node{
	public String email;
	public String phone;
	public String name;
	public String reason;
	public int priority;
	public Node next;
	
	// constructors for the new instance of a node, always has to have these 5 values
	public Node(String name, String phone, String email, String reason, int priority){ 
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.reason = reason;
		this.priority = priority;
	}
	
	//the node that is pointed to by next is returned
	public Node getNext(){
		return (next);
	}
	
	//the node pointed to by next is changed to newNode
	public void setNext(Node newNode){ //set next node
		next = newNode;
	}

	public String getName(){ //get the name of the node
		return (name);
	}
	
	public String getPhone(){ //get the phone number stored in the node
		return (phone);
	}
	
	public String getEmail(){ //get the email stored in the node
		return (email);
	}
	
	public String getReason(){ //get the reason stored in the node
		return (reason);
	}
	
	public int getPriority(){ //get the priority stored in the node
		return (priority);
	}
	
}