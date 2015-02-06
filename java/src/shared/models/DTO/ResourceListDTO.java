package shared.models.DTO;

/**
 * This class stores the information needed to create a JSON string of a
 * resource list, and is used to facilitate the transfer of data between the
 * server and client.
 *
 * @author Austin
 *
 */
public class ResourceListDTO {

    private int brick;
    private int ore;
    private int sheep;
    private int wheat;
    private int wood;

    public ResourceListDTO() {
        brick = 23;
        ore = 22;
        sheep = 20;
        wheat = 22;
        wood = 21;
    }

    public int getBrick() {
        return brick;
    }

    public void setBrick(int brick) {
        this.brick = brick;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public int getSheep() {
        return sheep;
    }

    public void setSheep(int sheep) {
        this.sheep = sheep;
    }

    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

}
