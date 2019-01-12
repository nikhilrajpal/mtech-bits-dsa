package wilp.dsa.attendance.system;

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
        return null;
    }

    /**
     * Get the most frequent visitor from attendance system tree.
     *
     * @param root root of the attendance system tree.
     * @return most frequent visitor EmployeeNode
     */
    public EmployeeNode frequentVisitor(EmployeeNode root) {
        return null;
    }

    /**
     * Prints the employee id and his attCount if the employee id false in the range of [i1, i2].
     *
     * @param root root of the attendance system tree.
     * @param id1  start id of the given range.
     * @param id2  end id of the given range.
     */
    public void printRangePresent(EmployeeNode root, int id1, int id2) {

    }
}
