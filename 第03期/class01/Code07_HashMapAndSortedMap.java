package class01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Code07_HashMapAndSortedMap {

	public static class Student {
		public int age;

		public Student(int a) {
			age = a;
		}
	}

	public static void main(String[] args) {
		Student s1 = new Student(4);
		Student s2 = new Student(4);
		Student s3 = new Student(4);
		HashSet<Student> set = new HashSet<>();
		set.add(s1);
		set.add(s2);
		set.add(s3);
		System.out.println(set.size());
		Student s4 = new Student(4);
		System.out.println(set.contains(s4));

		HashMap<Integer, String> map = new HashMap<>();
		map.put(7, "我是7");
		map.remove(7);
		map.put(7, "我是7");
		map.put(7, "他是7");
		map.get(7);
		map.containsKey(7);

		// O(1) 常数时间

		TreeMap<Integer, String> treeMap = new TreeMap<>();
		treeMap.put(-5, "我是5");
		treeMap.put(15, "我是5");
		treeMap.put(555, "我是5");
		treeMap.put(5, "我是5");
		treeMap.put(3, "我是5");
		treeMap.put(50, "我是5");

		// put、remove、get、containsKey
		// log N
		System.out.println(treeMap.firstKey());
		System.out.println(treeMap.lastKey());
		int num = -10;
		// <= num 且离num最近的key
		System.out.println(treeMap.floorKey(num));
		// >= num 且离num最近的key
		System.out.println(treeMap.ceilingKey(num));
		System.out.println("==========");
		Cat cat1 = new Cat(4, 40);
		Cat cat2 = new Cat(3, 400);
		Cat cat3 = new Cat(-9, 17);
		Cat cat4 = new Cat(1000, 7);

		Cat[] cats = { cat1, cat2, cat3, cat4 };

		// 根据年龄排序
		Arrays.sort(cats, (a, b) -> b.age - a.age);
		for (Cat cat : cats) {
			System.out.println(cat.age + " , " + cat.weight);
		}

		TreeSet<Cat> catSet = new TreeSet<>((a, b) -> b.age - a.age);
		catSet.add(cat1);
		catSet.add(cat2);
		catSet.add(cat3);
		catSet.add(cat4);

		System.out.println(catSet.first().age);

		// 根据重量排序

		Integer[] arr = { 3, 4, 7, 2 };
		Arrays.sort(arr, (a,b) -> b - a);
		for(Integer x : arr) {
			System.out.println(x);
		}
		
		int[] arr2 = { 3, 4, 7, 2 };
		Arrays.sort(arr2);
		for(int x : arr2) {
			System.out.println(x);
		}
	}

	public static class CatComparator implements Comparator<Cat> {

		// 返回负，o1好！
		// 返回正，o2好！
		// 返回0，o1、o2一样好！

		@Override
		public int compare(Cat o1, Cat o2) {
//			if (o1.age < o2.age) {
//				return -1;
//			} else if (o1.age > o2.age) {
//				return 1;
//			} else {
//				return 0;
//			}
			return o2.age - o1.age;
		}

	}

	public static class Cat {
		public int age;
		public int weight;

		public Cat(int a, int w) {
			age = a;
			weight = w;
		}
	}

}
