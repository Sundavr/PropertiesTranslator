package myjavafx;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;

/**
 * ChoiceDialog improved.
 * @author Johan
 * @param <T> The type of the items to show to the user
 * @see javafx.scene.control.ChoiceDialog
 */
public class MyChoiceDialog<T> extends ChoiceDialog<T> {
    /**
     * Creates a default, empty instance of ChoiceDialog with no set items 
     * and a null default choice. Users of this constructor will subsequently 
     * need to call getItems() to specify which items to show to the user.
     */
    public MyChoiceDialog() {
        super();
    }
    
    /**
     * Add the given item to the ChoiceDialog.
     * @param item item to add
     * @throws UnsupportedOperationException if the add operation is not supported by this ChoiceDialog
     * @throws IllegalArgumentException if some property of this element prevents 
     * it from being added to this ChoiceDialog
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this ChoiceDialog
     * @throws NullPointerException if the specified element is null and this ChoiceDialog does not permit null elements
     */
    public void add(T item) {
        getItems().add(item);
    }
    
    /**
     * Inserts the specified element at the specified position in this ChoiceDialog 
     * (optional operation). 
     * Shifts the element currently at that position (if any) and any subsequent 
     * elements to the right (adds one to their indices).
     * @param index index at which add the item
     * @param item item to add
     * @UnsupportedOperationException if the add operation is not supported by this ChoiceDialog
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException if some property of the specified element 
     * prevents it from being added to this ChoiceDialog
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this ChoiceDialog
     * @throws NullPointerException if the specified element is null and this 
     * ChoiceDialog does not permit null elements
     */
    public void add(int index, T item) {
        getItems().add(index, item);
    }
    
    /**
     * Appends all of the elements in the specified collection to the end of 
     * this ChoiceDialog, in the order that they are returned by the specified 
     * collection's iterator (optional operation). 
     * The behavior of this operation is undefined if the specified collection 
     * is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this ChoiceDialog, and it's nonempty.)
     * @param collection collection containing elements to be added to this ChoiceDialog
     * @return <tt>true</tt> if this collection changed as a result of the calltrue 
     * if this ChoiceDialog changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not supported by this ChoiceDialog
     * @throws IllegalArgumentException if some property of an element of the 
     * specified collection prevents it from being added to this ChoiceDialog
     * @throws ClassCastException if the class of an element of the specified 
     * collection prevents it from being added to this ChoiceDialog
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ChoiceDialog does not permit null elements, 
     * or if the specified collection is null
     */
    public boolean addAll(Collection<? extends T> collection) {
        return getItems().addAll(collection);
    }
    
    /**
     * A convenient method for var-arg adding of elements.
     * @param elements the elements to add 
     * @return <tt>true</tt> (as specified by Collection.add(E))
     */
    public boolean addAll(T... elements) {
        return getItems().addAll(elements);
    }
    
    /**
     * Inserts all of the elements in the specified collection into this ChoiceDialog at 
     * the specified position (optional operation). Shifts the element currently 
     * at that position (if any) and any subsequent elements to the right (increases their indices). 
     * The new elements will appear in this ChoiceDialog in the order that they are returned 
     * by the specified collection's iterator. The behavior of this operation is 
     * undefined if the specified collection is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this ChoiceDialog, and it's nonempty.)
     * @param index index at which to insert the first element from the specified collection
     * @param collection collection containing elements to be added to this ChoiceDialog
     * @return <tt>true</tt> if this ChoiceDialog changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not supported by this ChoiceDialog
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException if some property of an element of the 
     * specified collection prevents it from being added to this ChoiceDialog
     * @throws ClassCastException if the class of an element of the specified 
     * collection prevents it from being added to this ChoiceDialog
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ChoiceDialog does not permit null elements, 
     * or if the specified collection is null
     */
    public boolean addAll(int index, Collection<? extends T> collection) {
        return getItems().addAll(index, collection);
    }
    
    /**
     * Returns true if this ChoiceDialog contains the specified element. 
     * More formally, returns true if and only if this ChoiceDialog contains at least 
     * one element e such that (item==null ? e==null : item.equals(e)).
     * @param item item whose presence in this ChoiceDialog is to be tested
     * @return <tt>true</tt> if this collection contains the specified element
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ChoiceDialog (optional)
     * @throws NullPointerException if the specified element is null and this 
     * ChoiceDialog does not permit null elements (optional)
     */
    public boolean contains(T item) {
        return getItems().contains(item);
    }
    
