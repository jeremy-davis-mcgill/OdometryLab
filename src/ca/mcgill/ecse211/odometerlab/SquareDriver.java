/*
 * SquareDriver.java
 */
package ca.mcgill.ecse211.odometerlab;

import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class SquareDriver {
  private static final int FORWARD_SPEED = 250;
  private static final int ROTATE_SPEED = 150;
  private static int turns; //A variable that is used in the OdometryCorrection class

  public static void drive(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor,
      double leftRadius, double rightRadius, double width) {
    // reset the motors
    for (EV3LargeRegulatedMotor motor : new EV3LargeRegulatedMotor[] {leftMotor, rightMotor}) {
      motor.stop();
      motor.setAcceleration(3000);
    }

    // wait 5 seconds
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // there is nothing to be done here because it is not expected that
      // the odometer will be interrupted by another thread
    }
    
    
    for (int i = 0; i < 4; i++) {
      // drive forward two tiles
    	
    	setTurns(i);
      leftMotor.setSpeed(FORWARD_SPEED);
      rightMotor.setSpeed(FORWARD_SPEED);

      leftMotor.rotate(convertDistance(leftRadius, 90), true);        //The robot goes in a 3x3 square    
      rightMotor.rotate(convertDistance(rightRadius, 90), false);

      // turn 90 degrees clockwise
      leftMotor.setSpeed(ROTATE_SPEED);
      rightMotor.setSpeed(ROTATE_SPEED);

      leftMotor.rotate(convertAngle(leftRadius, width, 90.0), true);
      rightMotor.rotate(-convertAngle(rightRadius, width, 90.0), false);
    }
  }

  private static int convertDistance(double radius, double distance) {
    return (int) ((180.0 * distance) / (Math.PI * radius));
  }

  private static int convertAngle(double radius, double width, double angle) {
    return convertDistance(radius, Math.PI * width * angle / 360.0);
  }
  
  public static int getTurns() {  //gets the number of turns
	  return turns;
  }
  
  private static void setTurns(int i) {  //sets the number of turns
	  turns = i;
  }
  
}
