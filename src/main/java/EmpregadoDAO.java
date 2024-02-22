import jakarta.persistence.*;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;
import java.util.List;

import java.util.Date;

public class EmpregadoDAO extends Empregado {
    private static final EntityManagerFactory emf=
            Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

//    public boolean inserir(float numEmp, String ename, Date hiredate, String job, float mgr, float salario, float comm, float deptno){
//        try {
//            Empregado empregado = new Empregado();
//            empregado.setNumEmp(numEmp);
//            empregado.setEname(ename);
//            empregado.setHiredate(hiredate);
//            empregado.setJob(job);
//            empregado.setMgr(mgr);
//            empregado.setSalario(salario);
//            empregado.setComm(comm);
//            empregado.setDeptno(deptno);
//
//            em.getTransaction().begin();
//            em.persist(empregado);
//            em.getTransaction().commit();
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }finally {
//            em.close();
//            emf.close();
//        }
//    }
public String inserir(Empregado empregado){
    try {
        em.getTransaction().begin();
        em.persist(empregado);
        em.getTransaction().commit();
        return "Deu certo!";
    } catch (RollbackException rbe){
        rbe.printStackTrace();
        return "Não é possivel realizar a conexão, ocorreu um problema durante a transação";
    } catch (EntityExistsException eee) {
        eee.printStackTrace();
        return "Não é possivel realizar a operação, erro ao inserir um valor ja existente";
    }
}
    //alterar o nome
    public String alterarNome(String ename, float numemp){
        try{
        Empregado empregado;
        em.getTransaction().begin();
        empregado = em.find(Empregado.class, numemp);
        empregado.setEname(ename);
        em.getTransaction().commit();
        } catch (RollbackException rbe){
            rbe.printStackTrace();
            return "Não é possivel realizar a conexão, ocorreu um problema durante a transação";
        } catch (EntityExistsException eee) {
            eee.printStackTrace();
            return "Não é possivel realizar a operação, erro ao inserir um valor ja existente";
    }
    }
    public boolean alterarSalario(float numEmp,float salario){
        try{
            Empregado empregado = new Empregado();
            em.getTransaction().begin();
            empregado = em.find(Empregado.class, numEmp);
            empregado.setSalario(salario);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean remover(float numEmp){
    try {
        em.getTransaction().begin();
        Empregado empregado = em.find(Empregado.class, numEmp);
        em.remove(empregado);
        em.getTransaction().commit();
    }catch (Exception e){
        e.printStackTrace();
        return false;
    }
    return true;
    }
    public boolean selecionar (){
    try {
        Query query = em.createQuery("SELECT emp FROM Empregado emp ");

        List<Empregado> empregados = query.getResultList();
        for (Empregado emp : empregados) {
            System.out.println(emp);
        }

    }catch (Exception e){
        e.printStackTrace();
        return false;
    }
    return true;
    }
    public boolean buscarPorNum (float id){
        try {
            Query query = em.createQuery("SELECT emp FROM Empregado emp WHERE emp.numEmp = :id ");
            query.setParameter("id", id);
            List<Empregado> empregados = query.getResultList();
            for (Empregado emp : empregados) {
                System.out.println(emp);
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean buscarPorTrabalho (String job){
        try {
            Query query = em.createQuery("SELECT emp FROM Empregado emp WHERE emp.job = :trabalho ");
            query.setParameter("trabalho", job);
            List<Empregado> empregados = query.getResultList();
            for (Empregado emp : empregados) {
                System.out.println(emp);
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean desconectar(){
    try {
        em.close();
        emf.close();
        return true;
    }catch (Exception e){
        return false;
    }
    }

}
