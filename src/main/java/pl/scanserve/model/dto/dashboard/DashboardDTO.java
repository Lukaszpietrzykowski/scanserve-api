package pl.scanserve.model.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {

    private Long ordersToday;
    private Long ordersTotal;
    private BigDecimal revenueTotal;
    private List<RevenuePerDay> revenuePerDay;
    private BigDecimal revenueToday;
    private List<OrdersPerDay> ordersPerDay = new ArrayList<>();
    private List<MostPopularMenuItem> mostPopularMenuItems = new ArrayList<>();


    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrdersPerDay {
        private Long ordersAmount;
        private LocalDate date;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RevenuePerDay {
        private BigDecimal revenue;
        private LocalDate date;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MostPopularMenuItem {
        private String name;
        private Long amount;
    }
}
