package com.algo;
import com.google.common.collect.Lists;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyUtil {
    private MyUtil() {
        throw new AssertionError();
    }

    public static void fileCopy(String source, String target) throws IOException {
        try (InputStream in = new FileInputStream(source)) {
            try (OutputStream out = new FileOutputStream(target)) {
                byte[] buffer = new byte[4096];
                int byteToRead;
                while ((byteToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, byteToRead);
                }
            }
        }
    }

    public static void fileCopyNIO(String source, String target) throws IOException {
        try (FileInputStream in = new FileInputStream(source)) {
            try (FileOutputStream out = new FileOutputStream(target)) {
                FileChannel inC = in.getChannel();
                FileChannel outC = out.getChannel();
                ByteBuffer bf = ByteBuffer.allocate(4096);
                while (inC.read(bf) != -1) {
                    bf.flip();
                    outC.write(bf);
                    bf.clear();
                }
            }
        }
    }

    public static int countWord(String file, String word) {
        int count = 0;
        try (FileReader r = new FileReader(file)) {
            try(BufferedReader br = new BufferedReader(r)) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    int idx = -1;
                    while (line.length() > word.length() && (idx = line.indexOf(word)) != -1) {
                        count++;
                        line = line.substring(idx + word.length());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void listDir(String dir, int level) {
        File fs = new File(dir);
        for(File f : fs.listFiles()) {
            for(int i=0; i<level; i++)
                System.out.print("\t");
            System.out.println(f.getName());
            if (f.isDirectory()) {
                listDir(f.getAbsolutePath(), level + 1);
            }
        }
    }

    public static void match() {
        String str = "北京市(朝阳区)(西城区)(海淀区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if(m.find()) {
            System.out.println( m.group() );
        }
    }

    public static void bubbleSort(int[] a) {
        for(int i=0; i<a.length; i++) {
            for (int j=i; j<a.length; j++) {
                if (a[j] < a[i]) {
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    public static int binSearch(int[] a, int k) {
        int l = 0, h = a.length-1;
        while ( l <= h) {
            int m = l + (h-l)/2;
            if (a[m] > k) h = m - 1;
            else if (a[m] < k) l = m + 1;
            else return m;
        }
        return -1;
    }

    public static String reverse1(String s) {
        char[] c = s.toCharArray();
        int a = 0, b = c.length-1;
        while ( a < b ) {
            c[a] = (char) (c[a] ^ c[b]);
            c[b] = (char) (c[b] ^ c[a]);
            c[a] = (char) (c[b] ^ c[a]);
            a++;
            b--;
        }
        return new String(c);
    }

    public static String reverse2(String s) {
        char[] c = s.toCharArray();
        for(int i=0; i<c.length/2; i++) {
            char tmp = c[i];
            c[i] = c[c.length - 1 - i];
            c[c.length - 1 - i] = tmp;
        }
        return new String(c);
    }

    public static String reverse3(String s) {
        char[] arr = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        for(char c : arr) {
            stack.push(c);
        }
        for(char c : arr) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return fibonacci(n-2) + fibonacci(n - 1);
    }
    static int JumpFloorII(int number) {
        return 1 << --number; //2^(number-1)用位移操作进行，更快
    }

    static boolean find(int[][] arr, int target) {
        int row = arr.length-1;
        int col = 0;
        while (row >=0 && col < arr[0].length) {
            if (arr[row][col] > target) {
                row--;
            } else if (arr[row][col] < target) {
                col++;
            } else return true;
        }
        return false;
    }

    static double power(double base, int exponent) {
        double ret = 1;
        for (int i=0; i<Math.abs(exponent); i++) {
            ret *= base;
        }
        return (exponent > 0) ? ret : 1/ret;
    }

    static Node findTopK(Node head, int k) {
        Node p1 = null, p2 = null;
        p1 = head;
        p2 = head;
        int a = k, cnt = 0;
        while (p1 != null) {
            p1 = p1.next;
            cnt++;
            if (k < 1) {
                p2 = p2.next;
            }
            k--;
        }
        if (cnt < a) return null;
        return p2;
    }

    static Node reverseNode(Node head) {
        Node prev = null, next = null;
        while (head != null){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static Node mergeNode(Node list1, Node list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val <= list2.val) {
            list1.next = mergeNode(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeNode(list1, list2.next);
            return list2;
        }
    }

    static void reorder(int[] a) {
        if (a.length < 2) return;
        int[] arr = new int[a.length];
        int oddCount = 0, oddBegin = 0;
        for(int i=0; i<a.length; i++)
            if ((a[i] & 1) == 1) oddCount++;
        for(int i=0; i<a.length; i++) {
            if ((a[i] & 1) == 1) {
                arr[oddBegin++] = a[i];
            } else {
                arr[oddCount++] = a[i];
            }
        }
        for(int i=0; i<a.length; i++) {
            a[i] = arr[i];
        }
    }

    public static void main(String[] args) throws IOException {
        //CopyFile copyfile = new CopyFile();
        long start = System.currentTimeMillis();
//        System.out.println(JumpFloorII(4));
        Node head = new Node(1);
        head.add(3);
        head.add(5);
        Node tail = new Node(2);
        tail.add(4);
        head = mergeNode(head, tail);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        String str = "this is test!";
        str = str.replaceAll("\\s", "%20");
        double dd = power(2.2,0);
        TreeSet<Person> treeSet = new TreeSet<Person>((o1, o2) -> {
            if (o1 == o2) return 0;
            int result = o1.getType() - o2.getType();
            if (result == 0) return o1.getName().compareTo(o2.getName());
            return result;
        });
        treeSet.add(new Person("张山1", 20));
        treeSet.add(new Person("张山2", 16));
        treeSet.add(new Person("张山3", 13));
        treeSet.add(new Person("张山4", 17));
        treeSet.add(new Person("张山5", 20));
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            Person p =  (Person) it.next();
            System.out.println(p);
        }
        String s = "在任何不匹配 pattern 的3字符串开始处匹配查找字符串";
        System.out.println(reverse3(s));
        int[] a = {12,4,26,15,32,7,33,3,94};
//        reorder(a);
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
        System.out.println(binSearch(a, 32));
//        try {
//            String str = "hello";
//            Method m = str.getClass().getMethod("toUpperCase");
//            System.out.println(m.invoke(str));  // HELLO
//        } catch (Exception e) {}
//        match();
//        MyUtil.listDir("C:\\Users\\Administrator\\Desktop\\", 0);
//        int count = MyUtil.countWord("C:\\Users\\Administrator\\Desktop\\report_daily_jinhua.sql", "shuffle");
//        MyUtil.fileCopy("C:\\Users\\Administrator\\Desktop\\1.rar", "C:\\Users\\Administrator\\Desktop\\2.rar");
//        MyUtil.fileCopyNIO("C:\\Users\\Administrator\\Desktop\\1.rar", "C:\\Users\\Administrator\\Desktop\\2.rar");
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }

}

class Node {
    int val;
    Node next = null;
    Node(int val) {
        this.val = val;
    }
    void add (int data) {
        Node temp = this;
        while(temp.next != null) temp=temp.next;
        temp.next = new Node(data);
    }
}
class Singleton {
    private static volatile Singleton instance = null;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}



