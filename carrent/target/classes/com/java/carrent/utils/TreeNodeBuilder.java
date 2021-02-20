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
//        遍历树节点
        for (TreeNode treeNode1 : nodes) {
//            如果树节点的父id等于1，说明这是要显示的根据节点
            if (treeNode1.getPid() == topPid) {
                treeNodes.add(treeNode1);
            }
            for (TreeNode treeNode2 : nodes) {
//                依次判断每个树节点的父id是否等于节点的id，如果有相等的匹配项，说明treeNode2这是一个子节点
                if (treeNode2.getPid() == treeNode1.getId()) {
//                  添加子节点
                    treeNode1.getChildren().add(treeNode2);
                }
            }
        }

        for (TreeNode node : treeNodes) {
            System.out.println(node.toString());
        }

        return treeNodes;
    }
}
