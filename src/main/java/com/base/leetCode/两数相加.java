package com.base.leetCode;

public class 两数相加 {

    public static class ListNode {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb1 = new StringBuilder(l1.val + "");
        while (l1.next != null) {
            sb1.append(l1.next.val + "");
            l1 = l1.next;
        }
        String sb1_str = sb1.toString();
        sb1 = new StringBuilder();
        for (int i = sb1_str.length() - 1; i >= 0; i--) {
            sb1.append(sb1_str.charAt(i));
        }

        StringBuilder sb2 = new StringBuilder(l2.val + "");
        while (l2.next != null) {
            sb2.append(l2.next.val + "");
            l2 = l2.next;
        }
        String sb2_str = sb2.toString();
        sb2 = new StringBuilder();
        for (int i = sb2_str.length() - 1; i >= 0; i--) {
            sb2.append(sb2_str.charAt(i));
        }

        Long val1 = Long.valueOf(sb1.toString());
        Long val2 = Long.valueOf(sb2.toString());
        Long val = val1 + val2;

        String val_str = val.toString();
        int size = val_str.length();
        ListNode ln = new ListNode(Integer.valueOf(val_str.charAt(size - 1) + ""));
        ListNode ln_next = ln.next = size > 1 ? new ListNode(Integer.valueOf(val_str.charAt(size - 2) + "")) : null;
        for (int i = size - 3; i >= 0; i--) {
            ln_next.next = new ListNode(Integer.valueOf(val_str.charAt(i) + ""));
            ln_next = ln_next.next;
        }
        return ln;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int val1 = l1.val;
        int val2 = l2.val;
        int val = val1 + val2;
        int val_add = val >= 10 ? 1 : 0;
        int val_index = val >= 10 ? val - 10 : val;
        ListNode ln0 = new ListNode(0);
        ListNode ln = ln0.next = new ListNode(val_index);
        while (l1.next != null || l2.next != null) {
            val1 = l1.next != null ? l1.next.val : 0;
            val2 = l2.next != null ? l2.next.val : 0;
            val = val1 + val2 + val_add;
            val_add = val >= 10 ? 1 : 0;
            val_index = val >= 10 ? val - 10 : val;
            ln.next = new ListNode(val_index);
            ln = ln.next;
            l1 = l1.next != null ? l1.next : l1;
            l2 = l2.next != null ? l2.next : l2;
        }
        if(val_add == 1){
            ln.next = new ListNode(val_add);
        }
        return ln0.next;
    }

    public static void main(String[] args) {
        String val_str = "243";
        ListNode l1 = new ListNode(Integer.valueOf(val_str.charAt(val_str.length() - 1) + ""));
        ListNode ln_next = l1.next = new ListNode(Integer.valueOf(val_str.charAt(val_str.length() - 2) + ""));
        for (int i = val_str.length() - 3; i >= 0; i--) {
            ln_next.next = new ListNode(Integer.valueOf(val_str.charAt(i) + ""));
            ln_next = ln_next.next;
        }
        System.out.println("111");

        StringBuilder sb1 = new StringBuilder(l1.val + "");
        while (l1.next != null) {
            sb1.append(l1.next.val + "");
            l1 = l1.next;
        }
        String sb1_str = sb1.toString();
        sb1 = new StringBuilder();
        for (int i = sb1_str.length() - 1; i >= 0; i--) {
            sb1.append(sb1_str.charAt(i));
        }
    }
}
