# Abigail Castro - abc3gnm
# Chapter 13 Summary and code

# Chapter 13 Relational Data
# multiple tables of data are called relational data because it is the relations, 
# not just the individual datasets, that are important

#13.1 Introduction
# There are three families of verbs designed to work with relational data:
  
  # Mutating joins: add new variables to one data frame from matching observations in another.

  # Filtering joins: filter observations from one data frame based on whether or not they match an observation in the other table.

  # Set operations:treat observations as if they were set elements.
library(tidyverse)
library(nycflights13)

# 13.2 nycflights13
# airlines lets you look up the full carrier name from its abbreviated code:
airlines
#> # A tibble: 16 x 2
#>   carrier name                    
#>   <chr>   <chr>                   
#> 1 9E      Endeavor Air Inc.       
#> 2 AA      American Airlines Inc.  
#> 3 AS      Alaska Airlines Inc.    
#> 4 B6      JetBlue Airways         
#> 5 DL      Delta Air Lines Inc.    
#> 6 EV      ExpressJet Airlines Inc.
#> # … with 10 more rows

# airports gives information about each airport, identified by the faa airport code:
airports
#> # A tibble: 1,458 x 8
#>   faa   name                          lat   lon   alt    tz dst   tzone         
#>   <chr> <chr>                       <dbl> <dbl> <dbl> <dbl> <chr> <chr>         
#> 1 04G   Lansdowne Airport            41.1 -80.6  1044    -5 A     America/New_Y…
#> 2 06A   Moton Field Municipal Airp…  32.5 -85.7   264    -6 A     America/Chica…
#> 3 06C   Schaumburg Regional          42.0 -88.1   801    -6 A     America/Chica…
#> 4 06N   Randall Airport              41.4 -74.4   523    -5 A     America/New_Y…
#> 5 09J   Jekyll Island Airport        31.1 -81.4    11    -5 A     America/New_Y…
#> 6 0A9   Elizabethton Municipal Air…  36.4 -82.2  1593    -5 A     America/New_Y…
#> # … with 1,452 more rows

# planes gives information about each plane, identified by its tailnum:
planes
#> # A tibble: 3,322 x 9
#>   tailnum  year type           manufacturer   model  engines seats speed engine 
#>   <chr>   <int> <chr>          <chr>          <chr>    <int> <int> <int> <chr>  
#> 1 N10156   2004 Fixed wing mu… EMBRAER        EMB-1…       2    55    NA Turbo-…
#> 2 N102UW   1998 Fixed wing mu… AIRBUS INDUST… A320-…       2   182    NA Turbo-…
#> 3 N103US   1999 Fixed wing mu… AIRBUS INDUST… A320-…       2   182    NA Turbo-…
#> 4 N104UW   1999 Fixed wing mu… AIRBUS INDUST… A320-…       2   182    NA Turbo-…
#> 5 N10575   2002 Fixed wing mu… EMBRAER        EMB-1…       2    55    NA Turbo-…
#> 6 N105UW   1999 Fixed wing mu… AIRBUS INDUST… A320-…       2   182    NA Turbo-…
#> # … with 3,316 more rows

# weather gives the weather at each NYC airport for each hour:
weather
#> # A tibble: 26,115 x 15
#>   origin  year month   day  hour  temp  dewp humid wind_dir wind_speed wind_gust
#>   <chr>  <int> <int> <int> <int> <dbl> <dbl> <dbl>    <dbl>      <dbl>     <dbl>
#> 1 EWR     2013     1     1     1  39.0  26.1  59.4      270      10.4         NA
#> 2 EWR     2013     1     1     2  39.0  27.0  61.6      250       8.06        NA
#> 3 EWR     2013     1     1     3  39.0  28.0  64.4      240      11.5         NA
#> 4 EWR     2013     1     1     4  39.9  28.0  62.2      250      12.7         NA
#> 5 EWR     2013     1     1     5  39.0  28.0  64.4      260      12.7         NA
#> 6 EWR     2013     1     1     6  37.9  28.0  67.2      240      11.5         NA
#> # … with 26,109 more rows, and 4 more variables: precip <dbl>, pressure <dbl>,
#> #   visib <dbl>, time_hour <dttm>

# For nycflights13:

  # flights connects to planes via a single variable, tailnum.

  # flights connects to airlines through the carrier variable.

  # flights connects to airports in two ways: via the origin and dest variables.

  # flights connects to weather via origin (the location), and year, month, day and hour (the time).

