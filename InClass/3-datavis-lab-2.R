## Birth Months and Baseball
# The birth month hypothesis says players who were born in early months of the year tend to get further in sports.
# The `Lahman` package has lots of baseball data. Can you test the hypothesis by only using data from the Master dataset?

library(tidyverse)


#1. Install the `Lahman` R package if you haven't

#2. Load the `Lahman` package and the `Master` data
library(Lahman)

#3. Take a peek at the Master data. What data columns are relevant to test the hypothesis?
data(Master)
Master
?Master
view(Master)
# birthMonth


#4. What calculations do we need to perform? Try them if necessary.
# To test the hypothesis, we can count the number of players born in each 
# month and see if there's a significant difference.
# This code changes the data type to character so that the categories
# will be kept.
Master$birthMonth <- as.character(Master$birthMonth)
class(Master$birthMonth)


Master$debut <- as.Date(Master$debut, format('%Y-%m-%d'))
class(Master$debut)
Master$finalGame <- as.Date(Master$finalGame, format('%Y-%m-%d'))
class(Master$finalGame)
Master$careerLength <- Master$finalGame - Master$debut
class(Master$careerLength)
Master$careerLength <- as.numeric(Master$careerLength, units="days")
  
#5. What type of plot should we make to explore/test the hypothesis? Try to make the plot.
Master <- within(Master, birthMonth <- factor(birthMonth, levels = c("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "NA"), ordered = TRUE))
ggplot(data = Master) + 
  geom_bar(mapping = aes(x = birthMonth))

ggplot(data = Master, aes(x=as.factor(birthMonth), y=careerLength)) + 
  geom_boxplot(fill="slateblue", alpha=0.2) + 
  xlab("birthMonth")

boxplot(careerLength~birthMonth,
        data = Master,
        main = "Different boxplots for each month",
        xlab = "Birth Month",
        ylab = "Career Length",
        col = "red",
        border ="black"
)
  
#6. What does the plot show? Does it confirm the hypothesis? What evidence does it provide?
# The plot shows no significant difference in the number of players
# born in each month. There is not enough evidence to prove the hypothesis.

#7. Save the plot in a pdf format.
#The name of the plot should include your Zoom breakout room number, i.e. "Team1-plot1.pdf"
