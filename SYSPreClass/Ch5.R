# Abigail Castro - abc3gnm
# Chapter 5 Summary and code

# Chapter 5 Data Transformation
# Mainly focus on how to transform data to make it easier 
# to work with

# 5.1 Introduction
# Load packages
library(nycflights13)
library(tidyverse)

# investigate data set
flights
?flights
View(flights)

# 5.1.3 dplyr basics
# five key dplyr functions
# Pick observations by their values (filter()).
# Reorder the rows (arrange()).
# Pick variables by their names (select()).
# Create new variables with functions of existing variables (mutate()).
# Collapse many values down to a single summary (summarise()).
# These can all be used in conjunction with group_by() which changes the 
# scope of each function from operating on the entire dataset to operating 
# on it group-by-group.

# The first argument is a data frame.
# The subsequent arguments describe what to do with the data frame, 
# using the variable names (without quotes).
# The result is a new data frame.

# 5.2 Filter rows with filter()
# selects all flights on Jan. 1 and returns a new data frame
filter(flights, month == 1, day == 1)

# to save the new data frame assign it to a variable
jan1 <- filter(flights, month == 1, day == 1)

# R either prints out the results, or saves them to a variable. If you want 
# to do both, you can wrap the assignment in parentheses:
(dec25 <- filter(flights, month == 12, day == 25))

# Common mistake: using = instead of ==
# will give you an informative error
filter(flights, month = 1)

# 5.2.1 Comparisons
# R provides the standard suite: >, >=, <, <=, != (not equal), and == (equal)

# Common problem when using ==
# will return false despite being true
sqrt(2) ^ 2 == 2
#> [1] FALSE
1 / 49 * 49 == 1
#> [1] FALSE

# Instead of ==, use near()
near(sqrt(2) ^ 2,  2)
#> [1] TRUE
near(1 / 49 * 49, 1)
#> [1] TRUE

# 5.2.2 Logical operators
# & is “and”, | is “or”, and ! is “not”

# The following code finds all flights that departed in November or December:
filter(flights, month == 11 | month == 12)

# select every row where x is one of the values in y
nov_dec <- filter(flights, month %in% c(11, 12))

# find flights that weren’t delayed (on arrival or departure) 
# by more than two hours
filter(flights, !(arr_delay > 120 | dep_delay > 120))
filter(flights, arr_delay <= 120, dep_delay <= 120)

# Whenever you start using complicated, multipart expressions in filter(), 
# consider making them explicit variables instead

# 5.2.3 Missing values
# NA represents an unknown value so missing values are “contagious":
# almost any operation involving an unknown value will also be unknown.

NA > 5
#> [1] NA
10 == NA
#> [1] NA
NA + 10
#> [1] NA
NA / 2
#> [1] NA

NA == NA
#> [1] NA

# Let x be Mary's age. We don't know how old she is.
x <- NA

# Let y be John's age. We don't know how old he is.
y <- NA

# Are John and Mary the same age?
x == y
#> [1] NA
# We don't know!

# If you want to determine if a value is missing, use is.na():
is.na(x)
#> [1] TRUE

# filter() only includes rows where the condition is TRUE; 
# it excludes both FALSE and NA values.
#  If you want to preserve missing values, ask for them explicitly:
df <- tibble(x = c(1, NA, 3))
filter(df, x > 1)
#> # A tibble: 1 x 1
#>       x
#>   <dbl>
#> 1     3
filter(df, is.na(x) | x > 1)
#> # A tibble: 2 x 1
#>       x
#>   <dbl>
#> 1    NA
#> 2     3

# 5.3 Arrange rows with arrange()
# arrange() takes a data frame and a set of column names 
# (or more complicated expressions) to order by
arrange(flights, year, month, day)

# Use desc() to re-order by a column in descending order:
arrange(flights, desc(dep_delay))

