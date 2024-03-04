import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Projects> allProjects = new ArrayList<>();

    // The capture data function will read through all pieces of data that is already in the text file
    private static void captureData() {
        try {
            File readFile = new File("database.txt");
            Scanner scan = new Scanner(readFile);
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                PeoplesList contractor = new PeoplesList(line[8], line[9], line[10], line[11]);
                PeoplesList architect = new PeoplesList(line[12], line[13], line[14], line[15]);
                PeoplesList client = new PeoplesList(line[16], line[17], line[18], line[19]);
                Projects tempFileProj = new Projects(line[0],line[1],line[2],line[3],line[4],line[5],line[6],line[7],
                        contractor, architect, client,line[20]);
                allProjects.add(tempFileProj);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found");
        }
    }
    //  Capturing the information for new projects
    private static void createProject() {
        try {
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

            System.out.println("Enter the name of the contractor:");
            String contractorName = input.nextLine();

            System.out.println("Enter the cell/telephone number of the contractor:");
            String contractorCellorTel = input.nextLine();

            System.out.println("Enter the email of the contractor:");
            String contractorEmail = input.nextLine();

            System.out.println("Enter the address of the contractor:");
            String contractorAddress = input.nextLine();

            System.out.println("Enter the name of the architect:");
            String architectName = input.nextLine();

            System.out.println("Enter the cell/telephone number details of the architect:");
            String architectCellorTel = input.nextLine();

            System.out.println("Enter the email of the architect:");
            String architectEmail = input.nextLine();

            System.out.println("Enter the address of the architect:");
            String architectAddress = input.nextLine();

            System.out.println("Enter the name of the client:");
            String clientName = input.nextLine();

            System.out.println("Enter the cell/telephone number of the client:");
            String clientCellorTel = input.nextLine();

            System.out.println("Enter the email of the client:");
            String clientEmail = input.nextLine();

            System.out.println("Enter the address of the client:");
            String clientAddress = input.nextLine();

            System.out.println("Enter Yes/No if the project has been completed or not:");
            String projectCompletion = input.nextLine();

            PeoplesList contractorInfo = new PeoplesList(contractorName,contractorEmail,contractorCellorTel,contractorAddress);
            PeoplesList architectInfo = new PeoplesList(architectName, architectEmail,architectCellorTel,architectAddress);
            PeoplesList clientInfo = new PeoplesList(clientName,clientEmail,clientCellorTel,clientAddress);

            // Creating the object for the new addition
            Projects newDetails = new Projects(projectName, projectNumber, buildingType, physicalAddress,
                    erfNumber,totalFee,amountToDate,deadline,contractorInfo,architectInfo,clientInfo,projectCompletion);

            allProjects.add(newDetails);

            FileWriter writer = new FileWriter("database.txt", true);
            System.out.println(newDetails);
            writer.write(newDetails.toString());
            writer.close();
        }
        catch (InputMismatchException e) {
            System.out.println("Please enter the correct information where it is applicable");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* The searchAndEditProject function will allow Poised to search up a specific project and depending
       on their choice, the user can edit specific detail of a project and it will reflect in the database
       text file.
    */
    private static void searchAndEditProjects() throws IOException {

        System.out.println("Enter the project number to find a project:");
        String projectFinder = input.nextLine();
        for (Projects project : allProjects) {
            if (project.getProjectNumber().equals(projectFinder)) {
                System.out.println(project.toString());

                System.out.println("""
                        Would you like to edit details of the project?
                        Y - Yes
                        N - No
                        """);
                String query = input.nextLine();

                if (query.charAt(0) == 'Y') {
                    System.out.println("""
                            Which aspect of the project would you like to edit?
                            1. Customer details
                            2. Architect details
                            3. Contractor details
                            4. ERF Number
                            5. Project Number
                            6. Amount to Date
                            7. Deadline
                            8. Project Completion
                            """);
                    int edit = Integer.parseInt(input.nextLine());
                    try {
                        if (edit == 1) {
                            System.out.println("""
                                    Choose from below which customer details to update:
                                    1. Name
                                    2. Email
                                    3. Cellphone/ Telephone Number
                                    4. Address
                                    """);
                            int editCustomer = Integer.parseInt(input.nextLine());
                            if (editCustomer == 1) {
                                System.out.println("Enter the new full name for the client:");
                                String newName = input.nextLine();
                                project.Client.setName(newName);
                            } else if (editCustomer == 2) {
                                System.out.println("Enter the new email address for the client:");
                                String newClientEmail = input.nextLine();
                                project.Client.setEmail(newClientEmail);
                            } else if (editCustomer == 3) {
                                System.out.println("Enter the new cellphone/telephone number for the client:");
                                String newClientNumber = input.nextLine();
                                project.Client.setCellorTelNo(newClientNumber);
                            } else if (editCustomer == 4) {
                                System.out.println("Enter the new address for the client:");
                                String newClientAddress = input.nextLine();
                                project.Client.setAddress(newClientAddress);
                            }
                        } else if (edit == 2) {
                            System.out.println("""
                                    Choose from below which architect details to update:
                                    1. Name
                                    2. Email
                                    3. Cellphone/ Telephone Number
                                    4. Address
                                    """);
                            int editArchitect = Integer.parseInt(input.nextLine());

                            if (editArchitect == 1) {
                                System.out.println("Enter the new full name for the architect:");
                                String newArchitectName = input.nextLine();
                                project.Architect.setName(newArchitectName);
                            } else if (editArchitect == 2) {
                                System.out.println("Enter the new email for the architect:");
                                String newArchitectEmail = input.nextLine();
                                project.Architect.setEmail(newArchitectEmail);
                            } else if (editArchitect == 3) {
                                System.out.println("Enter the new cellphone/telephone number for the architect:");
                                String newArchitectNo = input.nextLine();
                                project.Architect.setCellorTelNo(newArchitectNo);
                            } else if (editArchitect == 4) {
                                System.out.println("Enter the new address for the architect:");
                                String newArchitectAddress = input.nextLine();
                                project.Architect.setAddress(newArchitectAddress);
                            }
                        } else if (edit == 3) {
                            System.out.println("""
                                    Choose from below which contractor details to update:
                                    1. Name
                                    2. Email
                                    3. Cellphone/ Telephone Number
                                    4. Address
                                    """);
                            int editContractor = Integer.parseInt(input.nextLine());

                            if (editContractor == 1) {
                                System.out.println("Enter the new full name for the client:");
                                String newContractorName = input.nextLine();
                                project.Contractor.setName(newContractorName);
                            } else if (editContractor == 2) {
                                System.out.println("Enter the new full name for the client:");
                                String newContractorEmail = input.nextLine();
                                project.Contractor.setEmail(newContractorEmail);
                            } else if (editContractor == 3) {
                                System.out.println("Enter the new full name for the client:");
                                String newContractorNo = input.nextLine();
                                project.Contractor.setCellorTelNo(newContractorNo);
                            } else if (editContractor == 4) {
                                System.out.println("Enter the new full name for the client:");
                                String newContractorAddress = input.nextLine();
                                project.Contractor.setAddress(newContractorAddress);
                            }
                        } else if (edit == 4) {
                            System.out.println("Enter the new ERF Number:");
                            String newERF = input.nextLine();
                            project.setErfNumber(newERF);
                        } else if (edit == 5) {
                            System.out.println("Enter the new project number:");
                            String newProjectNo = input.nextLine();
                            project.setProjectNumber(newProjectNo);
                        } else if (edit == 6) {
                            System.out.println("Enter the new full name for the client:");
                            String newAmountToDate = input.nextLine();
                            project.setAmountToDate(newAmountToDate);
                        } else if (edit == 7) {
                            System.out.println("Enter the new full name for the client:");
                            String newDeadline = input.nextLine();
                            project.setDeadline(newDeadline);
                        } else if (edit == 8) {
                            System.out.println("Enter the new full name for the client:");
                            String projectStatus = input.nextLine();
                            project.setProjectCompletion(projectStatus);
                        }
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(e);
                    }
                } else if (query == "N") {
                    break;
                }
            }
        }
    }

    // The viewProjects function will allow the user to see all the work that has been done by Poised so far.
    public static void viewProjects(){
        for (Projects writeProject : allProjects) {
            System.out.println(writeProject);
        }
    }
    // The incompleteProjects function will check all the projects that are complete or not
    public static void incompleteProjects(){
        for (Projects writeProject : allProjects) {
            if (writeProject.getProjectCompletion().equals("No"))
                System.out.println(writeProject);
        }
    }
    /* The overdueProjects function will allow the user to see all projects that are past
       the due date
    */
    public static void overdueProjects() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date();
        try {
            System.out.println( "we here baby");
            for (Projects writeProject : allProjects) {
                Date textFileDateObject = formatter.parse(writeProject.getDeadline());
                if ((textFileDateObject).compareTo(date) > 0) {
                    System.out.println(writeProject);
                }
            }
        } catch (ParseException e) {
            System.out.println(e);
        }
    }
    public static void main(String [] args) throws IOException, ParseException {

        int option;
        while (true) {
            System.out.println("""
                Please enter an integer to choose from the following options to continue. 
                Once you are happy with your project, input 7 to exit the program and the 
                necessary changes will reflect:

                1. Read data
                2. Create new project
                3. Search and Edit projects
                4. View projects
                5. Incomplete projects
                6. Overdue projects
                7. Exit
                :""");
            option = Integer.parseInt(input.nextLine());

            if (option == 1) {
                captureData();
            } else if (option == 2) {
                createProject();
            } else if (option == 3) {
                searchAndEditProjects();
            } else if (option == 4) {
                viewProjects();
            } else if (option == 5){
                incompleteProjects();
            } else if (option == 6) {
                overdueProjects();
            } else if (option == 7) {
                FileWriter writer = new FileWriter("database.txt", false);

                for (Projects writeProject : allProjects) {
                    writer.write(writeProject.toString());
                }
                writer.close();
                System.out.println("You have exited the program");
                break;
            }
        }
    }
}