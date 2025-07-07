package com.akash.cardInventory.model;
import jakarta.persistence.*;

@Entity
@Table(name = "persocard")
public class PersoCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profile;
    private String configurator;
    private String issuedTo;
    private String customer;
    private String rst;
    private String telcaPersoTest;
    private String shredCard;
    private String cardDescription;
    private String formFactor;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProfile() { return profile; }
    public void setProfile(String profile) { this.profile = profile; }

    public String getConfigurator() { return configurator; }
    public void setConfigurator(String configurator) { this.configurator = configurator; }

    public String getIssuedTo() { return issuedTo; }
    public void setIssuedTo(String issuedTo) { this.issuedTo = issuedTo; }

    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }

    public String getRst() { return rst; }
    public void setRst(String rst) { this.rst = rst; }

    public String getTelcaPersoTest() { return telcaPersoTest; }
    public void setTelcaPersoTest(String telcaPersoTest) { this.telcaPersoTest = telcaPersoTest; }

    public String getShredCard() { return shredCard; }
    public void setShredCard(String shredCard) { this.shredCard = shredCard; }

    public String getCardDescription() { return cardDescription; }
    public void setCardDescription(String cardDescription) { this.cardDescription = cardDescription; }

    public String getFormFactor() { return formFactor; }
    public void setFormFactor(String formFactor) { this.formFactor = formFactor; }
}