# Missing values are always sorted at the end:
df <- tibble(x = c(5, 2, NA))
arrange(df, x)
#> # A tibble: 3 x 1
#>       x
#>   <dbl>
#> 1     2
#> 2     5
#> 3    NA
arrange(df, desc(x))
#> # A tibble: 3 x 1
#>       x
#>   <dbl>
#> 1     5
#> 2     2
#> 3    NA

# 5.4 Select columns with select()
# select() allows you to rapidly zoom in on a useful subset using 
# operations based on the names of the variables
# Select columns by name
select(flights, year, month, day)
#> # A tibble: 336,776 x 3
#>    year month   day
#>   <int> <int> <int>
#> 1  2013     1     1
#> 2  2013     1     1
#> 3  2013     1     1
#> 4  2013     1     1
#> 5  2013     1     1
#> 6  2013     1     1
#> # … with 336,770 more rows
# Select all columns between year and day (inclusive)
select(flights, year:day)
#> # A tibble: 336,776 x 3
#>    year month   day
#>   <int> <int> <int>
#> 1  2013     1     1
#> 2  2013     1     1
#> 3  2013     1     1
#> 4  2013     1     1
#> 5  2013     1     1
#> 6  2013     1     1
#> # … with 336,770 more rows
# Select all columns except those from year to day (inclusive)
select(flights, -(year:day))
#> # A tibble: 336,776 x 16
#>   dep_time sched_dep_time dep_delay arr_time sched_arr_time arr_delay carrier
#>      <int>          <int>     <dbl>    <int>          <int>     <dbl> <chr>  
#> 1      517            515         2      830            819        11 UA     
#> 2      533            529         4      850            830        20 UA     
#> 3      542            540         2      923            850        33 AA     
#> 4      544            545        -1     1004           1022       -18 B6     
#> 5      554            600        -6      812            837       -25 DL     
#> 6      554            558        -4      740            728        12 UA     
#> # … with 336,770 more rows, and 9 more variables: flight <int>, tailnum <chr>,
#> #   origin <chr>, dest <chr>, air_time <dbl>, distance <dbl>, hour <dbl>,
#> #   minute <dbl>, time_hour <dttm>

# helper functions you can use within select():
#   starts_with("abc"): matches names that begin with “abc”
#   ends_with("xyz"): matches names that end with “xyz”
#   contains("ijk"): matches names that contain “ijk”
#   matches("(.)\\1"): selects variables that match a regular expression. 
#     This one matches any variables that contain repeated characters.
#   num_range("x", 1:3): matches x1, x2 and x3

# select() can be used to rename variables, but it’s rarely useful 
# because it drops all of the variables not explicitly mentioned
# use rename(), which is a variant of select() that keeps all 
# the variables that aren’t explicitly mentioned:
rename(flights, tail_num = tailnum)
#> # A tibble: 336,776 x 19
#>    year month   day dep_time sched_dep_time dep_delay arr_time sched_arr_time
#>   <int> <int> <int>    <int>          <int>     <dbl>    <int>          <int>
#> 1  2013     1     1      517            515         2      830            819
#> 2  2013     1     1      533            529         4      850            830
#> 3  2013     1     1      542            540         2      923            850
#> 4  2013     1     1      544            545        -1     1004           1022
#> 5  2013     1     1      554            600        -6      812            837
#> 6  2013     1     1      554            558        -4      740            728
#> # … with 336,770 more rows, and 11 more variables: arr_delay <dbl>,
#> #   carrier <chr>, flight <int>, tail_num <chr>, origin <chr>, dest <chr>,
#> #   air_time <dbl>, distance <dbl>, hour <dbl>, minute <dbl>, time_hour <dttm>

# Another option is to use select() in conjunction with the everything() 
# helper. This is useful if you have a handful of variables you’d like to 
# move to the start of the data frame.

