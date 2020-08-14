
# initialize with
# myMap <- HashMap$new()
HashMap <- setRefClass("HashMap", 
                       fields = list(hash = "environment"), 
                       methods = list(
                         initialize = function(...) {
                           hash <<- new.env(hash = TRUE, parent = emptyenv(), size = 100L)
                           callSuper(...)
                         },
                         get = function(key) { unname(base::get(key, hash)) },
                         put = function(key, value) { base::assign(key, value, hash) },
                         append = function(key, appendant){
                           if(base::exists(key, hash)){
                             base::assign(key, base::append(unname(base::get(key, hash)) ,appendant), hash)
                           } else {
                             base::assign(key, appendant, hash)
                           }
                         },
                         containsKey = function(key) { base::exists(key, hash) },
                         keySet = function() { base::ls(hash) },
                         toString = function() {
                           keys <- ls(hash)
                           rval <- vector("list", length(keys))
                           names(rval) <- keys
                           for(k in keys){
                             rval[[k]] <- unname(base::get(k, hash))
                           }
                           return(rval)
                         },
                         size = function() { length(hash) },
                         isEmpty = function() { length(hash)==0 },
                         remove = function(key) { .self$hash[[key]] <- NULL }, 
                         clear = function() { hash <<- new.env(hash = TRUE, parent = emptyenv(), size = 100L) }
                       ) 
)
