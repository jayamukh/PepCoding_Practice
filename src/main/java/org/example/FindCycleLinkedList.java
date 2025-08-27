package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public  class FindCycleLinkedList {


    public static boolean hasCycle(Node head) {

        /*if(head == null)
        {
            return false;
        }

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
            {
                return true;
            }
        }
        return false;*/
        //Using Hash Set

        Set<Node> set  = new HashSet<>();
        Node current  = head;

        while(current != null)
        {

            if(set.contains(current))
                return true;

             set.add(current);
            current = current.next;
        }
        return false;
    }



    public static void main(String[] args)
    {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head.next;

        System.out.println(hasCycle(head));
    }
}
   // Node head;

    class Node{

        Node next;
        int data;
        Node(int d)
        {
            this.data = d;
            this.next = null;
        }


    }





