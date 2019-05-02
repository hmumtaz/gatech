/**
 * CS 2110 - Spring 2016 - Homework #10
 *
 * @author Hussain Mumtaz
 *
 * deque.c: Complete the functions!
 */

#include <stdlib.h>
#include <stdio.h>
#include "deque.h"

/* The node struct. Has a prev pointer, next pointer, and data. */
/* DO NOT DEFINE ANYTHING OTHER THAN WHAT'S GIVEN OR YOU WILL GET A ZERO */
/* Design consideration: Only this file should know about nodes */
/* Only this file should be manipulating nodes */
/* DO NOT TOUCH THIS DECLARATION, DO NOT PUT IT IN OTHER FILES */
typedef struct dnode
{
    struct dnode* prev; /* Pointer to previous node */
    struct dnode* next; /* Pointer to next node */
    void* data; /* User data */
} node;

/* Do not create any global variables here. Your deque library should obviously
 * work for multiple concurrent deques */

// This function is declared as static since you should only be calling this
// function from inside this file.
static node* create_node(void* data);

/** create_node
  *
  * Helper function that creates a node by allocating memory for it on the heap.
  * Be sure to set its pointers to NULL.
  *
  * @param data a void pointer to data the user wants to store in the deque
  * @return a node
  */
static node* create_node(void* data)
{
  /// @todo Implement changing the return value
  node* newNode;
  newNode = malloc(sizeof(node));
  if (data == NULL)
  {
    newNode->data = NULL;
  }
  else
  { 
  newNode->data = data;
  }
  newNode->prev = NULL;
  newNode->next = NULL;
  return newNode;
}

/** create_deque
  *
  * Creates a deque by allocating memory for it on the heap.
  * Be sure to initialize size to zero and head/tail to NULL.
  *
  * @return an empty deque
  */
deque* create_deque(void)
{
  /// @todo Implement changing the return value!
  deque* newDeque = malloc(sizeof(deque));
  newDeque->size = 0;
  newDeque->head = NULL;
  newDeque->tail = NULL; 
  return newDeque;
}

/** push_front
  *
  * Adds the data to the front of the deque.
  *
  * @param d a pointer to the deque structure.
  * @param data pointer to data the user wants to store in the deque.
  */
void push_front(deque *d, void *data)
{
    /// @todo Implement
    node* n = create_node(data);
    if (d == NULL)
    {
      d = create_deque();
    }
    if (d->size == 0)
    {
      n->next = n;
      n->prev = n; 
    }
    else
    {
      node *oldHead = d->head;
      node *oldPrev = oldHead->prev;
      n->next = oldHead;
      n->prev = oldHead->prev;
      oldHead->prev = n;
      oldPrev->next = n;
    }
    d->head = n;
    d->size ++;
}

/** push_back
  *
  * Adds the data to the back of the deque.
  *
  * @param d a pointer to the deque structure.
  * @param data pointer to data the user wants to store in the deque.
  */
void push_back(deque *d, void *data)
{
    /// @todo Implement
    node *n = create_node(data);
    if (d == NULL)
    {
      d = create_deque();
    }
    if (d->size == 0)
    {
      n->next = n;
      n->prev = n;
      d->head = n;
    }
    else
    {
      node *head = d->head;
      node *oldPrev = head->prev;
      n->next = head;
      n->prev = head->prev;
      head->prev = n;
      oldPrev->next = n;
    }
    d->tail = n;
    d->size ++;
  }

/** front
  *
  * Gets the data at the front of the deque
  * If the deque is empty return NULL.
  *
  * @param d a pointer to the deque
  * @return The data at the first node in the deque or NULL.
  */
void *front(deque *d)
{
    /// @todo Implement changing the return value!
    /// @note you are returning the HEAD's DATA not the head node. Remember, the
    /// user should never deal with the deque nodes.
    if (d == NULL || d->size == 0)
    {
      return 0;
    }
    else
    {
      return d->head->data;
    }
}

/** back
  *
  * Gets the data at the "end" of the deque
  * If the deque is empty return NULL.
  *
  * @param d a pointer to the deque structure
  * @return The data at the last node in the deque or NULL.
  */
