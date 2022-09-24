package vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * This class represents a ManualTransmission of a vehicle.At any given point,
 * it provides speed,gear and status of the vehicle as it moves.
 */
public class RegularManualTransmission implements ManualTransmission {

  private final int currentSpeed;
  private final int currentGear;
  private final String currentStatus;
  private final int initialSpeed;
  private final int highestSpeed;
  private final Map<Integer, ArrayList<Integer>> speedRanges = new HashMap<>();
  private static final int initialGear = 1;
  private static final int highestGear = 5;
  private static final int speedChange = 1;
  private static final String initialStatus = "OK: everything is OK.";


  /**
   * Constructs a RegularManualTransmission object
   * and initializes the given speed ranges for each of its gear.
   *
   * @param l2 lowest speed in second gear
   * @param h2 highest speed in second gear
   * @param l3 lowest speed in third gear
   * @param h3 highest speed in third gear
   * @param l4 lowest speed in fourth gear
   * @param h4 highest speed in fourth gear
   * @param l5 lowest speed in fifth gear
   * @param h5 highest speed in fifth gear
   * @throws IllegalArgumentException will throw an IllegalArgumentException for any invalid inputs
   */
  public RegularManualTransmission(int l1, int h1, int l2,
                                   int h2, int l3, int h3, int l4, int h4,
                                   int l5, int h5) throws IllegalArgumentException {
    validateInputs(l1, h1, l2, h2, l3, h3, l4, h4, l5, h5);
    this.speedRanges.put(1, new ArrayList<>(Arrays.asList(l1, h1)));
    this.speedRanges.put(2, new ArrayList<>(Arrays.asList(l2, h2)));
    this.speedRanges.put(3, new ArrayList<>(Arrays.asList(l3, h3)));
    this.speedRanges.put(4, new ArrayList<>(Arrays.asList(l4, h4)));
    this.speedRanges.put(5, new ArrayList<>(Arrays.asList(l5, h5)));
    this.initialSpeed = l1;
    this.highestSpeed = h5;
    this.currentSpeed = initialSpeed;
    this.currentGear = initialGear;
    this.currentStatus = initialStatus;
  }

  private RegularManualTransmission(int l1, int h1, int l2,
                                    int h2, int l3, int h3, int l4, int h4,
                                    int l5, int h5, int speed,
                                    int gear, String status) throws IllegalArgumentException {
    validateInputs(l1, h1, l2, h2, l3, h3, l4, h4, l5, h5);
    if (speed < 0 || gear < 1 || gear > 5) {
      throw new IllegalArgumentException("Invalid Inputs");
    }
    this.speedRanges.put(1, new ArrayList<>(Arrays.asList(l1, h1)));
    this.speedRanges.put(2, new ArrayList<>(Arrays.asList(l2, h2)));
    this.speedRanges.put(3, new ArrayList<>(Arrays.asList(l3, h3)));
    this.speedRanges.put(4, new ArrayList<>(Arrays.asList(l4, h4)));
    this.speedRanges.put(5, new ArrayList<>(Arrays.asList(l5, h5)));
    this.initialSpeed = l1;
    this.highestSpeed = h5;
    this.currentGear = gear;
    this.currentSpeed = speed;
    this.currentStatus = status;
  }

  @Override
  public String getStatus() {
    return this.currentStatus;
  }

  @Override
  public int getSpeed() {
    return this.currentSpeed;
  }

  @Override
  public int getGear() {
    return this.currentGear;
  }

  @Override
  public ManualTransmission increaseSpeed() {
    ArrayList<Integer> speedRange;
    if (getGear() != 5) {
      speedRange = this.speedRanges.get(getGear() + 1);
    } else {
      speedRange = this.speedRanges.get(getGear());
    }
    int nextLow = speedRange.get(0);

    ArrayList<Integer> currentSpeedRange = this.speedRanges.get(getGear());
    int currentHigh = currentSpeedRange.get(1);

    int updatedSpeed = this.getSpeed() + speedChange;
    String status;
    int currentSpeed = this.getSpeed();
    if (updatedSpeed > highestSpeed) {
      status = "Cannot increase speed. Reached maximum speed.";
    } else if (updatedSpeed >= nextLow && updatedSpeed > currentHigh && this.getGear() != 5) {
      status = "Cannot increase speed, increase gear first.";
    } else if (updatedSpeed >= nextLow && nextLow < currentHigh
            && this.getGear() != 5) {
      status = "OK: you may increase the gear.";
      currentSpeed = updatedSpeed;
    } else {
      status = "OK: everything is OK.";
      currentSpeed = updatedSpeed;
    }
    ManualTransmission result = initialiseMT(currentSpeed, this.getGear(), status);
    return result;
  }

