import java.util.Scanner;
class Node{
    int data;
    Node first,next,prev,cur;

    Node(){
        first =null;
    }
    void entry(){
        Scanner sc = new Scanner (System.in);
        char ch = 'y';
        while(ch=='y'|| ch =='Y'){
            cur = new Node();
            cur.data = sc.nextInt();
            cur.next = null;
            if(first == null){
                first = prev = cur;
            }
            else{
                prev.next = cur;
                prev = cur;
            }
            System.out.println("do uh want to add more node (y/n)");
            ch = sc.next().charAt(0);
        }
    }
    void show(){
        Node temp = first;
        while (temp!=null){
            System.out.print(temp.data+ " ");
            temp =temp.next;
        }
        System.out.println();
    }
    void insertiontop(){
        Scanner sc = new Scanner (System.in);
        Node temp = new Node();
        temp.data = sc.nextInt();
        temp.next = first;
        first = temp;
        System.out.println("new node added successfully");
    }
    void insertionmid(){
        int flag = 0;
        Scanner sc  = new Scanner (System.in);
        cur = first;
        int t = sc.nextInt();
        while(cur!=null){
            if (cur.data == t){
                flag =1;
                break;
            }
            else{
                prev = cur;
                cur = cur.next;
            }

        }
        if (flag ==1){
            Node temp = new Node();
            temp.data = sc.nextInt();
            temp.next = cur;
            prev.next= temp;
            System.out.println("node added at mid");
        }
        else{
            System.out.println("node csnnot be added");
        }
    }
    void insertend(){
        Scanner sc = new Scanner(System.in);
       Node temp = new Node();
       if (temp == first){
          while (temp!=null){
              prev = temp;
              temp = temp.next;

            Node  curr= new Node();
              curr.data =sc.nextInt();
              curr.next = null;
              prev.next = cur;
          }
       }
    }

}
public class insert02 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Node list = new Node();
        int choice;
        do {
            System.out.println("-----MENU-----");
            System.out.println("1.entry");
            System.out.println("2.show");
            System.out.println("3.insertion at top");
            System.out.println("4. insertion at mid");
            System.out.println("5.insertion at end");
            System.out.println("6. exitting");
            System.out.println("enter choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    list.entry();
                    break;
                case 2:
                    list.show();
                    break;
                case 3:
                    list.insertiontop();
                    break;
                case 4:
                    list.insertionmid();
                    break;
                case 5:
                    list.insertend();
                    break;
                 case 6:
                     System.out.println("exitiing...");
                     break;
                     default:
                    System.out.println("invalid choices ");
            }
        } while (choice != 5);
    }
}
