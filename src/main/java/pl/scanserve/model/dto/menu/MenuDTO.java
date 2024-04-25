package pl.scanserve.model.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private Long id;
    private String name;
    private String displayName;
    private List<Long> categoryIds = new ArrayList<>();
}
