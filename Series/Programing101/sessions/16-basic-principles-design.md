# Basic principles of software design

## Script / Outline (60 mins) (_video coming soon_)
1. Mental constructs for dealing with complexity
   1. Dependency management:
      1. What is a dependency? ([check it here](https://www.uml-diagrams.org/dependency.html))
      2. Why is it important?
      3. Important take away: __Make dependencies explicit!__
   2. [Information hidding](https://en.wikipedia.org/wiki/Information_hiding)
      1. What is it?
      2. Why is it important?
      3. [Visibility of Kotlin's top level constructs](https://kotlinlang.org/docs/reference/visibility-modifiers.html#visibility-modifiers)
2. Principles of software design: introduction
   1. Do not Repeat Yourself ([DRY](https://wiki.c2.com/?DontRepeatYourself))
   2. Single Responsibility Principle ([SRP](https://blog.cleancoder.com/uncle-bob/2014/05/08/SingleReponsibilityPrinciple.html))
3. Demos: 
   1. Review current design of Pong, fixing it it needed
   2. Adding sound to Pong: 
      1. Can we do it without breaking our design?
      2. To be considered:
         1. How to play sounds? 
         2. And, most importantly, __when__ and __where__ should we play them?

## Pong design considerarions: (_coming soon_)