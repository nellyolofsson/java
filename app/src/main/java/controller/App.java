package controller;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    model.StuffLendingSystem stuffLendingSystem = new model.StuffLendingSystem();
    Controller controller = new Controller(stuffLendingSystem);
    // Run the application through the MemberController
    controller.run();

  }
}
