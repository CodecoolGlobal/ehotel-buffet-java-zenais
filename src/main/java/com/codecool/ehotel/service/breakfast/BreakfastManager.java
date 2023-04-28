package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.breakfastGroup.BreakfastGroupProvider;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.successMetrics.SuccessMetrics;
import com.codecool.ehotel.ui.Screen;

import java.time.LocalDate;
import java.util.*;

public class BreakfastManager {
    private final Random random;
    private final Screen screen;
    private GuestService guestService;
    private BuffetService buffetService;
    private BreakfastGroupProvider breakfastGroupProvider;
    private List<Guest> allGuests;
    private LocalDate seasonStart;
    private LocalDate seasonEnd;
    private Buffet buffet;
    private int cycle;
    private int assumedCostOfUnhappyGuest = 50;

    public BreakfastManager(Buffet buffet, GuestService guestService, BuffetService buffetService, BreakfastGroupProvider breakfastGroupProvider, List<Guest> allGuests, LocalDate seasonStart, LocalDate seasonEnd, Random random, Screen screen) {
        this.guestService = guestService;
        this.buffetService = buffetService;
        this.breakfastGroupProvider = breakfastGroupProvider;
        this.allGuests = allGuests;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.buffet = buffet;
        this.random = random;
        this.screen = screen;
    }

    private static void reduceRefillMap(Buffet buffet, Map<MealType, Integer> portionsToRefill) {
        for (int i = 0; i < buffet.mealsOnDisplay().size(); i++) {
            MealType meal = buffet.mealsOnDisplay().get(i).meal();
            if (portionsToRefill.containsKey(meal)) {
                if (portionsToRefill.get(meal) > 1) {
                    portionsToRefill.put(meal, portionsToRefill.get(meal) - 1);
                } else {
                    portionsToRefill.remove(meal);
                    i--;
                }
            }
        }
    }

    public void simulateSeason() {
        LocalDate day = seasonStart;
        SuccessMetrics seasonSuccessMetrics = new SuccessMetrics(0, 0, allGuests.size());
        List<SuccessMetrics> dailySuccessMetricsList = new ArrayList<>();
        while (!day.isAfter(seasonEnd)) {
            day = simulateDay(day, seasonSuccessMetrics, dailySuccessMetricsList);
        }
        screen.displayBreakfastSeason(seasonSuccessMetrics, dailySuccessMetricsList);
    }

    private LocalDate simulateDay(LocalDate day, SuccessMetrics seasonSuccessMetrics, List<SuccessMetrics> dailySuccessMetricsList) {
        cycle = 1;
        Set<Guest> guestsForDay = guestService.getGuestsForDay(allGuests, day);
        SuccessMetrics daySuccessMetrics = new SuccessMetrics(0, 0, guestsForDay.size());
        List<Set<Guest>> guestsCycles = breakfastGroupProvider.getBreakfastGroups(guestsForDay, 8);
        Map<GuestType, Integer> numberGuestsPerType = guestService.getNumberOfGuestsPerType(guestsForDay);
        serveCycles(daySuccessMetrics, guestsCycles, numberGuestsPerType);
        daySuccessMetrics.addCostOfWastedFood(buffetService.closeBuffet());
        seasonSuccessMetrics.addCostOfWastedFood(daySuccessMetrics.getCostOfWastedFood());
        seasonSuccessMetrics.addUnhappyGuests(daySuccessMetrics.getNumberOfUnhappyGuests());
        dailySuccessMetricsList.add(daySuccessMetrics);
        day = day.plusDays(1);
        return day;
    }

    private void serveCycles(SuccessMetrics daySuccessMetrics, List<Set<Guest>> guestsCycles, Map<GuestType, Integer> numberGuestsPerType) {
        for (Set<Guest> guests : guestsCycles) {
            daySuccessMetrics.addCostOfWastedFood(buffetService.collectWaste(cycle));
            serve(guests, numberGuestsPerType, daySuccessMetrics);
            cycle += 1;
        }
    }

    public void serve(Set<Guest> guestGroup, Map<GuestType, Integer> numberGuestsPerType, SuccessMetrics daySuccessMetrics) {
        //TODO:
        Map<MealType, Integer> portions = getOptimalPortions(buffet, numberGuestsPerType, cycle, assumedCostOfUnhappyGuest);
        buffetService.refill(portions, cycle);
        int unHappyGuests = 0;
        for (Guest guest : guestGroup) {
            boolean guestHappy = consume(guest);
            if (!guestHappy) {
                unHappyGuests += 1;
            }
            daySuccessMetrics.addUnhappyGuests(unHappyGuests);
            guestService.removeGuestFromTypeMap(guest, numberGuestsPerType);
        }
    }

    private boolean consume(Guest guest) {
        GuestType guestType = (GuestType) guest.guestType();
        List<MealType> preferredMeals = guestType.getMealPreferences();
        MealType randPickedMeal = preferredMeals.get(random.nextInt(preferredMeals.size()));
        boolean isHappy = buffetService.consumeFreshest(randPickedMeal);
        return isHappy;
    }

    public Map<MealType, Integer> getOptimalPortions(Buffet buffet,
                                                     Map<GuestType, Integer> numberOfGuestsPerType,
                                                     int cycle,
                                                     int assumedCostOfUnhappyGuest) {
        int[] percentageForCycles = {40, 40, 40, 40, 40, 100, 0, 0};
        Map<MealType, Integer> portionsToRefill = new HashMap<>();

        fillRefillMap(numberOfGuestsPerType, cycle, percentageForCycles, portionsToRefill);
        reduceRefillMap(buffet, portionsToRefill);

        return portionsToRefill;
    }

    private void fillRefillMap(Map<GuestType,
            Integer> numberOfGuestsPerType,
                               int cycle,
                               int[] percentageForCycles,
                               Map<MealType, Integer> portionsToRefill) {
        for (GuestType type : numberOfGuestsPerType.keySet()) {
            Integer numberOfTypeGuests = numberOfGuestsPerType.get(type);
            int mealsToPrep = (int) Math.ceil((double) numberOfTypeGuests / 100 * percentageForCycles[cycle - 1]);
            List<MealType> mealTypes = type.getMealPreferences();
            for (int i = 0; i < mealsToPrep; i++) {
                MealType mealToAdd = mealTypes.get(random.nextInt(mealTypes.size()));
                if (!portionsToRefill.containsKey(mealToAdd)) {
                    portionsToRefill.put((MealType) mealToAdd, 1);
                } else {
                    portionsToRefill.put((MealType) mealToAdd, portionsToRefill.get(mealToAdd) + 1);
                }
            }
        }
    }


}
