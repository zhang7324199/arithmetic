package zhang.unit8;

import java.util.Arrays;

public class Test {

	/**
	 *����ʱ������ 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] A = {3,2,1,2,7,6,9};
//		countSort();
//		radixSort();
//		Linear_in_Place(A,10);
	}
	
	
	//��������
	//�������Ҫ��0-k֮�� 
	//���������и���Ҫ�������ȶ���(2����ͬ���������к��������ʽ��ͬ˳��)
	public static void countSort(){
		int[] A = {1,333,334,3124,10,4,6,21,6},B=new int[A.length],C = new int[3125];
		for(int i=0; i<A.length; i++) C[A[i]] = C[A[i]]+1;
		for(int j=1; j<C.length; j++){
			C[j] = C[j]+C[j-1];
		}
		for(int p=A.length-1;p>=0; p--){
			B[C[A[p]]-1] = A[p];
			C[A[p]]--;  //��ֹ��ͬ������������ 
		}
		System.out.println(Arrays.toString(B));
	}
	
	
	//�������� (�ײ������Ҫ�ȶ�)
	public static void radixSort(){
		int[] A = {1,333,334,3124,10,4,6,21,6},B,C;
		int d = 4,i=0; //���λ��4λ  
		//i����ӵ�һλ��ʼ����
		while(true){
			i++;
			C = new int[10];  //��ʼ��C,��¼���е�����
			B = new int[A.length];
			for(int a=0; a<A.length; a++){
				int index = getNumInt(A[a],i);
				C[index] = C[index]+1;
			}
			if(C[0]==A.length) break;
			for(int b=1; b<C.length; b++){
				C[b] = C[b]+C[b-1];
			}
			for(int c=A.length-1; c>=0; c--){
				int index = getNumInt(A[c],i);
				B[C[index]-1] = A[c];
				C[index]--;
			}
			A = B;
		}
		System.out.println(Arrays.toString(A));
	}
	public static int getNumInt(int num, int position){
		if(position>1){
			int total=1;
			for(int i=1;i<position; i++){
				total*=10;
			}
			return num/total%10;
		}else{
			return num%10;
		}
	}

	
	//�޸ļ�������ʹ��ԭַ����
	//ԭַ���壺�������㷨�У�������������н��г�����Ԫ����
	//Ҫ����������д洢������֮�⣬��������㷨��ԭַ�ģ��鲢������ԭַ����
	public static void Linear_in_Place(int[] A, int k){
        int[] C = new int[k];
        int[] P = new int[k];
        for (int i = 0; i < A.length; i++){
            C[A[i]] = C[A[i]] + 1;
        }
        for (int i = 1; i < C.length; i++){
            C[i] = C[i] + C[i - 1];
        }
        System.arraycopy(C, 0, P, 0, k);
        int j = 0;
        while (j < A.length){
          boolean placed;
          if (A[j] == 0){
        	  placed = j < P[A[j]];
          }else{
        	  placed = P[A[j] - 1] < j+1 && P[A[j]] >= j;
          }
          if (placed){
              j++;
          }else{
              int c = C[A[j]] = C[A[j]] - 1;
              int temp = A[j];
              A[j] = A[c];
              A[c] = temp;
          }
        }
        System.out.println(Arrays.toString(A));
    }
}
