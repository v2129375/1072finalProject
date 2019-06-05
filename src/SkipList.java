import java.util.logging.Level;

public class SkipList {
    ListNode first;//最上方第一個節點指針
    ListNode temp;//進行操作的前一個節點指針
    ListNode nextTemp;//進行操作後一個節點指針
    ListNode searchTemp;//每層都要調用的搜索節點指針
    int level;
    SkipList(int input[]){
        int size=input.length;
        level=(int)log(size,2);//計算最大層數
        System.out.println("跳躍串列的最大層數是"+level);
        first=new ListNode(Integer.MIN_VALUE);
        first.right=new ListNode(Integer.MAX_VALUE);
        rightConnect(first,first.right);
        ListNode newList =first;
        for(int i=level-1;i>0;i--){
            newList.down=new ListNode(Integer.MIN_VALUE);
            downConnect(newList,newList.down);
            newList=newList.down;
            newList.right=new ListNode(Integer.MAX_VALUE);
            rightConnect(newList,newList.right);
        }//在每一層的首尾添加無限大的值
        for(int i=0;i<size;i++){//System.out.println("call add");
            add(input[i]);
            //show();
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
                temp=first;
                int tempLevel=level;
                while(true){//System.out.println("one");
                    if(in>temp.right.data){//System.out.println(">");
                        temp=temp.right;//找到同層中與加入的值最接近且小於其的點
                    }else if(tempLevel>1){//System.out.println("down");
                        temp=temp.down;
                        tempLevel--;
                    }else if(tempLevel==1){//System.out.println("=1");
                        nextTemp=temp.right;
                        ListNode newNode =new ListNode(in);
                        rightConnect(temp,newNode);
                        rightConnect(newNode,nextTemp);
                        while(Math.random()>0.5&&tempLevel<level){//System.out.println("two");//按照50%的概率向上添加節點
                            tempLevel++;
                            temp=newNode.up;
                            temp=new ListNode(newNode.data);
                            downConnect(temp,newNode);//建立上下連接
                            searchTemp=first;
                            for(int i=tempLevel;i<level;i++){//將指針降到合適的層
                                searchTemp=searchTemp.down;
                            }
                            while (in>searchTemp.right.data){//System.out.println("three");
                                searchTemp=searchTemp.right;
                            }//找到同層中與加入的值最接近且小於其的點
                            nextTemp=searchTemp.right;
                            rightConnect(searchTemp,temp);
                            rightConnect(temp,nextTemp);//建立新加入的節點上層節點的左右連接
                            newNode=newNode.up;

                        }
                        break;
                    }
                }
            }
        }
    }
    boolean search(int in){
        temp=first;
        int tempLevel=level;
        while(true){//System.out.println("four");
            if(in>=temp.right.data){//System.out.println("right");
                temp=temp.right;//找到同層中與加入的值最接近且小於其的點
            }else if(in==temp.data){
                return true;
            }else if(tempLevel>1){//System.out.println("down");
                temp=temp.down;
                tempLevel--;
            }else if(tempLevel==1){
                return false;
            }
        }
    }
    void rightConnect(ListNode a,ListNode b){//建立互相的左右連接
        a.right=b;
        b.left=a;
    }
    void downConnect(ListNode a,ListNode b){//建立互相的上下連接
        a.down=b;
        b.up=a;
    }
    void delete(int in){
        if(search(in)==false){
            System.out.println("陣列中沒有此元素");
        }else{
            //System.out.println("上一个节点值"+temp.left.data);
            //System.out.println("下一个节点值"+temp.right.data);
            while(true){
                nextTemp=temp.right;
                searchTemp=temp.left;
                rightConnect(searchTemp,nextTemp);
                if(temp.down==null){
                    break;
                }else{
                    temp=temp.down;
                }
            }
        }
    }
    void show(){
        for(int i=level;i>0;i--){
            System.out.print("Level"+i+":");//添加層數標記
            searchTemp=first;//從頭開始映出
            for(int j=i;j<level;j++){//每映出完一層就重新計算層數
                searchTemp=searchTemp.down;
            }
            while (searchTemp.data<=100){
                if(searchTemp.data>0){//不映出首尾的無線大值
                    System.out.print(" "+searchTemp.data);
                }
                searchTemp=searchTemp.right;
            }
            System.out.println();
        }
    }
    /*public static void main(String arg[]){
        int [] in={1,2,3,4,5};
        SkipList t=new SkipList(in);
    }*/
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