    /**
     * Returns <tt>true</tt> if this ChoiceDialog contains all of the elements of the specified collection.
     * @param collection collection to be checked for containment in this ChoiceDialog
     * @return <tt>true</tt> if this collection contains all of the elements in 
     * the specified collection
     * @throws ClassCastException if the types of one or more elements in the 
     * specified collection are incompatible with this ChoiceDialog (optional)
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ChoiceDialog does not permit null elements (optional), 
     * or if the specified collection is null
     */
    public boolean containsAll(Collection<?> collection) {
        return getItems().containsAll(collection);
    }
    
    /**
     * Creates a FilteredList wrapper of this ChoiceDialog using the specified predicate.
     * @param predicate the predicate to use 
     * @return new FilteredList
     * @since JavaFX 8.0
     */
    public FilteredList<T> filtered(Predicate<T> predicate) {
        return getItems().filtered(predicate);
    }
    
    /**
     * Performs the given action for each element of the Iterable until all 
     * elements have been processed or the action throws an exception. 
     * Unless otherwise specified by the implementing class, actions are 
     * performed in the order of iteration (if an iteration order is specified). 
     * Exceptions thrown by the action are relayed to the caller.
     * @param action The action to be performed for each element
     * @throws NullPointerException - if the specified action is null
     * @since 1.8
     */
    public void forEach(Consumer<? super T> action) {
        getItems().forEach(action);
    }
    
    /**
     * Returns the element at the specified position in this ChoiceDialog.
     * @param index index of the element to return 
     * @return the element at the specified position in this ChoiceDialog
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public T get(int index) {
        return getItems().get(index);
    }

    /**
     * Returns the index of the first occurrence of the specified element in 
     * this ChoiceDialog, or -1 if this ChoiceDialog does not contain the element. 
     * More formally, returns the lowest index i such that 
     * (item==null ? get(i)==null : item.equals(get(i))), or -1 if there is no such index.
     * @param item element to search for
     * @return the index of the first occurrence of the specified element in this ChoiceDialog, 
     * or -1 if this ChoiceDialog does not contain the element
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ChoiceDialog (optional)
     * @throws NullPointerException if the specified element is null and this 
     * ChoiceDialog does not permit null elements (optional)
     */
    public int indexOf(T item) {
        return getItems().indexOf(item);
    }
    
    /**
     * Returns true if this ChoiceDialog contains no elements.
     * @return <tt>true</tt> if this ChoiceDialog contains no elements
     */
    public boolean isEmpty() {
        return getItems().isEmpty();
    }
    
    /**
     * Returns an iterator over the elements in this ChoiceDialog in proper sequence.
     * @return an iterator over the elements in this ChoiceDialog in proper sequence
     */
    public Iterator<T> iterator() {
        return getItems().iterator();
    }
    
    /**
     * Returns a possibly parallel Stream with this collection as its source. 
     * It is allowable for this method to return a sequential stream.
     * This method should be overridden when the spliterator() method cannot 
     * return a spliterator that is IMMUTABLE, CONCURRENT, or late-binding. 
     * (See spliterator() for details.)
     * @return a possibly parallel Stream over the elements in this collection
     */
    public Stream<T> parallelStream() {
        return getItems().parallelStream();
    }
    
    /**
     * Removes the first occurrence of the specified element from this ChoiceDialog, 
     * if it is present (optional operation). 
     * If this ChoiceDialog does not contain the element, it is unchanged. 
     * More formally, removes the element with the lowest index i such that 
     * (item==null ? get(i)==null : item.equals(get(i))) 
     * (if such an element exists). 
     * Returns true if this ChoiceDialog contained the specified element 
     * (or equivalently, if this ChoiceDialog changed as a result of the call).
     * @param item element to be removed from this ChoiceDialog, if present
     * @return true if an element was removed as a result of this call
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this ChoiceDialog
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ChoiceDialog (optional)
     * @throws NullPointerException if the specified element is null and 
     * this ChoiceDialog does not permit null elements (optional)
     */
    public boolean remove(T item) {
        return getItems().remove(item);
    }
    
    /**
     * Removes the element at the specified position in this ChoiceDialog (optional operation). 
     * Shifts any subsequent elements to the left (subtracts one from their indices). 
     * Returns the element that was removed from the ChoiceDialog.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the remove operation is not supported by this ChoiceDialog
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public T remove(int index) {
        return getItems().remove(index);
    }
    
    /**
     * Basically a shortcut to sublist(from, to).clear(). 
     * As this is a common operation, ObservableList has this method for convenient usage.
     * @param from the start of the range to remove (inclusive)
     * @param to the end of the range to remove (exclusive)
     */
    public void remove(int from, int to) {
        getItems().remove(from, to);
    }
    
