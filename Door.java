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
	private String color, direction;
	private Room behindDoor;

	// direction must be one of North, South
	public Door(String description, String direction, Room behindDoor)
	{
		this.description = description;
		this.direction = direction;
		this.behindDoor = behindDoor;
	}

	public String getDescription()
	{
		return description;
	}

	public String getDirection()
	{
		return direction;
	}

	public Room getBehindDoor()
	{
		return behindDoor;
	}

	public void setBehindDoor(Room behindDoor)
	{
		this.behindDoor = behindDoor;
	}

	public String toString()
	{
		return "A " + description + " leads " + direction + ".\n";
	}
}