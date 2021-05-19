# Abigail Castro - abc3gnm
# Chapter 14 Summary and code

# Chapter 14 Strings
# string manipulation in R

library(tidyverse)

# 14.2 String basics

string1 <- "This is a string"
string2 <- 'If I want to include a "quote" inside a string, I use single quotes'

# If you forget to close a quote, you’ll see +, the continuation character:
# If this happens to you, press Escape and try again!

# To include a literal single or double quote in a string you can use \ to “escape” it:
double_quote <- "\"" # or '"'
single_quote <- '\'' # or "'"

# To see the raw contents of the string, use writeLines():
x <- c("\"", "\\")
x
#> [1] "\"" "\\"
writeLines(x)
#> "
#> \

# "\n", newline
# "\t", tab
# complete list by requesting help on ": ?'"', or ?"'"
# "\u00b5", this is a way of writing non-English characters that works on all platforms:
x <- "\u00b5"
x
#> [1] "µ"

# character vector
c("one", "two", "three")
#> [1] "one"   "two"   "three"

# 14.2.1 String length
# str_length() tells you the number of characters in a string:
str_length(c("a", "R for data science", NA))
#> [1]  1 18 NA

# 14.2.2 Combining strings
# To combine two or more strings, use str_c():
str_c("x", "y")
#> [1] "xy"
str_c("x", "y", "z")
#> [1] "xyz"

# Use the sep argument to control how they’re separated:
str_c("x", "y", sep = ", ")
#> [1] "x, y"

# to print as "NA", use str_replace_na():
x <- c("abc", NA)
str_c("|-", x, "-|")
#> [1] "|-abc-|" NA
str_c("|-", str_replace_na(x), "-|")
#> [1] "|-abc-|" "|-NA-|"

# str_c() is vectorised, and it automatically recycles shorter vectors to the same length as the longest:
str_c("prefix-", c("a", "b", "c"), "-suffix")
#> [1] "prefix-a-suffix" "prefix-b-suffix" "prefix-c-suffix"

# Objects of length 0 are silently dropped. This is particularly useful in conjunction with if:
name <- "Hadley"
time_of_day <- "morning"
birthday <- FALSE

str_c(
  "Good ", time_of_day, " ", name,
  if (birthday) " and HAPPY BIRTHDAY",
  "."
)
#> [1] "Good morning Hadley."

# To collapse a vector of strings into a single string, use collapse:
str_c(c("x", "y", "z"), collapse = ", ")
#> [1] "x, y, z"

# 14.2.3 Subsetting strings
# str_sub() takes start and end arguments which give the (inclusive) position of the substring:
x <- c("Apple", "Banana", "Pear")
str_sub(x, 1, 3)
#> [1] "App" "Ban" "Pea"
# negative numbers count backwards from end
str_sub(x, -3, -1)
#> [1] "ple" "ana" "ear"

# str_sub() won’t fail if the string is too short: it will just return as much as possible:
str_sub("a", 1, 5)
#> [1] "a"

# use the assignment form of str_sub() to modify strings:
str_sub(x, 1, 1) <- str_to_lower(str_sub(x, 1, 1))
x
#> [1] "apple"  "banana" "pear"

# 14.2.4 Locales
# You can pick which set of rules to use by specifying a locale:
# Turkish has two i's: with and without a dot, and it
# has a different rule for capitalising them:
str_to_upper(c("i", "ı"))
#> [1] "I" "I"
str_to_upper(c("i", "ı"), locale = "tr")
#> [1] "İ" "I"

# you may want to use str_sort() and str_order() which take an additional locale argument:
x <- c("apple", "eggplant", "banana")

str_sort(x, locale = "en")  # English
#> [1] "apple"    "banana"   "eggplant"

str_sort(x, locale = "haw") # Hawaiian
#> [1] "apple"    "eggplant" "banana"


