import java.util.*;
class nodee{
    int data;
    nodee next,prev,back,first;

    nodee(){
        first = null;
    }
    void creation(){
        Scanner sc = new Scanner (System.in);
        char ch = 'y';
        while (ch =='y'|| ch =='Y'){
            nodee cur = new nodee();
            cur.data =sc.nextInt();
            cur.next = null;
            cur.back = null;
            if(first == null){
                first = prev = cur;
            }
            else{
                prev.next = cur;
                cur.back = prev;
                prev = cur;
            }
            ch = sc.next().charAt(0);
        }
    }

    void inserttop(){
        Scanner sc = new Scanner(System.in);
        nodee temp = new nodee();
        temp.data = sc.nextInt();
        temp.next = first;
        temp.back = null;
        first = temp;
        System.out.println("node added at top successfully ");
    }

    void atmid(){
        Scanner sc = new Scanner(System.in);
        nodee temp = new nodee();
        int flag = 0;
        nodee cur = first;
        System.out.println("enter key to check ");
        int t = sc.nextInt();
        while(temp!=null){
            if(cur.data == t){
                flag = 1;
                break;
            }
            else{
                prev = cur;
                cur = cur.next;
            }
            if(flag == 1){
                temp = new nodee();
                temp.data = sc.nextInt();
                temp.next = cur;
                cur.back = temp;
                prev.next = temp;
                temp.back = prev;
            }
        }
    }
    void atend(){
        Scanner sc = new Scanner(System.in);
        nodee temp = new nodee();
        nodee last = new nodee();
        if(temp == last){
            last.data = sc.nextInt();
            last.next = null;
            last.back = prev;
        }
    }
}
public class insertion {
    public static void main(String[] args) {

    }
}
