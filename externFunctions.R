myself <- "/path/to/this/file"
file.lines <- paste(scan(myself, what=character(), skip=12, sep='\n'), collapse='\n')
cmds <- parse(textConnection(file.lines))
assign.funs <- sapply(cmds, function(x) {
  if(x[[1]]=="<-") {
    if(x[[3]][[1]]=="function") {
      return(TRUE)
    }
  }
  return(FALSE)
})
eval(cmds[assign.funs])


helloWorld()

helloWorld() <- function(){ print("Hello There") }
