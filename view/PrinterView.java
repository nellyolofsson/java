package view;

import model.Contract;
import model.Item;
import model.Member;
import model.StuffLendingSystem;

/**
 * The PrintView for the view.
 */
public class PrinterView {

  /**
   * Prints out full information of a member.
   */
  public void printOneFullMember(Member member) {
    System.out.println("Name: " + member.getName());
    System.out.println("Member ID: " + member.getId());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone number: " + member.getPhoneNumber());
    System.out.println("Credits: " + member.getCredits());
    System.out.println("Items owned: " + member.getAmountOfItems());
    System.out.println("Items: ");
    for (Item item : member.getItems()) {
      System.out.println("  Name: " + item.getName());
      System.out.println("  Description: " + item.getDescription());
      System.out.println("  Category: " + item.getCategory());
      System.out.println("  Cost per day: " + item.getCostPerDay());
      System.out.println("  Date of creation: " + item.getDateOfCreation());
    }
    System.out.println();
  }

  /**
   * Prints out information from menu options.
   *
   * @param members The members to print.
   */
  public void printMembers(Iterable<Member> members) {
    for (Member member : members) {
      System.out.println("Name: " + member.getName());
      System.out.println("Member ID: " + member.getId());
      System.out.println("Email: " + member.getEmail());
      System.out.println("Phone number: " + member.getPhoneNumber());
      System.out.println("Credits: " + member.getCredits());
      System.out.println("Items owned: " + member.getAmountOfItems());
      System.out.println();
    }
  }

  public void printNoAvailableItems() {
    System.out.println("No available items");
  }

  public void printAvailableItemsHeader() {
    System.out.println("Available Items:");
  }

  /**
   * Prints out information from menu options.
   *
   * @param members The members to print.
   */
  public void printMembersVerbose(Iterable<Member> members, StuffLendingSystem stuffLendingSystem) {
    for (Member member : members) {
      System.out.println("Name: " + member.getName());
      System.out.println("Email: " + member.getEmail());
      System.out.println("Phone number: " + member.getPhoneNumber());
      System.out.println("Items: ");
      for (Item item : member.getItems()) {
        System.out.println("  Name: " + item.getName());
        System.out.println("  Description: " + item.getDescription());
        System.out.println("  Category: " + item.getCategory());
        System.out.println("  Cost per day: " + item.getCostPerDay());
        System.out.println("  Date of creation: " + item.getDateOfCreation());
        System.out.println();
        printAllContracts(item, stuffLendingSystem);
      } 
      System.out.println();
    }
  }

  /**
   * Prints out information of available items.
   *
   * @param items The items to print.
   */
  public void printAvailableItems(Iterable<Item> items) {
    for (Item item : items) {
      System.out.println("Name:" + item.getName() + " " + "Description:" + item.getDescription() + " " + "Category: "
          + item.getCategory() + " "
          + "Cost:" + item.getCostPerDay());
    }
  }

  public void printContract() {
    System.out.println("Contract added:");
  }

  public void printBorrowYourOwnItem() {
    System.out.println("You cannot borrow your own item.");
  }

  public void printItemIsNotAvailable() {
    System.out.println("Item is not available this time.");
  }
 

  /**
   * Prints out information of contracts.
   *
   * @param contracts The contracts to print.
   */
  public void printContracts(Iterable<Contract> contracts) {
    for (Contract contract : contracts) {
      System.out.println("------Contract of " + contract.getItem().getName() + "------"); 
      System.out.println("Borrower: " + contract.getBorrower().getName());
      System.out.println("Lender: " + contract.getLender().getName());
      System.out.println("Start date: " + contract.getStartDate());
      System.out.println("End date: " + contract.getEndDate());
    }
  }

  /**
   * Prints out information of items.
   *
   * @param items The items to print.
   */
  public void printItems(Iterable<Item.Mutable> items) {
    for (Item.Mutable item : items) {
      System.out.println("Name: " + item.getName());
      System.out.println("Owner: " + item.getOwner().getName());
      System.out.println("Items ID: " + item.getId());
      System.out.println("Description: " + item.getDescription());
      System.out.println("Category: " + item.getCategory());
      System.out.println("Cost per day: " + item.getCostPerDay());
      System.out.println("Date of creation: " + item.getDateOfCreation());
    }
  }

  /**
   * Prints out information of all contracts for a specific item.
   *
   * @param item The item to print contracts for.
   */
  public void printAllContracts(Item item, StuffLendingSystem stuffLendingSystem) {
    Iterable<Contract> contracts = stuffLendingSystem.getItemContracts(item);
    for (Contract contract : contracts) {
      if (contract.getItem().equals(item)) {
        // Print the details of the contract
        System.out.println("------Contract of " + contract.getItem().getName() + "------");
        System.out.println("Start Date: " + contract.getStartDate());
        System.out.println("End Date: " + contract.getEndDate());
        System.out.println("Borrower: " + contract.getBorrower().getName());
        System.out.println("Lender: " + contract.getLender().getName());
        
        System.out.println();
      }
    }
  }


  /**
   * Prints out information if borrowing was successful.
   *
   * @param items The items to print.
   */
  public void printBorrowedSuccess(Iterable<Item> items) {
    for (Item item : items) {
      System.out.println("You have successfully borrowed: " + item.getName());
    }
  }

  private void printMember(Member member, String message) {
    System.out.println(member.getName() + message);
  }

  public void printMemberAdded(Member member) {
    printMember(member, " was added successfully!");
  }

  public void printMemberRemoved(Member member) {
    printMember(member, " was removed.");
  }

  public void printMemberEdited(Member member) {
    printMember(member, " was edited.");
  }

  public void printMemberFailed(Member member) {
    printMember(member, " failed");
  }

  public void printAddedItem(Item item) {
    printItem(item, " added");
  }

  private void printItem(Item item, String message) {
    System.out.println("Name of item: " + item.getName());
    System.out.println("Description: " + item.getDescription());
    System.out.println("Category: " + item.getCategory());
    System.out.println("Cost per day: " + item.getCostPerDay());
  }

  public void printItemFailed(Item item) {
    printItem(item, " failed");
  }

  public void printRemovedItem(Item item) {
    printItem(item, " removed");
  }

  public void printEditItem(Item item) {
    printItem(item, " edit");
  }

}
