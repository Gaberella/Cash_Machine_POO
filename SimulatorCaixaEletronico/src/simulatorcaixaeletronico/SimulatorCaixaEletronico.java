
package simulatorcaixaeletronico;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimulatorCaixaEletronico {

    public static void main(String[] args) {
        Scanner ler  = new Scanner(System.in);
        String nconta,senhaconta;
        char continuar;
        
        do{
            System.out.println("Digite o número da conta:");
            nconta = ler.nextLine();

            System.out.println("Digite a senha da conta:");
            senhaconta = ler.nextLine();

           int op;


            try {
                Conta cc = ContaDAO.contaUsuario(nconta, senhaconta);

                if(cc != null)
                {   
                    System.out.println("Conta verificada!");

                    System.out.println("\n================================== Caixa eletronico ==========================\n");
                    System.out.println("Para sacar, digite 1 \n");
                    System.out.println("Para deposito,digite 2 \n");
                    System.out.println("Para consultar saldo,digte 3 \n");
                    System.out.println("Para encerrar,digite 4 ");
                    op = ler.nextInt();

                    switch(op)
                    {
                        case 1:
                            int qtd = 0; 
                            do{
                                System.out.println("Digite a quantidade que deseja sacar:");
                                qtd = ler.nextInt();

                                if(qtd <=0 || cc.getSaldo() < qtd)
                                {
                                    System.out.println("Quantidade inválida!");
                                }
                            }while(qtd <=0 || cc.getSaldo() < qtd);

                            sacar(cc, qtd);
                            break;
                        case 2:
                            ler.nextLine();
                            Conta dest = null;

                            System.out.println("Digite o número da conta: ");
                            nconta = ler.nextLine();

                            System.out.println("Digite a agencia da conta: ");
                            senhaconta = ler.nextLine();

                            dest = ContaDAO.contaUsuario(nconta, senhaconta);

                            if(dest != null){
                                int quantidadeDeposito = 0;
                                do{
                                    System.out.println("Digite a quantidade que deve ser depositada: ");
                                    quantidadeDeposito = ler.nextInt();
                                
                                    if(quantidadeDeposito <= 0){
                                        System.out.println("Quantidade inválida!");
                                    }
                                }while(quantidadeDeposito <= 0);

                                deposito(dest, quantidadeDeposito);
                            }
                            else{
                                System.out.println("Conta inválida!");
                            }

                            break;
                        case 3:
                            ConsultarSaldo(cc);
                            break;
                    }

                    ler.nextLine();

                }else{
                    System.out.println("Conta não existe!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SimulatorCaixaEletronico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Deseja continuar? (S/N)");
            continuar = Character.toUpperCase(ler.nextLine().charAt(0)); //O usuário digita um Char e esse char é transformado em maiúsculo e armazenado em 'continuar'
        }while(continuar == 'S');
        
        System.out.println("Programa finalizado:");
        
    }
    
    public static void deposito(Conta dest, int quantidadeDeposito)
    {
        try {
            ContaDAO.RealizaDeposito(dest.getId(), quantidadeDeposito); //Realizando o depósito
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sacar(Conta c, int quantidade)
    {
        int nota2 = 0,nota5 = 0,nota10 = 0,nota20 = 0,nota50 = 0,nota100 = 0, qtdAux = 0;
        qtdAux = quantidade; //Armazena a quantidade que deve ser sacada
        
        if(quantidade >= 100)
        {
            nota100 = quantidade / 100; //Calculando o número de notas de 100
            quantidade = quantidade % 100; //Calculando o resto depois de obter o número de notas de 100
        }
        if(quantidade >= 50)
        {
           nota50 =  quantidade / 50; //Calculando o número de notas de 50
           quantidade = quantidade % 50; //Calculando o resto depois de obter o número de notas de 50
        }
        if(quantidade >=20)
        {
            nota20 = quantidade / 20; //Calculando o número de notas de 20
            quantidade = quantidade % 20; //Calculando o resto depois de obter o número de notas de 20
        }
        if(quantidade >= 10)
        {
            nota10 = quantidade / 10; //Calculando o número de notas de 10
            quantidade = quantidade % 10; //Calculando o resto depois de obter o número de notas 10
        }
        if(quantidade >= 5)
        {
          nota5 = quantidade /  5; //Calculando o número de notas de 5
          quantidade = quantidade % 5; //Calculando o resto depois de obter o número de notas de 5
        }
        if(quantidade  >= 2)
        {
            nota2 = quantidade / 2; //Calculando o número de notas de 2
            quantidade = quantidade % 2; //Calculando o resto depois de obter o número de notas de 2
        }
        
        try {
            ContaDAO.RealizaSaque(c.getId(), qtdAux); //Realizando o saque
            
            System.out.printf("Saque realizado de R$ %d \n" ,qtdAux);
            System.out.println("Notas de 100: " + nota100);
            System.out.println("Notas de 50: " + nota50);
            System.out.println("Notas de 20: " + nota20);
            System.out.println("Notas de 10: " + nota10);
            System.out.println("Notas de 5: " + nota5);
            System.out.println("Notas de 2: " + nota2);
            
        } catch (SQLException ex) {
            ex.printStackTrace(); //PrintStackTrace() irá mostrar no console uma pilha de erros que eventualmente podem acontecer.
        }
        
    }
    public static void ConsultarSaldo(Conta c)
    {
        System.out.println("O saldo atual:" + c.getSaldo());
        System.out.println("A quantidade de saques realizado: R$  " + c.getTotalSaques());
        System.out.println("A quantidade de depositos realizado: R$ " + c.getTotalDepositos());
        
    }
    
    
}