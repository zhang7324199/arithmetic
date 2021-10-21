package zhang.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Demo3 {
    public static void main(String[] args) {
//        beibao();
//        f1();
        f2();
        f3();
    }


    /**
     * ����һ��������������飬�ҵ���������������еĳ���(���Բ�����)
     * @return
     */
    public static void f2() {
        int[] nums = {19,9,2,5,3,7,101,18,19};
        int len = nums.length;
        if (len < 2) {
            System.out.println("�����"+len);
        }
        int[] dp = new int[len];
        // �Լ�һ����һ��������
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            // ����ǰ�ģ�����С�ģ�˵�����Խ��ں����γ�һ��������������
            // int curMax = Integer.MIN_VALUE; ��������д����һǰ��û�б��Լ�С�ģ�
            // ���ֵ�͵ò�������
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println("�����"+res);
    }

    public static void f3() {
        int[] nums = {19,9,2,5,3,7,101,18,19};
        int len = nums.length;
        if (len < 2) {
            System.out.println("�����"+len);
        }
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = len-1; i >= 0; i--) {
            for (int j = i; j <len; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println("�����"+res);
    }

    /**
     * ������ 100 ��ĸ�¥������������ȫһ���ļ�����
     * �������һ�ַ������ܹ��Գ����ӵڼ���¥��ʼ�����Ӽ������������顣
     * ���������£�������Ҫ������ٴβ���֪���ӵڼ���¥��ʼ�����Ӽ������������飿
     */
    public static void f1(){
        int n = 100; //100��
        int num = 2; //2������
        int[][] ba = new int[n+1][num+1];//��n��num�����������Ӧ���������Ž�Ϊba(n,s);
        /**
         * (0) ba(m,2)>=ba(n,2) ���m>n,trivial.
         * (1) ba(n,1)=n
         * ����ֻʣ��һ����Ϊ���ܹ�������ٽ�㣬��ֻ�������飬����������ļ������Ϊn��
         * (2)ba(1,2)=1,ba(2,2)=2
         * (3)iterative: �������k���������������Σ�
         *
         * ���ơ���ô�ٽ���Ȼ��1�㵽k-1��֮�䣬ʣ��һ����(1)�ó���������������Ĳ���Ϊ: 1 + ba(k-1,1)=k;
         * ���ơ�������n-k�����������Q(n-k,2), ���������������貽���ǣ�1+ba(n-k,2);
         * �ۺ�1��2���������貽����ba(n,2) = min{max{k��1+ba(n-k,2)},k in (2,n-1)}
         *
         * ���ս��Q=b(100,2)
         */
        for(int i=1;i<=n;i++){//��Ӧ����(1)
            ba[i][1] = i;
        }
        ba[1][2] = 1;ba[2][2] = 2;//��Ӧ����(2)

        //�������ba(x,2) x(2,100)
        for(int x=3; x<=100; x++){
            int min = Integer.MAX_VALUE;
            for(int k=2;k<=x-1;k++){
                int v = Math.max(k,ba[x-k][2]+1);
                if(v<min){
                    min = v;
                }
            }
            ba[x][2] = min;
        }

        for(int i=3;i<=100;i++)
            System.out.print(ba[i][2]+"  ");
        System.out.println();
        System.out.println("�����"+ba[100][2]);
    }

    /**
     * 01����
     */
    public static void beibao(){
        int v[]={0,4,10,6,3,7,2};
        int w[]={0,4,6,2,2,5,1};
        int totalWeight = 12;

        int m[][]=new int[v.length][totalWeight+1];
        for(int i=1;i<v.length;i++){
            for(int j=0;j<=totalWeight;j++){
                if(j>=w[i]){
                    m[i][j]=Math.max(m[i-1][j],m[i-1][j-w[i]]+v[i]);
                }
                else{
                    m[i][j]=m[i-1][j];
                }
            }
        }


        for(int i=0;i<v.length;i++){
            for(int j=0;j<=totalWeight;j++){
                System.out.print(m[i][j]+"             ");
            }
            System.out.println();
        }

        int num = v.length-1;
        int weight = totalWeight;
        List<Integer> list = new ArrayList<>();
        List<Integer> ind = new ArrayList<>();
        while (num>0){
            //�鿴��num����Ʒ��û�ţ����˼����б�
            if(m[num][weight]!=m[num-1][weight]){ //����
                list.add(v[num]);
                ind.add(num);
                weight-=w[num];
            }
            num--;
        }
        Collections.reverse(ind);
        Collections.reverse(list);
        System.out.println("��"+(v.length-1)+"����Ʒ��������Ϊ"+totalWeight+"�ı�����");
        System.out.println("����ֵ:"+m[v.length-1][totalWeight]);
        System.out.println("�������Ʒ���:"+ Arrays.toString(ind.toArray()));
        System.out.println("�������Ʒ��ֵ:"+ Arrays.toString(list.toArray()));
    }
}
