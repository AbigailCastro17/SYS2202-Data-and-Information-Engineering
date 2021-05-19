# You can convert this into an .Rmd if you are comfortable with it. 
# The name of your file should include your Zoom team number. Please make sure to pick a consistent name with your teammates.  
# ----------------------------------------------------------
library(dplyr)
library(nycflights13)
library(tidyverse)

View(flights)
?flights

# Imagine you are hired as a Systems Engineer to optimize the air-traffic at New York airports. 
# First you need to identify what needs to be optimized:
# Brainstorm about potential problems in air traffic. Write down at least 3 potential problems. 

# 1. Bad weather
# 2. Destination
# 3. Time of Year

# Time of Year
#   Month, maybe more flights during certain months
#   Data sources


# Distance
#   Longer flights, more likely to cancel

# Time of Day
#   Later flights more likely to cancel
# Ww want to see if later flights cause more problems

# For each item in your list of problems, 
  # identify data sources in nycflights13 that can be helpful in your analysis. 
  # Write down the rationale for why/how you think this data will help demonstrate the problem.
  # List the set of data transformation techniques you may need for each, e.g., what new variables/attributes do you need to create? what combination of data columns will you need? etc.
  # List the visualization techniques you can use to demonstrate the problem, trends, etc. What will each visualization show?  

# Write an R script for demonstrating the first problem in your list.
flights <- within(flights, month <- factor(month, levels = c("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "NA"), ordered = TRUE))
ggplot(data = flights) + 
  geom_bar(mapping = aes(x = month))

flights <- within(flights, origin <- factor(origin))
ggplot(data = flights) + 
  geom_bar(mapping = aes(x = origin))

ggplot(data = flights) + 
  geom_point(mapping = aes(x = distance, y = dep_delay, color = origin, na.rm = FALSE))

ggplot(data = flights) + 
  geom_point(mapping = aes(x = distance, y = arr_delay, color = dest))

ggplot(data = flights, aes(x = dep_delay, y = arr_delay, color = origin, na.rm = TRUE)) + 
  geom_point() +
  geom_smooth()

