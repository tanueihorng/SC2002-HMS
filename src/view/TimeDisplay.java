package view;

public class TimeDisplay implements DisplayInfo{
	static public void display() {
		String border = "----------------------------------------------";
		System.out.println("");
		System.out.printf("%-44s\n", "		Select Time			   ");
		System.out.println(border);
		System.out.println("Please select timing: ");
		System.out.println("1) 8AM");
		System.out.println("2) 9AM");
		System.out.println("3) 10AM");
		System.out.println("4) 11AM");
		System.out.println("5) 12PM");
		System.out.println("6) 1PM");
		System.out.println("7) 2PM");
		System.out.println("8) 3PM");
		System.out.println("9) 4PM");
		System.out.println("10) 5PM");
		System.out.println(border);
		System.out.println("");
	}
}
