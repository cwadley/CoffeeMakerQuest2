/**
* Room.java
*
* Clinton Wadley
* cvw5@pitt.edu
* 2/9/16
* CS1632
* Deliverable 2
*
* Room class for use with the CoffeeMakerQuest game.
*/
import java.util.HashMap;

public class Room
{
	private String name, furniture, item;
	private HashMap<String, Door> doors;
	private boolean hasItem;

	public Room(String name, String furniture, String item)
	{
		this.name = name;
		this.furniture = furniture;
		this.item = item;

		if (item == null)
			hasItem = false;
		else
			hasItem = true;

		doors = new HashMap<String, Door>();
	}

	public String getName()
	{
		return name;
	}

	public String getFurniture()
	{
		return furniture;
	}

	public Door getDoor(String direction)
	{
		return doors.get(direction);
	}

	public String printDoors()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < doors.length; i++)
			sb.append(doors[i].toString());

		return sb.toString();
	}

	public String getItem()
	{
		if (hasItem)
			return item;
		else
			return "";
	}

	public void removeItem()
	{
		hasItem = false;
	}

	public void addDoor(Door newDoor)
	{
		doors.add(newDoor);
	}

	public String getPrompt()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSTRUCTIONS: (");
		if (doors.containsKey("North"))
			sb.append("N, ");
		if (doors.containsKey("South"))
			sb.append("S, ");

		sb.append("L, I, D) > ");

		return sb.toString();
	}
	
	public void printPrompt()
	{
		System.out.print("\n" + getPrompt());
	}


	public String toString()
	{
		// build the room info string
		StringBuilder sb = new StringBuilder();
		sb.append("You see a " + name + " room.\n");
		sb.append("It has a " + item + ".\n");
		sb.append(printDoors());
		
		return sb.toString();
	}
}