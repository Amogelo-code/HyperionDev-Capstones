public class PeoplesList {
// Attributes
    String ClientName;
    String ClientEmail;
    String ClientCellorTelNo;
    String ClientAddress;
    String ArchitectName;
    String ArchitectEmail;
    String ArchitectCellorTelNo;
    String ArchitectAddress;
    String ContractorName;
    String ContractorEmail;
    String ContractorCellorTelNo;
    String ContractorAddress;

    public PeoplesList(String ClientName,String ClientEmail,String ClientCellorTelNo,String ClientAddress,
                       String ArchitectName,String ArchitectEmail,String ArchitectCellorTelNo,String ArchitectAddress,
                       String ContractorName,String ContractorEmail,String ContractorCellorTelNo,
                       String ContractorAddress){
    this.ClientName = ClientName;
    this.ClientEmail = ClientEmail;
    this.ClientCellorTelNo = ClientCellorTelNo;
    this.ClientAddress = ClientAddress;
    this.ArchitectName = ArchitectName;
    this.ArchitectEmail = ArchitectEmail;
    this.ArchitectCellorTelNo = ArchitectCellorTelNo;
    this.ArchitectAddress = ArchitectAddress;
    this.ContractorName = ContractorName;
    this.ContractorEmail = ContractorEmail;
    this.ContractorCellorTelNo = ContractorCellorTelNo;
    this.ContractorAddress = ContractorAddress;
    }
    public String toString() {
        String output = "Client Details";
            output += "\nName: " + ClientName;
            output += "\nEmail Address: " + ClientEmail;
            output += "\nCellphone/Telephone Number: " + ClientCellorTelNo;
            output += "\nHome Address: " + ClientAddress;
            output += "\n Architect Details";
            output += "\nName: " + ArchitectName;
            output += "\nEmail: " + ArchitectEmail;
            output += "\nCellphone/Telephone Number: " + ArchitectCellorTelNo;
            output += "\nHome Address: " + ArchitectAddress;
            output += "\n Contractor Details";
            output += "\nName: " + ContractorName;
            output += "\nEmail Address: " + ContractorEmail;
            output += "\nCellphone/Telephone Number: " + ContractorCellorTelNo;
            output += "\nHome Address: " + ContractorAddress;
        return output;
    }
// Setters
    public void setClientCellorTelNo(String clientCellorTelNo) {
        ClientCellorTelNo = clientCellorTelNo;
    }
    public void setArchitectName(String architectName) {
        ArchitectName = architectName;
    }
    public void setArchitectCellorTelNo(String architectCellorTelNo) {
        ArchitectCellorTelNo = architectCellorTelNo;
    }
    public void setContractorName(String contractorName) {
        ContractorName = contractorName;
    }
    public void setContractorCellorTelNo(String contractorCellorTelNo) {
        ContractorCellorTelNo = contractorCellorTelNo;
    }
    // The client, contractor and architect object for each project that Poised has done
// Details on Project Malone




}
