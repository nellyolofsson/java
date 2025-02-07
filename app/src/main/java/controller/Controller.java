package controller;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import model.Contract;
import model.Item;
import model.Member;
import model.StuffLendingSystem;
import model.Item.Mutable;
import model.persistence.HardCoded;
import view.ControllerCallback;
import view.PrinterView;
import view.View;

/**
 * Responsible for controlling the flow of the membership in application.
 */
public class Controller implements ControllerCallback {

  private StuffLendingSystem stuffLendingSystem;
  private View view;
  private PrinterView printerview;
  private HardCoded hardCoded;


  // Register callbacks with the View

  /**
   * Creates a new instance of the MemberController class.
   *
   * @param stuffLendingSystem the stuff lending system.
   */
  public Controller(StuffLendingSystem stuffLendingSystem) {
    this.stuffLendingSystem = new StuffLendingSystem();
    this.view = new view.ConsoleView();
    this.printerview = new PrinterView();
    this.hardCoded = new HardCoded();

    // Register callbacks with the View
    view.setControllerCallback(this);
  }

  public void run() {
    view.run();
  }

  private void addMember() {
    try {
      Member viewMember = view.getMemberToAdd();
      Member addMember = stuffLendingSystem.addMember(viewMember); 
      printerview.printMemberAdded(addMember);
    } catch (Exception e) {
      Member defaultMember = new Member("Unknown", "", "", 0);
      printerview.printMemberFailed(defaultMember);
    }
  }

  private void showOneMember() {
    try {
      Member viewMember = view.getMemberToRemove(stuffLendingSystem.getMembers());
      printerview.printOneFullMember(viewMember);
    } catch (Exception e) {
      Member defaultMember = new Member("Unknown", "", "", 0);
      printerview.printMemberFailed(defaultMember);
    }
  }

  private void listMembers() {
    // Get members from your stuffLendingSystem
    Iterable<Member> systemMembers = stuffLendingSystem.getMembers();
    //printerview.printMembers(systemMembers);
    // Get hardcoded members loaded from dataLoader
    Iterable<Member> hardcodedMemberss = hardCoded.loadHardcodedData(stuffLendingSystem);
    
    // Combine both sets of members
    Iterable<Member> allMembers = Iterables.concat(systemMembers, hardcodedMemberss);
    printerview.printMembers(allMembers);
  }


  private void listMembersVerbose() {
    Iterable<Member> members = stuffLendingSystem.getMembers();
    printerview.printMembersVerbose(members, stuffLendingSystem);
  }

  private void removeMember() {
    Member viewMember = view.getMemberToRemove(stuffLendingSystem.getMembers());
    Member removeMember = stuffLendingSystem.removeMember(viewMember);

    if (removeMember != null) {
      printerview.printMemberRemoved(removeMember);
    } else {
      printerview.printMemberFailed(viewMember);
    }
  }

  private void editMember() {
    Member viewMember = view.getMemberToRemove(stuffLendingSystem.getMembers());

    // Get new values to name, email and phone number
    Member editedValuesOfMember = view.getEditedValuesOfMember(viewMember);

    // Set new values to member in stuff lending system
    Member editMember =  stuffLendingSystem.editMember(viewMember, editedValuesOfMember);

    if (editMember != null) {
      printerview.printMemberEdited(editMember);
    } else {
      printerview.printMemberFailed(viewMember);
    }
  }

  @Override
  public void handleMemberMenuAction(view.MenuActions.MemberMenuAction selectedOption) { 
    switch (selectedOption) {
      case ADD_MEMBER:
        addMember();
        break;
      case REMOVE_MEMBER:
        removeMember();
        break;
      case EDIT_MEMBER:
        editMember();
        break;
      case LIST_MEMBERS:
        listMembers();
        break;
      case LIST_MEMBERS_VERBOSE:
        listMembersVerbose();
        break;
      case SHOW_ONE_MEMBER:
        showOneMember();
        break;
      case EXIT_TO_MAIN_MENU:
        break;
      default:
        break;
    }
  }

  @Override
  public void handleItemMenuAction(view.MenuActions.ItemMenuAction selectedOption) {
    switch (selectedOption) {
      case ADD_ITEM:
        addItem();
        break;
      case REMOVE_ITEM:
        removeItem();
        break;
      case EDIT_ITEM:
        editItem();
        break;
      case BORROW_ITEM:
        borrowItem();
        break;
      case EXIT_TO_MAIN_MENU:
        break;
      default:
        break;
    }
  }

  private void addItem() {
    Item.Mutable addItem = null;
    try {
      Member member = view.findMember(stuffLendingSystem.getMembers());
      Item.Mutable viewItem = view.getItemToAdd();
      Item.Mutable addedItem = member.addItem(viewItem);
      printerview.printAddedItem(addedItem);
    } catch (Exception e) {
      printerview.printItemFailed(addItem);
    }
  }
  

  private void removeItem() {
    Item removeTheItem = null;
    try {
      Member member = view.findMember(stuffLendingSystem.getMembers());
      Item viewItem = view.getItemtoRemove(member.getItems());
      Item removedItem = member.removeItem(viewItem);
      printerview.printRemovedItem(removedItem);
    } catch (Exception e) {
      printerview.printItemFailed(removeTheItem);
    }
  }

  private void editItem() {
    Member member = view.findMember(stuffLendingSystem.getMembers());

    if (member != null) {
      Item viewItem = view.getItemtoEdit(member.getItems());
      Item newValuesOfItem = view.getEditedValuesOfItem(viewItem);
      Item editedItem = member.editItem(viewItem, newValuesOfItem); // Add the item to the selected member
      if (editedItem != null) {
        // Item added successfully
        printerview.printEditItem(editedItem);
      } else {
        // Failed to add item
        printerview.printItemFailed(viewItem);
      }
    }
  }

  private void borrowItem() {
    //System.out.println("Borrow item");
    Iterable<Member> members = stuffLendingSystem.getMembers();
    Iterable<Item.Mutable> allItems = new ArrayList<Item.Mutable>();
    printerview.printAvailableItemsHeader();

    for (Member member : members) {
      printerview.printItems(member.getItems());
      allItems = member.getItems();
    }

    Item.Mutable itemToBorrow = view.getItemToBorrow(allItems);
    Member borrower = view.getMemberToBorrowFrom(members);
    Member lender = itemToBorrow.getOwner();
    System.out.println("Borrower: " + borrower.getName());
    System.out.println("Lender: " + lender.getName());


    if (borrower == lender) {
      printerview.printBorrowYourOwnItem();
    } else {
      Contract newContract = view.createLendingContract(itemToBorrow, borrower, lender);
      Iterable<Contract> contracts = stuffLendingSystem.getItemContracts(itemToBorrow);
      boolean isItemAvailable = true;
  
      int newStartDate = newContract.getStartDate();
      int newEndDate = newContract.getEndDate();
  
      for (Contract contract : contracts) {
        int contractStartDate = contract.getStartDate();
        int contractEndDate = contract.getEndDate();
        if (newStartDate == contractStartDate && newEndDate == contractEndDate) {
          printerview.printItemIsNotAvailable();
          isItemAvailable = false;
          break; // Exit the loop since there is a matching contract
        }
      }
      if (isItemAvailable) {
        stuffLendingSystem.addContract(newContract);
        System.out.println("Contract added" + borrower.getCredits() + lender.getCredits());
      }
    }
    printerview.printContract();
    Iterable<Contract> contracts = stuffLendingSystem.getContracts();
    printerview.printContracts(contracts);
  }
}
