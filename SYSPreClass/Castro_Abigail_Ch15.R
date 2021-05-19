# Abigail Castro - abc3gnm
# Chapter 15 Summary and code

# Chapter 15 Factors
# In R, factors are used to work with categorical variables, variables that have a fixed and known set of possible values
library(tidyverse)

# 15.2 Creating factors
x1 <- c("Dec", "Apr", "Jan", "Mar")
# There are only twelve possible months, and there’s nothing saving you from typos:
x2 <- c("Dec", "Apr", "Jam", "Mar")
# It doesn’t sort in a useful way:
sort(x1)
#> [1] "Apr" "Dec" "Jan" "Mar"

# To create a factor you must start by creating a list of the valid levels:
month_levels <- c(
  "Jan", "Feb", "Mar", "Apr", "May", "Jun", 
  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
)

y1 <- factor(x1, levels = month_levels)
y1
#> [1] Dec Apr Jan Mar
#> Levels: Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec
sort(y1)
#> [1] Jan Mar Apr Dec
#> Levels: Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec

# And any values not in the set will be silently converted to NA:
y2 <- factor(x2, levels = month_levels)
y2
#> [1] Dec  Apr  <NA> Mar 
#> Levels: Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec

# If you want a warning, you can use readr::parse_factor():
y2 <- parse_factor(x2, levels = month_levels)
#> Warning: 1 parsing failure.
#> row col           expected actual
#>   3  -- value in level set    Jam

# If you omit the levels, they’ll be taken from the data in alphabetical order:
factor(x1)
#> [1] Dec Apr Jan Mar
#> Levels: Apr Dec Jan Mar

f1 <- factor(x1, levels = unique(x1))
f1
#> [1] Dec Apr Jan Mar
#> Levels: Dec Apr Jan Mar

f2 <- x1 %>% factor() %>% fct_inorder()
f2
#> [1] Dec Apr Jan Mar
#> Levels: Dec Apr Jan Mar

levels(f2)
#> [1] "Dec" "Apr" "Jan" "Mar"

# 15.3 General Social Survey
gss_cat
#> # A tibble: 21,483 x 9
#>    year marital      age race  rincome    partyid     relig     denom    tvhours
#>   <int> <fct>      <int> <fct> <fct>      <fct>       <fct>     <fct>      <int>
#> 1  2000 Never mar…    26 White $8000 to … Ind,near r… Protesta… Souther…      12
#> 2  2000 Divorced      48 White $8000 to … Not str re… Protesta… Baptist…      NA
#> 3  2000 Widowed       67 White Not appli… Independent Protesta… No deno…       2
#> 4  2000 Never mar…    39 White Not appli… Ind,near r… Orthodox… Not app…       4
#> 5  2000 Divorced      25 White Not appli… Not str de… None      Not app…       1
#> 6  2000 Married       25 White $20000 - … Strong dem… Protesta… Souther…      NA
#> # … with 21,477 more rows

# When factors are stored in a tibble, you can’t see their levels so easily. One way to see them is with count():
gss_cat %>%
  count(race)
#> # A tibble: 3 x 2
#>   race      n
#>   <fct> <int>
#> 1 Other  1959
#> 2 Black  3129
#> 3 White 16395

ggplot(gss_cat, aes(race)) +
  geom_bar()

# By default, ggplot2 will drop levels that don’t have any values. You can force them to display with:
ggplot(gss_cat, aes(race)) +
  geom_bar() +
  scale_x_discrete(drop = FALSE)

# 15.4 Modifying factor order
relig_summary <- gss_cat %>%
  group_by(relig) %>%
  summarise(
    age = mean(age, na.rm = TRUE),
    tvhours = mean(tvhours, na.rm = TRUE),
    n = n()
  )
#> `summarise()` ungrouping output (override with `.groups` argument)

ggplot(relig_summary, aes(tvhours, relig)) + geom_point()

# fct_reorder() takes three arguments:
  # f, the factor whose levels you want to modify.
  # x, a numeric vector that you want to use to reorder the levels.
  # Optionally, fun, a function that’s used if there are multiple values of x for each value of f. The default value is median.

ggplot(relig_summary, aes(tvhours, fct_reorder(relig, tvhours))) +
  geom_point()

relig_summary %>%
  mutate(relig = fct_reorder(relig, tvhours)) %>%
  ggplot(aes(tvhours, relig)) +
  geom_point()

rincome_summary <- gss_cat %>%
  group_by(rincome) %>%
  summarise(
    age = mean(age, na.rm = TRUE),
    tvhours = mean(tvhours, na.rm = TRUE),
    n = n()
  )
