package zhang.unit9;

import java.util.Arrays;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//[-11, -9, 2, 2, 3, 4, 7, 8, 10, 14]
		int[] num = {2,4,10,14,7,-9,3,2,8,-11};
		for(int i=1; i<=num.length; i++){
			System.out.print(randomSelect(num,0,num.length-1,i)+" ");
		}
	}

	//从一个数组中选择第k小的数
	public static int randomSelect(int[] num,int start, int end, int k){
		if(start>=end){
			return num[start];
		}
		int p = randomSelect(num,start,end);
		if(p+1==k){
			return num[p];
		}else if(p+1>k){
			return randomSelect(num,start,p-1,k);
		}else{
			return randomSelect(num,p+1,end,k);
		}
	}
	public static int randomSelect(int[] num,int start, int end){
		int p = num[end],m=start,j=start-1;
		for(int i=m; i<end; i++){
			if(p>=num[i]){
				j++;                   
				int temp = num[i];
				num[i] = num[j];
				num[j] = temp;
			}
		}
		int temp = num[end];
		num[end] = num[j+1];
		num[j+1] = temp;
		return j+1;
	}
}
