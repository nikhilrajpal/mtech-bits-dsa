package wilp.dsa.attendance.system;

/**
 * Class to describes the node in attendance system tree.
 * Created by nikhilrajpal on 1/12/19.
 */
public class EmployeeNode {

    private static final Integer START_COUNT_FOR_ATTENDANCE = 1;

    /**
     * Id of the employee.
     */
    private Integer empId;

    /**
     * Attendance count of the employee.
     * First  time  an  employee  enters  into  the  office, the corresponding id is set to 1.
     * From then onwards, each time an employee leaves the office premises for tea break or lunch break, and enters back this id is incremented
     */
    private Integer attCount;

    /**
     * Left and right references to the node in tree.
     */
    private EmployeeNode left;
    private EmployeeNode right;

    /**
     * Constructor to initialize the {@link EmployeeNode}
     *
     * @param empId employee id.
     */
    public EmployeeNode(Integer empId) {
        this.empId = empId;
        this.attCount = START_COUNT_FOR_ATTENDANCE;
    }

    public void incrementAttendanceCount() {
        attCount++;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getAttCount() {
        return attCount;
    }

    public void setAttCount(Integer attCount) {
        this.attCount = attCount;
    }

    public EmployeeNode getLeft() {
        return left;
    }

    public void setLeft(EmployeeNode left) {
        this.left = left;
    }

    public EmployeeNode getRight() {
        return right;
    }

    public void setRight(EmployeeNode right) {
        this.right = right;
    }

}
