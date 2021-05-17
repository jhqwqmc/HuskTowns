package me.william278.husktowns.object.cache;

import me.william278.husktowns.data.DataManager;
import me.william278.husktowns.object.town.TownBonus;

import java.util.HashMap;
import java.util.HashSet;

public class TownBonusesCache {

    private final HashMap<String,HashSet<TownBonus>> townBonuses;

    public TownBonusesCache() {
        townBonuses = new HashMap<>();
        reload();
    }

    public void reload() {
        townBonuses.clear();
        DataManager.updateCachedBonuses();
    }

    public boolean contains(String townName) {
        return townBonuses.containsKey(townName);
    }

    public void add(String townName, TownBonus townBonus) {
        if (!townBonuses.containsKey(townName)) {
            townBonuses.put(townName, new HashSet<>());
        }
        HashSet<TownBonus> currentBonuses = getTownBonuses(townName);
        currentBonuses.add(townBonus);
        townBonuses.put(townName, currentBonuses);
    }

    public HashSet<TownBonus> getTownBonuses(String townName) {
        return townBonuses.get(townName);
    }

    public int getBonusMembers(String townName) {
        int bonusMembers = 0;
        for (TownBonus bonus : getTownBonuses(townName)) {
            bonusMembers = bonusMembers + bonus.getBonusMembers();
        }
        return bonusMembers;
    }

    public int getBonusClaims(String townName) {
        int bonusClaims = 0;
        for (TownBonus bonus : getTownBonuses(townName)) {
            bonusClaims = bonusClaims + bonus.getBonusClaims();
        }
        return bonusClaims;
    }

}
