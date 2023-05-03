# 6th Assignment Report

![](https://github.com/kianaghamsari/Second-Assignment/blob/develop/uni.png)

## Kiana Ghamsari - 400222079


# Introduction 

The purpose of this project is to review the concepts of multithreaded programming and utilize them correctly.


# Design and Implementation

The following exercises have been completed:
* `CPU Simulator`: The `sort()` function has been implemented to sort tasks based on their time and then a for loop is created to create, run, and wait for them to complete.
* `Find Multiples`: An array list of unique integers has been created to store multiples of 3, 5, and 7, each handled by 3 separate tasks. Then, the sum of them will be returned. The `synchronized` keyword is used to handle race conditions.
* `Use Interrupts`: The interruptions have been handled in each task, and, each thread is terminated after 3000 milliseconds using the `interrupt()` method.


# Conclusion

Threads allow a program to operate more efficiently by doing multiple things at the same time.
Multithreading allows concurrent execution of two or more parts of a program. In this case, handling race conditions is important. They occur when two threads operate on the same object without proper synchronization and their operation interleaves on each other.
An easy way to handle them is to use the `synchronized` keyword.
