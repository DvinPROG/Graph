package com.david.dvinskykh.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class GNodeUtils {

    public static List<GNode> walkGraph(GNode rootNode) {
        Set<GNode> nodes = new LinkedHashSet<>();
        addChildNodes(rootNode, nodes);
        return new ArrayList<>(nodes);
    }

    private static void addChildNodes(GNode rootNode, Set<GNode> nodes) {
        nodes.add(rootNode);
        Arrays.stream(rootNode.getChildren())
                .filter(((Predicate<GNode>) nodes::contains).negate())
                .forEach(childNode -> addChildNodes(childNode, nodes));
    }

    public static List<List<GNode>> paths(GNode node) {
        return childPaths(node, new ArrayList<>());
    }

    private static List<List<GNode>> childPaths(GNode root, List<GNode> rootPath) {
        rootPath.add(root);
        GNode[] children = root.getChildren();
        List<List<GNode>> resultPaths = new ArrayList<>();
        if (children.length == 0) {
            resultPaths.add(rootPath);
            return resultPaths;
        }
        for (GNode child : children) {
            List<GNode> childPath = new ArrayList<>(rootPath);
            if (childPath.contains(child)) {
                childPath.add(child);
                resultPaths.add(childPath);
            } else {
                resultPaths.addAll(childPaths(child, childPath));
            }
        }
        return resultPaths;
    }

}
