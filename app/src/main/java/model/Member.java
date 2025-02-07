package model;

import java.util.ArrayList;
import java.util.List;

import model.Item.Mutable;

/**
 * Represents a member of the stuff lending system.
 */
public class Member {
  private MemberValidationService nameValidator = new MemberValidationService();
  private List<Item.Mutable> items = new ArrayList<Item.Mutable>();
  private String name;
  private String email;
  private String phoneNumber;
  private int credits;
  private String id;

  // Create classes of name, email and phone number whitin the Member class, just
  // to make them as types and not strings.

  /**
   * Creates a new member.
   *
   * @param name        The name of the member.
   * @param email       The email of the member.
   * @param phoneNumber The phone number of the member.
   */
  public Member(String name, String email, String phoneNumber, int credits) {
    // Validate and set the name, email, and phoneNumber attributes
    if (nameValidator.isValidName(name)) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Invalid name");
    }

    if (nameValidator.isValidEmail(email)) {
      this.email = email;
    } else {
      throw new IllegalArgumentException("Invalid email");
    }

    if (nameValidator.isValidPhoneNumber(phoneNumber)) {
      this.phoneNumber = phoneNumber;
    } else {
      throw new IllegalArgumentException("Invalid phone number");
    }

    this.credits = 0;
    generateId();
  }

  /**
   * Creates a new member.
   *
   * @param member The member to copy.
   */
  public Member(Member member) {
    this.name = member.name;
    this.email = member.email;
    this.phoneNumber = member.phoneNumber;
    this.credits = member.credits;
  }

  /**
   * Creates a copy of the member.
   *
   * @return A copy of the member.
   */
  public Member memberCopy() {
    Member copy = new Member(this);
    copy.setName(name);
    copy.setEmail(email);
    copy.setPhoneNumber(phoneNumber);
    copy.setCredits(credits);
    System.out.println("Member credits: " + copy.getName() +  " " + copy.getCredits());
    copy.setId(id);
    return copy;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public int getCredits() {
    return credits;
  }

  public String getId() {
    return id;
  }

  public void setCredits(int credits) {
    this.credits = credits;
  }

  /**
   * Sets the name of the member.
   *
   * @param name The name of the member.
   */
  public void setName(String name) {
    if (nameValidator.isValidName(name)) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Invalid name");
    }
  }

  /**
   * Sets the email of the member.
   *
   * @param email The email of the member.
   */
  public void setEmail(String email) {
    if (nameValidator.isValidEmail(email)) {
      this.email = email;
    } else {
      throw new IllegalArgumentException("Invalid email");
    }
  }

  /**
   * Sets the phone number of the member.
   *
   * @param phoneNumber The phone number of the member.
   */
  public void setPhoneNumber(String phoneNumber) {
    if (nameValidator.isValidPhoneNumber(phoneNumber)) {
      this.phoneNumber = phoneNumber;
    } else {
      throw new IllegalArgumentException("Invalid phone number");
    }
  }

  /**
   * Adds an item to the member's list of items.
   * When item is added to the member, the member receives 100 credits.
   *
   * @param viewItem The item to add.
   */
  public Item.Mutable addItem(Item viewItem) {
    var addItem = new Item(viewItem);
    addItem.generateId();
    addItem.setOwner(this);
    checkIdOfItem(addItem);
    items.add((Mutable) addItem);
    credits += 100;
    return (Mutable) addItem;
  }

  private void checkIdOfItem(Item addItem) {
    for (var item : items) {
      if (item.getId().equalsIgnoreCase(addItem.getId())) {
        addItem.generateId();
      }
    }
  }

  public void addCredits(int credits) {
    this.credits += credits;
  }


  /**
   * Gets the items that the member has.
   *
   * @return The items that the member has.
   */
  public Iterable<Item.Mutable> getItems() {
    return new ArrayList<>(items);
  }

  /**
   * Gets the amount of items that the member has.
   *
   * @return The amount of items that the member has.
   */
  public int getAmountOfItems() {
    return items.size();
  }

  /**
   * Edits an item in the member's list of items.
   *
   * @param viewItem The item to edit.
   */
  public Item editItem(Item viewItem, Item editedItem) {
    for (var item : items) {
      if (item.getName().equals(viewItem.getName())) {
        item.setName(editedItem.getName());
        item.setDescription(editedItem.getDescription());
        item.setCategory(editedItem.getCategory());
        return item;
      }
    }
    return editedItem;
  }

  /**
   * Removes an item from the member's list of items.
   *
   * @param item The item to remove.
   */
  public Item removeItem(Item item) {
    var removeItem = new Item(item);
    items.remove(item);
    return removeItem;
  }

  /**
   * Transfers credits from the member to the system.
   *
   * @param costPerDay The cost per day of the item.
   */
  public void transferCredits(int costPerDay) {
    if (credits >= costPerDay && costPerDay > 0) {
      credits -= costPerDay;
    } else {
      throw new IllegalArgumentException("Not enough credits");
    }
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
}


