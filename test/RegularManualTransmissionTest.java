import org.junit.Before;
import org.junit.Test;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;

/**
 * This class represents RegularManualTransmission class test file.
 */

public class RegularManualTransmissionTest {

  private ManualTransmission rMT;

  int min = 0;

  int max = 100;

  @Before
  public void setUp() {
    rMT = new RegularManualTransmission(0, 20, 20, 40, 40, 60, 60, 80, 80, 100);
  }

  @Test
  public void testGetInitialSpeed() {
    assertEquals(0, rMT.getSpeed());
  }

  @Test
  public void testGetInitialGear() {

    assertEquals(1, rMT.getGear());
  }

  @Test
  public void testGetCurrentStatus() {
    assertEquals("OK: everything is OK.", rMT.getStatus());
  }


  @Test
  public void testOverlappingSpeeds() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, generateRandomNumber(), generateRandomNumber(), generateRandomNumber(), generateRandomNumber(), generateRandomNumber(), generateRandomNumber(), generateRandomNumber(), generateRandomNumber(), 100);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "Any gear's higher speed "
            + "should be strictly less than the next gear's higher speed.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void testOverlappingSpeeds1() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, 19, 19, 59, 20, 39, 60, 79, 80, 100);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "Any gear's higher speed should be strictly "
            + "less than the next gear's higher speed.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void testWhenAllSpeedsAreNotCovered() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, 10, 20, 40, 40, 60, 60, 80, 80, 100);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "The given speeds do not cover "
            + "the maximumSpeedLimit range.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void testNonOverlappingSpeeds() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, 20, 21, 40, 41, 60, 61, 80, 81, 100);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "Given ranges shouldn't be non-overlapping.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void whenVehicleDoesntMove() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "One or more of the given speed values are negative or zero.";
    assertEquals(actualMessage, expectedMessage);
  }


  @Test
  public void validateWhenLowestSpeedsOfFirstGearIsNegative() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(-1, 10, 20, 30, 0, 0, 0, 0, 0, 0);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "First gear's lowest speed ought to be 0.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void validateWhenLowestSpeedsOfFirstGearIsPositive() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(1, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "First gear's lowest speed ought to be 0.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void validateWhenSpeedsAreNegative() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, -10, 10, 20, 30, 40, 40, 50, 50, 60);
    } catch (IllegalArgumentException exception) {
      actualMessage = exception.getMessage();
    }
    String expectedMessage = "One or "
            + "more of the given speed values are negative or zero.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void validateWhenLowerSpeedOfGearIsFasterThanNextGear() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, 10, 10, 20, 30, 40, 40, 50, 30, 80);
    } catch (IllegalArgumentException exception) {
      actualMessage = exception.getMessage();
    }
    String expectedMessage = "Any gear's "
            + "lower speed should be strictly less than the next gear's lower speed.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void validateWhenLowerSpeedOfGearIsMoreThanHigherSpeed() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, 10, 21, 20, 30, 40, 40, 50, 60, 80);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "Lower speed "
            + "should be less than or equal to that gear's higher speed.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void testWhenNonAdjacentGearsLowerSpeedIsOverLapping() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, 20, 10, 20, 20, 60, 60, 80, 80, 100);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "Only adjacent-gear ranges may overlap;"
            + " other ranges should not.";
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  public void testWhenNonAdjacentGearsHighestSpeedIsOverLapping() {
    String actualMessage = null;
    try {
      rMT = new RegularManualTransmission(0, 40, 20, 60, 41, 60, 60, 80, 80, 100);
    } catch (IllegalArgumentException e) {
      actualMessage = e.getMessage();
    }
    String expectedMessage = "Only adjacent-gear ranges may overlap;"
            + " other ranges should not.";
    assertEquals(actualMessage, expectedMessage);
  }


  @Test
  public void testInitialStateOfVehicle() {
    assertEquals("OK: everything is OK.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
    assertEquals(0, rMT.getSpeed());
  }

  @Test
  public void testIncreaseSpeed() {
    assertEquals(0, rMT.getSpeed());
    ManualTransmission mT = rMT.increaseSpeed();
    //speed of the vehicle is updated to new speed.
    assertEquals("OK: everything is OK.", mT.getStatus());
    assertEquals(1, mT.getSpeed());
  }

  @Test
  public void testMinSpeed() {
    rMT = rMT.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
  }

  @Test
  public void testMinGear() {
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed. Reached minimum speed.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
  }

  @Test
  public void testMaxGear() {

    rMT = testIncreaseSpeedNonOverLappingSeq(rMT);
    rMT = rMT.increaseGear();
    assertEquals("Cannot increase gear. Reached maximum gear.", rMT.getStatus());
  }

  @Test
  public void testMaxSpeed() {
    rMT = testIncreaseSpeedNonOverLappingSeq(rMT);
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed. Reached maximum speed.", rMT.getStatus());
  }

  @Test
  public void testCantIncreaseSpeed() {
    for (int i = 0; i < 20; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    rMT = rMT.increaseSpeed();
    assertEquals(1, rMT.getGear());
    assertEquals("Cannot increase speed, increase gear first.", rMT.getStatus());

  }

  @Test
  public void testCantIncreaseGear() {

    for (int i = 0; i < 20; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    rMT = rMT.increaseSpeed();
    assertEquals(1, rMT.getGear());
    assertEquals("Cannot increase speed, increase gear first.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals(2, rMT.getGear());
    rMT = rMT.increaseGear();
    assertEquals("Cannot increase gear, increase speed first.", rMT.getStatus());
  }


  @Test
  public void testIncreaseSpeedAndGearWithOverlappingSeq() {
    rMT = new RegularManualTransmission(0, 20, 10, 40, 40, 60, 50, 80, 80, 100);
    //test initial state of the vehicle
    testInitialState(rMT);
    //Test increase speed and increase gear scenario with overlapping seq
    rMT = testIncreaseSpeedOverLappingSeq(rMT);
    //test final state of the vehicle
    assertEquals(100, rMT.getSpeed());
    assertEquals(5, rMT.getGear());
    assertEquals("OK: everything is OK.", rMT.getStatus());
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed. Reached maximum speed.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals("Cannot increase gear. Reached maximum gear.", rMT.getStatus());

  }

  @Test
  public void testIncreaseSpeedAndGearWithNonOverlappingSeq() {
    rMT = new RegularManualTransmission(0, 20, 20, 40, 40, 60, 60, 80, 80, 100);
    //test initial state of the vehicle
    rMT = testInitialState(rMT);
    //Test increase speed and increase gear scenario with non overlapping seq
    rMT = testIncreaseSpeedNonOverLappingSeq(rMT);
    //test final state of the vehicle
    assertEquals(100, rMT.getSpeed());
    assertEquals(5, rMT.getGear());
    assertEquals("OK: everything is OK.", rMT.getStatus());
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed. Reached maximum speed.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals("Cannot increase gear. Reached maximum gear.", rMT.getStatus());
  }

  @Test
  public void testDecreaseSpeedAndGearWithOverlappingSeq() {
    rMT = new RegularManualTransmission(0, 20, 10, 40, 40, 60, 50, 80, 80, 100);
    //test increase speed and then test decrease speed transmission.
    rMT = testIncreaseSpeedOverLappingSeq(rMT);
    //test final state of vehicle after reaching max speed and gear.
    rMT = testFinalState(rMT);

    //test decrease speed and gear actions
    rMT = testDecreaseSpeedWithOverLappingSeq(rMT);

    //test initial state of the vehicle after reaching min speed and gear.
    assertEquals("OK: everything is OK.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
    assertEquals(0, rMT.getSpeed());
    rMT = rMT.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed. Reached minimum speed.", rMT.getStatus());
    assertEquals(1, rMT.getGear());


  }

  @Test
  public void testDecreaseSpeedWithNonOverlappingSeq() {
    rMT = new RegularManualTransmission(0, 20, 20, 40, 40, 60, 60, 80, 80, 100);

    //test increase speed and then test decrease speed transmission
    rMT = testIncreaseSpeedNonOverLappingSeq(rMT);
    //test final state of vehicle after reaching max speed and gear.
    rMT = testFinalState(rMT);
    //test decrease speed and gear actions
    rMT = testDecreaseSpeedNonOverLappingSeq(rMT);

    //test initial state of the vehicle after reaching min speed and gear.
    assertEquals("OK: everything is OK.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
    assertEquals(0, rMT.getSpeed());
    rMT = rMT.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed. Reached minimum speed.", rMT.getStatus());
    assertEquals(1, rMT.getGear());

  }

  @Test
  public void testCantDecreaseSpeed() {
    rMT = new RegularManualTransmission(0, 20, 10, 40, 40, 60, 50, 80, 80, 100);
    //test increase speed and then test decrease speed transmission.
    rMT = testIncreaseSpeedOverLappingSeq(rMT);
    //test final state of vehicle after reaching max speed and gear.
    rMT = testFinalState(rMT);
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(80, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", rMT.getStatus());
  }

  @Test
  public void testCantDecreaseGear() {
    rMT = new RegularManualTransmission(0, 20, 10, 40, 40, 60, 50, 80, 80, 100);
    //test increase speed and then test decrease speed transmission.
    rMT = testIncreaseSpeedOverLappingSeq(rMT);
    //test final state of vehicle after reaching max speed and gear.
    rMT = testFinalState(rMT);
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(80, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(4, rMT.getGear());
    rMT = rMT.decreaseGear();
    assertEquals("Cannot decrease gear, decrease speed first.", rMT.getStatus());
  }

  private ManualTransmission testDecreaseSpeedWithOverLappingSeq(ManualTransmission rMT) {
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(80, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(4, rMT.getGear());
    rMT = rMT.decreaseGear();
    assertEquals("Cannot decrease gear, decrease speed first.", rMT.getStatus());
    for (int i = 0; i < 19; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(61, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(3, rMT.getGear());
    assertEquals(60, rMT.getSpeed());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(40, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(2, rMT.getGear());
    for (int i = 0; i < 19; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(21, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(1, rMT.getGear());
    assertEquals(20, rMT.getSpeed());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    return rMT;
  }

  private ManualTransmission testDecreaseSpeedNonOverLappingSeq(ManualTransmission rMT) {
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(80, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(4, rMT.getGear());
    rMT = rMT.decreaseGear();
    assertEquals("Cannot decrease gear, decrease speed first.", rMT.getStatus());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(60, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(3, rMT.getGear());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(40, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(2, rMT.getGear());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(20, rMT.getSpeed());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", rMT.getStatus());
    rMT = rMT.decreaseGear();
    assertEquals(1, rMT.getGear());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.decreaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    return rMT;

  }

  private ManualTransmission testIncreaseSpeedNonOverLappingSeq(ManualTransmission rMT) {
    for (int i = 0; i < 20; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    rMT = rMT.increaseSpeed();
    assertEquals(1, rMT.getGear());
    assertEquals("Cannot increase speed, increase gear first.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals(2, rMT.getGear());
    rMT = rMT.increaseGear();
    assertEquals("Cannot increase gear, increase speed first.", rMT.getStatus());
    for (int i = 20; i < 40; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals(3, rMT.getGear());
    for (int i = 40; i < 60; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals(4, rMT.getGear());
    for (int i = 60; i < 80; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals(5, rMT.getGear());
    for (int i = 80; i < 100; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    return rMT;
  }

  private ManualTransmission testIncreaseSpeedOverLappingSeq(ManualTransmission rMT) {
    for (int i = 0; i < 20; i++) {
      rMT = rMT.increaseSpeed();
    }
    rMT = rMT.increaseGear();
    assertEquals(2, rMT.getGear());
    assertEquals(20, rMT.getSpeed());
    rMT = rMT.increaseGear();
    assertEquals("Cannot increase gear, increase speed first.", rMT.getStatus());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    rMT = rMT.increaseGear();
    assertEquals(3, rMT.getGear());
    for (int i = 0; i < 9; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    rMT = rMT.increaseSpeed();
    assertEquals(50, rMT.getSpeed());
    assertEquals(3, rMT.getGear());
    assertEquals("OK: you may increase the gear.", rMT.getStatus());
    for (int i = 0; i < 10; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: you may increase the gear.", rMT.getStatus());
    }
    assertEquals(3, rMT.getGear());
    assertEquals(60, rMT.getSpeed());
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals(4, rMT.getGear());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(80, rMT.getSpeed());
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals(5, rMT.getGear());
    for (int i = 0; i < 20; i++) {
      rMT = rMT.increaseSpeed();
      assertEquals("OK: everything is OK.", rMT.getStatus());
    }
    assertEquals(100, rMT.getSpeed());
    return rMT;
  }

  private ManualTransmission testInitialState(ManualTransmission rMT) {
    assertEquals("OK: everything is OK.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
    assertEquals(0, rMT.getSpeed());
    rMT = rMT.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
    rMT = rMT.decreaseSpeed();
    assertEquals("Cannot decrease speed. Reached minimum speed.", rMT.getStatus());
    assertEquals(1, rMT.getGear());
    return rMT;
  }

  private ManualTransmission testFinalState(ManualTransmission rMT) {
    assertEquals(100, rMT.getSpeed());
    assertEquals(5, rMT.getGear());
    assertEquals("OK: everything is OK.", rMT.getStatus());
    rMT = rMT.increaseSpeed();
    assertEquals("Cannot increase speed. Reached maximum speed.", rMT.getStatus());
    rMT = rMT.increaseGear();
    assertEquals("Cannot increase gear. Reached maximum gear.", rMT.getStatus());
    return rMT;
  }

  private int generateRandomNumber() {
    return (int) (Math.random() * (max - min + 1) + min);
  }

}
