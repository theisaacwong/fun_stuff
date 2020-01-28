# fun_stuff
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


## externFunctions.R (NSFW)

R will not let you use functions before you declare them, unlike languages such as C and Java, which let you use a function that you define later on in your code. I want to be able to use functions before declaring them. Prime Directives of Good R Coding says that to load functions you should have those functions in a separate file and use source() to load them. I don't like this; having two files to manage is twice the headache of having a single file. By pasting this snippet of code at the top of your R script, you can basically have your R script source() itself. Only function declarations are sourced. Now, you can be free to use a function which you define later on in your code. 