select(flights, time_hour, air_time, everything())
#> # A tibble: 336,776 x 19
#>   time_hour           air_time  year month   day dep_time sched_dep_time
#>   <dttm>                 <dbl> <int> <int> <int>    <int>          <int>
#> 1 2013-01-01 05:00:00      227  2013     1     1      517            515
#> 2 2013-01-01 05:00:00      227  2013     1     1      533            529
#> 3 2013-01-01 05:00:00      160  2013     1     1      542            540
#> 4 2013-01-01 05:00:00      183  2013     1     1      544            545
#> 5 2013-01-01 06:00:00      116  2013     1     1      554            600
#> 6 2013-01-01 05:00:00      150  2013     1     1      554            558
#> # … with 336,770 more rows, and 12 more variables: dep_delay <dbl>,
#> #   arr_time <int>, sched_arr_time <int>, arr_delay <dbl>, carrier <chr>,
#> #   flight <int>, tailnum <chr>, origin <chr>, dest <chr>, distance <dbl>,
#> #   hour <dbl>, minute <dbl>

# 5.5 Add new variables with mutate()
# mutate() always adds new columns at the end of your dataset
flights_sml <- select(flights, 
                      year:day, 
                      ends_with("delay"), 
                      distance, 
                      air_time
)
mutate(flights_sml,
       gain = dep_delay - arr_delay,
       speed = distance / air_time * 60
)
#> # A tibble: 336,776 x 9
#>    year month   day dep_delay arr_delay distance air_time  gain speed
#>   <int> <int> <int>     <dbl>     <dbl>    <dbl>    <dbl> <dbl> <dbl>
#> 1  2013     1     1         2        11     1400      227    -9  370.
#> 2  2013     1     1         4        20     1416      227   -16  374.
#> 3  2013     1     1         2        33     1089      160   -31  408.
#> 4  2013     1     1        -1       -18     1576      183    17  517.
#> 5  2013     1     1        -6       -25      762      116    19  394.
#> 6  2013     1     1        -4        12      719      150   -16  288.
#> # … with 336,770 more rows

# If you only want to keep the new variables, use transmute():
transmute(flights,
          gain = dep_delay - arr_delay,
          hours = air_time / 60,
          gain_per_hour = gain / hours
)
#> # A tibble: 336,776 x 3
#>    gain hours gain_per_hour
#>   <dbl> <dbl>         <dbl>
#> 1    -9  3.78         -2.38
#> 2   -16  3.78         -4.23
#> 3   -31  2.67        -11.6 
#> 4    17  3.05          5.57
#> 5    19  1.93          9.83
#> 6   -16  2.5          -6.4 
#> # … with 336,770 more rows

# 5.5.1 Useful creation functions
# key property is that the function must be vectorised: 
#   it must take a vector of values as input, 
#   return a vector with the same number of values as output

# a selection of functions that are frequently useful:
#   Arithmetic operators: +, -, *, /, ^

#   Modular arithmetic: %/% (integer division) and %% (remainder), 
#     where x == y * (x %/% y) + (x %% y)
transmute(flights,
          dep_time,
          hour = dep_time %/% 100,
          minute = dep_time %% 100
)
#> # A tibble: 336,776 x 3
#>   dep_time  hour minute
#>      <int> <dbl>  <dbl>
#> 1      517     5     17
#> 2      533     5     33
#> 3      542     5     42
#> 4      544     5     44
#> 5      554     5     54
#> 6      554     5     54
#> # … with 336,770 more rows

#   Logs: log(), log2(), log10()

#   Offsets: lead() and lag() allow you to refer to leading or lagging values
(x <- 1:10)
#>  [1]  1  2  3  4  5  6  7  8  9 10
lag(x)
#>  [1] NA  1  2  3  4  5  6  7  8  9
lead(x)
#>  [1]  2  3  4  5  6  7  8  9 10 NA

#   Cumulative and rolling aggregates: R provides functions for running sums, 
#     products, mins and maxes: cumsum(), cumprod(), cummin(), cummax(); and dplyr provides cummean() for cumulative means.
x
#>  [1]  1  2  3  4  5  6  7  8  9 10
cumsum(x)
#>  [1]  1  3  6 10 15 21 28 36 45 55
cummean(x)
#>  [1] 1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0 5.5

