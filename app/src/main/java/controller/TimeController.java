package controller;

/**
 * Responsible for time management.
 */
public class TimeController {
  private int currentDay;

  public TimeController() {
    this.currentDay = 0;
  }

  // Get the current day
  public int getCurrentDay() {
    return this.currentDay;
  }

  /**
   * Advance the current day by a certain number of days.
   *
   * @param days The number of days to advance.
   */
  public void advanceDay(int days) {
    if (days < 0) {
      throw new IllegalArgumentException("Days must be positive.");
    }

    this.currentDay += days;
  }

  // Flytta fram den aktuella dagen med en dag
  public void advanceOneDay() {
    this.currentDay++;
  }
}