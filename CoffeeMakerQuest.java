/**
* CoffeeMakerQuest.java
*
* Clinton Wadley
* cvw5@pitt.edu
* 2/9/16
* CS1632
* Deliverable 2
*
* Implementation of the game Coffee Maker Quest, demonstrating the use of unit tests
* and testing-driven development.
*/
import java.util.Scanner;

public class CoffeeMakerQuest
{
	private static int inventory;
	private static Room currRoom;

    public static void main(String[] args)
    {
    	inventory = 0x0000;
    	Scanner scan = new Scanner(System.in);
    	String input = null;

    	// initialize the rooms
    	initRooms();

        System.out.println("\nWelcome to Coffee Maker Quest!\n");

        while(true)
        {
        	currRoom.toString();
        	currRoom.printPrompt();
        	input = scan.nextLine();
        	processInput(input);
        }

    }

    public static Room initRooms()
    {
    	Room temp1, temp2;
    	String[] roomNames = {"Small", "Funny", "Refinanced", "Dumb", "Bloodthirsty", "Rough"};
    	String[] roomFurniture = {"quaint sofa", "sad record player", "tight pizza", "flat energy drink", "beautiful bag of money", "perfect air hockey table"};
    	String[] roomItems = {"creamy cream", null, "caffeinated coffee", null, null, "sweet sugar"};
    	String[][][] roomDoors = new String[][][] {
								{{"magenta", "North"}},
								{{"beige", "North"}, {"massive", "South"}},
								{{"dead", "North"}, {"smart", "South"}},
								{{"vivacious", "North"}, {"slim", "South"}},
								{{"purple", "North"}, {"sandy", "South"}},
								{{"minimalist", "South"}}};

    	for (int i = 0; i < roomNames.length; i++)
    	{
    		// create a new room and add it to the list
    		temp2 = new Room(roomNames[i], roomFurniture[i], roomItems[i]);
    		for(int j = 0; j < roomDoors[i].length; j++)
    		{
    			temp2.addDoor(new Door(roomDoors[i][j][0], roomDoors[i][j][1], null));
    		}

   			if (temp1 != null)
   			{
   				temp1.getDoor("North").setBehindDoor(temp2);
   				currRoom = temp2;
   			}

   			temp2.getDoor("South").setBehindDoor(temp1);
    		temp1 = temp2;
    	}
    }

    public static void processInput(String input)
    {
    	String lower = input.toLowerCase();
    	if (lower.equals("n"))
    		move("North");
    	else if (lower.equals("s"))
    		move("South");
    	else if (lower.equals("l"))
    		look(currRoom);
    	else if (lower.equals("i"))
    		printInventory(inventory);
    	else if (lower.equals("d"))
    		endGame(inventory);
    	else
    		System.out.println("What?");
    }

    public static void move(String direction)
    {
		Door tempDoor = currRoom.getDoor(direction);
		if (tempDoor != null)
			currRoom = tempDoor.getBehindDoor();
		else
			System.out.println("There is no door to the " + direction + ".");
    }

    public static void printHelp()
    {
    	System.out.println("The object of the game is to make a cup of coffee before studying.");
    	System.out.println("To make such a cup of coffee, you must collect coffee, sugar, and cream.");
    	System.out.println("Progress through each room by using the \"N\" and \"S\" commands.");
    	System.out.println("To see if a room contains a coffee component, and to collect said item, use the \"L\" command.");
    	System.out.println("Once you have all of the ingredients, use the \"D\" command to drink the coffee.");
    }

    public static void printInventory(int inv)
    {
    	int cream, sugar, coffee;
    	int creamMask = 0x0001;
    	int sugarMask = 0x0002;
    	int coffeeMask = 0x0004;

    	cream = inv & creamMask;
    	sugar = inv & sugarMask;
    	coffee = inv & coffeeMask;

    	StringBuilder sb = new StringBuilder();
    	if (coffee > 0)
    		sb.append("You have some caffeinated coffee.\n");
    	else
    		sb.append("YOU HAVE NO COFFEE!\n");
    	if (sugar > 0)
    		sb.append("You have some creamy cream.\n");
    	else
    		sb.append("YOU HAVE NO SUGAR!\n");
    	if (cream > 0)
    		sb.append("You have some sweet sugar.\n");
    	else
    		sb.append("YOU HAVE NO SUGAR!\n");

    	System.out.println(sb.toString());
    }

	public static void endGame(int code)
	{
		switch (code)
		{
			case 0: System.out.println("You drink the air, sputter, cough, and wither. You have died.\nYou lose!");
					break;
			case 1: System.out.println("You drink the cream. It is a bit old and smells kind of bad. You feel sick.\nYou lose!");
					break;
			case 2: System.out.println("Sugar is a solid, so in order to drink it, you first melt it on the stove.\nYou burn your mouth horribly. You lose!");
					break;
			case 3: System.out.println("You add the sugar to the cream.\n\"Too bad this isn\'t whipped...\" you think as you choke down the sickly-sweet mixture. You lose!");
					break;
			case 4: System.out.println("You drink the coffee. It is pretty low-grade, so without sugar and cream, it tastes really bad.\nYou lose!");
					break;
			case 5: System.out.println("You drink the coffee with the cream, but it is too bitter without sugar.\nYou lose!");
					break;
			case 6: System.out.println("You drink the coffee with the sugar. You can\'t put your finger on it,\nbut it is definitely missing something. You lose!");
					break;
			case 7: System.out.println("You drink the coffee with the sugar and the cream. It is awesome. You can study now.\nYou win! Congratulations!");
					break;
			default:break;
		}

		System.out.println("Exiting with code: " + inventory);
		System.exit(inventory);
	}
}