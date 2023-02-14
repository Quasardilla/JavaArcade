package APFreeResponse;

public class StudentAdvance extends Advance {
    public StudentAdvance(int days) {
        super(days);
    }

    public double getPrice() {
        return super.getPrice() / 2;
    }

    @Override
    public String toString() {
        return super.toString() + " Student ID Required";
    }

}