    /**
     * Removes from this ChoiceDialog all of its elements that are contained in the 
     * specified collection (optional operation).
     * @param collection collection containing elements to be removed from this ChoiceDialog
     * @return <tt>true</tt> if this collection changed as a result of the call
     */
    public boolean removeAll(Collection<?> collection) {
        return getItems().removeAll(collection);
    }
    
    /**
     * A convenient method for var-arg usage of removaAll method.
     * @param elements the elements to be removed
     * @return <tt>true</tt> if ChoiceDialog changed as a result of this call
     */
    public boolean removeAll(T... elements) {
        return getItems().removeAll(elements);
    }
    
    /**
     * Removes all of the elements of this collection that satisfy the given predicate. 
     * Errors or runtime exceptions thrown during iteration or by the predicate 
     * are relayed to the caller.
     * @param filter a predicate which returns true for elements to be removed
     * @return <tt>true</tt> if any elements were removed
     * @throws UnsupportedOperationException if elements cannot be removed from this collection. 
     * Implementations may throw this exception if a matching element cannot be removed or if, 
     * in general, removal is not supported.
     * @throws NullPointerException if the specified filter is null
     * @since 1.8
     */
    public boolean removeIf(Predicate<? super T> filter) {
        return getItems().removeIf(filter);
    }
    
    /**
     * Replaces each element of this ChoiceDialog with the result of applying the 
     * operator to that element. 
     * Errors or runtime exceptions thrown by the operator are relayed to the caller.
     * @param operator the operator to apply to each element
     * @throws UnsupportedOperationException if this ChoiceDialog is unmodifiable. 
     * Implementations may throw this exception if an element cannot be replaced 
     * or if, in general, modification is not supported
     * @throws NullPointerException if the specified operator is null or if the 
     * operator result is a null value and this ChoiceDialog does not permit null elements 
     * (optional) 
     * @since 1.8
     */
    public void replaceAll(UnaryOperator<T> operator) {
        getItems().replaceAll(operator);
    }
    
    /**
     * Retains only the elements in this ChoiceDialog that are contained in the 
     * specified collection (optional operation). 
     * In other words, removes from this ChoiceDialog all of its elements that are not 
     * contained in the specified collection.
     * @param collection collection containing elements to be retained in this ChoiceDialog
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the retainAll operation is not 
     * supported by this ChoiceDialog
     * @throws ClassCastException if the class of an element of this ChoiceDialog is 
     * incompatible with the specified collection (optional)
     * @throws NullPointerException if this ChoiceDialog contains a null element and the 
     * specified collection does not permit null elements (optional), 
     * or if the specified collection is null
     */
    public boolean retainAll(Collection<?> collection) {
        return getItems().retainAll(collection);
    }
    
    /**
     * A convenient method for var-arg usage of retain method.
     * @param elements the elements to be retained 
     * @return <tt>true</tt> if ChoiceDialog changed as a result of this call
     */
    public boolean retainAll(T... elements) {
        return getItems().retainAll(elements);
    }
    
    /**
     * Replaces the element at the specified position in this ChoiceDialog with the 
     * specified element (optional operation).
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the set operation is not supported by this ChoiceDialog
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     * @throws IllegalArgumentException if some property of the specified 
     * element prevents it from being added to this ChoiceDialog
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this ChoiceDialog
     * @throws NullPointerException if the specified element is null and this 
     * ChoiceDialog does not permit null elements
     */
    public T set(int index, T element) {
        return getItems().set(index, element);
    }
    
    /**
     * Clears the ObservableList and add all elements from the collection.
     * @param collection the collection with elements that will be added to this ChoiceDialog
     * @return <tt>true</tt> (as specified by Collection.add(T))
     */
    public boolean setAll(Collection<? extends T> collection) {
        return getItems().setAll(collection);
    }
    
    /**
     * Clears the ObservableList and add all the elements passed as var-args.
     * @param elements the elements to set
     * @return <tt>true</tt> (as specified by Collection.add(E))
     * @throws NullPointerException if the specified arguments contain one or more null elements
     */
    public boolean setAll(T... elements) {
        return getItems().setAll(elements);
    }
    
    /**
     * Returns the number of elements in this ChoiceDialog. 
     * If this ChoiceDialog contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of elements in this ChoiceDialog
     */
    public int size() {
        return getItems().size();
    }
    
