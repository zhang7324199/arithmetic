package zhang.unit7;

import java.util.Arrays;

public class Test {

	/**
	 * 快速排序
	 * @param args
	 */
	public static void main(String[] args) {
		int[] num = {2,4,-10,14,7,-9,8,-11,1};
		quick_sort(num,0,num.length-1);
		System.out.println(Arrays.toString(num));
//		System.out.println("master first commit");
	}
	
	public static void quick_sort(int[] num, int p,int r){
		if(p<r){
			int q = partition(num,p,r);//每次取最右边的元素为基准
			quick_sort(num,p,q-1);
			quick_sort(num,q+1,r);
		}
	}
	private static int partition(int[] num, int left,int right){
		/**
		 * 1.以最右边r为基准
		 * 2.遍历0->r-1, 标志位i记录的是小于r位置上数值的下标最大临界值（意味着<=i下标的值都会小于r）
		 */
		int i = left-1,x=num[right];
		if(left<right && left>=0){
			for(int j=left; j<right; j++){
				if(x>num[j]){
					i++;
					int temp = num[j];
					num[j] = num[i];
					num[i] = temp;
				}
			}
			int temp = num[right];
			num[right] = num[i+1];
			num[i+1] = temp;
		}
		return i+1;
	}

}
