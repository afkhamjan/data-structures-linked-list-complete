import java.util.*;
class nodee{
    Scanner sc = new Scanner(System.in);
    int data;
    nodee first,next,prev,cur;
    nodee(){
        first = null;
    }
    void entry(){
        char ch = 'y';
        while (ch == 'y'|| ch == 'Y'){
            cur = new nodee();
            cur.data = sc.nextInt();
            cur.next = null;
            if(first == null){
                first = prev =cur;
            }
            else{
                prev.next = cur;
                prev = cur;
            }
            System.out.println("add more node(y/n)?");
            ch = sc.next().charAt(0);
        }
    }
    void show(){
        nodee temp = first;
        while (temp!=null){
            System.out.print(temp.data+ " ");
            temp = temp.next;
        }
    }
    void deltop(){
        nodee temp =first;
        first = first.next;
        temp.next = null;
        System.out.println("deleterd sucessfully");
    }
    void delmids(){
        int flag = 0;
        cur = first;
        int t = sc.nextInt();
        while (cur!=null){
            if (cur.data ==t){
                flag = 1;
                break;
            }
            else{
                prev = cur;
                cur = cur.next;
            }
        }
        if(flag ==1){
            prev.next = cur.next;
            cur.next = null;
            System.out.println("deletion completed! ");
        }
        else{
            System.out.println("nodee does'nt found ");
        }
    }
    void delend(){
        nodee temp =first;
        if (temp ==first){
            while (temp!=null){
                temp = temp.next;
            }
     prev.next = null;
        }
    }
}
public class deletion03 {
    public static void main(String[] args) {

     Scanner sc = new Scanner(System.in);
    nodee list = new nodee();
    int choice = sc.nextInt();
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

        switch (choice){
            case 1:
                list.entry();
                break;
            case 2:
                list.show();
                break;
            case 3:
                list.deltop();
                break;
            case 4:
                list.delmids();
                break;
            case 5:
                list.delend();
                break;
            case 6:
                System.out.println("EXITTING..");
            default:
                System.out.println("not found any method..");

        }
    }while (choice !=5);

    }
}
