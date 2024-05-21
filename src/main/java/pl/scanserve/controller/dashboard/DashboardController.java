package pl.scanserve.controller.dashboard;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.scanserve.model.dto.dashboard.DashboardDTO;
import pl.scanserve.service.dashboard.DashboardService;

@RestController
@RequestMapping("/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardDTO getDashboardInfo() {
        return dashboardService.getDashboardInfo();
    }
}
