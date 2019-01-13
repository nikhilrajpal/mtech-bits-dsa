package wilp.dsa.attendance.system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikhilrajpal on 1/12/19.
 */
public class EmpBT implements IAttendanceSystemTree {

    /**
     * This function creates an {@link EmployeeNode} in the attendance tree for the given id.
     *
     * @param root       root of the attendance system tree.
     * @param employeeId employee id.
     */
    public EmployeeNode readEmployee(EmployeeNode root, Integer employeeId) {
        if (root == null) {
            return new EmployeeNode(employeeId);
        }

        if (employeeId < root.getEmpId()) {
            // insert in left subtree.
            root.setLeft(readEmployee(root.getLeft(), employeeId));
        } else if (employeeId > root.getEmpId()) {
            // insert in right subtree.
            root.setRight(readEmployee(root.getRight(), employeeId));
        } else {
            // root employeeid = employeeid
            root.incrementAttendanceCount();
        }
        return root;
    }

    /**
     * Get the total employees registered in attendance system tree.
     *
     * @param root root of the attendance system tree.
     * @return size of the tree.
     */
    public Integer getHeadCount(EmployeeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getHeadCount(root.getLeft()) + getHeadCount(root.getRight());
    }

    /**
     * Search for a given employeeId in attendance tree.
     *
     * @param root       root of the attendance system tree.
     * @param employeeId
     * @return True if employeeId exists in tree, else False.
     */
    public Boolean searchId(EmployeeNode root, Integer employeeId) {
        return searchInternal(root, employeeId) != null;
    }

    private EmployeeNode searchInternal(EmployeeNode root, Integer employeeId) {
        if (root != null) {
            if (root.getEmpId().equals(employeeId)) {
                return root;
            } else if (root.getLeft() != null && root.getLeft().getEmpId() < employeeId) {
                return searchInternal(root.getLeft(), employeeId);
            } else if (root.getRight() != null && root.getRight().getEmpId() >= employeeId) {
                return searchInternal(root.getRight(), employeeId);
            }
        }
        return null;
    }

    /**
     * Get how often the employee with given employeeId entered the organization.
     *
     * @param root       root of the attendance system tree.
     * @param employeeId employee id.
     * @return Count represeting number of times mployee with given employeeId entered the organization.
     */
    public Integer howOften(EmployeeNode root, Integer employeeId) {
        if(root == null){
            return null;
        }

        if(employeeId == root.getEmpId()){
            int isEven = root.getAttCount()%2;
            return (isEven == 0)?(root.getAttCount()/2):((root.getAttCount()/2)+1) ;
        } else if (employeeId < root.getEmpId()){
            return howOften(root.getLeft(), employeeId);
        } else {
            return howOften(root.getRight(), employeeId);
        }
    }

    /**
     * Get the most frequent visitor from attendance system tree.
     *
     * @param root root of the attendance system tree.
     * @return most frequent visitor EmployeeNode
     */
    public EmployeeNode frequentVisitor(EmployeeNode root) {
        if (root == null)
            return null;

        EmployeeNode maxNode = root;
        EmployeeNode maxNodeLeft = frequentVisitor(root.getLeft());
        EmployeeNode maxNodeRight = frequentVisitor(root.getRight());

        if ((maxNodeLeft != null) && (maxNodeLeft.getAttCount() > root.getAttCount()))
            maxNode = maxNodeLeft;
        if ((maxNodeRight != null) && (maxNodeRight.getAttCount() > root.getAttCount()))
            maxNode = maxNodeRight;
        return maxNode;
    }

    /**
     * Prints the employee id and his attCount if the employee id false in the range of [i1, i2].
     *
     * @param root root of the attendance system tree.
     * @param id1  start id of the given range.
     * @param id2  end id of the given range.
     */
    public void printRangePresent(EmployeeNode root, int id1, int id2) {

        List<EmployeeNode> employeesInRange = new ArrayList<>();
        employeesInRange = getEmployeesInRange(root, employeesInRange, id1, id2);

        // Build file text
        StringBuilder strBuilder = new StringBuilder();
        for (EmployeeNode employee:employeesInRange) {
            strBuilder.append(employee.getEmpId() + ", " + employee.getAttCount() + "\n");
        }

        // Write file
        try{
            String fileContents = strBuilder.toString();
            System.out.println("Employees in range:\n" + fileContents);
            Files.write(Paths.get("output.txt"), fileContents.getBytes());
        } catch (IOException ioException) {
            System.out.println("Exception occurred while writing file : " +  ioException.toString());
        }


    }

    /**
     * This function returns a list of employee nodes which lie within the specified range.
     *
     * @param root root of the attendance system tree.
     * @param employeesInRange list of employee nodes used for recursive calls
     * @param id1 start id of the given range.
     * @param id2 end id of the given range.
     * @return list of employee nodes which lie within the range
     */
    private List<EmployeeNode> getEmployeesInRange(EmployeeNode root, List<EmployeeNode> employeesInRange, int id1, int id2) {

        // base case
        if (root == null) {
            return employeesInRange;
        }

        // Traverse left sub-tree first
        if (id1 < root.getEmpId()) {
            getEmployeesInRange(root.getLeft(), employeesInRange, id1, id2);
        }

        if (id1 <= root.getEmpId() && id2 >= root.getEmpId()) {
            employeesInRange.add(root);
        }

        // Traverse right sub-tree
        if (id2 > root.getEmpId()) {
            getEmployeesInRange(root.getRight(), employeesInRange, id1, id2);
        }

        return employeesInRange;
    }
}
