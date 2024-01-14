package com.kermeth.profilematcher.profile.infrastructure;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.kermeth.profilematcher.campaign.Campaign;
import com.kermeth.profilematcher.profile.domain.Clan;
import com.kermeth.profilematcher.profile.domain.Device;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Document("player_profiles")
public class PlayerProfileDocument {
    @Id
    private UUID playerId;
    private String credential;
    private Instant created;
    private Instant modified;
    private Instant lastSession;
    private Integer totalSpent;
    private Integer totalRefund;
    private Integer totalTransactions;
    private Instant lastPurchase;
    private List<Campaign> activeCampaigns;
    private List<Device> devices;
    private Integer level;
    private Integer xp;
    private Integer totalPlaytime;
    private String country;
    private String language;
    private Instant birthdate;
    private String gender;
    private Map<String,Integer> inventory;
    private Clan clan;
    @JsonAnySetter
    private Map<String,Object> customFields;

    public PlayerProfileDocument(UUID playerId, String credential, Instant created, Instant modified, Instant lastSession, Integer totalSpent, Integer totalRefund, Integer totalTransactions, Instant lastPurchase, List<Campaign> activeCampaigns, List<Device> devices, Integer level, Integer xp, Integer totalPlaytime, String country, String language, Instant birthdate, String gender, Map<String, Integer> inventory, Clan clan, Map<String, Object> customFields) {
        this.playerId = playerId;
        this.credential = credential;
        this.created = created;
        this.modified = modified;
        this.lastSession = lastSession;
        this.totalSpent = totalSpent;
        this.totalRefund = totalRefund;
        this.totalTransactions = totalTransactions;
        this.lastPurchase = lastPurchase;
        this.activeCampaigns = activeCampaigns;
        this.devices = devices;
        this.level = level;
        this.xp = xp;
        this.totalPlaytime = totalPlaytime;
        this.country = country;
        this.language = language;
        this.birthdate = birthdate;
        this.gender = gender;
        this.inventory = inventory;
        this.clan = clan;
        this.customFields = customFields;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public Instant getLastSession() {
        return lastSession;
    }

    public void setLastSession(Instant lastSession) {
        this.lastSession = lastSession;
    }

    public Integer getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Integer totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Integer getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(Integer totalRefund) {
        this.totalRefund = totalRefund;
    }

    public Integer getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Integer totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public Instant getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(Instant lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public List<Campaign> getActiveCampaigns() {
        return activeCampaigns;
    }

    public void setActiveCampaigns(List<Campaign> activeCampaigns) {
        this.activeCampaigns = activeCampaigns;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getTotalPlaytime() {
        return totalPlaytime;
    }

    public void setTotalPlaytime(Integer totalPlaytime) {
        this.totalPlaytime = totalPlaytime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Instant getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Instant birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Map<String, Object> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, Object> customFields) {
        this.customFields = customFields;
    }
}
