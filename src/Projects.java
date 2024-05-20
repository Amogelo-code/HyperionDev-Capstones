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
    PeoplesList Contractor;
    PeoplesList Architect;
    PeoplesList Client;
    String projectCompletion;

    // Constructor was too long for one line. Does not look neat but all attributes are present
    public Projects(String projectName,String projectNumber,String buildingType,String physicalAddress,
                    String erfNumber,String totalFee,String amountToDate,String deadline,PeoplesList Contractor,
                    PeoplesList Architect,PeoplesList Client,String projectCompletion){
        this.projectName = projectName;
        this.projectNumber = projectNumber;
        this.buildingType = buildingType;
        this.physicalAddress = physicalAddress;
        this.erfNumber = erfNumber;
        this.totalFee = totalFee;
        this.amountToDate = amountToDate;
        this.deadline = deadline;
        this.Contractor = Contractor;
        this.Architect = Architect;
        this.Client = Client;
        this.projectCompletion = projectCompletion;
    }
//    All the variables and their captured information will be printed out
    public String toString() {
        String output = projectName + "," + projectNumber + "," + buildingType + "," + physicalAddress + "," + erfNumber + "," + totalFee + "," + amountToDate + "," + deadline + "," + Contractor.toString() + "," + Architect.toString() + "," + Client.toString() + "," + projectCompletion + "\n";
        return output;
    }

    // Getters and Setters
    public String getProjectName(){
        return projectName;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getErfNumber() {
        return erfNumber;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public String getProjectCompletion(){
        return projectCompletion;
    }

    public void setDeadline(String newDeadline) {
        this.deadline = newDeadline;
    }
    public void setErfNumber(String erfNumber) {
        this.erfNumber = erfNumber;
    }
    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }
    public void setAmountToDate(String amountToDate) {
        this.amountToDate = amountToDate;
    }
    public void setProjectCompletion(String projectCompletion) {
        this.projectCompletion = projectCompletion;
    }


}