void *back(deque *d)
{
    /// @todo Implement changing the return value!
    if (d == NULL || d->size == 0)
    {
      return 0;
    }
    else if (d->size == 1)
    {
      return front(d);
    }
    else
    {
      return d->tail->data;
    }
}

/** get
  *
  * Gets the data at the specified index in the deque
  *
  * @param d a pointer to the deque structure
  * @param index 0-based, starting from the head.
  * @return The data from the specified index in the deque or NULL if index is
  *         out of range of the deque.
  */
void *get(deque *d, int index)
{
    /// @todo Implement changing the return value!
    if (index >= d->size || d == 0)
    {
      return NULL;
    }
    else
    {
      node* cur;
      cur = d->head;
      while (index > 0)
      {
        cur = cur->next;
        index --;
      }
      void* result = cur->data;
      return result;
    }
}

/** contains
  *
  * Traverses the deque, trying to see if the deque contains some data.
  * Since non-NULL values are considered true, this can be used like a boolean
  *
  * The "data" parameter may not necessarily point to the same address as the
  * equal data you are returning from this function, it's just data which the
  * eq_func says is equal. For instance, if you have a deque of person structs:
  *   (Andrew, 26), (Nick, 24), (Collin, 23), (Marie, 23)
  * And you want to return any person whose age is 22, you could create a new
  * person struct (<NULL>, 24) with an eq_func that returns age == 24 and pass
  * that into this function as "data". contains() would then return (Nick, 24)
  *
  * If there are multiple pieces of data in the deque which are equal to the
  * "data" parameter, return any one of them.
  *
  * @param d a pointer to the deque structure
  * @param data The data, to see if it exists in the deque
  * @param eq_func A function written by the user that will tell if two pieces
  *                of data are equal. Returns 0 if equal, something else
  *                otherwise. Imagine subtracting two numbers.
  * @return The data contained in the deque, or NULL if it doesn't exist
  */
void *contains(deque *d, void *data, deque_eq eq_func) {
    /// @todo Implement
    if (d == NULL || eq_func == NULL)
    {
      return NULL;
    }
    int result = 1;
    node* cur = d->head;
    node* nxt;
    for (int i = 0; i < d->size; i++)
    {
      nxt = cur->next;
      result = eq_func(data, cur->data);
      if (result == 0)
      {
        return cur->data;
      }
      cur = nxt;
    }
    return NULL;
}

/** pop_front
  *
  * Removes the node at the front of the deque, and returns its data to the user
  *
  * @param d a pointer to the deque.
  * @return The data in the first node, or NULL if the deque is NULL or empty
  */
void *pop_front(deque *d)
{
    /// @todo Implement
    if (d == NULL || d->size == 0)
    {
      return NULL;
    }
    else
    {
      node* cur = d->head;
      void* result = cur->data;
      d->head = cur->next;
      d->head->prev = NULL;
      d->size --;
      free(cur);
      return result;
    }
}

/** pop_back
  *
  * Removes the node at the end of the deque, and returns its data to the user
  *
  * @param d a pointer to the deque.
  * @return The data in the first node, or NULL if the deque is NULL or empty
  */
void *pop_back(deque *d)
{
    /// @todo Implement
    if (d == NULL || d->size == 0)
    {
      return NULL;
    }
    else if (d->size == 1)
    {
      return pop_front(d);
    }
    else
    {
      node* cur = d->tail;
      void* result = cur->data;
      d->tail = cur->prev;
      d->tail->next = NULL;
      d->size --;
      free(cur);
      return result;
    }
}

/** copy_deque
  *
  * Create a new deque structure, new nodes, and new copies of the data by using
  * the copy function. Its implementation for any test structure must copy
  * EVERYTHING!
  *
  * @param d A pointer to the deque structure to make a copy of
  * @param copy_func A function pointer to a function that makes a copy of the
  *                  data that's being used in this deque, allocating space for
  *                  every part of that data on the heap. This is some function
  *                  you must write yourself for testing, tailored specifically
  *                  to whatever context you're using the deque for in your test
  * @return The deque structure created by copying the old one, or NULL if the
  *         structure passed in is NULL.
  */
