import org.hibernate.exception.ConstraintViolationException;

import javax.xml.crypto.Data;
import java.nio.channels.FileLock;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        EmpregadoDAO empregadoDAO = new EmpregadoDAO();
//        empregadoDAO.inserir(new Empregado(7882, "SAM", new Date(1990, 1, 10), "MANAGER", 7698, 234, 6890, 20));
//        empregadoDAO.alterarNome("MODOLO", 7888);
//        empregadoDAO.alterarSalario(7654, 2450);
//        empregadoDAO.remover(7882);
//        empregadoDAO.selecionar();
//        empregadoDAO.buscarPorNum(7888);
//        empregadoDAO.buscarPorTrabalho("MANAGER");
        Scanner entrada = new Scanner(System.in);
        boolean erro = false;
        int opcao = -1;
        do {
            try {
                System.out.print("""
                        ---Menu---
                        [1] Inserir
                        [2] Alterar nome
                        [3] Alterar salario
                        [4] Remover
                        [5] buscar tudo
                        [6] buscar por id
                        [7] buscar por trabalho
                        [0] Sair
                        Digite uma das opções:
                        """);
                opcao = entrada.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("ERRO! Digite uma das opções sugeridas " + ime.getStackTrace());
                entrada.nextLine();
                erro = true;
            }
            //Implementando o metodo inserir
            if (opcao == 1) {
                do {
                    try {
                        System.out.println("Digite o numEmp: ");
                        Float numep = entrada.nextFloat();
                        System.out.println("Digite o nome: ");
                        String nome = entrada.next();
                        System.out.println("Digite o ano de contratação: ");
                        int ano = entrada.nextInt();
                        if (ano<1900){
                            System.out.println("ERRO! Digite um ano valido");
                            erro = true;
                        }
                        System.out.println("Digite mes de contratação: ");
                        int mes = entrada.nextInt();
                        System.out.println("Digite o dia de contratação: ");
                        int dia = entrada.nextInt();
                        Date hiredate = new Date(ano, mes, dia);
                        System.out.println("Digite o cargo do trabalhador: ");
                        String job = entrada.next();
                        System.out.println("Digite o mgr do trabalhador: ");
                        Float mgr = entrada.nextFloat();
                        System.out.println("Digite o salario: ");
                        Float salario = entrada.nextFloat();
                        System.out.println("Digite a comissao: ");
                        Float comm = entrada.nextFloat();
                        System.out.println("Digite a chave segundaria do departamento: ");
                        Float deptno = entrada.nextFloat();
                        String resultado = empregadoDAO.inserir(new Empregado(numep, nome, hiredate, job, mgr, salario, comm, deptno));
                        System.out.println(resultado);
                    } catch (InputMismatchException ime) {
                        System.out.println("ERRO! Digite uma das opções sugeridas " + ime.getStackTrace());
                        entrada.nextLine();
                        erro = true;
                    } catch(ConstraintViolationException cve){
                        System.out.println("ERRO DE FK!! Digite uma fk existente, mg ou deptno");
                        entrada.nextLine();
                        erro = true;
                    }
                }while(erro);
                //Implementando o metodo alterar nome
            } else if (opcao == 2) {
                do {
                    try {
                        System.out.println("Digite o numEmp do empregado que deseja alterar: ");
                        Float id = entrada.nextFloat();
                        System.out.println("Digite o nome novo do empregado: ");
                        String nome = entrada.next();
                        boolean resultado = empregadoDAO.alterarNome(nome, id);
                        if (resultado) {
                            System.out.println("Deu certo!!");
                            empregadoDAO.buscarPorNum(id); //buscar nao deu certo??>?>8349
                        } else {
                            System.out.println("ERRO!! Não foi possivel buscar o empregado.");
                        }
                        erro = false;
                    } catch (InputMismatchException ime) {
                        System.out.println("ERRO! Digite uma das opções sugeridas " + ime.getStackTrace());
                        entrada.nextLine();
                        erro = true;
                    }
                }while (erro);
                //Implementando o metodo alterar salario
            } else if (opcao == 3) {
                do {
                    try {
                        System.out.println("Digite o numEmp do empregado que deseja alterar: ");
                        Float id = entrada.nextFloat();
                        System.out.println("Digite o novo salario do empregado: ");
                        Float salarioNovo = entrada.nextFloat();
                        boolean resultado = empregadoDAO.alterarSalario(id, salarioNovo);
                        if (resultado) {
                            System.out.println("Deu certo!!");
                            empregadoDAO.buscarPorNum(id); //buscar nao deu certo??>?>8349
                        } else {
                            System.out.println("ERRO!! Não foi possivel buscar o empregado.");
                        }
                        erro = false;
                    } catch (InputMismatchException ime) {
                        System.out.println("ERRO! Digite uma das opções sugeridas " + ime.getStackTrace());
                        entrada.nextLine();
                        erro = true;
                    }
                }while (erro);
                //Implementando o metodo remover
            } else if (opcao == 4) {
                do {
                    try {
                        System.out.println("Digite o numEmp do empregado que deseja remover: ");
                        Float id = entrada.nextFloat();
                        boolean resultado = empregadoDAO.remover(id);
                        if (resultado) {
                            System.out.println("Deu certo!!");
                            empregadoDAO.buscarPorNum(id); //buscar nao deu certo??>?>8349
                        } else {
                            System.out.println("ERRO!! Não foi possivel buscar o empregado.");
                        }
                    } catch (InputMismatchException ime) {
                        System.out.println("ERRO! Digite uma das opções sugeridas " + ime.getStackTrace());
                        entrada.nextLine();
                        erro = true;
                    }
                    erro = false;
                }while (erro);
                //Implementando o metodo selecionar tudo
            } else if (opcao == 5) {
                do {
                    try {
                        boolean resultado = empregadoDAO.selecionar();
                        if (resultado) {
                            System.out.println("Deu certo!!");
                        } else {
                            System.out.println("ERRO!! Não foi possivel buscar o empregado.");
                        }
                        erro = false;
                    } catch (InputMismatchException ime) {
                        System.out.println("ERRO! Digite uma das opções sugeridas " + ime.getStackTrace());
                        entrada.nextLine();
                        erro = true;
                    }
                }while (erro);
                //Implementando o metodo buscar por numero
            } else if (opcao == 6) {
                do {
                    try {
                        System.out.println("Digite o numEmp do empregado que deseja buscar: ");
                        Float id = entrada.nextFloat();
                        boolean resultado = empregadoDAO.buscarPorNum(id);
                        if (resultado) {
                            System.out.println("Deu certo!!");
                        } else {
                            System.out.println("ERRO!! Não foi possivel buscar o empregado.");
                        }
                        erro = false;
                    } catch (InputMismatchException ime) {
                        System.out.println("ERRO! Digite uma das opções sugeridas " + ime.getStackTrace());
                        entrada.nextLine();
                        erro = true;
                    }
                }while (erro);
                //Implementando o metodo buscar por trabalho
            } else if (opcao == 7) {
                do {
                    try {
                        System.out.println("Digite o cargo do(s) empregado(s) que deseja buscar: ");
                        String job = entrada.next();
                        boolean resultado = empregadoDAO.buscarPorTrabalho(job);
                        if (resultado) {
                            System.out.println("Deu certo!!");
                        } else {
                            System.out.println("ERRO!! Não foi possivel buscar o empregado.");
                        }
                        erro = false;
                    } catch (InputMismatchException ime) {
                        System.out.println("ERRO! Digite uma das opções sugeridas " + ime.getStackTrace());
                        entrada.nextLine();
                        erro = true;
                    }
                }while (erro);
            }
        }while (opcao != 0);
        System.out.println(empregadoDAO.desconectar());
    }
}
