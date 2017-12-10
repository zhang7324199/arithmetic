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
	
	//Young式矩阵，每行和每列都是升序的二维举证
	//Young式矩阵中返回并且去除最小的数
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
	
	
	//优先队列
	//把元素key加到下标i数组上并且满足条件num[i]<=key
	public static void heap_increase_key(int[] num,int i,int key){
		if(i>=0 && num[i]>key){
			System.out.print("插入的元素过小或者下标超出数组范围...");
			return;
		}
		while(i>0 && num[(i-1)/2]<key){
			num[i] = num[(i-1)/2];
			i = (i-1)/2;
		}
		num[i] = key;
	}
	
	//优先队列
	//返回最大元素
	public static int heap_maximun(int[] num){
		return num[0];
	}
	
	//优先队列
    //去掉并且返回最大元素
	public static int heap_extract_max(int[] num){
		int max = num[0];
		num[0] = num[num.length-1];
		bigheap(num,0,num.length-1);
		return max;
	}
	
	//堆排序
	//是建立在最大堆基础上进行的排序
	//算法如下(根节点永远是最大的，所以采取逐步递减方法得出递减序列)
	public static void heapsort(int[] num){
		int index = num.length-1;
		createbigheap(num,num.length);  //构建最大堆
		while(index>0){
			int temp = num[0];
			num[0] = num[index];
			num[index] = temp;
			index--;
			bigheap(num,0,index+1);
		}
	}

	//构造最大堆   最大对的含义是父节点大于2个子节点的一个二叉树
	//有个结论n个元素堆 叶节点的下表为n/2+1....n
	//注意数组下标是从0开始的
	//事件复杂度为O(n)
	public static void createbigheap(int[] num, int heapSize){
		for(int i = heapSize/2; i>=0;i--){
			bigheap(num,i,heapSize);
		}
	}
	
	//事件复杂度为lgn
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
