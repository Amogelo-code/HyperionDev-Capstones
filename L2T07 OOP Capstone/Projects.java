import java.util.Scanner;

public class Projects {
    // Attributes
    String projectNumber;
    String projectName;
    String buildingType;
    String physicalAddress;
    String erfNumber;
    String totalFee;
    String amountToDate;
    String deadline;
    String contractorFullName;
    String contractorEmailAddress;
    String contractorCellorTelNo;
    String contractorHomeAddress;
    String architectFullName;
    String architectEmailAddress;
    String architectCellorTelNo;
    String architectHomeAddress;
    String clientFullName;
    String clientEmailAddress;
    String clientCellorTelNo;
    String clientHomeAddress;

    // Constructor was too long for one line. Does not look neat but all attributes are present
    public Projects(String projectName, String projectNumber, String buildingType, String physicalAddress,
                    String erfNumber, String totalFee, String amountToDate, String deadline, String contractorFullName,
                    String contractorEmailAddress, String contractorCellorTelNo, String contractorHomeAddress,
                    String architectFullName, String architectEmailAddress, String architectCellorTelNo,
                    String architectHomeAddress, String clientFullName, String clientEmailAddress,
                    String clientCellorTelNo, String clientHomeAddress){
        this.projectName = projectName;
        this.projectNumber = projectNumber;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.amountToDate = amountToDate;
        this.deadline = deadline;
        this.contractorFullName = contractorFullName;
        this.contractorEmailAddress = contractorEmailAddress;
        this.contractorCellorTelNo = contractorCellorTelNo;
        this.contractorHomeAddress = contractorHomeAddress;
        this.architectFullName = architectFullName;
        this.architectEmailAddress = architectEmailAddress;
        this.architectCellorTelNo = architectCellorTelNo;
        this.architectHomeAddress = architectHomeAddress;
        this.clientFullName = clientFullName;
        this.clientEmailAddress = clientEmailAddress;
        this.clientCellorTelNo = clientCellorTelNo;
        this.clientHomeAddress = clientHomeAddress;
    }
//    All the variables and their captured information will be printed out
    public String toString() {
        String output = "Project Name: " + projectName;
            output += "\nProject Number: " + projectNumber;
            output += "\nBuilding Type: " + buildingType;
            output += "\nPhysical Address: " + physicalAddress;
            output += "\nERF Number:" + erfNumber;
            output += "\nTotal Fee:" + totalFee;
            output += "\nAmount Paid to Date:" + amountToDate;
            output += "\nDeadline:" + deadline;
            output += "\n Contractor Details";
            output += "\nFull Name:" + contractorFullName;
            output += "\nCellphone or Telephone Number:" + contractorCellorTelNo;
            output += "\nEmail Address:" + contractorEmailAddress;
            output += "\nHome Address:" + contractorHomeAddress;
            output += "\n Architect Details";
            output += "\nFull Name:" + architectFullName;
            output += "\nCellphone or Telephone Number:" + architectCellorTelNo;
            output += "\nEmail Address:" + architectEmailAddress;
            output += "\nHome Address:" + architectHomeAddress;
            output += "\n Client Details";
            output += "\nFull Name:" + clientFullName;
            output += "\nCellphone or Telephone Number:" + clientCellorTelNo;
            output += "\nEmail Address:" + clientEmailAddress;
            output += "\nHome Address:" + clientHomeAddress;
        return output;
    }
//    Setters for the deadline, amount to date and contractor details
    public void setDeadline(String newDeadline) {
        this.deadline = newDeadline;
    }
    public void setAmountToDate(String newAmountToDate){
        this.amountToDate = newAmountToDate;
    }
    public void setContractorFullName(String newContractorFullName){
        this.contractorFullName = newContractorFullName;
    }
    public void setContractorCellorTelNo(String newContractorCellorTelNo){
        this.contractorEmailAddress = newContractorCellorTelNo;
    }
    public void setContractorEmailAddress(String newContractorEmailAddress) {
        this.contractorEmailAddress = newContractorEmailAddress;
    }
    public void setContractorHomeAddress(String newContractorHomeAddress) {
        this.contractorHomeAddress = newContractorHomeAddress;
    }
}