//GROUP ASSIGNMENT
// AFKHAM JAN SP24-BCS-011
// AHMAD ASIF SP24-BCS-013

import java.util.*;
import java.io.*;

//class representing timber record
class Timber{
    char zone;
    int timberID;
    String kind;
    float weight;
    String status;
    float height;
    int quantity;
    int price;
    String cutDate;
    Timber next;

    //constructor
    Timber ( char zone, int timberID, String kind, float weight, String status, float height, int quantity, int price, String cutDate){
        this.zone = zone;
        this.timberID = timberID;
        this.kind = kind;
        this.weight = weight;
        this.status = status;
        this.height = height;
        this.quantity = quantity;
        this.price = price;
        this.cutDate = cutDate;
        this.next = null; //next node=null
    }
}

//manage for timber records
class TimberInventory{
    Timber head = null;
    Scanner sc = new Scanner (System.in);

    // 1: add timber record
    void addTimber() {
        System.out.println("enter Timber ID");
        int id = sc.nextInt();
        sc.nextLine();

        //if duplicate ID
        Timber temp = head;
        while (temp != null) {
            if (temp.timberID == id) {
                System.out.println("timber id already exists");
                return;
            }
            temp = temp.next;
        }

        // take input
        System.out.println("enter zone (A/B/C/D)");
        char zone = sc.next().charAt(0);
        sc.nextLine();

        System.out.println("enter kind ");
        String kind = sc.nextLine();

        System.out.println("enter weight ");
        float weight = sc.nextFloat();

        sc.nextLine();
        System.out.println("enter status(in_stock / sold / reserved)");
        String status = sc.nextLine();

        System.out.println("enter height");
        float height = sc.nextFloat();

        System.out.println("enter quantity");
        int quantity = sc.nextInt();

        System.out.println("enter price ");
        int price = sc.nextInt();

        sc.nextLine();
        System.out.println("enter the cut date (dd/mm/yyyy)");
        String cutdate = sc.nextLine();

        Timber newTimber = new Timber(zone, id, kind, weight, status, height, quantity, price, cutdate);

        //insert at end
        if (head == null)
            head = newTimber;
        else {
            Timber current = head;
            while (current.next != null)
                current = current.next;
            current.next = newTimber;

            System.out.println("timber record added succesfully");
        }
    }

        //display rec
        void displayByZone(){
            System.out.println("enter zone (a/b/c/d):");
            char zone =sc.next().charAt(0);
            Timber temp = head;
            boolean found =false;
            while(temp!=null){
                if (temp.zone == zone){
                    printTimber(temp);
                    found = true;
                }
                temp = temp.next;
            }
            if(!found)
                System.out.println("no records for zone" +zone);
        }
        //3: rec by kind
    void displayByKind(){
        sc.nextLine();
        System.out.println("enter timber kind: ");
        String kind = sc.nextLine();
        Timber temp = head;
        boolean found = false;
        while(temp != null){
            if(temp.kind.equalsIgnoreCase(kind)){
                printTimber(temp);
                found = true;
                break;
            }
            temp = temp.next;
        }
        if(!found)
            System.out.println("record not found for kind " +kind);
    }
    //4: rec where qnty <100
    void lowStockAnalysis(){
        Timber temp = head;
        boolean found = false;
        System.out.println("timbers with quantity less than 100:\n");
        while(temp!=null){
            if(temp.quantity< 100){
                printTimber(temp);
                found = true;
            }
            temp = temp.next;
        }
        if(!found)
            System.out.println("no loow stock found");
    }
    //5: sales update report
    void salesUpdate(){
        System.out.println("neter timber id to sell: ");
        int id = sc.nextInt();
        Timber temp = head;
        while(temp!=null){
            if(temp.timberID == id){
                System.out.println("enter quantity to sell");
                int q = sc.nextInt();
                if(q>temp.quantity){
                    System.out.println("not enough stock available");
                }
                else{
                    temp.quantity -=q;
                    temp.status = "sold";
                    System.out.println("sale success! remaining stock: " + temp.quantity);
                }
                return;
            }
            temp = temp.next;
        }
        System.out.println("timber ID not found.");
    }
    //6: del rec by timber id
    void deleteTimber(){
        System.out.println("enter timber id to delete");
        int id = sc.nextInt();
        Timber temp = head;
        Timber prev = null;

        //if first node del
        if(temp!= null && temp.timberID == id){
            head = temp.next;
            System.out.println("record deleted! ");
            return;
        }
        //search,del in middle + end
        while (temp !=null && temp.timberID!=id){
            prev = temp;
            temp = temp.next;
        }
        if (temp == null){
            System.out.println("timber not found ");
            return;
        }
        prev.next = temp.next;
        System.out.println("record deleted! ");
    }