  @Override
  public ManualTransmission decreaseSpeed() {

    ArrayList<Integer> speedRange;
    if (getGear() != 1) {
      speedRange = speedRanges.get(getGear() - 1);
    } else {
      speedRange = speedRanges.get(getGear());
    }
    int previousHigh = speedRange.get(1);

    ArrayList<Integer> currentSpeedRange = speedRanges.get(getGear());
    int currentLow = currentSpeedRange.get(0);
    String status;
    int updatedSpeed = this.getSpeed() - speedChange;
    int currentSpeed = this.getSpeed();
    if (updatedSpeed < initialSpeed) {
      status = "Cannot decrease speed. Reached minimum speed.";
    } else if (updatedSpeed <= previousHigh && updatedSpeed < currentLow && this.getGear() != 1) {
      status = "Cannot decrease speed, decrease gear first.";
    } else if (updatedSpeed <= previousHigh && previousHigh > currentLow
            && updatedSpeed >= currentLow && this.getGear() != 1) {
      status = "OK: you may decrease the gear.";
      currentSpeed = updatedSpeed;
    } else {
      currentSpeed = updatedSpeed;
      status = "OK: everything is OK.";
    }
    ManualTransmission result = initialiseMT(currentSpeed, this.getGear(), status);
    return result;
  }

  @Override
  public ManualTransmission increaseGear() {

    ArrayList<Integer> speedRange;
    if (getGear() != 5) {
      speedRange = speedRanges.get(getGear() + 1);
    } else {
      speedRange = speedRanges.get(getGear());
    }
    int nextLow = speedRange.get(0);
    String status;
    int currentGear = this.getGear();
    if (this.getGear() == highestGear) {
      status = "Cannot increase gear. Reached maximum gear.";
    } else if (nextLow > this.getSpeed() && this.getGear() != 5) {
      status = "Cannot increase gear, increase speed first.";
    } else {
      status = "OK: everything is OK.";
      currentGear = this.getGear() + 1;
    }
    ManualTransmission result = initialiseMT(this.getSpeed(), currentGear, status);
    return result;
  }

  @Override
  public ManualTransmission decreaseGear() {

    ArrayList<Integer> speedRange;
    if (getGear() != 1) {
      speedRange = speedRanges.get(getGear() - 1);
    } else {
      speedRange = speedRanges.get(getGear());
    }
    int previousHigh = speedRange.get(1);
    String status;
    int currentGear = this.getGear();
    if (this.getGear() == initialGear) {
      status = "Cannot decrease gear. Reached minimum gear.";
    } else if (this.getSpeed() > previousHigh && this.getGear() != 1) {
      status = "Cannot decrease gear, decrease speed first.";
    } else {
      status = "OK: everything is OK.";
      currentGear = this.getGear() - 1;
    }
    ManualTransmission result = initialiseMT(this.getSpeed(), currentGear, status);
    return result;
  }

  private ManualTransmission initialiseMT(int speed, int gear, String status) {
    ManualTransmission result = new RegularManualTransmission(this.speedRanges.get(1).get(0),
            this.speedRanges.get(1).get(1),
            this.speedRanges.get(2).get(0),
            this.speedRanges.get(2).get(1),
            this.speedRanges.get(3).get(0),
            this.speedRanges.get(3).get(1),
            this.speedRanges.get(4).get(0),
            this.speedRanges.get(4).get(1),
            this.speedRanges.get(5).get(0),
            this.speedRanges.get(5).get(1),
            speed, gear, status);
    return result;
  }

