import java.util.Random;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }
        /**
         * 理论上速度快排>归并>堆排>冒泡
         * 实际上归并更加稳定，数据量多时优先使用归并。
         *速度比较十万条数据bubble耗时：16000左右 insert耗时1000左右 select耗时4000左右 shell耗时20~30(百万条数据耗时200~300)
         * merge耗时10~20左右(百万条数据耗时140左右) 快速排序耗时10~20(百万条数据耗时170左右) 堆排序耗时 10000左右
         */
        public void start() {
            int[] date = new int[100000];
            for(int i=0;i<100000;i++){
                Random random = new Random();
                date[i]=random.nextInt(100000);
            }
            long start = System.currentTimeMillis();
            heapSort(date);
            long end = System.currentTimeMillis();
            System.out.println(end-start);

            for(int i:date){
                System.out.print(" "+i);
            }

        }
        /**
         * 冒泡排序：大的放在后面
         * 时间复杂度O(n^2)，时间复杂度最好O(n) 时间复杂度最坏O(n^2) 空间复杂度O(1) 稳定
         */
        public void bubbleSort(int[] date){
            for(int i=date.length;i>0;i--){
                for(int j=0;j<i-1;j++){
                    if(date[j]>date[j+1]){
                        date[j]=date[j]^date[j+1];
                        date[j+1]=date[j]^date[j+1];
                        date[j]=date[j]^date[j+1];
                    }
                }
            }
        }

        /**
         * 选择排序：选择小的放在前面
         * 时间复杂度O(n^2)，时间复杂度最好O(n^2) 时间复杂度最坏O(n^2) 空间复杂度O(1) 不稳定
         * @param date
         */
        public void selectSort(int[] date){
            for(int i=0;i<date.length;i++){
                int min = i;
                for(int j=i+1;j<date.length;j++){
                    if(date[min]>date[j]){
                        min = j ;
                    }
                }
                int swap = 0;
                swap = date[min];
                date[min] = date[i];
                date[i] = swap;
            }
        }

        /**
         * 插入排序 从1所在的数开始为x，x和前面的数比较，将大的数后移一位；如果前面的数小于后面的数，把前面的这个数放x
         * 时间复杂度O(n^2)，时间复杂度最好O(n) 时间复杂度最坏O(n^2) 空间复杂度O(1) 稳定
         * @param date
         */
        public void insertSort(int[] date){
            for(int i=1;i<date.length;i++){
                int min = date[i];
                int j = i;
                while(j>0&&date[j-1]>min){
                    date[j]=date[j-1];
                    j--;
                }
                date[j]=min;
            }
        }

        /**
         * shell 排序从n/2开始，计算增量点，每轮循环增量减小为1/2
         * 时间复杂度O(n^1.3)，时间复杂度最好O(n) 时间复杂度最坏O(n^2) 空间复杂度O(1) 不稳定
         * @param date
         */
        public void shellSort(int[] date){
            for(int increment = date.length/2; increment>0;increment=increment/2){
                for(int i=increment;i<date.length;i++){
                    int min = date[i];
                    int j = i;
                    while(j>=increment&&date[j-increment]>min){
                        date[j]=date[j-increment];
                        j-=increment;
                    }
                    date[j]=min;
                }
            }
        }

        /**
         * 归并排序：通过递归的方法，将数组分成小的部分排序，然后合并
         * 时间复杂度O(nlogn)，时间复杂度最好O(nlogn) 时间复杂度最坏O(nlogn) 空间复杂度O(n) 稳定
         * @param date
         */
        public void mergeSort(int[] date){
            int[] temp = new int[date.length];
            sort(date,0,date.length-1,temp);
        }
        public void sort(int[] date,int start,int end,int[] temps){
            if(start<end){
                int medium = (start+end)/2;
                sort(date,start,medium,temps);
                sort(date,medium+1,end,temps);
                merge(date,start,end,temps,medium);
            }
        }
        public void merge(int[] date,int start ,int end,int[] temps,int medium){
            int m =0;
            int i = start;
            int j = medium+1;
            while(i<=medium&&j<=end){
                if(date[i]<=date[j]){
                    temps[m++]=date[i++];
                }else{
                    temps[m++]=date[j++];
                }
            }
            while(j<=end){
                temps[m++]=date[j++];
            }
            while(i<=medium){
                temps[m++]=date[i++];
            }
            while(--m>=0){
                date[end--]=temps[m];
            }
        }

        /**
         * 快速排序：选择第一个元素作为基准，从左边开始遍历找到大于基准的元素，从右边开始遍历找到小于基准的元素，两者交换
         * 时间复杂度O(nlogn)，时间复杂度最好O(nlogn) 时间复杂度最坏O(n^2) 空间复杂度O(nlogn) 不稳定
         * @param date
         */
        public void quickSort(int[] date){
            qs(date,0,date.length-1);
        }
        public void qs(int[] date,int start,int end){
            if(start<end){
                int j=quick(date,start,end);
                qs(date,start,j-1);
                qs(date,j,end);
            }
        }
        public int quick(int[] date,int start ,int end){
            int base = date[start]; //第一个元素作为基准
            int i = start+1; //选择基准下一个元素开始遍历
            int j = end ; //右边开始遍历起点
            while(i<j){
                if(date[i]>base){
                    if(date[j]<base){
                        swap(date,i++,j--);
                    }else{
                        j--;
                    }
                }else{
                    i++;
                }
            }
            if(i>=j&&date[j]<base)
                swap(date,start,j);
            return j;
        }
        public void swap(int[] date,int x ,int y){
            int temp = date[x];
            date[x] = date[y];
            date[y] = temp ;
        }

        /**
         * 堆排序 使用大根堆每一次循环将大值向上移动，循环完毕，替换首尾元素，
         * 时间复杂度O(nlogn)，时间复杂度最好O(nlogn) 时间复杂度最坏O(nlogn) 空间复杂度O(nlogn) 不稳定
         * @param date
         */
        public void heapSort(int[] date){
            for(int i= date.length;i>1;i--){
                hs(date,i);
                swap(date,0,i-1);
            }
        }
        public void hs(int[] date,int length){

            for(int i = length/2-1;i>=0;i-=1){
                int child = 2*i+1;
                //有右节点
                if (child+1<length&&date[child]<date[child+1]){
                    child++;
                }
                if(date[i]<date[child]){
                    swap(date,child,i);
                }
            }

        }

}
