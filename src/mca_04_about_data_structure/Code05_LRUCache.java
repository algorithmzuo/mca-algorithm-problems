package mca_04_about_data_structure;

import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/lru-cache/
public class Code05_LRUCache {

	public class LRUCache {

		public LRUCache(int capacity) {
			cache = new MyCache(capacity);
		}

		private MyCache cache;

		public int get(int key) {
			return cache.get(key);
		}

		public void put(int key, int value) {
			cache.set(key, value);
		}

		public class Node {
			public int key;
			public int value;
			public Node last;
			public Node next;

			public Node(int k, int v) {
				key = k;
				value = v;
			}
		}

		public class NodeDoubleLinkedList {
			private Node head;
			private Node tail;

			public NodeDoubleLinkedList() {
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
				tail.next = node;
				tail = node;
			}

			public Node removeHead() {
				if (head == null) {
					return null;
				}
				Node res = head;
				if (head == tail) {
					head = null;
					tail = null;
				} else {
					head = res.next;
					res.next = null;
					head.last = null;
				}
				return res;
			}

		}

		public class MyCache {
			private HashMap<Integer, Node> keyNodeMap;
			private NodeDoubleLinkedList nodeList;
			private final int capacity;

			public MyCache(int cap) {
				keyNodeMap = new HashMap<>();
				nodeList = new NodeDoubleLinkedList();
				capacity = cap;
			}

			public int get(int key) {
				if (keyNodeMap.containsKey(key)) {
					Node res = keyNodeMap.get(key);
					nodeList.moveNodeToTail(res);
					return res.value;
				}
				return -1;
			}

			public void set(int key, int value) {
				if (keyNodeMap.containsKey(key)) {
					Node node = keyNodeMap.get(key);
					node.value = value;
					nodeList.moveNodeToTail(node);
				} else {
					Node newNode = new Node(key, value);
					keyNodeMap.put(key, newNode);
					nodeList.addNode(newNode);
					if (keyNodeMap.size() == capacity + 1) {
						removeMostUnusedCache();
					}
				}
			}

			private void removeMostUnusedCache() {
				Node removeNode = nodeList.removeHead();
				keyNodeMap.remove(removeNode.key);
			}

		}
	}

}
