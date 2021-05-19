# Abigail Castro - abc3gnm
# Breakout Room 7


library(dplyr)
library(nycflights13)
library(tidyverse)

view(flights)

mean(data = flights, dep_delay)
#check if there are more flights leaving from EWR
flights <- within(flights, origin <- factor(origin))
ggplot(data = flights) + 
  geom_bar(mapping = aes(x = origin))


# departure delay average vs origin
# departure delay is about the same for all the origin airports
# 
#flights[flights < 0] <- NA
ggplot(data = flights, mapping = aes(x = origin, y = dep_delay)) +
  geom_boxplot() +
  ylim(-20,100)

# departure delay decreases as air time increases
ggplot(data = flights, aes(x = air_time, y = dep_delay)) + 
  geom_smooth()


