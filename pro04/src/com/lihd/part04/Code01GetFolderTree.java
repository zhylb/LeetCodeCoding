package com.lihd.part04;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/6/16 22:51
 */
public class Code01GetFolderTree {


    public static class Node {
        String path;
        TreeMap<String, Node> map = new TreeMap<>();
        public Node(String path) {
            this.path = path;
        }
    }

    public static Node generateFolderTree(String[] folderPaths) {
        Node root = new Node("");
        for (String folderPath : folderPaths) {
            String[] paths = folderPath.split("\\\\");
            Node cur = root;
            for (String path : paths) {
                if (!cur.map.containsKey(path)) {
                    cur.map.put(path, new Node(path));
                }
                cur = cur.map.get(path);
            }
        }
        return root;
    }

    public static void printFolder(String[] folderPaths) {
        Node node = generateFolderTree(folderPaths);
        printFolderTree(node, 0);
    }

    public static void printFolderTree(Node node, int level) {
        if (level != 0) {
            System.out.println(getBlank((level - 1) * 2) + node.path);
        }
        for (Map.Entry<String, Node> entry : node.map.entrySet()) {
            printFolderTree(entry.getValue(),level + 1);
        }
    }

    public static String getBlank(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] arr ={"b\\st","d","a\\d\\e","a\\b\\c"};
        printFolder(arr);
    }
}
