package com.example.myapplication54656494458548

import android.widget.Toast
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

object constant1 {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWER: String = "correct_answers"


    fun getQuestions(): ArrayList<Question> {


        val questionsList1 = ArrayList<Question>()



//        when (level) {
//            // 1
//               "1"-> {
                   val que1 = Question(
                       1,
                       "What does the following function do for a given Linked List with first node as head?\n" +
                               "void fun1(struct node* head)\n" +
                               "{\n" +
                               "  if(head == NULL)\n" +
                               "    return;\n" +
                               "  \n" +
                               "  fun1(head->next);\n" +
                               "  printf(\"%d  \", head->data);\n" +
                               "}",
                       R.drawable.ic_bg,
                       "\n" +
                               "Prints all nodes of linked lists",
                       "Prints all nodes of linked list in reverse order",
                       "Prints alternate nodes of Linked List",

                       "Prints alternate nodes in reverse order",
                       2

                   )

                   questionsList1.add(que1)

                   // 2
                   val que2 = Question(
                       2,
                       "Which of the following points is/are true about Linked List data structure when it is compared with array",
                       R.drawable.ic_bg,
                       "Arrays have better cache locality that can make them better in terms of performance.",

                       "It is easy to insert and delete elements in Linked List",

                       "Random access is not allowed in a typical implementation of Linked Lists",

                       "All of the above",
                       4


                   )

                   questionsList1.add(que2)

                   // 3
                   val que3 = Question(
                       3,
                       "Consider the following function that takes reference to head of a Doubly Linked List as parameter. Assume that a node of doubly linked list has previous pointer as prev and next pointer as next.\n\n" +
                               "\n\n" +
                               "void fun(struct node **head_ref)\n" +
                               "{\n" +
                               "    struct node *temp = NULL;\n" +
                               "    struct node *current = *head_ref;\n" +
                               " \n" +
                               "    while (current !=  NULL)\n" +
                               "    {\n" +
                               "        temp = current->prev;\n" +
                               "        current->prev = current->next;\n" +
                               "        current->next = temp;\n" +
                               "        current = current->prev;\n" +
                               "    }\n" +
                               " \n" +
                               "    if(temp != NULL )\n" +
                               "        *head_ref = temp->prev;\n" +
                               "}" +
                               "\n" +
                               "_________________________________" +
                               "\n" +
                               "Assume that reference of head of following doubly linked list is passed to above function 1 <--> 2 <--> 3 <--> 4 <--> 5 <-->6. What should be the modified linked list after the function call?",
                       R.drawable.ic_bg,
                       "2 <--> 1 <--> 4 <--> 3 <--> 6 <-->5",
                       "5 <--> 4 <--> 3 <--> 2 <--> 1 <-->6.",
                       "6 <--> 5 <--> 4 <--> 3 <--> 2 <--> 1.",
                       "6 <--> 5 <--> 4 <--> 3 <--> 1 <--> 2",
                       3
                   )

                   questionsList1.add(que3)

                   // 4
                   val que4 = Question(
                       4,
                       "Which of the following sorting algorithms can be used to sort a random linked list with minimum time complexity?",
                       R.drawable.ic_bg,
                       "Insertion Sort",
                       "Quick Sort",
                       "Heap Sort",
                       "Merge Sort",
                       4
                   )

                   questionsList1.add(que4)

                   // 5
                   val que5 = Question(
                       5,
                       "The following function reverse() is supposed to reverse a singly linked list. There is one line missing at the end of the function.\n" +
                               "\n" +
                               "/* Link list node */\n" +
                               "struct node\n" +
                               "{\n" +
                               "    int data;\n" +
                               "    struct node* next;\n" +
                               "};\n" +
                               " \n" +
                               "/* head_ref is a double pointer which points to head (or start) pointer \n" +
                               "  of linked list */\n" +
                               "static void reverse(struct node** head_ref)\n" +
                               "{\n" +
                               "    struct node* prev   = NULL;\n" +
                               "    struct node* current = *head_ref;\n" +
                               "    struct node* next;\n" +
                               "    while (current != NULL)\n" +
                               "    {\n" +
                               "        next  = current->next;  \n" +
                               "        current->next = prev;   \n" +
                               "        prev = current;\n" +
                               "        current = next;\n" +
                               "    }\n" +
                               "    /*ADD A STATEMENT HERE*/\n" +
                               "}" +
                               "\n" +
                               "What should be added in place of \"/*ADD A STATEMENT HERE*/\", so that the function correctly reverses a linked list.",
                       R.drawable.ic_bg,
                       "*head_ref = prev;",
                       "*head_ref = current;",
                       "*head_ref = next;",
                       "*head_ref = NULL;",
                       1
                   )

                   questionsList1.add(que5)

                   // 6
                   val que6 = Question(
                       6,
                       "What is the output of following function for start pointing to first node of following linked list? 1->2->3->4->5->6" +
                               "\n" +
                               "void fun(struct node* start)\n" +
                               "{\n" +
                               "  if(start == NULL)\n" +
                               "    return;\n" +
                               "  printf(\"%d  \", start->data); \n" +
                               "  \n" +
                               "  if(start->next != NULL )\n" +
                               "    fun(start->next->next);\n" +
                               "  printf(\"%d  \", start->data);\n" +
                               "}" +
                               "\n",
                       R.drawable.ic_bg,
                       "1 4 6 6 4 1",
                       "1 3 5 1 3 5",
                       "1 2 3 5",
                       "1 3 5 5 3 1",
                       4
                   )

                   questionsList1.add(que6)

                   // 7
                   val que7 = Question(
                       7,
                       "The following C function takes a simply-linked list as input argument. It modifies the list by moving the last element to the front of the list and returns the modified list. Some part of the code is left blank. Choose the correct alternative to replace the blank line." +
                               "\n" +
                               "typedef struct node \n" +
                               "{\n" +
                               "  int value;\n" +
                               "  struct node *next;\n" +
                               "}Node;\n" +
                               "  \n" +
                               "Node *move_to_front(Node *head) \n" +
                               "{\n" +
                               "  Node *p, *q;\n" +
                               "  if ((head == NULL: || (head->next == NULL)) \n" +
                               "    return head;\n" +
                               "  q = NULL; p = head;\n" +
                               "  while (p-> next !=NULL) \n" +
                               "  {\n" +
                               "    q = p;\n" +
                               "    p = p->next;\n" +
                               "  }\n" +
                               "  _______________________________\n" +
                               "  return head;\n" +
                               "}",
                       R.drawable.ic_bg,
                       "q = NULL; p->next = head; head = p;",
                       "q->next = NULL; head = p; p->next = head;",
                       "head = p; p->next = q; q->next = NULL;",
                       "q->next = NULL; p->next = head; head = p;",
                       4
                   )

                   questionsList1.add(que7)

                   // 8
                   val que8 = Question(
                       8,
                       "The following C function takes a single-linked list of integers as a parameter and rearranges the elements of the list. The function is called with the list containing the integers 1, 2, 3, 4, 5, 6, 7 in the given order. What will be the contents of the list after the function completes execution?" +
                               "\n" +
                               "struct node \n" +
                               "{\n" +
                               "  int value;\n" +
                               "  struct node *next;\n" +
                               "};\n" +
                               "void rearrange(struct node *list)\n" +
                               "{\n" +
                               "  struct node *p, * q;\n" +
                               "  int temp;\n" +
                               "  if ((!list) || !list->next) \n" +
                               "      return;\n" +
                               "  p = list;\n" +
                               "  q = list->next;\n" +
                               "  while(q) \n" +
                               "  {\n" +
                               "     temp = p->value;\n" +
                               "     p->value = q->value;\n" +
                               "     q->value = temp;\n" +
                               "     p = q->next;\n" +
                               "     q = p?p->next:0;\n" +
                               "  }\n" +
                               "}",
                       R.drawable.ic_bg,
                       "1,2,3,4,5,6,7",
                       "2,1,4,3,6,5,7",
                       "1,3,2,5,4,7,6",
                       "2,3,4,5,6,7,1",
                       2
                   )

                   questionsList1.add(que8)

                   // 9
                   val que9 = Question(
                       9,
                       "WIn the worst case, the number of comparisons needed to search a singly linked list of length n for a given element is (GATE CS 2002)" +
                               "\n",
                       R.drawable.ic_bg,
                       "log 2 n",
                       "n/2",
                       "log 2 n – 1",
                       "n",
                       4
                   )

                   questionsList1.add(que9)

                   val ques10 = Question(
                       10,
                       "Suppose each set is represented as a linked list with elements in arbitrary order. Which of the operations among union, intersection, membership, cardinality will be the slowest? (GATE CS 2004)" +
                               "\n",
                       R.drawable.ic_bg,
                       "union only",
                       "intersection, membership",
                       "membership, cardinality",
                       "union, intersection",
                       4
                   )
                   questionsList1.add(ques10)
//               }
//
//        }

        return questionsList1
    }
}