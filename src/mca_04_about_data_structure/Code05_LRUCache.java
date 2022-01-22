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

		// 自己要实现的双向链表！
		public class NodeDoubleLinkedList {
			private Node head;
			private Node tail;

			public NodeDoubleLinkedList() {
				head = null;
				tail = null;
			}

			// 让你加一个节点！往尾巴上加！
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

			// 一定能保证，x就在双向链表上！
			// 请把，x之前，和x之后，的节点之间重连！抠出x
			// 把x放到尾巴上去！
			public void moveNodeToTail(Node x) {
				if (tail == x) {
					return;
				}
				// x不是尾巴！x右边一定是有节点的！不为空！
				if (head == x) { // 前边没有节点，x直接去尾巴！，head要往下走！
					head = x.next;
					head.last = null;
				} else { // x有前！也有后！
					x.last.next = x.next;
					x.next.last = x.last;
				}
				x.last = tail;
				x.next = null;
				tail.next = x;
				tail = x;
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
