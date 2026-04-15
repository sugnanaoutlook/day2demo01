import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        // ── ADD employees ───────────────────────────────────────────────────
        System.out.println("=== Adding Employees ===");
        Employee e1 = new Employee(0, "Alice Johnson", "Engineering", 75000);
        Employee e2 = new Employee(0, "Bob Smith",    "Marketing",   60000);
        Employee e3 = new Employee(0, "Carol White",  "HR",          55000);
        dao.addEmployee(e1);
        dao.addEmployee(e2);
        dao.addEmployee(e3);

        // ── FETCH ALL ───────────────────────────────────────────────────────
        System.out.println("\n=== All Employees ===");
        List<Employee> all = dao.getAllEmployees();
        all.forEach(System.out::println);

        // ── FETCH BY ID ─────────────────────────────────────────────────────
        System.out.println("\n=== Fetch Employee by ID (" + e1.getId() + ") ===");
        Employee fetched = dao.getEmployeeById(e1.getId());
        System.out.println(fetched);

        // ── UPDATE ──────────────────────────────────────────────────────────
        System.out.println("\n=== Updating Employee ===");
        e1.setDepartment("DevOps");
        e1.setSalary(90000);
        dao.updateEmployee(e1);

        // ── FETCH ALL AFTER UPDATE ──────────────────────────────────────────
        System.out.println("\n=== All Employees After Update ===");
        dao.getAllEmployees().forEach(System.out::println);

        // ── DELETE ──────────────────────────────────────────────────────────
        System.out.println("\n=== Deleting Employee (id=" + e3.getId() + ") ===");
        dao.deleteEmployee(e3.getId());

        // ── FINAL LIST ──────────────────────────────────────────────────────
        System.out.println("\n=== Final Employee List ===");
        dao.getAllEmployees().forEach(System.out::println);
    }
}
