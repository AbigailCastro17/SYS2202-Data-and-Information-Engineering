library(Lahman)
data(Master)
view(Master)
# If we define success as the amount of time in the league,
# we must first use the final game date and debut date to find
# the length of each players career.
# Since we are trying to see if the birth month impacts the
# chances of success, which is categorical and the measure of
# success is numerical, we should plot the data as box plots
# for each month.
# After the plotting the data, one could see visually whether
# a particular month has a better chance of success.
# One thing we should take into account is whether the data set
# has an equal number of players born in each month.