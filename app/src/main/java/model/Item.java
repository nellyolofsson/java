package model;

import java.util.Date;
import java.util.Objects;

/**
 * Represents an item that can be lent.
 */
public class Item {
  private String name;
  private String description;
  private Category category;
  private Date dateOfCreation;
  private int costPerDay;
  private Member owner;
  private String id;

  /**
   * Creates a new item.
   *
   * @param name        The name of the item.
   * @param description The description of the item.
   * @param category    The category of the item.
   * @param costPerDay  The cost per day of the item.
   */
  public Item(String name, String description, Category category, int costPerDay) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.costPerDay = costPerDay;
    this.dateOfCreation = new Date();
    generateId();
    
  }

  /**
   * Creates a new item.
   *
   * @param item The item to copy.
   */
  public Item(Item item) {
    this.name = item.name;
    this.description = item.description;
    this.category = item.category;
    this.costPerDay = item.costPerDay;
    this.dateOfCreation = item.dateOfCreation;
    this.owner = item.owner;
  }

  /**
   * Creates a copy of the item.
   *
   * @return A copy of the item.
   */
  public Item copy() {
    Item copy = new Item(this);
    copy.setName(name);
    copy.setDescription(description);
    copy.setCategory(category);
    copy.setCostPerDay(costPerDay);
    copy.setDateOfCreation(dateOfCreation);
    copy.setId(id);
    copy.setOwner(owner);
    System.out.println("Item owner: " + copy.getName() +  " " + copy.getOwner());
    return copy;
  }

  public Member getOwner() {
    System.out.println("Item owner: " + owner.getName() +  " " + owner.getCredits());
    return owner != null ? owner.memberCopy() : null;
  }


  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Category getCategory() {
    return category;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public Date getDateOfCreation() {
    return new Date(dateOfCreation.getTime());
  }

  public String getId() {
    return id;
  }

  /**
   * Sets the owner of the item.
   *
   * @param owner The owner of the item.
   */
  public void setOwner(Member owner) {
    this.owner = owner != null ? owner.memberCopy() : null;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  public void setDateOfCreation(Date dateOfCreation) {
    this.dateOfCreation = new Date(dateOfCreation.getTime());
  }

  /**
   * Sets the id of the member.
   */
  private void setId(String id) {
    this.id = id;
  }

  /**
   * Generates a random alphanumeric ID.
   */
  public void generateId() {
    StringBuilder idBuilder = new StringBuilder();
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    int length = 6;
    
    for (int i = 0; i < length; i++) {
      int index = (int) (Math.random() * characters.length());
      idBuilder.append(characters.charAt(index));
    }
    setId(idBuilder.toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } 
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(id, item.id) && Objects.equals(name, item.name); // Compare other attributes as well
  }

  @Override
    public int hashCode() {
    return Objects.hash(id, name); // Hash other attributes as well
  }


  public static class Mutable extends Item {

    public Mutable(String name, String description, Category category, int costPerDay) {
      super(name, description, category, costPerDay);
    }

    public void setName(String name) {
      super.setName(name);
    }

    public void setDescription(String description) {
      super.setDescription(description);
    }

    public void setCategory(Category category) {
      super.setCategory(category);
    }

    public void setCostPerDay(int costPerDay) {
      super.setCostPerDay(costPerDay);
    }

    public void setDateOfCreation(Date dateOfCreation) {
      super.setDateOfCreation(dateOfCreation);
    }

    public void setId(String id) {
      super.setId(id);
    }

    public void setOwner(Member owner) {
      super.setOwner(owner);
    }
  }
}
