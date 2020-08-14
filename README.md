# fun_stuff
## HashMap
a java-like implementation of a hashmap using R's reference class. Keys can only be strings, but values can be of any type.
<pre>
> myHash <- HashMap$new()
> myHash$put("hello", "there")
> myHash$get("hello")
[1] "there"
> 
> anotherHash <- HashMap$new()
> anotherHash$put("general", "kenobi")
> 
> myHash$put("2nd_hash", anotherHash)
> myHash$get("2nd_hash")$get("general")
[1] "kenobi"
> 
> myHash$get("2nd_hash")$put("you", "are a bold one")
> anotherHash$get("you")
[1] "are a bold one"
</pre>



## Jesus Game: https://en.wikipedia.org/wiki/Wikipedia:Wiki_Game
Find Jesus from a given wikipedia page using only wikipedia links

<pre>
> java -jar JesusGame --help
Usage: java -jar JesusGame [OPTIONs]
From a starting webpage, find a series of links to  reach a destination webpage
Usage example:
java -jar JesusGame --start https://en.wikipedia.org/wiki/DNA --end https://en.wikipedia.org/wiki/Tea --depth 10

Mandatory arguments:
-s,     --start         starting URL

Options:
-e,     --end           ending URL to reach, default: https://en.wikipedia.org/wiki/Jesus
-d,     --depth         maximum depth to search, default: 7
</pre>



## MyFirstCalculator

Do simple math on the command line! supports +, -, /, and *
<pre> > java myFirstCalculator 1 + 1 
   2 </pre>
Unfortunately, I only support inputing integers up to 50. But I'm working on increasing support for larger numbers - typing everything just takes so much time!

## expr.R

Using expr on linux is painful. Using R syntax instead is easier. Just alias this script in your .bashrc file and use R to evaluate expressions the same way.
<pre>
> expr 1+1
  1+1
> echo 'alias rexpr="Rscript expr.R"' >> ~/.bashrc
> source ~/.bashrc
> rexpr 1+1
  2
</pre>


## externFunctions.R (NSFW)

R will not let you use functions before you declare them, unlike languages such as C and Java, which let you use a function that you define later on in your code. I want to be able to use functions before declaring them. Prime Directives of Good R Coding says that to load functions you should have those functions in a separate file and use source() to load them. I don't like this; having two files to manage is twice the headache of having a single file. By pasting this snippet of code at the top of your R script, you can basically have your R script source() itself. Only function declarations are sourced. Now, you can be free to use a function which you define later on in your code. 