# 13.3 Keys
# Keys: the variables used to connect each pair of tables
# A key is a variable (or set of variables) that uniquely identifies an observation.
# There are two types of keys:
  # primary key: uniquely identifies an observation in its own table. 
    # For example, planes$tailnum is a primary key because it uniquely identifies each plane in the planes table.
  # foreign key: uniquely identifies an observation in another table. 
    # For example, flights$tailnum is a foreign key because it appears in the flights table where it matches each flight to a unique plane.
# A variable can be both a primary key and a foreign key.

# Once you’ve identified the primary keys in your tables, it’s good practice to 
# verify that they do indeed uniquely identify each observation. One way to do that is
# to count() the primary keys and look for entries where n is greater than one:
planes %>% 
  count(tailnum) %>% 
  filter(n > 1)
#> # A tibble: 0 x 2
#> # … with 2 variables: tailnum <chr>, n <int>

weather %>% 
  count(year, month, day, hour, origin) %>% 
  filter(n > 1)
#> # A tibble: 3 x 6
#>    year month   day  hour origin     n
#>   <int> <int> <int> <int> <chr>  <int>
#> 1  2013    11     3     1 EWR        2
#> 2  2013    11     3     1 JFK        2
#> 3  2013    11     3     1 LGA        2

# Sometimes a table doesn’t have an explicit primary key
flights %>% 
  count(year, month, day, flight) %>% 
  filter(n > 1)
#> # A tibble: 29,768 x 5
#>    year month   day flight     n
#>   <int> <int> <int>  <int> <int>
#> 1  2013     1     1      1     2
#> 2  2013     1     1      3     2
#> 3  2013     1     1      4     2
#> 4  2013     1     1     11     3
#> 5  2013     1     1     15     2
#> 6  2013     1     1     21     2
#> # … with 29,762 more rows

flights %>% 
  count(year, month, day, tailnum) %>% 
  filter(n > 1)
#> # A tibble: 64,928 x 5
#>    year month   day tailnum     n
#>   <int> <int> <int> <chr>   <int>
#> 1  2013     1     1 N0EGMQ      2
#> 2  2013     1     1 N11189      2
#> 3  2013     1     1 N11536      2
#> 4  2013     1     1 N11544      3
#> 5  2013     1     1 N11551      2
#> 6  2013     1     1 N12540      2
#> # … with 64,922 more rows

# surrogate key: If a table lacks a primary key, it’s sometimes useful to add one with mutate() and row_number()
# A primary key and the corresponding foreign key in another table form a relation. Relations are typically one-to-many.

# 13.4 Mutating joins
# A mutating join allows you to combine variables from two tables.
# Like mutate(), the join functions add variables to the right, so if you have a lot of variables already, the new variables won’t get printed out.
# make narrower dataset for example
flights2 <- flights %>% 
  select(year:day, hour, origin, dest, tailnum, carrier)
flights2
#> # A tibble: 336,776 x 8
#>    year month   day  hour origin dest  tailnum carrier
#>   <int> <int> <int> <dbl> <chr>  <chr> <chr>   <chr>  
#> 1  2013     1     1     5 EWR    IAH   N14228  UA     
#> 2  2013     1     1     5 LGA    IAH   N24211  UA     
#> 3  2013     1     1     5 JFK    MIA   N619AA  AA     
#> 4  2013     1     1     5 JFK    BQN   N804JB  B6     
#> 5  2013     1     1     6 LGA    ATL   N668DN  DL     
#> 6  2013     1     1     5 EWR    ORD   N39463  UA     
#> # … with 336,770 more rows

# Imagine you want to add the full airline name to the flights2 data. 
# You can combine the airlines and flights2 data frames with left_join():
flights2 %>%
  select(-origin, -dest) %>% 
  left_join(airlines, by = "carrier")
#> # A tibble: 336,776 x 7
#>    year month   day  hour tailnum carrier name                  
#>   <int> <int> <int> <dbl> <chr>   <chr>   <chr>                 
#> 1  2013     1     1     5 N14228  UA      United Air Lines Inc. 
#> 2  2013     1     1     5 N24211  UA      United Air Lines Inc. 
#> 3  2013     1     1     5 N619AA  AA      American Airlines Inc.
#> 4  2013     1     1     5 N804JB  B6      JetBlue Airways       
#> 5  2013     1     1     6 N668DN  DL      Delta Air Lines Inc.  
#> 6  2013     1     1     5 N39463  UA      United Air Lines Inc. 
#> # … with 336,770 more rows

# In this case, you could have got to the same place using mutate() and R’s base subsetting:
flights2 %>%
  select(-origin, -dest) %>% 
  mutate(name = airlines$name[match(carrier, airlines$carrier)])
