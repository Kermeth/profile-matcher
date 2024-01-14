package com.kermeth.profilematcher.profile.infrastructure.mongodb;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.kermeth.profilematcher.profile.domain.Clan;
import com.kermeth.profilematcher.profile.domain.Device;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Document("player_profiles")
public class PlayerProfileDocument {
    @Id
    private ObjectId id;
    @Indexed
    @Field("player_id")
    private String playerId;
    @Field("credential")
    private String credential;
    @Field("created")
    private String created;
    @Field("modified")
    private String modified;
    @Field("last_session")
    private String lastSession;
    @Field("total_spent")
    private Integer totalSpent;
    @Field("total_refund")
    private Integer totalRefund;
    @Field("total_transactions")
    private Integer totalTransactions;
    @Field("last_purchase")
    private String lastPurchase;
    @Field("active_campaigns")
    private List<String> activeCampaigns;
    @Field("devices")
    private List<Device> devices;
    @Field("level")
    private Integer level;
    @Field("xp")
    private Integer xp;
    @Field("total_playtime")
    private Integer totalPlaytime;
    @Field("country")
    private String country;
    @Field("language")
    private String language;
    @Field("birthdate")
    private String birthdate;
    @Field("gender")
    private String gender;
    @Field("inventory")
    private Map<String,Integer> inventory;
    @Field("clan")
    private Clan clan;
    private Map<String,Object> customFields;

    public PlayerProfileDocument(String playerId,
                                 String credential,
                                 String created,
                                 String modified,
                                 String lastSession,
                                 Integer totalSpent,
                                 Integer totalRefund,
                                 Integer totalTransactions,
                                 String lastPurchase,
                                 List<String> activeCampaigns,
                                 List<Device> devices,
                                 Integer level,
                                 Integer xp,
                                 Integer totalPlaytime,
                                 String country,
                                 String language,
                                 String birthdate,
                                 String gender,
                                 Map<String, Integer> inventory,
                                 Clan clan,
                                 Map<String, Object> customFields
    ) {
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

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getLastSession() {
        return lastSession;
    }

    public void setLastSession(String lastSession) {
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

    public String getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(String lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public List<String> getActiveCampaigns() {
        return activeCampaigns;
    }

    public void setActiveCampaigns(List<String> activeCampaigns) {
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
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

    @JsonAnyGetter
    public Map<String, Object> getCustomFields() {
        return customFields;
    }

    @JsonAnySetter
    public void setCustomFields(Map<String, Object> customFields) {
        this.customFields = customFields;
    }
}
