import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiMain {
    static List<Employee> employees = new ArrayList<Employee>();
    static{
        employees.add(new Employee("Luffy","Monkey",500000.00, Arrays.asList("Project1","Project2")));
        employees.add(new Employee("Tanjiro","Kamado",120000.00,Arrays.asList("Project2","Project4")));
        employees.add(new Employee("Light","Akami",34000.00,Arrays.asList("Project3","Project1")));
    }
    public static void main(String[] args) {

        //forEach - Terminal Operation
        //employees.stream().forEach(employee -> System.out.println(employee));

        //Map - Intermediate Operation - Increasing Salary by 10%
        List<Employee> IncreasedSalary = employees.stream().map(employee -> new Employee(employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary() * 1.10,
                employee.getProjects())).collect(Collectors.toList());

        //System.out.println(IncreasedSalary);

        //Map
        List<List<String>> projects = employees.stream().map(employee -> employee.getProjects()).collect(Collectors.toList());
        System.out.println(projects);

        //filter - Intermediate
        List<Employee> filterEmp = employees.stream().filter(employee -> employee.getSalary() > 50000.00)
                .map(employee -> new Employee(employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects())).collect(Collectors.toList());

        //System.out.println(filterEmp);

        //FindFirst - Terminal
        Employee first = employees.stream().filter(employee -> employee.getSalary() > 50000.00)
                .map(employee -> new Employee(employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects())).findFirst().orElse(null);
        //System.out.println(first);

        //flatMap
        String allProjects = employees.stream().map(employee -> employee.getProjects())
                .flatMap(strings -> strings.stream()).collect(Collectors.joining(","));
        //System.out.println(allProjects);

        //Short Circuit Operations
        List<Employee> ShortCircuit = employees.stream().skip(1).limit(1).collect(Collectors.toList());
        //System.out.println(ShortCircuit);

        //Finite Data
        //Stream.generate(Math::random).limit(5).forEach(value -> System.out.println(value));

        //Sorting
        List<Employee> SortedData =
                employees.stream().sorted((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName())).collect(Collectors.toList());
        //System.out.println(SortedData);

        //Min or Max
        Employee employee1 = employees.stream().max(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new);
        //System.out.println(employee1);

        //reduce
        Double totalSalary =
                employees.stream().map(employee -> employee.getSalary()).reduce(0.0, Double::sum);
        //System.out.println(totalSalary);


    }
}
