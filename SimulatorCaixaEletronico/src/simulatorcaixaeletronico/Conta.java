
package simulatorcaixaeletronico;

public class Conta {
    private double Saldo;
    private double TotalSaques,TotalDepositos;
    private int id;
    private String nConta,senhaConta, agencia;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }
    
    public double getTotalSaques() {
        return TotalSaques;
    }

    public void setTotalSaques(double TotalSaques) {
        this.TotalSaques = TotalSaques;
    }

    public double getTotalDepositos() {
        return TotalDepositos;
    }

    public void setTotalDepositos(double TotalDepositos) {
        this.TotalDepositos = TotalDepositos;
    }

    public String getnConta() {
        return nConta;
    }

    public void setnConta(String nConta) {
        this.nConta = nConta;
    }

    public String getSenhaConta() {
        return senhaConta;
    }

    public void setSenhaConta(String senhaConta) {
        this.senhaConta = senhaConta;
    }
   
    
}
