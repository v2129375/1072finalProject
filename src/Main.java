import java.util.*;
public class Main {
    public static void main(String arg[]){
        System.out.println("請選擇你使用的資料結構");
        System.out.println("1、平衡二叉樹  2、紅黑樹  3、跳躍串列");
        Scanner sc =new Scanner(System.in);
        int choose=sc.nextInt();
        System.out.println("請輸入你要輸入的節點個數");
        int n =sc.nextInt();
        int input[]=new int[n];
        System.out.println("是否使用隨機數？需要請輸入0,不需要請輸入其他值");
        if(sc.nextInt()==0){
            for(int i=0;i<n;i++){
                input[i]=(int)(Math.random()*100);
            }
        }else{
            for(int i=0;i<n;i++){
                System.out.println("請輸入剩下"+(n-i)+"個節點的值");
                input[i]=sc.nextInt();
            }
        }
        System.out.println("這是你本次輸入的數值");
        for(int i=0;i<n;i++){
            System.out.print(input[i]+" ");
        }
        System.out.println();

        if(choose==1){
            testTree t =new testTree(input);
            while(true){
                System.out.println("請選擇你要做的動作   1、新增    2、刪除    3、查找");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("請輸入要新增的元素");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("請輸入要刪除的元素");
                    t.delete(sc.nextInt());
                }
                    System.out.println("請輸入要查找的元素");
                    t.search(sc.nextInt());
            }

        }else if(choose==2){
            testTree t =new testTree(input);
            while(true){
                System.out.println("請選擇你要做的動作   1、新增    2、刪除    3、查找");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("請輸入要新增的元素");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("請輸入要刪除的元素");
                    t.delete(sc.nextInt());
                }
                System.out.println("請輸入要查找的元素");
                t.search(sc.nextInt());
            }
        }else if(choose==3){
            testTree t =new testTree(input);
            while(true){
                System.out.println("請選擇你要做的動作   1、新增    2、刪除    3、查找");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("請輸入要新增的元素");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("請輸入要刪除的元素");
                    t.delete(sc.nextInt());
                }
                System.out.println("請輸入要查找的元素");
                t.search(sc.nextInt());
            }
        }
    }
}
