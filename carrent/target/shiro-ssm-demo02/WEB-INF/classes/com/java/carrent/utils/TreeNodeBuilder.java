package com.java.carrent.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    /**
     * 把简单的集合转成有层级关系的集合
     * @param nodes
     * @param topPid
     * @return
     */
    public static List<TreeNode> builder(List<TreeNode> nodes, Integer topPid) {

        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode treeNode : nodes) {
            if (treeNode.getPid() == topPid) {
                treeNodes.add(treeNode);
            }
            for (TreeNode treeNode2 : nodes) {
                if (treeNode2.getPid() == treeNode.getId()) {
                    treeNode.getChildren().add(treeNode2);
                }
            }
        }

        return treeNodes;
    }
}
