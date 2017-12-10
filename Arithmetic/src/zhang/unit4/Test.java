package zhang.unit4;

class Test {

	public static void main(String[] args) {
		int[] num = {12,-2,5,-6,1,-2,3,11,-5,2};
		int[] a = findMaxSubarray(num,0,num.length-1);
		System.out.println(a[0]+" "+a[1]+" "+a[2]);
		findMaxSubarray2(num);
	}
	
	//给一个数组 求它的最大子数组,子数组是连续的(各个数的和最大)
	//用分治思想用递归解出 时间复杂度为O(nlgn)
	public static int[] findMaxSubarray(int[] num,int l, int r){
		if(r>l){
			int mid = (l+r)/2;
			int[] l_max = findMaxSubarray(num,l,mid);   //左边最大
			int[] r_max = findMaxSubarray(num,mid+1,r); //右边最大
			int[] mid_max = findMidArray(num,l,mid,r);
			if(l_max[2]>=r_max[2] && l_max[2]>=mid_max[2]){
				return l_max;
			}else if(r_max[2]>=l_max[2] && r_max[2]>=mid_max[2]){
				return r_max;
			}else{
				return mid_max;
			}
		}else{
			int[] a = {l,r,num[l]};
			return a;
		}
	}
	public static int[] findMidArray(int[] num,int l, int mid, int r){
		int l_max=num[mid],r_max=num[mid+1],
			l_sum=num[mid],r_sum=num[mid+1],
			l_index=mid,r_index=mid+1;
		for(int i=mid-1; i>=l;i--){
			l_sum+=num[i];
			if(l_max<l_sum){
				l_max=l_sum;
				l_index=i;
			}
		}
		for(int j=mid+2; j<=r;j++){
			r_sum+=num[j];
			if(r_max<r_sum){
				r_max=r_sum;
				r_index=j;
			}
		}
		int[] a = {l_index,r_index,l_max+r_max};
		return a;
	}
	
	//利用动态规划可以将求最大子数组问题的时间复杂度降为O(n)
	public static void findMaxSubarray2(int[] num){
		//初始化数据结构
		class A{
			int left = 0;
			int right = 0;
			int sum = 0;
		}
		A[] a = new A[num.length];
		for(int i=0; i<a.length; i++) a[i] = new A();
		a[0].left=0;a[0].right=0;a[0].sum=num[0];
		
		//开始重左至右遍历
		for(int i=1; i<num.length; i++){
			if(a[i-1].sum<0){
				a[i].left=i;
				a[i].right=i;
				a[i].sum=num[i];
			}else{
				a[i].left=a[i-1].left;
				a[i].right=i;
				a[i].sum=a[i-1].sum+num[i];
			}
		}
		int max_index = 0;
		for(int i=1; i<num.length; i++){
			if(a[i].sum>a[max_index].sum){
				max_index=i;
			}
		}
		System.out.println(a[max_index].left+" "+a[max_index].right+" "+a[max_index].sum);
	}

}