#> # A tibble: 336,776 x 7
#>    year month   day  hour tailnum carrier name                  
#>   <int> <int> <int> <dbl> <chr>   <chr>   <chr>                 
#> 1  2013     1     1     5 N14228  UA      United Air Lines Inc. 
#> 2  2013     1     1     5 N24211  UA      United Air Lines Inc. 
#> 3  2013     1     1     5 N619AA  AA      American Airlines Inc.
#> 4  2013     1     1     5 N804JB  B6      JetBlue Airways       
#> 5  2013     1     1     6 N668DN  DL      Delta Air Lines Inc.  
#> 6  2013     1     1     5 N39463  UA      United Air Lines Inc. 
#> # … with 336,770 more rows

# 13.4.1 Understanding joins

x <- tribble(
  ~key, ~val_x,
  1, "x1",
  2, "x2",
  3, "x3"
)
y <- tribble(
  ~key, ~val_y,
  1, "y1",
  2, "y2",
  4, "y3"
)

# A join is a way of connecting each row in x to zero, one, or more rows in y

# 13.4.2 Inner join
# An inner join matches pairs of observations whenever their keys are equal:
# The output of an inner join is a new data frame that contains the key, the x values, and the y values.

x %>% 
  inner_join(y, by = "key")
#> # A tibble: 2 x 3
#>     key val_x val_y
#>   <dbl> <chr> <chr>
#> 1     1 x1    y1   
#> 2     2 x2    y2

# The most important property of an inner join is that unmatched rows are not included in the result.

# 13.4.3 Outer joins
# An outer join keeps observations that appear in at least one of the tables.
# There are three types of outer joins:
  # A left join keeps all observations in x.
  # A right join keeps all observations in y.
  # A full join keeps all observations in x and y.

# 13.4.4 Duplicate keys
# There are two possibilities:
  # 1. One table has duplicate keys. This is useful when you want to add 
    # in additional information as there is typically a one-to-many relationship.
x <- tribble(
  ~key, ~val_x,
  1, "x1",
  2, "x2",
  2, "x3",
  1, "x4"
)
y <- tribble(
  ~key, ~val_y,
  1, "y1",
  2, "y2"
)
left_join(x, y, by = "key")
#> # A tibble: 4 x 3
#>     key val_x val_y
#>   <dbl> <chr> <chr>
#> 1     1 x1    y1   
#> 2     2 x2    y2   
#> 3     2 x3    y2   
#> 4     1 x4    y1
  
  # 2. Both tables have duplicate keys. This is usually an error because in 
    # neither table do the keys uniquely identify an observation. 
    # When you join duplicated keys, you get all possible combinations, the Cartesian product:
x <- tribble(
  ~key, ~val_x,
  1, "x1",
  2, "x2",
  2, "x3",
  3, "x4"
)
y <- tribble(
  ~key, ~val_y,
  1, "y1",
  2, "y2",
  2, "y3",
  3, "y4"
)
left_join(x, y, by = "key")
#> # A tibble: 6 x 3
#>     key val_x val_y
#>   <dbl> <chr> <chr>
#> 1     1 x1    y1   
#> 2     2 x2    y2   
#> 3     2 x2    y3   
#> 4     2 x3    y2   
#> 5     2 x3    y3   
#> 6     3 x4    y4

# 13.4.5 Defining the key columns

# The default, by = NULL, uses all variables that appear in both tables, the so 
# called natural join. For example, the flights and weather tables match on their 
# common variables: year, month, day, hour and origin.
flights2 %>% 
  left_join(weather)
#> Joining, by = c("year", "month", "day", "hour", "origin")
#> # A tibble: 336,776 x 18
#>    year month   day  hour origin dest  tailnum carrier  temp  dewp humid
#>   <int> <int> <int> <dbl> <chr>  <chr> <chr>   <chr>   <dbl> <dbl> <dbl>
#> 1  2013     1     1     5 EWR    IAH   N14228  UA       39.0  28.0  64.4
#> 2  2013     1     1     5 LGA    IAH   N24211  UA       39.9  25.0  54.8
#> 3  2013     1     1     5 JFK    MIA   N619AA  AA       39.0  27.0  61.6
#> 4  2013     1     1     5 JFK    BQN   N804JB  B6       39.0  27.0  61.6
#> 5  2013     1     1     6 LGA    ATL   N668DN  DL       39.9  25.0  54.8
#> 6  2013     1     1     5 EWR    ORD   N39463  UA       39.0  28.0  64.4
#> # … with 336,770 more rows, and 7 more variables: wind_dir <dbl>,
#> #   wind_speed <dbl>, wind_gust <dbl>, precip <dbl>, pressure <dbl>,
#> #   visib <dbl>, time_hour <dttm>

