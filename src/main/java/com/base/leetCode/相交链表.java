package com.base.leetCode;

import java.util.ArrayList;

public class 相交链表 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode_v1(ListNode headA, ListNode headB) {
        ArrayList<ListNode> listA = new ArrayList<>();
        listA.add(headA);
        ListNode nextA = headA.next;
        while (nextA != null) {
            listA.add(nextA);
            nextA = nextA.next;
        }

        if (listA.contains(headB)) return headB;
        ListNode nextB = headB.next;
        while (nextB != null) {
            if (listA.contains(nextB)) return nextB;
            nextB = nextB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode_v2(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            if (p1 == null)
                p1 = headB;
            else
                p1 = p1.next;

            if (p2 == null)
                p2 = headB;
            else
                p2 = p2.next;
        }
        return p1;

    }
}
