public class SkipList {
    Listnode first=new Listnode();//最上方第一個節點指針
    Listnode down=new Listnode();//尾節點指針
    Listnode right=new Listnode();//右節點指針
    Listnode left=new Listnode();//左節點指針
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
                while(true){
                    if(in<temp.right.data&&temp.right.data!=0){
                        temp.right=temp.right.right;//找到同層中與加入的值最接近且小於其的點
                    }else if(temp.right.data==0){

                    }

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