# A character vector, by = "x". This is like a natural join, but uses only some 
# of the common variables. For example, flights and planes have year variables, 
# but they mean different things so we only want to join by tailnum.
flights2 %>% 
  left_join(planes, by = "tailnum")
#> # A tibble: 336,776 x 16
#>   year.x month   day  hour origin dest  tailnum carrier year.y type 
#>    <int> <int> <int> <dbl> <chr>  <chr> <chr>   <chr>    <int> <chr>
#> 1   2013     1     1     5 EWR    IAH   N14228  UA        1999 Fixe…
#> 2   2013     1     1     5 LGA    IAH   N24211  UA        1998 Fixe…
#> 3   2013     1     1     5 JFK    MIA   N619AA  AA        1990 Fixe…
#> 4   2013     1     1     5 JFK    BQN   N804JB  B6        2012 Fixe…
#> 5   2013     1     1     6 LGA    ATL   N668DN  DL        1991 Fixe…
#> 6   2013     1     1     5 EWR    ORD   N39463  UA        2012 Fixe…
#> # … with 336,770 more rows, and 6 more variables: manufacturer <chr>,
#> #   model <chr>, engines <int>, seats <int>, speed <int>, engine <chr>

# A named character vector: by = c("a" = "b"). This will match variable a in 
# table x to variable b in table y. The variables from x will be used in the output.
flights2 %>% 
  left_join(airports, c("dest" = "faa"))
#> # A tibble: 336,776 x 15
#>    year month   day  hour origin dest  tailnum carrier name    lat   lon   alt
#>   <int> <int> <int> <dbl> <chr>  <chr> <chr>   <chr>   <chr> <dbl> <dbl> <dbl>
#> 1  2013     1     1     5 EWR    IAH   N14228  UA      Geor…  30.0 -95.3    97
#> 2  2013     1     1     5 LGA    IAH   N24211  UA      Geor…  30.0 -95.3    97
#> 3  2013     1     1     5 JFK    MIA   N619AA  AA      Miam…  25.8 -80.3     8
#> 4  2013     1     1     5 JFK    BQN   N804JB  B6      <NA>   NA    NA      NA
#> 5  2013     1     1     6 LGA    ATL   N668DN  DL      Hart…  33.6 -84.4  1026
#> 6  2013     1     1     5 EWR    ORD   N39463  UA      Chic…  42.0 -87.9   668
#> # … with 336,770 more rows, and 3 more variables: tz <dbl>, dst <chr>,
#> #   tzone <chr>

flights2 %>% 
  left_join(airports, c("origin" = "faa"))
#> # A tibble: 336,776 x 15
#>    year month   day  hour origin dest  tailnum carrier name    lat   lon   alt
#>   <int> <int> <int> <dbl> <chr>  <chr> <chr>   <chr>   <chr> <dbl> <dbl> <dbl>
#> 1  2013     1     1     5 EWR    IAH   N14228  UA      Newa…  40.7 -74.2    18
#> 2  2013     1     1     5 LGA    IAH   N24211  UA      La G…  40.8 -73.9    22
#> 3  2013     1     1     5 JFK    MIA   N619AA  AA      John…  40.6 -73.8    13
#> 4  2013     1     1     5 JFK    BQN   N804JB  B6      John…  40.6 -73.8    13
#> 5  2013     1     1     6 LGA    ATL   N668DN  DL      La G…  40.8 -73.9    22
#> 6  2013     1     1     5 EWR    ORD   N39463  UA      Newa…  40.7 -74.2    18
#> # … with 336,770 more rows, and 3 more variables: tz <dbl>, dst <chr>,
#> #   tzone <chr>

# 13.5 Filtering joins
# Filtering joins match observations in the same way as mutating joins, but 
# affect the observations, not the variables. There are two types:
  # semi_join(x, y) keeps all observations in x that have a match in y.
  # anti_join(x, y) drops all observations in x that have a match in y.

# top ten most popular destinations:
top_dest <- flights %>%
  count(dest, sort = TRUE) %>%
  head(10)
top_dest
#> # A tibble: 10 x 2
#>   dest      n
#>   <chr> <int>
#> 1 ORD   17283
#> 2 ATL   17215
#> 3 LAX   16174
#> 4 BOS   15508
#> 5 MCO   14082
#> 6 CLT   14064
#> # … with 4 more rows

