# Abigail Castro - abc3gnm
# Chapter 7 Summary and code

# Chapter 7 Data Transformation
# use visualisation and transformation to explore your data in a systematic way

# 7.1 Introduction
# Load packages
library(tidyverse)
library(nycflights13)

# variable: a quantity, quality, or property that you can measure
# value: the state of a variable when you measure it
# observation: a set of measurements made under similar conditions
#   An observation will contain several values, each associated with a 
#   different variable. I’ll sometimes refer to an observation as a data point.
# Tabular data: a set of values, each associated with a variable and 
#   an observation

# 7.3 Variation
# Variation: the tendency of the values of a variable to change 
#   from measurement to measurement

# 7.3.1 Visualizing distributions
# A variable is categorical if it can only take one of a small set of values
# To examine the distribution of a categorical variable, use a bar chart:
ggplot(data = diamonds) +
  geom_bar(mapping = aes(x = cut))

# height of the bars displays how many observations occured with each x value

# compute manually with:
diamonds %>% 
  count(cut)

# A variable is continuous if it can take any of an 
#   infinite set of ordered values.
# To examine the distribution of a continuous variable, use a histogram:
ggplot(data = diamonds) +
  geom_histogram(mapping = aes(x = carat), binwidth = 0.5)

# compute this by hand by combining dplyr::count() and ggplot2::cut_width():
diamonds %>% 
  count(cut_width(carat, 0.5))

# here is how the graph above looks when we zoom into just the diamonds with 
# a size of less than three carats and choose a smaller binwidth.
smaller <- diamonds %>% 
  filter(carat < 3)

ggplot(data = smaller, mapping = aes(x = carat)) +
  geom_histogram(binwidth = 0.1)

# geom_freqpoly() performs the same calculation as geom_histogram(), 
# but instead of displaying the counts with bars, uses lines instead
ggplot(data = smaller, mapping = aes(x = carat, colour = cut)) +
  geom_freqpoly(binwidth = 0.1)

# Clusters of similar values suggest that subgroups exist in your data
ggplot(data = smaller, mapping = aes(x = carat)) +
  geom_histogram(binwidth = 0.01)

# The histogram below shows the length (in minutes) of 272 eruptions of the Old 
# Faithful Geyser in Yellowstone National Park. Eruption times appear to be 
# clustered into two groups: there are short eruptions (of around 2 minutes) 
# and long eruptions (4-5 minutes), but little in between.
ggplot(data = faithful, mapping = aes(x = eruptions)) + 
  geom_histogram(binwidth = 0.25)

# 7.3.3 Unusual values
# Outliers are observations that are unusual; 
# data points that don’t seem to fit the pattern.
# The only evidence of outliers is the unusually wide limits on the x-axis.
ggplot(diamonds) + 
  geom_histogram(mapping = aes(x = y), binwidth = 0.5)

# To make it easy to see the unusual values, we need to zoom to small 
# values of the y-axis with coord_cartesian():
ggplot(diamonds) + 
  geom_histogram(mapping = aes(x = y), binwidth = 0.5) +
  coord_cartesian(ylim = c(0, 50))

# filter out outliers
unusual <- diamonds %>% 
  filter(y < 3 | y > 20) %>% 
  select(price, x, y, z) %>%
  arrange(y)
unusual

# If you’ve encountered unusual values in your dataset, and simply want to 
# move on to the rest of your analysis, you have two options.
# Drop the entire row with the strange values:
diamonds2 <- diamonds %>% 
  filter(between(y, 3, 20))
# replace unusual values with missing values
diamonds2 <- diamonds %>% 
  mutate(y = ifelse(y < 3 | y > 20, NA, y))

# Shows a warning about the missing values
ggplot(data = diamonds2, mapping = aes(x = x, y = y)) + 
  geom_point()
#> Warning: Removed 9 rows containing missing values (geom_point).

# To suppress that warning, set na.rm = TRUE:
ggplot(data = diamonds2, mapping = aes(x = x, y = y)) + 
  geom_point(na.rm = TRUE)

# Other times you want to understand what makes observations with missing 
# values different to observations with recorded values.
#  For example, in nycflights13::flights, missing values in the 
# dep_time variable indicate that the flight was cancelled. So you might 
# want to compare the scheduled departure times for cancelled and 
# non-cancelled times. You can do this by making a new variable with is.na().
nycflights13::flights %>% 
  mutate(
    cancelled = is.na(dep_time),
    sched_hour = sched_dep_time %/% 100,
    sched_min = sched_dep_time %% 100,
    sched_dep_time = sched_hour + sched_min / 60
  ) %>% 
  ggplot(mapping = aes(sched_dep_time)) + 
  geom_freqpoly(mapping = aes(colour = cancelled), binwidth = 1/4)


# 7.5 Covariation
# Covariation is the tendency for the values of two or more variables 
# to vary together in a related way. 

