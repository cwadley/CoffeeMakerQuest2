/**
* DoorTest.java
*
* Clinton Wadley
* cvw5@pitt.edu
* 2/16/16
* CS1632
* Deliverable 2
*
* JUnit test class for the Door class in CoffeeMakerQuest
*/

import static org.junit.Assert.*;
import org.mockito.*;
import org.junit.Test;

public class DoorTest
{
    /* The Door constructor should return a 
     *
    /* The get description method should return the a value
     * equivalent to the passed description
     */
    @Test
    public void getDescriptionReturnsSameValue()
    {
        String name = "Door name";
        Door testDoor = new Door(name, "NORTH", null);

        assertEquals(name, testDoor.getDescription());
    }

    /* The get direction method should return the a value
     * equivalent to the passed direction
     */
    @Test
    public void getDirectionReturnsSameValue()
    {
        String dir = "NORTH";
        Door testDoor = new Door(null, dir, null);

        assertEquals(dir, testDoor.getDirection());
    }

    /* The get description method should return the same Room
     * object that was passed to it
     */
    @Test
    public void getBehindDoorReturnsSameObject()
    {
        Room fakeRoom = Mockito.mock(Room.class);
        Door testDoor = new Door(null, "SOUTH", fakeRoom);

        assertSame(fakeRoom, testDoor.getBehindDoor());
    }

    /* After setting the behind door, it should return the same room
     */
    @Test
    public void setBehindDoorReturnsSameObject()
    {
        Room fakeRoom = Mockito.mock(Room.class);
        Door testDoor = new Door(null, "SOUTH", null);
        testDoor.setBehindDoor(fakeRoom);

        assertSame(fakeRoom, testDoor.getBehindDoor());
    }

    /* Door toString() should return a valid string representation of the door
     */
    @Test
    public void toStringValid()
    {
        Door testDoor = new Door("bulging", "north", null);
        String expected = "A bulging door leads NORTH.";

        assertEquals(expected, testDoor.toString());
    }
}