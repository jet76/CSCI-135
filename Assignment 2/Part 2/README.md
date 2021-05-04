# Part 2: RadarContacts			

You are a communications officer onboard an [E-3 Sentry AWACS](http://en.wikipedia.org/wiki/Boeing_E-3_Sentry) surveillance aircraft. Your radar equipment generates a file containing all radar contacts within your region. Each radar contact is given by its [UTM coordinates](http://en.wikipedia.org/wiki/Universal_Transverse_Mercator_coordinate_system). UTM coordinates consist of two numbers, an easting and a northing. The easting specifies how many meters the contact is to the east of a fixed grid reference location. Similarly the northing is how far the contact is north of the grid reference.  

In addition to the location of every aircraft, your equipment also queries the transponder of all aircraft to obtain their call sign. Only friendly aircraft respond with a call sign. Other unknown or hostile aircraft are assigned a question mark as a call sign. Here is [radar4.txt](radar4.txt), a small example file showing four contacts, two friendly and two unknown:  

```
4  
34754 12029 EJ-475  
38002 11193 CX-120  
11899 28929 ?  
39222 10028 ?  
```

The first line of the example file specifies that there are four contacts in the data file. Each of the remaining lines gives the easting, northing and call sign (in that order). You can assume all eastings and northings are non-negative integers.  

Your job is to write a program RadarContacts.java that first reports the number of friendly and non-friendly contacts in the region. The program then generates a list of warning messages for transmission to all friendly aircraft. The messages inform each friendly aircraft about the distance to any radar contacts that are too close. You program should take two command-line arguments radius and mode. The radius argument specifies how close (in kilometers) another contact must be before a warning is generated. A warning should be generated if the two-dimensional [Euclidean distance](http://en.wikipedia.org/wiki/Euclidean_distance) between the friendly aircraft in question and the contact is less than or equal to radius kilometers. The radius argument can be any non-negative floating-point value (e.g. 1.5).  

The *mode* command-line argument specifies the type of contacts that should be reported:  
* mode 0, only non-friendly contacts are listed  
* mode 1, only friendly contacts are listed  
* mode 2, first non-friendly contacts are listed, followed by friendly contacts  

The output report format first lists the call sign of the friendly aircraft followed by a colon. In mode 0 and mode 2, all non-friendly that are too close are listed (denoted by a question mark) followed by the bogey's distance (in kilometers) in parentheses. In mode 1, all friendly contacts are listed by their known call sign followed by the friendly's distance in parentheses. If a friendly contact does not have any contacts that are too close (base on the mode), it should not appear in the report. All distances should be reported in kilometers rounded to two decimal places. Contacts within the friendly and non-friendly sets for a given aircraft's report can appear in any order. Here are some example runs:  

```console
% java RadarContacts 1.0 2 < radar4.txt  
Friendly aircraft: 2  
Non-friendly aircraft: 2  
```
```console
% java RadarContacts 2.0 2 < radar4.txt  
Friendly aircraft: 2  
Non-friendly aircraft: 2  
CX-120:  
? (1.69)  
```

```console
% java RadarContacts 3.5 2 < radar4.txt  
Friendly aircraft: 2  
Non-friendly aircraft: 2  
EJ-475:  
CX-120 (3.35)  

CX-120:  
? (1.69)  
EJ-475 (3.35)  
```

```console
% java RadarContacts 3.5 1 < radar4.txt  
Friendly aircraft: 2  
Non-friendly aircraft: 2  
EJ-475:  
CX-120 (3.35)  

CX-120:  
EJ-475 (3.35)  
```

```console
% java RadarContacts 3.5 0 < radar4.txt  
Friendly aircraft: 2  
Non-friendly aircraft: 2  
CX-120:  
? (1.69)  
```

Here is some sample output from [radar397.txt](radar397.txt) which contains many more contacts:  

```console
% java RadarContacts 0.5 0 < radar397.txt  
Friendly aircraft: 262  
Non-friendly aircraft: 135  
EW-443:  
? (0.47)  

YN-457:  
? (0.29)  

RO-952:  
? (0.12)  
```

```console
% java RadarContacts 0.75 0 < radar397.txt  
Friendly aircraft: 262  
Non-friendly aircraft: 135  
EW-443:  
? (0.47)  

YN-457:  
? (0.71)  
? (0.29)  

OT-689:  
? (0.75)  

TB-219:  
? (0.70)  

DT-583:  
? (0.58)  

RO-952:  
? (0.12)  

KG-168:  
? (0.70)  
```

```console
% java RadarContacts 0.25 2 < radar397.txt  
Friendly aircraft: 262  
Non-friendly aircraft: 135  
NE-655:  
AR-490 (0.12)  

RO-952:  
? (0.12)  

AR-490:  
NE-655 (0.12)  
```

**How do I check a String variable to see if it is equal to something?** You'll want to use the equals() method. So for example if foo is a String, foo.equals("bar") would return true only if the variable foo contains exactly the string "bar". DO NOT use == to compare String variables! It is not doing what you think and will cause trouble.  

**How can I check the length of String variable?** If the variable foo is a String, you can call foo.length() to return the number of characters in the String. If the String is empty, its length() will be 0.  

**How do I put a return (line-feed) into a String variable?** Put \n inside the double-quotes of a String literal.  

**How do I print a double variable rounded to two decimal places?** You want to use the printf() method. Here is an example: System.out.printf("value = %.2f", foo);  

**How do I create a String variable that has a double rounded to two decimal places?** You want to use the format() method. Here is an example: String bar = String.format("value = %.2f", foo);  

**How do I compute the distance between two points?** You need to use the formula. In this formula p1 and p2 are the coordinates of one contact and q1 and q2 are the coordinates of another. You can take a square root in Java using Math.sqrt(). While there is a math method for taking powers, if you are just squaring a value, it is much more efficient just to use the normal multiplication operator.

---

This README was adapted from an assignment page at Montana Tech: https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/input/
