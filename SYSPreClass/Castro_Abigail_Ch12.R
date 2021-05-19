# Abigail Castro - abc3gnm
# Chapter 12 Summary and code

# Chapter 12 Tidy Data
# consistent way to organise your data in R, an organisation called tidy data. 

library(tidyverse)

# 12.2 Tidy data

# There are three interrelated rules which make a dataset tidy:
  # Each variable must have its own column.
  # Each observation must have its own row.
  # Each value must have its own cell.
table1 #tidy
#> # A tibble: 6 x 4
#>   country      year  cases population
#>   <chr>       <int>  <int>      <int>
#> 1 Afghanistan  1999    745   19987071
#> 2 Afghanistan  2000   2666   20595360
#> 3 Brazil       1999  37737  172006362
#> 4 Brazil       2000  80488  174504898
#> 5 China        1999 212258 1272915272
#> 6 China        2000 213766 1280428583
table2
#> # A tibble: 12 x 4
#>   country      year type           count
#>   <chr>       <int> <chr>          <int>
#> 1 Afghanistan  1999 cases            745
#> 2 Afghanistan  1999 population  19987071
#> 3 Afghanistan  2000 cases           2666
#> 4 Afghanistan  2000 population  20595360
#> 5 Brazil       1999 cases          37737
#> 6 Brazil       1999 population 172006362
#> # … with 6 more rows
table3
#> # A tibble: 6 x 3
#>   country      year rate             
#> * <chr>       <int> <chr>            
#> 1 Afghanistan  1999 745/19987071     
#> 2 Afghanistan  2000 2666/20595360    
#> 3 Brazil       1999 37737/172006362  
#> 4 Brazil       2000 80488/174504898  
#> 5 China        1999 212258/1272915272
#> 6 China        2000 213766/1280428583

# Spread across two tibbles
table4a  # cases
#> # A tibble: 3 x 3
#>   country     `1999` `2000`
#> * <chr>        <int>  <int>
#> 1 Afghanistan    745   2666
#> 2 Brazil       37737  80488
#> 3 China       212258 213766
table4b  # population
#> # A tibble: 3 x 3
#>   country         `1999`     `2000`
#> * <chr>            <int>      <int>
#> 1 Afghanistan   19987071   20595360
#> 2 Brazil       172006362  174504898
#> 3 China       1272915272 1280428583


# Compute rate per 10,000
table1 %>% 
  mutate(rate = cases / population * 10000)
#> # A tibble: 6 x 5
#>   country      year  cases population  rate
#>   <chr>       <int>  <int>      <int> <dbl>
#> 1 Afghanistan  1999    745   19987071 0.373
#> 2 Afghanistan  2000   2666   20595360 1.29 
#> 3 Brazil       1999  37737  172006362 2.19 
#> 4 Brazil       2000  80488  174504898 4.61 
#> 5 China        1999 212258 1272915272 1.67 
#> 6 China        2000 213766 1280428583 1.67

# Compute cases per year
table1 %>% 
  count(year, wt = cases)
#> # A tibble: 2 x 2
#>    year      n
#>   <int>  <int>
#> 1  1999 250740
#> 2  2000 296920

# Visualise changes over time
library(ggplot2)
ggplot(table1, aes(year, cases)) + 
  geom_line(aes(group = country), colour = "grey50") + 
  geom_point(aes(colour = country))

# 12.3 Pivoting
# The first step is always to figure out what the variables and observations are.
# The second step is to resolve one of two common problems:
  # One variable might be spread across multiple columns.
  # One observation might be scattered across multiple rows.

# 12.3.1 Longer
# A common problem is a dataset where some of the column names are not names of variables, but values of a variable.
# column names represent values of year, and columns represent value of cases
table4a
#> # A tibble: 3 x 3
#>   country     `1999` `2000`
#> * <chr>        <int>  <int>
#> 1 Afghanistan    745   2666
#> 2 Brazil       37737  80488
#> 3 China       212258 213766

# need to pivot the offending columns into a new pair of variables. To describe that operation we need three parameters:
  # The set of columns whose names are values, not variables. In this example, those are the columns 1999 and 2000.
  # The name of the variable to move the column names to. Here it is year.
  # The name of the variable to move the column values to. Here it’s cases.
table4a %>% 
  pivot_longer(c(`1999`, `2000`), names_to = "year", values_to = "cases")
#> # A tibble: 6 x 3
#>   country     year   cases
#>   <chr>       <chr>  <int>
#> 1 Afghanistan 1999     745
#> 2 Afghanistan 2000    2666
#> 3 Brazil      1999   37737
#> 4 Brazil      2000   80488
#> 5 China       1999  212258
#> 6 China       2000  213766

