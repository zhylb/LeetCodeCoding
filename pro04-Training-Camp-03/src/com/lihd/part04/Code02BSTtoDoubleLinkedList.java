package com.lihd.part04;

import ans.class04.Code02_BSTtoDoubleLinkedList;

import java.util.Map;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/16 22:51
 */
public class Code02BSTtoDoubleLinkedList {
    public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

    public static class Info{
        Node last;
        Node next;


        public Info(Node last, Node next) {
            this.last = last;
            this.next = next;
        }
    }


    public static Node convert(Node head) {
        if (head == null) {
            return null;
        }
        return getInfo(head).last;
    }
    public static Info getInfo(Node node) {
        //上游能保证node 不为null吗
        if (node == null) {
            return new Info(null, null);
        }
        //假设可以
        //node此时不是null
        Info leftInfo = getInfo(node.left);
        Info rightInfo = getInfo(node.right);


        if (leftInfo.next != null) {
            leftInfo.next.right = node;
            node.left = leftInfo.next;
        }
        if (rightInfo.last != null) {
            rightInfo.last.left = node;
            node.right = rightInfo.last;
        }

        return new Info(
                leftInfo.last != null ? leftInfo.last : node,
                rightInfo.next != null ? rightInfo.next : node
        );






    }

    public static void printDoubleLinkedList(Node head) {
		System.out.print("Double Linked List: ");
		Node end = null;
		while (head != null) {
			System.out.print(head.value + " ");
			end = head;
			head = head.right;
		}
		System.out.print("| ");
		while (end != null) {
			System.out.print(end.value + " ");
			end = end.left;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(2);
		head.right = new Node(9);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.left.right.right = new Node(4);
		head.right.left = new Node(7);
		head.right.right = new Node(10);
		head.left.left = new Node(1);
		head.right.left.left = new Node(6);
		head.right.left.right = new Node(8);

        printDoubleLinkedList(head);
		head = convert(head);
		printDoubleLinkedList(head);

        System.out.println("=============================");

		head = new Node(5);
		head.left = new Node(2);
		head.right = new Node(9);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.left.right.right = new Node(4);
		head.right.left = new Node(7);
		head.right.right = new Node(10);
		head.left.left = new Node(1);
		head.right.left.left = new Node(6);
		head.right.left.right = new Node(8);

        printDoubleLinkedList(head);
		head = convert(head);
		printDoubleLinkedList(head);

	}



}
