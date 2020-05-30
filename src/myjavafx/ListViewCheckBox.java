package myjavafx;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.cell.CheckBoxListCell;

/**
 * ListView improved with CheckBox for each row.
 * @author Johan
 * @param <Item> items of type <String, boolean> for this ListView
 */
public class ListViewCheckBox<Item> extends MyListView {
    /**
     * Creates a default ListView control with no content.
     */
    public ListViewCheckBox() {
        super();
        this.setCellFactory(CheckBoxListCell.forListView(Item::selectedProperty));
    }
    
    /**
     * Returns a stream on all the selected items.
     * In other words, all the items with a CheckBox value set at <tt>true</tt> 
     * will be contains in the stream.
     * @return the list of all the selected items
     */
    public Stream<Item> getSelectedStream() {
        return (Stream<Item>)getItems().stream()
                .filter(item -> ((Item)item).isSelected());
    }
    
    /**
     * Returns the list of all the selected items.
     * In other words, all the items with a CheckBox value set at <tt>true</tt> 
     * will be returns.
     * @return the list of all the selected items
     */
    public List<Item> getSelectedItems() {
        return (List<Item>)getItems().stream()
                .filter(item -> ((Item)item).isSelected())
                .collect(Collectors.toList());
    }
    
    /**
     * Returns the list containing the texts of all the selected items.
     * In other words, all the items with a CheckBox value set at <tt>true</tt> 
     * will have their text added in the returned list.
     * @return the list of all the selected items
     */
    public List<String> getSelectedTexts() {
        return (List<String>)getItems().stream()
                .filter(item -> ((Item)item).isSelected())
                .map(item -> ((Item)item).getText())
                .collect(Collectors.toList());
    }
    
    /**
     * Add an item with the given text and a CheckBox with <code>checkBoxValue</code> as value.
     * @param text item's text
     * @param checkBoxValue value of the CheckBox
     */
    public void add(String text, boolean checkBoxValue) {
        getItems().add(new Item(text, checkBoxValue));
    }
    
    /**
     * Add an item with the given text and a CheckBox with <code>checkBoxValue</code> 
     * as value at the given index.
     * @param index index in which insert the new item
     * @param text item's text
     * @param checkBoxValue value of the CheckBox
     */
    public void add(int index, String text, boolean checkBoxValue) {
        getItems().add(index, new Item(text, checkBoxValue));
    }
    
    /**
     * Add an item with the given text, a CheckBox with <code>checkBoxValue</code> 
     * as value and the given listener to listen checkBoxChanges.
     * @param text item's text
     * @param checkBoxValue value of the CheckBox
     * @param listener listener for the CheckBox
     */
    public void add(String text, boolean checkBoxValue, ChangeListener<? super Boolean> listener) {
        Item item = new Item(text, checkBoxValue);
        item.selectedProperty().addListener(listener);
        add(item);
    }
    
    
    /**
     * Add an item with the given text, a CheckBox with <code>checkBoxValue</code> 
     * as value and the given listener to listen checkBoxChanges at the given index.
     * @param index index in which insert the new item
     * @param text item's text
     * @param checkBoxValue value of the CheckBox
     * @param listener listener for the CheckBox
     */
    public void add(int index, String text, boolean checkBoxValue, ChangeListener<? super Boolean> listener) {
        Item item = new Item(text, checkBoxValue);
        item.selectedProperty().addListener(listener);
        add(index, item);
    }
    
    /**
     * Check or uncheck the idem at the given index of this ListView, depending 
     * on the value of <code>check</code>.
     * In others words, the items will have its CheckBox value set to <code>check</code>.
     * @param index index of the item to check/uncheck
     * @param check new value of the item's CheckBox
     */
    public void check(int index, boolean check) {
        if (index >= size()) return;
        ((Item)get(index)).setSelected(check);
    }
    
    /**
     * Check or uncheck the idem, depending on the value of <code>check</code>.
     * In others words, the items will have its CheckBox value set to <code>check</code>.
     * @param item item to check/uncheck
     * @param check new value of the item's CheckBox
     */
    public void check(Item item, boolean check) {
        if (item == null) return;
        item.setSelected(check);
    }
    
    /**
     * Invert the item's CheckBox value.
     * @param index the index of the item to check/uncheck
     */
    public void changeCheck(int index) {
        if (index >= size()) return;
        ((Item)get(index)).changeSelected();
    }
    
    /**
     * Invert the item's CheckBox value.
     * @param item the item to check/uncheck
     */
    public void changeCheck(Item item) {
        if (item == null) return;
        item.changeSelected();
    }
    
    /**
     * Check the item at the given index of this ListView.
     * @param index index of the item to check
     */
    public void check(int index) {
        if (index >= size()) return;
        ((Item)get(index)).setSelected(true);
    }
    