    /**
     * Sorts this ChoiceDialog according to the order induced by the specified Comparator.
     * All elements in this ChoiceDialog must be mutually comparable using the specified 
     * comparator (that is, c.compare(e1, e2) must not throw a ClassCastException 
     * for any elements e1 and e2 in the ChoiceDialog).
     * If the specified comparator is null then all elements in this ChoiceDialog must implement 
     * the Comparable interface and the elements' natural ordering should be used.
     * This ChoiceDialog must be modifiable, but need not be resizable.
     * @param comparator the Comparator used to compare ChoiceDialog elements. 
     * A null value indicates that the elements' natural ordering should be used
     * @throws UnsupportedOperationException if the ChoiceDialog's list-iterator does 
     * not support the set operation
     * @throws IllegalArgumentException (optional) if the comparator is found 
     * to violate the Comparator contract
     * @throws ClassCastException if the ChoiceDialog contains elements that are not 
     * mutually comparable using the specified comparator
     * @since 1.8
     */
    public void sort(Comparator<? super T> comparator) {
        getItems().sort(comparator);
    }
    
    /**
     * Creates a SortedList wrapper of this ChoiceDialog with the natural ordering.
     * @return new SortedList
     * @since JavaFX 8.0
     */
    public SortedList<T> sorted() {
        return getItems().sorted();
    }
    
    /**
     * Creates a SortedList wrapper of this ChoiceDialog using the specified comparator.
     * @param comparator the comparator to use or null for unordered ChoiceDialog
     * @return new SortedList
     * @since JavaFX 8.0
     */
    public SortedList<T> sorted(Comparator<T> comparator) {
        return getItems().sorted(comparator);
    }
    
    /**
     * Returns a sequential Stream with this collection as its source.
     * This method should be overridden when the spliterator() method cannot 
     * return a spliterator that is IMMUTABLE, CONCURRENT, or late-binding. 
     * (See spliterator() for details.)
     * @return a sequential Stream over the elements in this ChoiceDialog
     * @since 1.8
     */
    public Stream<T> stream() {
        return getItems().stream();
    }
    
    /**
     * Returns an array containing all of the elements in this ChoiceDialog in proper 
     * sequence (from first to last element).
     * The returned array will be "safe" in that no references to it are 
     * maintained by this ChoiceDialog. 
     * (In other words, this method must allocate a new array even if this ChoiceDialog is backed by an array). 
     * The caller is thus free to modify the returned array.
     * This method acts as bridge between array-based and collection-based APIs.
     * @return an array containing all of the elements in this ChoiceDialog in proper sequence
     */
    public T[] toArray() {
        return (T[])getItems().toArray();
    }
    
    /**
     * Returns an array containing all of the elements in this ChoiceDialog in proper 
     * sequence (from first to last element); 
     * the runtime type of the returned array is that of the specified array. 
     * If the ChoiceDialog fits in the specified array, it is returned therein. 
     * Otherwise, a new array is allocated with the runtime type of the specified 
     * array and the size of this ChoiceDialog.
     * @param dest the array into which the elements of this ChoiceDialog are to be stored, if it 
     * is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collectionan array 
     * containing the elements of this ChoiceDialog
     * @throws ArrayStoreException if the runtime type of the specified array is 
     * not a supertype of the runtime type of every element in this ChoiceDialog
     * @throws NullPointerException if the specified array is null
     */
    public T[] toArray(T[] dest) {
        return getItems().toArray(dest);
    }
    
    /**
     * Observable list of button types used for the dialog button bar area 
     * (created via the createButtonBar() method). 
     * Modifying the contents of this list will immediately change the buttons 
     * displayed to the user within the dialog pane.
     * @return The ObservableList of button types available to the user.
     */
    public ObservableList<ButtonType> getButtonTypes() {
        return getDialogPane().getButtonTypes();
    }
    
    /**
     * Add the given button to the DialogPane.
     * @param buttonType button to add
     */
    public void addButton(ButtonType buttonType) {
        getDialogPane().getButtonTypes().add(buttonType);
    }
    
    /**
     * Inserts the specified element at the specified position in this 
     * ChoiceDialog's buttons (optional operation). 
     * Shifts the element currently at that position (if any) and any subsequent 
     * elements to the right (adds one to their indices).
     * @param index index at which add the item
     * @param buttonType button to add
     * @UnsupportedOperationException if the add operation is not supported by this ChoiceDialog's buttons
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException if some property of the specified element 
     * prevents it from being added to this ChoiceDialog's buttons
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this ChoiceDialog's buttons
     * @throws NullPointerException if the specified element is null and this 
     * ChoiceDialog's buttons does not permit null elements
     */
    public void addButton(int index, ButtonType buttonType) {
        getDialogPane().getButtonTypes().add(index, buttonType);
    }
    
