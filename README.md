# fun_stuff
Jesus Game: https://en.wikipedia.org/wiki/Wikipedia:Wiki_Game
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
