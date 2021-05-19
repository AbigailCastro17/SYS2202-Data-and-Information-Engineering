# Abigail Castro - abc3gnm
# Summary and code

# Chapter 3 Data Visualization
# Mainly focus on ggplot2
# Load tidyverse library
library(tidyverse)

# Data frame: rectangular collection of variables (columns)
# and observations (rows)

# The mpg data frame contains observations collected by the
# US Environmental Protection Agency on 38 models of cars.
ggplot2::mpg
# Variables:
# displ: car's engine size (litres)
# hwy: car's fuel efficiency on the highway (mpg)

# learn more about mpg
?mpg

# Plots mpg w/ displ on x-axis and hwy on y-axis
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy))
# The plot shows a negative relationship between engine
# size (displ) and fuel efficiency (hwy)

# ggplot() creates a coordinate system
#   The first argument is the data set to use in the graph,
#   but it creates an empty graph

# geom_point() adds a layer of points to the plot
#   Takes a mapping argument, which defines how variables 
#   are mapped to visual properties.
#   The mapping argument is always paired with aes(), and
#   the x and y arguments of aes() specify which variables to
#   map on the x and y axes.

# A graphing template:(can this all be in one line?)
# ggplot(data = <DATA>) + 
# <GEOM_FUNCTION>(mapping = aes(<MAPPINGS>))

# 3.3 Aesthetic mappings
# The class variable of mpg data set classifies cars into groups
# You can add a third variable to a two dimensional scatterplot
# by mapping it to an aesthetic such as size, shape or color of the points.

# For example, you can map the colors of your points 
# to the class variable to reveal the class of each car.
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, color = class))
# To map an aesthetic to a variable, associate the name of 
# the aesthetic to the name of the variable inside aes().
# We get a warning here, because mapping an unordered variable (class) 
# to an ordered aesthetic (size) is not a good idea.

ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, size = class))
#> Warning: Using size for a discrete variable is not advised.

# Alpha aesthetic: controls transparency of points
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, alpha = class))

# Shape: Only six shapes at a time
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy, shape = class))

# To set an aesthetic manually, set the aesthetic by name as an argument 
# of your geom function; i.e. it goes outside of aes().
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy), color = "blue")

# 3.4 Common Problems
# misplaced characters
# Check the left-hand of your console: if it’s a +, it means that R 
# doesn’t think you’ve typed a complete expression and it’s waiting 
# for you to finish it. 
# Press ESCAPE to abort current command

# '+' must come at the end of the line, so the next two lines will not run
#ggplot(data = mpg) 
#+ geom_point(mapping = aes(x = displ, y = hwy))

# get help about any R function by running ?function_name

# 3.5 Facets
# Another wat to add additional variables is to split the plot 
# into facets, subplots that each display one subset of the data.

#To facet your plot by a single variable, use facet_wrap(). The first 
# argument of facet_wrap() should be a formula, which you create with ~ 
# followed by a variable name. The variable that you pass to facet_wrap()
# should be discrete.
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy)) + 
  facet_wrap(~ class, nrow = 2)

# To facet your plot on the combination of two variables, add facet_grid() 
# to your plot call. The first argument of facet_grid() is also a formula. 
# This time the formula should contain two variable names separated by a ~.
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy)) + 
  facet_grid(drv ~ cyl)

# If you prefer to not facet in the rows or columns dimension, use a . instead 
# of a variable name, e.g. + facet_grid(. ~ cyl).
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy)) + 
  facet_grid(. ~ cyl)

# 3.6 Geometric objects
# geom: the geometrical object that a plot uses to represent data.
# To change the geom in your plot, change the geom function that 
# you add to ggplot(). + geom_geomType(mapping = aes (x = displ, y = hwy))

# Point geom
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy))

# Smooth geom
ggplot(data = mpg) + 
  geom_smooth(mapping = aes(x = displ, y = hwy))

# One can set the linetype of a line. geom_smooth() will draw a different line,
# with a different linetype, for each unique value of the variable that you 
# map to linetype.
ggplot(data = mpg) + 
  geom_smooth(mapping = aes(x = displ, y = hwy, linetype = drv))

# normal
ggplot(data = mpg) +
  geom_smooth(mapping = aes(x = displ, y = hwy))

# Groups without distinction
ggplot(data = mpg) +
  geom_smooth(mapping = aes(x = displ, y = hwy, group = drv))

# Groups with distinction
ggplot(data = mpg) +
  geom_smooth(
    mapping = aes(x = displ, y = hwy, color = drv),
    show.legend = FALSE
  )

# To display multiple geoms in the same plot, 
# add multiple geom functions to ggplot():
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy)) +
  geom_smooth(mapping = aes(x = displ, y = hwy))
# The above code is inefficient due to code duplication, 
# changes would be harder to implement.

# Avoid duplication by passing a set of mappings to ggplot()
# uses the mappings as global mappings that apply to each geom in graph
ggplot(data = mpg, mapping = aes(x = displ, y = hwy)) + 
  geom_point() + 
  geom_smooth()

# If you place mappings in a geom function, ggplot2 will treat them as 
# local mappings for the layer. It will use these mappings to extend or 
# overwrite the global mappings for that layer only.
ggplot(data = mpg, mapping = aes(x = displ, y = hwy)) + 
  geom_point(mapping = aes(color = class)) + 
  geom_smooth()

