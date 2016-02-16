/**
* Door.java
*
* Clinton Wadley
* cvw5@pitt.edu
* 2/9/16
* CS1632
* Deliverable 2
*
* Door class for use with the CoffeeMakerQuest game.
*/

public class Door
{
	private String description, direction;
	private Room behindDoor;

	/**
	* Constructor
	* direction must be one of NORTH, SOUTH, case insensitive
	*/
	public Door(String description, String direction, Room behindDoor)
	{
		this.description = description;
		this.direction = direction.toUpperCase();
		this.behindDoor = behindDoor;
	}

	/**
	* Returns the door description
	*/
	public String getDescription()
	{
		return description;
	}

	/**
	* Returns the door direction
	*/
	public String getDirection()
	{
		return direction;
	}

	/**
	* Returns the Room object linked behind the door
	* Door objects, tied to each room, are the links between those rooms in the linked-list.
	*/
	public Room getBehindDoor()
	{
		return behindDoor;
	}

	/**
	* Sets the passed Room object to be behind the door
	*/
	public void setBehindDoor(Room behindDoor)
	{
		this.behindDoor = behindDoor;
	}

	/**
	* Returns the string reperesentation of the door
	*/
	public String toString()
	{
		return "A " + description + " door leads " + direction + ".";
	}
}