import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by wjf on 16-12-25.
 */
public class Main {

    public static void main(String[] args){
//        System.out.println("this is Main");
//        int[] a = {1};
//        int b =0;
////        int[] c=searchRange2(a,b);
//        int[] c = searchRange2(a,b);
//        System.out.println(c[0] + ":"+c[1]);

//        System.out.println(1/(2181583.0/677399*18/47236));
//        int[][] s = {{1,2,3}};
//        List<Integer> result = spiralOrder(s);
//            for(int li: result) {
//                System.out.print(li);
//            }

//        generateMatrix(3);
         int[][] m = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
         System.out.println(searchMatrix(m,3));
    }


    //34. Search for a Range
    // this is my version
    public static int[] searchRange(int[] nums, int target) {
        int[] a = new int[31];
        a[1]= 1;
        for(int i=2 ;i<31;i++) {
            a[i]  = a[i-1] *2;

        }
        int i =1;
        int temp = 0;
        while ((a[i] - 1 < nums.length) && nums[a[i] -1 ] < target) {
            i++;
        }
        while(i>0) {
            if ((a[i] + temp - 1 < nums.length) && nums[a[i] + temp -1 ] < target ) {
                temp += a[i];
            }
            i--;
        }
        int r = nums.length ;

        i=1;
        while ((r - a[i]  >= 0 ) && nums[r- a[i] ] > target) {
            i++;
        }
        while(i>0) {
            if ((r - a[i] - 1 >= 0) && nums[r - a[i] ] > target ) {
                r -= a[i];

            }
            i--;
        }

        int[] result = new int[2];
        result[0]= temp+1;
        result[1] = r;
        System.out.println(r+":" + temp+1);
        if(nums[r-1] == target && nums[temp] == target) {
            result[0] = temp;
            result[1] = r-1;
        } else  {
            result[0]= -1;
            result[1]= -1;
        }


        return result;
    }

