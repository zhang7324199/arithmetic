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
     * �����ڵ���Ϊ n ��������ǰ����������������������ؽ����ö���������������ͷ��㡣
     * ��������ǰ���������{1,2,4,7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}�����ؽ�������ͼ��ʾ��
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
        dgui(1, pS, 0, genIndex-1, pre, vin, treeNode,true); //�������
        dgui(pS+1, vin.length-1, genIndex+1, vin.length-1, pre, vin, treeNode,false); //�ұ�����
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
        dgui(perStart+1, pS, midStart, genIndex-1, pre, vin, tn,true); //�������
        dgui(pS+1, perEnd, genIndex+1, midEnd, pre, vin, tn,false); //�ұ�����
    }


    /**
     * ������������ˮƽ���ϰ�������˳��д�� nums1 �� nums2 �е�������
     *
     * ���ڣ����Ի���һЩ������������ nums1[i]?�� nums2[j]?��ֱ�ߣ���Щֱ����Ҫͬʱ�������㣺
     *
     * ?nums1[i] == nums2[j]
     * �һ��Ƶ�ֱ�߲����κ��������ߣ���ˮƽ�ߣ��ཻ��
     * ��ע�⣬���߼�ʹ�ڶ˵�Ҳ�����ཻ��ÿ������ֻ������һ�����ߡ�
     *
     * �����ַ������������������ؿ��Ի��Ƶ������������
     * ���ӣ�
     * ���룺nums1 = [1,4,2], nums2 = [1,2,4]
     * �����2
     *
     * ���룺nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
     * �����3
     *
     */
    public static void f2() {
        int[] nums1={1,4,2},nums2={1,2,4};
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1]; //dp[m][n]��ʾ ����0:m������0:n��������г���
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
     * ����һ�����������ź���ĸ��ɵ��ַ��� s ��ɾ����С��������Ч���ţ�ʹ��������ַ�����Ч��
     * �������п��ܵĽ�����𰸿��԰� ����˳�� ���ء�
     * ��ʾ��
     * 1 <= s.length <= 25
     * s ��СдӢ����ĸ�Լ����� '(' �� ')' ���
     * s �����ຬ 20 ������
     *
     * �����������(BFS)�������������(DFS)
     */
    private static int len;
    private static char[] charArray;
    private static Set<String> validExpressions = new HashSet<>();
    public static void removeInvalidParenthesesDFS() {
        String s="(a)())()";
        len = s.length();
        charArray = s.toCharArray();

        // �� 1 ��������һ�Σ�����������������
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < len; i++) {
            if (charArray[i] == '(') {
                leftRemove++;
            } else if (charArray[i] == ')') {
                // ���������ŵ�ʱ����Ҫ�����Ѿ����ڵ���������������
                if (leftRemove == 0) {
                    rightRemove++;
                }
                if (leftRemove > 0) {
                    // �ؼ���һ�������ų��ֿ��Ե���֮ǰ������������
                    leftRemove--;
                }
            }
        }

        // �� 2 ���������㷨������ÿһ�ֿ��ܵ�ɾ������
        StringBuilder path = new StringBuilder();
        dfs(0, 0, 0, leftRemove, rightRemove, path);
        System.out.println(validExpressions);
    }
    /**
     * @param index       ��ǰ���������±�
     * @param leftCount   �Ѿ��������������ŵĸ���
     * @param rightCount  �Ѿ��������������ŵĸ���
     * @param leftRemove  ����Ӧ��ɾ���������ŵĸ���
     * @param rightRemove ����Ӧ��ɾ���������ŵĸ���
     * @param path        һ�����ܵĽ��
     */
    private static void dfs(int index, int leftCount, int rightCount, int leftRemove, int rightRemove, StringBuilder path) {
        if (index == len) {
            if (leftRemove == 0 && rightRemove == 0) {
                validExpressions.add(path.toString());
            }
            return;
        }

        char character = charArray[index];
        // ���ܵĲ��� 1��ɾ����ǰ���������ַ�
        if (character == '(' && leftRemove > 0) {
            // ���� leftRemove > 0�����ҵ�ǰ�������������ţ���˿��Գ���ɾ����ǰ������������
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        if (character == ')' && rightRemove > 0) {
            // ���� rightRemove > 0�����ҵ�ǰ�������������ţ���˿��Գ���ɾ����ǰ������������
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }

        // ���ܵĲ��� 2��������ǰ���������ַ�
        path.append(character);
        if (character != '(' && character != ')') {
            // ����������ţ�����������ȱ���
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        } else if (character == '(') {
            // ����������
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        } else if (rightCount < leftCount) {
            // ����������
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        path.deleteCharAt(path.length() - 1);
    }

    public static void removeInvalidParenthesesBFS() {
        String s="(a)())()";
        List<String> res = new ArrayList<>();

        // ������ȱ�����Ҫ�Ķ��кͷ�ֹ�ظ������Ĺ�ϣ�� visited
        Set<String> visited = new HashSet<>();
        visited.add(s);
        Queue<String> queue = new LinkedList<>();
        queue.add(s);

        // �ҵ�Ŀ��ֵ�Ժ��Ƴ�ѭ��
        boolean found = false;
        while (!queue.isEmpty()) {
            // ���Ž�һ����ͬһ��
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String front = queue.poll();
                if (isValid(front)) {
                    res.add(front);
                    found = true;
                }

                int currentWordLen = front.length();
                char[] charArray = front.toCharArray();
                for (int j = 0; j < currentWordLen; j++) {//������ȥ����j���ַ������Ƿ����Ҫ��
                    if (front.charAt(j) != '(' && front.charAt(j) != ')') {
                        continue;
                    }

                    // ע�� new String() ������ API���� 1 ���������ַ����飬�� 2 ���������ַ��������ʼ�±꣬�� 3 �������ǽ�ȡ���ַ��ĳ���
                    String next = new String(charArray, 0, j) + new String(charArray, j + 1, currentWordLen - j - 1);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }

            // ��һ���ҵ��Ժ��˳����ѭ�������ؽ��
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
     * ����2�������� a(a�ĳ���>500),b(b�ĳ���>200)��
     * ����ʹ�����еķ�����BigInteger,�Լ�ʵ���㷨����a��b�ĳ˻�
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
            //������
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
            //������
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
