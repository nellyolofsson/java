package view;

/**
 * The actions for the menu.
 */
public class MenuActions {
  /**
   * The options for the member menu.
   */
  public enum MemberMenuAction {
    ADD_MEMBER,
    REMOVE_MEMBER,
    EDIT_MEMBER,
    LIST_MEMBERS,
    LIST_MEMBERS_VERBOSE,
    SHOW_ONE_MEMBER,
    EXIT_TO_MAIN_MENU
  }

  /**
   * The options for the item menu.
   */
  public enum ItemMenuAction {
    ADD_ITEM,
    REMOVE_ITEM,
    EDIT_ITEM,
    BORROW_ITEM,
    EXIT_TO_MAIN_MENU
  }
}