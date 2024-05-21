package pl.scanserve.service.dashboard;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.scanserve.model.dto.dashboard.DashboardDTO;
import pl.scanserve.model.entity.menuitem.MenuItemEntity;
import pl.scanserve.model.entity.order.OrderEntity;
import pl.scanserve.repository.order.OrderRepository;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class DashboardService {

    private OrderRepository orderRepository;

    @Transactional
    public DashboardDTO getDashboardInfo() {
        List<OrderEntity> allOrders = orderRepository.findAll();
        List<MenuItemEntity> menuItems = extractMenuItems(allOrders);

        Map<LocalDate, List<OrderEntity>> ordersGroupedByDate = allOrders.stream()
                .collect((Collectors.groupingBy(OrderEntity::getOrderTime)));

        List<DashboardDTO.OrdersPerDay> ordersPerDay = getOrdersPerDay(ordersGroupedByDate);

        Map<LocalDate, BigDecimal> revenuePerDayMap = prepareRevenuePerDayMap(ordersGroupedByDate);

        List<DashboardDTO.RevenuePerDay> revenuePerDay = getRevenuePerDay(revenuePerDayMap);

        return DashboardDTO.builder()
                .ordersPerDay(ordersPerDay)
                .ordersToday((long) allOrders.stream()
                        .filter(orderEntity -> orderEntity.getOrderTime().equals(LocalDate.now()))
                        .toList()
                        .size())
                .ordersTotal((long) allOrders.size())
                .revenueTotal(menuItems.stream()
                        .map(MenuItemEntity::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .revenueToday(revenuePerDayMap.get(LocalDate.now()) == null ? BigDecimal.valueOf(0) : revenuePerDayMap.get(LocalDate.now()))
                .revenuePerDay(revenuePerDay)
                .build();
    }

    private static List<DashboardDTO.RevenuePerDay> getRevenuePerDay(Map<LocalDate, BigDecimal> revenuePerDayMap) {
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<LocalDate> weekDates = IntStream.rangeClosed(0, DayOfWeek.SUNDAY.getValue())
                .mapToObj(startOfWeek::plusDays)
                .toList();

        Map<LocalDate, BigDecimal> defaultRevenueMap = weekDates.stream()
                .collect(Collectors.toMap(date -> date, date -> BigDecimal.valueOf(0.0)));

        defaultRevenueMap.putAll(revenuePerDayMap);

        return defaultRevenueMap.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(startOfWeek) && !entry.getKey().isAfter(endOfWeek))
                .map(entry -> DashboardDTO.RevenuePerDay.builder()
                        .revenue(entry.getValue())
                        .date(entry.getKey())
                        .build())
                .sorted(Comparator.comparing(DashboardDTO.RevenuePerDay::getDate))
                .toList();
    }

    private static Map<LocalDate, BigDecimal> prepareRevenuePerDayMap(Map<LocalDate, List<OrderEntity>> ordersGroupedByDate) {
        return ordersGroupedByDate.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(OrderEntity::getOrderTotalAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add))
                );
    }

    private static List<MenuItemEntity> extractMenuItems(List<OrderEntity> allOrders) {
        return allOrders.stream()
                .map(OrderEntity::getOrderedMenuItems)
                .flatMap(List::stream)
                .toList();
    }

    private static List<DashboardDTO.OrdersPerDay> getOrdersPerDay(Map<LocalDate, List<OrderEntity>> ordersGroupedByDate) {
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<LocalDate> weekDates = IntStream.rangeClosed(0, DayOfWeek.SUNDAY.getValue())
                .mapToObj(startOfWeek::plusDays)
                .toList();

        Map<LocalDate, Integer> defaultOrdersMap = weekDates.stream()
                .collect(Collectors.toMap(date -> date, date -> 0));

        Map<LocalDate, Integer> mapped = ordersGroupedByDate.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, localDateListEntry -> localDateListEntry.getValue().size()));

        defaultOrdersMap.putAll(mapped);

        return defaultOrdersMap
                .entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(startOfWeek) && !entry.getKey().isAfter(endOfWeek))
                .map(entry -> DashboardDTO.OrdersPerDay.builder()
                        .date(entry.getKey())
                        .ordersAmount(Long.valueOf(entry.getValue()))
                        .build()
                )
                .sorted(Comparator.comparing(DashboardDTO.OrdersPerDay::getDate))
                .toList();
    }
}
