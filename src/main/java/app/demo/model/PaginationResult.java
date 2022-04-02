package app.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResult {
    private final int pageSize;
    private final int pageIndex;
    private final long totalItems;
    private final List<Dragon> list;

    PaginationResult() {
        pageSize = 0;
        pageIndex = 0;
        totalItems = 0;
        list = new ArrayList<>();
    }
}