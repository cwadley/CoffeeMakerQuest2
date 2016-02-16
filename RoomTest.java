/**
* RoomTest.java
*
* Clinton Wadley
* cvw5@pitt.edu
* 2/16/16
* CS1632
* Deliverable 2
*
* JUnit test class for the Room class in CoffeeMakerQuest
*/

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.*;
import org.junit.Test;

public class RoomTest
{
    /* Room getName() should return the same name value initialized
     */
    @Test
    public void getNameReturnsSameValue()
    {
        String testName = "Bob";
        Room testRoom = new Room(testName, null, null);

        assertEquals(testName, testRoom.getName());
    }

    /* Room getFurniture() should return the same furniture value initialized
     */
    @Test
    public void getFurnitureReturnsSameValue()
    {
        String testFurniture = "burly oak";
        Room testRoom = new Room(null, testFurniture, null);

        assertEquals(testFurniture, testRoom.getFurniture());
    }

    /* Room getItem() should return the same item value initialized
     */
    @Test
    public void getItemReturnsSameValue()
    {
        String testItem = "slippery creamer";
        Room testRoom = new Room(null, null, testItem);

        assertEquals(testItem, testRoom.getItem());
    }

    /* Room getDoor() should return the same Door that was added
     * through addDoor()
     */
    @Test
    public void getDoorReturnsSameObject()
    {
        Door mahDoor = Mockito.mock(Door.class);
        when(mahDoor.getDirection()).thenReturn("NORTH");
        Room testRoom = new Room(null, null, null);
        testRoom.addDoor(mahDoor);

        assertSame(mahDoor, testRoom.getDoor("NORTH"));
    }

    /* Room getDoor() should return null if invalid direction is passed
     */
    @Test
    public void getDoorReturnsNullInvalidDirection()
    {
        Room testRoom = new Room(null, null, null);

        assertSame(null, testRoom.getDoor("Bacon"));
    }

    /* Room removeItem() should result in null being return from getItem()
     */
    @Test
    public void getItemReturnsNullAfterRemoveItem()
    {
        Room testRoom = new Room(null, null, "not null");
        testRoom.removeItem();

        assertEquals(null, testRoom.getItem());
    }

    /* Room toString() should return a valid string representation
     * of the room
     */
    @Test
    public void toStringValid()
    {
        Door fakeDoor = Mockito.mock(Door.class);
        when(fakeDoor.toString()).thenReturn("A lunky door leads NORTH.");
        when(fakeDoor.getDirection()).thenReturn("NORTH");
        Room testRoom = new Room("balmy", "delicious couch", null);
        testRoom.addDoor(fakeDoor);
        String expected = "You see a balmy room.\nIt has a delicious couch.\nA lunky door leads NORTH.\n";

        assertEquals(expected, testRoom.toString());
    }

    /* Room getPrompt() returns a valid prompt based on the doors passed
     */
    @Test
    public void getPromptValid()
    {
        Door fakeDoor = Mockito.mock(Door.class);
        when(fakeDoor.getDirection()).thenReturn("NORTH");
        Room testRoom = new Room(null, null, null);
        testRoom.addDoor(fakeDoor);
        String expected = "INSTRUCTIONS: (N, L, I, H, D) > ";

        assertEquals(expected, testRoom.getPrompt());
    }


}