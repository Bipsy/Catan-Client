package shared.models.DTO;

/**
 * @author Anna Sokolova
 *
 */
public class AIPlayerDTO {

    private String type;

    public AIPlayerDTO() {
        this.type = null;
    }

    public AIPlayerDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
