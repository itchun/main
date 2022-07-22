package com.base.leetCode;

import com.base.leetCode.entity.ListNode;

import java.util.ArrayList;

public class 环形链表 {

    public static void main(String[] args) {
        ListNode node = getListNode();
        System.out.println(hasCycle(node));
    }

    public static ListNode getListNode() {
        ListNode index_1 = new ListNode(1);
        ListNode index_2 = new ListNode(2);
        index_1.next = index_2;
        index_2.next = index_1;
        return index_1;
    }

    public static boolean hasCycle(ListNode head) {
        boolean is = false;
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            int hashcode = head.hashCode();
            if(list.contains(hashcode)) {
                is = true;
                break;
            }
            list.add(hashcode);
            head = head.next;
        }
        return is;
    }
}
