package class08;

import java.util.HashMap;

// 实现RandomizedSet 类：
// RandomizedSet() 初始化 RandomizedSet 对象
// bool insert(int val) 
//     当元素 val 不存在时，向集合中插入该项，并返回 true
//     否则，返回 false
// bool remove(int val) 
//     当元素 val 存在时，从集合中移除该项，并返回 true
//     否则，返回 false
// int getRandom() 随机返回现有集合中的一项
//     每个元素应该有 相同的概率 被返回
// 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
// 测试链接 : https://leetcode.cn/problems/insert-delete-getrandom-o1/
public class Code06_InsertDeleteGetRandom {

	public class RandomizedSet {

		// v -> i
		private HashMap<Integer, Integer> keyIndexMap;
		// i -> v
		private HashMap<Integer, Integer> indexKeyMap;
		// 几个数进来了
		private int size;

		public RandomizedSet() {
			keyIndexMap = new HashMap<Integer, Integer>();
			indexKeyMap = new HashMap<Integer, Integer>();
			size = 0;
		}

		public boolean insert(int val) {
			if (!keyIndexMap.containsKey(val)) {
				keyIndexMap.put(val, size);
				indexKeyMap.put(size++, val);
				return true;
			}
			return false;
		}

		public boolean remove(int val) {
			// 拿最后的位置，填上当前要删掉数字的，洞！
			if (keyIndexMap.containsKey(val)) {
				// 删掉 30   7
				// size-1位置的数字，填洞
				// 查出30在7位置
				int deleteIndex = keyIndexMap.get(val);
				// 最后位置是啥？size - 1
				int lastIndex = --size;
				// x   lastKey
				int lastKey = indexKeyMap.get(lastIndex);
				keyIndexMap.put(lastKey, deleteIndex);
				indexKeyMap.put(deleteIndex, lastKey);
				keyIndexMap.remove(val);
				indexKeyMap.remove(lastIndex);
				return true;
			}
			return false;
		}

		public int getRandom() {
			if (size == 0) {
				return -1;
			}
			int randomIndex = (int) (Math.random() * size);
			return indexKeyMap.get(randomIndex);
		}
	}

}
