package view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import model.Contract;
import model.Item;
import model.Member;
import model.Item.Mutable;


/**
 * Represents the console view.
 */
public class ConsoleView implements View {
  private ControllerCallback callback;

  public void setControllerCallback(ControllerCallback callback) {
    this.callback = callback;

  }

  /**
   * Creates a new instance of the ConsoleView class.
   */
  protected void showMenu() {
    System.out.println("Stuff Lending System");
    System.out.println("1. Member menu");
    System.out.println("2. Item menu");
    System.out.println("0. Exit");
  }

  /**
   * Creates a new instance of the ConsoleView class.
   */
  public void run() {
    boolean isRunning = true;
    while (isRunning) {
      showMenu();
      String choice = getUserChoice();
      switch (choice) {
        case "1":
          memberMenu();
          break;
        case "2":
          itemMenu();
          break;
        case "0":
          isRunning = false;
          break;
        default:
          System.out.println("Invalid choice");
      }
    }
  }

  /**
   * Gets the choice from user.
   */
  public String getUserChoice() {
    System.out.print("Enter your choice: ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    try {
      return reader.readLine();
    } catch (IOException | NumberFormatException e) {
      e.printStackTrace();
      return "";
    }
  }

  private void showMemberMenu() {
    System.out.println("Member Menu");
    System.out.println("1. Add member");
    System.out.println("2. Remove member");
    System.out.println("3. Edit member");
    System.out.println("4. List members");
    System.out.println("5. List members verbose");
    System.out.println("6. Show one member");
    System.out.println("0. Exit to main menu");
  }

  /**
   * Creates a new instance of the ConsoleView class.
   */
  protected void memberMenu() {
    try {
      MenuActions.MemberMenuAction selectedOption = null;
      while (selectedOption != MenuActions.MemberMenuAction.EXIT_TO_MAIN_MENU) {
        showMemberMenu();
        String input = getUserChoice();

        switch (input.charAt(0)) {
          case '1':
            selectedOption = MenuActions.MemberMenuAction.ADD_MEMBER;
            break;
          case '2':
            selectedOption = MenuActions.MemberMenuAction.REMOVE_MEMBER;
            break;
          case '3':
            selectedOption = MenuActions.MemberMenuAction.EDIT_MEMBER;
            break;
          case '4':
            selectedOption = MenuActions.MemberMenuAction.LIST_MEMBERS;
            break;
          case '5':
            selectedOption = MenuActions.MemberMenuAction.LIST_MEMBERS_VERBOSE;
            break;
          case '6':
            selectedOption = MenuActions.MemberMenuAction.SHOW_ONE_MEMBER;
            break;
          case '0':
            selectedOption = MenuActions.MemberMenuAction.EXIT_TO_MAIN_MENU;
            break;
          default:
            System.out.println("Invalid choice");
        }

        // Notify the controller of the selected option
        if (callback != null) {
          callback.handleMemberMenuAction(selectedOption);
        } else {
          System.out.println("No callback registered");
        }
      }
    } catch (Exception e) {
      System.out.println("Invalid input");
    }
  }

  private void showItemMenu() {
    System.out.println("Item Menu");
    System.out.println("1. Add item");
    System.out.println("2. Remove item");
    System.out.println("3. Edit item");
    System.out.println("4. Borrow item");
    System.out.println("0. Exit to main menu");
  }