#   Logical comparisons, <, <=, >, >=, !=, and ==

#   Ranking: min_rank(),row_number(), dense_rank(), percent_rank(), 
#     cume_dist(), ntile()
y <- c(1, 2, 2, NA, 3, 4)
min_rank(y)
#> [1]  1  2  2 NA  4  5
min_rank(desc(y))
#> [1]  5  3  3 NA  2  1
row_number(y)
#> [1]  1  2  3 NA  4  5
dense_rank(y)
#> [1]  1  2  2 NA  3  4
percent_rank(y)
#> [1] 0.00 0.25 0.25   NA 0.75 1.00
cume_dist(y)
#> [1] 0.2 0.6 0.6  NA 0.8 1.0

# 5.6 Grouped summaries with summarise()
# summarise() collapses a data frame to a single row:
summarise(flights, delay = mean(dep_delay, na.rm = TRUE))
#> # A tibble: 1 x 1
#>   delay
#>   <dbl>
#> 1  12.6

# summarise() is not terribly useful unless we pair it with group_by()
# if we applied exactly the same code to a data frame grouped by date, 
# we get the average delay per date:
by_day <- group_by(flights, year, month, day)
summarise(by_day, delay = mean(dep_delay, na.rm = TRUE))
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 4
#> # Groups:   year, month [12]
#>    year month   day delay
#>   <int> <int> <int> <dbl>
#> 1  2013     1     1 11.5 
#> 2  2013     1     2 13.9 
#> 3  2013     1     3 11.0 
#> 4  2013     1     4  8.95
#> 5  2013     1     5  5.73
#> 6  2013     1     6  7.15
#> # … with 359 more rows

# 5.6.1 Combining multiple operations with the pipe
by_dest <- group_by(flights, dest)
delay <- summarise(by_dest,
                   count = n(),
                   dist = mean(distance, na.rm = TRUE),
                   delay = mean(arr_delay, na.rm = TRUE)
)
#> `summarise()` ungrouping output (override with `.groups` argument)
delay <- filter(delay, count > 20, dest != "HNL")

# It looks like delays increase with distance up to ~750 miles 
# and then decrease. Maybe as flights get longer there's more 
# ability to make up delays in the air?
ggplot(data = delay, mapping = aes(x = dist, y = delay)) +
  geom_point(aes(size = count), alpha = 1/3) +
  geom_smooth(se = FALSE)
#> `geom_smooth()` using method = 'loess' and formula 'y ~ x'

# There are three steps to prepare this data:
#   Group flights by destination.
#   Summarise to compute distance, average delay, and number of flights.
#   Filter to remove noisy points and Honolulu airport, which is almost twice 
#     as far away as the next closest airport.

# There’s another way to tackle the same problem with the pipe, %>%:
delays <- flights %>% 
  group_by(dest) %>% 
  summarise(
    count = n(),
    dist = mean(distance, na.rm = TRUE),
    delay = mean(arr_delay, na.rm = TRUE)
  ) %>% 
  filter(count > 20, dest != "HNL")
#> `summarise()` ungrouping output (override with `.groups` argument)

# Behind the scenes, x %>% f(y) turns into f(x, y), 
# and x %>% f(y) %>% g(z) turns into g(f(x, y), z) and so on.

# use the pipe to rewrite multiple operations in a way that you 
# can read left-to-right, top-to-bottom

# 5.6.2 Missing values
# if we don’t set na.rm
flights %>% 
  group_by(year, month, day) %>% 
  summarise(mean = mean(dep_delay))
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 4
#> # Groups:   year, month [12]
#>    year month   day  mean
#>   <int> <int> <int> <dbl>
#> 1  2013     1     1    NA
#> 2  2013     1     2    NA
#> 3  2013     1     3    NA
#> 4  2013     1     4    NA
#> 5  2013     1     5    NA
#> 6  2013     1     6    NA
#> # … with 359 more rows

