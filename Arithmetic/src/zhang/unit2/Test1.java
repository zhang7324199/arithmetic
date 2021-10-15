package zhang.unit2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Test1 {

	public static void main(String[] args) throws IOException {
//		int[] num = {5,2,3,11,-4,1};
//		int n = nishuduei(num,0,num.length-1);
//		System.out.println(n);

		insertSort();
	}
	
	//插入排序
	//大概就是把数组右边部分的插入数组左边部分排好序的数组中
	public static void insertSort(){
		int[] num = {5,2,3,11,-4,1};
		for(int i=1; i<num.length; i++){
			int j=i-1;
			int key = num[i];
			while(j>=0 && num[j]<key){
				num[j+1] = num[j];
				j = j-1;
			}
			num[j+1] = key;
		}
		System.out.println(Arrays.toString(num));
	}
	
	//输出数组中多少个逆数对
	public static int nishuduei(int[] num,int p,int r){
		int q = (r+p)/2,count = 0;
		if(r>p){
			count += nishuduei(num,p,q);
			count += nishuduei(num,q+1,r);
			count += merg(num,p,q,r);
		}
		return count;
	}
	public static int merg(int[] num,int p,int q, int r){
		int n1 = q-p+1,n2 = r-q, count = 0;;
		int[] L = new int[n1],R = new int[n2];
		for(int i=0; i<n1; i++) L[i] = num[p+i];
		for(int j=0; j<n2; j++) R[j] = num[q+j+1];
		for(int i=0,j=0,k=p;k<=r; k++){
			if(i==n1){
				num[k] = R[j++];
			}else if(j==n2){
				num[k] = L[i++];
			}else if(L[i]<=R[j]){
				num[k] = L[i++];
			}else{
				num[k] = R[j++];
				count += n1-i;//数出左边数组比右边数组R[j]大的个数
			}
		}
		return count;
	}

}
