# Hotel-Booking-System
Hotel booking system design in Java. Completed as part of an interview process for potential full-time employment. The two use-cases given were as follows:

1. Determine if a room of a given type is available on a given date.
2. Book a room of some given type for a particular range of dates.

The program can be run from the main class and method using console input. Initially, the user must define how many of each of the four (arbitrary) room types are
available in the "hotel" in question. If more room types exist, one must update the enumerated class RoomType.java.

Once the user has defined the number of rooms of each type, the program allows the user to type '1' to check availability of a given room type on a given date or '2'
to book a room type over some specified range. Input validation is present for all console inputs, and I've done my best to format the console output to make it as straightforward
and visually appealing as possible.

The backend storage and searching/sorting is all handled by a Red-Black Binary Search Tree, following from code I studied in University (via Sedgewick & Wayne's 'Algorithms: Fourth Edition').
I've cited this code in the doc itself, though I made a few minor edits (and only used functions that I knew would be necessary for the given use cases).

This project was written using Eclipse, and can therefore be opened as an Eclipse project. All tests were written and run using JUnit5, and all Java code was compiled using JavaSE-17.

If you have any questions about any design decisions made, please feel free to reach out by email and I'd be happy to discuss them with you :)
