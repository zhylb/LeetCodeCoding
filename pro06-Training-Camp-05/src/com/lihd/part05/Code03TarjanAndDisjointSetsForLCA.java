package com.lihd.part05;

import java.util.*;

/**
 * 代码出错了
 * 找了好久才知道, 因为 使用fatherMap.get() 而没有使用 findFather()获取
 * 以后 只有再 findFather内部使用 fatherMap.get() 其他情况使用 findFather()方法获取 才能保证正确
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/8/25 15:14
 */
public class Code03TarjanAndDisjointSetsForLCA {

    /**
     * Tarjan算法 和 并查集 批量查询公共祖先的主方法 <br/>
     * 请保证参数的有效性 复杂度可以做到 O(N + M)
     * @param root 二叉树根节点 假设节点个数为N
	 * @param queries 要查询的数组 假设长度为M
     * @return com.lihd.part05.Code03TarjanAndDisjointSetsForLCA.Node[]
     * @author lihd
     * @date 2022/8/25 16:56
     */
    public static Node[] tarJanQuery(Node root, Query[] queries) {
        // 将二叉树转换为 node
        List<Node> list = generateBinaryTreeNodesToList(root);
        UnionFind<Node> unionFind = new UnionFind<>(list);
        Node[] ans = new Node[queries.length];
        HashMap<Node, List<Node>> nodeMap = new HashMap<>();
        HashMap<Node, List<Integer>> indexMap = new HashMap<>();
        addToRegistrationMapAndAddSimpleAnswer(queries, nodeMap, indexMap, ans);
        addToAnswer(root, nodeMap, indexMap, unionFind, ans);
        return ans;
    }

    /**
     * 从根节点调用这个方法即可, 要求每个参数都初始化完成, 只剩下 部分的答案没有填入ans <br/>
     * 最终会把答案填完.
     * @param node 递归遍历所处的节点
     * @param nodeMap 填完的 每个节点 对应一群节点的 map
     * @param indexMap 填完的 每个节点 对应一群index的 map
	 * @param unionFind 自定义的并查集 功能强大
     * @param ans 主过程中最终返回的 答案数组 Node[]
     * @author lihd
     * @date 2022/8/25 16:54
     */
    private static void addToAnswer(Node node,
                                    HashMap<Node, List<Node>> nodeMap,
                                    HashMap<Node, List<Integer>> indexMap,
                                    UnionFind<Node> unionFind,
                                    Node[] ans) {

        if (node == null) {
            return;
        }
        // 只有遍历过的 节点 才会设置 tag
        // 这说明了如果一个节点没有tag ,说明还没有遍历过
        addToAnswer(node.left, nodeMap, indexMap, unionFind, ans);
        unionFind.union(node.left, node);
        unionFind.setTag(node, node);

        addToAnswer(node.right, nodeMap, indexMap, unionFind, ans);
        unionFind.union(node.right, node);
        unionFind.setTag(node, node);


        //全部设置好, 最后一次进入该节点
        List<Node> nodeList = nodeMap.get(node);
        List<Integer> indexList = indexMap.get(node);
        // 为什么要考虑为 null呢 因为 我要求查询的节点 和 node没有关系的肯定是null
        // 为什么要考虑为 空呢 不考虑是空的 这个while 一定能把所有的元素都用完,到时候一定报错
        while (nodeList != null && !nodeList.isEmpty()) {
            Node nodeRemove = nodeList.remove(nodeList.size() - 1);
            Integer indexRemove = indexList.remove(indexList.size() - 1);

            // 下面这个方法 可以获取 这个点所在集合的 tag (集合代表点的tag)
            if (unionFind.containsTag(nodeRemove)) {
                // 如果这个节点有tag 说明遍历过, 而且我们是第二次遍历,
                // 也就是node 和 nodeRemove是一对
                // nodeRemove 有tag , 是第一次遍历,
                // node 是一对中第二次到达的, 需要结算 ,
                // indexRemove 是一对中第一次到达的索引, 应该结算, 并且结算值是 第一次遍历所在集合中的tag
                ans[indexRemove] = unionFind.getTag(nodeRemove);
            }
            // 如果这个节点没有tag 说明还没有遍历到, 我可以非常放松的删去这些节点 而不用担心发生问题
        }

    }

