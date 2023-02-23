package class03;

import java.util.PriorityQueue;

// 给你一个链表数组，每个链表都已经按升序排列。
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。
// 测试链接：https://leetcode.cn/problems/merge-k-sorted-lists/
public class Code01_MergeKSortedLists {

//	public static void main(String[] args) {
//		ListNode h1 = new ListNode(1);
//		h1.next = new ListNode(6);
//		h1.next.next = new ListNode(9);
//
//		ListNode h2 = new ListNode(3);
//		h2.next = new ListNode(4);
//		h2.next.next = new ListNode(4);
//
//		ListNode h3 = new ListNode(5);
//		h3.next = new ListNode(6);
//		h3.next.next = new ListNode(9);
//
//		ListNode[] arr = { h1, h2, h3 };
//
//		ListNode all = mergeKLists(arr);
//
//		while (all != null) {
//			System.out.print(all.val + " ");
//			all = all.next;
//		}
//		System.out.println();
//	}

	// 这个类不提交
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int v) {
			val = v;
		}
	}

	// 提交以下方法
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null) {
			return null;
		}
		// 小根堆！
		PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
		// 所有链表的头节点，进入小根堆
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				heap.add(lists[i]);
			}
		}
		if (heap.isEmpty()) {
			return null;
		}
		ListNode head = heap.poll();
		ListNode pre = head;
		if (pre.next != null) {
			heap.add(pre.next);
		}
		while (!heap.isEmpty()) {
			ListNode cur = heap.poll();
			pre.next = cur;
			pre = cur;
			if (cur.next != null) {
				heap.add(cur.next);
			}
		}
		return head;
	}

}