#> `summarise()` ungrouping output (override with `.groups` argument)

ggplot(rincome_summary, aes(age, fct_reorder(rincome, age))) + geom_point()

ggplot(rincome_summary, aes(age, fct_relevel(rincome, "Not applicable"))) +
  geom_point()

by_age <- gss_cat %>%
  filter(!is.na(age)) %>%
  count(age, marital) %>%
  group_by(age) %>%
  mutate(prop = n / sum(n))

ggplot(by_age, aes(age, prop, colour = marital)) +
  geom_line(na.rm = TRUE)

ggplot(by_age, aes(age, prop, colour = fct_reorder2(marital, age, prop))) +
  geom_line() +
  labs(colour = "marital")

gss_cat %>%
  mutate(marital = marital %>% fct_infreq() %>% fct_rev()) %>%
  ggplot(aes(marital)) +
  geom_bar()

# 15.5 Modifying factor levels
# fct_recode(). It allows you to recode, or change, the value of each level.
gss_cat %>% count(partyid)
#> # A tibble: 10 x 2
#>   partyid                n
#>   <fct>              <int>
#> 1 No answer            154
#> 2 Don't know             1
#> 3 Other party          393
#> 4 Strong republican   2314
#> 5 Not str republican  3032
#> 6 Ind,near rep        1791
#> # … with 4 more rows

gss_cat %>%
  mutate(partyid = fct_recode(partyid,
                              "Republican, strong"    = "Strong republican",
                              "Republican, weak"      = "Not str republican",
                              "Independent, near rep" = "Ind,near rep",
                              "Independent, near dem" = "Ind,near dem",
                              "Democrat, weak"        = "Not str democrat",
                              "Democrat, strong"      = "Strong democrat"
  )) %>%
  count(partyid)
#> # A tibble: 10 x 2
#>   partyid                   n
#>   <fct>                 <int>
#> 1 No answer               154
#> 2 Don't know                1
#> 3 Other party             393
#> 4 Republican, strong     2314
#> 5 Republican, weak       3032
#> 6 Independent, near rep  1791
#> # … with 4 more rows

# To combine groups, you can assign multiple old levels to the same new level:
gss_cat %>%
  mutate(partyid = fct_recode(partyid,
                              "Republican, strong"    = "Strong republican",
                              "Republican, weak"      = "Not str republican",
                              "Independent, near rep" = "Ind,near rep",
                              "Independent, near dem" = "Ind,near dem",
                              "Democrat, weak"        = "Not str democrat",
                              "Democrat, strong"      = "Strong democrat",
                              "Other"                 = "No answer",
                              "Other"                 = "Don't know",
                              "Other"                 = "Other party"
  )) %>%
  count(partyid)
#> # A tibble: 8 x 2
#>   partyid                   n
#>   <fct>                 <int>
#> 1 Other                   548
#> 2 Republican, strong     2314
#> 3 Republican, weak       3032
#> 4 Independent, near rep  1791
#> 5 Independent            4119
#> 6 Independent, near dem  2499
#> # … with 2 more rows

# For each new variable, you can provide a vector of old levels:
gss_cat %>%
  mutate(partyid = fct_collapse(partyid,
                                other = c("No answer", "Don't know", "Other party"),
                                rep = c("Strong republican", "Not str republican"),
                                ind = c("Ind,near rep", "Independent", "Ind,near dem"),
                                dem = c("Not str democrat", "Strong democrat")
  )) %>%
  count(partyid)
#> # A tibble: 4 x 2
#>   partyid     n
#>   <fct>   <int>
#> 1 other     548
#> 2 rep      5346
#> 3 ind      8409
#> 4 dem      7180

# lump together all the small groups to make a plot or table simpler
gss_cat %>%
  mutate(relig = fct_lump(relig)) %>%
  count(relig)
#> # A tibble: 2 x 2
#>   relig          n
#>   <fct>      <int>
#> 1 Protestant 10846
#> 2 Other      10637

# we can use the n parameter to specify how many groups (excluding other) we want to keep:
gss_cat %>%
  mutate(relig = fct_lump(relig, n = 10)) %>%
  count(relig, sort = TRUE) %>%
  print(n = Inf)
#> # A tibble: 10 x 2
#>    relig                       n
#>    <fct>                   <int>
#>  1 Protestant              10846
#>  2 Catholic                 5124
#>  3 None                     3523
#>  4 Christian                 689
#>  5 Other                     458
#>  6 Jewish                    388
#>  7 Buddhism                  147
#>  8 Inter-nondenominational   109
#>  9 Moslem/islam              104
#> 10 Orthodox-christian         95






