# pivot_longer() makes datasets longer by increasing the number of rows and decreasing the number of columns.
table4b %>% 
  pivot_longer(c(`1999`, `2000`), names_to = "year", values_to = "population")
#> # A tibble: 6 x 3
#>   country     year  population
#>   <chr>       <chr>      <int>
#> 1 Afghanistan 1999    19987071
#> 2 Afghanistan 2000    20595360
#> 3 Brazil      1999   172006362
#> 4 Brazil      2000   174504898
#> 5 China       1999  1272915272
#> 6 China       2000  1280428583

# combine the tidied versions of table4a and table4b

tidy4a <- table4a %>% 
  pivot_longer(c(`1999`, `2000`), names_to = "year", values_to = "cases")
tidy4b <- table4b %>% 
  pivot_longer(c(`1999`, `2000`), names_to = "year", values_to = "population")
left_join(tidy4a, tidy4b)
#> Joining, by = c("country", "year")
#> # A tibble: 6 x 4
#>   country     year   cases population
#>   <chr>       <chr>  <int>      <int>
#> 1 Afghanistan 1999     745   19987071
#> 2 Afghanistan 2000    2666   20595360
#> 3 Brazil      1999   37737  172006362
#> 4 Brazil      2000   80488  174504898
#> 5 China       1999  212258 1272915272
#> 6 China       2000  213766 1280428583

# 12.3.2 Wider
# table2: an observation is a country in a year, but each observation is spread across two rows.
table2
#> # A tibble: 12 x 4
#>   country      year type           count
#>   <chr>       <int> <chr>          <int>
#> 1 Afghanistan  1999 cases            745
#> 2 Afghanistan  1999 population  19987071
#> 3 Afghanistan  2000 cases           2666
#> 4 Afghanistan  2000 population  20595360
#> 5 Brazil       1999 cases          37737
#> 6 Brazil       1999 population 172006362
#> # … with 6 more rows

# pivot_wider() needs two parameters:
  # The column to take variable names from. Here, it’s type.
  # The column to take values from. Here it’s count.

table2 %>%
  pivot_wider(names_from = type, values_from = count)
#> # A tibble: 6 x 4
#>   country      year  cases population
#>   <chr>       <int>  <int>      <int>
#> 1 Afghanistan  1999    745   19987071
#> 2 Afghanistan  2000   2666   20595360
#> 3 Brazil       1999  37737  172006362
#> 4 Brazil       2000  80488  174504898
#> 5 China        1999 212258 1272915272
#> 6 China        2000 213766 1280428583

# 12.4 Separating and uniting
# table3 has a different problem: we have one column (rate) that contains two variables (cases and population)
# separate() pulls apart one column into multiple columns, by splitting wherever a separator character appears. Take table3:
table3
#> # A tibble: 6 x 3
#>   country      year rate             
#> * <chr>       <int> <chr>            
#> 1 Afghanistan  1999 745/19987071     
#> 2 Afghanistan  2000 2666/20595360    
#> 3 Brazil       1999 37737/172006362  
#> 4 Brazil       2000 80488/174504898  
#> 5 China        1999 212258/1272915272
#> 6 China        2000 213766/1280428583

# separate() takes the name of the column to separate, and the names of the columns to separate into, as shown in Figure 12.4 and the code below.
table3 %>% 
  separate(rate, into = c("cases", "population"))
#> # A tibble: 6 x 4
#>   country      year cases  population
#>   <chr>       <int> <chr>  <chr>     
#> 1 Afghanistan  1999 745    19987071  
#> 2 Afghanistan  2000 2666   20595360  
#> 3 Brazil       1999 37737  172006362 
#> 4 Brazil       2000 80488  174504898 
#> 5 China        1999 212258 1272915272
#> 6 China        2000 213766 1280428583

# By default, separate() will split values wherever it sees a non-alphanumeric character (i.e. a character that isn’t a number or letter). 
# If you wish to use a specific character to separate a column, you can pass the character to the sep argument of separate(). 
table3 %>% 
  separate(rate, into = c("cases", "population"), sep = "/")


# default behavior separate(): cases and population are character columns.
#  We can ask separate() to try and convert to better types using convert = TRUE:
table3 %>% 
  separate(rate, into = c("cases", "population"), convert = TRUE)
#> # A tibble: 6 x 4
#>   country      year  cases population
#>   <chr>       <int>  <int>      <int>
#> 1 Afghanistan  1999    745   19987071
#> 2 Afghanistan  2000   2666   20595360
#> 3 Brazil       1999  37737  172006362
#> 4 Brazil       2000  80488  174504898
#> 5 China        1999 212258 1272915272
#> 6 China        2000 213766 1280428583

