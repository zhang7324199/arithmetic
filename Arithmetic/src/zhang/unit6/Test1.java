package zhang.unit6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {

	public static void main(String[] args) {
		int[] num = {16,4,10,14,7,9,3,2,8,11};
//		int[] num = {1,2,3};
//		createbigheap(num,num.length);
		
//		heap_extract_max(num);
//		heapsort(num);
//		heap_increase_key(num,3,22);
//		System.out.println(Arrays.toString(num));
		heapsort(num);
		System.out.println(Arrays.toString(num));
	}
	
	//Youngʽ����ÿ�к�ÿ�ж�������Ķ�ά��֤
	//Youngʽ�����з��ز���ȥ����С����
	public static int young(){
		int[][] num = {{-6,1,2},{8,11,20},{13,14,21}};
		bigheap_young(num,0,0);
		for(int i=0; i<num.length; i++){
			for(int j=0; j<num[0].length; j++){
				System.out.print(num[i][j]+"  ");
			}
			System.out.println();
		}
		return num[0][0];
	}
	
	public static void bigheap_young(int[][] num, int i, int j){
		int i1 = i,j1 = j;
		if(i<num.length-1 && num[i+1][j] < num[i][j]){
			i1 = i+1;
		}
		if(j<num[0].length-1 && num[i][j+1]<num[i1][j1]){
			j1 = j+1;
			i1 = i;
		}
		if(i1 != i || j1 != j){
			int temp = num[i][j];
			num[i][j] = num[i1][j1];
			num[i1][j1] = temp;
			bigheap_young(num,i1,j1);
		}
		
	}
	
	
	//���ȶ���
	//��Ԫ��key�ӵ��±�i�����ϲ�����������num[i]<=key
	public static void heap_increase_key(int[] num,int i,int key){
		if(i>=0 && num[i]>key){
			System.out.print("�����Ԫ�ع�С�����±곬�����鷶Χ...");
			return;
		}
		while(i>0 && num[(i-1)/2]<key){
			num[i] = num[(i-1)/2];
			i = (i-1)/2;
		}
		num[i] = key;
	}
	
	//���ȶ���
	//�������Ԫ��
	public static int heap_maximun(int[] num){
		return num[0];
	}
	
	//���ȶ���
    //ȥ�����ҷ������Ԫ��
	public static int heap_extract_max(int[] num){
		int max = num[0];
		num[0] = num[num.length-1];
		bigheap(num,0,num.length-1);
		return max;
	}
	
	//������
	//�ǽ��������ѻ����Ͻ��е�����
	//�㷨����(���ڵ���Զ�����ģ����Բ�ȡ�𲽵ݼ������ó��ݼ�����)
	public static void heapsort(int[] num){
		int index = num.length-1;
		createbigheap(num,num.length);  //��������
		while(index>0){
			int temp = num[0];
			num[0] = num[index];
			num[index] = temp;
			index--;
			bigheap(num,0,index+1);
		}
	}

	//��������   ���Եĺ����Ǹ��ڵ����2���ӽڵ��һ��������
	//�и�����n��Ԫ�ض� Ҷ�ڵ���±�Ϊn/2+1....n
	//ע�������±��Ǵ�0��ʼ��
	//�¼����Ӷ�ΪO(n)
	public static void createbigheap(int[] num, int heapSize){
		for(int i = heapSize/2; i>=0;i--){
			bigheap(num,i,heapSize);
		}
	}
	
	//�¼����Ӷ�Ϊlgn
	public static void bigheap(int[] num, int i, int heapSize){
		int l = 0,r = 0,lagest=i;
		do{
			i = lagest;
		    l = (i+1)*2-1;
		    r = (i+1)*2;
			if(l<= heapSize -1 && num[i]<num[l])
				lagest = l;
			else
				lagest = i;
			if(r<= heapSize -1 && num[lagest]<num[r])
				lagest = r;
			if(lagest != i){
				int temp = num[i];
				num[i] = num[lagest];
				num[lagest] = temp;
			}
		}while(lagest != i);
	}
}
