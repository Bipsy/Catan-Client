package shared.models.DTO.params;

public class RollNumber extends MoveParams {

    private int number;

    public RollNumber(int playerIndex, int number) {
        super("rollNumber", playerIndex);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