    /**
     * Appends all of the elements in the specified collection to the end of 
     * this ChoiceDialog's buttons, in the order that they are returned by the specified 
     * collection's iterator (optional operation). 
     * The behavior of this operation is undefined if the specified collection 
     * is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this ChoiceDialog's buttons, and it's nonempty.)
     * @param collection collection containing elements to be added to this ChoiceDialog's buttons
     * @return <tt>true</tt> if this collection changed as a result of the calltrue 
     * if this ChoiceDialog's buttons changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not supported by this ChoiceDialog's buttons
     * @throws IllegalArgumentException if some property of an element of the 
     * specified collection prevents it from being added to this ChoiceDialog's buttons
     * @throws ClassCastException if the class of an element of the specified 
     * collection prevents it from being added to this ChoiceDialog's buttons
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ChoiceDialog's buttons does not permit null elements, 
     * or if the specified collection is null
     */
    public boolean addAllButtons(Collection<? extends ButtonType> collection) {
        return getDialogPane().getButtonTypes().addAll(collection);
    }
    
    /**
     * A convenient method for var-arg adding of elements.
     * @param elements the elements to add 
     * @return <tt>true</tt> (as specified by Collection.add(E))
     */
    public boolean addAllButtons(ButtonType... elements) {
        return getDialogPane().getButtonTypes().addAll(elements);
    }
    
    /**
     * Inserts all of the elements in the specified collection into this ChoiceDialog's buttons 
     * at the specified position (optional operation). Shifts the element currently 
     * at that position (if any) and any subsequent elements to the right (increases their indices). 
     * The new elements will appear in this ChoiceDialog's buttons in the order that they are returned 
     * by the specified collection's iterator. The behavior of this operation is 
     * undefined if the specified collection is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this ChoiceDialog's buttons, and it's nonempty.)
     * @param index index at which to insert the first element from the specified collection
     * @param collection collection containing elements to be added to this ChoiceDialog's buttons
     * @return <tt>true</tt> if this ChoiceDialog's buttons changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not supported by this ChoiceDialog's buttons
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException if some property of an element of the 
     * specified collection prevents it from being added to this ChoiceDialog's buttons
     * @throws ClassCastException if the class of an element of the specified 
     * collection prevents it from being added to this ChoiceDialog's buttons
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ChoiceDialog's buttons does not permit null elements, 
     * or if the specified collection is null
     */
    public boolean addAllButtons(int index, Collection<? extends ButtonType> collection) {
        return getDialogPane().getButtonTypes().addAll(index, collection);
    }
    
    /**
     * Returns true if this ChoiceDialog's buttons contains the specified element. 
     * More formally, returns true if and only if this ChoiceDialog's buttons contains at least 
     * one element e such that (item==null ? e==null : item.equals(e)).
     * @param item item whose presence in this ChoiceDialog's buttons is to be tested
     * @return <tt>true</tt> if this collection contains the specified element
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ChoiceDialog's buttons (optional)
     * @throws NullPointerException if the specified element is null and this 
     * ChoiceDialog's buttons does not permit null elements (optional)
     */
    public boolean containsButton(ButtonType item) {
        return getDialogPane().getButtonTypes().contains(item);
    }
    
    /**
     * Returns <tt>true</tt> if this ChoiceDialog's buttons contains all of the 
     * elements of the specified collection.
     * @param collection collection to be checked for containment in this ChoiceDialog's buttons
     * @return <tt>true</tt> if this collection contains all of the elements in 
     * the specified collection
     * @throws ClassCastException if the types of one or more elements in the 
     * specified collection are incompatible with this ChoiceDialog's buttons (optional)
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ChoiceDialog's buttons does not permit null elements (optional), 
     * or if the specified collection is null
     */
    public boolean containsAllButtons(Collection<?> collection) {
        return getDialogPane().getButtonTypes().containsAll(collection);
    }
    
    /**
     * Creates a FilteredList wrapper of this ChoiceDialog's buttons using the 
     * specified predicate.
     * @param predicate the predicate to use 
     * @return new FilteredList
     * @since JavaFX 8.0
     */
    public FilteredList<ButtonType> filteredButtons(Predicate<ButtonType> predicate) {
        return getDialogPane().getButtonTypes().filtered(predicate);
    }
    
