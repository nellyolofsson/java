package view;

/**
 * The interface for the view.
 */
public interface ControllerCallback {
  void handleMemberMenuAction(MenuActions.MemberMenuAction selectedOption);

  void handleItemMenuAction(MenuActions.ItemMenuAction selectedOption);
}