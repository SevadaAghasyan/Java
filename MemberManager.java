import java.util.ArrayList;
import java.util.List;

public class MemberManager {
    Member root;

    MemberManager() {
        root = null;
    }

    public void sortMembersByStatus() {
        List<Member> memberList = new ArrayList<>();
        inorderTraversal(root, memberList);
        Member[] memberArray = memberList.toArray(new Member[0]);
        quickSort(memberArray, 0, memberArray.length - 1);
        root = buildTree(memberArray, 0, memberArray.length - 1);
    }

    private void inorderTraversal(Member node, List<Member> memberList) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, memberList);
        memberList.add(node);
        inorderTraversal(node.right, memberList);
    }

    private void quickSort(Member[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Member[] arr, int low, int high) {
        int pivot = arr[high].getStatus();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].getStatus() < pivot) {
                i++;
                Member temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Member temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    private Member buildTree(Member[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Member node = arr[mid];
        node.left = buildTree(arr, start, mid - 1);
        node.right = buildTree(arr, mid + 1, end);
        return node;
    }

    // Insert method
    void addMember(String id, String name, int status, String activity) {
        root = addMemberRec(root, id, name, status, activity);
    }

    // A recursive function to insert a new value in BST
    Member addMemberRec(Member root, String id, String name, int status,
            String activity) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Member(id, name, status, activity);
            return root;
        }
        // Otherwise, recur down the tree
        if (root.id.compareTo(id) < 0)
            root.left = addMemberRec(root.left, id, name, status, activity);
        else if (root.id.compareTo(id) > 0)
            root.right = addMemberRec(root.right, id, name, status, activity);
        // return the (unchanged) node pointer
        return root;
    }

    void removeMember(String id) {
        root = removeMemberRec(root, id);
    }

    Member removeMemberRec(Member root, String id) {
        if (root == null)
            return root;

        // Traverse the tree
        if (root.id.compareTo(id) < 0)
            root.left = removeMemberRec(root.left, root.id);
        else if (root.id.compareTo(id) > 0)
            root.right = removeMemberRec(root.right, root.id);
        else {
            // Node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Node with two children: Get the inorder successor (smallest in the right
            // subtree)
            root.id = minValue(root.right);

            // Delete the inorder successor
            root.right = removeMemberRec(root.right, root.id);
        }

        return root;
    }

    String minValue(Member root) {
        String minv = root.id;
        while (root.left != null) {
            minv = root.left.id;
            root = root.left;
        }
        return minv;

    }

    Member searchMember(String id) {
        return searchMemberRec(root, id);
    }

    Member searchMemberRec(Member root, String id) {
        if (root == null)
            return null;
        if (id.equals(root.id))
            return root;
        else if (root.id.compareTo(id) < 0)
            return searchMemberRec(root.left, id);
        else
            return searchMemberRec(root.right, id);
    }

    void printTree() {
        printTreeRec(root, 0);
    }

    void printTreeRec(Member node, int indent) {
        if (node == null)
            return;

        // Increase the indent for each level in the tree
        indent += 10;

        // Process right child first
        printTreeRec(node.right, indent);

        // Print current node after space count
        System.out.print("\n");
        for (int i = 10; i < indent; i++)
            System.out.print(" ");
        System.out.print(node.toString() + "\n");

        // Process left child
        printTreeRec(node.left, indent);
    }
}