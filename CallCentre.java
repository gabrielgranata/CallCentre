import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CallCentre extends JFrame implements ActionListener{

	private static final int frameWidth = 1000; //variables for frame size and position of GUI
	private static final int frameHeight = 1000;
	private static final int xPosition = 700;
	private static final int yPosition = 500;
	
	private static final int buttonWidth = 500; //variables for button sizes
	private static final int buttonHeight = 250;

	private JButton addButton; //different JButtons used throughout program
	private JButton compNextButton;
	private JButton compAllButton;
	
	public String name; //public variables needed to be accessed by each node
	public String telephone;
	public String email;
	public String reason;
	public int priority;
	
	static CallCentre frame = new CallCentre(); //sets up an instance of the class (GUI)
	
	public LinkedList calls = new LinkedList(); //sets up an instance of the linked list class
	
	public static void main(String [] args){
		
		frame.setVisible(true);	 //initializes the frame, sets it visible to true -- begin program
		
	}
	
	public CallCentre(){ //constructors always opened when an instance of the class is created
		
		Container contentPane = getContentPane(); //content pane where all of the buttons are added
		
		//these methods are all defined in JFrame and therefore may
		//be called directly through inheritance
		setTitle("Call Center"); //title of the GUI
		setSize(frameWidth, frameHeight); //sets the size of the GUI
		setLocation(xPosition, yPosition); //sets location on the screen of the GUI
		setResizable(false); //sets it so GUI cannot be resized
		
		//set the layout manager
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); //sets the layout of the GUI
		
		//create and place two buttons on the frame's content pane
		addButton = new JButton("Add Call"); //adds first button "Add Call"
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addButton.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		addButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		addButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));;
		contentPane.add(addButton); //add button to the GUI
		
		compNextButton = new JButton("Complete Next Call"); //adds next button "Complete Next Call"
		compNextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		compNextButton.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		compNextButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		compNextButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		contentPane.add(compNextButton); //adds button to GUI
		
		compAllButton = new JButton("Complete All Calls"); //adds "Complete All Calls" button
		compAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		compAllButton.setMinimumSize(new Dimension(buttonWidth, buttonHeight));
		compAllButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
		compAllButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		contentPane.add(compAllButton); //adds button to the GUI
		
		//registering a ButtonHandler as an action listener of the two buttons
		
		compNextButton.addActionListener(this); //adds action listeners for each button
		addButton.addActionListener(this);
		compAllButton.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); //sets it so program terminates when GUI is closed
			
	}
	
	public void actionPerformed(ActionEvent evt){ //action listener method for each button
		
		JButton clickedButton = (JButton) evt.getSource(); //variable for the button that is clicked
		
		if(clickedButton.equals(addButton)){ //if "Add Call" button is clicked
			frame.setVisible(false); //sets GUI invisible so program can ask users for their data
			
			try{
				//following lines ask users for each of their information as strings
				name = JOptionPane.showInputDialog(null, "Enter Name: "); 
				telephone = JOptionPane.showInputDialog(null, "Enter Telephone Number: ");
				email = JOptionPane.showInputDialog(null, "Enter Email Address: ");
				reason = JOptionPane.showInputDialog(null, "Enter Reason for Call: ");
				String priorityStr = JOptionPane.showInputDialog(null, "Enter Priority (For Supervisor Enter 1, Important Enter 2, Standard Enter 3): ");
				priority = Integer.parseInt(priorityStr); //changes the priority to an int so that numbers can be compared
			
				if(calls.size()==0){ //if size of the list is 0 add variable to the front always
					calls.addAtFront(name, telephone, email, reason, priority);
					JOptionPane.showMessageDialog(null, "               Added Call" + "\n" + "Name: " +name+ "\n"+"Telephone Number: "+telephone+ "\n"+"Email Address: "+email+ "\n"+"Reason for Call: "+reason+"\n"+"Priority: "+priority);
				}
				else{ //if size of list is not 0, makes following checks
					if(priority==1 || priority==2 || priority ==3) {
						int position = calls.findPosition(name, telephone, email, reason, priority); //finds the position via the findPosition() method in the LinkedList class
						if(position==1){ //if the position desired is 1, the program will always add the new call to the front of the list via addAtFront() method
							calls.addAtFront(name, telephone, email, reason, priority);
							JOptionPane.showMessageDialog(null, "               Added Call" + "\n" + "Name: " +name+ "\n"+"Telephone Number: "+telephone+ "\n"+"Email Address: "+email+ "\n"+"Reason for Call: "+reason+"\n"+"Priority: "+priority);
						}
						else{ //if position is not 1 -- insertAtPostion() method is called which passes in the node's desire
							calls.insertAtPosition(name, telephone, email, reason, priority, position);
							JOptionPane.showMessageDialog(null, "               Added Call" + "\n" + "Name: " +name+ "\n"+"Telephone Number: "+telephone+ "\n"+"Email Address: "+email+ "\n"+"Reason for Call: "+reason+"\n"+"Priority: "+priority);
						}
					}
					else{ //if the priority isn't possible, invalid
						JOptionPane.showMessageDialog(null, "Invalid. Input a number between 1 and 3 please.");
						}
				}
			}catch(Exception e){ //catches invalid input exceptions, close exceptions, etc..
				JOptionPane.showMessageDialog(null, "Invalid input.");
			}
			frame.setVisible(true); //sets frame back visible to true
		}
			
		if(clickedButton.equals(compNextButton)){ //if the button clicked is the "Complete Next Call" button
			frame.setVisible(false); //sets frame visible to false so prompts can be printed
			int size = calls.size();
			if(size!=0){ //if the size is not 0, program calls the remove method
				calls.remove(); //remove method in the linked list class
				frame.setVisible(true);
			}
			else{ //if the size is 0, error is shown
				JOptionPane.showMessageDialog(null, "There are no calls to remove. Please try a different option.");
				frame.setVisible(true);
			}		
		}
				
		if(clickedButton.equals(compAllButton)){ //if the button clicked is the "Complete All Calls" button
			frame.setVisible(false);
			if(calls.size()!=0){ // if size is not 0
				while(calls.size()!=0){ //runs through while loop until size of the list is 
					calls.remove(); //after each time the loop is ran, the remove method is called which always removes the first call from the list
				}
				JOptionPane.showMessageDialog(null, "Your calls have all been completed!");
				frame.setVisible(true); //sets frame back to being visible
			}
			else{
				JOptionPane.showMessageDialog(null, "There are no calls to remove. Please try a different option.");
				frame.setVisible(true); //sets frame back to being visible
			}
		}
	}
}