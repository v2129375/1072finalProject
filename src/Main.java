import java.util.*;
public class Main {
    public static void main(String arg[]){
        System.out.println("Ո�x����ʹ�õ��Y�ϽY��");
        System.out.println("1��ƽ������  2���t�ژ�  3�����S����");
        Scanner sc =new Scanner(System.in);
        int choose=sc.nextInt();
        System.out.println("Ոݔ����Ҫݔ��Ĺ��c����");
        int n =sc.nextInt();
        int input[]=new int[n];
        System.out.println("�Ƿ�ʹ���S�C������ҪՈݔ��0,����ҪՈݔ������ֵ");
        if(sc.nextInt()==0){
            for(int i=0;i<n;i++){
                input[i]=(int)(Math.random()*100+1);
            }
        }else{
            for(int i=0;i<n;i++){
                System.out.println("Ոݔ��ʣ��"+(n-i)+"�����c��ֵ");
                input[i]=sc.nextInt();
            }
        }
        System.out.println("�@���㱾��ݔ��Ĕ�ֵ");
        for(int i=0;i<n;i++){
            System.out.print(input[i]+" ");
        }
        System.out.println();

        if(choose==1){
            testTree t =new testTree(input);
            while(true){
                System.out.println("Ո�x����Ҫ���Ą���   1������    2���h��    3������    4��ӳ��    5���Y����ʽ");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("Ոݔ��Ҫ������Ԫ��");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("Ոݔ��Ҫ�h����Ԫ��");
                    t.delete(sc.nextInt());
                }else if(choose1==3){
                    System.out.println("Ոݔ��Ҫ���ҵ�Ԫ��");
                    if(t.search(sc.nextInt())==true){
                        System.out.println("�Y�ϽY�����д�Ԫ��");
                    }else{
                        System.out.println("�Y�ϽY���Л]�д�Ԫ��");
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
                System.out.println("Ո�x����Ҫ���Ą���   1������    2���h��    3������    4��ӳ��    5���Y����ʽ");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("Ոݔ��Ҫ������Ԫ��");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("Ոݔ��Ҫ�h����Ԫ��");
                    t.delete(sc.nextInt());
                }else if(choose1==3){
                    System.out.println("Ոݔ��Ҫ���ҵ�Ԫ��");
                    if(t.search(sc.nextInt())==true){
                        System.out.println("�Y�ϽY�����д�Ԫ��");
                    }else{
                        System.out.println("�Y�ϽY���Л]�д�Ԫ��");
                    };
                }else if(choose1==4){
                    t.show();
                }else{
                    break;
                }
            }
        }else if(choose==3){
            testTree t =new testTree(input);
            while(true){
                System.out.println("Ո�x����Ҫ���Ą���   1������    2���h��    3������    4��ӳ��    5���Y����ʽ");
                int choose1=sc.nextInt();
                if(choose1==1){
                    System.out.println("Ոݔ��Ҫ������Ԫ��");
                    t.add(sc.nextInt());
                }else if(choose1==2){
                    System.out.println("Ոݔ��Ҫ�h����Ԫ��");
                    t.delete(sc.nextInt());
                }else if(choose1==3){
                    System.out.println("Ոݔ��Ҫ���ҵ�Ԫ��");
                    if(t.search(sc.nextInt())==true){
                        System.out.println("�Y�ϽY�����д�Ԫ��");
                    }else{
                        System.out.println("�Y�ϽY���Л]�д�Ԫ��");
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
