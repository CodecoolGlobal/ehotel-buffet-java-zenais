package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.breakfastGroup.BreakfastGroupProvider;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.guest.GuestService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BreakfastManager {
    GuestService guestService;
    BuffetService buffetService;
    BreakfastGroupProvider breakfastGroupProvider;
    private List<Guest> allGuests;
    private LocalDate seasonStart;
    private LocalDate seasonEnd;

    public BreakfastManager(GuestService guestService, BuffetService buffetService, BreakfastGroupProvider breakfastGroupProvider, List<Guest> allGuests, LocalDate seasonStart, LocalDate seasonEnd){
        this.guestService = guestService;
        this.buffetService = buffetService;
        this.breakfastGroupProvider = breakfastGroupProvider;
        this.allGuests = allGuests;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
    }

    public void simulateSeason(){
        LocalDate day = seasonStart;
        while (!day.isAfter(seasonEnd)){
            day.plusDays(1);
            Set<Guest> guestsForDay = guestService.getGuestsForDay(allGuests,day);
            List<Set<Guest>> guestsCycles = breakfastGroupProvider.getBreakfastGroups(guestsForDay,7);
            for (Set<Guest> guests: guestsCycles ) {
                serve(guests);
            }
        }
    }
    public void serve(Set<Guest> guestGroup){
        //TODO:
        /*
        * refill buffet supply
        * consume breakfast
        * discard old meals
        * */
        // refill buffet:
        // get optimal portions
        //
        Map<MealType, Integer> mealTypeIntegerMap = null;
        int cycle = 1;

        buffetService.refill(mealTypeIntegerMap, cycle);
    }

    public Map<MealType, Integer> getOptimalPortions(Buffet buffet, Map<GuestType, Integer > numberOfGuestsPerType, int cyclesLeft, int assumedCostOfUnhappyGuest){
        //TODO:
        return null;
    }
}
