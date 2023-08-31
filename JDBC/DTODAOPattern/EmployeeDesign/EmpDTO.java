package DTODAOPattern.EmployeeDesign;

public class EmpDTO {
    private int id;
    private String name;
    private String desgn;
    private double sal;
    public String getDesgn() {
        return desgn;
    }

    public double getSal() {
        return sal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDesgn(String desgn) {
        this.desgn = desgn;
    }
    public void setSal(double sal) {
        this.sal = sal;
    }




}
