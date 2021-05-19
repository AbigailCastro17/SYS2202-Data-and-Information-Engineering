###----------------------------------------------------------------------------
## In-class exercises
## Data Visualization
## 03-datavis-lab.R 
###----------------------------------------------------------------------------

#-- Load Required Packages
library(tidyverse)  # which loads ggplot2
library(gcookbook)


#--------------------------
## Your Turn: Scatterplots
#--------------------------
library(ggplot2)
data(mpg)
view(mpg)

# 1. Map a discrete variable to color, size, alpha, and shape. 
#    Then map a continuous variable to each. Does ggplot2 behave differently 
#    for discrete vs. continuous variables?
#    - The discrete variables in mpg are: manufacturer, model, trans, drv, fl, class. How can you determine that?
#    - The continuous variables in mpg are: displ, year, cyl, cty, hwy. How can you determine that?
# You can view variable values by executing mpg or look at the descriptions 
# by executing ?mpg.

# discrete
# color
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, color = class))
# size
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, size = class))
# alpha
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, alpha = class))
# shape
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, shape = class))
# Using aesthetics on discrete variables will have 

# continuous
# color
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, color = displ))
# size
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, size = displ))
# alpha
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, alpha = displ))
# shape - produces an error
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, shape = displ))
# Color, alpha and size can be used for continuous variables because they 
# can represent gradual change.

# 2. Map the same variable to multiple aesthetics in the same plot. Does it work? 
#    How many legends does ggplot2 create?
# Mapping the same variable to multiple aesthetics does work.

# Mapping a discrete variable to multiple aesthetics creates two legends.
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, color = class, shape = class))
# Mapping a continuous variable to multiple aesthetics creates one legends.
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, color = cyl, alpha = cyl))


# 3. Attempt to set an aesthetic to something other than a variable name, 
#    like `displ < 5`. What happens?
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, color = displ < 5))
# The aesthetics is mapped as whether the it meets the condition or not.

#4. Try to form a hypothesis based on the data. Get inspiration from the example in the book about the relationship between fuel consumption and the engine. 
# If the car has front wheel drive, then the fuel efficiency will be greater.
ggplot(data = mpg, mapping = aes(x = displ, y = hwy, color = drv, shape = drv)) + 
  geom_point() +
  geom_smooth()

#--------------------------
## Your Turn: Geoms and Layers
#--------------------------
library(gcookbook)


# 1. what does `method='lm'` do for `geom_smooth()`? Try it.
# without method='lm'
ggplot(data=heightweight, mapping = aes(x = heightIn, y = ageYear)) +
  geom_smooth() +
  geom_point()

# makes the trendline linear, lm probably means linear model
ggplot(data=heightweight, mapping = aes(x = heightIn, y = ageYear)) +
  geom_smooth(method='lm') + 
  geom_point()

# 2. What will this produce (describe in words). 
# This code produces a plot of height vs weight. The shape aesthetic will
# change the point shape based on sex. The color aesthetic will assign colors
# to each geom based on sex. Lastly, the fill aesthetic will make the
# spread of the trendlines the same color as the trendlines.
ggplot(heightweight, aes(x = heightIn, y = weightLb, 
                                shape=sex, color=sex)) +
  geom_smooth(aes(fill=sex)) + 
  geom_point()

#3. How do you interpret the trends you see in the above plot? What hypotheses can you form based on that? 
# As height increases, weight also increases. 


#4. Why will this not work
ggplot(mapping=aes(heightIn)) + 
  geom_point(data=heightweight, aes(y=weightLb)) + 
  geom_smooth()
# The data argument is not in the ggplot() function
# Did not use mapping = aes() in second line
# Did not specify an x argument in the




