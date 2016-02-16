/*
 * RunTests.java
 * 
 * Clinton Wadley
 * cvw5@pitt.edu
 * 2/16/16
 * CS1632
 * Deliverable 2
 *
 * Runs the JUnit tests defined in the test files associated with the Coffee Maker Quest program
 */

import org.junit.runner.notification.Failure;
import org.junit.runner.*;


public class RunTests
{
    public static void main(String[] args)
    {
        System.out.println("\nRunning tests...");

        // run the tests and collect the results
        System.out.println("DoorTest");
        Result doorResult = JUnitCore.runClasses(DoorTest.class);
        System.out.println("RoomTest");
        Result roomResult = JUnitCore.runClasses(RoomTest.class);
        System.out.println("CoffeeTest");
        Result coffeeResult = JUnitCore.runClasses(CoffeeTest.class);

        // print out the results
        long runtime = doorResult.getRunTime() + roomResult.getRunTime() + coffeeResult.getRunTime();
        System.out.println("Run completed in " + runtime + " milliseconds.");
        int runCount = doorResult.getRunCount() + roomResult.getRunCount() + coffeeResult.getRunCount();
        System.out.println(runCount + " total tests run.");

        if (doorResult.wasSuccessful() && roomResult.wasSuccessful() && coffeeResult.wasSuccessful())
        {
            System.out.println("Congratulations, all tests passed!");
        }
        else
        {
            System.out.println("-------------The following test failures were encountered--------------");
            if (!doorResult.wasSuccessful())
            {
                System.out.println("\n" + doorResult.getFailureCount() + " tests failed in DoorTest:");
                for (Failure fail : doorResult.getFailures())
                {
                    System.out.println(fail.toString());
                }
            }

            if (!roomResult.wasSuccessful())
            {
                System.out.println("\n" + roomResult.getFailureCount() + " tests failed in RoomTest:");
                for (Failure fail : roomResult.getFailures())
                {
                    System.out.println(fail.toString());
                }
            }

            if (!coffeeResult.wasSuccessful())
            {
                System.out.println("\n" + coffeeResult.getFailureCount() + " tests failed in CoffeeTest:");
                for (Failure fail : coffeeResult.getFailures())
                {
                    System.out.println(fail.toString());
                }
            }
        }
    }
}