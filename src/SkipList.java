public class SkipList {
    Listnode first;//最上方第一個節點指針
    Listnode down;//尾節點指針
    Listnode right;//右節點指針
    Listnode left;//左節點指針
    int level;
    SkipList(int input[]){
        int size=input.length;
        for(int i=0;i<size;i++){
            add(input[i]);
        }

    }
    void add(int in){
        if(first.right.data==0){
            first.right.data=in;
        }else{
            if(search(in)==true){
                System.out.println("重複的節點");//遇到重複節點不添加，回傳錯誤信息
            }else{
                Listnode temp=first;
                while(in<temp.right.data&&temp.right.data!=0){
                    temp.right=temp.right.right;
                }
            }
        }
    }
    boolean search(int in){
        return true;
    }
    void delete(int in){}
    void show(){}
}
class Listnode{
    int  data;//存的資料
    Listnode up;//上方節點的位置
    Listnode down;//下方節點的位置
    Listnode right;//右邊節點的位置
    Listnode left;//右邊節點的位置
    Listnode(int data){
        this.data=data;
    }
    Listnode(){

    }
}