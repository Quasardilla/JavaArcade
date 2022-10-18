package Intro.RationalNumber;

public class RationalNumber implements Comparable<RationalNumber>
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

        a = (int) ((a * (newB / (double) b)) - (rn.getA() * (newB / (double) rn.getB())));
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

    public String getRationalForm()
    {
        return (a / (double) b == a / b) ? ""+(a/b) : ""+a+"/"+b;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RationalNumber)) {
            return false;
        }
        RationalNumber rationalNumber = (RationalNumber) o;
        rationalNumber.simplify();
        RationalNumber rn = this; rn.simplify();
        return rn.a == rationalNumber.a && rn.b == rationalNumber.b;
    }

    public static int gcf(int num1, int num2)
    {
       num1 = Math.abs(num1);
       num2 = Math.abs(num2);
 
       int gcf = num1 * num2;
 
       for(int i = 1; i < Math.max(num1, num2); i++)
       {
          if(num1 % i == 0 && num2 % i == 0)
          {
             gcf = i;
          }
       }
 
       return gcf;
    }

    public void simplify()
    {
        if (a == 0)
        {
            b = 1;
            return;
        }

        int gcf = gcf(b, a);
        a /= gcf;
        b /= gcf;

        if ((b > 0 && a < 0 || b < 0 && a > 0) || (b < 0 && a < 0))
        {
            b *= -1;
            a *= -1;
            return;
        }
        
    }

    public RationalNumber getReciprocal()
    {
        RationalNumber rn = new RationalNumber(b, a);
        rn.simplify();
        return rn;
    }

    @Override
    public int compareTo(RationalNumber o) {
        return (int) (10 * (asDecimal() - o.asDecimal()));
    }

}