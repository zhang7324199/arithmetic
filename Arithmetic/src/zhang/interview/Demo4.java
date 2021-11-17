package zhang.interview;

import java.math.BigInteger;
import java.util.*;

public class Demo4 {
    public static void main(String[] args) {
//        f1();
//        removeInvalidParenthesesDFS();
//        removeInvalidParenthesesBFS();
//        f2();
        TreeNode treeNode = reConstructBinaryTree();
        zxubianli(treeNode);
    }
    public static boolean f = false;
    public static void zxubianli(TreeNode treeNode){
        if(treeNode==null) return;
        zxubianli(treeNode.left);
        if(f){
            System.out.println(treeNode.val);
            f = false;
        }else{
            if(treeNode.val==3){
                f = true;
            }else{
                f = false;
            }
        }
        zxubianli(treeNode.right);
    }


    public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

    /**
     * 给定节点数为 n 二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建出如下图所示。
     * @return
     */
    public static TreeNode reConstructBinaryTree() {
        int [] pre = {1,2,4,7,3,5,6,8};
        int [] vin = {4,7,2,1,5,3,8,6};

        TreeNode treeNode = new TreeNode(pre[0]);
        int genIndex = -1;
        for(int i=0; i<vin.length; i++){
            if(pre[0]==vin[i]){
                genIndex = i;
                break;
            }
        }
        int pS = genIndex;//perStart+(genIndex-midStart)
        dgui(1, pS, 0, genIndex-1, pre, vin, treeNode,true); //左边子树
        dgui(pS+1, vin.length-1, genIndex+1, vin.length-1, pre, vin, treeNode,false); //右边子树
        return treeNode;
    }
    private static void dgui(int perStart,int perEnd,int midStart,int midEnd,int [] pre,int [] vin, TreeNode treeNode, boolean isLeft){
        if(perStart>perEnd || midStart>midEnd) return;
        int genIndex = -1;
        for(int i=midStart; i<=midEnd; i++){
            if(pre[perStart]==vin[i]){
                genIndex = i;
                break;
            }
        }
        TreeNode tn = new TreeNode(vin[genIndex]);
        if(isLeft){
            treeNode.left = tn;
        }else{
            treeNode.right = tn;
        }
        int pS = perStart+(genIndex-midStart);
        dgui(perStart+1, pS, midStart, genIndex-1, pre, vin, tn,true); //左边子树
        dgui(pS+1, perEnd, genIndex+1, midEnd, pre, vin, tn,false); //右边子树
    }


