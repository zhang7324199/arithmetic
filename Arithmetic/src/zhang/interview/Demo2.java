package zhang.interview;

public class Demo2 {
    public static void main(String[] args) {
        f3();
    }

    /**
     * 给你一个长度为 n 的数组，其中只有一个数字出现了奇数次，
     * 其他均出现偶数次，问如何使用优秀的时空复杂度快速找到这个数字
     */
    public static void f1(){
        int[] numArr = {55,-23,-122,-23,55};
        int num=numArr[0];
        //二进制的异或(0/1) 一系列0和1数组（其中一个为偶数个一个为奇数个）异或的累加结果为是奇数个的那一个数
        for(int i=1;i<numArr.length;i++){
                num ^= numArr[i];
        }
        System.out.println(num);
    }

    /**
     * 给你一个长度为 n 的数组，其中有个元素占数组的一半以上，找出该元素
     *
     * 摩尔投票法：
     * 核心就是对拼消耗。
     * 玩一个诸侯争霸的游戏，假设你方人口超过总人口一半以上，并且能保证每个人口出去干仗都能一对一同归于尽。最后还有人活下来的国家就是胜利。
     * 那就大混战呗，最差所有人都联合起来对付你（对应你每次选择作为计数器的数都是众数），或者其他国家也会相互攻击（会选择其他数作为计数器的数），但是只要你们不要内斗，最后肯定你赢。
     * 最后能剩下的必定是自己人。
     */
    public static void f3(){
        int[] numArr = {-23,55,-23,-122,-23,55,-23,7, -23};
        int res=0,cnt=0;
        for(int i=0;i<numArr.length;i++){
            if(cnt==0) {
                res=numArr[i];
                cnt++;
            }
            else{
                if(res==numArr[i]){
                    cnt++;
                }else {
                    cnt--;
                }
            }
        }
        System.out.println(res);
    }
}
