package vehicle;


/**
 * This interface represents a set of regular transmission operations
 * by an actual vehicle.An actual vehicle lets its driver change the speed
 * (using the gas pedal) and change gears (using the stick shift)
 * and presents the results of the driver's actions as a status.
 * It takes the speed ranges for the 5 gears as two integral numbers each: low [lx] and high [hx].
 * <code> For any gear [gx]: speedRange is ([lx],[hx]) </code> where
 * <ul>
 * <li>[lx] lowest speed of  gear [gx] </li>
 * <li>[hx] lowest speed of  gear [gx] </li>
 * </ul>
 */
public interface ManualTransmission {

  /**
   * Report the status of the transmission of vehicle as a formatted String.
   *
   * @return current status of the vehicle as a formatted string as it moves.
   */
  String getStatus();


  /**
   * Get the current speed of the vehicle as a whole number.
   *
   * @return current speed of the vehicle as it moves.
   */
  int getSpeed();

  /**
   * Get the current gear of the vehicle as a whole number.
   *
   * @return current gear of the vehicle as it moves.
   */
  int getGear();


  /**
   * If permitted, increase the speed by a fixed amount or remain same.
   *
   * @return return transmission object with updated speed.
   */
  ManualTransmission increaseSpeed();

  /**
   * If permitted, decrease the speed by a fixed amount or remain same.
   *
   * @return return transmission object with updated speed.
   */
  ManualTransmission decreaseSpeed();

  /**
   * If permitted, increase the gear by one or stay at the same gear.
   *
   * @return return transmission object with updated gear.
   */
  ManualTransmission increaseGear();

  /**
   * If permitted, decrease the gear by one or stay at the same gear.
   *
   * @return return transmission object with updated gear.
   */
  ManualTransmission decreaseGear();
}