# 14.3 Matching patterns with regular expressions
# Regexps are a very terse language that allow you to describe patterns in strings.
# 14.3.1 Basic matches
x <- c("apple", "banana", "pear")
str_view(x, "an")

# The next step up in complexity is ., which matches any character (except a newline):
str_view(x, ".a.")

# To create the regular expression, we need \\
dot <- "\\."

# But the expression itself only contains one:
writeLines(dot)
#> \.

# And this tells R to look for an explicit .
str_view(c("abc", "a.c", "bef"), "a\\.c")

x <- "a\\b"
writeLines(x)
#> a\b

str_view(x, "\\\\")

# 14.3.2 Anchors
# ^ to match the start of the string.
# $ to match the end of the string.
x <- c("apple", "banana", "pear")
str_view(x, "^a")
str_view(x, "a$")

# To force a regular expression to only match a complete string, anchor it with both ^ and $:
x <- c("apple pie", "apple", "apple cake")
str_view(x, "apple")
str_view(x, "^apple$")

#  For example, I’ll search for \bsum\b to avoid matching summarise, summary, rowsum and so on.


# 14.3.3 Character classes and alternatives
# \d: matches any digit.
# \s: matches any whitespace (e.g. space, tab, newline).
# [abc]: matches a, b, or c.
# [^abc]: matches anything except a, b, or c.
# Look for a literal character that normally has special meaning in a regex
str_view(c("abc", "a.c", "a*c", "a c"), "a[.]c")
str_view(c("abc", "a.c", "a*c", "a c"), ".[*]c")
str_view(c("abc", "a.c", "a*c", "a c"), "a[ ]")

# use alternation to pick between one or more alternative patterns
str_view(c("grey", "gray"), "gr(e|a)y")

# 14.3.4 Repetition
# ?: 0 or 1
# +: 1 or more
# *: 0 or more
x <- "1888 is the longest year in Roman numerals: MDCCCLXXXVIII"
str_view(x, "CC?")
str_view(x, "CC+")
str_view(x, 'C[LX]+')

#You can also specify the number of matches precisely:
  #{n}: exactly n
  #{n,}: n or more
  #{,m}: at most m
  #{n,m}: between n and m
str_view(x, "C{2}")
str_view(x, "C{2,}")
str_view(x, "C{2,3}")

# By default these matches are “greedy”: they will match the longest string possible. You can make them “lazy”, matching the shortest string possible by putting a ? after them.
str_view(x, 'C{2,3}?')
str_view(x, 'C[LX]+?')

# 14.3.5 Grouping and backreferences
# A capturing group stores the part of the string matched by the part of the regular expression inside the parentheses.
# the following regular expression finds all fruits that have a repeated pair of letters
str_view(fruit, "(..)\\1", match = TRUE)

# 14.4 Tools
# 14.4.1 Detect matches
# If a character vector matches a pattern, use str_detect()
x <- c("apple", "banana", "pear")
str_detect(x, "e")
#> [1]  TRUE FALSE  TRUE

# How many common words start with t?
sum(str_detect(words, "^t"))
#> [1] 65
# What proportion of common words end with a vowel?
mean(str_detect(words, "[aeiou]$"))
#> [1] 0.2765306

# Find all words containing at least one vowel, and negate
no_vowels_1 <- !str_detect(words, "[aeiou]")
# Find all words consisting only of consonants (non-vowels)
no_vowels_2 <- str_detect(words, "^[^aeiou]+$")
identical(no_vowels_1, no_vowels_2)
#> [1] TRUE

words[str_detect(words, "x$")]
#> [1] "box" "sex" "six" "tax"
str_subset(words, "x$")
#> [1] "box" "sex" "six" "tax"

df <- tibble(
  word = words, 
  i = seq_along(word)
)
df %>% 
  filter(str_detect(word, "x$"))
#> # A tibble: 4 x 2
#>   word      i
#>   <chr> <int>
#> 1 box     108
#> 2 sex     747
#> 3 six     772
#> 4 tax     841

