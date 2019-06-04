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
                input[i]=(int)(Math.random()*100+1);
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
            AVLTree t =new AVLTree(input);
            //System.out.println("t="+t);
            while(true){
                System.out.println("請選擇你要做的動作   1、新增    2、刪除    3、查找    4、映出    5、結束程式");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("請輸入要新增的元素");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("請輸入要刪除的元素");
                    t.delete(sc.nextInt());
                }else if(choose1==3){
                    System.out.println("請輸入要查找的元素");
                    if(t.search(sc.nextInt())==true){
                        System.out.println("資料結構中有此元素");
                    }else{
                        System.out.println("資料結構中沒有此元素");
                    };
                }else if(choose1==4){
                    t.show();
                }else{
                    break;
                }
            }

        }else if(choose==2){
            testTree t =new testTree(input);
            while(true){
                System.out.println("請選擇你要做的動作   1、新增    2、刪除    3、查找    4、映出    5、結束程式");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("請輸入要新增的元素");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("請輸入要刪除的元素");
                    t.delete(sc.nextInt());
                }else if(choose1==3){
                    System.out.println("請輸入要查找的元素");
                    if(t.search(sc.nextInt())==true){
                        System.out.println("資料結構中有此元素");
                    }else{
                        System.out.println("資料結構中沒有此元素");
                    };
                }else if(choose1==4){
                    t.show();
                }else{
                    break;
                }
            }
        }else if(choose==3){
            SkipList t =new SkipList(input);
            while(true){
                System.out.println("請選擇你要做的動作   1、新增    2、刪除    3、查找    4、映出    5、結束程式");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("請輸入要新增的元素");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("請輸入要刪除的元素");
                    t.delete(sc.nextInt());
                }else if(choose1==3){
                    System.out.println("請輸入要查找的元素");
                    if(t.search(sc.nextInt())==true){
                        System.out.println("資料結構中有此元素");
                    }else{
                        System.out.println("資料結構中沒有此元素");
                    };
                }else if(choose1==4){
                    t.show();
                }else{
                    break;
                }
            }
        }
    }
}
