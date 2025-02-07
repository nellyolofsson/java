package view;

import model.Contract;
import model.Item;
import model.Member;

/**
 * The interface for the view.
 */
public interface View {

  public Member getMemberToAdd();

  public Member getMemberToRemove(Iterable<Member> members);

  public Member getEditedValuesOfMember(Member member);

  public void run();

  public void setControllerCallback(ControllerCallback controllerCallback);
  
  public Item.Mutable getItemToAdd();

  public Item.Mutable getItemtoRemove(Iterable<Item.Mutable> items);

  public Item.Mutable getItemtoEdit(Iterable<Item.Mutable> items);

  public Item getEditedValuesOfItem(Item item);
  
  public String getUserChoice();

  public Member findMember(Iterable<Member> members);

  public Item.Mutable getItemByIndex(Iterable<Item.Mutable> items, int index);

  public Item.Mutable getIdItem(Iterable<Item.Mutable> items);

  public Item.Mutable getItemToBorrow(Iterable<Item.Mutable> items);
  
  public Contract createLendingContract(Item.Mutable item, Member borrower, Member lender);

  public Member getMemberToBorrowFrom(Iterable<Member> members);

}