# str_count(): rather than a simple yes or no, it tells you how many matches there are in a string:
x <- c("apple", "banana", "pear")
str_count(x, "a")
#> [1] 1 3 1

# On average, how many vowels per word?
mean(str_count(words, "[aeiou]"))
#> [1] 1.991837

df %>% 
  mutate(
    vowels = str_count(word, "[aeiou]"),
    consonants = str_count(word, "[^aeiou]")
  )
#> # A tibble: 980 x 4
#>   word         i vowels consonants
#>   <chr>    <int>  <int>      <int>
#> 1 a            1      1          0
#> 2 able         2      2          2
#> 3 about        3      3          2
#> 4 absolute     4      4          4
#> 5 accept       5      2          4
#> 6 account      6      3          4
#> # … with 974 more rows

# 14.4.2 Extract matches
# To extract the actual text of a match, use str_extract()
length(sentences)
#> [1] 720
head(sentences)
#> [1] "The birch canoe slid on the smooth planks." 
#> [2] "Glue the sheet to the dark blue background."
#> [3] "It's easy to tell the depth of a well."     
#> [4] "These days a chicken leg is a rare dish."   
#> [5] "Rice is often served in round bowls."       
#> [6] "The juice of lemons makes fine punch."

colours <- c("red", "orange", "yellow", "green", "blue", "purple")
colour_match <- str_c(colours, collapse = "|")
colour_match
#> [1] "red|orange|yellow|green|blue|purple"

has_colour <- str_subset(sentences, colour_match)
matches <- str_extract(has_colour, colour_match)
head(matches)
#> [1] "blue" "blue" "red"  "red"  "red"  "blue"

more <- sentences[str_count(sentences, colour_match) > 1]
str_view_all(more, colour_match)


str_extract(more, colour_match)
#> [1] "blue"   "green"  "orange"

str_extract_all(more, colour_match)
#> [[1]]
#> [1] "blue" "red" 
#> 
#> [[2]]
#> [1] "green" "red"  
#> 
#> [[3]]
#> [1] "orange" "red"

str_extract_all(more, colour_match, simplify = TRUE)
#>      [,1]     [,2] 
#> [1,] "blue"   "red"
#> [2,] "green"  "red"
#> [3,] "orange" "red"

x <- c("a", "a b", "a b c")
str_extract_all(x, "[a-z]", simplify = TRUE)
#>      [,1] [,2] [,3]
#> [1,] "a"  ""   ""  
#> [2,] "a"  "b"  ""  
#> [3,] "a"  "b"  "c"

# 14.4.3 Grouped matches
noun <- "(a|the) ([^ ]+)"

has_noun <- sentences %>%
  str_subset(noun) %>%
  head(10)
has_noun %>% 
  str_extract(noun)
#>  [1] "the smooth" "the sheet"  "the depth"  "a chicken"  "the parked"
#>  [6] "the sun"    "the huge"   "the ball"   "the woman"  "a helps"

has_noun %>% 
  str_match(noun)
#>       [,1]         [,2]  [,3]     
#>  [1,] "the smooth" "the" "smooth" 
#>  [2,] "the sheet"  "the" "sheet"  
#>  [3,] "the depth"  "the" "depth"  
#>  [4,] "a chicken"  "a"   "chicken"
#>  [5,] "the parked" "the" "parked" 
#>  [6,] "the sun"    "the" "sun"    
#>  [7,] "the huge"   "the" "huge"   
#>  [8,] "the ball"   "the" "ball"   
#>  [9,] "the woman"  "the" "woman"  
#> [10,] "a helps"    "a"   "helps"

tibble(sentence = sentences) %>% 
  tidyr::extract(
    sentence, c("article", "noun"), "(a|the) ([^ ]+)", 
    remove = FALSE
  )
