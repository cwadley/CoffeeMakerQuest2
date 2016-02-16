/**
* CoffeeTest.java
*
* Clinton Wadley
* cvw5@pitt.edu
* 2/16/16
* CS1632
* Deliverable 2
*
* JUnit test class for the CoffeeMakerQuest class in CoffeeMakerQuest game
*/

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.*;
import org.junit.Test;

public class CoffeeTest
{
    /* processInput() should return a valid room
     */
    @Test
    public void initRoomsGivesValidRoom()
    {
    	Room headRoom = CoffeeMakerQuest.initRooms();
    	
    	assertNotEquals(null, headRoom);
    }

    /* endGame() should return the int passed
     */
    @Test
    public void endGameReturnsIntPassed()
    {
        int returnVal = CoffeeMakerQuest.endGame(2);

        assertEquals(2, returnVal);
    }

    /* processInput() should return negative one on any input other than 'd'
     */
    @Test
    public void processInputReturnsNegOne()
    {
        int returnVal = CoffeeMakerQuest.processInput("A");
        returnVal += CoffeeMakerQuest.processInput("h");
        returnVal = returnVal / 2;
        
        assertEquals(-1, returnVal);
    }

    /* processInput() should not return negative one on 'd'
     */
    @Test
    public void processInputReturnsZeroOnd()
    {
        int returnVal = CoffeeMakerQuest.processInput("d");

        assertNotEquals(-1, returnVal);
    }

    /* processInput() should not return negative one on 'D'
     */
    @Test
    public void processInputReturnsZeroOnD()
    {
        int returnVal = CoffeeMakerQuest.processInput("D");

        assertNotEquals(-1, returnVal);
    }

    /* look() should return the expected String based on Room
     */
    @Test
    public void lookReturnsExpectedString()
    {
        Room fakeRoom = Mockito.mock(Room.class);
        when(fakeRoom.getItem()).thenReturn("tallow");
        String expected = "You dig through all of the trash on the floor and find... \nsome tallow!\n";

        assertEquals(expected, CoffeeMakerQuest.look(fakeRoom));
    }

    /* getHelp() should return the expected help string
     */
    @Test
    public void getHelpReturnsProperString()
    {
        String expected = "The object of the game is to make an outstanding cup of coffee before studying.\n"
        + "To make such a cup of coffee, you must collect coffee, sugar, and cream.\n"
        + "Progress through each room by using the \"N\" and \"S\" commands.\n"
        + "To see if a room contains a coffee ingredient, use the \"L\" command. You will automatically collect it.\n"
        + "Once you have all of the ingredients, use the \"D\" command to drink the coffee.\n";

        assertEquals(expected, CoffeeMakerQuest.getHelp());
    }

    /* getInventory() should return the expected string given an inventory int
     */
    @Test
    public void getInventoryReturnsExpectedString()
    {
        String expected = "YOU HAVE NO COFFEE!\nYou have some sweet sugar.\nYOU HAVE NO CREAM!\n";

        assertEquals(expected, CoffeeMakerQuest.getInventory(2));
    }
    
}