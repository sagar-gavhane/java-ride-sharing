package com.example.geektrust;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RideSharingAppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("should be able to provide commands to the app")
    void shouldBeAbleToProvideCommandsToTheApp() {
        List<String> commands = Arrays.asList("ADD_DRIVER D1 1 1", "ADD_RIDER R1 1 1");
        RideSharingApp rideSharingApp = new RideSharingApp(commands);
        assertEquals(Arrays.asList("ADD_DRIVER D1 1 1", "ADD_RIDER R1 1 1"), rideSharingApp.getCommands());
    }

    @Test
    @DisplayName("should be able to execute commands with expected output case 1")
    void shouldBeAbleToExecuteCommandsWithExpectedOutputCase1() {
        List<String> commands = Arrays.asList(
            "ADD_DRIVER D1 1 1",
            "ADD_DRIVER D2 4 5",
            "ADD_DRIVER D3 2 2",
            "ADD_RIDER R1 0 0",
            "MATCH R1",
            "START_RIDE RIDE-001 2 R1",
            "STOP_RIDE RIDE-001 4 5 32",
            "BILL RIDE-001"
        );
        RideSharingApp rideSharingApp = new RideSharingApp(commands);
        rideSharingApp.executeCommands();
        String expectedOutput = "DRIVERS_MATCHED D1 D3\n" +
                                "RIDE_STARTED RIDE-001\n" +
                                "RIDE_STOPPED RIDE-001\n" +
                                "BILL RIDE-001 D3 186.74";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("should be able to execute commands with expected output case 2")
    void shouldBeAbleToExecuteCommandsWithExpectedOutputCase2() {
        List<String> commands = Arrays.asList(
            "ADD_DRIVER D1 0 1",
            "ADD_DRIVER D2 2 3",
            "ADD_RIDER R1 3 5",
            "ADD_DRIVER D3 4 2",
            "ADD_RIDER R2 1 1",
            "MATCH R1",
            "MATCH R2",
            "START_RIDE RIDE-101 1 R1",
            "START_RIDE RIDE-102 1 R2",
            "STOP_RIDE RIDE-101 10 2 48",
            "STOP_RIDE RIDE-102 7 9 50",
            "BILL RIDE-101",
            "BILL RIDE-102"
        );
        RideSharingApp rideSharingApp = new RideSharingApp(commands);
        rideSharingApp.executeCommands();
        String expectedOutput = "DRIVERS_MATCHED D2 D3\n"+
                                "DRIVERS_MATCHED D1 D2 D3\n"+
                                "RIDE_STARTED RIDE-101\n"+
                                "RIDE_STARTED RIDE-102\n"+
                                "RIDE_STOPPED RIDE-101\n"+
                                "RIDE_STOPPED RIDE-102\n"+
                                "BILL RIDE-101 D2 234.60\n"+
                                "BILL RIDE-102 D1 258.00";    
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }    
}