# Here, our smooth line displays just a subset of the mpg dataset, 
# the subcompact cars. The local data argument in geom_smooth() overrides 
# the global data argument in ggplot() for that layer only.
ggplot(data = mpg, mapping = aes(x = displ, y = hwy)) + 
  geom_point(mapping = aes(color = class)) + 
  geom_smooth(data = filter(mpg, class == "subcompact"), se = FALSE)

# 3.7 Statistical transformations
# bar graph of diamonds dataset
ggplot(data = diamonds) + 
geom_bar(mapping = aes(x = cut))

#bar charts, histograms, and frequency polygons bin your data and then 
# plot bin counts, the number of points that fall in each bin.

# smoothers fit a model to your data and then plot predictions from the model.

# boxplots compute a robust summary of the distribution 
# and then display a specially formatted box.

# You can generally use geoms and stats interchangeably. For example, 
# you can recreate the previous plot using stat_count() instead of geom_bar():
ggplot(data = diamonds) + 
  stat_count(mapping = aes(x = cut))

# This works because every geom has a default stat; 
# and every stat has a default geom.

# Three reasons one might need to use a stat explicitly
#   To override the default stat.
demo <- tribble(
  ~cut,         ~freq,
  "Fair",       1610,
  "Good",       4906,
  "Very Good",  12082,
  "Premium",    13791,
  "Ideal",      21551
)

ggplot(data = demo) +
  geom_bar(mapping = aes(x = cut, y = freq), stat = "identity")

#   To override the default mapping from transformed variables to aethetics
ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, y = stat(prop), group = 1))

#   To draw greater attention to the statistical transformation in your code
ggplot(data = diamonds) + 
  stat_summary(
    mapping = aes(x = cut, y = depth),
    fun.min = min,
    fun.max = max,
    fun = median
  )

# 3.8 Position adjustments

# Color a bar chart using either colour aesthetic or more usefully fill
# Outlines the bars in different colors
ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, colour = cut))
# Fills the bars in different colors
ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, fill = cut))

# map the fill aesthetic to another variable, like clarity: 
# the bars are automatically stacked.
ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, fill = clarity))

# The stacking is performed automatically by the position adjustment 
# specified by the position argument.
# If you don’t want a stacked bar chart, you can use one of three other options:
# "identity", "dodge" or "fill".

# position = "identity" will place each object exactly where it falls in 
# the context of the graph. This is not very useful for bars, because it
# overlaps them. To see that overlapping we either need to make the bars 
# slightly transparent by setting alpha to a small value, or completely 
# transparent by setting fill = NA.

# changes transparency
ggplot(data = diamonds, mapping = aes(x = cut, fill = clarity)) + 
  geom_bar(alpha = 1/5, position = "identity")
# outlines each rectangle, no fill
ggplot(data = diamonds, mapping = aes(x = cut, colour = clarity)) + 
  geom_bar(fill = NA, position = "identity")

# position = "identity" will place each object exactly where it falls 
# in the context of the graph. This is not very useful for bars, because 
# it overlaps them. To see that overlapping we either need to make the bars 
# slightly transparent by setting alpha to a small value, or completely 
# transparent by setting fill = NA.
ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, fill = clarity), position = "fill")

# position = "dodge" places overlapping objects directly beside one another. 
# This makes it easier to compare individual values.
ggplot(data = diamonds) + 
  geom_bar(mapping = aes(x = cut, fill = clarity), position = "dodge")

# Overplotting: values are rounded so mant points overlap each other
# Avoid this by using position = "jitter" which adds a small amount of noise
# to each point, thereby spreading the points out, so they don't overlap.
ggplot(data = mpg) + 
  geom_point(mapping = aes(x = displ, y = hwy), position = "jitter")

# 3.9 Coordinate systems

# default coordinate system: Cartesian
# coord_flip() switches the x and y axes
#   Useful for horizintal boxplots, or long labels
# Default
ggplot(data = mpg, mapping = aes(x = class, y = hwy)) + 
  geom_boxplot()
# Using coord_flip()
ggplot(data = mpg, mapping = aes(x = class, y = hwy)) + 
  geom_boxplot() +
  coord_flip()

# coord_quickmap() sets the aspect ratio correctly for maps
nz <- map_data("nz")
# Original
ggplot(nz, aes(long, lat, group = group)) +
  geom_polygon(fill = "white", colour = "black")
# Using coord_quickmap()
ggplot(nz, aes(long, lat, group = group)) +
  geom_polygon(fill = "white", colour = "black") +
  coord_quickmap()

# coord_polar() uses polar coordinates
bar <- ggplot(data = diamonds) + 
  geom_bar(
    mapping = aes(x = cut, fill = cut), 
    show.legend = FALSE,
    width = 1
  ) + 
  theme(aspect.ratio = 1) +
  labs(x = NULL, y = NULL)

bar + coord_flip()
bar + coord_polar()

# 3.10 The layered grammar of graphics
# Template
#ggplot(data = <DATA>) + 
#  <GEOM_FUNCTION>(
#    mapping = aes(<MAPPINGS>),
#    stat = <STAT>, 
#    position = <POSITION>
#  ) +
#  <COORDINATE_FUNCTION> +
#  <FACET_FUNCTION>

# How to build a basic plot from scratch
#   Transform dataset into the information you wish to display
#   Choose a geometric object to represent each observation
#   Add aesthetics
#   Select a coordinate system
#   Adjust positions of the geoms within the plot (position adjustment)
#   Split the graph into subplots (faceting)
