package APFreeResponse;

public class Advance /*extends Ticket*/{
    private double price;

    public Advance (int days){
        if(days >= 10)
            price = 30.0;
        else  
            price = 40.0;
    }   

    public double getPrice()
    {
        return price;
    }


    @Override
    public String toString() {
        return "{" +
            " price='" + getPrice() + "'" +
            "}";
    }

}
