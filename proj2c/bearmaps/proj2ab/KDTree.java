package bearmaps.proj2ab;


import java.util.Collections;
import java.util.List;

public class KdTree implements PointSet{
    private Node root;
    private int size;
    private static final boolean HORIZONTAL = false;
    public KdTree(List<Point> points) {
        Collections.shuffle(points);
        for (Point point : points) {
            root = insert(point, HORIZONTAL, root);
        }
/*        treeConstructor(points);
        size = points.size();*/
    }
    private Node insert(Point p, boolean flag, Node n) {
        if (n == null) {
            return new Node(p, flag);
        }
        if (p.equals(n.val)) {
            return n;
        }
        int cmp = comparePoints(p, n.val, flag);
        if (cmp < 0) {
            n.left = insert(p, !flag, n.left);
        } else {
            n.right = insert(p, !flag, n.right);
        }
        return n;
    }

    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root).val;
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        double bestDis = Point.distance(goal, best.val);
        double dis = Point.distance(goal, n.val);
        if (dis < bestDis) {
            best = n;
        }
        Node goodSide;
        Node badSide;

        int cmp = comparePoints(goal, n.val, n.flag);
        if (cmp < 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = nearest(goodSide, goal, best);
        if (isWorthLooking(n, goal, best.val)) {
            best = nearest(badSide, goal, best);
        }
        return best;
    }
    private boolean isWorthLooking(Node node, Point target, Point best) {
        double distToBest = Point.distance(best, target);
        double distToBad;
        if (!node.flag) {
            distToBad = Point.distance(new Point(node.val.getX(), target.getY()), target);
        } else {
            distToBad = Point.distance(new Point(target.getX(), node.val.getY()), target);
        }
        return Double.compare(distToBad, distToBest) < 0;
    }
    private int comparePoints(Point a, Point b, boolean splitDim) {
        if (splitDim == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    private class Node {
        Point val;
        Node left;
        Node right;
        boolean flag;
        public Node(Point p, Node l, Node r, boolean f) {
            val = p;
            left = l;
            right = r;
            flag = f;
        }
        public Node(Point p, boolean f) {
            val = p;
            left = null;
            right = null;
            flag = f;
        }
    }
    private void add(Point p, boolean flag) {
        Node ptr = root;
        if (root.val == null) {
            root.val = p;
        }
        if (ptr.val.equals(p)) {
            return;
        }
        while (true) {
            // if flag is true, we compare the x value;
            if (flag) {
                if (p.getX() < ptr.val.getX()) {
                    if (ptr.left == null) {
                        ptr.left = new Node(p, null, null, flag);
                        break;
                    } else {
                        ptr = ptr.left;
                        flag = !flag;
                    }
                } else {
                    if (ptr.right == null) {
                        ptr.right = new Node(p, null, null, flag);
                        break;
                    } else {
                        ptr = ptr.right;
                        flag = !flag;
                    }
                }
            }
            // if flag is false, we compare the y value;
            else {
                if (p.getY() < ptr.val.getY()) {
                    if (ptr.left == null) {
                        ptr.left = new Node(p, null, null, flag);
                        break;
                    } else {
                        ptr = ptr.left;
                        flag = !flag;
                    }
                } else {
                    if (ptr.right == null) {
                        ptr.right = new Node(p, null, null, flag);
                        break;
                    } else {
                        ptr = ptr.right;
                        flag = !flag;
                    }
                }
            }
        }
    }
    private void treeConstructor(List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            Point pointToAdd = points.get(i);
            add(pointToAdd, true);
        }
    }
}