# find each flight that went to one of those destinations
flights %>% 
  filter(dest %in% top_dest$dest)
#> # A tibble: 141,145 x 19
#>    year month   day dep_time sched_dep_time dep_delay arr_time sched_arr_time
#>   <int> <int> <int>    <int>          <int>     <dbl>    <int>          <int>
#> 1  2013     1     1      542            540         2      923            850
#> 2  2013     1     1      554            600        -6      812            837
#> 3  2013     1     1      554            558        -4      740            728
#> 4  2013     1     1      555            600        -5      913            854
#> 5  2013     1     1      557            600        -3      838            846
#> 6  2013     1     1      558            600        -2      753            745
#> # … with 141,139 more rows, and 11 more variables: arr_delay <dbl>,
#> #   carrier <chr>, flight <int>, tailnum <chr>, origin <chr>, dest <chr>,
#> #   air_time <dbl>, distance <dbl>, hour <dbl>, minute <dbl>, time_hour <dttm>

#  filter statement that used year, month, and day to match it back to flights
flights %>% 
  semi_join(top_dest)
#> Joining, by = "dest"
#> # A tibble: 141,145 x 19
#>    year month   day dep_time sched_dep_time dep_delay arr_time sched_arr_time
#>   <int> <int> <int>    <int>          <int>     <dbl>    <int>          <int>
#> 1  2013     1     1      542            540         2      923            850
#> 2  2013     1     1      554            600        -6      812            837
#> 3  2013     1     1      554            558        -4      740            728
#> 4  2013     1     1      555            600        -5      913            854
#> 5  2013     1     1      557            600        -3      838            846
#> 6  2013     1     1      558            600        -2      753            745
#> # … with 141,139 more rows, and 11 more variables: arr_delay <dbl>,
#> #   carrier <chr>, flight <int>, tailnum <chr>, origin <chr>, dest <chr>,
#> #   air_time <dbl>, distance <dbl>, hour <dbl>, minute <dbl>, time_hour <dttm>

# flights that don’t have a match in planes:
flights %>%
  anti_join(planes, by = "tailnum") %>%
  count(tailnum, sort = TRUE)
#> # A tibble: 722 x 2
#>   tailnum     n
#>   <chr>   <int>
#> 1 <NA>     2512
#> 2 N725MQ    575
#> 3 N722MQ    513
#> 4 N723MQ    507
#> 5 N713MQ    483
#> 6 N735MQ    396
#> # … with 716 more rows


# 13.6 Join problems

# 1.Start by identifying the variables that form the primary key in each table. 
  # You should usually do this based on your understanding of the data, not 
  # empirically by looking for a combination of variables that give a unique identifier. 
  # If you just look for variables without thinking about what they mean, you might 
  # get (un)lucky and find a combination that’s unique in your current data but the 
  # relationship might not be true in general.
airports %>% count(alt, lon) %>% filter(n > 1)
#> # A tibble: 0 x 3
#> # … with 3 variables: alt <dbl>, lon <dbl>, n <int>

# 2.Check that none of the variables in the primary key are missing. 
  # If a value is missing then it can’t identify an observation!

# 3.Check that your foreign keys match primary keys in another table. The best 
# way to do this is with an anti_join(). It’s common for keys not to match 
# because of data entry errors. Fixing these is often a lot of work.

# 13.7 Set operations
# The final type of two-table verb are the set operations
# All these operations work with a complete row, comparing the values of every variable.
# intersect(x, y): return only observations in both x and y.
# union(x, y): return unique observations in x and y.
# setdiff(x, y): return observations in x, but not in y.

# Given: 
df1 <- tribble(
  ~x, ~y,
  1,  1,
  2,  1
)
df2 <- tribble(
  ~x, ~y,
  1,  1,
  1,  2
)

# The four possibilities are:
intersect(df1, df2)
#> # A tibble: 1 x 2
#>       x     y
#>   <dbl> <dbl>
#> 1     1     1

# Note that we get 3 rows, not 4
union(df1, df2)
#> # A tibble: 3 x 2
#>       x     y
#>   <dbl> <dbl>
#> 1     1     1
#> 2     2     1
#> 3     1     2

setdiff(df1, df2)
#> # A tibble: 1 x 2
#>       x     y
#>   <dbl> <dbl>
#> 1     2     1

setdiff(df2, df1)
#> # A tibble: 1 x 2
#>       x     y
#>   <dbl> <dbl>
#> 1     1     2



