#- Load the fuel economy data
library(tidyverse) # note: mpg data is from ggplot2 package
data(mpg) # loads the data (not necessary, but helpful
# to specify)

#- Make plot
ggplot(data=mpg) +
  geom_point(aes(x=displ, y=hwy))

#- Save plot
ggsave("mpg.pdf")

#- Save data
write_csv(mpg, file = "mpg.csv")