    // 34. Search for a Range
    // this is a version from leetcode, very good
    public static int[] searchRange2(int[] nums, int target) {
        int[] result = new int[2];
        int i = 0;
        int j = nums.length -1;
        int middle ;
        while(i<j) {
            middle = (i + j) / 2;
            if(nums[middle] < target) i = middle +1;
            else j = middle;
        }
        if(nums[j] == target) result[0] = j;
        else {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        i = 0;
        j = nums.length -1;
        while(i<j) {
            middle = (i + j) /2 + 1;
            if(nums[middle] > target) j = middle - 1;
            else i = middle;
        }
        if(nums[i] == target) result[1] = i;
        return result;
    }

    //35. Search Insert Position
    public static int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length- 1;
        while(l <= r){
            int m = (l + r) /2;
            if(nums[m] == target) {
                return m;
            } else if(nums[m]> target) {
                r = m -1;
            } else {
                l = m + 1;
            }
        }
        System.out.println(l+":" + r);
        return l;



    }
    //36. Valid Sudoku
    public static boolean isValidSudoku(char[][] board) {
        boolean[] flag = new boolean[9];
        for(int i=0;i<flag.length;i++)flag[i] = false;
        for(int i= 0;i<9;i++) {
            for(int j=0;j<9;j++) {

                if(board[i][j] == '.')continue;
                if(flag[board[i][j] - '0' - 1] == true) return false;
                flag[board[i][j] - '0' -1] = true;
            }
            for(int j = 0;j<9;j++) {
                flag[j] = false;
            }
        }
        for(int i= 0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(board[j][i] == '.')continue;
                if(flag[board[j][i] - '0' - 1] == true) return false;
                flag[board[j][i] - '0' -1] = true;
            }
            for(int j = 0;j<9;j++) {
                flag[j] = false;
            }
        }
        for(int i= 0;i<9;i +=3) {
            for(int j=0;j<9;j += 3) {
                for(int k = i;k<i+3;k++) {
                    for(int l = j;l<j+3;l++) {
                        if(board[k][l] == '.')continue;
                        if(flag[board[k][l] - '0' - 1] == true) return false;
                        flag[board[k][l] - '0' -1] = true;
                    }
                }
                for(int k = 0;k<9;k++) {
                    flag[k] = false;
                }
            }

        }
        return true;
    }

    //35 version 2
    public static boolean isValidSudoku2(char[][] board) {
        for(int i =0 ;i<9;i++) {
            HashSet<Character> row = new HashSet<Character>();
            HashSet<Character> column = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for(int j = 0;j < 9;j++) {
                if(board[i][j] != '.' && !row.add(board[i][j])) return false;
                if(board[j][i] != '.' && !column.add(board[j][i])) return false;
                int rowBase= 3*(i/3) + j/3;
                int columnBase = 3*(i%3) + j%3;

                if(board[rowBase][columnBase] != '.' && !cube.add(board[rowBase][columnBase])) return false;
            }
        }
        return true;
    }

    //38. Count and Say
    public static String countAndSay(int n) {
        if(n == 0) return null;
        if(n == 1) return "1";
        StringBuffer temp = new StringBuffer("1");
        for(int i=2;i<=n;i++) {
            StringBuffer cur = new StringBuffer("");
            for(int j =0;j<temp.length();j++) {
                int count  = 1;
                while((j+1)< temp.length() && temp.charAt(j) == temp.charAt(j+1)){
                    j++;
                    count++;
                }
                cur.append(String.valueOf(count) + temp.charAt(j));
            }
            temp = cur;
        }
        return temp.toString();
    }
    //43. Multiply Strings
    public static String multiply(String num1, String num2) {
        int lena = num1.length();
        int lenb = num2.length();
        int[] a = new int[lena];
        int[] b = new int[lenb];

        for(int i = 0;i<lena;i++) a[lena - i -1] = num1.charAt(i) - '0';
        for(int i = 0;i<lenb;i++) b[lenb - i -1] = num2.charAt(i) - '0';
        int[] result = new int[lena + lenb];
        for(int i = 0 ;i< lena;i++) {
            int flag = 0;
            int j;
            for(j = 0;j< lenb;j++) {
                int temp = result[i+j] + a[i] *b[j] + flag ;
                result[i +j] = temp%10;
                flag = temp/10;
            }
            if(flag != 0) result[i+j] = flag;
        }
        StringBuilder re = new StringBuilder("");
        int i = lena + lenb -1;

        while(i>=1 && result[i] == 0) {
            i--;
        }
        for( ;i>= 0;i--) {
            re.append(String.valueOf(result[i]));
        }
        return  re.toString();
    }
    //49. Group Anagrams

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        for(int i = 0;i< strs.length;i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            StringBuilder s = new StringBuilder("");
            for(int k = 0;k<temp.length;k++) {
                s.append(temp[k]);
            }
            String key = s.toString();
            if(map.containsKey(key)){
                map.get(key).add(strs[i]);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(key,list);
            }
        }
        List<List<String>> result = new ArrayList<List<String>>();
        for(String key:map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }
    //53. Maximum Subarray
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int current = nums[0];
        for(int i= 1;i< nums.length ;i++) {
            if(current > 0){
                current += nums[i];
            } else {
                current = nums[i];
            }
            if(current > max) max = current;
        }
        return max;
    }
    //54. Spiral Matrix 这个题目　bug 非常多
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        int row = matrix.length;
        // 如果没有　row ==0  的　判断　将会有一个bug ,这里也很显然，就是在访问数组元素的时候，一定要确保有这个元素．
        if(row  == 0) return result;
        int col = matrix[0].length;
        int min =  row > col ? col:row;
        int num = (min + 1)/2;
        for(int i = 0;i < num;i++) {
            int base = i;
            for(int k = base ; k < col - base ; k++) {
                result.add(matrix[base][k]);
            }
            for(int k = base+1; k< row - base ; k++) {
                result.add(matrix[k][col - base -1]);
            }
            // 这个判断也很重要
            if(row - base - 1 <= base)continue;
            for(int k = col - base -2;k>= base;k-- ) {
                result.add(matrix[row - base -1][k]);
            }
            if(col - base - 1 <= base) continue;
            for(int k = row - base - 2;k> base;k--) {
                result.add(matrix[k][base]);
            }
        }
        return result;
    }

    // jump game　　非常好的　题目
    public static boolean canJump(int[] nums) {
        if(nums.length == 0) return true;
        int max = -1;
        for(int i = 1; i<= nums.length ;i++) {

            if(nums[i-1] + i > max) max = nums[i-1] + i;
            if(max < i) break;

        }
        if(max >= nums.length) return true;
        else return false;
    }
    //58. Length of Last Word
    public int lengthOfLastWord(String s) {
        if(s == null) return 0;
        int i = s.length() -1;
        int result = 0;
        while(i>=0 && s.charAt(i) == ' ') i--;
        while(i>= 0 && s.charAt(i) != ' ') {
            i--;
            result++;
        }
        return result;
    }
    // 59. Spiral Matrix II
    public static int[][] generateMatrix(int n) {
        int num = (n+1)/2;
        int temp = 1;
        int[][] result = new int[n][n];
        for(int i = 0;i< num;i++) {
            int base = i;
            for(int k = base ;k < n - base ;k++) {
                result[base][k] = temp;
                temp++;
            }
            for(int k = base + 1; k < n - base; k ++) {
                result[k][n - base - 1] = temp;
                temp++;
            }
            if( n - base - 1 <= base) continue;
            for(int k = n - base - 2; k >= base; k--) {
                result[n - base - 1][k] = temp;
                temp++;
            }
            for(int k = n - base - 2;k > base ;k--) {
                result[k][base] = temp;
                temp++;
            }
        }
        return result;
    }

    //66. Plus One
    public int[] plusOne(int[] digits) {
        int flag = 1;
        for(int i = digits.length - 1 ;i>=0;i--) {
            int temp = (digits[i] + flag) /10;
            digits[i ] = (digits[i] + flag) % 10;
            flag = temp;

        }
        if(flag == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for(int i =1;i< digits.length + 1;i++) {
                result[i] = digits[i - 1];
            }
            return result;
        } else{
            return digits;
        }
    }
    //67. Add Binary
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder("");
        int i = a.length() -1;
        int j = b.length() -1;
        int flag = 0;
        while(i>=0 && j >= 0) {
            int tempa = a.charAt(i) - '0';
            int tempb = b.charAt(j) - '0';
            ans.append(String.valueOf((tempa + tempb + flag)%2));
            flag = (tempa + tempb + flag)/2;
            i--;
            j--;
        }
        while(i >= 0) {
            int tempa = a.charAt(i)  - '0';
            ans.append(String.valueOf((tempa + flag) %2));
            flag = (tempa + flag) /2;
            i--;
        }
        while(j >=0) {
            int tempb = b.charAt(j) - '0';
            ans.append(String.valueOf((tempb + flag)%2));
            flag = (tempb + flag) /2;
            j--;
        }
        if(flag != 0) {
            ans.append(String.valueOf(flag));
        }
        String re = ans.reverse().toString();
        return re;
    }
    //70. Climbing Stairs
    public int climbStairs(int n) {
        int a = 1;
        int b = 1;
        if(n == 1) return 1;
        int i = 2;
        int temp  = 0;
        while(i <= n ) {
            temp = a + b;
            a = b;
            b = temp;
            i++;
        }
        return temp;

    }
    //73. Set Matrix Zeroes
    public static void setZeroes(int[][] matrix) {
        // 记录 那些行和 列已经处理过了
        if(matrix.length == 0) return;
        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];

        for(int i = 0;i< matrix.length ;i++) {
            for(int j = 0;j< matrix[0].length ;j++) {
                if(matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        for(int i = 0;i< row.length ;i++) {
            if(row[i] == 1) {
                for(int j = 0;j< col.length ;j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for(int j = 0 ;j< col.length;j++) {
            if(col[j] == 1) {
                for(int i = 0;i< row.length ;i++) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

//    69. Sqrt(x)
    public static int mySqrt(int x) {
        long temp = 1;
        int count = 1;
        while(temp * temp<= x) {
            temp <<= 1;
            count++;
        }
        temp/=2;
        int ans = 0;
        for(int i =0;i < count -1;i++) {
            if((ans + temp) * (ans + temp) <= x) {
                ans += temp;
            }
            temp /=2;
        }
        return ans;
    }
    //74. Search a 2D Matrix
    public static boolean searchMatrix(int[][] matrix, int target) {

        if(matrix.length == 0) return false;
        if(matrix[0].length == 0) return false;
        int l = 0;
        int r = matrix.length -1;
        int m ;
        while(l <= r) {
            m = (l + r) /2;
            if(matrix[m][0] <  target) l = m + 1;
            else if(matrix[m][0] > target) r = m -1;
            else return true;
        }
        // 画图思考这里为什么是 row  = r ,其实 每次 跳出 二分查找 r 所在的位置都会 <=
        int row = r;
        if( row < 0) return false;
        l = 0;
        r = matrix[0].length -1;
        while( l<= r) {
            m = (l + r)/2;
            if(matrix[row][m] < target ) l = m +1;
            else if(matrix[row][m] > target) r = m -1;
            else return true;
        }
        return false;
    }
   // 75. Sort Colors
    public void sortColors(int[] nums) {
        int zeros = 0;
        int one = 0;
        for(int i=0;i<nums.length ;i++) {
            if(nums[i] == 0) zeros++;
            else if(nums[i] == 1) one++;
        }
        int i =0;
        while(i< zeros) {
            nums[i++] = 0;
        }
        while(i< zeros + one) {
            nums[i++] = 1;
        }
        while(i< nums.length) {
            nums[i++] = 2;
        }
    }
    //78. Subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<String> flag = new ArrayList<String>();
        int count=2;
        flag.add("0");
        flag.add("1");
        for(int i = 1;i<nums.length;i++) {
            for(int j = 0;j< count;j++) {
                String temp = flag.remove(0);
                flag.add(temp + "0");
                flag.add(temp + "1");
            }
            count <<=1;
        }
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if(nums.length == 0) {
            return ans;
        }
        for(int i = 0;i< count;i++) {
            String temp = flag.remove(0);
            List<Integer> cur = new ArrayList<Integer>();
            for(int j = 0 ; j < temp.length() ;j++) {
                if(temp.charAt(j) == '1') {
                    cur.add(nums[j]);
                }
            }
            ans.add(cur);
        }
        return ans;
    }
    //80. Remove Duplicates from Sorted Array II
    public static int removeDuplicates(int[] nums) {
        int len = 0;
        for(int i=0;i <nums.length ;i++) {
            if( (i+1) < nums.length && nums[i+1] == nums[i]) {
                nums[len++] = nums[i];
                nums[len++] = nums[++i];

                while((i+1) <nums.length && nums[i+1] == nums[i]) {
                    i++;
                }
                // 思考这里并不需要回退
//                i--;

            } else {
                nums[len++] = nums[i];
            }
        }
        return len;
    }

    //82. Remove Duplicates from Sorted List II
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode ans = null;
        ListNode cur = head;
        ListNode result = null;
        if(cur == null) return null;
        if(cur.next == null) return cur;
        int pre = head.next.val;
        while(cur != null) {
            if( (cur.next == null && cur.val != pre)||(cur.next != null && cur.val != pre && cur.val != cur.next.val)) {
                if(result == null){
                    ans = cur;
                    result = ans;
                }else {
                    ans.next = cur;
                    ans = ans.next;
                }
            }
            pre = cur.val;
            cur = cur.next;
        }
        // 这个地方保证 后面 不受其他元素影响 比如 1 2 2 如何没有这个操作,结果 就是 1 2 2
        // 同时也不能直接 赋值为 null ,要判断,如何由 上面的 if 退出一定不会是 null,如果是直接退出 比如 1 1 则为 null
        if(ans != null) ans.next=null;
        return result;
    }

    // 83. Remove Duplicates from Sorted List
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode ans = null;
        ListNode cur = head;
        while(cur!= null) {
            if(ans == null) {
                ans = cur;
                head = ans;
            } else {
                ans.next = cur;
                ans = ans.next;
            }
            while(cur.next != null && cur.val == cur.next.val) cur= cur.next;
            cur = cur.next;
        }
        if(ans != null)ans.next = null;
        return head;
    }

    //
//    public ListNode reverseBetween(ListNode head, int m, int n) {
//
//    }
    //18. 4Sum
    // 这个题目 没有使用 一个 额外的 Set 进行去重,错了10次
    // 注意事项: 见注释
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums==null) return null;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 两个变量分别 表示 i ,j 的状态
        boolean flag1 = false;
        boolean flag2 ;
        for(int i = 0;i<nums.length-3;i++) {
            // flag1 标示 i ,如果这个 i 已经成功找到过 一个组合,那么 i 就应该进行 while 避免重复
            if(flag1) while(nums[i] == nums[i-1])i++;
            // flag1 和 flag2 都设置成 false,这个很重要,尤其是 容易忽略的地方 flag2 = false,只要 i 前进了,j 就不能多次前进了,
            // 所以 flag2 = flase, 否则会漏掉某些情况.
            flag1 = false;
            flag2 = false;

            for(int j = i + 1; j< nums.length -2;j++) {
                if(flag2)while(nums[j] == nums[j-1])j++;
                flag2 = false;
                int l = j + 1;
                int r = nums.length - 1;
                while(l < r) {
                    int temp = nums[i] + nums[j] + nums[l] + nums[r];
                    if (temp > target){
//                        while(r -1 > l && nums[r] == nums[r -1]) r--;
                        r--;
                    }
                    else if (temp < target){
//                        while(l+1 < r && nums[l+1] == nums[l]) l++;
                        l++;
                    }
                    else {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        ans.add(list);
                        while(l+1 < r && nums[l+1] == nums[l]) l++;
                        while( r -1 > l && nums[r] == nums[r -1]) r--;
                        l++;
                        r--;
                        flag1 = true;
                        flag2 = true;
                    }
                }
            }

        }
        return ans;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
