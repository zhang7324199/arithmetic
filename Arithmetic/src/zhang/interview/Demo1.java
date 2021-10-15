package zhang.interview;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Demo1 {
    public static void main(String[] args) {
        int[] numArr = {56,23,-122,0,55};
        maopao(numArr);
        guibing(numArr);

    }

    //冒泡排序
    public static void maopao(int []numArr){
        for(int i=0;i<numArr.length-1;i++){
            for(int j=0; j<numArr.length-i-1;j++){
                if(numArr[j]>numArr[j+1]){
                    int temp = numArr[j];
                    numArr[j] = numArr[j+1];
                    numArr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(numArr));
    }

    /**
     * 归并排序
     * @param arr
     */
    public static void guibing(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }


    /**
     * 快速排序
     * @param array
     */
    public static void quickSort(int[] array) {
        int len;
        if(array == null
                || (len = array.length) == 0
                || len == 1) {
            return ;
        }
        sort(array, 0, len - 1);
    }
    private static void sort(int[] array, int left, int right) {
        if(left >= right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while(array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }


    /**
     * 插入排序
     */
    public static int[] charu(int[] ins){

        for(int i=1; i<ins.length; i++){
            for(int j=i; j>0; j--){
                if(ins[j]<ins[j-1]){
                    int temp = ins[j-1];
                    ins[j-1] = ins[j];
                    ins[j] = temp;
                }
            }
        }
        return ins;
    }
}
