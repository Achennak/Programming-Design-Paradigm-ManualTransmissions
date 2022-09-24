
Assignment 1: Transmission 
This assignment is to completed individually. Please review the standards for academic integrity in the syllabus.

1 Introduction
Vehicles with a manual transmission require the driver to change gears as he/she is changing speed. Putting it simply, this changes how much of the rotation of the engine affects the rotation of the axles (low gears transfer rotation slowly, so that even if the engine is revved up the car does not speed up much).

Every gear works within a range of speeds. If the speed is decreased to be lower than this range without decreasing the gear, then the vehicle "knocks" at this gear. If the gas pedal is pushed beyond the capacity of this gear, the engine "revvs" but the speed does not increase. Learning to "listen" to the car and change gears accordingly is one of the main skills needed to drive a manual transmission vehicle. It is possible to simulate this gear-speed behavior in a computer program.

We may note that "automatic" transmission conceptually lets the car's computer control the gear, given the speed of the vehicle. This further supports the notion that the gear-speed control mechanism is fairly algorithmic in nature.

2 Transmission
An actual vehicle lets its driver change the speed (using the gas pedal) and change gears (using the stick shift) and presents the results of the driver's actions in some way (engine knocking or revving vibrations). Accordingly our simulator provides an interface that offers the following operations:

Increase the speed by a certain fixed amount.

Decrease the speed by a certain fixed amount.

Increase the gear by one.

Decrease the gear by one.

Report the status of the vehicle (as a result of any of the above operations).

3 What to do
All your code (except tests) should be in a package called ''vehicle".

3.1 The interface
Write an interface ManualTransmission that offers only the following methods:

getStatus : Return the status of this transmission as a String without any additional parameters.

getSpeed : Get the current speed of the vehicle as a whole number.

getGear : Get the current gear of the vehicle as a whole number.

increaseSpeed : Increase the speed by a fixed amount without changing gears and return the resulting transmission object. If the speed cannot be increased, then it should return an object with the same speed as before. The speed change amount is up to the implementation and is not an argument of this method.

decreaseSpeed : Decrease the speed by a fixed amount without changing gears and return the resulting transmission object. If the speed cannot be decreased, then it should return an object with the same speed as before. The speed change amount is up to the implementation and is not an argument of this method.

increaseGear : Increase the gear by one without changing speed and return the resulting transmission object. If the gear cannot be increased, then it should return an object with the same gear as before.

decreaseGear : Decrease the gear by one without changing speed and return the resulting transmission object. If the gear cannot be decreased, then it should return an object with the same gear as before.

Do Now!
Think about why the last four operations are designed to return the resulting object. Does it say something about the intended design?

3.2 The implementation
Write an implementation called RegularManualTransmission that implements the ManualTransmission interface. It must have the following characteristics:

The speed changes by 1 at a time.

The transmission supports exactly 5 gears, numbered 1 through 5. Gear 1 has the lowest speed range.

The class offers a constructor that takes the speed ranges for the 5 gears as two integral numbers each: low and high. Thus it takes arguments as l1,h1,l2,h2,...,l5,h5. For each gear x, lx should be less than or equal to hx. Furthermore the lower speed for gear 1 should be strictly lesser than that of gear 2, and so on. Finally (only) adjacent-gear ranges may overlap (e.g. l2 may be between l1 and h1, etc.) but the ranges cannot be non-overlapping (i.e. each speed is covered by a gear range). The lower speed of the first gear should be 0, and the highest speed in the last gear represents the speed limit of the vehicle (it cannot go faster than this). If any of these conditions are not fulfilled by the inputs then the constructor should throw an IllegalArgumentException with an appropriate message.

The status message it returns is exactly one of the following possibilities (depending on the state of the transmission and the last thing that was attempted):

"OK: everything is OK.": this is the status if the speed was changed successfully without changing gears, or the gear was changed successfully without changing speed.

"OK: you may increase the gear.": this is the status if the speed was increased successfully, but it is now within the range of the next gear (remember that adjacent gear ranges may overlap)

"OK: you may decrease the gear.": this is the status if the speed was decreased successfully, but it is now within the range of the previous gear (remember that adjacent gear ranges may overlap)

"Cannot increase speed, increase gear first.": this is the status if the speed cannot be increased more unless the gear is increased first. This implies that the intended speed is too high for the current gear.

"Cannot decrease speed, decrease gear first.": this is the status if the speed cannot be decreased more unless the gear is decreased first. This implies that the intended speed is too low for the current gear.

"Cannot increase gear, increase speed first.": this is the status if the gear cannot be increased more unless the speed is increased first. This implies that the current speed will be too low for the next gear.

"Cannot decrease gear, decrease speed first.": this is the status if the gear cannot be decreased more unless the speed is decreased first. This implies that the current speed will be too high for the previous gear.

"Cannot increase speed. Reached maximum speed.": this is the status if the speed cannot be increased as it will go beyond the speed limit of the vehicle

"Cannot decrease speed. Reached minimum speed.": this is the status if the speed cannot be decreased as it is already 0

"Cannot increase gear. Reached maximum gear.": this is the status if the gear cannot be increased as it is already in gear 5

"Cannot decrease gear. Reached minimum gear.": this is the status if the gear cannot be decreased as it is already in gear 1

An object should start in the first gear with zero speed and a status of "OK: everything is OK.".

You must implement the class as specified above. It is expected that other than the constructor above, all the public methods in this class must come only from the ManualTransmission interface. There is no restriction on private methods: you can define as many as you like.

3.3 Tests
Write tests for this class (create an empty RegularManualTransmission , then write tests and finally fill the implementation). Read the test notes on the course web page to help you design tests effectively.

3.4 Documentation
We expect your code to be well-commented. The expectations are:

Each interface and class contains a comment above it explaining specifically what it represents. This should be in plain language, understandable by anybody wishing to use it. Comment above a class should be specific: it should not merely state that it is an implementation of a particular interface.

Each public method in the interface should have information about what this method accomplishes (purpose), the nature and explanation of any arguments, return values and exceptions thrown by it and whether it changes the calling object in any way (contract).

If a class implements a method declared in an interface that it implements, and the comments in the interface describe this implementation completely and accurately, there is no need to replicate that documentation in the class.

All comments should be in Javadoc-style.

4 Grading criteria
Correctness of the code

Quality and coverage of tests (i.e. whether tests verify behavior in various situations)

Design of classes, interfaces and methods

Quality of documentation

Code style
