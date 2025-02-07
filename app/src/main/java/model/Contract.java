package model;

import java.util.List;

import model.Item.Mutable;

/**
 * Represents a contract between a member and another member.
 */
public class Contract {
  private int startDate;
  private int endDate;
  private Member lender; // The member lending the item
  private Member borrower; // The member borrowing the item
  private Item.Mutable items;

  /**
   * Creates a new contract.
   *
   * @param startDate The date the contract starts.
   * @param endDate   The date the contract ends.
   * @param item      The item that is being contracted.
   */
  public Contract(int startDate, int endDate, Item.Mutable items,  Member borrower, Member lender) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.items = items;
    this.borrower = borrower.memberCopy(); // Create a copy of the borrower
    this.lender = lender.memberCopy(); // Create a copy of the lender
  }

  /**
   * Creates a new contract.
   *
   * @param contract The contract to copy.
   */
  public Contract(Contract contract) {
    this.startDate = contract.startDate;
    this.endDate = contract.endDate;
    this.items = contract.items;
    this.borrower = contract.borrower;
    this.lender = contract.lender;
  }

  public int getStartDate() {
    return startDate;
  }

  public int getEndDate() {
    return endDate;
  }

  public Item getItem() {
    return items;
  }

  public Member getLender() {
    return lender.memberCopy();
  }

  public Member getBorrower() {
    return borrower.memberCopy();
  }
}


