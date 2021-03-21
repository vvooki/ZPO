import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO{

    DBConnector dbconnector = new DBConnector("h2", "~/test", "sa", "");

    @Override
    public Optional<Employee> findOne(Integer id) {
        Optional<Employee> result = Optional.empty();

        String sql = "SELECT * FROM EMPLOYEE WHERE id="+id;
        try{
            ResultSet resultSet = dbconnector.getResultSet(sql);
            if(resultSet.next()) {
                result = Optional.of(new Employee(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getFloat(4)));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Optional<Employee> findByName(String name) {
        Optional<Employee> result = Optional.empty();

        String sql = "SELECT * FROM EMPLOYEE WHERE name LIKE '"+name+"%'";
        try{
            ResultSet resultSet = dbconnector.getResultSet(sql);
            if(resultSet.next()) {
                result = Optional.of(new Employee(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getFloat(4)));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Employee> findAll() {
        List<Employee> result = new ArrayList<>();

        String sql = "SELECT * FROM EMPLOYEE";
        try{
            ResultSet resultSet = dbconnector.getResultSet(sql);
            while(resultSet.next()) {
                result.add(new Employee(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getFloat(4)));

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    @Override
    public void delete(Employee employee) {
        String sql = "DELETE FROM EMPLOYEE WHERE id="+employee.getId();
        try{
            ResultSet resultSet = dbconnector.getResultSet(sql);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Employee employee) {
        String check = "SELECT * FROM EMPLOYEE WHERE id="+employee.getId();

        try{
            ResultSet resultSet = dbconnector.getResultSet(check);
            if(resultSet.next()) {
                String sql1 = "UPDATE EMPLOYEE SET name= '" + employee.getName() + "', email= '" + employee.getEmail()
                        +"', salary= " + employee.getSalary() + "WHERE id="+employee.getId();
                ResultSet rs1 = dbconnector.getResultSet(sql1);
            } else {
                String sql2 = "INSERT INTO EMPLOYEE VALUES("+employee.getId()+", '"+employee.getName()+"', '"
                        +employee.getEmail()+"',"+employee.getSalary()+")";
                ResultSet rs2 = dbconnector.getResultSet(sql2);

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