#  aggregation functions obey the usual rule of missing values: if there’s 
# any missing value in the input, the output will be a missing value.
# all aggregation functions have an na.rm argument which removes 
# the missing values prior to computation:
flights %>% 
  group_by(year, month, day) %>% 
  summarise(mean = mean(dep_delay, na.rm = TRUE))
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 4
#> # Groups:   year, month [12]
#>    year month   day  mean
#>   <int> <int> <int> <dbl>
#> 1  2013     1     1 11.5 
#> 2  2013     1     2 13.9 
#> 3  2013     1     3 11.0 
#> 4  2013     1     4  8.95
#> 5  2013     1     5  5.73
#> 6  2013     1     6  7.15
#> # … with 359 more rows

# where missing values represent cancelled flights, we could also tackle 
# the problem by first removing the cancelled flights
not_cancelled <- flights %>% 
  filter(!is.na(dep_delay), !is.na(arr_delay))

not_cancelled %>% 
  group_by(year, month, day) %>% 
  summarise(mean = mean(dep_delay))
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 4
#> # Groups:   year, month [12]
#>    year month   day  mean
#>   <int> <int> <int> <dbl>
#> 1  2013     1     1 11.4 
#> 2  2013     1     2 13.7 
#> 3  2013     1     3 10.9 
#> 4  2013     1     4  8.97
#> 5  2013     1     5  5.73
#> 6  2013     1     6  7.15
#> # … with 359 more rows

# 5.6.3 Counts
# Whenever you do any aggregation, it’s always a good idea to include either 
# a count (n()), or a count of non-missing values (sum(!is.na(x)))

# let’s look at the planes (identified by their tail number) that 
# have the highest average delays:
delays <- not_cancelled %>% 
  group_by(tailnum) %>% 
  summarise(
    delay = mean(arr_delay)
  )
#> `summarise()` ungrouping output (override with `.groups` argument)

ggplot(data = delays, mapping = aes(x = delay)) + 
  geom_freqpoly(binwidth = 10)

# get more insight if we draw a scatterplot of 
# number of flights vs. average delay:
delays <- not_cancelled %>% 
  group_by(tailnum) %>% 
  summarise(
    delay = mean(arr_delay, na.rm = TRUE),
    n = n()
  )
#> `summarise()` ungrouping output (override with `.groups` argument)

ggplot(data = delays, mapping = aes(x = n, y = delay)) + 
  geom_point(alpha = 1/10)

# When looking at this sort of plot, it’s often useful to filter
# out the groups with the smallest numbers of observations, so you can see more
# of the pattern and less of the extreme variation in the smallest groups.
delays %>% 
  filter(n > 25) %>% 
  ggplot(mapping = aes(x = n, y = delay)) + 
  geom_point(alpha = 1/10)

# Ctrl+Shift+P resends the previously sent chunk from the editor to the console.

library(Lahman)

# plot the skill of the batter (measured by the batting average, ba) 
# against the number of opportunities to hit the ball (measured by at bat, ab),
# you see two patterns:
#   the variation in our aggregate decreases as we get more data points.
#   There’s a positive correlation between skill (ba) and opportunities to 
#     hit the ball (ab). This is because teams control who gets to play, 
#     and obviously they’ll pick their best players.

# Convert to a tibble so it prints nicely
batting <- as_tibble(Lahman::Batting)

batters <- batting %>% 
  group_by(playerID) %>% 
  summarise(
    ba = sum(H, na.rm = TRUE) / sum(AB, na.rm = TRUE),
    ab = sum(AB, na.rm = TRUE)
  )
#> `summarise()` ungrouping output (override with `.groups` argument)

batters %>% 
  filter(ab > 100) %>% 
  ggplot(mapping = aes(x = ab, y = ba)) +
  geom_point() + 
  geom_smooth(se = FALSE)
#> `geom_smooth()` using method = 'gam' and formula 'y ~ s(x, bs = "cs")'