    //7: update  record
    void updateTimber(){
        System.out.println("Enter Timber ID to update");
        int id = sc.nextInt();
        sc.nextLine();
        Timber temp = head;
        while(temp!=null){
            if(temp.timberID == id){
                System.out.println("enter new STATUS: ");
                temp.status = sc.nextLine();
                System.out.println("enter new PRICE:");
                temp.price = sc.nextInt();
                System.out.println("enter updated QUANTITY: ");
                temp.quantity = sc.nextInt();
                System.out.println("RECORD updated succeful");
                return;
            }
            temp = temp.next;
        }
        System.out.println("your input timber NOT found: ");
    }
    //8: generarate report
    void inventoryReport(){
        Timber temp = head;
        int qty = 0,pr = 0, count =0;
        System.out.println("\n------INVENTORY REPORT-----\n");
        while(temp!=null){
            printTimber(temp);
            qty+=temp.quantity;
            pr+=temp.price;
            count ++;
            temp = temp.next;
        }
        if(count>0)
            System.out.println("Total Records:" + count + "Total Quantity:" + qty + "Avg Price:" + (pr/count));
        else{
            System.out.println("no records to show ");
        }

    }
    //  Backup & Restore
    void backupData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("backup.txt"))) {
            Timber temp = head;
            while (temp != null) {
                pw.println(temp.zone + "," + temp.timberID + "," + temp.kind + "," + temp.weight + "," +
                        temp.status + "," + temp.height + "," + temp.quantity + "," + temp.price + "," + temp.cutDate);
                temp = temp.next;
            }
            System.out.println(" Data backed up successfully!");
        } catch (IOException e) {
            System.out.println(" Error during backup.");
        }
    }

    void restoreData() {
        try (BufferedReader br = new BufferedReader(new FileReader("backup.txt"))) {
            head = null;
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                char zone = data[0].charAt(0);
                int id = Integer.parseInt(data[1]);
                String kind = data[2];
                float weight = Float.parseFloat(data[3]);
                String status = data[4];
                float height = Float.parseFloat(data[5]);
                int quantity = Integer.parseInt(data[6]);
                int price = Integer.parseInt(data[7]);
                String cutDate = data[8];
                Timber newNode = new Timber(zone, id, kind, weight, status, height, quantity, price, cutDate);
                if (head == null) head = newNode;
                else {
                    Timber temp = head;
                    while (temp.next != null)
                        temp = temp.next;
                    temp.next = newNode;
                }
            }
            System.out.println(" Data restored successfully!");
        } catch (IOException e) {
            System.out.println(" Error restoring data.");
        }
    }
     void printTimber(Timber temp){
         System.out.println("----------------------------");
         System.out.println("Zone: " + temp.zone);
         System.out.println("timberID: " + temp.timberID);
         System.out.println("Kind: " + temp.kind);
         System.out.println("weight: " + temp.weight);
         System.out.println("statuss: " + temp.status);
         System.out.println("heigjt: " + temp.height);
         System.out.println("qtyy: " + temp.quantity);
         System.out.println("prices: " + temp.price);
         System.out.println("CUT-DATE: " + temp.cutDate);
    }
}

public class timberproject {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        TimberInventory list = new TimberInventory();
        int choice;

        do{
            System.out.println("\n===== MUHAMMAD UMAR TIMBER STORE =====");
            System.out.println("1] Add new Timber record");
            System.out.println("2] Display all records by Zone");
            System.out.println("3] Display record by Kind");
            System.out.println("4] Analysis (Quantity < 100)");
            System.out.println("5] Sales update");
            System.out.println("6] Delete a record");
            System.out.println("7] Update a recor");
            System.out.println("8] Gen Inventory Report");
            System.out.println("9] Back & Restore Data");
            System.out.println("10] Exitting...");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice){
                case 1 : list.addTimber();
                break;
                case 2 : list.displayByZone();
                break;
                case 3 : list.displayByKind();
                break;
                case 4: list.lowStockAnalysis();
                break;
                case 5: list.salesUpdate();
                break;
                case 6: list.deleteTimber();
                break;
                case 7: list.updateTimber();
                break;
                case 8: list.inventoryReport();
                break;
                case 9: {
                    System.out.println("1] Backup");
                    System.out.println("2] Restore");
                    int opt = sc.nextInt();
                    if (opt == 1) list.backupData();
                    else list.restoreData();
                    break;
                }
                case 10 :
                    System.out.println("EXITTING PROGRAM...");
                    break;
                default :
                    System.out.println("not valid choices.");
            }
        }while(choice !=10);
    }
}
