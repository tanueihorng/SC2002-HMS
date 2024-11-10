package model;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Available {
	private ArrayList<Availability> availableDates = new ArrayList<>();
	private Scanner scan = new Scanner(System.in);
	
	public int viewAvailableAppointmentSlots() { 
		if (this.availableDates.size() == 0) { 
			System.out.println("No available dates for appointments."); 
			return -1; 
		} 
		 
		System.out.println("Available dates for appointments are:"); 
		int count = 0;

		/* this is ALL available dates OR for the doctor's own personal dates, one of the object made of this class will hold all the available dates -> see Doctor class */ 
		for (int i = 0; i < this.availableDates.size() ; i++) { 
			if (this.availableDates.get(i).getStatus().equals("Available")) { 
				count++; 
				System.out.println((i+1)+")Doctor: "+this.availableDates.get(i).getDoctor().getName()); 
				System.out.println("    Date: "+this.availableDates.get(i).getDate()); 
				System.out.println("    Time: "+this.availableDates.get(i).getTime()); 
			} 
		} 
		return count; 
	}
	 
	
	public Appointment selectAvailableSlot(ArrayList<Appointment> scheduledAppointments, Patient patient) {
		
		int choice = 0;
		boolean validity = false;

		int check = this.viewAvailableAppointmentSlots(); 
		if (check == -1){ 
			return null; 
		} 
		if (check == 0){ 
			System.out.println("No available dates for appointments."); 
			return null; 
		}

	      while (!validity) { 
	            try {
	                System.out.print("Please enter your choice: ");
	                choice = scan.nextInt(); 
	                if(choice>0 && choice<=availableDates.size()) {
	                	/* Check if selected appointment is VALID -> meaning that it has no conflict with other scheduled appointments*/
	                	String date = this.availableDates.get(choice-1).getDate();
	                	String time = this.availableDates.get(choice-1).getTime();
	                	
	                	validity = true;
	                	
	                	for(int i=0;i<scheduledAppointments.size();i++) {
	                		if (!scheduledAppointments.get(i).getStatus().equals("Rejected")) {
		                		String checkDate = scheduledAppointments.get(i).getDate();
		                		String checkTime = scheduledAppointments.get(i).getTime();
		                		if(date.equals(checkDate) && time.equals(checkTime)) {
		                			System.out.print("Conflicting date and time with an already scheduled appointment!");
		                			validity = false;
		                			break;
		                		}
	                		}
	                	}
	                }
	                else {
	                	System.out.println("Please input a choice that is valid.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input! Please enter an appropriate choice.");
	                scan.next(); 
	            }
	        }
	      
			/* clear the enter key */
			scan.nextLine(); 
			
	      	choice = choice-1;
			this.availableDates.get(choice).setStatus("Booked");
			
			/* here can sort the arraylist if wanted (NEED ADD CODE)*/
			
			Appointment appointment = new Appointment(this.availableDates.get(choice).getDoctor(),this.availableDates.get(choice).getDate(),this.availableDates.get(choice).getTime(),patient);
			this.availableDates.get(choice).getDoctor().incomingAppointment(appointment);
			return appointment;
		
	}
	
	public void updateAvailableDates(Doctor doc, String dat, String tim) {
		
		for (int i=0;i<this.availableDates.size();i++) {
			if(this.availableDates.get(i).getStatus().equals("Booked")) {
				Availability currentDate = this.availableDates.get(i);
				if(currentDate.getDoctor()==doc && currentDate.getDate().equals(dat) && currentDate.getTime().equals(tim)) {
					currentDate.setStatus("Available");
					break;
				}
			}
		}
		
	}
	
	public void addAvailableDates(Availability avail) {
		
		Doctor doc = avail.getDoctor();
		String dat = avail.getDate();
		String tim = avail.getTime();
		
		for(int i =0; i<availableDates.size();i++) {
			if(availableDates.get(i).getDoctor() == doc && availableDates.get(i).getDate().equals(dat) && availableDates.get(i).getTime().equals(tim) ) {
				System.out.println("Date is already available.");
				return;
			}
		}
		

		
		availableDates.add(avail);
		
	}
	
}