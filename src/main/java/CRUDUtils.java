
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

  private static final String INSERT_STUDENT = "INSERT INTO students(name, surname, curse_name) VALUES (?, ?, ?);";
  private static final String UPDATE_STUDENT = "UPDATE students SET curse_name = ? WHERE id = ?";
  private static final String DELETE_STUDENT = "DELETE FROM students WHERE id = ?";

  public static List<Student> getStudentData(String query) {
    List<Student> students = new ArrayList<>();

    try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String curse_name = rs.getString("curse_name");

        students.add(new Student(id, name, surname, curse_name));

      }

    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return students;
  }

  public static List<Student> saveStudent(Student student) {
    List<Student> students = new ArrayList<>();

    try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

      preparedStatement.setString(1, student.getName());
      preparedStatement.setString(2, student.getSurname());
      preparedStatement.setString(3, student.getCurs_name());
      preparedStatement.executeUpdate();

      PreparedStatement allStudents = connection.prepareStatement("SELECT * FROM students");
      ResultSet rs = allStudents.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String curse_name = rs.getString("curse_name");

        students.add(new Student(id, name, surname, curse_name));

      }
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return students;
  }

  public static List<Student> updateStudent(int studentId, String courseName) {
    List<Student> updateStudents = new ArrayList<>();

    try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

      preparedStatement.setString(1, courseName);
      preparedStatement.setInt(2, studentId);
      preparedStatement.executeUpdate();

      PreparedStatement allStudents = connection.prepareStatement("SELECT * FROM students");
      ResultSet rs = allStudents.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String curse_name = rs.getString("curse_name");

        updateStudents.add(new Student(id, name, surname, curse_name));

      }
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return updateStudents;
  }

  public static List<Student> deleteStudents(int studentId) {
    List<Student> updateStudents = new ArrayList<>();
    try (Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {

      preparedStatement.setInt(1, studentId);
      preparedStatement.executeUpdate();

      PreparedStatement allStudents = connection.prepareStatement("SELECT * FROM students");
      ResultSet rs = allStudents.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String curse_name = rs.getString("curse_name");

        updateStudents.add(new Student(id, name, surname, curse_name));
      }
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return updateStudents;
  }
}
