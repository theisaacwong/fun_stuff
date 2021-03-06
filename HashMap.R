# initialize with
# myMap <- HashMap$new()
HashMap <- setRefClass("HashMap", 
                       fields = list(hash = "environment"), 
                       methods = list(
                         initialize = function(...) {
                           hash <<- new.env(hash = TRUE, parent = emptyenv(), size = 1000L)
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
                         populate = function(dataframe, keyCol, valCol){
                           if(is.character(keyCol) | is.character(valCol)){
                             keyCol = colnames(dataframe)[which(colnames(dataframe) == keyCol) ]
                             valCol = colnames(dataframe)[which(colnames(dataframe) == valCol) ]
                           }
                           for(i in 1:nrow(dataframe)){
                             base::assign(dataframe[i, keyCol], dataframe[i, valCol], hash)
                           }
                         },
                         populate_append = function(dataframe, keyCol, valCol){
                           if(is.character(keyCol) | is.character(valCol)){
                             keyCol = colnames(dataframe)[which(colnames(dataframe) == keyCol) ]
                             valCol = colnames(dataframe)[which(colnames(dataframe) == valCol) ]
                           }
                           for(i in 1:nrow(dataframe)){
                             key <- dataframe[i, keyCol]
                             appendant <- dataframe[i, valCol]
                             if(base::exists(key, hash)){
                               base::assign(key, base::append(unname(base::get(key, hash)) ,appendant), hash)
                             } else {
                               base::assign(key, appendant, hash)
                             }
                           }
                         },
                         head = function(){
                           keys <- utils::head(ls(hash))
                           rval <- vector("list", length(keys))
                           names(rval) <- keys
                           for(k in keys){
                             rval[[k]] <- unname(base::get(k, hash))
                           }
                           return(rval)
                         },
                         random = function(n=10){
                           keys <- sample(ls(hash), n)
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
                         clear = function() { hash <<- new.env(hash = TRUE, parent = emptyenv(), size = 1000L) }
                       ) 
)