    /**
     * 这个过程 会把传入 的 nodeMap 和 indexMap初始好 (因此初始前请保证里面没有元素且不是null)
     * 并且会 填上简单的答案 比如 两个queries中两个节点相同, 或者一个节点为null 都是非常好填的
     * 这个填简单答案是必须的, 因为主过程是没有办法填入这些元素的
     * @param queries 要查询的数组
	 * @param nodeMap 每个节点 对应一群节点的 map
	 * @param indexMap 每个节点 对应一群index的 map
	 * @param ans 主过程中最终返回的 答案数组 Node[]
     * @author lihd
     * @date 2022/8/25 16:49
     */
    private static void addToRegistrationMapAndAddSimpleAnswer(Query[] queries,
                                                               HashMap<Node, List<Node>> nodeMap,
                                                               HashMap<Node, List<Integer>> indexMap,
                                                               Node[] ans) {
        for (int i = 0; i < queries.length; i++) {
            Node o1 = queries[i].o1;
            Node o2 = queries[i].o2;
            if (o1 == o2 || o1 == null || o2 == null) {
                ans[i] = o1 == null ? o2 : o1;
            } else {
                // 有必要填
                // 正着填
                if (nodeMap.containsKey(o1)) {
                    nodeMap.get(o1).add(o2);
                    indexMap.get(o1).add(i);
                } else {
                    ArrayList<Node> list1 = new ArrayList<>();
                    list1.add(o2);
                    nodeMap.put(o1, list1);

                    ArrayList<Integer> list2 = new ArrayList<>();
                    list2.add(i);
                    indexMap.put(o1, list2);
                }
                // 倒着填
                if (nodeMap.containsKey(o2)) {
                    nodeMap.get(o2).add(o1);
                    indexMap.get(o2).add(i);
                } else {
                    ArrayList<Node> list1 = new ArrayList<>();
                    list1.add(o1);
                    nodeMap.put(o2, list1);

                    ArrayList<Integer> list2 = new ArrayList<>();
                    list2.add(i);
                    indexMap.put(o2, list2);
                }
            }
        }

    }

    /**
     * 使用先序遍历将 root节点及其所有子节点 放到list中 并且返回
     * 和 addNodeToListByRecursive 配合使用
     * @param root 根节点
     * @return java.util.List<com.lihd.part05.Code03TarjanAndDisjointSetsForLCA.Node>
     * @author lihd
     * @date 2022/8/25 16:48
     */
    private static List<Node> generateBinaryTreeNodesToList(Node root) {
        List<Node> list = new ArrayList<>();
        addNodeToListByRecursive(root, list);
        return list;
    }
    /**
     * 使用先序遍历 将 node节点及其所有子节点放到node中 和 generateBinaryTreeNodesToList 搭配使用
     * @param node 节点
	 * @param list 要把节点添加到的list
     * @author lihd
     * @date 2022/8/25 16:47
     */
    private static void addNodeToListByRecursive(Node node, List<Node> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        addNodeToListByRecursive(node.left, list);
        addNodeToListByRecursive(node.right, list);
    }


    /**
     * 并查集
     * @param <T>
     */
    private static class UnionFind<T>{
        HashMap<T, Wrapper<T>> nodesMap = new HashMap<>();
        HashMap<Wrapper<T>, Wrapper<T>> fatherMap = new HashMap<>();
        HashMap<Wrapper<T>, Integer> sizeMap = new HashMap<>();

        HashMap<Wrapper<T>, Wrapper<T>> tagMap = new HashMap<>();

