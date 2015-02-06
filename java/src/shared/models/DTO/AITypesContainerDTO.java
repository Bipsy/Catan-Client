package shared.models.DTO;

/**
 * @author Anna Sokolova
 *
 */
import java.util.List;
import java.util.ArrayList;

public class AITypesContainerDTO {

    private List<AIPlayerDTO> types;

    public AITypesContainerDTO() {
        types = new ArrayList<AIPlayerDTO>();
    }

    public List<AIPlayerDTO> getTypes() {
        return this.types;
    }

    public void setTypes(List<AIPlayerDTO> types) {
        this.types = types;
    }

}
