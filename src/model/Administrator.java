package model;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import controller.InventoryManagement;
import controller.StaffManagement;
import model.Appointment;

public class Administrator extends User {
    private Scanner scan = new Scanner(System.in);
	private InventoryManagement inventoryManagement;
    private StaffManagement staffManagement;
	private ArrayList<Appointment> allAppointments;

    public Administrator(String id, String name, int age, String gender, ArrayList<Inventory> allInventoryItems, ArrayList<User> staffList, ArrayList<Appointment> allAppointments) {
        super(id, name, age, gender);
		this.inventoryManagement = new InventoryManagement(allInventoryItems);
        this.staffManagement = new StaffManagement(staffList, allInventoryItems, allAppointments);
		this.allAppointments = allAppointments;
    }

	
    // Inventory management methods delegated to InventoryManagement
    public void manageInventory() {
        inventoryManagement.manageInventory();
    }

	public void approveRequest() {
		inventoryManagement.approveRequest();
	}

    // Staff management methods delegated to StaffManagement
    public void manageStaff() {
        staffManagement.manageStaff();
    }
	
    public void displayMenu() {
        int choice=-1;
		boolean validity = false;
		
		do{
			validity = false;
			while (!validity) { 
				try {
					System.out.println("1) View and Manage Staff\r\n"
							+ "2) View Appointment Details\r\n"
							+ "3) View and Manage Medication Inventory\r\n"
							+ "4) Approve Replenishment Requests"
							+ "5) Logout\r\n");
					System.out.print("Please enter your choice: ");
					choice = scan.nextInt(); 
					if(choice > 0 && choice <= 5) {
						validity = true;
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
			switch(choice) {
			case 1:
				manageStaff();
				break;
			case 2:
				viewAppointment(allAppointments);
				break;
			case 3:
				manageInventory();
				break;
			case 4:
				approveRequest();
				break;
			case 5:
				break;
			}
		} while (choice != 5);
		
		// scan.close();
    }
	
	private void viewAppointment(ArrayList<Appointment> allAppointments) {
		for(Appointment a : allAppointments) {
			a.printInfoForAdmin();
		}
	}
    
}