        public UnionFind(List<T> list) {
            for (T t : list) {
                Wrapper<T> w = new Wrapper<>(t);
                nodesMap.put(t, w);
                fatherMap.put(w, w);
                sizeMap.put(w, 1);
            }
        }

        /**
         * 判断节点t所在的集合中 的tag是否在tag集合中
         * @param t 节点
         * @return boolean
         * @author lihd
         * @date 2022/8/25 16:30
         */
        public boolean containsTag(T t) {
            return tagMap.containsKey(findFather(t));
        }

        /**
         * 返回 节点t所在集合中的 tag
         * @param t 节点
         * @return T
         * @author lihd
         * @date 2022/8/25 16:32
         */
        public T getTag(T t) {
            return tagMap.get(findFather(t)).val;
        }

        /**
         * 将节点1 所在的集合 设置一个tag, tag不会找代表点, 会直接 成为节点1的tag
         * @param t 节点1
	     * @param tag tag
         * @author lihd
         * @date 2022/8/25 16:29
         */
        public void setTag(T t, T tag) {
            if (isNotValid(t) || isNotValid(tag) ){
                return;
            }
            Wrapper<T> fT = findFather(t);
            Wrapper<T> nodeTag = nodesMap.get(tag);
            tagMap.put(fT, nodeTag);
        }

        /**
         * 将t1 和 t2 所在集合的两个集合 合并起来
         * @param t1 节点1
         * @param t2 节点2
         * @author lihd
         * @date 2022/8/25 16:28
         */
        public void union(T t1, T t2) {
            if (isNotValid(t1) || isNotValid(t2)) {
                return;
            }
            // 保证有效性
            Wrapper<T> f1 = findFather(t1);
            Wrapper<T> f2 = findFather(t2);
            if (f1 == f2) {
                return;
            }
            int size1 = sizeMap.get(f1);
            int size2 = sizeMap.get(f2);
            Wrapper<T> large = size1 > size2 ? f1 : f2;
            Wrapper<T> small = large == f1 ? f2 : f1;
            fatherMap.put(small, large);
            sizeMap.put(large, size1 + size2);
            sizeMap.remove(small);
        }

        private Wrapper<T> findFather(T t) {
            Wrapper<T> w = nodesMap.get(t);
            Stack<Wrapper<T>> stack = new Stack<>();
            while (w != fatherMap.get(w)) {
                stack.push(w);
                w = fatherMap.get(w);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), w);
            }
            return w;
        }

        private boolean isNotValid(T t) {
            return !nodesMap.containsKey(t);
        }


    }

    private static class Wrapper<T> {
        T val;
        public Wrapper(T val) {
            this.val = val;
        }
    }


    /**
     * Query类
     */
    private static class Query{
        Node o1;
        Node o2;
        public Query(Node o1, Node o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }

    /**
     * 二叉树节点
     * @author lihd
     * @date 2022/8/25 15:15
     */
    private static class Node{
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    //////////////////


    // for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.val + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.right.right.left = new Node(8);
		printTree(head);
		System.out.println("===============");

		Query[] qs = new Query[7];
		qs[0] = new Query(head.left.right, head.right.left);
		qs[1] = new Query(head.left.left, head.left);
		qs[2] = new Query(head.right.left, head.right.right.left);
		qs[3] = new Query(head.left.left, head.right.right);
		qs[4] = new Query(head.right.right, head.right.right.left);
		qs[5] = new Query(head, head);
//		qs[6] = new Query(head.left, head.right.right.left);
		qs[6] = new Query(head.right, head.right.right.left);


		Node[] ans = tarJanQuery(head, qs);
        System.out.println(Arrays.toString(ans));

		for (int i = 0; i != ans.length; i++) {
			System.out.println("o1 : " + qs[i].o1.val);
			System.out.println("o2 : " + qs[i].o2.val);
			System.out.println("ancestor : " + ans[i].val);
			System.out.println("===============");
		}

	}
}