# 7.5.1 A categorical and continuous variable
# The default appearance of geom_freqpoly() is not that useful for that sort of
# comparison because the height is given by the count. That means if one of the 
# groups is much smaller than the others, it’s hard to see the differences in shape.
ggplot(data = diamonds, mapping = aes(x = price)) + 
  geom_freqpoly(mapping = aes(colour = cut), binwidth = 500)

ggplot(diamonds) + 
  geom_bar(mapping = aes(x = cut))

# To make the comparison easier we need to swap what is displayed on the y-axis.
# Instead of displaying count, we’ll display density, which is the count 
# standardised so that the area under each frequency polygon is one.
ggplot(data = diamonds, mapping = aes(x = price, y = ..density..)) + 
  geom_freqpoly(mapping = aes(colour = cut), binwidth = 500)

#  Boxplot: a type of visual shorthand for a distribution of values that 
# is popular among statisticians.
ggplot(data = diamonds, mapping = aes(x = cut, y = price)) +
  geom_boxplot()

ggplot(data = mpg, mapping = aes(x = class, y = hwy)) +
  geom_boxplot()

# To make the trend easier to see, we can reorder class based on the 
# median value of hwy:
ggplot(data = mpg) +
  geom_boxplot(mapping = aes(x = reorder(class, hwy, FUN = median), y = hwy))

# If you have long variable names, geom_boxplot() will work better if you 
# flip it 90°. You can do that with coord_flip().
ggplot(data = mpg) +
  geom_boxplot(mapping = aes(x = reorder(class, hwy, FUN = median), y = hwy)) +
  coord_flip()

# 7.5.2 Two categorical variables
# To visualise the covariation between categorical variables, you’ll need to 
# count the number of observations for each combination. One way to do that is 
# to rely on the built-in geom_count():
ggplot(data = diamonds) +
  geom_count(mapping = aes(x = cut, y = color))

# Another approach is to compute the count with dplyr:
diamonds %>% 
  count(color, cut)

# Then visualise with geom_tile() and the fill aesthetic:
diamonds %>% 
  count(color, cut) %>%  
  ggplot(mapping = aes(x = color, y = cut)) +
  geom_tile(mapping = aes(fill = n))

# 7.5.3 Two continuous variables
# draw a scatterplot with geom_point(). You can see covariation as a pattern 
# in the points. For example, you can see an exponential relationship between 
# the carat size and price of a diamond.
ggplot(data = diamonds) +
  geom_point(mapping = aes(x = carat, y = price))

ggplot(data = diamonds) + 
  geom_point(mapping = aes(x = carat, y = price), alpha = 1 / 100)

# geom_bin2d() creates rectangular bins
# geom_hex() creates hexagonal bins
ggplot(data = smaller) +
  geom_bin2d(mapping = aes(x = carat, y = price))

# install.packages("hexbin")
library(hexbin)
ggplot(data = smaller) +
  geom_hex(mapping = aes(x = carat, y = price))

# Another option is to bin one continuous variable so it acts 
# like a categorical variable
ggplot(data = smaller, mapping = aes(x = carat, y = price)) + 
  geom_boxplot(mapping = aes(group = cut_width(carat, 0.1)))

# Another approach is to display approximately the same number of
# points in each bin. That’s the job of cut_number():
ggplot(data = smaller, mapping = aes(x = carat, y = price)) + 
  geom_boxplot(mapping = aes(group = cut_number(carat, 20)))

# 7.6 Patterns and model
# A scatterplot of Old Faithful eruption lengths vs the wait time between eruptions 
# shows a pattern: longer wait times are associated with longer eruptions
ggplot(data = faithful) + 
  geom_point(mapping = aes(x = eruptions, y = waiting))

# The following code fits a model that predicts price from carat and then 
# computes the residuals (the difference between the predicted value and the 
# actual value). The residuals give us a view of the price of the diamond, once 
# the effect of carat has been removed.
library(modelr)

mod <- lm(log(price) ~ log(carat), data = diamonds)

diamonds2 <- diamonds %>% 
  add_residuals(mod) %>% 
  mutate(resid = exp(resid))

ggplot(data = diamonds2) + 
  geom_point(mapping = aes(x = carat, y = resid))

# Once you’ve removed the strong relationship between carat and price, you can 
# see what you expect in the relationship between cut and price: relative to 
# their size, better quality diamonds are more expensive.
ggplot(data = diamonds2) + 
  geom_boxplot(mapping = aes(x = cut, y = resid))

# 7.7 ggplot2 calls
ggplot(data = faithful, mapping = aes(x = eruptions)) + 
  geom_freqpoly(binwidth = 0.25)

# Rewriting the previous plot more concisely yields:
ggplot(faithful, aes(eruptions)) + 
  geom_freqpoly(binwidth = 0.25)

diamonds %>% 
  count(cut, clarity) %>% 
  ggplot(aes(clarity, cut, fill = n)) + 
  geom_tile()