# You can also pass a vector of integers to sep. separate() will interpret the integers as positions to split at.
table3 %>% 
  separate(year, into = c("century", "year"), sep = 2)
#> # A tibble: 6 x 4
#>   country     century year  rate             
#>   <chr>       <chr>   <chr> <chr>            
#> 1 Afghanistan 19      99    745/19987071     
#> 2 Afghanistan 20      00    2666/20595360    
#> 3 Brazil      19      99    37737/172006362  
#> 4 Brazil      20      00    80488/174504898  
#> 5 China       19      99    212258/1272915272
#> 6 China       20      00    213766/1280428583

# 12.4.2 Unite
# unite() is the inverse of separate(): it combines multiple columns into a single column.
table5 %>% 
  unite(new, century, year)
#> # A tibble: 6 x 3
#>   country     new   rate             
#>   <chr>       <chr> <chr>            
#> 1 Afghanistan 19_99 745/19987071     
#> 2 Afghanistan 20_00 2666/20595360    
#> 3 Brazil      19_99 37737/172006362  
#> 4 Brazil      20_00 80488/174504898  
#> 5 China       19_99 212258/1272915272
#> 6 China       20_00 213766/1280428583

# The default will place an underscore (_) between the values from different columns. 
table5 %>% 
  unite(new, century, year, sep = "")
#> # A tibble: 6 x 3
#>   country     new   rate             
#>   <chr>       <chr> <chr>            
#> 1 Afghanistan 1999  745/19987071     
#> 2 Afghanistan 2000  2666/20595360    
#> 3 Brazil      1999  37737/172006362  
#> 4 Brazil      2000  80488/174504898  
#> 5 China       1999  212258/1272915272
#> 6 China       2000  213766/1280428583

# 12.5 Missing values
# A value can be missing in one of two possible ways:
  # Explicitly, i.e. flagged with NA.
  # Implicitly, i.e. simply not present in the data.
stocks <- tibble(
  year   = c(2015, 2015, 2015, 2015, 2016, 2016, 2016),
  qtr    = c(   1,    2,    3,    4,    2,    3,    4),
  return = c(1.88, 0.59, 0.35,   NA, 0.92, 0.17, 2.66)
)
# There are two missing values in this dataset:
  # The return for the fourth quarter of 2015 is explicitly missing, because the cell where its value should be instead contains NA.
  # The return for the first quarter of 2016 is implicitly missing, because it simply does not appear in the dataset.

stocks %>% 
  pivot_wider(names_from = year, values_from = return)
#> # A tibble: 4 x 3
#>     qtr `2015` `2016`
#>   <dbl>  <dbl>  <dbl>
#> 1     1   1.88  NA   
#> 2     2   0.59   0.92
#> 3     3   0.35   0.17
#> 4     4  NA      2.66

# Because these explicit missing values may not be important in other
# representations of the data, you can set values_drop_na = TRUE in pivot_longer() to turn explicit missing values implicit:
stocks %>% 
  pivot_wider(names_from = year, values_from = return) %>% 
  pivot_longer(
    cols = c(`2015`, `2016`), 
    names_to = "year", 
    values_to = "return", 
    values_drop_na = TRUE
  )
#> # A tibble: 6 x 3
#>     qtr year  return
#>   <dbl> <chr>  <dbl>
#> 1     1 2015    1.88
#> 2     2 2015    0.59
#> 3     2 2016    0.92
#> 4     3 2015    0.35
#> 5     3 2016    0.17
#> 6     4 2016    2.66

# complete(): makes missing values explicit
stocks %>% 
  complete(year, qtr)
#> # A tibble: 8 x 3
#>    year   qtr return
#>   <dbl> <dbl>  <dbl>
#> 1  2015     1   1.88
#> 2  2015     2   0.59
#> 3  2015     3   0.35
#> 4  2015     4  NA   
#> 5  2016     1  NA   
#> 6  2016     2   0.92
#> # … with 2 more rows

# complete() takes a set of columns, and finds all unique combinations.
# It then ensures the original dataset contains all those values, filling in explicit NAs where necessary.

#Sometimes when a data source has primarily been used for data entry, missing values indicate that the previous value should be carried forward:
treatment <- tribble(
  ~ person,           ~ treatment, ~response,
  "Derrick Whitmore", 1,           7,
  NA,                 2,           10,
  NA,                 3,           9,
  "Katherine Burke",  1,           4
)

