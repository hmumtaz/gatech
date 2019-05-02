/**
 * CS 2110:  Timed Lab 4
 * Semester: Summer 2016
 * Name:     YOUR NAME HERE
 * GTID:     YOUR GTID HERE
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void merge(int* left, int size_l, int* right, int size_r, int* merged);
void merge_sort(int* arr, int size);

/**
 * Function: main
 * --------------
 * takes in a sequence of positive integers, sorts, and returns them
 *
 * COMPLETE THIS FUNCTION (!)
 */
int main(void)
{
    int num, size, i;
    int* arr;
    int chastise_user = 0;

    /* Prompt the user for the size of the array */
    printf("How many positive integers does your array contain? ");\
    scanf("%d", &size);

    // TODO: What should we do here with 'arr'?
    arr = malloc(sizeof(int) * size);

    /* Prompt the user for their input */
    printf("Enter an array of %d positive integers ('0' to exit): ", size);

    /* Initialize the index */
    i = -1;

    /* Take input from the user and fill the array */
    do {
        /* Get the input, one integer at a time */
        scanf("%d", &num);

        /* If num == '0', we need to exit */
        if (num == 0)
        {
            break;
        }

        /* If we receive negative integer input, quit the program */
        if (num < 0)
        {
            printf("No negative numbers allowed!\n");
            return 0;
        }

        /* Increment to the next index */
        i++;

        /* Dynamically allocate a larger array if the user gave us a bad
         * array size */
        if (i == size)
        {
            /* User gave more input than they said they would >:( */
            chastise_user = 1;
            // TODO: What should we do here?
            int *newArr = malloc(sizeof(int) * (i + 1));
            memcpy(newArr, arr, sizeof(int) * i);
            free(arr);
            arr = newArr;
            size = i + 1;
        }

        /* Copy the new input */
        arr[i] = num;

    } while (1);

    /* Run merge_sort on our array */
    merge_sort(arr, size);

    /* Chastise the user if necessary */
    if (chastise_user)
    {
        printf("You gave the incorrect array size!\n");
        printf("Your array actually contained %d items.\n", size);
    }

    /* Print out the sorted array */
    printf("Sorted: ");

    for (i = 0; i < size; i++)
    {
        printf("%d ", arr[i]);
    }

    printf("\n");

    // TODO: What should we do here?
    free(arr);
    return 0;
}

/**
 * Function: merge_sort
 * -------------------
 * sorts 'arr' with 'size' integers, using the merge sort algorithm.
 * The merge sort algorithm recursively splits the 'arr' into
 * sublists and merges the single element sublists back into a
 * single sorted array.
 *
 * arr:     the array to be sorted
 * size:    the number of integers in the array
 *
 * COMPLETE THIS FUNCTION (!)
 */
void merge_sort(int* arr, int size)
{
    int i;

    /* These are the size(s) of each sublist */
    int s_left, s_right;

    /* These are the two sublists we will use to sort */
    int* left;
    int* right;

    /* If size == 1 then the array is sorted */
    if (size > 1)
    {
        /* Calculate the value(s) of 's_left' and 's_right' */
        s_left = size / 2;
        s_right = size - s_left;

        // TODO: What should we do here with 'left' and 'right'?
        left = malloc(sizeof(int) * s_left);
        right = malloc(sizeof(int) * s_right);

        /* Copy the first half of the elements to 'left' */
        for (i = 0; i < s_left; i++)
        {
            left[i] = arr[i];
        }

        /* Copy the second half of the elements to 'right' */
        for (i = 0; i < s_right; i++)
        {
            right[i] = arr[i + s_left];
        }

        /* Recursively sort both sublists */
        merge_sort(left, s_left);
        merge_sort(right, s_right);

        /* Merge the sorted sublists */
        merge(left, s_left, right, s_right, arr);

        // TODO: What should we do here?
        free(left);
        free(right);
    }
}

/**
 * Function: merge
 * ---------------
 * merges two sorted arrays into one sorted array
 *
 * left:    the first sorted array
 * size_l:  the size of the first sorted array
 * right:   the second sorted array
 * size_r:  the size of the second sorted array
 *
 * DO NOT MODIFY THIS FUNCTION (!)
 */
void merge(int* left, int size_l, int* right, int size_r, int* merged)
{
    int i,j,k;

    i = 0;
    j = 0;
    k = 0;

    /* Merge the 'left' and 'right' arrays into 'merged' */
    while (i < size_l && j < size_r)
    {
        if (left[i] <= right[j])
        {
            merged[k] = left[i];
            i++;
            k++;
        } else {
            merged[k] = right[j];
            j++;
            k++;
        }
    }

    /* Copy remaining elements of 'left' into 'merged' */
    while (i < size_l)
    {
        merged[k] = left[i];
        i++;
        k++;
    }

    /* Copy remaining element of 'right' into 'merged' */
    while (j < size_r)
    {
        merged[k] = right[j];
        j++;
        k++;
    }
}