  private void validateInputs(int l1, int h1, int l2, int h2, int l3,
                              int h3, int l4, int h4,
                              int l5, int h5) {
    int[][] num = {{l1, h1}, {l2, h2}, {l3, h3}, {l4, h4}, {l5, h5}};
    if (l1 != 0) {
      throw new IllegalArgumentException("First gear's lowest speed ought to be 0.");
    } else if (h1 <= 0 || l2 <= 0 || h2 <= 0
            || l3 <= 0 || l4 <= 0 || h4 <= 0 || l5 <= 0 || h5 <= 0) {
      throw new IllegalArgumentException("One or more"
              + " of the given speed values are negative or zero.");
    } else if (l1 > h1 || l2 > h2 || l3 > h3 || l4 > h4 || l5 > h5) {
      throw new IllegalArgumentException("Lower speed should be "
              + "less than or equal to that gear's higher speed.");
    } else if (l1 >= l2 || l2 >= l3 || l3 >= l4 || l4 >= l5) {
      throw new IllegalArgumentException("Any gear's lower speed should "
              + "be strictly less than the next gear's lower speed.");
    } else if (h1 > h2 || h2 > h3 || h3 > h4 || h4 > h5) {
      throw new IllegalArgumentException("Any gear's higher speed should "
              + "be strictly less than the next gear's higher speed.");
    } else if (!isCovered(num, l1, h5)) {
      throw new IllegalArgumentException("The given speeds do not "
              + "cover the maximumSpeedLimit range.");
    } else if (l2 > h1 || l3 > h2 || l4 > h3 || l5 > h4) {
      throw new IllegalArgumentException("Given ranges shouldn't be non-overlapping.");
    } else if ((l3 <= l1 && l1 <= h3) || (l4 <= l1 && l1 <= h4)
            || (l5 <= l1 && l1 <= h5)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap; "
              + "other ranges should not.");
    } else if ((l4 <= l2 && l2 <= h4) || (l5 <= l2 && l2 <= h5)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap; "
              + "other ranges should not.");
    } else if ((l1 <= l3 && l3 <= h1) || (l5 <= l3 && l3 <= h5)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap; "
              + "other ranges should not.");
    } else if ((l1 <= l4 && l4 <= h1) || (l2 <= l4 && l4 < h2)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap; "
              + "other ranges should not.");
    } else if ((l3 <= l5 && l5 <= h3) || (l2 <= l5 && l5 <= h2)
            || (l1 <= l5 && l5 <= h1)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap; "
              + "other ranges should not.");
    } else if ((l3 <= h1 && h1 <= h3) || (l4 <= h1 && h1 <= h4)
            || (l5 <= h1 && h1 <= h5)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap; "
              + "other ranges should not.");
    } else if ((l4 <= h2 && h2 <= h4) || (l5 <= h2 && h2 <= h5)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap; "
              + "other ranges should not.");
    } else if ((l1 <= h3 && h3 <= h1) || (l5 <= h3 && h3 <= h5)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap;"
              + " other ranges should not.");
    } else if ((l1 <= h4 && h4 <= h1) || (l2 <= h4 && h4 <= h2)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap; "
              + "other ranges should not.");
    } else if ((l3 <= h5 && h5 <= h3) || (l2 <= h5 && h5 <= h2)
            || (l1 <= h5 && h5 <= h1)) {
      throw new IllegalArgumentException("Only adjacent-gear ranges may overlap;"
              + " other ranges should not.");
    }
  }

  private boolean isCovered(int[][] speedRanges, int initialSpeed, int highestSpeed) {
    HashSet<Integer> resultSet = new HashSet<>();
    for (int i = initialSpeed; i <= highestSpeed; i++) {
      resultSet.add(i);
    }
    for (int[] ints : speedRanges) {
      int num1 = ints[0];
      int num2 = ints[1];
      for (int k = num1; k <= num2; k++) {
        if (k >= initialSpeed && k <= highestSpeed) {
          resultSet.remove(k);
        }
      }
    }
    return resultSet.size() == 0;
  }
}