    /**
     * Performs the given action for each element of the Iterable until all 
     * elements have been processed or the action throws an exception. 
     * Unless otherwise specified by the implementing class, actions are 
     * performed in the order of iteration (if an iteration order is specified). 
     * Exceptions thrown by the action are relayed to the caller.
     * @param action The action to be performed for each element
     * @throws NullPointerException - if the specified action is null
     * @since 1.8
     */
    public void forEachButtons(Consumer<? super ButtonType> action) {
        getDialogPane().getButtonTypes().forEach(action);
    }
    
    /**
     * Returns the element at the specified position in this ChoiceDialog's buttons.
     * @param index index of the element to return 
     * @return the element at the specified position in this ChoiceDialog's buttons
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public ButtonType getButton(int index) {
        return getDialogPane().getButtonTypes().get(index);
    }

    /**
     * Returns the index of the first occurrence of the specified element in this 
     * ChoiceDialog's buttons, or -1 if this ChoiceDialog's buttons does not contain the element. 
     * More formally, returns the lowest index i such that 
     * (item==null ? get(i)==null : item.equals(get(i))), or -1 if there is no such index.
     * @param item element to search for
     * @return the index of the first occurrence of the specified element in this ChoiceDialog's buttons, 
     * or -1 if this ChoiceDialog's buttons does not contain the element
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ChoiceDialog's buttons (optional)
     * @throws NullPointerException if the specified element is null and this 
     * ChoiceDialog's buttons does not permit null elements (optional)
     */
    public int indexOfButton(ButtonType item) {
        return getDialogPane().getButtonTypes().indexOf(item);
    }
    
    /**
     * Returns true if this ChoiceDialog's buttons contains no elements.
     * @return <tt>true</tt> if this ChoiceDialog's buttons contains no elements
     */
    public boolean isEmptyButtons() {
        return getDialogPane().getButtonTypes().isEmpty();
    }
    
    /**
     * Returns an iterator over the elements in this ChoiceDialog's buttons in proper sequence.
     * @return an iterator over the elements in this ChoiceDialog's buttons in proper sequence
     */
    public Iterator<ButtonType> iteratorButtons() {
        return getDialogPane().getButtonTypes().iterator();
    }
    
    /**
     * Returns a possibly parallel Stream with this collection as its source. 
     * It is allowable for this method to return a sequential stream.
     * This method should be overridden when the spliterator() method cannot 
     * return a spliterator that is IMMUTABLE, CONCURRENT, or late-binding. 
     * (See spliterator() for details.)
     * @return a possibly parallel Stream over the elements in this collection
     */
    public Stream<ButtonType> parallelStreamButtons() {
        return getDialogPane().getButtonTypes().parallelStream();
    }
    
    /**
     * Removes the first occurrence of the specified element from this ChoiceDialog's buttons, 
     * if it is present (optional operation). 
     * If this ChoiceDialog's buttons does not contain the element, it is unchanged. 
     * More formally, removes the element with the lowest index i such that 
     * (item==null ? get(i)==null : item.equals(get(i))) 
     * (if such an element exists). 
     * Returns true if this ChoiceDialog's buttons contained the specified element 
     * (or equivalently, if this ChoiceDialog's buttons changed as a result of the call).
     * @param item element to be removed from this ChoiceDialog's buttons, if present
     * @return true if an element was removed as a result of this call
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this ChoiceDialog's buttons
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ChoiceDialog's buttons (optional)
     * @throws NullPointerException if the specified element is null and 
     * this ChoiceDialog's buttons does not permit null elements (optional)
     */
    public boolean removeButton(ButtonType item) {
        return getDialogPane().getButtonTypes().remove(item);
    }
    
    /**
     * Removes the element at the specified position in this ChoiceDialog's buttons (optional operation). 
     * Shifts any subsequent elements to the left (subtracts one from their indices). 
     * Returns the element that was removed from the ChoiceDialog's buttons.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the remove operation is not supported by this ChoiceDialog's buttons
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public ButtonType removeButton(int index) {
        return getDialogPane().getButtonTypes().remove(index);
    }
    
    /**
     * Basically a shortcut to sublist(from, to).clear(). 
     * As this is a common operation, ObservableList has this method for convenient usage.
     * @param from the start of the range to remove (inclusive)
     * @param to the end of the range to remove (exclusive)
     */
    public void removeButtons(int from, int to) {
        getDialogPane().getButtonTypes().remove(from, to);
    }
    
