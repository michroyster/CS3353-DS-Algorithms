// public class Customer {
//     int customerID;
//     String phoneNumber;
//     String customerName;
//     String serviceType;

//     int getCustomerID() { return customerID; }
//     String getPhoneNumber() { return phoneNumber; }
//     String getCustomerName() { return customerName; }
//     String getServiceType() { return serviceType; }

//     void setCustomerID(int customerID) { this.customerID = customerID; }
//     void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
//     void setCustomerName(String customerName) { this.customerName = customerName; }
//     void setServiceType(String serviceType) { this.serviceType = serviceType; }

//     public Customer(String[] parameters)
//     {
//         this.setCustomerID(Integer.parseInt(parameters[0]));
//         this.setPhoneNumber(parameters[1]);
//         this.setCustomerName(parameters[2]);
//         this.setServiceType(parameters[3]);
//     }

//     @Override
//     public String toString()
//     {
//         return customerID + "\t" + phoneNumber + "\t" + customerName + "\t" + serviceType;
//     }

//     public void printDetails()
//     {
//         System.out.println(customerID + "\t" + phoneNumber + "\t" + customerName + "\t" + serviceType);
//     }
// }