# You can fill in these missing values with fill(). It takes a set of columns where you want missing values to be replaced by the most recent non-missing value (sometimes called last observation carried forward).
treatment %>% 
  fill(person)
#> # A tibble: 4 x 3
#>   person           treatment response
#>   <chr>                <dbl>    <dbl>
#> 1 Derrick Whitmore         1        7
#> 2 Derrick Whitmore         2       10
#> 3 Derrick Whitmore         3        9
#> 4 Katherine Burke          1        4

# 12.6 Case Study
who
#> # A tibble: 7,240 x 60
#>   country iso2  iso3   year new_sp_m014 new_sp_m1524 new_sp_m2534 new_sp_m3544
#>   <chr>   <chr> <chr> <int>       <int>        <int>        <int>        <int>
#> 1 Afghan… AF    AFG    1980          NA           NA           NA           NA
#> 2 Afghan… AF    AFG    1981          NA           NA           NA           NA
#> 3 Afghan… AF    AFG    1982          NA           NA           NA           NA
#> 4 Afghan… AF    AFG    1983          NA           NA           NA           NA
#> 5 Afghan… AF    AFG    1984          NA           NA           NA           NA
#> 6 Afghan… AF    AFG    1985          NA           NA           NA           NA
#> # … with 7,234 more rows, and 52 more variables: new_sp_m4554 <int>,
#> #   new_sp_m5564 <int>, new_sp_m65 <int>, new_sp_f014 <int>,
#> #   new_sp_f1524 <int>, new_sp_f2534 <int>, new_sp_f3544 <int>,
#> #   new_sp_f4554 <int>, new_sp_f5564 <int>, new_sp_f65 <int>,
#> #   new_sn_m014 <int>, new_sn_m1524 <int>, new_sn_m2534 <int>,
#> #   new_sn_m3544 <int>, new_sn_m4554 <int>, new_sn_m5564 <int>,
#> #   new_sn_m65 <int>, new_sn_f014 <int>, new_sn_f1524 <int>,
#> #   new_sn_f2534 <int>, new_sn_f3544 <int>, new_sn_f4554 <int>,
#> #   new_sn_f5564 <int>, new_sn_f65 <int>, new_ep_m014 <int>,
#> #   new_ep_m1524 <int>, new_ep_m2534 <int>, new_ep_m3544 <int>,
#> #   new_ep_m4554 <int>, new_ep_m5564 <int>, new_ep_m65 <int>,
#> #   new_ep_f014 <int>, new_ep_f1524 <int>, new_ep_f2534 <int>,
#> #   new_ep_f3544 <int>, new_ep_f4554 <int>, new_ep_f5564 <int>,
#> #   new_ep_f65 <int>, newrel_m014 <int>, newrel_m1524 <int>,
#> #   newrel_m2534 <int>, newrel_m3544 <int>, newrel_m4554 <int>,
#> #   newrel_m5564 <int>, newrel_m65 <int>, newrel_f014 <int>,
#> #   newrel_f1524 <int>, newrel_f2534 <int>, newrel_f3544 <int>,
#> #   newrel_f4554 <int>, newrel_f5564 <int>, newrel_f65 <int>

#  It contains redundant columns, odd variable codes, and many missing values
  # It looks like country, iso2, and iso3 are three variables that redundantly specify the country.
  # year is clearly also a variable.
  # We don’t know what all the other columns are yet, but given the structure in the variable names (e.g. new_sp_m014, new_ep_m014, new_ep_f014) these are likely to be values, not variables.

who1 <- who %>% 
  pivot_longer(
    cols = new_sp_m014:newrel_f65, 
    names_to = "key", 
    values_to = "cases", 
    values_drop_na = TRUE
  )
who1
#> # A tibble: 76,046 x 6
#>   country     iso2  iso3   year key          cases
#>   <chr>       <chr> <chr> <int> <chr>        <int>
#> 1 Afghanistan AF    AFG    1997 new_sp_m014      0
#> 2 Afghanistan AF    AFG    1997 new_sp_m1524    10
#> 3 Afghanistan AF    AFG    1997 new_sp_m2534     6
#> 4 Afghanistan AF    AFG    1997 new_sp_m3544     3
#> 5 Afghanistan AF    AFG    1997 new_sp_m4554     5
#> 6 Afghanistan AF    AFG    1997 new_sp_m5564     2
#> # … with 76,040 more rows

who1 %>% 
  count(key)