  /**
   * Creates a new instance of the ConsoleView class.
   */
  protected void itemMenu() {
    try {
      MenuActions.ItemMenuAction selectedOption = null;
      while (selectedOption != MenuActions.ItemMenuAction.EXIT_TO_MAIN_MENU) {
        showItemMenu();
        String input = getUserChoice();

        switch (input.charAt(0)) {
          case '1':
            selectedOption = MenuActions.ItemMenuAction.ADD_ITEM;
            break;
          case '2':
            selectedOption = MenuActions.ItemMenuAction.REMOVE_ITEM;
            break;
          case '3':
            selectedOption = MenuActions.ItemMenuAction.EDIT_ITEM;
            break;
          case '4':
            selectedOption = MenuActions.ItemMenuAction.BORROW_ITEM;
            break;
          case '0':
            selectedOption = MenuActions.ItemMenuAction.EXIT_TO_MAIN_MENU;
            break;
          default:
            System.out.println("Invalid choice");
        }
        if (callback != null) {
          callback.handleItemMenuAction(selectedOption);
        } else {
          System.out.println("No callback registered");
        }
      }
    } catch (Exception e) {
      System.out.println("Invalid input " + e.getMessage());
    }
  }

  /**
   * Gets the item id.
   */
  public Item.Mutable getIdItem(Iterable<Item.Mutable> items) {
    String name = getString("Name of item to borrow:");
    String id = getName(name);
    for (Item.Mutable item : items) {
      if (item.getName() == id) {
        return item;
      }
    }
    return null;
  }

  /**
   * Gets the item by index.
   */
  public Item.Mutable getItemByIndex(Iterable<Item.Mutable> items, int index) {
    int itemNumber = 0;
    for (Item.Mutable item : items) {
      if (itemNumber == index) {
        return item;
      }
      itemNumber++;
    }
    return null;
  }

