import java.util.Scanner;
public class Program {
//  Capturing the information for new projects
    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the project name:");
        String projectName = input.nextLine();

        System.out.println("Enter the project number:");
        String projectNumber = input.nextLine();

        System.out.println("What is the building type?");
        String buildingType = input.nextLine();

        System.out.println("Enter the physical address of the project:");
        String physicalAddress = input.nextLine();

        System.out.println("Enter the erf number:");
        String erfNumber = input.nextLine();

        System.out.println("What is the total amount of the project?");
        String totalFee = input.nextLine();

        System.out.println("How much has been paid so far?");
        String amountToDate = input.nextLine();

        System.out.println("What is the due date of the project:");
        String deadline = input.nextLine();

        System.out.println("Enter the full name of the contractor:");
        String contractorFullName = input.nextLine();

        System.out.println("Enter the Cellphone or Telephone Number of the contractor");
        String contractorCellorTelNo = input.nextLine();

        System.out.println("Enter the email address of the contractor");
        String contractorEmailAddress = input.nextLine();

        System.out.println("Enter the home address of the contractor");
        String contractorHomeAddress = input.nextLine();

        System.out.println("Enter the full name of the architect");
        String architectFullName = input.nextLine();

        System.out.println("Enter the Cellphone or Telephone Number of the architect");
        String architectCellorTelNo = input.nextLine();

        System.out.println("Enter the email address of the architect");
        String architectEmailAddress = input.nextLine();

        System.out.println("Enter the home address of the architect");
        String architectHomeAddress = input.nextLine();

        System.out.println("Enter the full name of the client");
        String clientFullName = input.nextLine();

        System.out.println("Enter the email address of the client");
        String clientEmailAddress = input.nextLine();

        System.out.println("Enter the clients Cellphone or Telephone number");
        String clientCellorTelNo = input.nextLine();

        System.out.println("Enter the home address of the client");
        String clientHomeAddress = input.nextLine();
// Creating the object for the new addition
            Projects newDetails = new Projects(projectName,projectNumber,buildingType,physicalAddress,erfNumber,
            totalFee, amountToDate,deadline,contractorFullName,contractorCellorTelNo,contractorEmailAddress,
            contractorHomeAddress,architectFullName,architectCellorTelNo,architectEmailAddress,architectHomeAddress,
            clientFullName,clientCellorTelNo,clientEmailAddress,clientHomeAddress);

            PeoplesList Malone = new PeoplesList("Jeff Malone", "jeffmalone@yahoo.com",
                    "0646077965", "202 Eclipse Apartments", "Stefan Antoni, " +
                    "Roald Dahl", "stefanPoised@gmail.com, roaldPoised@gmail.com",
                    "0123456789, 0987654321","123 Cherry Lane, 456 Apple Avenue",
                    "Logan Paul","loganContractor@gmail.com", "0826794119",
                    "46 Serengeti Street");

        PeoplesList Rogers = new PeoplesList("Steve Rogers", "steve.rogers@gmail.com",
                "0646074862","67 London Avenue", "Reed Richards",
                "ReedPoised@gmail.com", "0911194563",
                "67 Clifton Drive", "Jake Paul", "JakeContractor@gmail.com",
                "08119148134","44 Hamilton Lane");

            System.out.println("Details of new project:" + "\n");
            System.out.println(newDetails + "\n");
            System.out.println("Details of Project Malone: " + "\n");
            System.out.println(Malone + "\n");
            System.out.println("Details of Project Rogers: " + "\n");
            System.out.println(Rogers + "\n");
    }
}