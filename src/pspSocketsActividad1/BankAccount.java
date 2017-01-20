package pspSocketsActividad1;

public class BankAccount {

    /**
     * Cantidad de dinero en la cuenta del cliente.
     */
    public double balance;
    
    /**
     * Constructor.
     */
    public BankAccount() {
        this.balance = 1000.0;
    }

    /**
     * Devuelve el balance actual de la cuenta.
     *
     * @return balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Incrementa el balance de la cuenta.
     *
     * @param cantidad cantidad en la que se incremetará el balance.
     */
    public void incrementarBalance(double qty) {               
        this.balance += qty;        
    }

    /**
     * Disminuye el balance de la cuenta.
     *
     * @param cantidad cantidad en la que se decremetará el balance.
     */
    public boolean decrementarBalance(double qty) {
        boolean isExtracted = false;
        
        if(qty <= this.balance){
            this.balance -= qty;
            isExtracted = true;
        }
        return isExtracted;
    }
}