#> # A tibble: 56 x 2
#>   key              n
#>   <chr>        <int>
#> 1 new_ep_f014   1032
#> 2 new_ep_f1524  1021
#> 3 new_ep_f2534  1021
#> 4 new_ep_f3544  1021
#> 5 new_ep_f4554  1017
#> 6 new_ep_f5564  1017
#> # … with 50 more rows

# The first three letters of each column denote whether the column contains new or old cases of TB. In this dataset, each column contains new cases.
# The next two letters describe the type of TB:
  #rel stands for cases of relapse
  #ep stands for cases of extrapulmonary TB
  #sn stands for cases of pulmonary TB that could not be diagnosed by a pulmonary smear (smear negative)
  #sp stands for cases of pulmonary TB that could be diagnosed by a pulmonary smear (smear positive)
#The sixth letter gives the sex of TB patients. The dataset groups cases by males (m) and females (f).
#The remaining numbers gives the age group. The dataset groups cases into seven age groups:
  #014 = 0 – 14 years old
  #1524 = 15 – 24 years old
  #2534 = 25 – 34 years old
  #3544 = 35 – 44 years old
  #5564 = 55 – 64 years old
  #65 = 65 or older

# replace the characters “newrel” with “new_rel”. This makes all variable names consistent.
who2 <- who1 %>% 
  mutate(key = stringr::str_replace(key, "newrel", "new_rel"))
who2
#> # A tibble: 76,046 x 6
#>   country     iso2  iso3   year key          cases
#>   <chr>       <chr> <chr> <int> <chr>        <int>
#> 1 Afghanistan AF    AFG    1997 new_sp_m014      0
#> 2 Afghanistan AF    AFG    1997 new_sp_m1524    10
#> 3 Afghanistan AF    AFG    1997 new_sp_m2534     6
#> 4 Afghanistan AF    AFG    1997 new_sp_m3544     3
#> 5 Afghanistan AF    AFG    1997 new_sp_m4554     5
#> 6 Afghanistan AF    AFG    1997 new_sp_m5564     2
#> # … with 76,040 more rows

# We can separate the values in each code with two passes of separate(). The first pass will split the codes at each underscore.
who3 <- who2 %>% 
  separate(key, c("new", "type", "sexage"), sep = "_")
who3
#> # A tibble: 76,046 x 8
#>   country     iso2  iso3   year new   type  sexage cases
#>   <chr>       <chr> <chr> <int> <chr> <chr> <chr>  <int>
#> 1 Afghanistan AF    AFG    1997 new   sp    m014       0
#> 2 Afghanistan AF    AFG    1997 new   sp    m1524     10
#> 3 Afghanistan AF    AFG    1997 new   sp    m2534      6
#> 4 Afghanistan AF    AFG    1997 new   sp    m3544      3
#> 5 Afghanistan AF    AFG    1997 new   sp    m4554      5
#> 6 Afghanistan AF    AFG    1997 new   sp    m5564      2
#> # … with 76,040 more rows

# drop the new column because it’s constant in this dataset. While we’re dropping columns, let’s also drop iso2 and iso3 since they’re redundant.
who3 %>% 
  count(new)
#> # A tibble: 1 x 2
#>   new       n
#>   <chr> <int>
#> 1 new   76046
who4 <- who3 %>% 
  select(-new, -iso2, -iso3)

# separate sexage into sex and age by splitting after the first character:
who5 <- who4 %>% 
  separate(sexage, c("sex", "age"), sep = 1)
who5
#> # A tibble: 76,046 x 6
#>   country      year type  sex   age   cases
#>   <chr>       <int> <chr> <chr> <chr> <int>
#> 1 Afghanistan  1997 sp    m     014       0
#> 2 Afghanistan  1997 sp    m     1524     10
#> 3 Afghanistan  1997 sp    m     2534      6
#> 4 Afghanistan  1997 sp    m     3544      3
#> 5 Afghanistan  1997 sp    m     4554      5
#> 6 Afghanistan  1997 sp    m     5564      2
#> # … with 76,040 more rows

who %>%
  pivot_longer(
    cols = new_sp_m014:newrel_f65, 
    names_to = "key", 
    values_to = "cases", 
    values_drop_na = TRUE
  ) %>% 
  mutate(
    key = stringr::str_replace(key, "newrel", "new_rel")
  ) %>%
  separate(key, c("new", "var", "sexage")) %>% 
  select(-new, -iso2, -iso3) %>% 
  separate(sexage, c("sex", "age"), sep = 1)

# 12.7 Non-tidy data
# There are two main reasons to use other data structures:
  # Alternative representations may have substantial performance or space advantages.
  # Specialised fields have evolved their own conventions for storing data that may be quite different to the conventions of tidy data.









