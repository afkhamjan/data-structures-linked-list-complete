import java.util.Scanner;

//-------------------- STUDENT NODE CLASS -----------------
class StudentNode {
    int rollNo;
    String fullName;
    int meritPoints;
    String appliedDegree;

    StudentNode leftChild, rightChild;

    StudentNode(int r, String name, int points, String degree) {
        rollNo = r;
        fullName = name;
        meritPoints = points;
        appliedDegree = degree;

        leftChild = rightChild = null;
    }
}

//------------------ BST CLASS -------------------
class StudentTree {
    StudentNode root;

    //----------- ADD STUDENT --------------
    StudentNode addStudent(StudentNode root, StudentNode newStud) {
        if (root == null) return newStud;

        if (newStud.rollNo < root.rollNo)
            root.leftChild = addStudent(root.leftChild, newStud);
        else if (newStud.rollNo > root.rollNo)
            root.rightChild = addStudent(root.rightChild, newStud);

        return root;
    }

    //----------- SEARCH STUDENT -----------
    StudentNode findStudent(StudentNode root, int roll) {
        if (root == null || root.rollNo == roll)
            return root;

        if (roll < root.rollNo)
            return findStudent(root.leftChild, roll);
        else
            return findStudent(root.rightChild, roll);
    }

    //----------- DELETE STUDENT -----------
    StudentNode removeStudent(StudentNode root, int roll) {
        if (root == null) return null;

        if (roll < root.rollNo)
            root.leftChild = removeStudent(root.leftChild, roll);
        else if (roll > root.rollNo)
            root.rightChild = removeStudent(root.rightChild, roll);
        else {
            // No child
            if (root.leftChild == null && root.rightChild == null)
                return null;

                // One child
            else if (root.leftChild == null)
                return root.rightChild;
            else if (root.rightChild == null)
                return root.leftChild;

            // Two children
            StudentNode succ = getMin(root.rightChild);

            root.rollNo = succ.rollNo;
            root.fullName = succ.fullName;
            root.meritPoints = succ.meritPoints;
            root.appliedDegree = succ.appliedDegree;

            root.rightChild = removeStudent(root.rightChild, succ.rollNo);
        }
        return root;
    }

    StudentNode getMin(StudentNode node) {
        while (node.leftChild != null)
            node = node.leftChild;
        return node;
    }

    // -------- DISPLAY SORTED (inorder) --------
    void showSorted(StudentNode root) {
        if (root == null) return;

        showSorted(root.leftChild);

        System.out.println(root.rollNo + " | " + root.fullName + " | "
                + root.meritPoints + " | " + root.appliedDegree);

        showSorted(root.rightChild);
    }

    // ---------- TOP 3 STUDENTS ---------
    void showTopThree(StudentNode root, int[] count) {
        if (root == null || count[0] >= 3) return;

        showTopThree(root.rightChild, count);

        if (count[0] < 3) {
            System.out.println(root.fullName + " - Score: " + root.meritPoints);
            count[0]++;
        }

        showTopThree(root.leftChild, count);
    }

    // -------- COUNT PROGRAM STUDENTS -------
    int countDegree(StudentNode node, String degree) {
        if (node == null) return 0;

        int c = node.appliedDegree.equalsIgnoreCase(degree) ? 1 : 0;

        return c + countDegree(node.leftChild, degree) + countDegree(node.rightChild, degree);
    }

    // ------- COUNT ALL STUDENTS -------
    int totalStudents(StudentNode node) {
        if (node == null) return 0;

        return 1 + totalStudents(node.leftChild) + totalStudents(node.rightChild);
    }

    //--------- DISPLAY SCORE > 80 --------
    void showAbove80(StudentNode node) {
        if (node == null) return;

        showAbove80(node.leftChild);

        if (node.meritPoints > 80)
            System.out.println(node.rollNo + " | " + node.fullName + " | "
                    + node.meritPoints + " | " + node.appliedDegree);

        showAbove80(node.rightChild);
    }

    //--------- SUM ALL SCORES ----------
    int totalScore(StudentNode node) {
        if (node == null) return 0;

        return node.meritPoints
                + totalScore(node.leftChild)
                + totalScore(node.rightChild);
    }
}

// ---------------------- MAIN CLASS --------------------
public class StudentPortal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentTree tree = new StudentTree();

        int choice;

        do {
            System.out.println("\n---- University Admission Portal (BST) ----");
            System.out.println("1. Add student");
            System.out.println("2. Show sorted merit list");
            System.out.println("3. Find student by roll no");
            System.out.println("4. Remove withdrawn student");
            System.out.println("5. Show Top 3 Students");
            System.out.println("6. Count students in degree");
            System.out.println("7. Total number of students");
            System.out.println("8. Students scoring above 80");
            System.out.println("9. Sum of all merit points");
            System.out.println("10. Exit");

            System.out.print("Your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Roll No: ");
                    int r = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String nm = sc.nextLine();

                    System.out.print("Merit Points: ");
                    int points = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Degree Applied: ");
                    String deg = sc.nextLine();

                    tree.root = tree.addStudent(tree.root,
                            new StudentNode(r, nm, points, deg));

                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.println("\n--- Sorted Merit List ---");
                    tree.showSorted(tree.root);
                    break;

                case 3:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();

                    StudentNode res = tree.findStudent(tree.root, roll);

                    if (res != null)
                        System.out.println("FOUND: " + res.fullName + ", Score: " + res.meritPoints);
                    else
                        System.out.println("Student not found.");
                    break;

                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    int del = sc.nextInt();

                    tree.root = tree.removeStudent(tree.root, del);
                    System.out.println("If student existed, record removed.");
                    break;

                case 5:
                    System.out.println("\n--- Top 3 Students ---");
                    tree.showTopThree(tree.root, new int[]{0});
                    break;

                case 6:
                    sc.nextLine();
                    System.out.print("Enter Degree Name: ");
                    String d = sc.nextLine();

                    System.out.println("Students Count = " + tree.countDegree(tree.root, d));
                    break;

                case 7:
                    System.out.println("Total Students = " + tree.totalStudents(tree.root));
                    break;

                case 8:
                    System.out.println("\n--- Students > 80 Score ---");
                    tree.showAbove80(tree.root);
                    break;

                case 9:
                    System.out.println("Total Merit Sum = " + tree.totalScore(tree.root));
                    break;

                case 10:
                    System.out.println("Closing System...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 10);
    }
}
