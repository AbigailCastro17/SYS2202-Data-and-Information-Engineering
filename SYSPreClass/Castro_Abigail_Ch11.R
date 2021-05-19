# Abigail Castro - abc3gnm
# Chapter 11 Summary and code

# Chapter 11 Data Transformation
# learn how to read plain-text rectangular files into R

library(tidyverse)

# 11.2 Getting started
# read_csv() reads comma delimited files
# read_csv2() reads semicolon separated files
# read_tsv() reads tab delimited files
# read_delim() reads in files with any delimiter
# read_fwf() reads fixed width files
#  specify fields either by their widths with fwf_widths() 
  # or their position with fwf_positions()
# read_table() reads a common variation of fixed width files 
  # where columns are separated by white space
# read_log() reads Apache style log files

#  first argument to read_csv() is the path to the file to read
heights <- read_csv("data/heights.csv")
#> 
#> ── Column specification ────────────────────────────────────────────────────────
#> cols(
#>   earn = col_double(),
#>   height = col_double(),
#>   sex = col_character(),
#>   ed = col_double(),
#>   age = col_double(),
#>   race = col_character()
#> )

# inline csv file
read_csv("a,b,c
1,2,3
4,5,6")
#> # A tibble: 2 x 3
#>       a     b     c
#>   <dbl> <dbl> <dbl>
#> 1     1     2     3
#> 2     4     5     6

# use skip = n to skip the first n lines; or use comment = "#" to drop
# all lines that start with (e.g.) #
read_csv("The first line of metadata
  The second line of metadata
  x,y,z
  1,2,3", skip = 2)
#> # A tibble: 1 x 3
#>       x     y     z
#>   <dbl> <dbl> <dbl>
#> 1     1     2     3

read_csv("# A comment I want to skip
  x,y,z
  1,2,3", comment = "#")
#> # A tibble: 1 x 3
#>       x     y     z
#>   <dbl> <dbl> <dbl>
#> 1     1     2     3

# You can use col_names = FALSE to tell read_csv() not to treat the first row 
# as headings, and instead label them sequentially from X1 to Xn
read_csv("1,2,3\n4,5,6", col_names = FALSE)
#> # A tibble: 2 x 3
#>      X1    X2    X3
#>   <dbl> <dbl> <dbl>
#> 1     1     2     3
#> 2     4     5     6

# "\n" is a convenient shortcut for adding a new line
read_csv("1,2,3\n4,5,6", col_names = c("x", "y", "z"))
#> # A tibble: 2 x 3
#>       x     y     z
#>   <dbl> <dbl> <dbl>
#> 1     1     2     3
#> 2     4     5     6

# na: this specifies the value (or values) that are used to 
# represent missing values in your file
read_csv("a,b,c\n1,2,.", na = ".")
#> # A tibble: 1 x 3
#>       a     b c    
#>   <dbl> <dbl> <lgl>
#> 1     1     2 NA

# 11.2.2 Compared to base R
# readr vs base function
  # 10x faster
  # produces tibbles, they don't convert character vectors to factors, 
    # use row names, or munge the column names
  # reproducible

# 11.3 Parsing a vector
# parse_*() functions take a character vector and return a more specialised 
  # vector like a logical, integer, or date
str(parse_logical(c("TRUE", "FALSE", "NA")))
#>  logi [1:3] TRUE FALSE NA
str(parse_integer(c("1", "2", "3")))
#>  int [1:3] 1 2 3
str(parse_date(c("2010-01-01", "1979-10-14")))
#>  Date[1:2], format: "2010-01-01" "1979-10-14"

# parse_*(): the first argument is a character vector to parse, 
  # and the na argument specifies which strings should be treated as missing
parse_integer(c("1", "231", ".", "456"), na = ".")
#> [1]   1 231  NA 456

# If parsing fails, you’ll get a warning:
x <- parse_integer(c("123", "345", "abc", "123.45"))
#> Warning: 2 parsing failures.
#> row col               expected actual
#>   3  -- an integer             abc   
#>   4  -- no trailing characters 123.45

# And the failures will be missing in the output:
x
#> [1] 123 345  NA  NA
#> attr(,"problems")
#> # A tibble: 2 x 4
#>     row   col expected               actual
#>   <int> <int> <chr>                  <chr> 
#> 1     3    NA an integer             abc   
#> 2     4    NA no trailing characters 123.45

# If there are many parsing failures, you’ll need to use problems() 
  # to get the complete set.
problems(x)
#> # A tibble: 2 x 4
#>     row   col expected               actual
#>   <int> <int> <chr>                  <chr> 
#> 1     3    NA an integer             abc   
#> 2     4    NA no trailing characters 123.45

# parsers:
  # parse_logical() and parse_integer() parse logicals and integers respectively
  # parse_double() is a strict numeric parser
  # parse_number() is a flexible numeric parser
  # parse_character()
  # parse_factor() create factors, the data structure that R uses to represent
    # categorical variables with fixed and known values
  # parse_datetime(), parse_date(), and parse_time() allow you to parse 
    # various date & time specifications

# 11.3.1 Numbers
# When parsing numbers, the most important option is the character you use for
# the decimal mark. You can override the default value of . by creating a new 
# locale and setting the decimal_mark argument:
parse_double("1.23")
#> [1] 1.23
parse_double("1,23", locale = locale(decimal_mark = ","))
#> [1] 1.23

# parse_number() addresses the second problem: it ignores non-numeric characters
# before and after the number. This is particularly useful for currencies and
# percentages, but also works to extract numbers embedded in text.

parse_number("$100")
#> [1] 100
parse_number("20%")
#> [1] 20
parse_number("It cost $123.45")
#> [1] 123.45

# The final problem is addressed by the combination of parse_number() and the 
# locale as parse_number() will ignore the “grouping mark”:
# Used in America
parse_number("$123,456,789")
#> [1] 123456789

# Used in many parts of Europe
parse_number("123.456.789", locale = locale(grouping_mark = "."))
#> [1] 123456789

# Used in Switzerland
parse_number("123'456'789", locale = locale(grouping_mark = "'"))
#> [1] 123456789

# 11.3.2 Strings
# In R, we can get at the underlying representation of a string using charToRaw()
charToRaw("Hadley")
#> [1] 48 61 64 6c 65 79

# Sometimes just one or two characters might be messed up; other times 
# you’ll get complete gibberish. For example:
x1 <- "El Ni\xf1o was particularly bad this year"
x2 <- "\x82\xb1\x82\xf1\x82\xc9\x82\xbf\x82\xcd"

x1
#> [1] "El Ni\xf1o was particularly bad this year"
x2
#> [1] "\x82\xb1\x82\xf1\x82\u0242\xbf\x82\xcd"

# To fix the problem you need to specify the encoding in parse_character():
parse_character(x1, locale = locale(encoding = "Latin1"))
#> [1] "El Niño was particularly bad this year"
parse_character(x2, locale = locale(encoding = "Shift-JIS"))
#> [1] "こんにちは"

guess_encoding(charToRaw(x1))
#> # A tibble: 2 x 2
#>   encoding   confidence
#>   <chr>           <dbl>
#> 1 ISO-8859-1       0.46
#> 2 ISO-8859-9       0.23
guess_encoding(charToRaw(x2))
#> # A tibble: 1 x 2
#>   encoding confidence
#>   <chr>         <dbl>
#> 1 KOI8-R         0.42

# 11.3.3 Factors
# Give parse_factor() a vector of known levels to generate a warning 
# whenever an unexpected value is present:
fruit <- c("apple", "banana")
parse_factor(c("apple", "banana", "bananana"), levels = fruit)
#> Warning: 1 parsing failure.
#> row col           expected   actual
#>   3  -- value in level set bananana
#> [1] apple  banana <NA>  
#> attr(,"problems")
#> # A tibble: 1 x 4
#>     row   col expected           actual  
#>   <int> <int> <chr>              <chr>   
#> 1     3    NA value in level set bananana
#> Levels: apple banana

#11.3.4 Dates, date-times, and times
# parse_datetime() expects an ISO8601 date-time. ISO8601 is an international 
# standard in which the components of a date are organised from biggest to 
# smallest: year, month, day, hour, minute, second.
parse_datetime("2010-10-01T2010")
#> [1] "2010-10-01 20:10:00 UTC"
# If time is omitted, it will be set to midnight
parse_datetime("20101010")
#> [1] "2010-10-10 UTC"

# parse_date() expects a four digit year, a - or /, the month, a - or /, then the day:
parse_date("2010-10-01")
#> [1] "2010-10-01"

# parse_time() expects the hour, :, minutes, optionally : and seconds, and an optional am/pm specifier:
library(hms)
parse_time("01:10 am")
#> 01:10:00
parse_time("20:10:01")
#> 20:10:01

# The best way to figure out the correct format is to create a few examples 
# in a character vector, and test with one of the parsing functions. For example:
parse_date("01/02/15", "%m/%d/%y")
#> [1] "2015-01-02"
parse_date("01/02/15", "%d/%m/%y")
#> [1] "2015-02-01"
parse_date("01/02/15", "%y/%m/%d")
#> [1] "2001-02-15"

parse_date("1 janvier 2015", "%d %B %Y", locale = locale("fr"))
#> [1] "2015-01-01"

# 11.4 Parsing a file
# 11.4.1 Strategy
# readr uses a heuristic to figure out the type of each column: it reads the
# first 1000 rows and uses some (moderately conservative) heuristics to figure
# out the type of each column. You can emulate this process with a character
# vector using guess_parser(), which returns readr’s best guess, and
# parse_guess() which uses that guess to parse the column:
guess_parser("2010-10-01")
#> [1] "date"
guess_parser("15:01")
#> [1] "time"
guess_parser(c("TRUE", "FALSE"))
#> [1] "logical"
guess_parser(c("1", "5", "9"))
#> [1] "double"
guess_parser(c("12,352,561"))
#> [1] "number"

str(parse_guess("2010-10-10"))
#>  Date[1:1], format: "2010-10-10"

# 11.4.2 Problems
# These defaults don’t always work for larger files. There are two basic problems:
  # The first thousand rows might be a special case, 
    # and readr guesses a type that is not sufficiently general. 
  # The column might contain a lot of missing values

# Example
challenge <- read_csv(readr_example("challenge.csv"))
#> 
#> ── Column specification ────────────────────────────────────────────────────────
#> cols(
#>   x = col_double(),
#>   y = col_logical()
#> )
#> Warning: 1000 parsing failures.
#>  row col           expected     actual                                                           file
#> 1001   y 1/0/T/F/TRUE/FALSE 2015-01-16 '/Users/runner/work/_temp/Library/readr/extdata/challenge.csv'
#> 1002   y 1/0/T/F/TRUE/FALSE 2018-05-18 '/Users/runner/work/_temp/Library/readr/extdata/challenge.csv'
#> 1003   y 1/0/T/F/TRUE/FALSE 2015-09-05 '/Users/runner/work/_temp/Library/readr/extdata/challenge.csv'
#> 1004   y 1/0/T/F/TRUE/FALSE 2012-11-28 '/Users/runner/work/_temp/Library/readr/extdata/challenge.csv'
#> 1005   y 1/0/T/F/TRUE/FALSE 2020-01-13 '/Users/runner/work/_temp/Library/readr/extdata/challenge.csv'
#> .... ... .................. .......... ..............................................................
#> See problems(...) for more details.

# It’s always a good idea to explicitly pull out the problems(), 
# so you can explore them in more depth:
problems(challenge)
#> # A tibble: 1,000 x 5
#>     row col   expected        actual   file                                     
#>   <int> <chr> <chr>           <chr>    <chr>                                    
#> 1  1001 y     1/0/T/F/TRUE/F… 2015-01… '/Users/runner/work/_temp/Library/readr/…
#> 2  1002 y     1/0/T/F/TRUE/F… 2018-05… '/Users/runner/work/_temp/Library/readr/…
#> 3  1003 y     1/0/T/F/TRUE/F… 2015-09… '/Users/runner/work/_temp/Library/readr/…
#> 4  1004 y     1/0/T/F/TRUE/F… 2012-11… '/Users/runner/work/_temp/Library/readr/…
#> 5  1005 y     1/0/T/F/TRUE/F… 2020-01… '/Users/runner/work/_temp/Library/readr/…
#> 6  1006 y     1/0/T/F/TRUE/F… 2016-04… '/Users/runner/work/_temp/Library/readr/…
#> # … with 994 more rows

# A good strategy is to work column by column until there are no problems remaining.
tail(challenge)
#> # A tibble: 6 x 2
#>       x y    
#>   <dbl> <lgl>
#> 1 0.805 NA   
#> 2 0.164 NA   
#> 3 0.472 NA   
#> 4 0.718 NA   
#> 5 0.270 NA   
#> 6 0.608 NA

# To fix the call, start by copying and pasting the column specification into your original call:
challenge <- read_csv(
  readr_example("challenge.csv"), 
  col_types = cols(
    x = col_double(),
    y = col_logical()
  )
)

# Then you can fix the type of the y column by specifying that y is a date column:
challenge <- read_csv(
  readr_example("challenge.csv"), 
  col_types = cols(
    x = col_double(),
    y = col_date()
  )
)
tail(challenge)
#> # A tibble: 6 x 2
#>       x y         
#>   <dbl> <date>    
#> 1 0.805 2019-11-21
#> 2 0.164 2018-03-29
#> 3 0.472 2014-08-04
#> 4 0.718 2015-08-16
#> 5 0.270 2020-02-04
#> 6 0.608 2019-01-06

# 11.4.3 Other strategies
# In the previous example, we just got unlucky: if we look at just one more 
# row than the default, we can correctly parse in one shot:
challenge2 <- read_csv(readr_example("challenge.csv"), guess_max = 1001)
#> 
#> ── Column specification ────────────────────────────────────────────────────────
#> cols(
#>   x = col_double(),
#>   y = col_date(format = "")
#> )
challenge2
#> # A tibble: 2,000 x 2
#>       x y         
#>   <dbl> <date>    
#> 1   404 NA        
#> 2  4172 NA        
#> 3  3004 NA        
#> 4   787 NA        
#> 5    37 NA        
#> 6  2332 NA        
#> # … with 1,994 more rows

# Sometimes it’s easier to diagnose problems if you just read in all the columns as character vectors:
challenge2 <- read_csv(readr_example("challenge.csv"), 
  col_types = cols(.default = col_character())
)

# This is particularly useful in conjunction with type_convert(), which applies
# the parsing heuristics to the character columns in a data frame.
df <- tribble(
  ~x,  ~y,
  "1", "1.21",
  "2", "2.32",
  "3", "4.56"
)
df
#> # A tibble: 3 x 2
#>   x     y    
#>   <chr> <chr>
#> 1 1     1.21 
#> 2 2     2.32 
#> 3 3     4.56

# Note the column types
type_convert(df)
#> 
#> ── Column specification ────────────────────────────────────────────────────────
#> cols(
#>   x = col_double(),
#>   y = col_double()
#> )
#> # A tibble: 3 x 2
#>       x     y
#>   <dbl> <dbl>
#> 1     1  1.21
#> 2     2  2.32
#> 3     3  4.56

# If you’re reading a very large file, you might want to set n_max to a 
# smallish number like 10,000 or 100,000

# If you’re having major parsing problems, sometimes it’s easier to just read
# into a character vector of lines with read_lines(), or even a character vector
# of length 1 with read_file(). Then you can use the string parsing skills
# you’ll learn later to parse more exotic formats.

# 11.5 Writing to a file
# readr also comes with two useful functions for writing data back to disk: write_csv() and write_tsv()
  # Always encoding strings in UTF-8.
  # Saving dates and date-times in ISO8601 format so they are easily parsed elsewhere.
write_csv(challenge, "challenge.csv")

# Note that the type information is lost when you save to csv:
challenge
#> # A tibble: 2,000 x 2
#>       x y         
#>   <dbl> <date>    
#> 1   404 NA        
#> 2  4172 NA        
#> 3  3004 NA        
#> 4   787 NA        
#> 5    37 NA        
#> 6  2332 NA        
#> # … with 1,994 more rows
write_csv(challenge, "challenge-2.csv")
read_csv("challenge-2.csv")
#> 
#> ── Column specification ────────────────────────────────────────────────────────
#> cols(
#>   x = col_double(),
#>   y = col_logical()
#> )
#> # A tibble: 2,000 x 2
#>       x y    
#>   <dbl> <lgl>
#> 1   404 NA   
#> 2  4172 NA   
#> 3  3004 NA   
#> 4   787 NA   
#> 5    37 NA   
#> 6  2332 NA   
#> # … with 1,994 more rows

# write_rds() and read_rds() are uniform wrappers around the base functions
# readRDS() and saveRDS(). These store data in R’s custom binary format called RDS:
write_rds(challenge, "challenge.rds")
read_rds("challenge.rds")
#> # A tibble: 2,000 x 2
#>       x y         
#>   <dbl> <date>    
#> 1   404 NA        
#> 2  4172 NA        
#> 3  3004 NA        
#> 4   787 NA        
#> 5    37 NA        
#> 6  2332 NA        
#> # … with 1,994 more rows

# The feather package implements a fast binary file format that can be shared
# across programming languages:
library(feather)
write_feather(challenge, "challenge.feather")
read_feather("challenge.feather")
#> # A tibble: 2,000 x 2
#>       x      y
#>   <dbl> <date>
#> 1   404   <NA>
#> 2  4172   <NA>
#> 3  3004   <NA>
#> 4   787   <NA>
#> 5    37   <NA>
#> 6  2332   <NA>
#> # ... with 1,994 more rows

# 11.6 Other types of data
# To get other types of data into R, we recommend starting with the tidyverse
# packages listed below. They’re certainly not perfect, but they are a good
# place to start. For rectangular data:
  # haven reads SPSS, Stata, and SAS files.
  # readxl reads excel files (both .xls and .xlsx).
  # DBI, along with a database specific backend (e.g. RMySQL, RSQLite, 
    # RPostgreSQL etc) allows you to run SQL queries against a database and return a data frame.