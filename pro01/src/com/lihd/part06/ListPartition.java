package com.lihd.part06;

/**
 * 划分链表
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/5/4 23:12
 */
public class ListPartition {


    //方式一 使用容器
    public Node listPartition(Node head, int pivot){
        if(head == null || head.next == null) return head;
        int arrSize = 0;
        Node temp = head;
        while(temp != null){
            arrSize ++;
            temp = temp.next;
        }
        Node[] arr = new Node[arrSize];
        // 全部放到数组中
        temp = head;
        for (int i = 0; i < arrSize; i++) {
            arr[i] = temp;
            temp = temp.next;
        }
        partition(arr,pivot);
        //把数组连接起来
        for (int i = 0; i < arrSize - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        //本来就是空 赋值不赋值都一样
        arr[arrSize - 1].next = null;
        return arr[0];
    }

    public void partition(Node[] arr,int pivot){
//        int less = -1;
//        int more = arr.length - 1;//这个more是
//        int i = 0;
//        while(i <= more){
//            if(arr[i].value == pivot){
//                i ++;
//            }else if(arr[i].value > pivot){
//                swap(arr,i,more--);
//            }else{
//                swap(arr,++less,i++);
//            }
//        }

        int less = -1;
        int more = arr.length;//这个more是
        int i = 0;
        while(i < more){
            if(arr[i].value == pivot){
                i ++;
            }else if(arr[i].value > pivot){
                swap(arr,i,--more);
            }else{
                swap(arr,++less,i++);
            }
        }

//        swap(arr,arr.length - 1,more);
    }

    public void swap(Node[] arr,int i, int j){
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public Node listPartitionPro(Node head, int pivot){
        if(head == null || head.next == null) return head;
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node greatHead = null;
        Node greatTail = null;
        Node temp = null;
        while(head != null){
            temp = head.next;//记录下个节点
            head.next = null;//断开连接 感觉不是必要的

            if(head.value < pivot){
                if(smallHead == null){
                    smallHead = head;
                    smallTail = head;
                }else{
                    smallTail.next = head;
                    smallTail = head;
                }
            }else if(head.value == pivot){
                if(equalHead == null){
                    equalHead = head;
                    equalTail = head;
                }else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }else {
                if(greatHead == null){
                    greatHead = head;
                    greatTail = head;
                }else{
                    greatTail.next = head;
                    greatTail = head;
                }
            }

            head = temp;//下一个节点
        }
        //全部串起来 比较难理解
        //肯定有一块区域是有值的
        //
        if(smallHead != null){
            smallTail.next = equalHead;
            if(equalHead == null){
                smallTail.next = greatHead;
            }
        }
        if(equalHead != null){
            equalTail.next = greatHead;
        }
        return smallHead != null ? smallHead : (equalHead != null ? equalHead : greatHead);
    }
}


