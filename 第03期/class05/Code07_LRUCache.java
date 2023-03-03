package class05;

import java.util.HashMap;

// 测试链接 : https://leetcode.cn/problems/lru-cache/
public class Code07_LRUCache {

	// 提交以下这个类
	public class LRUCache {

		public static class Node {
			public int key;
			public int val;
			public Node last;
			public Node next;

			public Node(int k, int v) {
				key = k;
				val = v;
			}
		}

		public static class DoubleLinkedList {
			private Node head;
			private Node tail;

			public DoubleLinkedList() {
				head = null;
				tail = null;
			}

			public void addNode(Node newNode) {
				if (newNode == null) {
					return;
				}
				if (head == null) {
					head = newNode;
					tail = newNode;
				} else {
					tail.next = newNode;
					newNode.last = tail;
					tail = newNode;
				}
			}

			public void moveNodeToTail(Node node) {
				if (tail == node) {
					return;
				}
				if (head == node) {
					head = node.next;
					head.last = null;
				} else {
					node.last.next = node.next;
					node.next.last = node.last;
				}
				node.last = tail;
				node.next = null;
				this.tail.next = node;
				this.tail = node;
			}

			public Node removeHead() {
				if (head == null) {
					return null;
				}
				Node ans = this.head;
				if (head == tail) {
					head = null;
					tail = null;
				} else {
					head = ans.next;
					ans.next = null;
					head.last = null;
				}
				return ans;
			}

		}

		private HashMap<Integer, Node> keyNodeMap;
		private DoubleLinkedList nodeList;
		private final int capacity;

		public LRUCache(int cap) {
			keyNodeMap = new HashMap<>();
			nodeList = new DoubleLinkedList();
			capacity = cap;
		}

		public int get(int key) {
			if (keyNodeMap.containsKey(key)) {
				Node ans = keyNodeMap.get(key);
				nodeList.moveNodeToTail(ans);
				return ans.val;
			}
			return -1;
		}

		public void put(int key, int value) {
			if (keyNodeMap.containsKey(key)) {
				Node node = keyNodeMap.get(key);
				node.val = value;
				nodeList.moveNodeToTail(node);
			} else {
				if (keyNodeMap.size() == capacity) {
					removeMostUnusedCache();
				}
				Node newNode = new Node(key, value);
				keyNodeMap.put(key, newNode);
				nodeList.addNode(newNode);
			}
		}

		private void removeMostUnusedCache() {
			Node removeNode = nodeList.removeHead();
			keyNodeMap.remove(removeNode.key);
		}

	}

}