#> # A tibble: 720 x 3
#>   sentence                                    article noun   
#>   <chr>                                       <chr>   <chr>  
#> 1 The birch canoe slid on the smooth planks.  the     smooth 
#> 2 Glue the sheet to the dark blue background. the     sheet  
#> 3 It's easy to tell the depth of a well.      the     depth  
#> 4 These days a chicken leg is a rare dish.    a       chicken
#> 5 Rice is often served in round bowls.        <NA>    <NA>   
#> 6 The juice of lemons makes fine punch.       <NA>    <NA>   
#> # … with 714 more rows

# 14.4.4 Replacing matches
x <- c("apple", "pear", "banana")
str_replace(x, "[aeiou]", "-")
#> [1] "-pple"  "p-ar"   "b-nana"
str_replace_all(x, "[aeiou]", "-")
#> [1] "-ppl-"  "p--r"   "b-n-n-"

x <- c("1 house", "2 cars", "3 people")
str_replace_all(x, c("1" = "one", "2" = "two", "3" = "three"))
#> [1] "one house"    "two cars"     "three people"

sentences %>% 
  str_replace("([^ ]+) ([^ ]+) ([^ ]+)", "\\1 \\3 \\2") %>% 
  head(5)
#> [1] "The canoe birch slid on the smooth planks." 
#> [2] "Glue sheet the to the dark blue background."
#> [3] "It's to easy tell the depth of a well."     
#> [4] "These a days chicken leg is a rare dish."   
#> [5] "Rice often is served in round bowls."

# 14.4.5 Splitting
# Use str_split() to split a string up into pieces
sentences %>%
  head(5) %>% 
  str_split(" ")
#> [[1]]
#> [1] "The"     "birch"   "canoe"   "slid"    "on"      "the"     "smooth" 
#> [8] "planks."
#> 
#> [[2]]
#> [1] "Glue"        "the"         "sheet"       "to"          "the"        
#> [6] "dark"        "blue"        "background."
#> 
#> [[3]]
#> [1] "It's"  "easy"  "to"    "tell"  "the"   "depth" "of"    "a"     "well."
#> 
#> [[4]]
#> [1] "These"   "days"    "a"       "chicken" "leg"     "is"      "a"      
#> [8] "rare"    "dish."  
#> 
#> [[5]]
#> [1] "Rice"   "is"     "often"  "served" "in"     "round"  "bowls."

"a|b|c|d" %>% 
  str_split("\\|") %>% 
  .[[1]]
#> [1] "a" "b" "c" "d"

sentences %>%
  head(5) %>% 
  str_split(" ", simplify = TRUE)
#>      [,1]    [,2]    [,3]    [,4]      [,5]  [,6]    [,7]     [,8]         
#> [1,] "The"   "birch" "canoe" "slid"    "on"  "the"   "smooth" "planks."    
#> [2,] "Glue"  "the"   "sheet" "to"      "the" "dark"  "blue"   "background."
#> [3,] "It's"  "easy"  "to"    "tell"    "the" "depth" "of"     "a"          
#> [4,] "These" "days"  "a"     "chicken" "leg" "is"    "a"      "rare"       
#> [5,] "Rice"  "is"    "often" "served"  "in"  "round" "bowls." ""           
#>      [,9]   
#> [1,] ""     
#> [2,] ""     
#> [3,] "well."
#> [4,] "dish."
#> [5,] ""

fields <- c("Name: Hadley", "Country: NZ", "Age: 35")
fields %>% str_split(": ", n = 2, simplify = TRUE)
#>      [,1]      [,2]    
#> [1,] "Name"    "Hadley"
#> [2,] "Country" "NZ"    
#> [3,] "Age"     "35"

x <- "This is a sentence.  This is another sentence."
str_view_all(x, boundary("word"))


str_split(x, " ")[[1]]
#> [1] "This"      "is"        "a"         "sentence." ""          "This"     
#> [7] "is"        "another"   "sentence."
str_split(x, boundary("word"))[[1]]
#> [1] "This"     "is"       "a"        "sentence" "This"     "is"       "another" 
#> [8] "sentence"

