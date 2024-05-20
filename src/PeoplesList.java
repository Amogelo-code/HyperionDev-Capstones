// L2T21 updates i: Updated the following variables to the long primitive: ArchitectCellorTelNo,
//                  ClientCellorTelNo,ContractorCellorTelNo. It will show across all three java
//                  files.
// L2T21 updates ii: Added a try/catch block in Program.java

public class PeoplesList {
// Attributes
    String name;
    String email;
    String cellorTelNo;
    String address;

    public PeoplesList(String name, String email, String cellorTelNo, String address){
    this.name = name;
    this.email = email;
    this.cellorTelNo = cellorTelNo;
    this.address = address;
    }

    public String toString() {
        String output = name + "," + email   + "," + cellorTelNo   + "," + address;
        return output;
    }
    // Setters
    public void setName(String name) {
       this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCellorTelNo(String cellorTelNo) {
        this.cellorTelNo = cellorTelNo;
    }
}