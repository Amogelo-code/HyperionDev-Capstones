import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    static int rowsAffected;

    // The capture data function will read through all pieces of data that is already in the text file

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParseException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poisedpms?useSSL=false",
                "amo1sc0d1nG",
                "c0d1nGG8d");
        /* Create a direct line to the database for running our queries. The result variable will provide
           an output from the database
         */
        Statement statement = connection.createStatement();
        ResultSet results;

        results = statement.executeQuery("SELECT Project_Number, Project_Name FROM project_details");
        // Loop over the results, printing them all.
        while (results.next()) {
            System.out.println(results.getInt("Project_Number") + ", " + results.getString("Project_Name"));
        }

        int option;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Please enter an integer to choose from the following options to continue. 
                    
                    1. Create new project
                    2. Search and Edit projects
                    3. View projects
                    4. Incomplete projects
                    5. Overdue projects
                    6. Exit
                    :""");
            option = Integer.parseInt(input.nextLine());

            if (option == 1) {
                createProject(statement);
            } else if (option == 2) {
                searchAndEditProjects(statement);
            } else if (option == 3) {
                viewProjects(statement);
            } else if (option == 4) {
                incompleteProjects(statement);
            } else if (option == 5) {
                overdueProjects(statement);
            } else if (option == 6) {
                System.out.println("You have exited the program");
                break;
            }
        }
    }
    static String getInput(String message){
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        return input.nextLine();
    }

    /**
     * The Create Project function will allow the user to add a new project to the poisedpms database
     * @param statement works with the printTable function to execute the query and to establish a
     *                  connection with the database. This reflects on all other functions.
     */
    private static void createProject(Statement statement) {
        try {
            String projectName = getInput("Enter the project name:");

            Scanner input = new Scanner(System.in);
            System.out.println("Enter the project number:");
            int projectNumber = input.nextInt();

            String buildingType = getInput("What is the building type?");

            String physicalAddress= getInput("Enter the physical address of the project:");

            System.out.println("Enter the erf number:");
            int erfNumber = input.nextInt();

            System.out.println("What is the total amount of the project?");
            int totalFee = input.nextInt();

            System.out.println("How much has been paid so far?");
            int amountToDate = input.nextInt();

            String deadline = getInput("What is the due date of the project: e.g 12 Oct 2024");

            System.out.println("Enter the ID number of the contractor:");
            int contractorID = input.nextInt();

            String contractorName = getInput("Enter the name of the contractor:");

            System.out.println("Enter the cell/telephone number of the contractor:");
            int contractorCellorTel = input.nextInt();

            String contractorEmail = getInput("Enter the email of the contractor:");

            String contractorAddress = getInput("Enter the address of the contractor:");

            System.out.println("Enter the ID number of the architect:");
            int architectID = input.nextInt();

            String architectName = getInput("Enter the name of the architect:");

            System.out.println("Enter the cell/telephone number details of the architect:");
            int architectCellorTel = input.nextInt();

            String architectEmail = getInput("Enter the email of the architect:");

            String architectAddress = getInput("Enter the address of the architect:");

            System.out.println("Enter the ID number of the client:");
            int clientID = input.nextInt();

            String clientName = getInput("Enter the name of the client:");

            System.out.println("Enter the cell/telephone number of the client:");
            int clientCellorTel = input.nextInt();

            String clientEmail = getInput("Enter the email of the client:");

            String clientAddress = getInput("Enter the address of the client:");

            String projectCompletion = getInput("Enter Yes/No if the project has been completed or not:");

            String query1 = "INSERT INTO project_details VALUES('" + projectName + "', " + projectNumber + ", '" + buildingType + "', '" + physicalAddress + "', " + erfNumber + ", " + totalFee + ", " + amountToDate + ", '" + deadline + "', '" + projectCompletion + "')";
            System.out.println(query1);
            String query2 = "INSERT INTO project_manager VALUES(" + contractorID + ", '" + contractorName + "', " + contractorCellorTel + ", '" + contractorEmail + "', '" + contractorAddress + "')";
            System.out.println(query2);
            String query3 = "INSERT INTO architect_details VALUES(" + architectID + ", '" + architectName + "', " + architectCellorTel + ", '" + architectEmail + "', '" + architectAddress + "')";
            System.out.println(query3);
            String query4 = "INSERT INTO client_details VALUES(" + clientID + ", '" + clientName + "', " + clientCellorTel + ", '" + clientEmail + "', '" + clientAddress + "')";
            System.out.println(query4);
            String query5 = "INSERT INTO relationship VALUES(" + projectNumber + ", " + clientID + ", " + architectID + ", " + contractorID + ")";
            System.out.println(query5);
            rowsAffected = statement.executeUpdate(query1);
            rowsAffected = statement.executeUpdate(query2);
            rowsAffected = statement.executeUpdate(query3);
            rowsAffected = statement.executeUpdate(query4);
            rowsAffected = statement.executeUpdate(query5);
            printTable(statement);

        } catch (InputMismatchException e) {
            System.out.println("Please enter the correct information where it is applicable");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    /** The searchAndEditProject function will allow Poised to search up a specific project, and depending
        on their choice, the user can edit specific details of a project, and it will reflect in the database.
    */
    private static void searchAndEditProjects(Statement statement) {
        try {
            int c_id = 0;
            int a_id = 0;
            int p_id = 0;
            String c_name = null;

            // The project number is required to edit a project
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the project number to find a project:");
            int projectFinder = input.nextInt();
            String r = ("SELECT *  FROM project_details, relationship, project_manager, architect_details, client_details  WHERE project_details.project_number= " + projectFinder + " AND relationship.project_number= project_details.project_number " +
                    "AND relationship.contractor_id = project_manager.contractor_id AND relationship.architect_id = architect_details.architect_id AND relationship.client_id = client_details.client_id");//  AND relationship.client_id= client_details.client_id
            ResultSet results = statement.executeQuery(r);
            while (results.next()) {
                System.out.println(results.getString("Project_Name") + ", " + results.getInt("Project_Number") +
                        ", " + results.getString("Building_Type") + ", " + results.getString("Address") +
                        ", " + results.getInt("ERF_Number") + ", " + results.getInt("Total_Fee") +
                        ", " + results.getInt("Amount_Paid") + ", " + results.getString("Deadline") +
                        ", " + results.getString("Project_Status") + ", " + results.getInt("Contractor_ID") +
                        ", " + results.getString("Contractor_Name") + ", " + results.getInt("Contractor_Cell") +
                        ", " + results.getString("Contractor_Email") + ", " + results.getString("Contractor_Address") +
                        ", " + results.getInt("Architect_ID") + ", " + results.getString("Architect_Name") +
                        ", " + results.getInt("Architect_Cell") + ", " + results.getString("Architect_Email") +
                        ", " + results.getString("Architect_Address") + ", " + results.getInt("Client_ID") +
                        ", " + results.getString("Client_Name") + ", " + results.getInt("Client_Cell") +
                        ", " + results.getString("Client_Email") + ", " + results.getString("Client_Address") +
                        ", " + results.getInt("project_number") + ", " + results.getInt("client_id") +
                        ", " + results.getInt("architect_id") + ", " + results.getInt("contractor_id")
                );
                c_id = results.getInt("client_id");
                a_id = results.getInt("architect_id");
                p_id = results.getInt("contractor_id");
                c_name = results.getString("Client_Name");
            }
            int query;
            System.out.println("""
                    Would you like to edit details of the project?
                    1. Yes
                    2. No
                    """);
            query = input.nextInt();
            /* If the user selects 1, another menu with all the available options will allow the user to edit
               any piece of information that they choose. Once completed, it will be reflected in the database.
            */
            if (query == 1) {
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
                int edit = input.nextInt();
                try {
                    if (edit == 1) {
                        /* Tables that require four pieces of data will have four options to edit as illustrated
                           for the client menu. The changes will reflect in the database.
                        */
                        System.out.println("""
                                Choose from below which customer details to update:
                                1. Name
                                2. Email
                                3. Cellphone/ Telephone Number
                                4. Address
                                """);
                        int editCustomer = input.nextInt();
                        if (editCustomer == 1) {
                            String newClientName = getInput("Enter the new name of the client:");
                            rowsAffected = statement.executeUpdate("UPDATE client_details SET Client_Name='" + newClientName + "' WHERE client_id=" + c_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editCustomer == 2) {
                            String newClientEmail = getInput("Enter the new email address of the client:");
                            rowsAffected = statement.executeUpdate("UPDATE client_details SET Client_Email='" + newClientEmail + "' WHERE client_id=" + c_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editCustomer == 3) {
                            System.out.println("Enter the new cellphone/telephone number of the client:");
                            int newClientNumber = input.nextInt();
                            rowsAffected = statement.executeUpdate("UPDATE client_details SET Client_Cell='" + newClientNumber + "'WHERE client_id=" + c_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editCustomer == 4) {
                            String newClientAddress = getInput("Enter the new address of the client:");
                            rowsAffected = statement.executeUpdate("UPDATE client_details SET Client_Address='" + newClientAddress + "'WHERE client_id=" + c_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);
                        }
                    } else if (edit == 2) {
                        /* Tables that require four pieces of data will have four options to edit as illustrated
                           for the architect menu. The changes will reflect in the database.
                        */
                        System.out.println("""
                                Choose from below which architect details to update:
                                1. Name
                                2. Email
                                3. Cellphone/ Telephone Number
                                4. Address
                                """);
                        int editArchitect = input.nextInt();

                        if (editArchitect == 1) {
                            String newArchitectName = getInput("Enter the new full name of the architect:");
                            rowsAffected = statement.executeUpdate("UPDATE architect_details SET Architect_Name='" + newArchitectName + "'WHERE architect_id=" + a_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editArchitect == 2) {
                            String newArchitectEmail = getInput("Update the architects email:");
                            rowsAffected = statement.executeUpdate("UPDATE architect_details SET Architect_Email='" + newArchitectEmail + "'WHERE architect_id=" + a_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editArchitect == 3) {
                            System.out.println("Enter the architects new number:");
                            int newArchitectNo = input.nextInt();
                            rowsAffected = statement.executeUpdate("UPDATE architect_details SET Architect_Cell='" + newArchitectNo + "'WHERE architect_id=" + a_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editArchitect == 4) {
                            String newArchitectAddress = getInput("Enter the architects new address:");
                            rowsAffected = statement.executeUpdate("UPDATE architect_details SET Client_Address='" + newArchitectAddress + "'WHERE architect_id=" + a_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        }
                    } else if (edit == 3) {
                        /* Tables that require four pieces of data will have four options to edit as illustrated
                           for the contractor menu. The changes will reflect in the database.
                        */
                        System.out.println("""
                                Choose from below which contractor details to update:
                                1. Name
                                2. Email
                                3. Cellphone/ Telephone Number
                                4. Address
                                """);
                        int editContractor = input.nextInt();

                        if (editContractor == 1) {
                            String newContractorName = getInput("Enter the new Contractors name:");
                            rowsAffected = statement.executeUpdate("UPDATE project_manager SET Contractor_Name='" + newContractorName + "'WHERE project_manager=" + p_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editContractor == 2) {
                            String newContractorEmail = getInput("Enter the Contractors new email:");
                            rowsAffected = statement.executeUpdate("UPDATE project_manager SET Contractor_Email='" + newContractorEmail + "'WHERE project_manager=" + p_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editContractor == 3) {
                            System.out.println("Enter the Contractors new number:");
                            int newContractorNo = input.nextInt();
                            rowsAffected = statement.executeUpdate("UPDATE project_manager SET Contractor_Cell='" + newContractorNo + "'WHERE project_manager=" + p_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);

                        } else if (editContractor == 4) {
                            String newContractorAddress = getInput("Enter the new address for the client:");
                            rowsAffected = statement.executeUpdate("UPDATE project_manager SET Contractor_Address='" + newContractorAddress + "'WHERE project_manager=" + p_id);
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);
                        }
                    } else if (edit == 4) {
                        System.out.println("Enter the new ERF Number for the project:");
                        int newERF = input.nextInt();
                        rowsAffected = statement.executeUpdate("UPDATE project_details SET ERF_Number='" + newERF + "'WHERE Project_Number=" + projectFinder);
                        System.out.println("Query complete, " + rowsAffected + " rows updated.");
                        printTable(statement);
                    } else if (edit == 5) {
                        System.out.println("Enter the new project number:");
                        int newProjectNo = input.nextInt();
                        rowsAffected = statement.executeUpdate("UPDATE project_details SET Project_Number='" + newProjectNo + "'WHERE Project_Number=" + projectFinder);
                        System.out.println("Query complete, " + rowsAffected + " rows updated.");
                        printTable(statement);
                    } else if (edit == 6) {
                        System.out.println("Enter the new amount that has been paid by the client:");
                        int newAmountToDate = input.nextInt();
                        rowsAffected = statement.executeUpdate("UPDATE project_details SET Amount_Paid='" + newAmountToDate + "'WHERE Project_Number=" + projectFinder);
                        results = statement.executeQuery(r);
                        while (results.next()) {
                            System.out.println(results.getString("Project_Name") + ", " + results.getInt("Project_Number") +
                                    ", " + results.getString("Building_Type") + ", " + results.getString("Address") +
                                    ", " + results.getInt("ERF_Number") + ", " + results.getInt("Total_Fee") +
                                    ", " + results.getInt("Amount_Paid") + ", " + results.getString("Deadline") +
                                    ", " + results.getString("Project_Status") + ", " + results.getInt("Contractor_ID") +
                                    ", " + results.getString("Contractor_Name") + ", " + results.getInt("Contractor_Cell") +
                                    ", " + results.getString("Contractor_Email") + ", " + results.getString("Contractor_Address") +
                                    ", " + results.getInt("Architect_ID") + ", " + results.getString("Architect_Name") +
                                    ", " + results.getInt("Architect_Cell") + ", " + results.getString("Architect_Email") +
                                    ", " + results.getString("Architect_Address") + ", " + results.getInt("Client_ID") +
                                    ", " + results.getString("Client_Name") + ", " + results.getInt("Client_Cell") +
                                    ", " + results.getString("Client_Email") + ", " + results.getString("Client_Address") +
                                    ", " + results.getInt("project_number") + ", " + results.getInt("client_id") +
                                    ", " + results.getInt("architect_id") + ", " + results.getInt("contractor_id"));

                            int balance = results.getInt("Total_Fee") - results.getInt("Amount_Paid");

                            // The following if statement displays a bill to the user only if they have yet
                            // to pay the full total fee.

                            if (results.getInt("Amount_Paid") != results.getInt("Total_Fee")) {
                                String invoice = "Customer name:" + c_name;
                                invoice += "\nCustomer Cellphone number:" + results.getInt("Client_Cell");
                                invoice += "\nCustomer Email Address:" + results.getString("Client_Email");
                                invoice += "\nCustomer Address:" + results.getString("Client_Address");
                                invoice += "\n----------------------------------------------";
                                invoice += "\nRemaining balance:" + balance;
                                invoice += "\n----------------------------------------------";
                                System.out.println(invoice);
                            } else {
                                System.out.println("Amount has been fully paid");
                            }
                            System.out.println("Query complete, " + rowsAffected + " rows updated.");
                            printTable(statement);
                        }
                    } else if (edit == 7) {
                        String newDeadline = getInput("Enter the new deadline for the project:");
                        rowsAffected = statement.executeUpdate("UPDATE project_details SET Deadline='" + newDeadline + "'WHERE Project_Number=" + projectFinder);
                        System.out.println("Query complete, " + rowsAffected + " rows updated.");
                        printTable(statement);

                    } else if (edit == 8) {
                        String projectStatus = getInput("Update the project status:");
                        rowsAffected = statement.executeUpdate("UPDATE project_details SET Project_Status='" + projectStatus + "'WHERE Project_Number=" + projectFinder);
                        System.out.println("Query complete, " + rowsAffected + " rows updated.");
                        printTable(statement);
                    }

                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                } catch (SQLException f) {
                    System.out.println(f);
                }
            } else if (query == 2) {
                System.out.println("You will now return to the main menu");
            }
        }catch (SQLException g) {
            System.out.println(g);
        }
    }

    /** The viewProjects function will allow the user to see all the work that has been done by Poised so far.
     * @param statement works with the printTable function to execute the query and to establish a connection
     *                  with the database.
     * @throws SQLException
     */
    public static void viewProjects(Statement statement) throws SQLException {
        // The code in this function only executes the query details which fall under the project_details table
        ResultSet results = statement.executeQuery("SELECT * from project_details");
        while (results.next()) {
            System.out.println(results.getString("Project_Name") + ", " + results.getInt("Project_Number") +
                    ", " + results.getString("Building_Type") + ", " + results.getString("Address") +
                    ", " + results.getInt("ERF_Number") + ", " + results.getInt("Total_Fee") +
                    ", " + results.getInt("Amount_Paid") + ", " + results.getString("Deadline") +
                    ", " + results.getString("Project_Status"));
        }
    }

    /**The incompleteProjects function will check all the projects that are yet to be complete
     * @param statement works with the printTable function to execute the query and to establish a
                        connection with the database.
     * @throws SQLException
     */
    public static void incompleteProjects(Statement statement) throws SQLException {
        try {
            String status = "No";
            ResultSet results = statement.executeQuery("SELECT * from project_details WHERE project_status= '" + status +"'");

            while (results.next()) {
                System.out.println(results.getString("Project_Name") + ", " + results.getInt("Project_Number") +
                        ", " + results.getString("Building_Type") + ", " + results.getString("Address") +
                        ", " + results.getInt("ERF_Number") + ", " + results.getInt("Total_Fee") +
                        ", " + results.getInt("Amount_Paid") + ", " + results.getString("Deadline") +
                        ", " + results.getString("Project_Status"));
            }
        }catch (SQLSyntaxErrorException e){
            System.out.println(e);
        }
    }
    /** The overdueProjects function will allow the user to see all projects that are past
        the due date
    */
    public static void overdueProjects(Statement statement) {
        // Similar to the viewProjects function, the query executes details which fall under the project_details
        // table
        try {
            String status = "No";
            ResultSet result6 = statement.executeQuery("SELECT * FROM project_details WHERE Deadline<NOW() AND Project_Status= '" +status+ "'");
            while(result6.next()){
                System.out.println(result6.getString("Project_Name") + ", " + result6.getInt("Project_Number") +
                        ", " + result6.getString("Building_Type") + ", " + result6.getString("Address") +
                        ", " + result6.getInt("ERF_Number") + ", " + result6.getInt("Total_Fee") +
                        ", " + result6.getInt("Amount_Paid") + ", " + result6.getString("Deadline") +
                        ", " + result6.getString("Project_Status"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /** The printTable function connects to the database by formatting it in a way that will reflect on mysql.
     * @param statement works with the printTable function to execute the query and to establish a
                        connection with the database.
     * @throws SQLException
     */
    public static void printTable(Statement statement) throws SQLException {
        /* The printTable function is responsible for all data being passed from Java to MySQL. The various
           variables hold data from different tables in the database.
        */
        ResultSet results = statement.executeQuery("SELECT Project_Name, Project_Number, Building_Type, Address, " +
                                    "ERF_Number, Total_Fee, Amount_Paid, Deadline, Project_Status FROM project_details");
        while (results.next()) {
            System.out.println(results.getString("Project_Name") + ", " + results.getInt("Project_Number") +
                    ", " + results.getString("Building_Type") + ", " + results.getString("Address") +
                    ", " + results.getInt("ERF_Number") + ", " + results.getInt("Total_Fee") +
                    ", " + results.getInt("Amount_Paid") + ", " + results.getString("Deadline") +
                    ", " + results.getString("Project_Status"));
        }
        ResultSet results2 = statement.executeQuery("SELECT Contractor_ID, Contractor_Name, Contractor_Cell, Contractor_Email, " +
                                    "Contractor_Address FROM project_manager");
        while (results2.next()) {
            System.out.println(results2.getInt("Contractor_ID") + ", " + results2.getString("Contractor_Name") +
                    ", " + results2.getInt("Contractor_Cell") + ", " + results2.getString("Contractor_Email") +
                    ", " + results2.getString("Contractor_Address"));
        }
        ResultSet results3 = statement.executeQuery("SELECT Architect_ID, Architect_Name, Architect_Cell, Architect_Email, " +
                                    "Architect_Address FROM architect_details");
        while (results3.next()) {
            System.out.println(results3.getInt("Architect_ID") + ", " + results3.getString("Architect_Name") +
                    ", " + results3.getInt("Architect_Cell") + ", " + results3.getString("Architect_Email") +
                    ", " + results3.getString("Architect_Address"));
        }
        ResultSet results4 = statement.executeQuery("SELECT Client_ID, Client_Name, Client_Cell, Client_Email, " +
                                    "Client_Address FROM client_details");
        while (results4.next()) {
            System.out.println(results4.getInt("Client_ID") + ", " + results4.getString("Client_Name") +
                    ", " + results4.getInt("Client_Cell") + ", " + results4.getString("Client_Email") +
                    ", " + results4.getString("Client_Address"));
        }
        ResultSet results5 = statement.executeQuery("SELECT project_number, client_id, architect_id, contractor_id " +
                                    "FROM relationship");
        while (results5.next()) {
            System.out.println(results5.getInt("project_number") + ", " + results5.getInt("client_id") +
                    ", " + results5.getInt("architect_id") + ", " + results5.getInt("contractor_id"));
        }
    }
}