# 14.4.6 Find matches
# str_locate() and str_locate_all() give you the starting and ending positions of each match.

# 14.5 Other types of pattern
# The regular call:
str_view(fruit, "nana")
# Is shorthand for
str_view(fruit, regex("nana"))

str_view(bananas, regex("banana", ignore_case = TRUE))

# multiline = TRUE allows ^ and $ to match the start and end of each line rather than the start and end of the complete string.
x <- "Line 1\nLine 2\nLine 3"
str_extract_all(x, "^Line")[[1]]
#> [1] "Line"
str_extract_all(x, regex("^Line", multiline = TRUE))[[1]]
#> [1] "Line" "Line" "Line"

# comments = TRUE allows you to use comments and white space to make complex regular expressions more understandable. 
phone <- regex("
  \\(?     # optional opening parens
  (\\d{3}) # area code
  [) -]?   # optional closing parens, space, or dash
  (\\d{3}) # another three numbers
  [ -]?    # optional space or dash
  (\\d{3}) # three more numbers
  ", comments = TRUE)

str_match("514-791-8141", phone)
#>      [,1]          [,2]  [,3]  [,4] 
#> [1,] "514-791-814" "514" "791" "814"

# dotall = TRUE allows . to match everything, including \n.

# fixed(): matches exactly the specified sequence of bytes.
microbenchmark::microbenchmark(
fixed = str_detect(sentences, fixed("the")),
regex = str_detect(sentences, "the"),
times = 20
)
#> Unit: microseconds
#>   expr     min       lq     mean   median       uq     max neval
#>  fixed 100.392 101.3465 118.7986 105.9055 108.8545 367.118    20
#>  regex 346.595 349.1145 353.7308 350.2785 351.4135 403.057    20

a1 <- "\u00e1"
a2 <- "a\u0301"
c(a1, a2)
#> [1] "á" "á"
a1 == a2
#> [1] FALSE

str_detect(a1, fixed(a2))
#> [1] FALSE
str_detect(a1, coll(a2))
#> [1] TRUE

# That means you also need to be aware of the difference
# when doing case insensitive matches:
i <- c("I", "İ", "i", "ı")
i
#> [1] "I" "İ" "i" "ı"

str_subset(i, coll("i", ignore_case = TRUE))
#> [1] "I" "i"
str_subset(i, coll("i", ignore_case = TRUE, locale = "tr"))
#> [1] "İ" "i"

stringi::stri_locale_info()
#> $Language
#> [1] "en"
#> 
#> $Country
#> [1] "US"
#> 
#> $Variant
#> [1] ""
#> 
#> $Name
#> [1] "en_US"

# As you saw with str_split() you can use boundary() to match boundaries.
x <- "This is a sentence."
str_view_all(x, boundary("word"))
str_extract_all(x, boundary("word"))
#> [[1]]
#> [1] "This"     "is"       "a"        "sentence"

# 14.6 Other uses of regular expressions
# apropos() searches all objects available from the global environment. This is useful if you can’t quite remember the name of the function.
apropos("replace")
#> [1] "%+replace%"       "replace"          "replace_na"       "setReplaceMethod"
#> [5] "str_replace"      "str_replace_all"  "str_replace_na"   "theme_replace"

# dir() lists all the files in a directory. 
head(dir(pattern = "\\.Rmd$"))
#> [1] "communicate-plots.Rmd" "communicate.Rmd"       "datetimes.Rmd"        
#> [4] "EDA.Rmd"               "explore.Rmd"           "factors.Rmd"

# 14.7 stringi
# stringr is built on top of the stringi package. stringr is useful when you’re 
# learning because it exposes a minimal set of functions, which have been carefully picked to handle the most common string manipulation functions. stringi, on the other hand, is designed to be comprehensive. It contains almost every function you might ever need: stringi has 250 functions to stringr’s 49.











