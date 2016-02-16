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
    	currRoom = initRooms();

        System.out.println("\nWelcome to Coffee Maker Quest!");

        // main program loop, responsible for game iteration
        boolean breakLoop = false;
        while(!breakLoop)
        {
            System.out.println("");
        	System.out.println(currRoom.toString());
        	System.out.print(currRoom.getPrompt());
        	input = scan.nextLine();
        	int breakVal = processInput(input);

            if (breakVal >= 0)
                breakLoop = true;
        }

        scan.close();
        System.exit(inventory);

    }

    /**
    * Initializes the linked-list of rooms used to play the game, and returns the head Room of the list
    */
    public static Room initRooms()
    {
    	Room temp1 = null;
        Room temp2 = null;
        Room firstRoom = null;

        // hard-coded data for the rooms, could be implemented to read this data from a file for extensibility
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

        // iterate through the room names, building them
    	for (int i = 0; i < roomNames.length; i++)
    	{
    		// create a new room and add it to the list
    		temp2 = new Room(roomNames[i], roomFurniture[i], roomItems[i]);
    		for (int j = 0; j < roomDoors[i].length; j++)
    		{
    			temp2.addDoor(new Door(roomDoors[i][j][0], roomDoors[i][j][1], null));
    		}

            // if we are not at the beginning of the list
   			if (temp1 != null)
   				temp1.getDoor("NORTH").setBehindDoor(temp2);
            // if we are at the beginning of the list
            else
                firstRoom = temp2;


   			Door tempDoor = temp2.getDoor("SOUTH");
            if (tempDoor != null)
            {
                tempDoor.setBehindDoor(temp1);
            }
    		temp1 = temp2;
    	}

        return firstRoom;
    }

    /**
    * Processes the passed input and calls other methods to take the necessary actions
    */
    public static int processInput(String input)
    {
        System.out.println("");

    	String lower = input.toLowerCase();
    	if (lower.equals("n"))
    		System.out.print(move("NORTH"));
    	else if (lower.equals("s"))
    		System.out.print(move("SOUTH"));
    	else if (lower.equals("l"))
    		System.out.print(look(currRoom));
    	else if (lower.equals("i"))
    		System.out.print(getInventory(inventory));
        else if (lower.equals("h"))
            System.out.print(getHelp());
    	else if (lower.equals("d"))
    		return endGame(inventory);
    	else
    		System.out.println("What?");

        return -1;
    }

    /**
    * Collects the item from the current room, if there is one, and adds it to the inventory.
    * Returns a string to be displayed saying if something was found
    */
    public static String look(Room currentRoom)
    {
        // get the item from the Room
        String item = currentRoom.getItem();
        StringBuilder sb = new StringBuilder();
        sb.append("You dig through all of the trash on the floor and find... \n");

        // if an item existed in the room, add it to the inventory,
        // remove it from the room, and build the string for return
        if (item != null)
        {
            sb.append("some " + item + "!\n");
            currentRoom.removeItem();
            if (item.equals("caffeinated coffee"))
                inventory += 0x0004;
            else if (item.equals("sweet sugar"))
                inventory += 0x0002;
            else if (item.equals("creamy cream"))
                inventory += 0x0001;
        }
        else
        {
            sb.append("nothing.\n");
        }

        return sb.toString();
    }

    /**
    * Moves the current room in the direction specified, if allowed.
    * Returns an error string if movement in the passed direction is not allowed.
    */
    private static String move(String direction)
    {
		Door tempDoor = currRoom.getDoor(direction);

        // move in that direction if the door exists
		if (tempDoor != null)
        {
			currRoom = tempDoor.getBehindDoor();
            return "";
        }
        // if the door doesn't exists, print this out
		else
        {
			return "There is no door to the " + direction + ".\n";
        }
    }

    /**
    * Return the string to be displayed on the help screen
    */
    public static String getHelp()
    {
        StringBuilder sb = new StringBuilder();
    	sb.append("The object of the game is to make an outstanding cup of coffee before studying.\n");
    	sb.append("To make such a cup of coffee, you must collect coffee, sugar, and cream.\n");
    	sb.append("Progress through each room by using the \"N\" and \"S\" commands.\n");
    	sb.append("To see if a room contains a coffee ingredient, use the \"L\" command. You will automatically collect it.\n");
    	sb.append("Once you have all of the ingredients, use the \"D\" command to drink the coffee.\n");

        return sb.toString();
    }

    /**
    * Returns the String corresponding to the passed inventory integer.
    */
    public static String getInventory(int inv)
    {
        // Inventory is tracked using simple bit masking, with coffee, sugar, and cream being
        // mapped to the first three bits.
    	int cream, sugar, coffee;
    	int creamMask = 0x0001;
    	int sugarMask = 0x0002;
    	int coffeeMask = 0x0004;

        // mask off the bits corresponding to the ingredients
    	cream = inv & creamMask;
    	sugar = inv & sugarMask;
    	coffee = inv & coffeeMask;

        // build the string based on the value of the inventory bits
    	StringBuilder sb = new StringBuilder();
    	if (coffee > 0)
    		sb.append("You have some caffeinated coffee.\n");
    	else
    		sb.append("YOU HAVE NO COFFEE!\n");
    	if (sugar > 0)
    		sb.append("You have some sweet sugar.\n");
    	else
    		sb.append("YOU HAVE NO SUGAR!\n");
    	if (cream > 0)
    		sb.append("You have some creamy cream.\n");
    	else
    		sb.append("YOU HAVE NO CREAM!\n");

    	return sb.toString();
    }

    /**
    * Prints the proper ending message to the game based upon the passed inventory int value
    */
	public static int endGame(int inv)
	{
        // based on the inventory code, we can determine what ingredients were collected, and therefore
        // what message to display
		switch (inv)
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
			case 5: System.out.println("You drink the coffee with the cream, but it is too bitter without sugar. You don\'t have time for this!\nYou lose!");
					break;
			case 6: System.out.println("You drink the coffee with the sugar. You can\'t put your finger on it,\nbut it is definitely missing something. You lose!");
					break;
			case 7: System.out.println("You drink the coffee with the sugar and the cream. It is awesome. You can study now.\nYou win! Congratulations!");
					break;
			default:break;
		}

		System.out.println("Exiting with code: " + inv);

		return inv;
	}
}