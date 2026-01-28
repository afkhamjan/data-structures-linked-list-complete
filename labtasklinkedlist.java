import java.util.*;
class NODE{
    int data;
    node next ,first,pre,cur;
NODE(){
    first = null;
}
    void entry(){
    Scanner sc = new Scanner (System.in);
    char ch = 'y';
    int count = 0;
    while(ch == 'y'|| ch == 'Y'){
        cur = new node();
        System.out.print("enter data");
        cur.data = sc.nextInt();
        cur.next = null;

        if(first == null)
            first = pre = cur;
        else{
            pre.next = cur;
            pre = cur;
        }
        count ++;
        System.out.println("add mor node(y/n):");
        ch = sc.next().charAt(0);
    }
        System.out.println("total nodes entered: " + count);
    }

    void show(){
    if(first == null){
        System.out.println("list empty! ");
        return ;
    }
    node temp = first;
        System.out.println("linked list ");
        while(temp!=null){
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    void showodd(){
    if(first == null){
        System.out.println("list is empty ");
        return;
    }
    node temp = first;
        System.out.println("odd elements ");
        while (temp!=null){
            if (temp.data%2 !=0)
                System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    void traverse(){
        if (first == null) {
            System.out.println("list is empty");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("enter starting position");
        int pos = sc.nextInt();
        node temp = first;
        int i =1;
        while(temp !=null && i<pos){
            temp = temp.next;
            i++;
        }
        if(temp == null){
            System.out.println("invalid position");
            return;
        }
        System.out.println("traversal from position" + pos + ": ");
        while(temp!=null){
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    void reversal(){
    if (first == null){
        System.out.println("list is empty ");
        return;
    }

    }
}

public class labtasklinkedlist {
}