    /**
     * Removes from this ChoiceDialog's buttons all of its elements that are contained in the 
     * specified collection (optional operation).
     * @param collection collection containing elements to be removed from this ChoiceDialog's buttons
     * @return <tt>true</tt> if this collection changed as a result of the call
     */
    public boolean removeAllButtons(Collection<?> collection) {
        return getDialogPane().getButtonTypes().removeAll(collection);
    }
    
    /**
     * A convenient method for var-arg usage of removaAll method.
     * @param elements the elements to be removed
     * @return <tt>true</tt> if ChoiceDialog's buttons changed as a result of this call
     */
    public boolean removeAllButtons(ButtonType... elements) {
        return getDialogPane().getButtonTypes().removeAll(elements);
    }
    
    /**
     * Removes all of the elements of this collection that satisfy the given predicate. 
     * Errors or runtime exceptions thrown during iteration or by the predicate 
     * are relayed to the caller.
     * @param filter a predicate which returns true for elements to be removed
     * @return <tt>true</tt> if any elements were removed
     * @throws UnsupportedOperationException if elements cannot be removed from this collection. 
     * Implementations may throw this exception if a matching element cannot be removed or if, 
     * in general, removal is not supported.
     * @throws NullPointerException if the specified filter is null
     * @since 1.8
     */
    public boolean removeIfButtons(Predicate<? super ButtonType> filter) {
        return getDialogPane().getButtonTypes().removeIf(filter);
    }
    
    /**
     * Replaces each element of this ChoiceDialog's buttons with the result of applying the 
     * operator to that element. 
     * Errors or runtime exceptions thrown by the operator are relayed to the caller.
     * @param operator the operator to apply to each element
     * @throws UnsupportedOperationException if this ChoiceDialog's buttons is unmodifiable. 
     * Implementations may throw this exception if an element cannot be replaced 
     * or if, in general, modification is not supported
     * @throws NullPointerException if the specified operator is null or if the 
     * operator result is a null value and this ChoiceDialog's buttons does not permit null elements 
     * (optional) 
     * @since 1.8
     */
    public void replaceAllButtons(UnaryOperator<ButtonType> operator) {
        getDialogPane().getButtonTypes().replaceAll(operator);
    }
    
    /**
     * Retains only the elements in this ChoiceDialog's buttons that are contained in the 
     * specified collection (optional operation). 
     * In other words, removes from this ChoiceDialog's buttons all of its elements that are not 
     * contained in the specified collection.
     * @param collection collection containing elements to be retained in this ChoiceDialog's buttons
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the retainAll operation is not 
     * supported by this ChoiceDialog's buttons
     * @throws ClassCastException if the class of an element of this ChoiceDialog's buttons is 
     * incompatible with the specified collection (optional)
     * @throws NullPointerException if this ChoiceDialog's buttons contains a null element and the 
     * specified collection does not permit null elements (optional), 
     * or if the specified collection is null
     */
    public boolean retainAllButtons(Collection<?> collection) {
        return getDialogPane().getButtonTypes().retainAll(collection);
    }
    
    /**
     * A convenient method for var-arg usage of retain method.
     * @param elements the elements to be retained 
     * @return <tt>true</tt> if ChoiceDialog's buttons changed as a result of this call
     */
    public boolean retainAllButtons(ButtonType... elements) {
        return getDialogPane().getButtonTypes().retainAll(elements);
    }
    
    /**
     * Replaces the element at the specified position in this ChoiceDialog's buttons with the 
     * specified element (optional operation).
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the set operation is not supported by this ChoiceDialog's buttons
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     * @throws IllegalArgumentException if some property of the specified 
     * element prevents it from being added to this ChoiceDialog's buttons
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this ChoiceDialog's buttons
     * @throws NullPointerException if the specified element is null and this 
     * ChoiceDialog's buttons does not permit null elements
     */
    public ButtonType setButton(int index, ButtonType element) {
        return getDialogPane().getButtonTypes().set(index, element);
    }
    
    /**
     * Clears the ObservableList and add all elements from the collection.
     * @param collection the collection with elements that will be added to this ChoiceDialog's buttons
     * @return <tt>true</tt> (as specified by Collection.add(T))
     */
    public boolean setAllButtons(Collection<? extends ButtonType> collection) {
        return getDialogPane().getButtonTypes().setAll(collection);
    }
    
