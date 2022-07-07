package com.base.leetCode;

public class 合并两个有序链表 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        int val1;
        int val2;
        ListNode merge = null;
        ListNode merge_next = null;
        while (list1 != null || list2 != null) {
            val1 = list1 == null ? 101 : list1.val;
            val2 = list2 == null ? 101 : list2.val;
            ListNode node;

            if (val1 >= val2) {
                node = new ListNode(val2);
                list2 = list2.next;
            } else {
                node = new ListNode(val1);
                list1 = list1.next;
            }

            if (merge == null) {
                merge = node;
                merge_next = merge;
            } else {
                merge_next.next = node;
                merge_next = merge_next.next;
            }
        }
        return merge;
    }

}

