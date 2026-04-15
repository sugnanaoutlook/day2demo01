import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // ── CREATE ──────────────────────────────────────────────────────────────
    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                emp.setId(keys.getInt(1));
            }
            System.out.println("Added: " + emp);

        } catch (SQLException e) {
            System.err.println("addEmployee failed: " + e.getMessage());
        }
    }

    // ── READ ALL ────────────────────────────────────────────────────────────
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("getAllEmployees failed: " + e.getMessage());
        }
        return list;
    }

    // ── READ BY ID ──────────────────────────────────────────────────────────
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("getEmployeeById failed: " + e.getMessage());
        }
        return null;
    }

    // ── UPDATE ──────────────────────────────────────────────────────────────
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());
            ps.setInt(4, emp.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Updated: " + emp);
            } else {
                System.out.println("No employee found with id=" + emp.getId());
            }
        } catch (SQLException e) {
            System.err.println("updateEmployee failed: " + e.getMessage());
        }
    }

    // ── DELETE ──────────────────────────────────────────────────────────────
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Deleted employee with id=" + id);
            } else {
                System.out.println("No employee found with id=" + id);
            }
        } catch (SQLException e) {
            System.err.println("deleteEmployee failed: " + e.getMessage());
        }
    }

    // ── helper ──────────────────────────────────────────────────────────────
    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getDouble("salary")
        );
    }
}