    /**
     * Clears the ObservableList and add all the elements passed as var-args.
     * @param elements the elements to set
     * @return <tt>true</tt> (as specified by Collection.add(E))
     * @throws NullPointerException if the specified arguments contain one or more null elements
     */
    public boolean setAllButtons(ButtonType... elements) {
        return getDialogPane().getButtonTypes().setAll(elements);
    }
    
    /**
     * Returns the number of elements in this ChoiceDialog's buttons. 
     * If this ChoiceDialog's buttons contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of elements in this ChoiceDialog's buttons
     */
    public int sizeButtons() {
        return getDialogPane().getButtonTypes().size();
    }
    
    /**
     * Sorts this ChoiceDialog's buttons according to the order induced by the specified Comparator.
     * All elements in this ChoiceDialog's buttons must be mutually comparable using the specified 
     * comparator (that is, c.compare(e1, e2) must not throw a ClassCastException 
     * for any elements e1 and e2 in the ChoiceDialog's buttons).
     * If the specified comparator is null then all elements in this ChoiceDialog's buttons must implement 
     * the Comparable interface and the elements' natural ordering should be used.
     * This ChoiceDialog's buttons must be modifiable, but need not be resizable.
     * @param comparator the Comparator used to compare ChoiceDialog's buttons elements. 
     * A null value indicates that the elements' natural ordering should be used
     * @throws UnsupportedOperationException if the ChoiceDialog's buttons's list-iterator does 
     * not support the set operation
     * @throws IllegalArgumentException (optional) if the comparator is found 
     * to violate the Comparator contract
     * @throws ClassCastException if the ChoiceDialog's buttons contains elements that are not 
     * mutually comparable using the specified comparator
     * @since 1.8
     */
    public void sortButtons(Comparator<? super ButtonType> comparator) {
        getDialogPane().getButtonTypes().sort(comparator);
    }
    
    /**
     * Creates a SortedList wrapper of this ChoiceDialog's buttons with the natural ordering.
     * @return new SortedList
     * @since JavaFX 8.0
     */
    public SortedList<ButtonType> sortedButtons() {
        return getDialogPane().getButtonTypes().sorted();
    }
    
    /**
     * Creates a SortedList wrapper of this ChoiceDialog's buttons using the specified comparator.
     * @param comparator the comparator to use or null for unordered ChoiceDialog's buttons
     * @return new SortedList
     * @since JavaFX 8.0
     */
    public SortedList<ButtonType> sortedButtons(Comparator<ButtonType> comparator) {
        return getDialogPane().getButtonTypes().sorted(comparator);
    }
    
    /**
     * Returns a sequential Stream with this collection as its source.
     * This method should be overridden when the spliterator() method cannot 
     * return a spliterator that is IMMUTABLE, CONCURRENT, or late-binding. 
     * (See spliterator() for details.)
     * @return a sequential Stream over the elements in this ChoiceDialog's buttons
     * @since 1.8
     */
    public Stream<ButtonType> streamButtons() {
        return getDialogPane().getButtonTypes().stream();
    }
    
    /**
     * Returns an array containing all of the elements in this ChoiceDialog's buttons in proper 
     * sequence (from first to last element).
     * The returned array will be "safe" in that no references to it are 
     * maintained by this ChoiceDialog's buttons. 
     * (In other words, this method must allocate a new array even if this ChoiceDialog's buttons is backed by an array). 
     * The caller is thus free to modify the returned array.
     * This method acts as bridge between array-based and collection-based APIs.
     * @return an array containing all of the elements in this ChoiceDialog's buttons in proper sequence
     */
    public ButtonType[] toArrayButtons() {
        return (ButtonType[])getDialogPane().getButtonTypes().toArray();
    }
    
    /**
     * Returns an array containing all of the elements in this ChoiceDialog's buttons in proper 
     * sequence (from first to last element); 
     * the runtime type of the returned array is that of the specified array. 
     * If the ChoiceDialog's buttons fits in the specified array, it is returned therein. 
     * Otherwise, a new array is allocated with the runtime type of the specified 
     * array and the size of this ChoiceDialog's buttons.
     * @param dest the array into which the elements of this ChoiceDialog's buttons are to be stored, if it 
     * is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collectionan array 
     * containing the elements of this ChoiceDialog's buttons
     * @throws ArrayStoreException if the runtime type of the specified array is 
     * not a supertype of the runtime type of every element in this ChoiceDialog's buttons
     * @throws NullPointerException if the specified array is null
     */
    public ButtonType[] toArrayButtons(ButtonType[] dest) {
        return getDialogPane().getButtonTypes().toArray(dest);
    }
}
