package com.algo;

import com.google.common.collect.Lists;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo {
	
    public static void main(String[] args) {
        HashMap hm3 = Lists.<Person>newArrayList(
                new Person(1, "tom"),
                new Person(1, "jerry")
        ).stream()
                .collect(() -> new HashMap<Integer,List<Person>>(),
                        (h, x) -> {
                            List<Person> value = h.getOrDefault(x.getType(), Lists.newArrayList());
                            value.add(x);
                            h.put(x.getType(), value);
                        },
                        HashMap::putAll
                );

        HashMap hm = Lists.<Person>newArrayList(
                new Person(1, "tom"),
                new Person(1, "jerry")
        ).stream()
                .collect(Collectors.groupingBy(Person::getType, HashMap::new, Collectors.mapping(Person::getName,Collectors.toSet())));

        final Integer collect = Lists.newArrayList(1, 2, 3, 4, 5)
                .stream().reduce(0, Integer::sum);

        final Integer collect2 = Lists.newArrayList(
                1,2,3,4,5,6
        )
                .stream()
                .collect(Collectors.reducing(0, Integer::sum));

        Map<Integer, String> namesMap = new HashMap<>();
        namesMap.put(1, "Larry");
        namesMap.put(2, "Steve");
        namesMap.put(3, "James");
        namesMap.forEach((key, value) -> System.out.println(key + " " + value));
        namesMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        int[] a = {1,2,3,4,5,6,7,8,9};
//        int b = bs(a, 0, a.length-1, 6);
        int b = bs1(a,8);
        System.out.println(b +"," + a[b]);
        String s1 = reverse11("skdkki2idifi23932dkdkfjgh");
        System.out.println(s1);
//        String s2 = reverse2("纸头乱照照");
//        String s3 = reverse3("纸头乱照照");
//        String s4 = reverse4("纸头乱照照");
        rect();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(c.getTime()));

        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = dt1.minusDays(1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(dt2.format(dtf));
    }


    static void rect() {
        double[][] a = {{1,1},{1,2},{2,2},{2,1.5},{3,1.5},{3,1}};
        int k = 13, len = a.length;
        double p = 0;
        List<Double> li = new ArrayList<>();
        for (int i=0; i<len; i++) {
            double x1 = a[i][0], x2 = i+1 == len ? a[0][0] : a[i+1][0];
            double y1 = a[i][1], y2 = i+1 == len ? a[0][1] : a[i+1][1];
            p += Math.abs(x1 - x2);
            p += Math.abs(y1 - y2);
            li.add(p);
        }
        double avg = p/k;
        double d = avg;
        System.out.println(li.size());
        int m = 0;
        double it = li.get(m);
        for(int i=1; i<k; i++) {
            while (d >= it && m+1 < len) it = li.get(++m);
            int n = m + 1;
            double x1 = a[m][0], x2 = n == len ? a[0][0] : a[n][0];
            double y1 = a[m][1], y2 = n == len ? a[0][1] : a[n][1];
            double x = 0, y = 0;
            if (x1 < x2) {
                x = x2 - (it - d);
                y = y1;
            } else if (x1 > x2) {
                x = x2 + (it - d);
                y = y1;
            } else if (y1 < y2) {
                x = x1;
                y = y2 - (it - d);
            } else if (y1 > y2) {
                x = x1;
                y = y2 + (it - d);
            }
            System.out.println(x +","+ y+","+ it+","+ d+","+ m);
            d += avg;
//            while (d >= it && m+1 < len) it = li.get(++m);
        }
        System.out.println(li);
    }

    static String reverse11(String s) {
        char[] c = s.toCharArray();
        int begin = 0;
        int end = c.length-1;
        while (begin < end) {
            c[begin] = (char) (c[begin] ^ c[end]);
            c[end] = (char) (c[end] ^ c[begin]);
            c[begin] = (char) (c[end] ^ c[begin]);
            begin++;
            end--;
        }
        return new String(c);
    }

    static int bs1(int[]a, int k) {
        int low = 0, high = a.length-1;
        while (low <= high) {
            int m = low + (high-low)/2;
            if (a[m] == k) return m;
            else if (a[m] > k) high = m - 1;
            else low = m + 1;
        }
        return -1;
    }

//    static String reverse1(String s) {
//        char[] arr = s.toCharArray();
//        Stack<Character> stack = new Stack<Character>();
//        for (char c : arr) {
//            stack.push(c);
//        }
//        StringBuffer sb = new StringBuffer();
//        for (char c: arr) {
//            sb.append(stack.pop());
//        }
//        return sb.toString();
//    }


    static String reverse1(String s) {
        char[] arr = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        for (char c : arr) {
            stack.push(c);
        }
        for (char c : arr) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    static String reverse2(String s) {
        String r = "";
        for(int i=0; i<s.length(); i++) {
            r = s.charAt(i) + r;
        }
        return r;
    }

    static String reverse3(String s) {
        char[] a = s.toCharArray();
        int end = s.length() - 1;
        int m = end / 2;
        for (int i=0; i<m; i++) {
            char tmp = a[i];
            a[i] = a[end-i];
            a[end-i] = tmp;
        }
        return new String(a);
    }

    static String reverse4(String s) {
        char[] a = s.toCharArray();
        int begin = 0;
        int end = s.length() - 1;
        while (begin < end) {
            a[begin] = (char) (a[begin] ^ a[end]); // a = a ^ b
            a[end] = (char) (a[end] ^ a[begin]); // b = b ^ a
            a[begin] = (char) (a[end] ^ a[begin]); // a = b ^ a
            begin++;
            end--;
        }
        return new String(a);
    }

}

class Person implements Comparable<Person> {
    private  int type;
    private String name;

    Person (int type, String name) {
        this.type = type;
        this.name = name;
    }

    Person (String name, int type) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return this.type;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return type + ((name == null) ? 0 : name.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (type != other.type)
            return false;
        if (name == null) {
            return other.name == null;
        } else return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", type=" + type + "]";
    }

    @Override
    public int compareTo(Person o) {
        int result = this.type - o.type;
        if (result == 0) {
            return this.name.compareTo(o.name);
        }
        return result;
    }
}


class Single {
    private volatile static Single inst;
    private Single(){}
    public static Single getInst() {
        if (inst == null) {
            synchronized (Single.class) {
                if (inst == null)
                    inst = new Single();
            }
        }
        return inst;
    }
}