deque* copy_deque(deque *d, deque_copy copy_func)
{
	/// @todo implement
  if ((d == NULL || d->size == 0) || copy_func == NULL)
  {
    return NULL;
  }
  else
  {
    deque *newDeque = create_deque();
    node *cur = d->head;
    node *nxt;
    for (int i = 0; i < d->size; i++)
    {
      nxt = cur->next;
      push_back(newDeque, copy_func(cur->data));
      cur = nxt;
    }
    return newDeque;
  }
}

/** size
  *
  * Gets the size of the deque
  *
  * @param d a pointer to the deque structure
  * @return The size of the deque
  */
int size(deque *d)
{
    ///@note simply return the size of the deque. It's that easy!
    if (d == NULL)
    {
      return 0;
    }
    return d->size;
}

/** remove_if
  *
  * Removes all nodes whose data when passed into the predicate function returns
  * true
  *
  * @param d a pointer to the deque structure
  * @param pred_func a pointer to a function that when it returns true, it
  *                  should remove the element from the deque.
  * @param free_func a pointer to a function that is responsible for freeing the
  *                  node's data
  * @return the number of nodes that were removed.
  */
int remove_if(deque *d, deque_pred pred_func, deque_op free_func)
{
    /// @todo Implement changing the return value!
    /// @note remember to also free all nodes you remove.
    /// @note be sure to call pred_func on the NODES DATA to check if the node
    ///       needs to be removed.
    /// @note free_func is a function that is responsible for freeing the node's
    ///       data only.
    if ((d == NULL || d->size == 0) || (pred_func == NULL || free_func == NULL))
    {
      return 0;
    }

    int removed = 0;
    node *cur = d->head;
    node *next;
    node *prev;

    for (int i = 0; i < d->size; i++)
    {
      next = cur->next;
      prev = cur->prev;
      if(pred_func(cur->data))
      {
        if (d->size == 1)
        {
          d->head = NULL;
          d->tail = NULL;
        }
        else if (cur == d->head)
        {
          next->prev = NULL;
          d->head = next;
        }
        else if (cur == d->tail)
        {
          prev->next = NULL;
          d->tail = prev;
        }
        else
        {
          prev->next = cur->next;
          next->prev = cur->prev;
        }
        free_func(cur->data);
        free(cur);
        removed++;
      }
      cur = next;
    }
    d->size -= removed;
    return removed;
}

/** is_empty
  *
  * Checks to see if the deque is empty.
  *
  * @param d a pointer to the deque structure
  * @return 1 if the deque is indeed empty, or 0 otherwise.
  */
int is_empty(deque *d)
{
    /// @note an empty deque should have a size of zero and head points to NULL.
    if (d == NULL)
    {
      return 0;
    }
    if (d->size == 0 && d->head == NULL)
    {
      return 1;
    }
    return 0;
}

/** empty_deque
  *
  * Empties the deque. After this is called, the deque should be empty.
  * This does not free the deque struct itself, just all nodes and data within.
  *
  * @param d a pointer to the deque structure
  * @param free_func function used to free the nodes' data.
  */
void empty_deque(deque *d, deque_op free_func)
{
    /// @todo Implement
    /// @note Free all of the nodes, not the deque structure itself.
    /// @note do not free the deque structure itself.
    if ((d == NULL || d->size == 0) || free_func == 0)
    {
      return;
    }
    else
    {
      node *cur = d->head;
      node *nxt;
      for (int i = 0; i < d->size; i++)
      {
        nxt = cur->next;
        free_func(cur->data);
        free(cur);
        cur = nxt;
      }
      d->size = 0;
    }
}

/** traverse
  *
  * Traverses the deque, calling a function on each node's data.
  *
  * @param d a pointer to the deque structure
  * @param do_func a function that does something to each node's data.
  */
void traverse(deque *d, deque_op do_func)
{
    /// @todo Implement
  if ((d == NULL || d->size == 0) || do_func == NULL)
  {
    return;
  }
  node *cur = d->head;
  for (int i = 0; i < d->size; i++)
  {
    do_func(cur->data);
    cur = cur->next;
  }
}
