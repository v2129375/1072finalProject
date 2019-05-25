public class SkipList {
    Listnode front;//首節點指針
    Listnode nil;//尾節點指針
    Listnode sk;//第一個節點的名稱
    SkipList(int input[]){
        int size=input.length;
        for(int i=0;i<size;i++){
            if(front.next==null){//建立第一個節點
                front.next.data=input[i];
                nil.next=front.next;
            }else{
                add(input[i]);
            }


        }

    }
    void add(int in){}
    boolean search(int in){return true;}
    void delete(int in){}
    void show(){}
}
class Listnode{
    int  data;//存的資料
    Listnode next;//右邊節點的位置
    Listnode down;//下方節點的位置
    Listnode(int data){
        this.data=data;
    }
    Listnode(){

    }
}