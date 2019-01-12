package wilp.dsa.attendance.system;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main menu class to recieve inputs of attendance system.
 * <p>
 * Created by nikhilrajpal on 1/12/19.
 */
public class EmployeeAttendance {

    public static void main(String[] args) {
        EmployeeAttendance attendance = new EmployeeAttendance(new EmpBT());
        attendance.startAttendanceSystem();
    }

    private IAttendanceSystemTree attendanceSystemTree;

    public EmployeeAttendance(IAttendanceSystemTree attendanceSystemTree) {
        this.attendanceSystemTree = attendanceSystemTree;
    }

    public void startAttendanceSystem() {
        Scanner scanner = new Scanner(System.in);
        MenuScreen menuScreen = new MenuScreen();
        menuScreen.getMenuScreen();
        EmployeeNode root = null;
        while (scanner.hasNext()) {
            String menuOption = scanner.nextLine();
            Integer menuOptionId = Integer.parseInt(menuOption);
            MenuOption option = MenuOption.getMenuOptionById(menuOptionId);
            switch (option) {
                case LOAD_INPUT_FILE:
                    System.out.println("Enter the file path to load input.txt");
                    String filePath = scanner.nextLine();
                    if (!Files.exists(Paths.get(filePath))) {
                        try {
                            root = createTree(Paths.get(filePath));
                        } catch (IOException e) {
                            System.out.println("No such file exists on the path : " + filePath);
                        }
                    } else {
                        System.out.println("No such file exists on the path : " + filePath);
                    }
                    break;
                case GET_HEAD_COUNT:
                    System.out.println(attendanceSystemTree.getHeadCount(root));
                    break;
                case SEARCH_EMPLOYEE:
                    System.out.println("Enter the employee id to search ..");
                    System.out.println(attendanceSystemTree.searchId(root, scanner.nextInt()));
                    break;
                case HOW_OFTEN_EMPLOYEE:
                    System.out.println("Enter the employee id to check ..");
                    System.out.println(attendanceSystemTree.howOften(root, scanner.nextInt()));
                    break;
                case FREQUENT_VISITOR_EMPLOYEE:
                    System.out.println("Frequent Visitor : " + attendanceSystemTree.frequentVisitor(root));
                    break;
                case PRINT_EMPLOYEE_RANGE:
                    System.out.println("Frequent Visitor : " + attendanceSystemTree.frequentVisitor(root));
                    break;
            }
        }
    }

    private EmployeeNode createTree(Path filePath) throws IOException {
        EmployeeNode root = null;
        List<String> inputLines = Files.readAllLines(filePath, Charset.defaultCharset());
        for (String inputLine : inputLines) {
            root = attendanceSystemTree.readEmployee(root, Integer.parseInt(inputLine.trim()));
        }
        return root;
    }
}

class MenuScreen {
    List<MenuOption> menuOptionList;

    public MenuScreen() {
        this.menuOptionList = Arrays.asList(MenuOption.values());
    }

    public void getMenuScreen() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("##################### Welcome to the WILP attendance System #####################").append("\n");
        stringBuilder.append("Here is the menu, please make your choice, choose 1,2,3, 4 etc. :").append("\n");
        for (MenuOption option : menuOptionList) {
            stringBuilder.append(option.getMenuOptionId() + " : " + option.name() + "(" + option.getDescription() + ")").append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}

enum MenuOption {
    LOAD_INPUT_FILE("To load the attendance input file", 1),
    GET_HEAD_COUNT("Total number of employees registered in attendance system", 2),
    SEARCH_EMPLOYEE("Search for a given employee with employee id.", 3),
    HOW_OFTEN_EMPLOYEE("To know the number of times the employee entered the organization", 4),
    FREQUENT_VISITOR_EMPLOYEE("To know the frequent visitor employee", 5),
    PRINT_EMPLOYEE_RANGE("To print the ids in the range id1 to id2 and how often they have entered the organization", 6);

    private String description;
    private Integer menuOptionId;

    MenuOption(String description, Integer menuOptionId) {
        this.description = description;
        this.menuOptionId = menuOptionId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMenuOptionId() {
        return menuOptionId;
    }

    public static MenuOption getMenuOptionById(Integer menuOptId) {
        for (MenuOption option : MenuOption.values()) {
            if (menuOptId.equals(option.getMenuOptionId())) {
                return option;
            }
        }

        throw new IllegalArgumentException("Illegal input found, please give valid input");
    }
}