# If you sort on desc(ba), the people with the best batting averages are 
# clearly lucky, not skilled:
batters %>% 
  arrange(desc(ba))
#> # A tibble: 19,689 x 3
#>   playerID     ba    ab
#>   <chr>     <dbl> <int>
#> 1 abramge01     1     1
#> 2 alanirj01     1     1
#> 3 alberan01     1     1
#> 4 banisje01     1     1
#> 5 bartocl01     1     1
#> 6 bassdo01      1     1
#> # … with 19,683 more rows

# 5.6.4 Useful summary functions
# Measures of location: we’ve used mean(x), but median(x) is also useful.
not_cancelled %>% 
  group_by(year, month, day) %>% 
  summarise(
    avg_delay1 = mean(arr_delay),
    avg_delay2 = mean(arr_delay[arr_delay > 0]) # the average positive delay
  )
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 5
#> # Groups:   year, month [12]
#>    year month   day avg_delay1 avg_delay2
#>   <int> <int> <int>      <dbl>      <dbl>
#> 1  2013     1     1      12.7        32.5
#> 2  2013     1     2      12.7        32.0
#> 3  2013     1     3       5.73       27.7
#> 4  2013     1     4      -1.93       28.3
#> 5  2013     1     5      -1.53       22.6
#> 6  2013     1     6       4.24       24.4
#> # … with 359 more rows

# Measures of spread: sd(x), IQR(x), mad(x). The root mean squared deviation, 
# or standard deviation sd(x), is the standard measure of spread
# Why is distance to some destinations more variable than to others?
not_cancelled %>% 
  group_by(dest) %>% 
  summarise(distance_sd = sd(distance)) %>% 
  arrange(desc(distance_sd))
#> `summarise()` ungrouping output (override with `.groups` argument)
#> # A tibble: 104 x 2
#>   dest  distance_sd
#>   <chr>       <dbl>
#> 1 EGE         10.5 
#> 2 SAN         10.4 
#> 3 SFO         10.2 
#> 4 HNL         10.0 
#> 5 SEA          9.98
#> 6 LAS          9.91
#> # … with 98 more rows

# Measures of rank: min(x), quantile(x, 0.25), max(x). 
# Quantiles are a generalisation of the median
# When do the first and last flights leave each day?
not_cancelled %>% 
  group_by(year, month, day) %>% 
  summarise(
    first = min(dep_time),
    last = max(dep_time)
  )
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 5
#> # Groups:   year, month [12]
#>    year month   day first  last
#>   <int> <int> <int> <int> <int>
#> 1  2013     1     1   517  2356
#> 2  2013     1     2    42  2354
#> 3  2013     1     3    32  2349
#> 4  2013     1     4    25  2358
#> 5  2013     1     5    14  2357
#> 6  2013     1     6    16  2355
#> # … with 359 more rows

# Measures of position: first(x), nth(x, 2), last(x). These work similarly 
# to x[1], x[2], and x[length(x)] but let you set a 
# default value if that position does not exist
not_cancelled %>% 
  group_by(year, month, day) %>% 
  summarise(
    first_dep = first(dep_time), 
    last_dep = last(dep_time)
  )
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 5
#> # Groups:   year, month [12]
#>    year month   day first_dep last_dep
#>   <int> <int> <int>     <int>    <int>
#> 1  2013     1     1       517     2356
#> 2  2013     1     2        42     2354
#> 3  2013     1     3        32     2349
#> 4  2013     1     4        25     2358
#> 5  2013     1     5        14     2357
#> 6  2013     1     6        16     2355
#> # … with 359 more rows

# Filtering gives you all variables, with each observation in a separate row:
not_cancelled %>% 
  group_by(year, month, day) %>% 
  mutate(r = min_rank(desc(dep_time))) %>% 
  filter(r %in% range(r))
