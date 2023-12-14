package delivery.glovo.controller.response;

import lombok.Data;
import java.util.List;

@Data
public class ApiResponse<D> {
    private boolean isSuccess;
    private D data;
    private List<String> messages;

}
