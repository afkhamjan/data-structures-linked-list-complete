import java.util.*;
class node {
    int data;
    node next,back,first,prev;

    node(){
        first = null;
    }
    void entry(){
        Scanner sc = new Scanner(System.in);
        char ch = 'y';
        while(ch == 'y'|| ch == 'Y'){
          node  cur = new node();
            cur.data = sc.nextInt();
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
    }

public class creation {
    public static void main(String[] args) {

    }
}
