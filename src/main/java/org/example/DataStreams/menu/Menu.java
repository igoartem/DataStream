package org.example.DataStreams.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.DataStreams.utils.ConsoleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Menu {
    private static final Logger log = LoggerFactory.getLogger(Menu.class);
    private static BufferedReader in;
    private static Menu rootMenu;
    private List<MenuItem> itemList;
    private MenuItem exitItem;
    private String title;
    private boolean isRootMenu;

    public Menu() {
        this.itemList = new ArrayList<MenuItem>();

        if (Menu.rootMenu == null) {
            Menu.in = new BufferedReader(new InputStreamReader(System.in)); // create the input stream
            Menu.rootMenu = this; // assign this instance to static rootMenu
            this.isRootMenu = true; // let this instance know it's a root menu
            this.setTitle("Menu");

            this.exitItem = new MenuItem("Exit"); // A root menu will exit from the program
        } else {
            this.setTitle("Sub Menu");
            this.exitItem = new MenuItem("Back"); // A sub menu will go back one level
        }
        this.exitItem.setExitItem(true); // Let the MenuItem know that it is the exit item for this menu
    }

    /*
     * This method adds a MenuItem to the menu. The first item added will be '1' in the
     * selection order, the next will be '2' and so on, up until the exit item which will
     * be the 'list.size() + 1' as a selection option.
     */
    public void addItem(MenuItem item) {
        this.itemList.add(item);
    }

    public void execute() {
        MenuItem item = null;
        do {
            this.print();
            item = this.getUserInput();
            item.invoke();
        } while (!item.isExitItem());
    }

    /* Menu uses this to know how to index the exit option. */
    private int getExitIndex() {
        return this.itemList.size() + 1;
    }

    /*
     * Menu uses this method to get the user input, validate it and return a MenuItem based
     * on the selection.
     */
    private MenuItem getUserInput() {
        MenuItem item = null;
        String input = null;

        try {
            input = Menu.in.readLine();
            int option = Integer.parseInt(input); // Throws NumberFormatException for non-numberic input

            if (option < 1 || option > this.getExitIndex())
                throw new NumberFormatException(); // Taking advantage of above to catch out-of-range numbers

            if (option == this.getExitIndex()) {
                item = exitItem;

                // If 'this' menu is the root menu, close the input stream
                if (this.isRootMenu)
                    Menu.in.close();
            } else
                item = itemList.get(option - 1); // -1 as itemList(0) -> item 1 in printed menu
        } catch (NumberFormatException ex) {
            System.out.println("\nError: '" + input + "' is not a valid menu option!");
            item = new MenuItem(null); // Return a dummy menu item which ensures it cannot be invoked
            ConsoleUtils.pauseExecution();
        } catch (IOException ex) {
            log.error("", ex);
        } finally {
            return item;
        }
    }

    /*
     * Menu uses this method to print the menu with all available options to the
     * console.
     */
    private void print() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");

        if (this.title.equals("") == false)
            sb.append(this.title + "\n");

        for (int i = 0; i < this.itemList.size(); i++)
            sb.append("\n" + (i + 1) + " " + this.itemList.get(i).getLabel());

        sb.append("\n").append(getExitIndex()).append(" ").append(exitItem.getLabel());
        sb.append("\n> ");

        System.out.print(sb.toString());
    }

}