    /**
     * Check the item.
     * @param item the item to check
     */
    public void check(Item item) {
        if (item == null) return;
        item.setSelected(true);
    }
    
    /**
     * Uncheck the item at the given index of this ListView.
     * @param index index of the item to uncheck
     */
    public void uncheck(int index) {
        if (index >= size()) return;
        ((Item)get(index)).setSelected(false);
    }
    
    /**
     * Uncheck the item.
     * @param item the item to uncheck
     */
    public void uncheck(Item item) {
        if (item == null) return;
        item.setSelected(false);
    }
    
    /**
     * Check or uncheck all the items of this ListView, depending on the value 
     * of <code>check</code>.
     * In others words, all the items will have their CheckBox value set to <code>check</code>.
     * @param check new value for the items CheckBoxs
     */
    public void chechAll(boolean check) {
        stream().forEach(item -> {
            ((Item)item).setSelected(check);
        });
    }
    
    /**
     * Check all the items CheckBoxs of this ListView.
     * In others words, all the items will have their CheckBox value set to <tt>true</tt>
     */
    public void checkAll() {
        stream().forEach(item -> {
            ((Item)item).setSelected(true);
        });
    }
    
    /**
     * Uncheck all the items CheckBox of this ListView.
     * In others words, all the items will have their CheckBox value set to <tt>false</tt>
     */
    public void uncheckAll() {
        stream().forEach(item -> {
            ((Item)item).setSelected(false);
        });
    }
    
    /**
     * Remove all the items with the given text.
     * @param text text of the items to be remove
     */
    public void remove(String text) {
        getItems().stream().filter(item -> ((Item)item).getText().equals(text)).forEach(item -> {
            super.remove(item);
        });
    }
    
    /**
     * Remove the first items with the given text.
     * @param text text of the item to be remove
     */
    public void removeFirst(String text) {
        getItems().stream().filter(item -> ((Item)item).getText().equals(text)).findFirst().ifPresent(item -> {
            super.remove(item);
        });
    }
    
    /**
     * Remove all the selected items.
     * In other words, all the items with <tt>true</tt> as CheckBox value will be removes.
     */
    public void removeAllSelected() {
        getItems().stream().filter(item -> ((Item)item).isSelected()).forEach(item -> {
            super.remove(item);
        });
    }
    
    /**
     * Remove all the unselected items.
     * In other words, all the items with <tt>true</tt> as CheckBox value will be removes.
     */
    public void removeAllUnselected() {
        getItems().stream().filter(item -> !((Item)item).isSelected()).forEach(item -> {
            super.remove(item);
        });
    }
    
    /**
     * Item with a text and CheckBox to insert in a ListViewCheckBox.
     */
    public static class Item {
        private final StringProperty text = new SimpleStringProperty();
        private final BooleanProperty selected = new SimpleBooleanProperty();
        
        /**
         * Create an Item with the given text and CheckBox value.
         * @param text text of this Item
         * @param selected <tt>true</tt> if the CheckBox have to be selected, otherwise <tt>false</tt>
         */
        public Item(String text, boolean selected) {
            setText(text);
            setSelected(selected);
        }
        
        /**
         * Create an Item with the given text and CheckBox value.
         * @param text text of this Item
         * @param selected <tt>true</tt> if the CheckBox have to be selected, otherwise <tt>false</tt>
         * @param listener listener for the CheckBox
         */
        public Item(String text, boolean selected, ChangeListener<? super Boolean> listener) {
            setText(text);
            setSelected(selected);
            this.selected.addListener(listener);
        }
        
        /**
         * Returns this Item's text Property.
         * @return this Item's text Property
         */
        public final StringProperty textProperty() {
            return this.text;
        }
        
        /**
         * Returns the text of this Item.
         * @return the text of this Item
         */
        public final String getText() {
            return this.textProperty().get();
        }
        
        /**
         * Change the text of Item.
         * @param text the new text of this Item
         */
        public final void setText(final String text) {
            this.textProperty().set(text);
        }
        
        /**
         * Returns this Item's CheckBox Property.
         * @return this Item's CheckBox Property
         */
        public final BooleanProperty selectedProperty() {
            return this.selected;
        }
        
        /**
         * Returns weither of not this Item's CheckBox is selected.
         * @return <tt>true</tt> if this Item's CheckBox is selected
         */
        public final boolean isSelected() {
            return this.selectedProperty().get();
        }
        
        /**
         * Change the CheckBox value of this Item.
         * @param selected new value of this Item's CheckBox
         */
        public final void setSelected(final boolean selected) {
            this.selectedProperty().set(selected);
        }
        
        /**
         * Invert the CheckBox value of this Item. Using the not (!) operator.
         */
        public final void changeSelected() {
            this.selectedProperty().set(!isSelected());
        }
        
        @Override
        public String toString() {
            return getText();
        }
    }
}
