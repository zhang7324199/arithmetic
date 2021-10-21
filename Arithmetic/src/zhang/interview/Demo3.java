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
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度(可以不连续)
     * @return
     */
    public static void f2() {
        int[] nums = {19,9,2,5,3,7,101,18,19};
        int len = nums.length;
        if (len < 2) {
            System.out.println("结果："+len);
        }
        int[] dp = new int[len];
        // 自己一定是一个子序列
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            // 看以前的，比它小的，说明可以接在后面形成一个更长的子序列
            // int curMax = Integer.MIN_VALUE; 不能这样写，万一前面没有比自己小的，
            // 这个值就得不到更新
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
        System.out.println("结果："+res);
    }

    public static void f3() {
        int[] nums = {19,9,2,5,3,7,101,18,19};
        int len = nums.length;
        if (len < 2) {
            System.out.println("结果："+len);
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
        System.out.println("结果："+res);
    }

    /**
     * 假设有 100 层的高楼，给你两个完全一样的鸡蛋。
     * 请你设计一种方法，能够试出来从第几层楼开始往下扔鸡蛋，鸡蛋会碎。
     * 请问最坏情况下，至少需要试验多少次才能知道从第几层楼开始往下扔鸡蛋，鸡蛋会碎？
     */
    public static void f1(){
        int n = 100; //100层
        int num = 2; //2个鸡蛋
        int[][] ba = new int[n+1][num+1];//记n层num鸡蛋的问题对应的最坏情况最优解为ba(n,s);
        /**
         * (0) ba(m,2)>=ba(n,2) 如果m>n,trivial.
         * (1) ba(n,1)=n
         * 当你只剩下一个球，为了能够检验出临界点，你只能逐层检验，最坏情况下所需的检验次数为n；
         * (2)ba(1,2)=1,ba(2,2)=2
         * (3)iterative: 假设你从k层扔球，有两种情形：
         *
         * 球破。那么临界层必然在1层到k-1层之间，剩下一球，由(1)得出，最坏情况最优所需的步数为: 1 + ba(k-1,1)=k;
         * 球不破。问题变成n-k层两球的问题Q(n-k,2), 所以最坏情况最优所需步数是：1+ba(n-k,2);
         * 综合1，2，最坏情况所需步数：ba(n,2) = min{max{k，1+ba(n-k,2)},k in (2,n-1)}
         *
         * 最终结果Q=b(100,2)
         */
        for(int i=1;i<=n;i++){//对应条件(1)
            ba[i][1] = i;
        }
        ba[1][2] = 1;ba[2][2] = 2;//对应条件(2)

        //求出所有ba(x,2) x(2,100)
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
        System.out.println("结果："+ba[100][2]);
    }

    /**
     * 01背包
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
            //查看第num个物品放没放，放了加入列表
            if(m[num][weight]!=m[num-1][weight]){ //放了
                list.add(v[num]);
                ind.add(num);
                weight-=w[num];
            }
            num--;
        }
        Collections.reverse(ind);
        Collections.reverse(list);
        System.out.println("把"+(v.length-1)+"件物品放入容量为"+totalWeight+"的背包。");
        System.out.println("最大价值:"+m[v.length-1][totalWeight]);
        System.out.println("放入的物品序号:"+ Arrays.toString(ind.toArray()));
        System.out.println("放入的物品价值:"+ Arrays.toString(list.toArray()));
    }
}
