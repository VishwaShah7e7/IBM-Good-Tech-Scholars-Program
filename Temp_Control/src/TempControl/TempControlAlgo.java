package TempControl;



public class TempControlAlgo {
	
	public static void main(String[] args) {
		int outsideTemp = 80;
		int insideTemp = 77;
		int setTemp = 76; // set by the user using our application. 
		int difference = 5;
		

		if(insideTemp == setTemp && Math.abs(setTemp - outsideTemp) < difference) {
			System.out.println("Do nothing");
		}
		if (insideTemp >= setTemp) {
			if (Math.abs(setTemp - outsideTemp) > difference) {
				System.out.println("Turn on HVAC and let the room cool");
			} else {
				System.out.println("Open the window. Let the room heat to DT");
			}
		} else {
			if (Math.abs(setTemp - outsideTemp) > difference) {
				System.out.println("Turn on heat and let the room heat until DT");
			} else {
				System.out.println("Wear a jacket");
			}
		} 
		
		/*if(!isThermostate) {
			if(outsideTemp >= insideTemp) {
				if(insideTemp >= desiredTemp) {
					System.out.println("Turn on HVAC and let the room cool");
				} else {
					System.out.println("Open the window. Let the room heat to DT");
				}
			} else if(outsideTemp <= insideTemp) {
				if(insideTemp >= desiredTemp) {
					if(outsideTemp > (desiredTemp + 5)) {
						System.out.println("Turn on the HVAC to cool the room to DT");
					} else {
						System.out.println("Open the window to cool the room");
					}
				} else if(insideTemp < (desiredTemp + 5)) {
					System.out.println("Turn on heat and let the room heat until DT");
				} else {
					System.out.println("Wear a jacket");
				}
			} else {
				
			}
		}else {
			setTemp = desiredTemp;
			if(outsideTemp > (desiredTemp + 5)) {
				System.out.println("Turn on the HVAC to cool the room to DT");
			} else if(outsideTemp < (desiredTemp + 5)) {
				System.out.println("Open the window to cool the room");
			} else if(outsideTemp < (desiredTemp - 5)) {
				System.out.println("Turn on heater");
			} else {
				
			}
		}*/
	}

}
