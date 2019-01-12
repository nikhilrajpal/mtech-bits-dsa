package wilp.dsa.attendance.system;

/**
 * Created by nikhilrajpal on 1/12/19.
 */
public interface IAttendanceSystemTree {

    /**
     * This function creates an {@link EmployeeNode} in the attendance tree for the given id.
     *
     * @param root       root of the attendance system tree.
     * @param employeeId employee id.
     * @return root of the attendance system tree.
     */
    EmployeeNode readEmployee(EmployeeNode root, Integer employeeId);

    /**
     * Get the total employees registered in attendance system tree.
     *
     * @param root root of the attendance system tree.
     * @return size of the tree.
     */
    Integer getHeadCount(EmployeeNode root);

    /**
     * Search for a given employeeId in attendance tree.
     *
     * @param root root of the attendance system tree.
     * @return True if employeeId exists in tree, else False.
     */
    Boolean searchId(EmployeeNode root, Integer employeeId);

    /**
     * Get how often the employee with given employeeId entered the organization.
     *
     * @param root       root of the attendance system tree.
     * @param employeeId employee id.
     * @return Count represeting number of times mployee with given employeeId entered the organization.
     */
    Integer howOften(EmployeeNode root, Integer employeeId);

    /**
     * Get the most frequent visitor from attendance system tree.
     *
     * @param root root of the attendance system tree.
     * @return most frequent visitor EmployeeNode
     */
    EmployeeNode frequentVisitor(EmployeeNode root);

    /**
     * Prints the employee id and his attCount if the employee id false in the range of [i1, i2].
     *
     * @param root root of the attendance system tree.
     * @param id1  start id of the given range.
     * @param id2  end id of the given range.
     */
    void printRangePresent(EmployeeNode root, int id1, int id2);
}
