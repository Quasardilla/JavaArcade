package Intro.RationalNumber;

public class RationalNumber
{
    private int a;
    private int b;



    public RationalNumber() 
    {
        a = 1;
        b = 1;
    }

    public RationalNumber(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return this.a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "{" +
            " a='" + getA() + "'" +
            ", b='" + getB() + "'" +
            "}";
    }

    public double asDecimal()
    {
        return (double) a / (double) b; 
    }

    public void add(RationalNumber rn)
    {
        int newB = b * rn.getB();

        a = (int) ((rn.getA() * (newB / (double) rn.getB())) + (a * (newB / (double) b)));
        b = newB;

        int num1 = Math.abs(a);
        int num2 = Math.abs(b);
  
        int gcf = num1 * num2;
  
        for(int i = 1; i < Math.max(num1, num2); i++)
        {
           if(num1 % i == 0 && num2 % i == 0)
           {
              gcf = i;
           }
        }

        a /= gcf;
        b /= gcf;
    }

    public void subtract(RationalNumber rn)
    {
        int newB = b * rn.getB();

        a = (int) ((rn.getA() * (newB / (double) rn.getB())) - (a * (newB / (double) b)));
        b = newB;

        int num1 = Math.abs(a);
        int num2 = Math.abs(b);
  
        int gcf = num1 * num2;
  
        for(int i = 1; i < Math.max(num1, num2); i++)
        {
           if(num1 % i == 0 && num2 % i == 0)
           {
              gcf = i;
           }
        }

        a /= gcf;
        b /= gcf;
    }

    public void multiply(RationalNumber rn)
    {
        a *= rn.getA();
        b *= rn.getB();

        int num1 = Math.abs(a);
        int num2 = Math.abs(b);
  
        int gcf = num1 * num2;
  
        for(int i = 1; i < Math.max(num1, num2); i++)
        {
           if(num1 % i == 0 && num2 % i == 0)
           {
              gcf = i;
           }
        }

        a /= gcf;
        b /= gcf;

    }

    public void divide(RationalNumber rn)
    {
        a *= rn.getB();
        b *= rn.getA();

        int num1 = Math.abs(a);
        int num2 = Math.abs(b);
  
        int gcf = num1 * num2;
  
        for(int i = 1; i < Math.max(num1, num2); i++)
        {
           if(num1 % i == 0 && num2 % i == 0)
           {
              gcf = i;
           }
        }

        a /= gcf;
        b /= gcf;

    }



}