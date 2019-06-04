public class SkipList {
    ListNode first=new ListNode();//最上方第一個節點指針
    ListNode down=new ListNode();//尾節點指針
    ListNode right=new ListNode();//右節點指針
    ListNode left=new ListNode();//左節點指針
    int level;
    SkipList(int input[]){
        int size=input.length;
        level=(int)log(2,size);
        first.data=Integer.MIN_VALUE;
        first.right.data=Integer.MAX_VALUE;
        rightConnect(first,first.right);
        ListNode newList =first;
        for(int i=level-1;i>0;i--){
            downConnect(newList,newList.down);
            newList=newList.down;
            newList.data=Integer.MIN_VALUE;
            newList.right.data=Integer.MAX_VALUE;
            rightConnect(newList,newList.right);
        }
        for(int i=0;i<size;i++){
            add(input[i]);
        }

    }
    static public double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
    void add(int in){
        if(first.data==0){
            first.data=in;
        }else{
            if(search(in)==true){
                System.out.println("重複的節點");//遇到重複節點不添加，回傳錯誤信息
            }else{
                ListNode temp=first;
                int tempLevel=level;
                while(true){
                    if(in<temp.right.data){
                        temp=temp.right;//找到同層中與加入的值最接近且小於其的點
                    }else if(tempLevel>1){
                        temp=temp.down;
                    }else if(tempLevel==1){
                        ListNode nextTemp=temp.right;
                        ListNode newNode =new ListNode(in);
                        rightConnect(temp,newNode);
                        rightConnect(newNode,nextTemp);
                        while(Math.random()>0.5&&tempLevel<4){
                            downConnect(newNode.up,newNode);
                            newNode.up.data=newNode.data;
                            newNode=newNode.up;
                        }
                    }

                }
            }
        }
    }
    boolean search(int in){
        return true;
    }
    void rightConnect(ListNode a,ListNode b){
        a.right=b;
        b.left=a;
    }
    void downConnect(ListNode a,ListNode b){
        a.down=b;
        b.up=a;
    }
    void delete(int in){}
    void show(){}
}
class ListNode{
    int  data;//存的資料
    ListNode up;//上方節點的位置
    ListNode down;//下方節點的位置
    ListNode right;//右邊節點的位置
    ListNode left;//右邊節點的位置
    ListNode(int data){
        this.data=data;
    }
    ListNode(){

    }
}