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

	/**
	* Constructor
	*/
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

	/**
	* Returns the name of the room
	*/
	public String getName()
	{
		return name;
	}

	/**
	* Returns the furniture in the room
	*/
	public String getFurniture()
	{
		return furniture;
	}

	/**
	* Returns the door in the passed direction.
	* Returns null if no door exists in that direction.
	*/
	public Door getDoor(String direction)
	{
		return doors.get(direction);
	}

	/**
	* Returns the string representation of all the doors in the room
	*/
	private String getDoorsString()
	{
		StringBuilder sb = new StringBuilder();
		Door leDoor = doors.get("NORTH");
		if (leDoor != null)
			sb.append(leDoor.toString() + "\n");
		leDoor = doors.get("SOUTH");
		if (leDoor != null)
			sb.append(leDoor.toString() + "\n");

		return sb.toString();
	}

	/**
	* Returns the item in the room. Returns null if there is no item.
	*/
	public String getItem()
	{
		if (hasItem)
			return item;
		else
			return null;
	}

	/**
	* Removes the item from the room
	*/
	public void removeItem()
	{
		hasItem = false;
	}

	/**
	* Adds the passed Door to the room, in the direction specified in the Door object
	*/
	public void addDoor(Door newDoor)
	{
		doors.put(newDoor.getDirection(), newDoor);
	}

	/**
	* Returns the prompt tailord to the room, based upon the presence or absence of doors
	*/
	public String getPrompt()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSTRUCTIONS: (");
		if (doors.containsKey("NORTH"))
			sb.append("N, ");
		if (doors.containsKey("SOUTH"))
			sb.append("S, ");

		sb.append("L, I, H, D) > ");

		return sb.toString();
	}

	/**
	* Returns the string representation of the room
	*/
	public String toString()
	{
		// build the room info string
		StringBuilder sb = new StringBuilder();
		sb.append("You see a " + name + " room.\n");
		sb.append("It has a " + furniture + ".\n");
		sb.append(getDoorsString());
		
		return sb.toString();
	}
}