#> # A tibble: 770 x 20
#> # Groups:   year, month, day [365]
#>    year month   day dep_time sched_dep_time dep_delay arr_time sched_arr_time
#>   <int> <int> <int>    <int>          <int>     <dbl>    <int>          <int>
#> 1  2013     1     1      517            515         2      830            819
#> 2  2013     1     1     2356           2359        -3      425            437
#> 3  2013     1     2       42           2359        43      518            442
#> 4  2013     1     2     2354           2359        -5      413            437
#> 5  2013     1     3       32           2359        33      504            442
#> 6  2013     1     3     2349           2359       -10      434            445
#> # … with 764 more rows, and 12 more variables: arr_delay <dbl>, carrier <chr>,
#> #   flight <int>, tailnum <chr>, origin <chr>, dest <chr>, air_time <dbl>,
#> #   distance <dbl>, hour <dbl>, minute <dbl>, time_hour <dttm>, r <int>

# Counts: You’ve seen n(), which takes no arguments, and returns the size of 
# the current group. To count the number of non-missing values, 
# use sum(!is.na(x)). To count the number of distinct (unique) values, 
# use n_distinct(x).
# Which destinations have the most carriers?
not_cancelled %>% 
  group_by(dest) %>% 
  summarise(carriers = n_distinct(carrier)) %>% 
  arrange(desc(carriers))
#> `summarise()` ungrouping output (override with `.groups` argument)
#> # A tibble: 104 x 2
#>   dest  carriers
#>   <chr>    <int>
#> 1 ATL          7
#> 2 BOS          7
#> 3 CLT          7
#> 4 ORD          7
#> 5 TPA          7
#> 6 AUS          6
#> # … with 98 more rows

# dplyr provides a simple helper if all you want is a count:
not_cancelled %>% 
  count(dest)
#> # A tibble: 104 x 2
#>   dest      n
#>   <chr> <int>
#> 1 ABQ     254
#> 2 ACK     264
#> 3 ALB     418
#> 4 ANC       8
#> 5 ATL   16837
#> 6 AUS    2411
#> # … with 98 more rows

# optionally provide a weight variable
# use this to “count” (sum) the total number of miles a plane flew:
not_cancelled %>% 
  count(tailnum, wt = distance)
#> # A tibble: 4,037 x 2
#>   tailnum      n
#>   <chr>    <dbl>
#> 1 D942DN    3418
#> 2 N0EGMQ  239143
#> 3 N10156  109664
#> 4 N102UW   25722
#> 5 N103US   24619
#> 6 N104UW   24616
#> # … with 4,031 more rows

# Counts and proportions of logical values: sum(x > 10), mean(y == 0)
# How many flights left before 5am? (these usually indicate delayed
# flights from the previous day)
not_cancelled %>% 
  group_by(year, month, day) %>% 
  summarise(n_early = sum(dep_time < 500))
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 4
#> # Groups:   year, month [12]
#>    year month   day n_early
#>   <int> <int> <int>   <int>
#> 1  2013     1     1       0
#> 2  2013     1     2       3
#> 3  2013     1     3       4
#> 4  2013     1     4       3
#> 5  2013     1     5       3
#> 6  2013     1     6       2
#> # … with 359 more rows

# What proportion of flights are delayed by more than an hour?
not_cancelled %>% 
  group_by(year, month, day) %>% 
  summarise(hour_prop = mean(arr_delay > 60))
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 4
#> # Groups:   year, month [12]
#>    year month   day hour_prop
#>   <int> <int> <int>     <dbl>
#> 1  2013     1     1    0.0722
#> 2  2013     1     2    0.0851
#> 3  2013     1     3    0.0567
#> 4  2013     1     4    0.0396
#> 5  2013     1     5    0.0349
#> 6  2013     1     6    0.0470
#> # … with 359 more rows

