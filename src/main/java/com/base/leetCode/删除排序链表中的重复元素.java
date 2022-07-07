package com.base.leetCode;

public class 删除排序链表中的重复元素 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode new_head = null;
        ListNode new_head_next = null;
        Integer i = null;
        while (head != null) {
            if (i == null) {
                new_head = new ListNode(head.val);
                new_head_next = new_head;
                i = head.val;
                head = head.next;
                continue;
            }
            int val = head.val;
            if (i != val) {
                new_head_next.next = new ListNode(head.val);
                new_head_next = new_head_next.next;
            }
            i = val;
            head = head.next;
        }
        return new_head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode befor_head = null;
        ListNode new_head = head;
        Integer i = null;
        while (new_head != null) {
            if (i == null) {
                i = new_head.val;
                befor_head = new_head;
                new_head = new_head.next;
                continue;
            }
            int val = new_head.val;
            if (i == val) {
                befor_head.next = new_head.next;
            } else {
                befor_head.next = new_head;
            }
            befor_head = befor_head.next;
            new_head = new_head.next;
        }
        return befor_head;
    }


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
}

