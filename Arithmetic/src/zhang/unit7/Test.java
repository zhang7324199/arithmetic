package zhang.unit7;

import java.util.Arrays;

public class Test {

	/**
	 * ¿ìËÙÅÅĞò
	 * @param args
	 */
	public static void main(String[] args) {
		int[] num = {2,4,10,14,7,-9,3,2,8,-11};
		quick_sort(num,0,num.length-1);
		System.out.println(Arrays.toString(num));
	}
	
	public static void quick_sort(int[] num, int p,int r){
		if(p<r){
			int q = partition(num,p,r);
			quick_sort(num,p,q-1);
			quick_sort(num,q+1,r);
		}
	}
	public static int partition(int[] num, int p,int r){
		int i = p-1,x=num[r];
		if(p<r && p>=0){
			for(int j=p; j<r; j++){
				if(x>num[j]){
					i++;
					int temp = num[j];
					num[j] = num[i];
					num[i] = temp;
				}
			}
			int temp = num[r];
			num[r] = num[i+1];
			num[i+1] = temp;
		}
		return i+1;
	}

}