# 5.6.5 Grouping by multiple variables
daily <- group_by(flights, year, month, day)
(per_day   <- summarise(daily, flights = n()))
#> `summarise()` regrouping output by 'year', 'month' (override with `.groups` argument)
#> # A tibble: 365 x 4
#> # Groups:   year, month [12]
#>    year month   day flights
#>   <int> <int> <int>   <int>
#> 1  2013     1     1     842
#> 2  2013     1     2     943
#> 3  2013     1     3     914
#> 4  2013     1     4     915
#> 5  2013     1     5     720
#> 6  2013     1     6     832
#> # … with 359 more rows
(per_month <- summarise(per_day, flights = sum(flights)))
#> `summarise()` regrouping output by 'year' (override with `.groups` argument)
#> # A tibble: 12 x 3
#> # Groups:   year [1]
#>    year month flights
#>   <int> <int>   <int>
#> 1  2013     1   27004
#> 2  2013     2   24951
#> 3  2013     3   28834
#> 4  2013     4   28330
#> 5  2013     5   28796
#> 6  2013     6   28243
#> # … with 6 more rows
(per_year  <- summarise(per_month, flights = sum(flights)))
#> `summarise()` ungrouping output (override with `.groups` argument)
#> # A tibble: 1 x 2
#>    year flights
#>   <int>   <int>
#> 1  2013  336776

# 5.6.6 Ungrouping

daily %>% 
  ungroup() %>%             # no longer grouped by date
  summarise(flights = n())  # all flights
#> # A tibble: 1 x 1
#>   flights
#>     <int>
#> 1  336776

# 5.7 Grouped mutates (and filters)
# Find the worst members of each group:
flights_sml %>% 
  group_by(year, month, day) %>%
  filter(rank(desc(arr_delay)) < 10)
#> # A tibble: 3,306 x 7
#> # Groups:   year, month, day [365]
#>    year month   day dep_delay arr_delay distance air_time
#>   <int> <int> <int>     <dbl>     <dbl>    <dbl>    <dbl>
#> 1  2013     1     1       853       851      184       41
#> 2  2013     1     1       290       338     1134      213
#> 3  2013     1     1       260       263      266       46
#> 4  2013     1     1       157       174      213       60
#> 5  2013     1     1       216       222      708      121
#> 6  2013     1     1       255       250      589      115
#> # … with 3,300 more rows

# Find all groups bigger than a threshold:
popular_dests <- flights %>% 
  group_by(dest) %>% 
  filter(n() > 365)
popular_dests
#> # A tibble: 332,577 x 19
#> # Groups:   dest [77]
#>    year month   day dep_time sched_dep_time dep_delay arr_time sched_arr_time
#>   <int> <int> <int>    <int>          <int>     <dbl>    <int>          <int>
#> 1  2013     1     1      517            515         2      830            819
#> 2  2013     1     1      533            529         4      850            830
#> 3  2013     1     1      542            540         2      923            850
#> 4  2013     1     1      544            545        -1     1004           1022
#> 5  2013     1     1      554            600        -6      812            837
#> 6  2013     1     1      554            558        -4      740            728
#> # … with 332,571 more rows, and 11 more variables: arr_delay <dbl>,
#> #   carrier <chr>, flight <int>, tailnum <chr>, origin <chr>, dest <chr>,
#> #   air_time <dbl>, distance <dbl>, hour <dbl>, minute <dbl>, time_hour <dttm>

# Standardise to compute per group metrics:
popular_dests %>% 
  filter(arr_delay > 0) %>% 
  mutate(prop_delay = arr_delay / sum(arr_delay)) %>% 
  select(year:day, dest, arr_delay, prop_delay)
#> # A tibble: 131,106 x 6
#> # Groups:   dest [77]
#>    year month   day dest  arr_delay prop_delay
#>   <int> <int> <int> <chr>     <dbl>      <dbl>
#> 1  2013     1     1 IAH          11  0.000111 
#> 2  2013     1     1 IAH          20  0.000201 
#> 3  2013     1     1 MIA          33  0.000235 
#> 4  2013     1     1 ORD          12  0.0000424
#> 5  2013     1     1 FLL          19  0.0000938
#> 6  2013     1     1 ORD           8  0.0000283
#> # … with 131,100 more rows