  /**
   * Gets the item to remove.
   */
  public Item.Mutable getItemtoRemove(Iterable<Item.Mutable> items) {
    String name = getString("Name of item to remove:");
    for (Item.Mutable item : items) {
      if (item.getName().equals(name)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Gets the item to edit.
   */
  public Item.Mutable getItemtoEdit(Iterable<Item.Mutable> items) {
    String name = getString("Name of item to edit:");
    for (Item.Mutable item : items) {
      if (item.getName().equals(name)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Gets the new values to edit the item with.
   */
  public Item getEditedValuesOfItem(Item item) {
    System.out.println("Enter new values for item, leave blank to keep old value");
    String name = getString("Edit name:");
    if (name.equals("")) {
      name = item.getName();
    }
    String description = getString("Edit description:");
    if (description.equals("")) {
      description = item.getDescription();
    }
    int costPerDay = getPrice();
    if (costPerDay == 0) {
      costPerDay = item.getCostPerDay();
    }
    // DOESN'T WORK!!!!!!!!!!!!!!
    model.Category category = getCategory();
    if (category == null) {
      category = item.getCategory();
    }

    return new Item(name, description, category, costPerDay);
  }

  /**
   * Gets the item to add.
   */
  public Item.Mutable getItemToAdd() {
    String name = getString("Name of item:");
    String description = getString("Description:");
    int costPerDay = getPrice();
    model.Category category = getCategory();

    return (Mutable) new Item(name, description, category, costPerDay);
  }

  /**
   * Finds a member.
   */
  public Member findMember(Iterable<Member> members) {
    var name = getName("Name of owner:");
    for (Member member : members) {
      if (member.getName().equals(name)) {
        return member;
      }
    }
    return null;
  }

  public model.Category getCategory() {
    return selectCategory();
  }

  /**
   * Gets the category of an item.
   *
   * @return The category of the item.
   */
  public model.Category selectCategory() {
    System.out.println("Select a category:");
    for (model.Category category : model.Category.values()) {
      System.out.println(category.ordinal() + 1 + ". " + category.toString());
    }

    int choice = Integer.parseInt(getUserChoice()); // Parse user input to integer

    if (choice >= 1 && choice <= model.Category.values().length) {
      return model.Category.values()[choice - 1];
    }
    return null;
  }

  public String getDescription() {
    String description = getString("Description:");
    return description;
  }

  /**
   * Gets the price of an item.
   *
   * @return The price of the item.
   */
  public int getPrice() {
    String priceString = getString("Price:");
    try {
      // Parse the user input into an integer
      return Integer.parseInt(priceString);
    } catch (NumberFormatException e) {
      // Handle the case where the input is not a valid integer
      System.err.println("Invalid input for price. Please enter a valid integer.");
      // You can handle this error by returning a default value or asking the user to
      // input again.
      return 0; // Default value, you can choose a different value if needed.
    }
  }

  /**
   * Creates a new instance of the ConsoleView class.
   */
  public Member getMemberToAdd() {
    var name = getName("Name:");
    var email = getEmail();
    var phoneNumber = getPhoneNumber();
    var credits = 0;

    return new Member(name, email, phoneNumber, credits);
  }

  /**
   * Gets the name of the member to remove.
   */
  public Member getMemberToRemove(Iterable<Member> members) {
    // Go through all members to match with the name of the member to remove
    var name = getName("Name of the member:");
    for (Member member : members) {
      if (member.getName().equals(name)) {
        return member;
      }
    }
    return null;
  }


  /**
   * Gets the name of the member to borrow from.
   */
  public Member getMemberToBorrowFrom(Iterable<Member> members) {
    // Go through all members to match with the name of the member to remove
    var name = getName("Name of the member you'd like to borrow an item from:");
    for (Member member : members) {
      if (member.getName().equals(name)) {
        return member;
      }
    }
    return null;
  }


  /**
   * Gets new values for a member.
   *
   * @param member The member to edit.
   * @return The edited member.
   */
  public Member getEditedValuesOfMember(Member member) {
    System.out.println("Type in new values. Leave blank to keep old value.");
    var name = getName("Edit name:");
    if (name.equals("")) {
      name = member.getName();
    }
    var email = getEmail();
    if (email.equals("")) {
      email = member.getEmail();
    }
    var phoneNumber = getPhoneNumber();
    if (phoneNumber.equals("")) {
      phoneNumber = member.getPhoneNumber();
    }

    return new Member(name, email, phoneNumber, member.getCredits());
  }

  private String getName(String prompt) {
    System.out.println(prompt);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    try {
      String name = reader.readLine();
      return name;
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  private String getEmail() {
    String email = getString("Email:");
    return email;
  }

  private String getPhoneNumber() {
    String phoneNumber = getString("Phone number:");
    return phoneNumber;
  }

  private String getString(String prompt) {
    System.out.println(prompt);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    try {
      String name = reader.readLine();
      return name;
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  /**
   * Creates a lending contract.
   */
  public Contract createLendingContract(Item.Mutable item, Member borrower, Member lender) {
    int startDate = getStarteDate();
    int endDate = getEndDate();

    return new Contract(startDate, endDate, item, borrower, lender);
  }


  /**
   * Gets the start date of a contract.
   *
   *
   * @return The start date of the contract.
   */
  public int getStarteDate() { 
    String prompt = getString("Enter startdate:");
    try {
      // Parse the user input into an integer
      return Integer.parseInt(prompt);
    } catch (NumberFormatException e) {
      // Handle the case where the input is not a valid integer
      System.err.println("Invalid input for price. Please enter a valid integer.");
      return 0; // Default value, you can choose a different value if needed.
    }
  }

  /**
   * Gets the start date of a contract.
   *
   * @return The start date of the contract.
   */
  public int getEndDate() { 
    String prompt = getString("Enter enddate:");
    try {
      // Parse the user input into an integer
      return Integer.parseInt(prompt);
    } catch (NumberFormatException e) {
      // Handle the case where the input is not a valid integer
      System.err.println("Invalid input for price. Please enter a valid integer.");
      return 0; // Default value, you can choose a different value if needed.
    }
  }

  /**
   * Gets an item to borrow.
   */
  public Item.Mutable getItemToBorrow(Iterable<Item.Mutable> items) {
    String id = getString("Enter the id of the item you want to borrow:");
    for (Item.Mutable item : items) {
      if (item.getId().equalsIgnoreCase(id)) {
        System.out.println(item.getName() + " is found");
        return item;
      }
    }
    System.out.println("No item with that id found");
    return null;
  }
}