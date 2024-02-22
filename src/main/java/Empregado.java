import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "emp")
public class Empregado {
    @Id
    @Column(name = "empno")
    private Float numEmp;
    private String ename;

    private Date hiredate;
    private String job;

    private Float mgr;
    @Column(name = "sal")
    private Float salario;
    private Float comm;
    private Float deptno;


    public Empregado(float numEmp, String ename, Date hiredate, String job, float mgr, float salario, float comm, float deptno) {
        this.numEmp = numEmp;
        this.ename = ename;
        this.hiredate = hiredate;
        this.job = job;
        this.mgr = mgr;
        this.salario = salario;
        this.comm = comm;
        this.deptno = deptno;
    }
    public Empregado(){

    }


    public float getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(float numEmp) {
        this.numEmp = numEmp;
    }
    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public float getMgr() {
        return mgr;
    }

    public void setMgr(float mgr) {
        this.mgr = mgr;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComm() {
        return comm;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }

    public float getDeptno() {
        return deptno;
    }

    public void setDeptno(float deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        return "Empregado{" +
                "numEmp: " + numEmp +
                ", ename: '" + ename + '\'' +
                ", hiredate: " + data.format(hiredate) +
                ", job: '" + job + '\'' +
                ", mgr: " + mgr +
                ", salario: " + salario +
                ", comm: " + comm +
                ", deptno: " + deptno +
                '}';
    }
}