    /**
     * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
     *
     * 现在，可以绘制一些连接两个数字 nums1[i]?和 nums2[j]?的直线，这些直线需要同时满足满足：
     *
     * ?nums1[i] == nums2[j]
     * 且绘制的直线不与任何其他连线（非水平线）相交。
     * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
     *
     * 以这种方法绘制线条，并返回可以绘制的最大连线数。
     * 例子：
     * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
     * 输出：2
     *
     * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
     * 输出：3
     *
     */
    public static void f2() {
        int[] nums1={1,4,2},nums2={1,2,4};
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1]; //dp[m][n]表示 序列0:m和序列0:n的最长子序列长度
        for (int i = 1; i <= m; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= n; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[m][n]);
    }

    /**
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     * 提示：
     * 1 <= s.length <= 25
     * s 由小写英文字母以及括号 '(' 和 ')' 组成
     * s 中至多含 20 个括号
     *
     * 广度优先搜索(BFS)和深度优先搜索(DFS)
     */
    private static int len;
    private static char[] charArray;
    private static Set<String> validExpressions = new HashSet<>();
    public static void removeInvalidParenthesesDFS() {
        String s="(a)())()";
        len = s.length();
        charArray = s.toCharArray();

        // 第 1 步：遍历一次，计算多余的左右括号
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < len; i++) {
            if (charArray[i] == '(') {
                leftRemove++;
            } else if (charArray[i] == ')') {
                // 遇到右括号的时候，须要根据已经存在的左括号数量决定
                if (leftRemove == 0) {
                    rightRemove++;
                }
                if (leftRemove > 0) {
                    // 关键：一个右括号出现可以抵销之前遇到的左括号
                    leftRemove--;
                }
            }
        }

        // 第 2 步：回溯算法，尝试每一种可能的删除操作
        StringBuilder path = new StringBuilder();
        dfs(0, 0, 0, leftRemove, rightRemove, path);
        System.out.println(validExpressions);
    }
    /**
     * @param index       当前遍历到的下标
     * @param leftCount   已经遍历到的左括号的个数
     * @param rightCount  已经遍历到的右括号的个数
     * @param leftRemove  最少应该删除的左括号的个数
     * @param rightRemove 最少应该删除的右括号的个数
     * @param path        一个可能的结果
     */
    private static void dfs(int index, int leftCount, int rightCount, int leftRemove, int rightRemove, StringBuilder path) {
        if (index == len) {
            if (leftRemove == 0 && rightRemove == 0) {
                validExpressions.add(path.toString());
            }
            return;
        }

        char character = charArray[index];
        // 可能的操作 1：删除当前遍历到的字符
        if (character == '(' && leftRemove > 0) {
            // 由于 leftRemove > 0，并且当前遇到的是左括号，因此可以尝试删除当前遇到的左括号
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        if (character == ')' && rightRemove > 0) {
            // 由于 rightRemove > 0，并且当前遇到的是右括号，因此可以尝试删除当前遇到的右括号
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }

        // 可能的操作 2：保留当前遍历到的字符
        path.append(character);
        if (character != '(' && character != ')') {
            // 如果不是括号，继续深度优先遍历
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        } else if (character == '(') {
            // 考虑左括号
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        } else if (rightCount < leftCount) {
            // 考虑右括号
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        path.deleteCharAt(path.length() - 1);
    }

    public static void removeInvalidParenthesesBFS() {
        String s="(a)())()";
        List<String> res = new ArrayList<>();

        // 广度优先遍历须要的队列和防止重复遍历的哈希表 visited
        Set<String> visited = new HashSet<>();
        visited.add(s);
        Queue<String> queue = new LinkedList<>();
        queue.add(s);

        // 找到目标值以后推出循环
        boolean found = false;
        while (!queue.isEmpty()) {
            // 最优解一定在同一层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String front = queue.poll();
                if (isValid(front)) {
                    res.add(front);
                    found = true;
                }

                int currentWordLen = front.length();
                char[] charArray = front.toCharArray();
                for (int j = 0; j < currentWordLen; j++) {//遍历，去掉第j个字符看看是否符合要求
                    if (front.charAt(j) != '(' && front.charAt(j) != ')') {
                        continue;
                    }

                    // 注意 new String() 方法的 API，第 1 个参数是字符数组，第 2 个参数是字符数组的起始下标，第 3 个参数是截取的字符的长度
                    String next = new String(charArray, 0, j) + new String(charArray, j + 1, currentWordLen - j - 1);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }

            // 这一层找到以后，退出外层循环，返回结果
            if (found) {
                break;
            }
        }
        System.out.println(res);
    }
    public static boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        int count = 0;
        for (char c : charArray) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    /**
     * 给定2个长整数 a(a的长度>500),b(b的长度>200)，
     * 不能使用现有的方法如BigInteger,自己实现算法计算a和b的乘积
     */
    public static void f1(){
        String a = "9456",b="513966";
        int[][] arr = new int[b.length()][a.length()];
        for(int i=b.length()-1,row=0;i>=0;i--,row++){
            Integer numB = Integer.valueOf(b.charAt(i)+"");
            for(int j=a.length()-1;j>=0;j--){
                arr[row][j] = Integer.valueOf(a.charAt(j)+"")*numB;
            }
        }
        int[] result = new int[a.length()+b.length()];
        int jinwei = 0, index = result.length-1;
        for(int i=a.length()-1;i>=0;i--){
            int col=i,row=0;
            int num = arr[row][col];
            while(++row<b.length() && ++col<a.length()){
                num += arr[row][col];
            }
            num+=jinwei;
            //保存结果
            result[index--] = num%10;
            jinwei = num/10;
        }

        for(int i=1;i<b.length();i++){
            int row=i,col=0;
            int num = arr[row][col];
            while(++row<b.length() && ++col<a.length()){
                num += arr[row][col];
            }
            num+=jinwei;
            //保存结果
            result[index--] = num%10;
            jinwei = num/10;
        }
        StringBuilder rs = new StringBuilder();
        boolean flag = false;
        for(int i=0;i<result.length;i++){
            if(result[i]>0) flag = true;
            if (flag)
             rs.append(result[i]+"");
        }
        System.out.println(jinwei+rs.toString());
    }
}
