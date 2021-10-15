package zhang.interview;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Demo1 {
    public static void main(String[] args) {
        int[] numArr = {56,23,-122,0,55};
        maopao(numArr);
        guibing(numArr);

    }

    //ð������
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
     * �鲢����
     * @param arr
     */
    public static void guibing(int []arr){
        int []temp = new int[arr.length];//������ǰ���Ƚ���һ�����ȵ���ԭ���鳤�ȵ���ʱ���飬����ݹ���Ƶ�����ٿռ�
        sort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//��߹鲢����ʹ��������������
            sort(arr,mid+1,right,temp);//�ұ߹鲢����ʹ��������������
            merge(arr,left,mid,right,temp);//����������������ϲ�����
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//������ָ��
        int j = mid+1;//������ָ��
        int t = 0;//��ʱ����ָ��
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(j<=right){//��������ʣ��Ԫ������temp��
            temp[t++] = arr[j++];
        }
        while(i<=mid){//�����ʣ��Ԫ������temp��
            temp[t++] = arr[i++];
        }
        t = 0;
        //��temp�е�Ԫ��ȫ��������ԭ������
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }


    /**
     * ��������
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
        // base�д�Ż�׼��
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // ˳�����Ҫ���ȴ��ұ߿�ʼ�����ң�ֱ���ҵ���baseֵС����
            while(array[j] >= base && i < j) {
                j--;
            }

            // �ٴ������ұ��ң�ֱ���ҵ���baseֵ�����
            while(array[i] <= base && i < j) {
                i++;
            }

            // �����ѭ��������ʾ�ҵ���λ�û���(i>=j)�ˣ������������������е�λ��
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // ����׼���ŵ��м��λ�ã���׼����λ��
        array[left] = array[i];
        array[i] = base;

        // �ݹ飬�������׼����������ִ�к�����ͬ���Ĳ���
        // i��������Ϊ������ȷ���õĻ�׼ֵ��λ�ã������ٴ���
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }


    /**
     * ��������
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
