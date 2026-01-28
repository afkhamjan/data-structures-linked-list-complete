import java.util.*;
class node {
    int data;
    node first, next, prev, cur;
    node() {
        first = null;
    }
    void entry(){
        Scanner sc = new Scanner(System.in);
        char ch = 'y';
        while (ch == 'y'|| ch == 'Y'){
            cur = new node();
            cur.data=sc.nextInt();
            cur.next = null;
            if(first == null){
                first = prev =cur;
            }
            else{
                prev.next=cur;
                prev = cur;
            }
            System.out.println("do uhh want to add more node(y/n)?");
            ch = sc.next().charAt(0);
        }
    }
    void show(){
        node temp = first;
        while (temp!=null){
            System.out.print(temp.data+ " ");
            temp = temp.next;
        }
    }
}
public class entryshow01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        node list = new node();
        int choice ;
        do {
            System.out.println("-----MENU------");
            System.out.println("1.entry of node:");
            System.out.println("2.SHOW");
            System.out.println("3.exit");
            System.out.println("enter choice to enter");
            choice  = sc.nextInt();
            switch(choice){
                case 1: list.entry();
                break;
                case 2: list.show();
                break;
                case 3:
                    System.out.println("EXITING...");
                    break;
                default:
                    System.out.println("invalid choice : try again.");
                    break;
            }
        }while(choice !=3);
    }
}
